package com.ylw.service.sys;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.ylw.entity.sys.SysUser;
import com.ylw.entity.sys.SysUserProfile;
import com.ylw.repository.SysUserDao;
import com.ylw.repository.SysUserProfileDao;
import com.ylw.util.Constants;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.IPUtil;
import com.ylw.util.MemcachedUtil;
import com.ylw.util.StoreCacheUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.spy.memcached.MemcachedClient;

/**
 * @author Nicolas.Cai
 * @version 1.0
 * @since 1.0
 */

@Component
@Transactional
public class SysUserService {

	private SysUserDao sysUserDao;
	
	private SysUserProfileDao sysUserProfileDao;
	
	private static final int SALT_SIZE = 8;
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	
	/**
	 * 通过id查询用户名
	 * @param id
	 * @return
	 */
	public String getUserNameById(Integer id){
		return sysUserDao.getUserNameById(id);
	}
	
	public SysUser getSysUser(java.lang.Integer id){
		return sysUserDao.findOne(id);
	}

	public void delete(java.lang.Integer id){
		sysUserDao.delete(id);
	}
	
	public SysUser getSysUserByLoginName(String loginName){
		return sysUserDao.findByLoginName(loginName);
	}
	
	/**
	 * 企业用户登录
	 * 
	 * @param user
	 * @param request
	 * @param response
	 */
	public void loginSysUser(SysUser user, HttpServletRequest request, HttpServletResponse response, String... remember) {
		String valiCookieValue = user.getLoginName().concat("_").concat(System.currentTimeMillis() + "");// request.getSession().getId();
		if (response != null) {
			Cookie cookie = new Cookie(Constants.COOKIE_KEY_ENTERPRISE_USER_VERIFY, valiCookieValue);
			cookie.setMaxAge(36000 * 24 * 7);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		request.getSession().setAttribute(Constants.SESSION_KEY_ENTERPRISE_USER, user);
		try {
			if (remember.length > 0) {
				MemcachedUtil.setCache(valiCookieValue, 36000, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//修改最后一次登录时间和最后一次登录ip
		SysUser dbUser=getSysUserByLoginName(user.getLoginName());
		if(dbUser!=null)
		{
			dbUser.setLastLoginTime(DateConvertUtils.getNow());
			dbUser.setLastLoginIp(IPUtil.getIpAddr(request));
			sysUserDao.save(dbUser);
		}
	}
	
	
	/**
	 * 验证用户
	 * @param loginname
	 * @param password
	 * @param role
	 * @return
	 */
	public SysUser verifySysUser(String loginname, String password) {
		SysUser user =sysUserDao.findByLoginNameAndRoleIds(loginname,"3");
		if (user != null) {
			String rawSalt = user.getSalt();
			byte[] salt = Encodes.decodeHex(rawSalt);
			byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
			if (Encodes.encodeHex(hashPassword).equals(user.getPassword())) {
				return user;// 成功验证
			} else {
				return null;// 验证失败,密码错误
			}
		} else {
			return null;// 不存在该用户
		}
	}
	
	/**
	 * 注册企业用户
	 * @param loginname
	 * @param thekey
	 * @param password
	 * @param ip
	 * @return 1：注册成功 ； 2： 注册失败 ; 3： 登陆名已存在
	 */
	public int registerSysUser(String mobile,String loginname, String thekey, String password, String ip) {
		Cache cache = StoreCacheUtil.getCacheManager().getCache("smscache");
		Element ele = cache.get(mobile);
		if (ele != null) {
			String key = (String) ele.getObjectValue();// 存在cache中的key
			if (thekey.equals(key)) {
				// 验证码正确
				if (sysUserDao.findByLoginName(loginname) != null) {
					return 3;// 登陆名已存在
				}
				SysUser user = new SysUser();
				user.setCreateTime(DateConvertUtils.getNow());
				user.setLastLoginIp(ip);
				user.setLastLoginTime(DateConvertUtils.getNow());
				user.setLoginName(loginname);
				user.setRoleIds("3");
				user.setUserName(loginname);
				user.setUpdateTime(DateConvertUtils.getNow());
				byte[] salt = Digests.generateSalt(SALT_SIZE);
				user.setSalt(Encodes.encodeHex(salt));
				byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
				user.setPassword(Encodes.encodeHex(hashPassword));
				sysUserDao.save(user);
				SysUserProfile userProfile = new SysUserProfile();
				userProfile.setMobile(mobile);
				userProfile.setUserId(user.getId());
				sysUserProfileDao.save(userProfile);
				return 1;
			}
		}
		return 0;
	}
	/**
	 * 更改用户密码
	 * 
	 * @param oldpwd
	 * @param newpwd
	 * @param userid
	 * @return 1 修改成功;2 密码错误修改失败
	 */
	public int changepwd(String oldpwd, String newpwd, Integer userid) {
		SysUser user = sysUserDao.findOne(userid);
		if (verifyPassword(oldpwd, user)) {
			entryptPassword(user, newpwd);
			sysUserDao.save(user);
			return 1;
		} else {
			return 0;// 密码错误
		}
	}
	
	/**
	 * 根据手机号得到SysUser
	 * @param mobile
	 * @return
	 */
	public SysUser getSysUserByMobile(String mobile) {
		SysUserProfile profile = sysUserProfileDao.getByMobile(mobile);
		if (profile != null) {
			return sysUserDao.findOne(profile.getUserId());
		}
		return null;
	}
	
	/**
	 * 更改企业用户密码
	 * 
	 * @param loginname
	 * @param password
	 * @return
	 */
	public SysUser changeSysUserPwd(String loginname, String password) {
		SysUser user = sysUserDao.findByLoginName(loginname);
		if (user != null) {
			byte[] salt = Digests.generateSalt(SALT_SIZE);
			user.setSalt(Encodes.encodeHex(salt));
			byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
			user.setPassword(Encodes.encodeHex(hashPassword));
			return sysUserDao.save(user);
		}
		return null;
	}
	
	/**
	 * 判断是否有相应用户名的企业用户
	 * 
	 * @param username
	 * @return
	 */
	public boolean hasSysUserByUsername(String username) {
		return sysUserDao.findByLoginName(username) == null ? false : true;
	}
	
	/**
	 * 验证企业用户是否登录
	 * 
	 * @param cookieValue
	 * @param session
	 * @return
	 */
	public Object validateSysUserLogin(HttpSession session) {
		SysUser user = (SysUser) session.getAttribute(Constants.SESSION_KEY_ENTERPRISE_USER);
		if (user != null && user.getId() != null) {
			return user;
		}
		return null;
	}
	
	/**
	 * 判断是手机号是否被绑定过
	 * 
	 * @param username
	 * @return
	 */
	public boolean hasSysUserByMobile(String mobile) {
		return sysUserProfileDao.getByMobile(mobile) == null ? false : true;
	}
	
	/**
	 * 验证明文密码是不是证用户密码
	 * 
	 * @param ming
	 * @param user
	 * @return true 相同，false 不同
	 */
	public boolean verifyPassword(String ming, SysUser user) {
		String rawSalt = user.getSalt();
		byte[] salt = Encodes.decodeHex(rawSalt);
		byte[] hashPassword = Digests.sha1(ming.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(hashPassword).equals(user.getPassword());
	}
	/**
	 * 查询分页对象
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<SysUser> getPage(Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<SysUser> spec = buildSpecification(searchParams);
		return sysUserDao.findAll(spec, pageRequest);
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(SysUser user, String password) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("loginName".equals(sortType)) {
			sort = new Sort(Direction.ASC, "loginName");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<SysUser> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<SysUser> spec = DynamicSpecifications.bySearchFilter(filters.values(),SysUser.class);
		return spec;
	}
	
	

		/**
	 * 清除登录信息
	 * 
	 * @param cookieValue
	 * @param session
	 */
	@SuppressWarnings("unchecked")
	public void clearLogin(String cookieValue, HttpSession session) {
		try {
			Enumeration<String> em = session.getAttributeNames();
			while (em.hasMoreElements()) {
				session.removeAttribute(em.nextElement().toString());
			}
			session.removeAttribute(Constants.SESSION_KEY_ENTERPRISE_USER);
			session.invalidate();
			if (StringUtils.isNotBlank(cookieValue)) {
				MemcachedUtil.deleteCache(cookieValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Autowired
	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	
	@Autowired
	public void setSysUserProfileDao(SysUserProfileDao sysUserProfileDao) {
		this.sysUserProfileDao = sysUserProfileDao;
	}
}