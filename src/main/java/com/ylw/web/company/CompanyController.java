package com.ylw.web.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import com.google.common.collect.Maps;
import com.ylw.entity.base.Branch;
import com.ylw.entity.base.Company;
import com.ylw.entity.base.User;
import com.ylw.entity.vo.CompanyListVo;
import com.ylw.entity.vo.UserCommunityTalkVo;
import com.ylw.service.advert.AdvertAreaPositionService;
import com.ylw.service.base.AccountService;
import com.ylw.service.base.AdvisoryService;
import com.ylw.service.base.ArticleService;
import com.ylw.service.base.BranchService;
import com.ylw.service.base.CompanyService;
import com.ylw.service.base.CookieService;
import com.ylw.service.base.DataDictionaryService;
import com.ylw.service.base.RecommendAdService;
import com.ylw.service.base.RecommendCompanyService;
import com.ylw.service.code.CodeAreaDistrictService;
import com.ylw.service.job.JobApplyService;
import com.ylw.service.job.JobBaseService;
import com.ylw.service.job.JobTagService;
import com.ylw.service.job.JobTypeService;
import com.ylw.service.sys.AppKeyService;
import com.ylw.util.Constants;
import com.ylw.util.IPUtil;
import com.ylw.util.StoreCacheUtil;
import com.ylw.util.URLRewriteUtil;

@Controller
@RequestMapping("company")
public class CompanyController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private JobBaseService jobBaseService;
	@Autowired
	private AdvisoryService advisoryService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private RecommendCompanyService recommendCompanyService;
	@Autowired
	private RecommendAdService recommendAdService;
	@Autowired
	private JobApplyService jobApplyService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CodeAreaDistrictService codeAreaDistrictService;
	@Autowired
	private JobTagService jobTagService;
	@Autowired
	private JobTypeService jobTypeService;
	@Autowired
	private AdvertAreaPositionService advertAreaPositionService;
	@Autowired
	private AppKeyService appKeyService;
	@Autowired
	private CookieService cookieService;
