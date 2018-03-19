package com.ylw.web.home;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ylw.util.RandomSeriNoUtils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylw.entity.base.Branch;
import com.ylw.entity.base.User;
import com.ylw.service.base.AccountService;
import com.ylw.service.base.BranchService;
import com.ylw.service.base.CookieService;
import com.ylw.service.base.UserService;
import com.ylw.service.job.JobBaseService;
import com.ylw.service.sys.AppKeyService;
import com.ylw.util.Constants;
import com.ylw.util.IPUtil;
import com.ylw.util.StoreCacheUtil;

@Controller
public class PortalController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private UserService userService;
	@Autowired
	private AppKeyService appKeyService;
	@Autowired
	private CookieService cookieService;
	@Autowired
	private JobBaseService jobBaseService;
	private Pattern urlPattern = Pattern.compile("^"+Constants.DOMAIN_NAME+"|^www."+Constants.DOMAIN_NAME);
	/**
	 * 百度推广portal页面
	 * @return
	 */
	@RequestMapping(value="portal",method=RequestMethod.GET)
	public String portal(HttpServletRequest request){
		Branch branch = branchService.getBranchByRequest(request, IPUtil.getIpAddr(request));
		if(urlPattern.matcher(request.getServerName()).find()){
			return "redirect:/redirect?url=http://"+branch.getWebPrefix()+"."+Constants.DOMAIN_NAME+"/portal";
		}
		if(request.getServerName().indexOf(Constants.DOMAIN_NAME) > -1){
			if(branch.getId() == 1 && !request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "").equals("sh") && !request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "").equals("www")){
				String url = branchService.getBranchByRequest(request, IPUtil.getIpAddr(request)).getWebPrefix()+"."+Constants.DOMAIN_NAME+"/portal";
				return "redirect:/redirect?url=http://"+url;
			}
		}
		return "portal/"+branch.getWebPrefix();
	}
	
	/**
	 *  
	 * @param user 用户对象
	 * @param valiCode 计算验证码
	 * @param request
	 * @param response
	 * @return java.lang.String
	 * @throws Exception
	 */
	@RequestMapping(value="portal",method = RequestMethod.POST)
	public String register(@Valid User user, 
			@RequestParam(value="valiCode", required=false)String valiCode, 
			HttpServletRequest request, 
			HttpServletResponse response,Model model) throws Exception {
			Branch branch = branchService.getBranchByRequest(request, IPUtil.getIpAddr(request));
			if(StringUtils.isNotBlank(valiCode)){//验证码为空，不注册
				String sessionCode = request.getSession().getAttribute(Constants.SESSION_KEY_VALI_CODE_REG)+"";
				if(sessionCode.equalsIgnoreCase(valiCode)){
					int regUserResult = accountService.registerUser(null,4,user,IPUtil.getIpAddr(request), branch.getId());
					if (regUserResult == 1) {//注册成功自动登录
						return "redirect:/job"; //返回job列表页
					} else if (regUserResult == 3) {//手机号已注册
						model.addAttribute("err_loginname", "该手机号码已经被注册");
						return "portal/"+branch.getWebPrefix();
					}
				}else{
					model.addAttribute("err_valiCodeu", "验证码计算不正确");
					return "portal/"+branch.getWebPrefix();
				}
			}else{
				model.addAttribute("err_valiCodeu", "验证码计算不正确");
				return "portal/"+branch.getWebPrefix();
			}
		return null;
	}
	
	/**
	 * 落地页面
	 * @return
	 */
	@RequestMapping(value="promotion/ldpg6.html",method=RequestMethod.GET)
	public String ldpg6(@RequestParam(value="fk",required=false)String fromKey, 
	@RequestParam(value="cc",required=false)String appChannelCode,
	HttpServletRequest request,
	HttpServletResponse response,
	Model model){
		if(StringUtils.isNotBlank(fromKey)){
			if(appKeyService.validAppKey(fromKey)){
				cookieService.setCookie(Constants.COOKIE_KEY_FROM_KEY,fromKey,response);
			}else{
				return "redirect:/404";
			}
		}
		if(StringUtils.isNotEmpty(appChannelCode)){
			if(appKeyService.validAppChannelCode(appChannelCode)){
				cookieService.setCookie(Constants.COOKIE_KEY_APP_CHANNEL_CODE,appChannelCode,response);
			}else{
				return "redirect:/404";
			}
		}
		Branch branch = branchService.getBranchByRequest(request, IPUtil.getIpAddr(request));
		if(urlPattern.matcher(request.getServerName()).find()){
			return "redirect:/redirect?url=http://"+branch.getWebPrefix()+"."+Constants.DOMAIN_NAME+"/promotion/ldpg6.html";
		}
		if(request.getServerName().indexOf(Constants.DOMAIN_NAME) > -1){
			if(branch.getId() == 1 && !request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "").equals("sh") && !request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "").equals("www")){
				String url = branchService.getBranchByRequest(request, IPUtil.getIpAddr(request)).getWebPrefix()+"."+Constants.DOMAIN_NAME+"/promotion/ldpg6.html";
				return "redirect:/redirect?url=http://"+url;
			}
		}
		model.addAttribute("newJobs", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_NEW_BOTTOM,branch!=null?branch.getId():null,1,6,"updatetime","bottom"));
		return "portal/ldpg6";
	}
	
	
	/**
	 * 落地页面报名
	 * @param fromKey
	 * @param appChannelCode
	 * @param name
	 * @param mobile
	 * @param thekey
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "ldpg/signUp", method = RequestMethod.POST)
	public String signupjob(@CookieValue(value=Constants.COOKIE_KEY_FROM_KEY,required=false)String fromKey,
			@CookieValue(value=Constants.COOKIE_KEY_APP_CHANNEL_CODE,required=false)String appChannelCode,
			String name, String mobile, String thekey, 
			HttpServletRequest request, HttpServletResponse response) {
		String result = null;
		int branchId = branchService.getBranchIdByRequest(request, IPUtil.getIpAddr(request));
		User users = userService.getByLoginname(mobile);
		if(users == null){
			Cache cache = StoreCacheUtil.getCacheManager().getCache("smscache");
			Element ele = cache.get(mobile);
			if (ele == null) {
				return Constants.RETURN_STATUS_FAILURE;
			}
			String rightkey = (String) ele.getObjectValue();
			if (!rightkey.equals(thekey)) {// 验证码出错
				return Constants.RETURN_STATUS_FAILURE;
			}
			String password = RandomSeriNoUtils.genCodeByTime("");//"123456";// 设置默认密码
			User user = userService.registerUser(fromKey, mobile, name, password, IPUtil.getIpAddr(request), branchId, null, null);
			JSONObject resumeMdb = null;
			if(user != null){
				resumeMdb = userService.findResumeMdb(user, mobile, name, null, null);
			}
			if(resumeMdb.get("status").equals(Constants.RETURN_STATUS_SUCCESS)){
				result = userService.signUp30s(user, null, fromKey, appChannelCode, mobile, IPUtil.getIpAddr(request), name, null, branchId,
						null, null, null);
			}
		}
//		String result = userService.signUp30s("A_lpjobsignup",thekey,fromKey,appChannelCode,mobile, IPUtil.getIpAddr(request), null, name, null, request, response, branchId,null,null);
		return result;
	}
}
