package com.ylw.web.account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ylw.service.base.*;
import com.ylw.service.utm.UtmLogService;
import net.sf.ehcache.Cache;
import net.sf.json.JSONObject;

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

import com.ylw.entity.base.Image;
import com.ylw.entity.base.Resume;
import com.ylw.entity.base.User;
import com.ylw.entity.code.CodeAreaCity;
import com.ylw.entity.code.CodeAreaDistrict;
import com.ylw.service.code.CodeAreaCityService;
import com.ylw.service.code.CodeAreaDistrictService;
import com.ylw.service.code.CodeAreaProvinceService;
import com.ylw.service.job.JobApplyService;
import com.ylw.service.job.JobTypeService;
import com.ylw.util.Constants;
import com.ylw.util.StoreCacheUtil;

@Controller
@RequestMapping("my")
public class MyController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private ResumeService resumeService;
	@Autowired
	private CodeAreaDistrictService areaDistrictService;
	@Autowired
	private CodeAreaCityService areaCityService;
	@Autowired
	private CodeAreaProvinceService areaProvinceService; 
	@Autowired
	private ImageService imageService;
	@Autowired
	private JobTypeService jobTypeService;
	@Autowired
	private CodeAreaCityService codeAreaCityService;
	@Autowired
	private JobApplyService jobApplyService;
	@Autowired
	private AccessLogService accessLogService;
	@Autowired
	private DataDictionaryService dataDictionaryService;
	@Autowired
	private UtmLogService utmLogService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResumeMdbService resumeMdbService;
