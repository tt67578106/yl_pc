package com.ylw.web.article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.web.Servlets;

import com.google.common.collect.Maps;
import com.ylw.entity.base.Article;
import com.ylw.entity.base.ArticleKeywords;
import com.ylw.service.advert.AdvertAreaPositionService;
import com.ylw.service.base.ArticleService;
import com.ylw.service.base.BranchService;
import com.ylw.service.base.CompanyService;
import com.ylw.service.base.RecommendAdService;
import com.ylw.service.base.RecommendJobService;
import com.ylw.service.job.JobBaseService;
import com.ylw.util.Constants;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.IPUtil;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private JobBaseService jobBaseService;
	@Autowired 
	private RecommendAdService recommendAdService;
	@Autowired
	private RecommendJobService recommendJobService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private AdvertAreaPositionService advertAreaPositionService;

	private static final String PAGE_SIZE = "10";
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("title", "标题");
	}
	/**
	 * 优蓝家园首页
	 * 1、行业新闻（活动专题类文章）；2、小蓝有约；3、求职帮助；4、优蓝访谈（访谈类文章）；5、优蓝现场（招聘现场类文章）
	 * @return
	 */
	@RequestMapping("zone")
	public String Index(HttpServletRequest request,Model model) {
		Integer branchId = branchService.getBranchIdByRequest(request, IPUtil.getIpAddr(request));
		if(request.getServerName().indexOf(Constants.DOMAIN_NAME) > -1){
			if(!request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "").equals("www")){
				return "redirect:/redirect?url=http://www."+Constants.DOMAIN_NAME+"/zone";
			}
		}
		model.addAttribute("articleActive", "current");
		// 行业新闻 精选
		model.addAttribute("rdxws",articleService.findArticleByType(null,3, 1, 3, "publishTime"));
		//最新资讯（获取最新的16条文章）
		model.addAttribute("newActives", articleService.findArticleByType(null ,1, 1, 16, "publishTime"));
		// 求职帮助findArticleByType(文章类别,推荐状态,pageNumber,pageSize,排序字段)
		model.addAttribute("qzbzs", articleService.findRecommendArticleCacheByArticleType(Constants.CACHE_KEY_HOME_ARTICLE_HELPS, 8, 3));
		// 小蓝有约
		//model.addAttribute("xlyys",articleService.findArticleByType(2 ,1, 1, 3, "publishTime"));
		// 优蓝访谈
		//model.addAttribute("ylfts",articleService.findArticleByType(4 ,1, 1, 3, "publishTime"));
		// 优蓝现场
		//model.addAttribute("ylxcs",articleService.findArticleByType(5 ,1, 1, 3, "publishTime"));
		// 优蓝动态
		//model.addAttribute("yldts",articleService.findArticleByType(6 ,1, 1, 3, "publishTime"));
		//文章右侧推荐企业
		model.addAttribute("rightRecommendCompanys", companyService.getCompanyByBranchIdCache(3,branchId));
		model.addAttribute("today", DateConvertUtils.getNow());
		//优蓝家园主页顶部的广告位
