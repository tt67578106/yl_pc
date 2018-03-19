package com.ylw.service.base;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ylw.entity.code.CodeAreaCity;
import com.ylw.service.code.CodeAreaCityService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.ylw.entity.base.Image;
import com.ylw.entity.base.Resume;
import com.ylw.entity.base.User;
import com.ylw.entity.base.UserProfile;
import com.ylw.repository.ApiAppDao;
import com.ylw.repository.ResumeDao;
import com.ylw.repository.UserDao;
import com.ylw.repository.UserProfileDao;
import com.ylw.util.Constants;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.MemcachedUtil;
import com.ylw.util.RandomSeriNoUtils;
import com.ylw.util.StoreCacheUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.json.JSONObject;

/**
 * 用户帐户相关
 * 
 * @author Nicolas
 *
 */
@Transactional
@Component
public class AccountService {
	private Logger logger = LoggerFactory.getLogger(AccountService.class);

	private static final int SALT_SIZE = 8;
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	
/*	private static final String FIND_RESUMEB_BY_WEBURL = "http://10.0.11.44:8080/api/v7/resume/findResumeByWeb";

	private static final String MODIFY_RESUME_BASEBY_WEB = "http://10.0.11.44:8080/api/v7/resume/modifyResumeBaseByWeb";

	private static final String MODIFY_RESUME_INTENTION_BYWEB = "http://10.0.11.44:8080/api/v7/resume/modifyResumeIntentionByWeb";
	                                                         
*/
	private UserDao userDao;
	private ResumeDao resumeDao;
	private UserProfileDao userProfileDao;
	private ApiAppDao apiAppDao;
	
	@Autowired
	private ResumeMdbService resumeMdbService;
	@Autowired
	private CodeAreaCityService codeAreaCityDao;

	@Autowired
	public void setApiAppDao(ApiAppDao apiAppDao) {
		this.apiAppDao = apiAppDao;
	}

	/**
	 * 根据手机号码查询是否存在用户简历
	 * 
	 * @param loginName
	 * @return
	 */
	public int countByLoginName(String loginName) {
		return userDao.countByLoginname(loginName);
	}

