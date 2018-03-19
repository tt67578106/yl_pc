package com.ylw.web.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ylw.entity.base.User;
import com.ylw.service.base.*;
import com.ylw.service.utm.UtmLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ylw.entity.base.Branch;
import com.ylw.entity.base.Feedback;
import com.ylw.entity.base.RecommendJob;
import com.ylw.entity.job.JobBase;
import com.ylw.service.advert.AdvertAreaPositionService;
import com.ylw.service.job.JobBaseService;
import com.ylw.service.job.JobTagService;
import com.ylw.service.job.JobTypeService;
import com.ylw.service.sys.AppKeyService;
import com.ylw.util.Constants;
import com.ylw.util.FreemarkUtil;
import com.ylw.util.IPUtil;

@Controller
public class IndexController {
	@Autowired
	private RecommendAdService recommendAdService;
	@Autowired
	private RecommendJobService recommendJobService;
	@Autowired
	private RecommendCompanyService recommendCompanyService;
	@Autowired
	private JobBaseService jobBaseService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private	JobTagService jobTagService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private FeedbackService feedBackService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private TmpApplyService tmpApplyService;
	@Autowired
	private AccessLogService accessLogService;
	@Autowired
	private CookieService cookieService;
	@Autowired
	private AppKeyService appKeyService;
	@Autowired
	private JobTypeService jobTypeService;
	@Autowired
	private AdvertAreaPositionService advertAreaPositionService;
	@Autowired
	private UtmLogService utmLogService;
	@Autowired
	private UserService userService;
	
