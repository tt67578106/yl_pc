package com.ylw.web.signup;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ylw.entity.code.CodeAreaCity;
import com.ylw.entity.job.JobBase;
import com.ylw.service.code.CodeAreaCityService;
import com.ylw.service.utm.UtmLogService;
import com.ylw.util.*;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ylw.entity.base.Resume;
import com.ylw.entity.base.User;
import com.ylw.service.base.AccessLogService;
import com.ylw.service.base.AccountService;
import com.ylw.service.base.BranchService;
import com.ylw.service.base.RecommendJobService;
import com.ylw.service.base.ResumeMdbService;
import com.ylw.service.base.ResumeService;
import com.ylw.service.base.UserService;
import com.ylw.service.job.JobApplyService;
import com.ylw.service.job.JobBaseService;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

/**
 * 报名
 * 
 * @author mabixiang
 *
 */
@Controller
@RequestMapping("signup")
public class SignUpController {

	@Autowired
	private JobApplyService jobApplyService;
	@Autowired
	private UserService userService;
	@Autowired
	private JobBaseService jobBaseService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private ResumeService resumeService;
	@Autowired
	private RecommendJobService recommendJobService;
	@Autowired
	private BranchService branchService; 
	@Autowired
	private AccessLogService accessLogService;
	@Autowired
	private ResumeMdbService resumeMdbService;
	@Autowired
	private UtmLogService utmLogService;
	@Autowired
	private CodeAreaCityService codeAreaCityService;

