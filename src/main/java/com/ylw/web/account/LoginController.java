package com.ylw.web.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ylw.service.base.*;
import com.ylw.service.utm.UtmLogService;
import com.ylw.util.*;
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

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;


@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	private AccountService accountService;
//	@Autowired
//	private SysUserService sysUserService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private ResumeMdbService resumeMdbService;
	@Autowired
	private ResumeService resumeService;
	@Autowired
	private UtmLogService utmLogService;
	@Autowired
	private UserService userService;
//	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(method=RequestMethod.GET)
	public String loginForm(Model model,@RequestParam(value="backUrl",required=false)String backUrl){
		model.addAttribute("url", backUrl);
		model.addAttribute("userLogin", "current");
		model.addAttribute("userShow", "show");
		model.addAttribute("roleClass", "login-bg");
		return "account/login";
	}

	/**
	 * 登录 如果role没有传并且不为enterprise的时候为普通用户，否则为企业用户
	 * @param thekey
	 * @param loginname
	 * @param name
	 * @param fromKey
	 * @param appChannelCode
	 * @param cookieValue
	 * @param recSource
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public String login(@RequestParam("thekey") String thekey, 
			@RequestParam("loginname") String loginname, 
			@RequestParam(value = "name",required=false) String name, 
			@CookieValue(value=Constants.COOKIE_KEY_FROM_KEY,required=false)String fromKey,
			@CookieValue(value=Constants.COOKIE_KEY_APP_CHANNEL_CODE,required=false)String appChannelCode,
			@CookieValue(value=Constants.COOKIE_KEY_RECRUITMENT_DETAIL_CODE,required=false)String cookieValue,
			@CookieValue(value=Constants.COOKIE_KEY_RECRUITMENT_DETAIL_RECSOURCE,required=false)String recSource,
			//@RequestParam(value = "clientId", required = false) String clientId, 
			HttpServletRequest request, HttpServletResponse response, Model model) {
		int sourceActivityId = 1;
		if(StringUtils.isNotBlank(cookieValue)){
			sourceActivityId = 4;
			if(recSource.equals("wu")){//网盟推广
				sourceActivityId = 7;
			}
		}
		//获取当前页面URL
		String utmUrl = request.getHeader("referer");
		if(utmUrl.indexOf("login/iframe")!=-1){
			//获取问答页面url
			utmUrl=request.getSession().getAttribute("wenDaUtmUrl").toString();
		}
		Cache cache = StoreCacheUtil.getCacheManager().getCache("smscache");
		Element ele = cache.get(loginname);
		if (ele != null) {
			String key = (String) ele.getObjectValue();// 存在cache中的key
			if (thekey.equals(key)) {//验证码正确
				User user = accountService.isActivate(loginname);
				String password = RandomSeriNoUtils.generateNumString(6);// 产生随机密码
				if (user == null) {// 用户不存在，注册并自动登录
					User newUser = new User();
					newUser.setLoginname(loginname);
					newUser.setPassword(password);
					int branchId = branchService.getBranchIdByRequest(request, IPUtil.getIpAddr(request));
					User resultUser = accountService.registerUser(name,fromKey,appChannelCode,sourceActivityId,newUser, thekey, IPUtil.getIpAddr(request), branchId);
					if(resultUser != null&&resultUser.getId()!=null){//修改简历
						JSONObject resumeMdbByUserIdAndMobile = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(resultUser.getId()), loginname, null,name);
						JSONObject data = resumeMdbByUserIdAndMobile.getJSONObject("data");
						String resumeId= data.getString("id");
						String resumeCode=data.getString("resumeCode");
						//存储用户访问信息（简历）
					utmLogService.saveUtmLog(null,null,cookieValue,"求职者", Long.valueOf(resultUser.getId()),
							utmUrl, "PC", 0, resumeId,resumeCode,"已验证","addresume",
							"被动", "ylife_recruit", "00d55953f18911e594980800277a9591",null,null);
					}
					//存储用户访问信息（注册）
					utmLogService.saveUtmLog(null, null,cookieValue, "求职者", Long.valueOf(resultUser.getId()),
							utmUrl, "PC", 0, userService.getByLoginname(loginname).getId().toString(),null,
							loginname, "A_register", "主动", "ylife_recruit",
							"00d55953f18911e594980800277a9591",null,null);
					accountService.loginUser(resultUser, request, response);
				}else{
					//存储用户访问信息（登陆）
					utmLogService.saveUtmLog(null, null,cookieValue, "求职者", Long.valueOf(user.getId()),
							utmUrl, "PC", 0, userService.getByLoginname(loginname).getId().toString(),null,
							loginname, "A_login", "主动", "ylife_recruit",
							"00d55953f18911e594980800277a9591",null,null);
					accountService.loginUser(user, request, response);
				}
				return Constants.RETURN_STATUS_SUCCESS;
			}
		}
		return Constants.RETURN_STATUS_ERROR;
	}
	/**
	 * 登录
	 * @param password
	 * @param remember
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="loginUser",method=RequestMethod.POST)
	public String loginUser(@RequestParam String mobile,
			@RequestParam String password,
			@RequestParam(value="remember",required=false) Integer remember,
			Model model,
			HttpServletRequest request,HttpServletResponse response){
		User user = accountService.verifyUser(mobile, password);
		if(user==null){
			model.addAttribute("login_err_msg", "用户名或密码错误");
			return "account/loginFrame";//用户名或密码错误
		}else if(user.getStatus() == -1){//冻结
			model.addAttribute("login_err_msg", "用户已经被停用 ");
			return "account/loginFrame";
		}else{
			accountService.loginUser(user, request, response);
			return "error/result";
		}
	}
	/**
	 * ajax登录
	 * @param mobile
	 * @param password
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("loginUserAjax")
	public String loginUserAjax(@RequestParam("mobile")String mobile,@RequestParam("password")String password,HttpServletRequest request){
		User user = accountService.verifyUser(mobile, password);
		if(user==null){
			return Constants.RETURN_STATUS_FAILURE;
		}else if(user.getStatus() != null && user.getStatus() ==-1){//冻结
			return Constants.RETURN_STATUS_FROZEN;
		}else{
			accountService.loginUser(user, request, null);
			return Constants.RETURN_STATUS_SUCCESS;
		}
	}
	
	@RequestMapping(value="iframe")
	public String loginIframe(){
		return "account/loginFrame";
	}

	@ResponseBody
	@RequestMapping("veryfy")
	public String verify(@CookieValue(value="yl_vali_cookie",required = false)String valiCookie,HttpServletRequest request){
		User user =  (User)accountService.validateLogin(valiCookie, request.getSession());
		//获取当前页面URL
		String utmUrl = request.getHeader("referer");
		request.getSession().setAttribute("wenDaUtmUrl",utmUrl);
		return (user != null&&user.getId()!=null)?valiCookie:"unlogin";
	}
	
	@RequestMapping("clear")
	public String clear(@CookieValue(value="yl_vali_cookie",required = false)String valiCookie, HttpServletRequest request){
		accountService.clearLogin(valiCookie, request.getSession());
		return "redirect:/job";
	}
	
	/**
	 * 设置clientId
	 * @param clientId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("callbackClientId")
	public String callbackClientId(@RequestParam("clientId")String clientId, HttpServletRequest request){
		request.getSession().setAttribute("_finger_client_id", clientId);
		Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
		//获取浏览器版本号
		Version version = browser.getVersion(request.getHeader("User-Agent"));
//		System.out.println(browser.getName());
//		System.out.println(version.getVersion());
		return Constants.RETURN_STATUS_SUCCESS;
	}
}