//	private Logger logger = LoggerFactory.getLogger(getClass());
	private Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
	/**
	 * 保存简历
	 * @param resume
	 * @return
	 */
	@RequestMapping(value="resume",method=RequestMethod.POST)
	public String resume(@CookieValue(value=Constants.COOKIE_KEY_USER_VERIFY,required=false)String cookieValue, 
			HttpServletRequest request,Resume resume,Model model,RedirectAttributes redirectAttributes){
		User user = (User) accountService.validateLogin(cookieValue, request.getSession());
//		String resumeJson=null;
//		Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
//		//获取浏览器版本号
//		Version version = browser.getVersion(request.getHeader("User-Agent"));
		//获取当前页面URL
		String utmUrl = request.getHeader("referer");
		if(user == null || user.getId() == null){
			String backUrl=request.getRequestURI();
			redirectAttributes.addFlashAttribute("url", backUrl);
			return "redirect:/login";
		}else{
			JSONObject resumeMdbByUserIdAndMobile = resumeMdbService.getResumeMdbByUserIdAndMobile(user.getId().toString(), user.getLoginname(), null,user.getUsername());
			JSONObject data = resumeMdbByUserIdAndMobile.getJSONObject("data");
			String resumeId= data.getString("id");
			System.out.println(resumeId);
			String resumeCode=data.getString("resumeCode");
			System.out.println(resumeCode);
			////存储用户访问信息（简历维护页）
			utmLogService.saveUtmLog(null,null,cookieValue,"求职者", Long.valueOf(user.getId()),utmUrl, "PC",
					0, resumeId,resumeCode,null,"addresume","主动", "ylife_recruit",
					"00d55953f18911e594980800277a9591",null,null);
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
			redirectAttributes.addFlashAttribute("result",Constants.RETURN_STATUS_SUCCESS);
			return "redirect:/my";
		}
	}
	/**
	 * 上传头像
	 * @param file
	 * @param attr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="saveHeadImage",method=RequestMethod.POST)
	public String saveHeadImage(@RequestParam(value="headData")String file,RedirectAttributes attr,HttpServletRequest request){
		if(file!=null){
			Integer imgId = imageService.uploadhead(file);
			User user =  (User)request.getSession().getAttribute(Constants.SESSION_KEY_USER);
			Image head=imageService.getImage(imgId);
			if(user!=null){
				Resume resume = new Resume();
				resume.setImage(head);
				resumeMdbService.saveProfileWeb(user.getId(),resume);
				HttpSession session = request.getSession();
				session.setAttribute(Constants.SESSION_KEY_USER_HEAD_PATH,head.getImgpath());
			}
			return "<script>$('#head_img',parent.document).attr('src', '"+Constants.IMAGE_FILE_URL+"o/"+head.getImgpath()+"');</script>";
		}
		return "<script>$('#head_img',parent.document).attr('src', '${ctx}/static/images/default-portrait.png');</script>";
	}
	/**
	 * 载入areaiframe
	 * @param model
	 * @return
	 */
	@RequestMapping(value="area",method=RequestMethod.GET)
	public String loadareas(Model model){
		model.addAttribute("provinces", areaProvinceService.findProvinces());
		return "account/areaFrame";
	}
	@ResponseBody
	@RequestMapping(value="citys",method=RequestMethod.POST)
	public List<CodeAreaCity> gainCityAjax(@RequestParam int provinceid){
		return areaCityService.findByProvinceId(provinceid);
	}
	@ResponseBody
	@RequestMapping(value="districts",method=RequestMethod.POST)
	public List<CodeAreaDistrict> gainDistrictAjax(@RequestParam int cityid){
		return areaDistrictService.findBycityId(cityid);
	}
	
	@RequestMapping
	public String profile(@CookieValue(value=Constants.COOKIE_KEY_USER_VERIFY,required=false)String cookieValue, HttpServletRequest request,Model model,RedirectAttributes redirectAttributes){
		//accessLogService.addAccessLog(request,3,null);
		User user = (User) accountService.validateLogin(cookieValue, request.getSession());
		if(user == null){
			String backUrl=request.getRequestURI();
			redirectAttributes.addFlashAttribute("url", backUrl);
			return "redirect:/login";
		}else{
			model.addAttribute("userInfo", user);
			//查询用户简历Mdb---------------------------------------------------------------
			if(user.getId() != null && user.getLoginname() != null){
				JSONObject resultMap = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(user.getId()), user.getLoginname(), null,null);
				Resume resume = resumeMdbService.findResume(resultMap);
				model.addAttribute("jobTypes", jobTypeService.findJobTypeByParentId(0));
				model.addAttribute("resumeBase", resume);
			}
			return "account/profile";
			
		}
	}
	
	
	
	
	/**
	 * 我的求职
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("apply")
	public String myApply(HttpServletRequest request, Model model,RedirectAttributes redirectAttributes){
		User user =  (User)request.getSession().getAttribute(Constants.SESSION_KEY_USER);
		if(user!=null&&user.getId()!=null){//登录状态
			JSONObject resultMap = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(user.getId()), user.getLoginname(), null,null);
			Resume resume = resumeMdbService.findResume(resultMap);
			model.addAttribute("resumeBase", resume);
//			Resume resume = resumeService.getByUserId(user.getId());
			Image headImg = resume!=null?resume.getImage():null;
			model.addAttribute("headimg", headImg);
			model.addAttribute("userInfo", user);
			model.addAttribute("realname", resume.getName());
			model.addAttribute("applyCount", jobApplyService.countByUserIdAndType(user.getId(),2));
			model.addAttribute("orderCount", jobApplyService.countByUserIdAndType(user.getId(),4));
			return "account/myApply";
		}
		String backUrl=request.getRequestURI();
		redirectAttributes.addFlashAttribute("url", backUrl);
		return "redirect:/login";
	}
	/**
	 * 投递记录
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("signupRecord")
	public String signupRecord(HttpServletRequest request, Model model,RedirectAttributes redirectAttributes){
		User user =  (User)request.getSession().getAttribute(Constants.SESSION_KEY_USER);
		if(user!=null&&user.getId()!=null){//登录状态
			Resume resume = resumeService.getByUserId(user);
			Image headImg = resume!=null?resume.getImage():null;
			model.addAttribute("headimg", headImg);
			model.addAttribute("userInfo", user);
			return "account/signupRecord";
		}
		String backUrl=request.getRequestURI();
		redirectAttributes.addFlashAttribute("url", backUrl);
		return "redirect:/login";
	}
	/**
	 * 去改密码
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("changePassword")
	public String toChangePassword(HttpServletRequest request,Model model){
		User user =  (User)request.getSession().getAttribute(Constants.SESSION_KEY_USER);
		if(user!=null&&user.getId()!=null){//登录状态
			Image headImg = resumeService.getByUserId(user).getImage();
			model.addAttribute("headimg", headImg);
			model.addAttribute("userInfo", user);
			return "account/changePassword";
		}
		return "redirect:/login";
	}
	/**
	 * 更改密码
	 * @param model
	 * @return
	 */
	@RequestMapping("changepwd")
	public String changePassword(HttpServletRequest request,Model model,
			@RequestParam String oldpwd,
			@RequestParam String newpwd,
			@RequestParam String newpwd2){
		
		User user =  (User)request.getSession().getAttribute(Constants.SESSION_KEY_USER);
		if(user!=null&&user.getId()!=null){//登录状态
			Image headImg = resumeService.getByUserId(user).getImage();
			model.addAttribute("headimg", headImg);
		}else{
			return "redirect:/login";
		}
		if(!newpwd.equals(newpwd2)){
			model.addAttribute("err_msg", "两次输入的密码不正确！");
			return "account/changePassword";
		}
		
		if(user!=null&&user.getId()!=null){
			if(accountService.changepwd(oldpwd, newpwd, user.getId())==1){
				model.addAttribute("err_msg", "更新密码成功！");
			}else{
				model.addAttribute("err_msg", "原密码错误！");
			}
			
		}
		else{
			model.addAttribute("err_msg", "请重新登陆！");
		}
		return "account/changePassword";
	}
	/**
	 * 根据省份查询城市列表 
	 * @param provinceId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("cityList")
	public List<CodeAreaCity> findCityByProvince(@RequestParam("provinceId")Integer provinceId){
		return codeAreaCityService.findCityByProvinceId(provinceId);
	}
	
	/**
	 * 省
	 * @param model
	 */
	@ModelAttribute
	public void getProvinceList(Model model){
		model.addAttribute("provinceList", cache.get(Constants.CACHE_KEY_PROVINCE).getObjectValue());
	}
	/**
	 *市
	 * @param model
	 */
	@ModelAttribute
	public void getCityList(Model model){
		model.addAttribute("cityList", cache.get(Constants.CACHE_KEY_CITY).getObjectValue());
	}
	/**
	 * 行业
	 * @param model
	 */
	@Deprecated
	@ModelAttribute
	public void getIntentList(Model model){
		model.addAttribute("intentList", cache.get(Constants.CACHE_KEY_DIC_INDUSTRY).getObjectValue());
	}
	/**
	 * 薪资
	 * @param model
	 */
	@ModelAttribute
	public void getSalaryList(Model model){
		model.addAttribute("salaryList", cache.get(Constants.CACHE_KEY_DIC_SALARY).getObjectValue());
	}
	
	/**
	 * 民族
	 * @param model
	 */
	@ModelAttribute
	public void getNationList(Model model){
		model.addAttribute("nationList", cache.get(Constants.CACHE_KEY_DIC_NATION).getObjectValue());
	}
	
	/**
	 * 学历
	 * @param model
	 */
	@ModelAttribute
	public void getEducationList(Model model){
		model.addAttribute("educationList", cache.get(Constants.CACHE_KEY_EDUCATION).getObjectValue());
	}
	/**
	 * 求职状态
	 * @param model
	 */
	@ModelAttribute
	public void getEmploymentStatusList(Model model){
		model.addAttribute("employmentStatusList", cache.get(Constants.CACHE_KEY_DIC_EMPLOYMENTSTATUS).getObjectValue());
	}
	/**
	 * 婚姻状况
	 * @param model
	 */
	@ModelAttribute
	public void getMaritalStatusList(Model model){
		model.addAttribute("maritalStatusList", cache.get(Constants.CACHE_KEY_DIC_MARITALSTATUS).getObjectValue());
	}
	/**
	 * 工作年限
	 * @param model
	 */
	@ModelAttribute
	public void getWorkYearList(Model model){
		model.addAttribute("workYearList", cache.get(Constants.CACHE_KEY_DIC_WORKYEAR).getObjectValue());
	}

}