	private static Logger logger = LoggerFactory.getLogger(SignUpController.class);
	/**
	 * 快速报名
	 * @return
	 */
	@RequestMapping(value = "fastSignUp", method = RequestMethod.GET)
	public String quick(@CookieValue(value = Constants.COOKIE_KEY_USER_VERIFY, required = false) String cookieValue, HttpServletRequest request, Model model) {
		//accessLogService.addAccessLog(request,11,null);
		Integer branchId = branchService.getBranchIdByRequest(request, IPUtil.getIpAddr(request));
		User user = (User) accountService.validateLogin(cookieValue, request.getSession());
		if (user != null) {
			model.addAttribute("userInfo", user);
			//简历查询----------------------------
			if(user.getId() != null && user.getLoginname() != null){
				JSONObject resultMap = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(user.getId()), user.getLoginname(), null,null);
				if(resultMap.get("status").equals(Constants.RETURN_STATUS_SUCCESS)){
					// 获取data
					JSONObject dataObject = resultMap.getJSONObject("data");
					// 求职意向信息
					net.sf.json.JSONObject intention = dataObject.getJSONObject("intention");
					// 获取data里面 resume_info_id
					final String resume_info_id = (String) dataObject.get("id");
					/*** 设置基本信息 start ***/
					Resume resume = new Resume();
					//姓名
					resume.setName(
							getValue(dataObject, "fullName") == null ? null : getValue(dataObject, "fullName").toString());
					//性别
					
					Integer sex =null;
					String gender = (String) getValue(dataObject, "gender");
					if(gender != null){
						if(gender.equals("男")){
							sex =1;
						}
						if(gender.equals("女")){
							sex =2;
						}
						resume.setGender(sex);
					}
					/*resume.setGender(getValue(dataObject, "gender") == null ? null
							: new Integer(getValue(dataObject, "gender").toString()));*/
				
					//手机号
					resume.setMobile(getValue(dataObject, "mainMobile") == null ? null
							: getValue(dataObject, "mainMobile").toString());
					//选择求职意向1城市
			/*		if(intention != null && intention.isNullObject()){
						net.sf.json.JSONObject workingPlace = intention.getJSONObject("workingPlace");
						if (workingPlace != null && !workingPlace.isNullObject()) {
							resume.setJobTargetProvinceid(getValue(workingPlace, "provinceId") == null ? null
									: new Integer(getValue(workingPlace, "provinceId").toString()));
						}
						//职位
						resume.setJobTarget(getValue(intention, "selfIntro") == null ? null
								: getValue(intention, "selfIntro").toString());
						//薪资
						// 期望薪资下限
						final String salaryFrom = getValue(intention, "salaryFrom") == null ? ""
								: getValue(intention, "salaryFrom").toString();
						// 期望薪资上限
						final String salaryTo = getValue(intention, "salaryTo") == null ? ""
								: getValue(intention, "salaryTo").toString();
						resume.setIntentionSalary(salaryFrom + "-" + salaryTo);
						
					}*/
					
					
				
					model.addAttribute("resumeBase", resume);
				}	
			}
			
			/*model.addAttribute("resumeBase", resumeService.getByUserId(user.getId()));*/
		}
		// recommendJobs 首页显示
		model.addAttribute("recommendJobList", recommendJobService.findRecommendJobCache(Constants.CACHE_KEY_RECOMMEND_JOB, Constants.POSITION_CODE_RECOMMEND_JOBS,branchId));
		model.addAttribute("applyCount", jobBaseService.countJobApply());
		return "job/fastSignUp";
	}
		
		/**
		 * 返回有效的非空 JSON 值
		 * 
		 * @param intention
		 *            查询的JSON对象
		 * @param key
		 *            查询对象对应的Key
		 * @return
		 */
		private Object getValue(final net.sf.json.JSONObject intention, final String key) {
			if (intention != null && StringUtils.isNoneBlank(key)) {
				Object value = intention.get(key);

				// 不能是 null 或者 JSONNull
				if (!(value instanceof JSONNull)) {
					return value;
				}
			}

			return null;
		}
	/**
	 * 快速报名
	 * 用户提交快速报名时： 如果系统中同一号码存在未处理报名，且提交间隔大于等于60分钟，则只更新报名时间，不生成新纪录。
	 * 如果系统内同一号码存在未处理报名，且提交间隔小于60分钟，则不做任何操作。 如果系统内同一号码不存在未处理报名记录，则生成一条新报名记录。
	 * 
	 * @param name
	 * @param mobile
	 * @param thekey
	 * @param gender
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "fastSignUp", method = RequestMethod.POST)
	public String signupjob(@CookieValue(value=Constants.COOKIE_KEY_RECRUITMENT_DETAIL_CODE,required=false)String cookieValue,
			@CookieValue(value=Constants.COOKIE_KEY_FROM_KEY,required=false)String fromKey,
			@CookieValue(value=Constants.COOKIE_KEY_APP_CHANNEL_CODE,required=false)String appChannelCode,
			@CookieValue(value=Constants.COOKIE_KEY_RECRUITMENT_DETAIL_RECSOURCE,required=false)String recSource,
			@CookieValue(value="yl_vali_cookie",required = false)String valiCookie,
			String name, String mobile, String intention, Integer gender, String thekey, 
			HttpServletRequest request, HttpServletResponse response) {
		CodeAreaCity city = null;	//意愿城市信息
		String result = null;		//返回结果
		JSONObject resumeMdb = new JSONObject(); //同步mongodb返回结果
		int branchId = branchService.getBranchIdByRequest(request, IPUtil.getIpAddr(request));	//分站Id
		if(StringUtils.isNotEmpty(intention)){
			String[] intention_array = intention.split(",");
			city = codeAreaCityService.findByAbbreviation(intention_array[0]);
		}
		User users = userService.getByLoginname(mobile);
		if(users != null && users.getIsValidation() ==0 ){		//更新用户信息
			java.util.Date lastLoginTime =  new Date();
			java.util.Date updateTime =  new Date();
			Integer isValidation = 1;
			userService.updateUser(lastLoginTime,updateTime,isValidation,users.getId());
		}
		/*修改整理后*/
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
			users = userService.registerUser(fromKey, mobile, name, password, IPUtil.getIpAddr(request), branchId, cookieValue, recSource);
		}
		if(users != null){
			resumeMdb = userService.findResumeMdb(users, mobile, name, gender, city);
		}
		if(resumeMdb.get("status").equals(Constants.RETURN_STATUS_SUCCESS)){
			result = userService.signUp30s(users, null, fromKey, appChannelCode, mobile, IPUtil.getIpAddr(request), name, intention, branchId,
					cookieValue, recSource, city);
		}
		List<Integer> applyIds = userService.getApplyId(userService.getByLoginname(mobile).getId(), mobile);
		Integer applyId = null;
		if(applyIds != null && applyIds.size() != 0)
			applyId = applyIds.get(applyIds.size()-1);

		/*String result = userService.signUp30s(null,thekey,fromKey,appChannelCode,mobile, IPUtil.getIpAddr(request), gender, name, intention,
				request, response, branchId,cookieValue,recSource);
		List<Integer> applyIds = userService.getApplyId(userService.getByLoginname(mobile).getId(), mobile);
		Integer applyId = applyIds.get(applyIds.size()-1);*/


		//获取当前页面URL
		String utmUrl = request.getHeader("referer");

		User user =  (User)accountService.validateLogin(valiCookie, request.getSession());
		if(user == null||user.getId()==null){//未登录，自动登录
			User dbuser = userService.getByLoginname(mobile);
			if(dbuser!=null){//自动登录
				accountService.loginUser(dbuser, request, response, intention, null);
			}
			//存储用户访问信息
			if(users==null){
				utmLogService.saveUtmLog(null, null,cookieValue, "求职者", Long.valueOf(dbuser.getId()),
						utmUrl, "PC", 0, dbuser.getId().toString(),null, mobile, "A_register",
						"被动", "ylife_recruit", "00d55953f18911e594980800277a9591",null,null);
				JSONObject resumeMdbByUserIdAndMobile = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(dbuser.getId()), dbuser.getLoginname(), null,name);
				JSONObject data = resumeMdbByUserIdAndMobile.getJSONObject("data");
				String resumeId= data.getString("id");
				String resumeCode=data.getString("resumeCode");
				utmLogService.saveUtmLog(null,null,cookieValue,"求职者", Long.valueOf(dbuser.getId()),utmUrl, "PC",
						0, resumeId,resumeCode,"已验证","addresume","被动", "ylife_recruit",
						"00d55953f18911e594980800277a9591",applyId.toString(),applyId);
			}else {
				utmLogService.saveUtmLog(null, null,cookieValue, "求职者", Long.valueOf(userService.getByLoginname(mobile).getId()),
						utmUrl, "PC", 0, userService.getByLoginname(mobile).getId().toString(),null,
						mobile, "A_login", "被动", "ylife_recruit",
						"00d55953f18911e594980800277a9591",applyId.toString(),applyId);
			}

		}
		return result;
	}

	/**
	 * 快速报名验证手机号，激活账户
	 * 
	 * @param mobile
	 * @param thekey
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "verifykey", method = RequestMethod.POST)
	public String verifykey(String mobile, String thekey, String password, HttpServletRequest request, HttpServletResponse response) {
		return userService.activateAccount(mobile, thekey, password, request, response);
	}

	/**
	 * 判断登录用户是否报名过该岗位
	 * @param valiCookie
	 * @param request
	 * @param jobid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "ifUserSignedJob", method = RequestMethod.POST)
	public String ifUserSignedJob(@CookieValue(value = "yl_vali_cookie", required = false) String valiCookie, HttpServletRequest request, @RequestParam Integer jobid) {
		User user = (User) accountService.validateLogin(valiCookie, request.getSession());
		if (user != null && user.getId() != null) {
			if (jobApplyService.ifUserSignedJob(user.getId(), jobid)) {
				return Constants.RETURN_STATUS_SUCCESS;
			} else {
				return Constants.RETURN_STATUS_ERROR;
			}
		} else {
			return Constants.RETURN_STATUS_FAILURE;
		}
	}

	/**
	 * 报名
	 * @param jobid
	 * @param type
	 * @param request
	 * @param scheduleCode
	 * @param fromKey
	 * @param appChannelCode
	 * @param cookieValue
	 * @param recSource
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "job", method = RequestMethod.POST)
	public String signupjob(@RequestParam("jobid")Integer jobid,
			@RequestParam("type")Integer type,HttpServletRequest request,
			@RequestParam(value="scheduleCode",required=false)String scheduleCode,
			@CookieValue(value=Constants.COOKIE_KEY_FROM_KEY,required=false)String fromKey,
			@CookieValue(value=Constants.COOKIE_KEY_APP_CHANNEL_CODE,required=false)String appChannelCode,
			@CookieValue(value=Constants.COOKIE_KEY_RECRUITMENT_DETAIL_CODE,required=false)String cookieValue,
			@CookieValue(value=Constants.COOKIE_KEY_RECRUITMENT_DETAIL_RECSOURCE,required=false)String recSource,
			HttpSession session) {
		Integer branchid = branchService.getBranchIdByRequest(request, IPUtil.getIpAddr(request));
		String objectProperty=null;
		//获取当前页面URL
		String utmUrl = request.getHeader("referer");
		JobBase job=jobBaseService.getJobBase(jobid);
		if(job!=null&&(job.getCooperationType()==1||job.getCooperationType()==2||job.getCooperationType()==5)){//真实岗位
			if(job.getJobConfig()!=null&&job.getJobConfig().getIsRecruitment()==0){//在招
				if (StringUtils.isNotBlank(scheduleCode)) {// 有排期
					objectProperty="真岗位#在招#有面试#无返现";
				} else {// 无排期
					objectProperty="真岗位#在招#无面试#无返现";
				}
			}else{//停招
				if (StringUtils.isNotBlank(scheduleCode)) {// 有排期
					objectProperty="真岗位#停招#有面试#无返现";
				} else {// 无排期
					objectProperty="真岗位#停招#无面试#无返现";
				}
			}
		}else{//假岗位
			objectProperty="假岗位";
		}
		User user = (User) session.getAttribute(Constants.SESSION_KEY_USER);
		JSONObject resumeMdbByUserIdAndMobile = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(user.getId()), user.getLoginname(), null, user.getUsername());
		JSONObject data = resumeMdbByUserIdAndMobile.getJSONObject("data");
		String resumeId= data.getString("id");
		String resumeCode=data.getString("resumeCode");
		if(user!=null&& user.getId() != null){
			Integer applyId = userService.signUpJob(fromKey,appChannelCode,jobid,scheduleCode,user,type,IPUtil.getIpAddr(request), branchid,cookieValue,recSource);
			if(applyId>0){
				//报名成功，打印统一日志
//				Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
//				//获取浏览器版本号
//				Version version = browser.getVersion(request.getHeader("User-Agent"));
//				String logMsg = OPTLogFactory.getLogStr(""+user.getId(), null,null,null,user.getLoginname(),null,null,null,Plant.youlanwPC,"优蓝网PC",IPUtil.getIpAddr(request),null,null,branchid+"",null,request,browser.getName(), version.getVersion(),Event.SignUp,jobid+"",null,applyId+"",null,
//						null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
//				logger.info(logMsg);
				utmLogService.saveUtmLog(null,null,cookieValue,"求职者", Long.valueOf(user.getId()),utmUrl, "PC",
						2, jobid.toString(),null,objectProperty,"A_jobsignup","主动", "ylife_recruit",
						"00d55953f18911e594980800277a9591",applyId.toString(),applyId);
				return Constants.RETURN_STATUS_SUCCESS;
			}else{
				return Constants.RETURN_STATUS_FAILURE;
			}
		}else{
			return Constants.RETURN_STATUS_NOTINIT;
		}

	}
	
	/**
	 * 根据登录的用户查询当天该用户已报名的数量
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "countJobApply",method=RequestMethod.POST)
	public Integer countJobApply(HttpSession session){
		User user = (User) session.getAttribute(Constants.SESSION_KEY_USER);
		if(user!=null&& user.getId() != null){
			return jobApplyService.countApplyByUserIdAndCreateDate(user.getId());
		}
		return 10;
	}
	/**
	 * 保存到简历
	 * @param cookieValue
	 * @param request
	 * @param resume
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="updateResume",method=RequestMethod.POST)
	public String resume(@CookieValue(value=Constants.COOKIE_KEY_USER_VERIFY,required=false)String cookieValue, 
			HttpServletRequest request, Resume resume,Model model,RedirectAttributes redirectAttributes){
		User user = (User) accountService.validateLogin(cookieValue, request.getSession());
//		String resumeJson = null;
//		Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
//		//获取浏览器版本号
//		Version version = browser.getVersion(request.getHeader("User-Agent"));
		if(user == null || user.getId() == null){
			return Constants.RETURN_STATUS_NOTINIT;
		}else{
			accountService.saveProfile(user.getId(), resume);
//			try {
//				resumeJson = JsonUtil.bean2Json(resume);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			String resumeLogMsg = OPTLogFactory.getRegisterLog(""+user.getId(),""+user.getId(),resume.getId()+"",resumeJson,Plant.youlanwPC,"优蓝网PC",
//					IPUtil.getIpAddr(request),null,null,null, null,request,browser.getName(), version.getVersion(), Event.EditResume,null,null,null,null);
//			logger.info(resumeLogMsg);
			return Constants.RETURN_STATUS_SUCCESS;
		}
	}
	/**
	 * 报名和预约岗位的时候判断必填字段是否完整
	 * @param type
	 * @param request
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "valresume", method = RequestMethod.POST)
	public String valresume(@RequestParam("type")Integer type,HttpServletRequest request,
			HttpSession session) {
		User user = (User) session.getAttribute(Constants.SESSION_KEY_USER);
		if(user!=null&& user.getId() != null){
			Boolean result=resumeService.resumeIsComplete(user.getId(),type);
			if(result){//已完善
				return Constants.RETURN_STATUS_SUCCESS;
			}else{//未完善
				return Constants.RETURN_STATUS_FAILURE;
			}
		}else{//未登录
			return Constants.RETURN_STATUS_NOTINIT;
		}
	}
	/**
	 * 发送验证码
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "valmobile", method = RequestMethod.POST)
	public String valmobile(@RequestParam("mobile") String mobile, HttpServletRequest request) {
		String message = null;
		try{
			Object limited = StoreCacheUtil.getCacheValue("smscacheLimit", mobile);
			if(limited != null && (StringUtils.isNumeric(limited+"") && Integer.parseInt(limited+"") >= 5) ){//次数超过五条手机号已被限制)
				message =  Constants.RETURN_STATUS_FROZEN;
			}else if(SmsSafeUtil.fiveSecondTimeOutLimit(mobile)){
				SMSUtil smsUtil =  new SMSUtil(mobile, "valmobile");
				smsUtil.start();
				message = Constants.RETURN_STATUS_SUCCESS;
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
	 * 该手机号码是否已经注册
	 * 
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "valmobilenumber", method = RequestMethod.POST)
	public String valmobilenumber(@RequestParam("mobile") String mobile) {
		return accountService.hasUserByUsername(mobile) ? Constants.RETURN_STATUS_SUCCESS : Constants.RETURN_STATUS_FAILURE;
	}

	/**
	 * 判断手机号是否激活
	 * 
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "isactivate", method = RequestMethod.POST)
	public String isactivate(@RequestParam("mobile") String mobile) {

		User user = accountService.isActivate(mobile);
		if (user == null) {// 不存在
			return Constants.RETURN_STATUS_FAILURE;
		} else {
			if (user.getStatus()==null || user.getStatus() == 0) {// 未初始化（未激活）
				return Constants.RETURN_STATUS_NOTINIT;
			}
		}
		return Constants.RETURN_STATUS_SUCCESS;
	}

	/**
	 * 未登录状态下，报名具体的职位
	 * 
	 * @param mobile
	 * @param name
	 * @param jobid
	 * @param request
	 * @return success:用户未登录，但系统存在该用户，为之报名，下一步在输入密码 ;falure:不存在该用户，要求输入验证码
	 */
	@ResponseBody
	@RequestMapping(value = "onlymobilenumber", method = RequestMethod.POST)
	public String onlymobilenumber(@RequestParam("mobile") String mobile, @RequestParam("name") String name, @RequestParam("jobid") Integer jobid, HttpServletRequest request) {
		Integer userid = accountService.hasUserByUsernameForId(mobile);
		if (userid != null && jobApplyService.ifUserSignedJob(userid, jobid)) {
			jobApplyService.onlymobilesiginup(userid, name, jobid, IPUtil.getIpAddr(request));
			return Constants.RETURN_STATUS_SUCCESS;
		} else {
			return Constants.RETURN_STATUS_FAILURE;
		}
	}


	/**
	 * 已登录用户报名
	 * 
	 * @param valiCookie
	 * @param request
	 * @param jobid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "loginOne", method = RequestMethod.POST)
	public String loginOneSignUp(@CookieValue(value = "yl_vali_cookie", required = false) String valiCookie, HttpServletRequest request, @RequestParam Integer jobid) {
		User user = (User) accountService.validateLogin(valiCookie, request.getSession());
		if (user != null && user.getId() != null) {
			if (jobApplyService.ifUserSignedJob(user.getId(), jobid)) {
				jobApplyService.userSignUp(user.getId(), jobid, IPUtil.getIpAddr(request));
				return Constants.RETURN_STATUS_SUCCESS;
			} else {
				return Constants.RETURN_STATUS_ERROR;
			}
		} else {
			return Constants.RETURN_STATUS_FAILURE;
		}
	}

	/**
	 * 登录情况下报名
	 * 
	 * @param valiCookie
	 * @param intent
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "loginOneQuick", method = RequestMethod.POST)
	public String loginOneSignUp(@CookieValue(value = "yl_vali_cookie", required = false) String valiCookie, @RequestParam(value = "jobId", required = false) Integer jobId, @RequestParam String intent, HttpServletRequest request) {
		User user = (User) accountService.validateLogin(valiCookie, request.getSession());
		if (user != null && user.getId() != null) {
			if (jobApplyService.countByJobidAndUserid(user.getId(), jobId) > 0) {
				return Constants.RETURN_STATUS_DUPLICATE;
			}
			jobApplyService.userSignUp(user.getId(), IPUtil.getIpAddr(request), intent);
			return Constants.RETURN_STATUS_SUCCESS;
		} else {
			return Constants.RETURN_STATUS_FAILURE;
		}
	}
	
	@ModelAttribute
	public void getEntity(@RequestParam(value = "id", defaultValue = "-1") Integer id,Model model) {
		if (id != -1) 	
			model.addAttribute("entity", resumeService.getResume(id));
	}
}
