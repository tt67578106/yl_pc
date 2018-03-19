package com.ylw.web.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ylw.entity.base.Company;
import com.ylw.entity.community.UserCommunityTalk;
import com.ylw.entity.community.UserCommunityTalkComment;
import com.ylw.service.utm.UtmLogService;
import net.sf.ehcache.Cache;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import com.google.common.collect.Maps;
import com.ylw.entity.base.Branch;
import com.ylw.entity.base.User;
import com.ylw.entity.job.JobBase;
import com.ylw.entity.job.JobType;
import com.ylw.entity.vo.CompanyListVo;
import com.ylw.entity.vo.UserCommunityTalkVo;
import com.ylw.service.advert.AdvertAreaPositionService;
import com.ylw.service.base.AccountService;
import com.ylw.service.base.AdvisoryService;
import com.ylw.service.base.BranchService;
import com.ylw.service.base.CompanyService;
import com.ylw.service.base.CookieService;
import com.ylw.service.base.RecommendAdService;
import com.ylw.service.base.RecommendJobService;
import com.ylw.service.base.ResumeService;
import com.ylw.service.code.CodeAreaDistrictService;
import com.ylw.service.job.InterviewScheduleService;
import com.ylw.service.job.JobBaseService;
import com.ylw.service.job.JobTagService;
import com.ylw.service.job.JobTypeService;
import com.ylw.service.sys.AppKeyService;
import com.ylw.util.Constants;
import com.ylw.util.IPUtil;
import com.ylw.util.StoreCacheUtil;
import com.ylw.util.URLRewriteUtil;

