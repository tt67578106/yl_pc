package com.ylw.web.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ylw.service.base.UserService;
import com.ylw.service.utm.UtmLogService;
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

import com.ylw.entity.base.User;
import com.ylw.service.base.AccountService;
import com.ylw.service.base.BranchService;
import com.ylw.service.base.ResumeMdbService;
import com.ylw.service.sys.SysUserService;
import com.ylw.util.Constants;
import com.ylw.util.IPUtil;
import com.ylw.util.SMSUtil;
import com.ylw.util.SmsSafeUtil;
import com.ylw.util.StoreCacheUtil;


@Controller
@RequestMapping("register")
public class RegisterController {
	@Autowired
	private BranchService branchServce;
	@Autowired
	private AccountService accountService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ResumeMdbService resumeMdbService;
	@Autowired
	private UtmLogService utmLogService;
	@Autowired
	private UserService userService;
//	@Autowired
//	private AccessLogService accessLogService;
//	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 注册跳转
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String registerForm(@RequestParam(value="role",required=false) String role,Model model,HttpServletRequest request){
		//accessLogService.addAccessLog(request,9,null);
		if(role!=null&&role.equals("enterprise")){
			model.addAttribute("enterprise_current", "current");
			model.addAttribute("reg_enterprise", "show");
		}else{
			model.addAttribute("user_current", "current");
			model.addAttribute("reg_user", "show");
		}
		return "account/register";
	}
	