	/**
	 * 验证用户是否登录
	 * 
	 * @param cookieValue
	 * @param session
	 * @return
	 */
	public Object validateLogin(String cookieValue, HttpSession session) {
		User user = (User) session.getAttribute(Constants.SESSION_KEY_USER);
		if (user != null && user.getId() != null) {
			return user;
		} else if (user == null && StringUtils.isNotBlank(cookieValue)) {
			user = (User) MemcachedUtil.getCacheValue(cookieValue);
			if (user != null && user.getId() != null) {
				return user;
			} else if (user == null && StringUtils.isNotBlank(cookieValue)) {
				user = (User) MemcachedUtil.getCacheValue(cookieValue);
				if (user != null && user.getId() != null) {
					session.setAttribute(Constants.SESSION_KEY_USER, user);
					if(user!=null){
						JSONObject resultMap = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(user.getId()), user.getLoginname(), null,null);
						Resume resume = resumeMdbService.findResume(resultMap);
						if(resume!=null){
							Image head = resume.getImage();
							if(head!=null){
								session.setAttribute(Constants.SESSION_KEY_USER_HEAD_PATH,head.getImgpath());
							}
						}
					}
				}
				logger.info("使用Cookie强制登录 ");
				return user;
			}
		}
		return null;
	}
	
	/**
	 * 清除登录信息
	 * 
	 * @param cookieValue
	 * @param session
	 */
	public void clearLogin(String cookieValue, HttpSession session) {
		Enumeration<String> em = session.getAttributeNames();
		while (em.hasMoreElements()) {
			session.removeAttribute(em.nextElement().toString());
		}
		session.removeAttribute(Constants.SESSION_KEY_USER);
		session.invalidate();
		if (StringUtils.isNotBlank(cookieValue)) {
			MemcachedUtil.deleteCache(cookieValue);
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param request
	 * @param response
	 */
	public void loginUser(User user, HttpServletRequest request, HttpServletResponse response, String... remember) {
		String valiCookieValue = user.getLoginname().concat("_").concat(System.currentTimeMillis() + "");// request.getSession().getId();
		if (response != null) {
			Cookie cookie = new Cookie(Constants.COOKIE_KEY_USER_VERIFY, valiCookieValue);
			cookie.setMaxAge(36000 * 24 * 7);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		HttpSession session = request.getSession();
		session.setAttribute(Constants.SESSION_KEY_USER, user);
		
		//看手机号是否验证  没有验证修改状态   并且更新更新时间   2017-11-23 加
		if(user.getIsValidation() == 0){
			java.util.Date lastLoginTime =  new Date();
			java.util.Date updateTime =  new Date();
			Integer isValidation = 1;
			userDao.updateUser(lastLoginTime,updateTime,isValidation,user.getId());
		}
		
		JSONObject resultMap = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(user.getId()), user.getLoginname(), null,null);
		if(resultMap.get("status").equals(Constants.RETURN_STATUS_SUCCESS)){
			JSONObject dataObject = resultMap.getJSONObject("data");
			session.setAttribute(Constants.SESSION_KEY_USER_HEAD_PATH,dataObject.get("avatar"));
		}
	}

	/**
	 * 用户登录(新增意愿城市参数，用于EHR简历分区)
	 *
	 * @param user
	 * @param user
	 * @param request
	 * @param response
	 */
	public void loginUser(User user, HttpServletRequest request, HttpServletResponse response, String intention, String... remember) {

		try {
			CodeAreaCity city = null;
			if(StringUtils.isNotEmpty(intention)){
				String[] intention_array = intention.split(",");
				city = codeAreaCityDao.findByAbbreviation(intention_array[0]);
			}
			String valiCookieValue = user.getLoginname().concat("_").concat(System.currentTimeMillis() + "");// request.getSession().getId();
			if (response != null) {
                Cookie cookie = new Cookie(Constants.COOKIE_KEY_USER_VERIFY, valiCookieValue);
                cookie.setMaxAge(36000 * 24 * 7);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
			HttpSession session = request.getSession();
			session.setAttribute(Constants.SESSION_KEY_USER, user);

			//看手机号是否验证  没有验证修改状态   并且更新更新时间   2017-11-23 加
			if(user.getIsValidation() == 0){
                Date lastLoginTime =  new Date();
                Date updateTime =  new Date();
                Integer isValidation = 1;
                userDao.updateUser(lastLoginTime,updateTime,isValidation,user.getId());
            }

			JSONObject resultMap = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(user.getId()), user.getLoginname(), null,user.getUsername(), city);
			if(resultMap.get("status").equals(Constants.RETURN_STATUS_SUCCESS)){
                JSONObject dataObject = resultMap.getJSONObject("data");
                session.setAttribute(Constants.SESSION_KEY_USER_HEAD_PATH,dataObject.get("avatar"));
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户注册
	 * @param sourceActivityId//1，正常 2，30s报名 3，百日大战 4，百度推广
	 * @param name
	 * @param thekey
	 * @param appChannelCode
	 * @param sourceActivityId
	 * @param appChannelCode
	 * @param appChannelCode
	 * @param ip
	 * @return 1：注册成功 ； 2： 注册失败 ; 3： 登陆名已存在
	 */
	public User registerUser(String name,String fromKey,String appChannelCode,Integer sourceActivityId, User reguser, String thekey, String ip, Integer branchId) {
		String loginname=reguser.getLoginname();
		String password=reguser.getPassword();
		Cache cache = StoreCacheUtil.getCacheManager().getCache("smscache");
		Element ele = cache.get(loginname);
		if (ele != null) {
			String key = (String) ele.getObjectValue();// 存在cache中的key
			if (thekey.equals(key)) {
				// 验证码正确
//				if (userDao.getByLoginname(loginname) != null) {
//					return 3;// 登陆名已存在
//				}
				User user = new User();
				if(StringUtils.isNotEmpty(fromKey)){
					user.setFromKey(fromKey);
					user.setAppSourceCode(apiAppDao.getSourceCodeByAppKey(fromKey));
				}else{
					user.setAppSourceCode("ylife_recruit");
					user.setFromKey("00d55953f18911e594980800277a9591");
				}
				if(StringUtils.isNotEmpty(appChannelCode)){
					user.setAppChannelCode(appChannelCode);
				}
				user.setSourceActivityId(sourceActivityId);
				entryptPassword(user, password);
				user.setFirstLoginTime(DateConvertUtils.getNow());
				user.setLastLoginIp(ip);
				user.setLastLoginTime(DateConvertUtils.getNow());
				user.setLoginname(loginname);
				if(StringUtils.isBlank(reguser.getUsername())){
					user.setUsername("YL_"+RandomSeriNoUtils.generateLowerString(5));
				}
				else{
					user.setUsername(reguser.getUsername());
				}
				user.setRegistertime(DateConvertUtils.getNow());
				user.setRegistertype(1);
				user.setRegisterBranchId(branchId);
				user.setRegisterSource(1);
				user.setBehavior("A_register");
				user.setStatus(1);
				user.setHasDownloadApp(2);//是否下载APP(1：已下载；2：未下载)
				user.setIsValidation(1);
				userDao.save(user);
			   
				/*Resume resume = new Resume();
				resume.setResumeCode(RandomSeriNoUtils.generateUUIDResumeCode());
				resume.setMobile(loginname);
				resume.setUserid(user.getId());
				resume.setCreatetime(DateConvertUtils.getNow());
				resume.setIsSynHr(1);
				resumeDao.save(resume);*/
				UserProfile userProfile=new UserProfile();//注册添加服务卡
				userProfile.setUserId(user.getId());
				userProfile.setMobile(loginname);
				userProfileDao.save(userProfile);
				return user;
			}
		}
		return null;
	}
	
	
	/**
	 * 百度推广注册  用户注册 不验证手机，注册未激活用户
	 * @param sourceActivityId//1，正常 2，30s报名 3，百日大战 4，百度推广
	 * @param loginname
	 * @param password
	 * @param ip
	 * @return 1：注册成功 ； 2： 注册失败 ; 3： 登陆名已存在
	 */
	public int registerUser(String fromKey,Integer sourceActivityId, User reguser, String ip, Integer branchId) {
		String loginname=reguser.getLoginname();
		String password=reguser.getPassword();
		if (userDao.getByLoginname(loginname) != null) {
			return 3;// 登陆名已存在
		}
		User user = new User();
		if(StringUtils.isNotEmpty(fromKey)){
			user.setFromKey(fromKey);
			user.setAppSourceCode(apiAppDao.getSourceCodeByAppKey(fromKey));
		}else{
			user.setAppSourceCode("ylife_recruit");
			user.setFromKey("00d55953f18911e594980800277a9591");
		}
		user.setBehavior("A_register");
		user.setSourceActivityId(sourceActivityId);
		entryptPassword(user, password);
		user.setFirstLoginTime(DateConvertUtils.getNow());
		user.setLastLoginIp(ip);
		user.setLastLoginTime(DateConvertUtils.getNow());
		user.setLoginname(loginname);
		if(StringUtils.isBlank(reguser.getUsername())){
			user.setUsername("YL_"+RandomSeriNoUtils.generateLowerString(5));
		}
		else{
			user.setUsername(reguser.getUsername());
		}
		user.setRegistertime(DateConvertUtils.getNow());
		user.setRegistertype(1);
		user.setRegisterBranchId(branchId);
		user.setRegisterSource(1);
		user.setHasDownloadApp(2);//是否下载APP(1：已下载；2：未下载)
		user.setStatus(0);
		user.setIsValidation(1);
		userDao.save(user);
		
		//---------------------------根据优蓝网用户id和手机号查询简历[get]------
		if(reguser.getId() != null){
			JSONObject resultMap = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(reguser.getId()), loginname, null,null);
			if(resultMap.get("status").equals(Constants.RETURN_STATUS_SUCCESS)){
				// 获取data
				JSONObject dataObject = resultMap.getJSONObject("data");
				// 获取data里面 resume_info_id
				final String resume_info_id = (String) dataObject.get("id");
				Map<String, String> resumeMap = new HashMap<String, String>();
				if(StringUtils.isNotBlank(reguser.getUsername())){
					resumeMap.put("full_name", reguser.getUsername());
					resumeMdbService.modifyResumeBaseMdb(String.valueOf(reguser.getId()),loginname,resume_info_id,resumeMap);
				}
				
			}
		}
		//---------------------------------	---------------------											
		/*Resume resume = new Resume();
		resume.setResumeCode(RandomSeriNoUtils.generateUUIDResumeCode());
		resume.setMobile(loginname);
		resume.setUserid(user.getId());
		resume.setCreatetime(DateConvertUtils.getNow());
		resume.setIsSynHr(1);
		resumeDao.save(resume);*/
		UserProfile userProfile=new UserProfile();//注册添加服务卡
		userProfile.setUserId(user.getId());
		userProfile.setMobile(loginname);
		userProfileDao.save(userProfile);
		return 1;
	}

	/**
	 * 更改用户密码
	 * 
	 * @param loginname
	 * @param password
	 * @return
	 */
	public User changePwd(String loginname, String password) {
		User user = userDao.getByLoginname(loginname);
		if (user != null) {
			entryptPassword(user, password);
			if(user.getStatus()!=-1){//非冻结，即激活
				user.setStatus(1);
			}
			return userDao.save(user);
		}
		return null;
	}

	/**
	 * 忘记密码逻辑
	 * 
	 * @param loginname
	 * @param thekey
	 * @return
	 */
	public int forgetPwd(String loginname, String thekey) {
		Cache cache = StoreCacheUtil.getCacheManager().getCache("smscache");
		Element ele = cache.get(loginname);
		if (ele == null) {
			return 0;
		}
		String key = (String) ele.getObjectValue();// 存在cache中的key
		if (thekey.equals(key)) {// 成功
			return 1;
		}
		return 0;
	}

	/**
	 * 保存简历
	 * @param userId
	 * @param resume
	 */
	public void saveProfile(Integer userId, Resume resume) {
//		List<Resume> tlist = resumeDao.findByUserid(userId);
//		Resume dbResume = tlist.size() > 0 ? tlist.get(0) : new Resume();
//		dbResume.setUserid(userId);
//		if(resume.getAge()!=null){
//			dbResume.setAge(resume.getAge());
//		}
//		if(resume.getGender()!=null){
//			dbResume.setGender(resume.getGender());
//		}
//		if(resume.getIdCard()!=null){
//			dbResume.setIdCard(resume.getIdCard());
//		}
//		if(resume.getNation()!=null){
//			dbResume.setNation(resume.getNation());
//		}
//		if(resume.getIntentionIndustry()!=null){
//			dbResume.setIntentionIndustry(resume.getIntentionIndustry());
//		}
//		if(resume.getIntentionSalary()!=null){
//			dbResume.setIntentionSalary(resume.getIntentionSalary());
//		}
//		if(resume.getName()!=null){
//			dbResume.setName(resume.getName());
//		}
//		if(resume.getNativeCountyid()!=null){
//			dbResume.setNativeCountyid(resume.getNativeCountyid());
//		}
//		if(resume.getNativeProvinceid()!=null){
//			dbResume.setNativeProvinceid(resume.getNativeProvinceid());
//		}
//		if(resume.getNativeCityid()!=null){
//			dbResume.setNativeCityid(resume.getNativeCityid());
//		}
//		if(resume.getQq()!=null){
//			dbResume.setQq(resume.getQq());
//		}
//		if(resume.getEducation()!=null){
//			dbResume.setEducation(resume.getEducation());
//		}
//		if(resume.getResidentCityid()!=null){
//			dbResume.setResidentCityid(resume.getResidentCityid());
//		}
//		if(resume.getResidentCountyid()!=null){
//			dbResume.setResidentCountyid(resume.getResidentCountyid());
//		}
//		if(resume.getResidentProvinceid()!=null){
//			dbResume.setResidentProvinceid(resume.getResidentProvinceid());
//		}
//		if(resume.getJobTarget()!=null){
//			dbResume.setJobTarget(resume.getJobTarget());
//		}
//		if(resume.getJobTargetProvinceid()!=null){
//			dbResume.setJobTargetProvinceid(resume.getJobTargetProvinceid());
//		}
//		if(resume.getJobTargetCityid()!=null){
//			dbResume.setJobTargetCityid(resume.getJobTargetCityid());
//		}
//		if(resume.getJobTargetCountyid()!=null){
//			dbResume.setJobTargetCountyid(resume.getJobTargetCountyid());
//		}
//		if (dbResume.getResumeCode() == null || dbResume.getResumeCode().isEmpty()) {
//			dbResume.setResumeCode(RandomSeriNoUtils.generateUUIDResumeCode());
//		}
//		dbResume.setIsSynHr(1);
		//---------------------------保存简历(同步web)-------------------------------------------
		resumeMdbService.saveProfileWeb(userId, resume);		
		//-----------------------------------------------------------------------------------												
		/*resumeDao.save(dbResume);*/
	}
	
	/**
	 * 快速注册用户
	 * 
	 * @param loginname
	 * @param username
	 * @param password
	 * @param ip
	 */
	public User quickregister(String loginname, String username, String password, String ip) {
		User user = new User();
		entryptPassword(user, password);
		user.setFirstLoginTime(DateConvertUtils.getNow());
		user.setLastLoginIp(ip);
		user.setLastLoginTime(DateConvertUtils.getNow());
		user.setLoginname(loginname);
		user.setUsername(username);
		user.setRegistertime(DateConvertUtils.getNow());
		user.setRegistertype(2);
		user.setRegisterSource(1);
		user.setStatus(1);
		user.setHasDownloadApp(2);//是否下载APP(1：已下载；2：未下载)
		user.setIsValidation(1);
		userDao.save(user);
		
		/*Resume resume = new Resume();
		resume.setUserid(user.getId());
		resume.setName(username);
		resume.setIsSynHr(1);
		resume.setCreatetime(DateConvertUtils.getNow());*/
		/*resumeDao.save(resume);*/
		//---------------------------快速注册用户同步简历------
		
		if(StringUtils.isNoneBlank(String.valueOf(user.getId())) && StringUtils.isNoneBlank(loginname)){
			JSONObject resultMap = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(user.getId()), loginname, null,null);
			if(resultMap.get("status").equals(Constants.RETURN_STATUS_SUCCESS)){
				// 获取data
				JSONObject dataObject = resultMap.getJSONObject("data");
				// 获取data里面 resume_info_id
				final String resume_info_id = (String) dataObject.get("id");
				Map<String, String> resumeMap = new HashMap<String, String>();
				if(StringUtils.isNotBlank(username)){
					resumeMap.put("full_name", username);
					resumeMdbService.modifyResumeBaseMdb(String.valueOf(user.getId()),loginname,resume_info_id,resumeMap);
				}
				
			}
		}
		
       //---------------------------------												
		return user;
	}
	
	/**
	 * 验证用户
	 * 
	 * @param loginname
	 * @param password
	 * @return 返回 null则验证失败
	 */
	public User verifyUser(String loginname, String password) {
		User user=userDao.getByLoginname(loginname);
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
	 * 更改用户密码
	 * 
	 * @param oldpwd
	 * @param newpwd
	 * @param userid
	 * @return 1 修改成功;2 密码错误修改失败
	 */
	public int changepwd(String oldpwd, String newpwd, Integer userid) {
		User user = userDao.findOne(userid);
		if (verifyPassword(oldpwd, user)) {
			entryptPassword(user, newpwd);
			userDao.save(user);
			return 1;
		} else {
			return 0;// 密码错误
		}
	}
	
	/**
	 * 短信验证更改管理，逻辑bug， TODO
	 * 
	 * @param loginname
	 * @param newpwd
	 * @param newpwd2
	 * @return
	 */
	@Deprecated
	public User changefgtpwd(String loginname, String newpwd, String newpwd2) {
		User user = userDao.getByLoginname(loginname);
		entryptPassword(user, newpwd);
		userDao.save(user);
		return user;
	}

	/**
	 * 验证明文密码是不是证用户密码
	 * 
	 * @param ming
	 * @param user
	 * @return true 相同，false 不同
	 */
	public boolean verifyPassword(String ming, User user) {
		String rawSalt = user.getSalt();
		byte[] salt = Encodes.decodeHex(rawSalt);
		byte[] hashPassword = Digests.sha1(ming.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(hashPassword).equals(user.getPassword());
	}

	/**
	 * 判断是否有相应用户名的用户
	 * 
	 * @param username
	 * @return
	 */
	public boolean hasUserByUsername(String username) {
		return userDao.getByLoginname(username) == null ? false : true;
	}
	
	/**
	 * 判断用户是否激活
	 * 
	 * @param username
	 * @return
	 */
	public User isActivate(String username) {
		return userDao.getByLoginname(username);
	}

	/**
	 * 判断是否有相应用户名的用户,有的返回id
	 * 
	 * @param username
	 * @return
	 */
	public Integer hasUserByUsernameForId(String username) {
		User user = userDao.getByLoginname(username);
		return user != null ? user.getId() : null;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user, String password) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setResumeDao(ResumeDao resumeDao) {
		this.resumeDao = resumeDao;
	}
	@Autowired
	public void setUserProfileDao(UserProfileDao userProfileDao) {
		this.userProfileDao = userProfileDao;
	}
	/**
	 * 快速登录、注册
	 * @param mobile
	 * @param ip
	 * @param branchId
	 * @return
	 */
	public User fastReg(String name,String fromKey,String appChannelCode,String mobile, String ip, int branchId,String cookieValue,String recSource) {
		User dbuser = userDao.getByLoginname(mobile);
		if (dbuser == null) {// 没注册
			String password =RandomSeriNoUtils.generateNumString(6);// 产生随机密码
			User user = new User();
			if(StringUtils.isNotEmpty(fromKey)){
				user.setFromKey(fromKey);
				user.setAppSourceCode(apiAppDao.getSourceCodeByAppKey(fromKey));
			}else{
				user.setAppSourceCode("ylife_recruit");
				user.setFromKey("00d55953f18911e594980800277a9591");
			}
			if(StringUtils.isNotEmpty(appChannelCode)){
				user.setAppChannelCode(appChannelCode);
			}
			if(StringUtils.isNotBlank(cookieValue)){
				user.setSourceActivityId(4);
				if(recSource.equals("wu")){
					user.setSourceActivityId(7);
				}
			}else{
				user.setSourceActivityId(1);
			}
			entryptPassword(user, password);
			user.setFirstLoginTime(DateConvertUtils.getNow());
			user.setLastLoginIp(ip);
			user.setLastLoginTime(DateConvertUtils.getNow());
			user.setLoginname(mobile);
			user.setUsername("YL_"+RandomSeriNoUtils.generateLowerString(5));
			user.setRegistertime(DateConvertUtils.getNow());
			user.setRegisterSource(1);
			user.setRegistertype(2);// 快捷注册
			user.setBehavior("B_register");
			user.setRegisterBranchId(branchId);
			user.setStatus(1);
			user.setHasDownloadApp(2);//是否下载APP(1：已下载；2：未下载)
			user.setIsValidation(1);
			user = userDao.save(user);
				//TODO
			/*Resume resume = new Resume();
			resume.setMobile(mobile);
			resume.setUserid(user.getId());
			resume.setCreatetime(DateConvertUtils.getNow());
			resume.setResumeCode(RandomSeriNoUtils.generateUUIDResumeCode());
			resume.setIsSynHr(1);
			resumeDao.save(resume);*/
			
			UserProfile userProfile=new UserProfile();//注册添加服务卡
			userProfile.setUserId(user.getId());
			userProfile.setMobile(mobile);
			userProfileDao.save(userProfile);
//			SMSUtil smsUtil =  new SMSUtil(mobile,password, "quick");
//			smsUtil.start();
			return user;
		} else {// 已注册
			return dbuser;
		}
	}

	
	
}