	private Pattern urlPattern = Pattern.compile("^"+Constants.DOMAIN_NAME+"|^www."+Constants.DOMAIN_NAME);
	/**
	 * 默认首页
	 * @param fromKey
	 * @param appChannelCode
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(@RequestParam(value="fk",required=false)String fromKey, 
			@RequestParam(value="cc",required=false)String appChannelCode,
			HttpServletRequest request,HttpServletResponse response,
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
		Integer branchId = branch.getId();
		if(urlPattern.matcher(request.getServerName()).find()){
			String url = "http://"+branch.getWebPrefix()+"."+Constants.DOMAIN_NAME;
			if(StringUtils.isNotBlank(fromKey)){
				if(url.contains("?")){
					url = url+"&fk="+fromKey;
				}else{
					url = url+"?fk="+fromKey;
				}
			}
			if(StringUtils.isNotBlank(appChannelCode)){
				if(url.contains("?")){
					url = url+"&cc="+appChannelCode;
				}else{
					url = url+"?cc="+appChannelCode;
				}
			}
			return "redirect:/redirect?url="+url;
		}
		//意向城市
		model.addAttribute("branchList", branchService.findAllBranch());
		//期望工作
		model.addAttribute("jobTypes", jobTypeService.findJobTypeByParentId(0));
		//附加要求，福利
		model.addAttribute("jobTags", jobTagService.findByParentId(0));
		//shufflingJobs	首页轮播
		model.addAttribute("shufflingJobs", advertAreaPositionService.findAdvertResourceRecordByCityIdAndPositionId(branchId, Constants.POSITION_CODE_HOME_SHUFFLING_JOBS_Code));
		//热门搜索
		model.addAttribute("hotRecommonds", advertAreaPositionService.findAdvertResourceRecordByCityIdAndPositionId(branchId, Constants.POSITION_CODE_HOME_HOT_RECOMMONDS_Code));
		//findJobCache 首页轮播图下面最新招聘
		model.addAttribute("newJobs", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_NEW_BOTTOM,branchId,1,6,"updatetime","bottom"));
		//区块广告位
		model.addAttribute("advertPositionVos", advertAreaPositionService.findAdvertAreaCache(Constants.CACHE_KEY_ADVERT_AREA_POSITIONS, branchId, 5));
		//首页底部友情链接
		model.addAttribute("blogroll", advertAreaPositionService.findAdvertResourceRecordByCityIdAndPositionId(branchId, Constants.POSITION_CODE_HOME_BLOGROLL_Code));
		
		
		
//		//activities 首页活动
//		model.addAttribute("activities", recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_HOME_ARTICLE_ACTIVITIES,Constants.POSITION_CODE_HOME_ARTICLE_ACTIVITIES_Code,3,branchId));
//		//activities 首页顶部广告位
//		model.addAttribute("topAds", recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_HOME_TOP_AD,Constants.POSITION_CODE_HOME_TOP_AD_Code,1,branchId));
//		//首页中部的广告推荐位
//		model.addAttribute("centerRecommendAd", recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_HOME_CENTER_JOBS,Constants.POSITION_CODE_HOME_CENTER_JOBS_Code,1,branchId));
//		//recommendCompanys 首页名企专区推荐的企业
//		model.addAttribute("recommendCompanyList", recommendCompanyService.findRecommendCompanyCache(Constants.CACHE_KEY_RECOMMEND_COMPANY, Constants.POSITION_CODE_RECOMMEND_COMPANYS,branchId));
//		//大家都想进
//		model.addAttribute("companyList",  companyService.getCompanyApplyCache(15));//通过大家都想进 倒序排列拿15条
//		//findRecommendJobCache 首页显示优蓝精选
//		model.addAttribute("recommendJobListYLJX", recommendJobService.findRecommendJobCache(Constants.CACHE_KEY_RECOMMEND_JOB_YLJX, Constants.POSITION_CODE_RECOMMEND_JOBS_YLJX,branchId));
//		//recommendJobs 首页显示优蓝精选下面的岗位信息
//		model.addAttribute("jobListYLJXbottom", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_YLJX_BOTTOM,branchId,1,6,"updatetime",null));
//		//findJobCache 首页显示待遇优厚
//		model.addAttribute("jobListDYYH", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_DYYH,branchId,2,6,"updatetime",null));
//		//findJobCache 首页显示待遇优厚下面的岗位信息
//		model.addAttribute("jobListDYYHbottom", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_DYYH_BOTTOM,branchId,3,6,"updatetime",null));
//		//findJobCache 首页底部热门岗位
//		model.addAttribute("hotJobs", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_HOT_BOTTOM,branchId,1,10,"applycount","bottom"));
//		//everyonesearch	热招  -大家都在找
//		model.addAttribute("everyoneSearch", jobBaseService.findHotJobCache(1, 6, null, branchId));
//		//工作机会 岗位数量  
//		model.addAttribute("jobCount", jobBaseService.getCountJob());
//		//知名企业  企业数量
//		model.addAttribute("companyCount", companyService.countCompany());
//		//成功服务人数 优蓝网友
//		model.addAttribute("applyCount", jobBaseService.countJobApply());
//		//求职帮助 
		model.addAttribute("helps", articleService.findRecommendArticleCacheByArticleType(Constants.CACHE_KEY_HOME_ARTICLE_HELPS, 10, 3));
//		//小蓝有约
//		model.addAttribute("xiaolan", articleService.findRecommendArticleCacheByXiaolan(Constants.CACHE_KEY_ARTICLE_XLYY, 8, 2,null));
//		//首页下左轮播findRecommendArticleCacheByXiaolan（ 缓存key, pageSize, 文章类型, 推荐状态）
//		model.addAttribute("leftArticle", articleService.findRecommendArticleCacheByXiaolan(Constants.CACHE_KEY_LEFT_INDEX_ARTICLE, 5, 5,1));
//		//首页下中推荐
		model.addAttribute("centerArticle",articleService.findRecommendArticleCacheByXiaolan(Constants.CACHE_KEY_CENTER_INDEX_ARTICLE, 7, 5,1));
		model.addAttribute("homeActive", "current");
		model.addAttribute("branch", branch);
		return "index_v2";
	}
	/**
	 * 使用HTML结尾访问后的跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/index.html")
	public String indexHtml(@RequestParam(value="fk",required=false)String fromKey,@RequestParam(value="cc",required=false)String appChannelCode, HttpServletRequest request, HttpServletResponse response, Model model){
		return this.index(fromKey,appChannelCode, request, response, model);
	}
	/**
	 * TODO ftl尚未完成
	 * @param request
	 * @return
	 */
	@RequestMapping("static")
	public String staticFile(@RequestParam(value="password")String password, HttpServletRequest request){
		FreemarkUtil fu = new FreemarkUtil();
		Map<String, Object> model = new HashMap<String, Object>();
		Integer branchId = 1;
		model.put("shufflingJobs", recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_HOME_SHUFFLING_JOBS,Constants.POSITION_CODE_HOME_SHUFFLING_JOBS_Code,10,branchId));
		//activities 首页活动
		model.put("activities", recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_HOME_ARTICLE_ACTIVITIES,Constants.POSITION_CODE_HOME_ARTICLE_ACTIVITIES_Code,3,branchId));
		//activities 首页顶部广告位
		model.put("topAds", recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_HOME_TOP_AD,Constants.POSITION_CODE_HOME_TOP_AD_Code,1,branchId));
		//首页中部的广告推荐位
		model.put("centerRecommendAd", recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_HOME_CENTER_JOBS,Constants.POSITION_CODE_HOME_CENTER_JOBS_Code,1,branchId));
		//recommendCompanys 首页名企专区推荐的企业
		model.put("recommendCompanyList", recommendCompanyService.findRecommendCompanyCache(Constants.CACHE_KEY_RECOMMEND_COMPANY, Constants.POSITION_CODE_RECOMMEND_COMPANYS,branchId));
		//大家都想进
		model.put("companyList",  companyService.getCompanyApplyCache(15));//通过大家都想进 倒序排列拿15条
		//findRecommendJobCache 首页显示优蓝精选
		model.put("recommendJobListYLJX", recommendJobService.findRecommendJobCache(Constants.CACHE_KEY_RECOMMEND_JOB_YLJX, Constants.POSITION_CODE_RECOMMEND_JOBS_YLJX,branchId));
		//recommendJobs 首页显示优蓝精选下面的岗位信息
		model.put("jobListYLJXbottom", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_YLJX_BOTTOM,branchId,1,6,"updatetime",null));
		//findJobCache 首页显示待遇优厚
		model.put("jobListDYYH", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_DYYH,branchId,2,6,"updatetime",null));
		//findJobCache 首页显示待遇优厚下面的岗位信息
		model.put("jobListDYYHbottom", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_DYYH_BOTTOM,branchId,3,6,"updatetime",null));
		//findJobCache 首页底部热门岗位
		model.put("hotJobs", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_HOT_BOTTOM,branchId,1,10,"applycount","bottom"));
		//findJobCache 首页底部今日新增
		model.put("newJobs", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_NEW_BOTTOM,branchId,1,6,"updatetime","bottom"));
		//everyonesearch	热招  -大家都在找
		model.put("everyoneSearch", jobBaseService.findHotJobCache(1, 6, null, branchId));
		//工作机会 岗位数量  
		model.put("jobCount", jobBaseService.getCountJob());
		//知名企业  企业数量
		model.put("companyCount", companyService.countCompany());
		//成功服务人数 优蓝网友
		model.put("applyCount", jobBaseService.countJobApply());
		//求职帮助 
		model.put("helps", articleService.findRecommendArticleCacheByArticleType(Constants.CACHE_KEY_HOME_ARTICLE_HELPS, 8, 3));
		//小蓝有约
		model.put("xiaolan", articleService.findRecommendArticleCacheByXiaolan(Constants.CACHE_KEY_ARTICLE_XLYY, 8, 2,0));
		//首页下左轮播
		model.put("leftArticle", articleService.findRecommendArticleCacheByXiaolan(Constants.CACHE_KEY_LEFT_INDEX_ARTICLE, 5, 5,1));
		//首页下中推荐
		model.put("centerArticle",articleService.findRecommendArticleCacheByXiaolan(Constants.CACHE_KEY_CENTER_INDEX_ARTICLE, 3, 1,0));
		String ctx = request.getScheme() + "://" + request.getServerName() +":"+request.getLocalPort()+ request.getContextPath();
		fu.genFileByTemplate("index.ftl", model, request.getSession().getServletContext().getRealPath("/"), "index", request.getSession().getServletContext(), ctx);
		return "redirect:/";
	}
	/**
	 * 通过Ajax方式获得展示在首页的城市名企
	 * @param cityid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="index/recommedJobAjax",method=RequestMethod.POST)
	public List<RecommendJob> recommedJobAjax(HttpServletRequest request){
		Integer branchId = branchService.getBranchIdByRequest(request, IPUtil.getIpAddr(request));
		return recommendJobService.findRecommendList(0,6, null, branchId);
	}
	
	
	/**
	 * 通过Ajax方式根据名企专区的企业id得到该企业下所有的职位
	 * @param companyid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="index/jobListAjax",method=RequestMethod.POST)
	public Page<JobBase> jobListAjax(@RequestParam Integer companyId){
		return  jobBaseService.findByCompanyId(companyId,3);
	}
	/**
	 * feedback页面
	 * @return
	 */
	@RequestMapping(value="feedback.html", method=RequestMethod.GET)
	public String feedback(){
		return "feedback";
	}
	/**
	 * 保存feedback
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="feedback",method=RequestMethod.POST)
	public String saveFeedBack(@Valid Feedback entity,HttpServletRequest request, RedirectAttributes redirectAttributes){
		feedBackService.save(entity,IPUtil.getIpAddr(request));
		//获取反馈ID
		Integer feedBackId = entity.getId();
		//获取手机号
		String mobile = entity.getContactInfo();
		//获取当前页面URL
		String utmUrl = request.getHeader("referer");
		//获取用户ID
//		Integer id = userService.getByLoginname(mobile).getId();
		User byLoginname = userService.getByLoginname(mobile);
		Long userId;
		if(byLoginname==null){
			userId=null;
		}else{
			userId = Long.valueOf(byLoginname.getId());
		}
		////存储用户访问信息（反馈）
		utmLogService.saveUtmLog(null,null,null,"求职者", userId, utmUrl,
				"PC", 6, feedBackId.toString(),null,null,"反馈",
				"主动", "ylife_recruit", "00d55953f18911e594980800277a9591",null,null);
		redirectAttributes.addFlashAttribute("msg", "您的反馈已经被接受，感谢您的参与！");
		return "redirect:/feedback.html";
	}
	/**
	 * 根据用户请求返回分站列表
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="branch",method=RequestMethod.GET)
	public List<Branch> branchs(HttpServletRequest request){
		List<Branch> branchList = null;
		Integer branchId = (Integer) request.getSession().getAttribute(Constants.SESSION_KEY_BRANCH_ID);
		String branchName = (String) request.getSession().getAttribute(Constants.SESSION_KEY_BRANCH_NAME);
		if(branchId == null){
			branchList = branchService.findBranchByRequest(IPUtil.getIpAddr(request));
			if(branchList!=null && branchList.size() == 1){//存入分站id
				request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_ID,branchList.get(0).getId());
				request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_NAME,branchList.get(0).getBranchName());
			}else{//TODO默认上海站
				request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_ID,1);
				request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_NAME,"上海站");
			}
			return branchList;
		}else{
			branchList = new ArrayList<Branch>();
			branchList.add(new Branch(branchId,branchName));
			return branchList;
		}
	}
	/**
	 * 保存分站到session中
	 * @param branchId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="branch",method=RequestMethod.POST)
	public String setBranch(@RequestParam("branchId")Integer branchId,HttpServletRequest request){
		Branch branch = branchService.getById(branchId);
		if(branch!=null){
			request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_ID,branch.getId());
			request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_NAME,branch.getBranchName());
			request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_WEB_PREFIX,branch.getWebPrefix());
			return branch.getWebPrefix();
		}
		return Constants.RETURN_STATUS_ERROR;
	}
	/**
	 * 协议
	 * @return
	 */
	@RequestMapping("agreement.html")
	public String agreement(){
		return "account/agreement";
	}
	/**
	 * 登出
	 * @param valiCookie
	 * @param request
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(@CookieValue(value=Constants.COOKIE_KEY_USER_VERIFY,required=false)String valiCookie,HttpServletRequest request,RedirectAttributes redirect){
		accountService.clearLogin(valiCookie, request.getSession());
		redirect.addFlashAttribute("clean", true);
		return "redirect:/";
	}
	/**
	 * 跳转
	 * @param url
	 * @return
	 */
	@RequestMapping("jumper")
	public String jumper(@RequestParam(value="url",required=false)String url){
		if(StringUtils.isNotBlank(url)){
			return "redirect:"+url;
		}
		return "redirect:/";
	}
	
	/**
	 * 404
	 * @return
	 */
	@RequestMapping("404")
	public String errorHtml(){
		return "error/404";
	}
}