//	@Autowired
//	private AccessLogService accessLogService;
	@Autowired
	DataDictionaryService dataDictionaryService;
	private static final String PAGE_SIZE = "10";
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "默认");
		sortTypes.put("updatetime", "发布日期");
		sortTypes.put("salary", "薪资高低");
	}
	/**
	 * 企业首页
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String index(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			HttpServletRequest request){
		Branch branch = branchService.getBranchByRequest(request, IPUtil.getIpAddr(request));
		Integer branchId = branch.getId();
		if(request.getServerName().contains(Constants.DOMAIN_NAME)){
			String webPrefix=request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "");
			if(!webPrefix.equals(branch.getWebPrefix())){
				return "redirect:/redirect?url=http://"+branch.getWebPrefix()+"."+Constants.DOMAIN_NAME+"/qiye";
			}
		}
		model.addAttribute("topRecommendCompany", recommendAdService.findRecommendAdCache(Constants.CACHE_PC_KEY_COMPANY_RECOMMEND_TOP,Constants.POSITION_CODE_COMPANY_RECOMMEND_TOP_Code,30,branchId));//企业首页：岗位推荐位
		model.addAttribute("hottestCompany", companyService.findCompanyByPointOfPraise(Constants.CACHE_PC_KEY_HOT_COMPANY ,5,branchId));//通过大家都想进 倒序排列拿5条
		model.addAttribute("consultingCompany", companyService.findConsultingCompanyCache(Constants.CACHE_PC_KEY_CONSULTING_COMPANY,5));//拿出提问最多的公司
		model.addAttribute("rightRecommendAd",recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_ARTICLE_RIGHT_AD,Constants.POSITION_CODE_RECOMMEND_ARTICLE_RIGHT_Code,3,branchId));//右侧广告位
		model.addAttribute("companyListOne", recommendCompanyService.findRecommendCompanyByTypeCache(Constants.CACHE_PC_KEY_COMPANY_RECOMMEND_ONE,Constants.POSITION_CODE_COMPANY_RECOMMEND_ONE_Code,5,branchId,"普工"));//企业首页 ：企业推荐位2
		model.addAttribute("companyListTWO", recommendCompanyService.findRecommendCompanyByTypeCache(Constants.CACHE_PC_KEY_COMPANY_RECOMMEND_TWO,Constants.POSITION_CODE_COMPANY_RECOMMEND_TWO_Code,5,branchId,"技工"));//企业首页 ：企业推荐位2
		model.addAttribute("companyListThree",recommendCompanyService.findRecommendCompanyByTypeCache(Constants.CACHE_PC_KEY_COMPANY_RECOMMEND_THREE,Constants.POSITION_CODE_COMPANY_RECOMMEND_THREE_Code,5,branchId,"客服"));//企业首页 ：企业推荐位3
		model.addAttribute("companyCount", companyService.countCompany());
		model.addAttribute("yesterdayCompany", companyService.yesterdayCompany());
		model.addAttribute("rightRecommendCompanys", companyService.getCompanyByBranchIdCache(20,branchId));
		model.addAttribute("companyActive", "current");
		// findJobCache 底部热门岗位
		model.addAttribute("hotJobs", jobBaseService.getJobByBranchIdCache(10,branchId));
		// findJobCache 底部今日新增
		model.addAttribute("newJobs", jobBaseService.getJobByBranchIdCache(10,branchId));
		//列表页  TDK 规范
		Map<String, String> data = articleService.getArticleTDK(7);
		model.addAttribute("title", data.get("title"));
		model.addAttribute("keywords", data.get("keyword"));
		model.addAttribute("description", data.get("description"));
		//热词
		model.addAttribute("hotWords",jobBaseService.findHotWords());
		return "job/enterprises";
	}

	/**
	 * 名企招聘列表
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			HttpServletRequest request) {
		//accessLogService.addAccessLog(request,6,null);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Branch branch=null;
		if(searchParams.get("EQ_cityid")!=null&&!searchParams.get("EQ_cityid").equals("0")&&!searchParams.get("EQ_cityid").equals("")){
			branch = branchService.getBranchByCityId(Integer.parseInt(searchParams.get("EQ_cityid").toString()),request,IPUtil.getIpAddr(request));
		}else{
			branch = branchService.getBranchByRequest(request, IPUtil.getIpAddr(request));
		}
		Integer branchId = branch.getId();
		if(request.getServerName().contains(Constants.DOMAIN_NAME)){
			String webPrefix=request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "");
			if(!webPrefix.equals(branch.getWebPrefix())){
				return "redirect:/redirect?url=http://"+branch.getWebPrefix()+"."+Constants.DOMAIN_NAME+"/mingqi";
			}
		}		
		Page<CompanyListVo> page = companyService.getHqlPage(searchParams, pageNumber, pageSize, sortType,branchId);
//		if(!page.hasContent()){
//			model.addAttribute("similarCompanyList", companyService.findCompanyListByCity(branchId));
//		}else{
//			model.addAttribute("similarCompanyList", null);
//		}
		model.addAttribute("companyVoList",page);
		model.addAttribute("industryTypes", dataDictionaryService.getIndustryTypeList(Constants.CACHE_KEY_DIC_INDUSTRY_TYPE));
//		Integer cityId = branchService.getById(branchId)==null?0:branchService.getById(branchId).getDefaultCityId();
//		model.addAttribute("districts", codeAreaDistrictService.findBycityId(cityId));//登录站点对于行政区域
//		model.addAttribute("otherCitys", branchService.findBranchCityList(branchId,cityId));//周边城市
		// findJobCache 底部热门岗位
//		model.addAttribute("hotJobs", jobBaseService.getJobByBranchIdCache(10,branchId));
		// findJobCache 底部今日新增
//		model.addAttribute("newJobs", jobBaseService.getJobByBranchIdCache(10,branchId));
//		model.addAttribute("rightRecommendAd",recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_ARTICLE_RIGHT_AD,Constants.POSITION_CODE_RECOMMEND_ARTICLE_RIGHT_Code,3,branchId));//右侧广告位
//		model.addAttribute("companyCount", companyService.countCompany());
//		model.addAttribute("yesterdayCompany", companyService.yesterdayCompany());
		model.addAttribute("sortType", sortType);
//		model.addAttribute("current_company", "current");
		
		//热门搜索
		model.addAttribute("hotRecommonds", advertAreaPositionService.findAdvertResourceRecordByCityIdAndPositionId(branchId, Constants.POSITION_CODE_HOME_HOT_RECOMMONDS_Code));
		//求职贴士
		model.addAttribute("helps", articleService.findRecommendArticleCacheByArticleType(Constants.CACHE_KEY_HOME_ARTICLE_HELPS, 8, 3));
		//最新入驻企业
		model.addAttribute("rightRecommendCompanys", companyService.getCompanyByBranchIdCache(8,branchId));
		//相关招聘
		model.addAttribute("relatedRecruitments", advertAreaPositionService.findAdvertResourceRecordByCityIdAndPositionId(branchId, Constants.POSITION_CODE_RELATED_RECRUITMENT_Code));
		
		//列表页  TDK 规范
		Map<String, String> data = articleService.getArticleTDK(8);
		model.addAttribute("title", data.get("title"));
		model.addAttribute("keywords", data.get("keyword"));
		model.addAttribute("description", data.get("description"));
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
//		model.addAttribute("companyActive", "current");
		//URL重写后，分页
		model.addAttribute("pageUrl", URLRewriteUtil.getUrlByParams(searchParams));
		return "job/companyRecruit";
	}
	
	/**
	 * 公司详情首页
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("detail/{id}.html")
	public String companyDetailIndex(@RequestParam(value="fk",required=false)String fromKey, 
			@RequestParam(value="cc",required=false)String appChannelCode,
			@PathVariable("id")Integer id,Model model,HttpServletRequest request,HttpServletResponse response){
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
		//accessLogService.addAccessLog(request,7,id);
		Company company = companyService.getOnlineCompany(id);
		if(company==null){
			return "redirect:/404";
		}
		Branch branch =  branchService.getBranchByCityId(company.getCity().getId(),request,IPUtil.getIpAddr(request));
		if(branch !=null && request.getServerName().indexOf(Constants.DOMAIN_NAME) > -1){
			String webPrefix=request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "");
			if(!webPrefix.equals(branch.getWebPrefix())){
				String url = branch.getWebPrefix()+"."+Constants.DOMAIN_NAME+"/qiye_"+id+".html";
				return "redirect:/redirect?url=http://"+url;
			}
		}
//		Company nextCompany = companyService.getNextCompany(id);
		model.addAttribute("company", company);
//		model.addAttribute("nextCompany", nextCompany);
		model.addAttribute("jobCount", jobBaseService.countByCompanyId(id));
		model.addAttribute("jobs", jobBaseService.findByCompanyId(id, 3).getContent());
//		model.addAttribute("advisoryList", advisoryService.findUserCommunityTalkVo(null,id, new HashMap<String, Object>(), 1, 3, "auto").getContent());
//		model.addAttribute("applyList", jobApplyService.getApplysByCompanyPage(id,new HashMap<String, Object>(),1,20,"createTime"));
//		model.addAttribute("companyArticle", companyService.findArticlesByCompanyId(id,5));
//		model.addAttribute("similarCompany", companyService.findSimilarCompany(3, company.getCity()==null?null:company.getCity().getId(), company.getIndustryid(),id));
		//图像集List
		model.addAttribute("companyImage", companyService.findImageByTypeAndCompany(-1,id));
		model.addAttribute("averageSalary", companyService.getAverageSalary(company.getId()));
//		model.addAttribute("count", companyService.getRandCount(company.getSatisfaction(),company.getId()));
//		model.addAttribute("index", "current");
//		model.addAttribute("companyActive", "current");
		//底部
//		model.addAttribute("rightRecommendCompanys", companyService.getCompanyByBranchIdCache(20,branch.getId()));
//		model.addAttribute("hotWords",jobBaseService.findHotWords());
//		model.addAttribute("hotJobs", jobBaseService.getJobByBranchIdCache(10,branch!=null?branch.getId():null));
		
		//企业问答
		Page<UserCommunityTalkVo> talkList = advisoryService.findUserCommunityTalkVo(null,id, new HashMap<String, Object>(), 1, 10, "auto");
		model.addAttribute("talkList",talkList );
		model.addAttribute("askCount", talkList.getTotalElements());
		//最新岗位
		model.addAttribute("newJobs", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_NEW_BOTTOM,branch.getId(),1,6,"updatetime","bottom"));
		//相关招聘
//		model.addAttribute("relatedRecruitments", advertAreaPositionService.findAdvertResourceRecordByCityIdAndPositionId(branch!=null?branch.getId():null, Constants.POSITION_CODE_RELATED_RECRUITMENT_Code));
		return "job/companyDetailIndex";
	}
	
	/**
	 * 公司详情..岗位列表
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/companyDetailJobList")
	public String companyDetailJobList(@RequestParam("id") Integer id
			,Model model
			,HttpServletRequest request){
		Company company = companyService.getCompany(id);
		if(company==null){
			return "redirect:/404";
		}
		Branch branch =  branchService.getBranchByCityId(company.getCity().getId(),request,IPUtil.getIpAddr(request));
		if(branch !=null && request.getServerName().indexOf(Constants.DOMAIN_NAME) > -1){
			String webPrefix=request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "");
			if(!webPrefix.equals(branch.getWebPrefix())){
				String url = branch.getWebPrefix()+"."+Constants.DOMAIN_NAME+"/company/job_"+id+"/";
				return "redirect:/redirect?url=http://"+url;
			}
		}
		model.addAttribute("company", company);
		model.addAttribute("jobCount", jobBaseService.countByCompanyId(id));
		model.addAttribute("jobs", jobBaseService.findByCompanyIdBySql(id,1, 20).getContent());
//		model.addAttribute("applyList", jobApplyService.getApplysByCompanyPage(id,new HashMap<String, Object>(),1,20,"createTime"));
//		model.addAttribute("companyArticle", companyService.findArticlesByCompanyId(id,5));
//		model.addAttribute("similarCompany", companyService.findSimilarCompany(3, company.getCity().getId(), company.getIndustryid(),id));
		model.addAttribute("averageSalary", companyService.getAverageSalary(company.getId()));
//		model.addAttribute("jobList", "current");
		model.addAttribute("jobActive", "current");
		//热词
		//model.addAttribute("hotWords",jobBaseService.findHotWords());
		//相关招聘
		//model.addAttribute("relatedRecruitments", advertAreaPositionService.findAdvertResourceRecordByCityIdAndPositionId(branch!=null?branch.getId():null, Constants.POSITION_CODE_RELATED_RECRUITMENT_Code));
		//最新岗位
		model.addAttribute("newJobs", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_NEW_BOTTOM,branch.getId(),1,6,"updatetime","bottom"));
		return "job/companyDetailJobList";
	}

	/**
	 * 公司相册筛选
	 */
	@RequestMapping("/companyDetailAlbum")
	public String companyDetailViewAlbum(@RequestParam("id")Integer id,
			@RequestParam(value = "type",defaultValue = "0",required=false) int type,
			Model model,
			HttpServletRequest request){
		Company company = companyService.getCompany(id);
		if(company!=null){
			Branch branch =  branchService.getBranchByCityId(company.getCity().getId(),request,IPUtil.getIpAddr(request));
			if(branch !=null && request.getServerName().indexOf(Constants.DOMAIN_NAME) > -1){
				String webPrefix=request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "");
				if(!webPrefix.equals(branch.getWebPrefix())){
					String url = branch.getWebPrefix()+"."+Constants.DOMAIN_NAME+"/company/album_"+id+"/";
					return "redirect:/redirect?url=http://"+url;
				}
			}
			model.addAttribute("company", companyService.getCompany(id));
			model.addAttribute("companyImage", companyService.findImageByTypeAndCompany(type,id));
			model.addAttribute("companyImageCount", companyService.findImageByTypeAndCompany(-2,id));
			model.addAttribute("averageSalary", companyService.getAverageSalary(id));
//			model.addAttribute("album", "current");
//			model.addAttribute("jobActive", "current");
			model.addAttribute("type", type);
			
			//相似企业
//			model.addAttribute("similarCompany", companyService.findSimilarCompany(3, company.getCity().getId(), company.getIndustryid(),id));
			//底部广告位
			//model.addAttribute("hotWords",jobBaseService.findHotWords());
			//最新岗位
			model.addAttribute("newJobs", jobBaseService.findJobCache(Constants.CACHE_KEY_JOB_NEW_BOTTOM,branch.getId(),1,6,"updatetime","bottom"));
			return "job/companyDetailViewAlbum";
		}else{
			return "redirect:/404";
		}
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("advisories/{id}")
	public Page<UserCommunityTalkVo> viewAdvisory(@PathVariable("id")Integer id,@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = "10") int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType,ServletRequest request){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page<UserCommunityTalkVo> page = advisoryService.findUserCommunityTalkVo(null,id, searchParams, pageNumber, pageSize, sortType);
		return page;
	}
	
	/**
	 * 查询所有与该公司相关的问题
	 * @param cookieValue
	 * @param jobId
	 * @param comment
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="postAdvisory",method=RequestMethod.POST)
	public String postAdvisory(@CookieValue(value=Constants.COOKIE_KEY_USER_VERIFY,required=false)String cookieValue, @RequestParam("companyId")Integer companyId,@RequestParam("comment")String comment,Model model,HttpServletRequest request){
		User user = (User) accountService.validateLogin(cookieValue, request.getSession());
		if(user == null){
			model.addAttribute("msg", "操作失败，您尚未登录");
		}else{
			Branch branch = branchService.getBranchByRequest(request, IPUtil.getIpAddr(request));
			List<Integer> city  = branchService.findBranchCityIdList(Integer.parseInt(branch.getId()+""));
			advisoryService.saveCompanyAdvisory(user.getId(),user.getUsername(),companyId,comment,city.get(0));
			model.addAttribute("msg", "操作已成功，审核通过后您的提问将被显示");
		}
		return "redirect:/company/detail/"+companyId+".html";
	}
	/**
	 * 点赞
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addPointOfPraise",method = RequestMethod.POST)
	public String addPointOfPraise(@RequestParam("id") Integer id,HttpServletRequest request){
		if(id!=null){
			Company company = companyService.getCompany(id);
			if(company==null){
				return Constants.RETURN_STATUS_ERROR;
			}else{
				String sessionId = request.getSession().getId();
				if(StoreCacheUtil.getCacheValue("pointOfPraiseCache",sessionId+company.getId())!=null){
					return Constants.RETURN_STATUS_FAILURE;
				}else{
					StoreCacheUtil.putCache("pointOfPraiseCache",sessionId+company.getId(), id);
					company.setPointOfPraise(company.getPointOfPraise()==null?0:company.getPointOfPraise()+1);
					companyService.save(company);
					return company.getPointOfPraise().toString();
				}
			}
		}
		return Constants.RETURN_STATUS_ERROR;
	}
}