@Controller
@RequestMapping("job")
public class JobController {
	private Logger logger  = LoggerFactory.getLogger(JobController.class);
	@Autowired
	private AccountService accountService;
	@Autowired
	private JobBaseService jobBaseService;
	@Autowired
	private AdvisoryService advisoryService;
	@Autowired
	private	JobTagService jobTagService;
	@Autowired
	private ResumeService resumeService;
	@Autowired
	private RecommendJobService recommendJobService;
	@Autowired
	private BranchService branchService;
	@Autowired
	CodeAreaDistrictService codeAreaDistrictService;
	@Autowired
	private RecommendAdService recommendAdService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private AdvertAreaPositionService advertAreaPositionService;
	@Autowired
	private AppKeyService appKeyService;
	@Autowired
	private CookieService cookieService;
	@Autowired
	private InterviewScheduleService interviewScheduleService;
	@Autowired
	private UtmLogService utmLogService;
//	@Autowired
//	private AccessLogService accessLogService;
	@Autowired
	private JobTypeService jobTypeService;
	private static final String PAGE_SIZE = "10";
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "默认排序");
		sortTypes.put("updatetime", "更新日期");
		sortTypes.put("salarydesc", "薪资");
	}
	private Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
	/**
	 * 热门岗位
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value="fk",required=false)String fromKey, 
			@RequestParam(value="cc",required=false)String appChannelCode,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber, 
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType,
			Model model, 
			HttpServletRequest request,
			HttpServletResponse response) {
		if(StringUtils.isNotBlank(fromKey)){
			if(appKeyService.validAppKey(fromKey)){
				cookieService.setCookie(Constants.COOKIE_KEY_FROM_KEY,fromKey,response);
			}
		}
		if(StringUtils.isNotEmpty(appChannelCode)){
			if(appKeyService.validAppChannelCode(appChannelCode)){
				cookieService.setCookie(Constants.COOKIE_KEY_APP_CHANNEL_CODE,appChannelCode,response);
			}
		}
	//	accessLogService.addAccessLog(request,4,null);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request,"search_");
		Branch branch=null;
		if(searchParams.get("EQ_cityid")!=null&&!searchParams.get("EQ_cityid").equals("0")&&!searchParams.get("EQ_cityid").equals("")){
			branch = branchService.getBranchByCityId(Integer.parseInt(searchParams.get("EQ_cityid").toString()),request,IPUtil.getIpAddr(request));
		}else{
			branch = branchService.getBranchByRequest(request, IPUtil.getIpAddr(request));
		}
		if(request.getServerName().contains(Constants.DOMAIN_NAME)){
			String webPrefix=request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "");
			if(!webPrefix.equals(branch.getWebPrefix())){
				return "redirect:/redirect?url=http://"+branch.getWebPrefix()+"."+Constants.DOMAIN_NAME+"/zhaopin";
			}
		}
		
		Page<CompanyListVo> page = jobBaseService.search(searchParams, pageNumber, pageSize, sortType, branch.getId());
		if(!page.hasContent()){
			model.addAttribute("recommendJobList", recommendJobService.findRecommendJobCache(Constants.CACHE_KEY_RECOMMEND_JOB_YLJX, Constants.POSITION_CODE_RECOMMEND_JOBS_YLJX,branch.getId()));
		}else{
			model.addAttribute("recommendJobList", null);
		}
		Integer cityId = branchService.getById(branch.getId())==null?0:branchService.getById(branch.getId()).getDefaultCityId();
		model.addAttribute("page", page);
		model.addAttribute("jobCount", page.getTotalElements());//工作数量
		//model.addAttribute("districts", codeAreaDistrictService.findBycityId(cityId));//登录站点对于行政区域
		model.addAttribute("otherCitys", branchService.findBranchCityList(branch.getId(),cityId));//周边城市
		//model.addAttribute("rightJobListRecommend", recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_ARTICLE_RIGHT_AD,Constants.POSITION_CODE_RECOMMEND_ARTICLE_RIGHT_Code,3,branch.getId()));
		model.addAttribute("labels", jobTagService.findJobTagList(1,20,1,0,"sorting"));
		model.addAttribute("jobTypes", jobTypeService.findJobTypeByParentId(0));
		//model.addAttribute("sortType", sortType);
		//model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		String pageUrl ="zhaopin/0_0_0_0";
		if(searchParams!=null&&searchParams.size()>0){
			if(searchParams.containsKey("LIKE_jobType")){
				pageUrl =searchParams.get("LIKE_jobType").toString().replace("job", "zhaopin")+"/";
				searchParams.remove("LIKE_jobType");
				pageUrl=pageUrl+URLRewriteUtil.getUrlByParams(searchParams);
			}else{
				pageUrl = URLRewriteUtil.getUrlByParams(searchParams);
			}
		}
		model.addAttribute("pageUrl",pageUrl);
		model.addAttribute("jobActive", "current");
		//热门搜索
		model.addAttribute("hotRecommonds", advertAreaPositionService.findAdvertResourceRecordByCityIdAndPositionId(branch.getId(), Constants.POSITION_CODE_HOME_HOT_RECOMMONDS_Code));
		//名企推荐
		model.addAttribute("recommondCompanys", advertAreaPositionService.findAdvertResourceRecordByCityIdAndPositionId(branch.getId(), Constants.POSITION_CODE__RECOMMOND_COMPANY_Code));
		// findJobCache 底部热门岗位
		//model.addAttribute("hotJobs", jobBaseService.getJobByBranchIdCache(10,branch.getId()));
		// findJobCache 底部今日新增
		//model.addAttribute("newJobs", jobBaseService.getJobByBranchIdCache(10,branch.getId()));
		//热词
		//model.addAttribute("hotWords",jobBaseService.findHotWords());
		model.addAttribute("newJobs", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_NEW_BOTTOM,branch.getId(),1,6,"updatetime","bottom"));
		return "job/jobHotList";
	}
	/**
	 * 热门岗位ajax
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="listAjax")
	public Page<JobBase> ajaxList(@RequestParam(value = "page", defaultValue = "1") int pageNumber, @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize, @RequestParam(value = "sortType", defaultValue = "auto") String sortType,
			@RequestParam(value = "jobType")String jobType,@RequestParam(value = "subJobType")String subJobType,@RequestParam(value = "salary")String salary,@RequestParam(value = "otherCity")String otherCity,
			@RequestParam(value = "districts")String districts,@RequestParam(value ="labels",required=false)String labels,HttpServletRequest request) {
		Integer branchId = branchService.getBranchIdByRequest(request, IPUtil.getIpAddr(request));
		Page<JobBase> page = jobBaseService.getHqlPageByAjax(pageNumber, pageSize, sortType, branchId, jobType,subJobType, salary, otherCity, districts, labels);
		return page;
	}
	/**
	 * 查看详细情况
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("detail/{id}.html")
	public String detail(@RequestParam(value="type",required=false)String type, 
			@RequestParam(value="scheduleCode",required=false)String scheduleCode,
			@CookieValue(value = Constants.COOKIE_KEY_USER_VERIFY, required = false) String cookieValue,
			@PathVariable("id") Integer id,
			Model model,
			HttpServletRequest request,
			HttpServletResponse response) {
		//accessLogService.addAccessLog(request,5,id);
		User user = (User) accountService.validateLogin(cookieValue, request.getSession());
		if (user != null) {

			model.addAttribute("resumeBase", resumeService.getByUserId(user));

		}
		JobBase jobBase = jobBaseService.getJobBase(id);
		if (jobBase == null) {
			return "redirect:/404";
		}
		List<JobBase> likeJobs = jobBaseService.findLikeJobs(jobBase,null);
		if(likeJobs.size() < 5){
			likeJobs = jobBaseService.findLikeJobs(jobBase,"most");
		}
		if(jobBase!=null&&jobBase.getJobConfig().getIsPublish()==0||jobBase.getJobConfig().getIsDel()==-1){
			model.addAttribute("likeJobs", likeJobs);
			return "/job/404";
		}
		Integer jobCityId = jobBase.getCity().getId();
		if(jobCityId == 78 && jobBase.getCountyid()!=null &&jobBase.getCountyid() == 785){
			jobCityId = 346;
		}
		Branch branch = branchService.getBranchByCityId(jobCityId,request,IPUtil.getIpAddr(request));
		if(branch !=null && request.getServerName().contains(Constants.DOMAIN_NAME)){
			String webPrefix=request.getServerName().substring(0,request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "");
			if(!webPrefix.equals(branch.getWebPrefix())){
				String url = branch.getWebPrefix()+"."+Constants.DOMAIN_NAME+"/zhaopin_"+id+".html";
				return "redirect:/redirect?url=http://"+url;
			}
		}
		//JobBase nextJobBase = jobBaseService.getNextJob(id);
		model.addAttribute("imageList", jobBaseService.getCompanySence(jobBase));
		model.addAttribute("jobBase", jobBase);
		//model.addAttribute("nextJobBase",nextJobBase);
		model.addAttribute("companyJobs", jobBaseService.findOtherJobsByCompanyId(jobBase.getCompany().getId(), jobBase.getId(), 5));
		List<JobBase> mostJobs = jobBaseService.findLikeJobs(jobBase,"most");
		if(mostJobs.size() < 5){
			mostJobs = jobBaseService.findLikeJobs(jobBase,"new");
		}
		List<JobBase> mostNewJobs = jobBaseService.findLikeJobs(jobBase,"new");
		model.addAttribute("educationList", StoreCacheUtil.getCacheValue("staticCache", Constants.CACHE_KEY_EDUCATION));
		model.addAttribute("jobTypes", jobTypeService.findJobTypeByParentId(0));
		//热词
		//model.addAttribute("hotWords",jobBaseService.findHotWords());
		model.addAttribute("jobs", jobBaseService.findByCompanyId(jobBase.getCompany().getId(), 3).getContent());
		//model.addAttribute("rightJobListRecommend", recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_ARTICLE_RIGHT_AD,Constants.POSITION_CODE_RECOMMEND_ARTICLE_RIGHT_Code,3,branch.getId()));
		model.addAttribute("likeJobs", likeJobs);
		model.addAttribute("mostJobs", mostJobs);
		model.addAttribute("mostNewJobs", mostNewJobs);
		model.addAttribute("jobDetail", jobBase.getJobDetail());
		model.addAttribute("newJobs", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_NEW_BOTTOM,branch.getId(),1,6,"updatetime",null));
		//model.addAttribute("newJobs", jobBaseService.getJobByBranchIdCache(10,branch.getId()));
		model.addAttribute("jobActive", "current");
		model.addAttribute("advisoryCount", advisoryService.countByJobBaseIdAndType(id));
		model.addAttribute("type", type);
		model.addAttribute("scheduleCode", scheduleCode);
		model.addAttribute("averageSalary", companyService.getAverageSalary(jobBase.getCompany().getId()));
		//问答
		Page<UserCommunityTalkVo> talkList =advisoryService.findUserCommunityTalkVo(id,null, new HashMap<String, Object>(), 1, 10, "auto");
		model.addAttribute("talkList",talkList );
		model.addAttribute("askCount", talkList.getTotalElements());
		//查询岗位拥有的排期
		model.addAttribute("scheduleList",interviewScheduleService.findScheduleByJobId(id));
		return "job/jobDetailNew";
	}
	/**
	 * 根据岗位id 得到问答列表
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("advisories/{id}")
	public Page<UserCommunityTalkVo> viewAdvisory(@PathVariable("id") Integer id, 
			@RequestParam(value = "page", defaultValue = "1") int pageNumber, 
			@RequestParam(value = "page.size", defaultValue = "10") int pageSize, 
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, 
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page<UserCommunityTalkVo> page = advisoryService.findUserCommunityTalkVo(id,null, searchParams, pageNumber, pageSize, sortType);
		return page;
	}

	/**
	 * 提交问题
	 * 
	 * @param cookieValue
	 * @param jobId
	 * @param comment
	 * @param companyId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "postAdvisory", method = RequestMethod.POST)
	public String postAdvisory(HttpServletRequest request,@CookieValue(value = Constants.COOKIE_KEY_USER_VERIFY, required = false) String cookieValue, @RequestParam("companyId") Integer companyId, @RequestParam(value="jobId",required = false) Integer jobId, @RequestParam("comment") String comment) {
		User user = (User) accountService.validateLogin(cookieValue, request.getSession());
		if (user == null) {
			return Constants.RETURN_STATUS_ERROR;
		} else {
			Branch branch = branchService.getBranchByRequest(request, IPUtil.getIpAddr(request));
			List<Integer> city  = branchService.findBranchCityIdList(Integer.parseInt(branch.getId()+""));
			advisoryService.saveJobAdvisory(user.getId(), user.getUsername(), companyId, jobId,comment, comment,city.get(0));
			return Constants.RETURN_STATUS_SUCCESS;
		}
	}
	

	/**
	 * 提交回复
	 * @param request
	 * @param cookieValue
	 * @param userCommunityTalkId
	 * @param content
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "postReply", method = RequestMethod.POST)
	public String postReply(HttpServletRequest request,
			@CookieValue(value = Constants.COOKIE_KEY_USER_VERIFY, required = false) String cookieValue, 
			@RequestParam("userCommunityTalkId") Integer userCommunityTalkId, 
			@RequestParam("content") String content) {
		User user = (User) accountService.validateLogin(cookieValue, request.getSession());
		UserCommunityTalk userCommunityTalk = utmLogService.getUserCommunityTalk(userCommunityTalkId,1);
		Integer companyId = userCommunityTalk.getCompany().getId();

		//获取当前页面URL
		String utmUrl = request.getHeader("referer");
		if (user == null) {
			return Constants.RETURN_STATUS_ERROR;
		} else {
			//存储用户访问信息（问题详情页）
			utmLogService.saveUtmLog(null,null,cookieValue,"求职者", Long.valueOf(user.getId()),utmUrl, "PC",
					4, companyId.toString(),null,"企业问题/职位问题","companyanswer","主动",
					"ylife_recruit", "00d55953f18911e594980800277a9591",userCommunityTalkId.toString(),null);
			advisoryService.saveReply(user, userCommunityTalkId, content);
			return Constants.RETURN_STATUS_SUCCESS;
		}
	}

	@ResponseBody
	@RequestMapping("findSimilarJob")
	public List<JobBase> findSimilarJob(@RequestParam("jobId")Integer jobId,HttpServletRequest request){
		Integer branchId = branchService.getBranchIdByRequest(request,IPUtil.getIpAddr(request));
		Integer cityid = null;
		List<Integer> cityList = null; //该城市同一分站，其他城市
		int salaryfrom = 0;
		int salaryto = 0;
		JobBase job=jobBaseService.getJobBase(jobId);
		if(job!=null){
			cityid = job.getCity()==null?null:job.getCity().getId();
			cityList = branchService.findBranchCityIdList(branchId==null?1:branchId);
			salaryfrom = job.getJobDetail().getSalaryfrom()==null?0:job.getJobDetail().getSalaryfrom();
			salaryto =  job.getJobDetail().getSalaryto()==null?0:job.getJobDetail().getSalaryto();
			return recommendJobService.findSimilarJob(4, cityid, cityList, salaryfrom,salaryto);
		}
		return null;
	}

	/**
	 * 弹出报名快捷报名iframe
	 * 
	 * @return
	 */
	@RequestMapping("signUpQuickFrame")
	public String signUpQuickFrame() {
		return "job/signUpQuickFrame";
	}
	/**
	 * ajax 通过岗位类型得到默认图
	 * @param jobType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getImageByJobType",method=RequestMethod.POST)
	public String getImageByJobType(String jobType){
		return jobTypeService.getImageByJobType(jobType);
	}
	/**
	 * 根据父id查询分类对象
	 * @param parentId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findJobTypesByParent")
	public List<JobType> findJobTypeListByParent(@RequestParam("parentId")Integer parentId){
		return jobTypeService.findJobTypeByParentId(parentId);
	}
	
	@ModelAttribute 
	public void getProvinceList(Model model){
		model.addAttribute("provinceList", cache.get(Constants.CACHE_KEY_PROVINCE).getObjectValue());
	}
	
	@ModelAttribute
	public void getNationsList(Model model){
		model.addAttribute("nationsList", cache.get(Constants.CACHE_KEY_DIC_NATION).getObjectValue());
	}

	/**
	 * 404
	 * @return
	 */
	@RequestMapping("404")
	public String errorHtml(){
		return "job/404";
	}
}