//		model.addAttribute("topRecommendAd", recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_HOME_CENTER_JOBS,Constants.POSITION_CODE_HOME_CENTER_JOBS_Code,1,branchId));
		return "article/zone";
	}

	/**
	 * 文章列表
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="article",method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "publishTime") String sortType, Model model,
			HttpServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
//		Integer branchId = branchService.getBranchIdByRequest(request, IPUtil.getIpAddr(request));
		if(request.getServerName().indexOf(Constants.DOMAIN_NAME) > -1){
			if(!request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "").equals("www")){
				return "redirect:/redirect?url=http://www."+Constants.DOMAIN_NAME+"/s_articleType_"+searchParams.get("articleType");
			}
		}
		Page<Article> page = articleService.getPage(searchParams, pageNumber, pageSize, sortType);
//		model.addAttribute("applyCount", jobBaseService.countJobApply());
		model.addAttribute("page", page);
		model.addAttribute("totalCount", page.getTotalElements());
		model.addAttribute("pageNumber", pageNumber);
//		model.addAttribute("sortType", sortType);
//		model.addAttribute("sortTypes", sortTypes);
		if(page.getContent().size()>0){
			model.addAttribute("articleTypeCode", page.getContent().get(0).getArticleType());
		}else{
			model.addAttribute("articleTypeCode", null);
		}
		
		// 求职帮助findArticleByType(文章类别,推荐状态,pageNumber,pageSize,排序字段)
		model.addAttribute("qzbzs", articleService.findRecommendArticleCacheByArticleType(Constants.CACHE_KEY_HOME_ARTICLE_HELPS, 8, 3));
		//招聘推荐
		model.addAttribute("relatedRecruitments", advertAreaPositionService.findAdvertResourceRecordByCityIdAndPositionId(null, Constants.POSITION_CODE_RELATED_RECRUITMENT_Code));
		//TDK 规范
		if(page!=null&&page.getTotalPages()>0){
			Map<String, String> data = articleService.getArticleTDK(page.getContent().get(0).getArticleType());
			model.addAttribute("title", data.get("title"));
			model.addAttribute("keywords", data.get("keyword"));
			model.addAttribute("description", data.get("description"));
		}
		
		//文章右侧广告
		//model.addAttribute("rightRecommendAds", recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_ARTICLE_RIGHT_AD,Constants.POSITION_CODE_RECOMMEND_ARTICLE_RIGHT_Code,3,branchId));
		//文章右侧推荐企业
		//model.addAttribute("rightRecommendCompanys", companyService.getCompanyByBranchIdCache(3,branchId));
		//文章右侧推荐岗位
		//model.addAttribute("rightRecommendJobs", jobBaseService.getJobByBranchIdCache(3,branchId));
		model.addAttribute("articleActive", "current");
		//model.addAttribute("hotJobs", jobBaseService.findHotJobList(1, 5, null,branchId));
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "article/articleList";
	}
	
	/**
	 * 文章详情
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("article/detail/{id}.html")
	public String read(HttpServletRequest request ,@PathVariable("id")Integer id,Model model){
		//Integer branchId = branchService.getBranchIdByRequest(request, IPUtil.getIpAddr(request));
		if(request.getServerName().indexOf(Constants.DOMAIN_NAME) > -1){
			if(!request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "").equals("www")){
				return "redirect:/redirect?url=http://www."+Constants.DOMAIN_NAME+"/xl_"+id+".html";
			}
		}
		HashMap<String, Article> threeArticle = articleService.getArticle4view(id,0);
		
		// 求职帮助findArticleByType(文章类别,推荐状态,pageNumber,pageSize,排序字段)
		model.addAttribute("qzbzs", articleService.findRecommendArticleCacheByArticleType(Constants.CACHE_KEY_HOME_ARTICLE_HELPS, 8, 3));
		//招聘推荐
		model.addAttribute("relatedRecruitments", advertAreaPositionService.findAdvertResourceRecordByCityIdAndPositionId(null, Constants.POSITION_CODE_RELATED_RECRUITMENT_Code));
		//文章右侧广告
		//model.addAttribute("rightRecommendAds", recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_ARTICLE_RIGHT_AD,Constants.POSITION_CODE_RECOMMEND_ARTICLE_RIGHT_Code,3,branchId));
		//文章右侧推荐企业
		//model.addAttribute("rightRecommendCompanys", companyService.getCompanyByBranchIdCache(3,branchId));
		//文章右侧推荐岗位
		//model.addAttribute("rightRecommendJobs", jobBaseService.getJobByBranchIdCache(3,branchId));
		//文章底部相关文章推荐
		List<Article> articleList=articleService.findArticleByType(threeArticle.get("current").getArticleType() ,null,1, 6, "publishTime");
		List<Article> recommendArticleList=new ArrayList<Article>();
		for (Article article : articleList) {//相关推荐文章去掉自己
			if(recommendArticleList.size()<5&&article.getId()!=threeArticle.get("current").getId()){
				recommendArticleList.add(article);
			}
		}
		model.addAttribute("recommendArticleList",recommendArticleList);
		model.addAttribute("articleActive", "current");
		model.addAttribute("currentArticle", threeArticle.get("current"));
		model.addAttribute("preArticle", threeArticle.get("pre"));
		model.addAttribute("nextArticle", threeArticle.get("next"));
		return "article/articleDetail";
	}
	
	/**
	 * 关键词列表
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tag",method = RequestMethod.GET)
	public String keywordsList(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = "98") int pageSize,
			@RequestParam(value = "sortType", defaultValue = "sorting") String sortType, Model model,
			HttpServletRequest request) {
		if(request.getServerName().indexOf(Constants.DOMAIN_NAME) > -1){
			if(!request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "").equals("www")){
				return "redirect:/redirect?url=http://www."+Constants.DOMAIN_NAME+"/tag/";
			}
		}
		Page<ArticleKeywords> page = articleService.getKeyWordsPage(pageNumber, pageSize, sortType);
		model.addAttribute("page", page);
		model.addAttribute("pageUrl", "tag");
		model.addAttribute("pageNumber", pageNumber);
		return "article/keywordList";
	}
}