	/**
	 *  注册 如果role没有传并且不为enterprise的时候为普通用户，否则为企业用户
	 * @param role 角色
	 * @param user 用户对象
	 * @param thekey 短信验证码
	 * @param valiCode 计算验证码
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(method = RequestMethod.POST)
//	public String register(@RequestParam(value = "mobile", required = false) String mobile, 
//			@Valid User user, 
//			String thekey, 
//			HttpServletRequest request,
//			HttpServletResponse response,
//			@CookieValue(value=Constants.COOKIE_KEY_FROM_KEY,required=false)String fromKey,
//			@CookieValue(value=Constants.COOKIE_KEY_APP_CHANNEL_CODE,required=false)String appChannelCode,
//			@CookieValue(value=Constants.COOKIE_KEY_RECRUITMENT_DETAIL_CODE,required=false)String cookieValue,
//			@CookieValue(value=Constants.COOKIE_KEY_RECRUITMENT_DETAIL_RECSOURCE,required=false)String recSource,
//			@RequestParam(value = "clientId", required = false) String clientId, 
//			Model model) throws Exception {
//		int branchId = branchServce.getBranchIdByRequest(request, IPUtil.getIpAddr(request));
//		model.addAttribute("user_current", "current");
//		model.addAttribute("reg_user", "show");
//		int sourceActivityId = 1;
//		if(StringUtils.isNotBlank(cookieValue)){
//			sourceActivityId = 4;
//			if(recSource.equals("wu")){//网盟推广
//				sourceActivityId = 7;
//			}
//		}
//		int regUserResult = accountService.registerUser(fromKey,appChannelCode,sourceActivityId,user, thekey, IPUtil.getIpAddr(request), branchId);
//		if (regUserResult == 1) {//注册成功自动登录
////			Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
////			//获取浏览器版本号
////			Version version = browser.getVersion(request.getHeader("User-Agent"));
////			//主动注册打印日志
////			String regLogMsg = OPTLogFactory.getLogStr(""+user.getId(), null, clientId, Plant.youlanwPC,"优蓝网PC", IPUtil.getIpAddr(request), null, null, ""+branchId, null, 
////					request,browser.getName(), version.getVersion(), Event.Register, "主动注册", "用户注册_优蓝网", null, null,null,null);
////			logger.info(regLogMsg);
//			User dbUser = accountService.verifyUser(user.getLoginname(), user.getPassword());
//			accountService.loginUser(dbUser, request, response);
////			String loginLogMsg = OPTLogFactory.getLogStr(""+user.getId(), null, clientId, Plant.youlanwPC,"优蓝网PC", IPUtil.getIpAddr(request), null, null, ""+branchId, null, 
////					request,browser.getName(), version.getVersion(), Event.Login, "被动登录", "用户注册_优蓝网", null, null,null,null);
////			logger.info(loginLogMsg);
//			//accessLogService.addAccessLog(request,10,dbUser.getId());
//			return "redirect:/"; //返回首页
//		} else if (regUserResult == 0) {//验证码不正确
//			model.addAttribute("err_valiCode", "请输入正确的验证码");
//			return "account/register";
//		} else if (regUserResult == 3) {//手机号已注册
//			model.addAttribute("err_loginname", "该手机号码已经被注册");
//			return "account/register";
//		}
//		return null;
//	}

	/**
	 * 快速注册、登录
	 * @param mobile
	 * @param thekey
	 * @param name
	 * @param request
	 * @param fromKey
	 * @param appChannelCode
	 * @param cookieValue
	 * @param recSource
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="fastLoginOrReg",method = RequestMethod.POST)
	public String register(@RequestParam(value = "mobile", required = false) String mobile, 
			@RequestParam(value = "thekey", required = false)String thekey, 
			@RequestParam(value = "name", required = false)String name, 
			HttpServletRequest request,
			@CookieValue(value=Constants.COOKIE_KEY_FROM_KEY,required=false)String fromKey,
			@CookieValue(value=Constants.COOKIE_KEY_APP_CHANNEL_CODE,required=false)String appChannelCode,
			@CookieValue(value=Constants.COOKIE_KEY_RECRUITMENT_DETAIL_CODE,required=false)String cookieValue,
			@CookieValue(value=Constants.COOKIE_KEY_RECRUITMENT_DETAIL_RECSOURCE,required=false)String recSource,
			HttpServletResponse response) throws Exception {
		Cache cache = StoreCacheUtil.getCacheManager().getCache("smscache");
		//获取当前页面url
        String utmUrl = request.getHeader("referer");
		Element ele = cache.get(mobile);
		if (ele != null) {
			String key = (String) ele.getObjectValue();// 存在cache中的key
			if (thekey.equals(key)) {
				int branchId = branchServce.getBranchIdByRequest(request, IPUtil.getIpAddr(request));
				User dbUser = accountService.fastReg(name,fromKey,appChannelCode,mobile, IPUtil.getIpAddr(request), branchId,cookieValue,recSource);
				//保存简历
				if(StringUtils.isNoneBlank(String.valueOf(dbUser.getId())) && StringUtils.isNoneBlank(mobile)){
					resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(dbUser.getId()), mobile, null, name);
				}
				accountService.loginUser(dbUser, request, response);
			}else{//验证码错误
				return Constants.RETURN_STATUS_ERROR;
			}
		}else{//验证码错误
			return Constants.RETURN_STATUS_ERROR;
		}
		User user = userService.getByLoginname(mobile);
		//存储用户访问信息
		if(name==""){
			utmLogService.saveUtmLog(null, null,cookieValue, "求职者", Long.valueOf(user.getId()),utmUrl,
					"PC", 0, userService.getByLoginname(mobile).getId().toString(),null, mobile,
					"A_login", "被动", "ylife_recruit", "00d55953f18911e594980800277a9591",
					null,null);
		}else{
			utmLogService.saveUtmLog(null, null,cookieValue, "求职者", Long.valueOf(user.getId()),utmUrl,
					"PC", 0, userService.getByLoginname(mobile).getId().toString(),null,
					mobile, "A_register", "被动", "ylife_recruit", "00d55953f18911e594980800277a9591",
					null,null);
			JSONObject resumeMdbByUserIdAndMobile = resumeMdbService.getResumeMdbByUserIdAndMobile(userService.getByLoginname(mobile).getId().toString(), mobile, null, name);
			JSONObject data = resumeMdbByUserIdAndMobile.getJSONObject("data");
			String resumeId= data.getString("id");
			String resumeCode=data.getString("resumeCode");
			utmLogService.saveUtmLog(null,null,cookieValue,"求职者",Long.valueOf(user.getId()),
					utmUrl, "PC", 0, resumeId,resumeCode,"已验证","addresume",
					"被动", "ylife_recruit", "00d55953f18911e594980800277a9591",null,null);
		}
		return Constants.RETURN_STATUS_SUCCESS;
	}
//	/**
//	 *进入忘记密码页面
//	 * @return
//	 */
//	@RequestMapping(value="toforgetPwd",method=RequestMethod.GET)
//	public String toforgetPwd(){
//		return "account/forgetPwdFrame";
//	}
//	/**
//	 * 忘记密码
//	 * @param mobile
//	 * @param password
//	 * @param thekey
//	 * @param model
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value="forgetPwd",method=RequestMethod.POST)
//	public String forgetPwd(@RequestParam String mobile,
//			@RequestParam String password,
//			@RequestParam String thekey,
//			Model model,
//			HttpServletRequest request,
//			HttpServletResponse response){
//		Cache cache = StoreCacheUtil.getCacheManager().getCache("smscache");
//		Element ele = cache.get(mobile);
//		if(ele != null && ele.getObjectValue() != null){
//			if(ele.getObjectValue().equals(thekey)){//验证码正确
//				User user = accountService.changePwd(mobile, password);
//				accountService.loginUser(user, request, response);
//				return "error/result";
//			}
//			else{//验证码错误
//				model.addAttribute("register_err_msg", "验证码错误");
//				return "account/forgetPwdFrame";
//			}
//		}else{//手机号码不对
//			model.addAttribute("register_err_msg", "请点击发送验证码");
//			return "account/forgetPwdFrame";
//		}
//	}
	
	/**
	 * 验证企业用户的用户名是否被注册过
	 * @param loginname
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="valloginname",method=RequestMethod.POST)
	public String valUserName(@RequestParam("loginname") String loginname)
	{
		if(sysUserService.hasSysUserByUsername(loginname))
		{
			return Constants.RETURN_STATUS_FAILURE;
		}
		return Constants.RETURN_STATUS_SUCCESS;
	}
	/**
	 * 验证手机号码
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="valmobile",method=RequestMethod.POST)
	public String valmobile(@RequestParam(value="role",required=false) String role,@RequestParam("mobile") String mobile,HttpServletRequest request){
		if (role != null && role.equals("enterprise")) {
			if (sysUserService.hasSysUserByMobile(mobile)) {// 该手机号码已经注册
				return Constants.RETURN_STATUS_FAILURE;
			}
		} else {
			if (accountService.hasUserByUsername(mobile)) {// 该手机号码已经注册
				return Constants.RETURN_STATUS_FAILURE;
			}
		}
		SMSUtil smsUtil =  new SMSUtil(mobile, "valmobile");
		smsUtil.start();
		return Constants.RETURN_STATUS_SUCCESS;
	}
	
	/**
	 * 验证手机号码
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="sendCode",method=RequestMethod.POST)
	public String sendCode(@RequestParam("mobile") String mobile,
			@RequestParam("type") String type,@RequestParam("currentValue") String currentValue,
			HttpServletRequest request){
		String message = null;
		try{
			Object limited = StoreCacheUtil.getCacheValue("smscacheLimit", mobile);
			if(limited != null && (StringUtils.isNumeric(limited+"") && Integer.parseInt(limited+"") >= 5) ){//次数超过五条手机号已被限制)
				message =  Constants.RETURN_STATUS_FROZEN;
			}else if(SmsSafeUtil.fiveSecondTimeOutLimit(mobile)){
				Object value = StoreCacheUtil.getCacheValue("smscacheValiCode", mobile);
				if(value != null && value.equals(currentValue)){
					SMSUtil smsUtil = null;
					if(StringUtils.isNotEmpty(type)){
						smsUtil =  new SMSUtil(mobile, "valmobile",1);
					}else{
						smsUtil =  new SMSUtil(mobile, "valmobile");
					}
					smsUtil.start();
					message =  Constants.RETURN_STATUS_SUCCESS;
				}
			}else{
				message =  Constants.RETURN_STATUS_FROZEN;
			}
		}catch(Exception e){
			e.printStackTrace();
			message =  Constants.RETURN_STATUS_FAILURE;
		}
		return message;
	}
	
	
	/**
	 * 验证手机号码
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="sendCodeInterView",method=RequestMethod.POST)
	public String sendCode(@RequestParam("mobile") String mobile,
			@RequestParam("type") String type,
			HttpServletRequest request){
		String message = null;
		try{
			Object limited = StoreCacheUtil.getCacheValue("smscacheLimit", mobile);
			if(limited != null && (StringUtils.isNumeric(limited+"") && Integer.parseInt(limited+"") >= 5) ){//次数超过五条手机号已被限制)
				message =  Constants.RETURN_STATUS_FROZEN;
			}else if(SmsSafeUtil.fiveSecondTimeOutLimit(mobile)){
				SMSUtil smsUtil = null;
				if(StringUtils.isNotEmpty(type)){
					smsUtil =  new SMSUtil(mobile, "valmobile",1);
				}else{
					smsUtil =  new SMSUtil(mobile, "valmobile");
				}
				smsUtil.start();
				message =  Constants.RETURN_STATUS_SUCCESS;
			}else{
				message =  Constants.RETURN_STATUS_FROZEN;
			}
		}catch(Exception e){
			e.printStackTrace();
			message =  Constants.RETURN_STATUS_FAILURE;
		}
		return message;
	}
	/**
	 * 忘记密码，验证手机号码
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="valmobileForForget",method=RequestMethod.POST)
	public String valmobileForForget(@RequestParam("mobile") String mobile,HttpServletRequest request){
		if(!accountService.hasUserByUsername(mobile)){//不存在用户不发短信
			return Constants.RETURN_STATUS_NULL;
		}
		SMSUtil smsUtil =  new SMSUtil(mobile, "valmobile");
		smsUtil.start();
		return Constants.RETURN_STATUS_SUCCESS;
	}
	/**
	 * 根据优蓝网用户id和手机号查询简历[get]
	 * @param appkey
	 * @param userId
	 * @param mobile
	 * @return
	 *//*
	@RequestMapping(value="getResume", method=RequestMethod.GET)
	public Map<String, String> getResume(@RequestParam("userId") Integer userId ,@RequestParam("mobile") String mobile){
		
		Map<String, String> resume = accountService.getResumeMdbByUserIdAndMobile(userId,mobile);
		 return resume;
	}*/
	/**
	 * 修改简历基本信息[post]   
	 * @param userid
	 * @param mobile
	 * @param resume_info_id 
	 * @param resume
	 * @return
	 *//* 
	@RequestMapping(value="UpdateResume",method=RequestMethod.POST)
	public  Map<String, String> modifyResumeBaseMdb(@RequestParam("userId") Long userid ,@RequestParam("mobile") String mobile, @RequestParam("resume_info_id") String resume_info_id){
		Map<String,String> map = new HashMap<>();
	   
		
		Map<String, String> resume1 = accountService.modifyResumeBaseMdb(userid,mobile,resume_info_id);
		return map;
			 	
	}*/
	/**
	 * 用户注册同步web
	 *//*
	@RequestMapping(value="registerUserWeb",method=RequestMethod.POST)
	public void registerUserWeb(){
		User reguser = new User();
		reguser.setLoginname("15045669094");
		Integer userid = 53;
		String  mobile = "15045669094";
		accountService.registerUserWeb(reguser, userid, mobile);
		
	}*/
/*	
	@RequestMapping(value="saveProfileWeb",method=RequestMethod.POST)
	public void saveProfileWeb(){
	    Resume resume = new Resume();
	    resume.setMobile("15045669094");
	    resume.setId(53);
	    resume.setName("qqqqqq");
	    resume.setQq("981818795");
	    resume.setIdCard("320924199001241494");
	    resume.setNation(1);
	    resume.setNativeProvinceid(15);
	    resume.setJobTargetCityid(15);
	    resume.setJobTargetCountyid(null);
	    resume.setIntentionSalary("10000");
	    resume.setIntentionPosition(52);
	    resume.setJobTarget("去玩儿推欧赔去玩儿推欧赔");
		Integer userid = 53;
		
		accountService.saveProfileWeb(userid, resume);
		
	}*/
}
