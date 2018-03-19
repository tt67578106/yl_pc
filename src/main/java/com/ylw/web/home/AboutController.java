package com.ylw.web.home;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springside.modules.web.Servlets;

import com.ylw.entity.job.JobBase;
import com.ylw.service.base.AdvisoryService;
import com.ylw.service.base.ArticleService;
import com.ylw.service.base.BranchService;
import com.ylw.service.base.CompanyService;
import com.ylw.service.base.DataDictionaryService;
import com.ylw.service.job.JobBaseService;
import com.ylw.service.job.JobTagService;
import com.ylw.service.job.JobTypeService;
import com.ylw.util.Constants;
import com.ylw.util.FreemarkUtil;
import com.ylw.util.IPUtil;
import com.ylw.util.StoreCacheUtil;
import com.ylw.util.TemplateUtil;

@Controller
public class AboutController {
	
	@Autowired
	private JobBaseService jobBaseService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private JobTagService jobTagService;
	@Autowired
	private AdvisoryService advisoryService;
	@Autowired
	private JobTypeService jobTypeService;
	@Autowired
	private DataDictionaryService  dataDictionaryService;
	
	private Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");

	
	@RequestMapping(value = "301/joblist")
	@ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
	public String jobList301(HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request,"");
		Object cityid = searchParams.get("search_EQ_city.id");
		Object jobType = searchParams.get("search_LIKE_jobType");
		Object searchName = searchParams.get("search_LIKE_searchName");
		Object subJobType = searchParams.get("search_LIKE_subJobType");
		Object totalsalary = searchParams.get("search_LIKE_totalsalary");
		Object page = searchParams.get("page");
		if(jobType==null||StringUtils.isBlank(jobType.toString())){
			jobType = "zhaopin";
		}else{
			jobType = URLEncoder.encode(jobType.toString(),"UTF-8");
		}
		String url = "/"+jobType+"/"+cityid+"_"+searchName+"_"+subJobType+"_"+totalsalary+"_"+page+"/";
		response.setHeader("Location", url);
		return null;
	}
	
	/**
	 * 关于我们
	 * 关于优蓝
	 * @return
	 */
	@RequestMapping(value = "about/index")
	public String about() {
		return "about/about";
	}
	
	
	/**
	 * sitemap 索引页
	 * @return
	 */
	@RequestMapping("sitemap/index.xml")
	public @ResponseBody String index(){
    	List<Integer> jobTypeIdList = jobTypeService.findJobTypeIdByParentIds(0);
		List<Integer> jobTageIdList = jobTagService.findJobTagIds();
		List<Integer> companyIndustryids = dataDictionaryService.findCompanyIndustryids();
		Page<Integer> clist =  companyService.findCompanyIdPage(1,1000);
		Page<Integer> jlist =  jobBaseService.findJobIdPage(1,1000);
		Page<Integer> alist =  articleService.findArticleIdPage(1,1000);
		Integer communityCompanyIdCount = advisoryService.findCommunityPage(1,500).getTotalPages();
		Integer jobCompanyIdCount = advisoryService.findJobBasePage(1,500).getTotalPages();
		Page<Integer> wendaList = advisoryService.findWenDaIdPage(1,1000);
		String ftlPath =AboutController.class.getClassLoader().getResource("").getPath() + "sitemap";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("domainName", Constants.DOMAIN_NAME);
		map.put("jobTypeIdList", jobTypeIdList);
		map.put("jobTageIdList", jobTageIdList);
		map.put("companyIndustryids", companyIndustryids);
    	map.put("companyPageCount", clist.getTotalPages());
    	map.put("jobPageCount", jlist.getTotalPages());
    	map.put("articlePageCount", alist.getTotalPages());
    	map.put("wendalistPageCount",Math.max(communityCompanyIdCount, jobCompanyIdCount));
    	map.put("wendaPageCount", wendaList.getTotalPages());
    	String templateName = "site_map_index.ftl";
    	String fileContent =FreemarkUtil.getStrByTemplate(map, ftlPath, templateName);
		return fileContent;
	}
	
	/**
	 * 创建首页分站
	 * @return
	 */
	@RequestMapping("sitemap/city.xml")
	public @ResponseBody String findBranchList(){
		List<String> branchList = branchService.findAllBranchWebPrefix();
		String ftlPath =AboutController.class.getClassLoader().getResource("").getPath() + "sitemap";
		Map<String,Object> map = new HashMap<String,Object>();
    	map.put("branchList", branchList);
    	map.put("domainName", Constants.DOMAIN_NAME);
    	String templateName = "site_map_branch.ftl";
    	String fileContent =FreemarkUtil.getStrByTemplate(map, ftlPath, templateName);
		return fileContent;
	}
	
	/**
	 * 创建首页分站
	 * @return
	 */
	@RequestMapping("sitemap/firmlist{industryId}.xml")
	public @ResponseBody String findCompanyList(@PathVariable Integer industryId){
		List<String> branchList = branchService.findAllBranchWebPrefix();
		String ftlPath =AboutController.class.getClassLoader().getResource("").getPath() + "sitemap";
		Map<String,Object> map = new HashMap<String,Object>();
    	map.put("branchList", branchList);
    	map.put("industryId",industryId);
    	map.put("domainName", Constants.DOMAIN_NAME);
    	String templateName = "site_map_companylist.ftl";
    	String fileContent =FreemarkUtil.getStrByTemplate(map, ftlPath, templateName);
		return fileContent;
	}
	
	/**
	 * 创建职位列表
	 * @return
	 */
	@RequestMapping("sitemap/joblistbytype{typeId}.xml")
	public @ResponseBody String findJobListByTypeId(@PathVariable Integer typeId){
		List<String> branchList = branchService.findAllBranchWebPrefix();
		String typeName = jobTypeService.getJobTypeNameById(typeId);
		String ftlPath =AboutController.class.getClassLoader().getResource("").getPath() + "sitemap";
		Map<String,Object> map = new HashMap<String,Object>();
    	map.put("branchList", branchList);
    	map.put("domainName", Constants.DOMAIN_NAME);
    	map.put("typeName", StringUtils.isNotBlank(typeName)?typeName:"0");
    	String templateName = "site_map_joblist_type.ftl";
    	String fileContent =FreemarkUtil.getStrByTemplate(map, ftlPath, templateName);
		return fileContent;
	}
	
	/**
	 * 创建职位列表
	 * @return
	 */
	@RequestMapping("sitemap/joblistbylabel{tagId}.xml")
	public @ResponseBody String findJobListByTagId(@PathVariable Integer tagId){
		List<String> branchList = branchService.findAllBranchWebPrefix();
		String labelName = jobTagService.getJobTageNameById(tagId);
		String ftlPath =AboutController.class.getClassLoader().getResource("").getPath() + "sitemap";
		Map<String,Object> map = new HashMap<String,Object>();
    	map.put("branchList", branchList);
    	map.put("domainName", Constants.DOMAIN_NAME);
    	map.put("tagName", StringUtils.isNotBlank(labelName)?labelName:"0");
    	String templateName = "site_map_joblist_label.ftl";
    	String fileContent =FreemarkUtil.getStrByTemplate(map, ftlPath, templateName);
		return fileContent;
	}
	
	/**
	 * 创建问题咨询列表
	 * @return
	 */
	@RequestMapping("sitemap/wendalist{pageNumber}.xml")
	public @ResponseBody String findWenDaList(@PathVariable Integer pageNumber){
		List<Integer> communityCompanyIdList = advisoryService.findCommunityPage(pageNumber,500).getContent();
		List<Integer> jobCompanyIdList = advisoryService.findJobBasePage(pageNumber,500).getContent();
		String ftlPath =AboutController.class.getClassLoader().getResource("").getPath() + "sitemap";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("communityCompanyIdList", communityCompanyIdList);
    	map.put("jobCompanyIdList", jobCompanyIdList);
    	map.put("domainName", Constants.DOMAIN_NAME);
    	String templateName = "site_map_wendalist.ftl";
    	String fileContent =FreemarkUtil.getStrByTemplate(map, ftlPath, templateName);
		return fileContent;
	}
	
	/**
	 * 创建问题咨询详情
	 * @return
	 */
	@RequestMapping("sitemap/wenda{pageNumber}.xml")
	public @ResponseBody String findWenDaDetail(@PathVariable Integer pageNumber){
		List<Integer> wendaIdList = advisoryService.findWenDaIdPage(pageNumber,1000).getContent();
		String ftlPath =AboutController.class.getClassLoader().getResource("").getPath() + "sitemap";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("wendaIdList", wendaIdList);
    	map.put("domainName", Constants.DOMAIN_NAME);
    	String templateName = "site_map_wenda.ftl";
    	String fileContent =FreemarkUtil.getStrByTemplate(map, ftlPath, templateName);
		return fileContent;
	}
	/**
	 * 创建企业siteMap
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping("sitemap/firm{pageNumber}.xml")
	public @ResponseBody String findCompnayDetail(@PathVariable Integer pageNumber){
		List<String> plist =  companyService.findCompanyIdAndWebPrefixPage(pageNumber,1000).getContent();
		String ftlPath = AboutController.class.getClassLoader().getResource("").getPath() + "sitemap";
    	String templateName = "site_map_company.ftl";
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("domainName", Constants.DOMAIN_NAME);
    	map.put("companyIdList", plist);
    	String fileContent = FreemarkUtil.getStrByTemplate(map, ftlPath, templateName);
		return fileContent;
	}
	
	/**
	 * 创建岗位的siteMap
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping("sitemap/job{pageNumber}.xml")
	public @ResponseBody String findJobDetail(@PathVariable Integer pageNumber){
		List<String> plist =  jobBaseService.findJobIdAndWebPrefixPage(pageNumber,1000).getContent();
		String ftlPath =AboutController.class.getClassLoader().getResource("").getPath() + "sitemap";
		Map<String,Object> map = new HashMap<String,Object>();
    	map.put("jobIdList", plist);
    	map.put("domainName", Constants.DOMAIN_NAME);
    	String templateName = "site_map_job.ftl";
    	String fileContent =FreemarkUtil.getStrByTemplate(map, ftlPath, templateName);
		return fileContent;
	}
	
	/**
	 * 创建文章的siteMap
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping("sitemap/news{pageNumber}.xml")
	public @ResponseBody String findArticleList(@PathVariable Integer pageNumber){
		List<Integer> plist =  articleService.findArticleIdPage(pageNumber,1000).getContent();
		String ftlPath = AboutController.class.getClassLoader().getResource("").getPath() + "sitemap";
    	String templateName = "site_map_article.ftl";
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("articleIdList", plist);
    	map.put("domainName", Constants.DOMAIN_NAME);
    	String fileContent = FreemarkUtil.getStrByTemplate(map, ftlPath, templateName);
		return fileContent;
	}
	/**
	 * 匹配PC与WAP页面的XML
	 * @return
	 */
	@RequestMapping("site_map_companyAndJobPattern.xml")
	public @ResponseBody String getCompanyAndJob(){
		String ftlPath = AboutController.class.getClassLoader().getResource("").getPath() + "sitemap";
    	String templateName = "site_map_companyAndJobPattern.ftl";
    	Map<String,Object> map = new HashMap<String,Object>();
    	return FreemarkUtil.getStrByTemplate(map, ftlPath, templateName);
	}
	/**
	 * 匹配PC与WAP页面的XML
	 * @return
	 */
	@RequestMapping("site_map_companyAndJobPattern.txt")
	public @ResponseBody String getCompanyAndJobTxt(){
		String ftlPath = AboutController.class.getClassLoader().getResource("").getPath() + "sitemap";
    	String templateName = "site_map_companyAndJobPatternTxt.ftl";
    	Map<String,Object> map = new HashMap<String,Object>();
    	return FreemarkUtil.getStrByTemplate(map, ftlPath, templateName);
	}
	/**
	 * 获取所有岗位  百度轻应用  api
	 * @return
	 */
	@RequestMapping("getJobList.xml")
	public @ResponseBody String getJobList(){
		List<JobBase> plist =  jobBaseService.getPage().getContent();
//		String userDir = System.getProperty("user.dir");//模板路径
//    	String ftlPath = userDir +"\\src\\main\\resources\\ftl\\job-data\\";
//    	File file = new File(ftlPath);
//    	boolean isExists = file.exists();//是否存在
//    	if(! isExists){
//    		System.out.println("ftlPath:"+ftlPath);
//    	}
		String ftlPath = AboutController.class.getClassLoader().getResource("").getPath() + "sitemap";
    	String templateName = "job-data.ftl";
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("jobList", plist);
    	return TemplateUtil.getStrByTemplate(map, ftlPath, templateName);
	}
	/**
	 * 获取素有岗位  百度招聘获取xml
	 * @return
	 */
	@RequestMapping("getBaiduJobList.xml")
	public @ResponseBody String getBaiduJobList(){
		List<JobBase> plist =  jobBaseService.getPage().getContent();
//		String userDir = System.getProperty("user.dir");//模板路径
//    	String ftlPath = userDir +"\\src\\main\\resources\\ftl\\job-data\\";
//    	File file = new File(ftlPath);
//    	boolean isExists = file.exists();//是否存在
//    	if(! isExists){
//    		System.out.println("ftlPath:"+ftlPath);
//    	}
		String ftlPath = AboutController.class.getClassLoader().getResource("").getPath() + "sitemap";
    	String templateName = "baidu-jobList.ftl";
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("jobList", plist);
    	return TemplateUtil.getStrByTemplate(map, ftlPath, templateName);
	}
	
	// 常见问题
	@RequestMapping(value = "about/common")
	public String common() {
		return "about/common";
	}

	// 联系我们
	@RequestMapping(value = "about/contact")
	public String contact() {
		return "about/contact";
	}

	// 网站声明
	@RequestMapping(value = "about/statement")
	public String statement() {
		return "about/statement";
	}

	// wap
	@RequestMapping("about/mobile")
	public String mobile(Model model) {
		model.addAttribute("aboutActive", "current");
		return "about/mobile";
	}

	// 用户协议
	@RequestMapping(value = "about/agreement")
	public String agreement() {
		return "about/agreement";
	}

	// 媒体报道
	@RequestMapping(value = "about/mediaReports")
	public String mediaReports() {
		return "about/mediaReports";
	}
	//分站选择
	@RequestMapping(value = "about/chooseCity")
	public String chooseCity(Model model) {
		model.addAttribute("domainName", Constants.DOMAIN_NAME);
		model.addAttribute("branchList", branchService.findAllBranch());
		return "about/city";
	}
	/**
	 * 关注我们
	 * 
	 * @return
	 */

	// 微信公众号
	@RequestMapping(value = "about/wechat")
	public String wechat() {
		return "about/wechat";
	}

	/**
	 * 求职指南、常见问题
	 * 
	 * @return
	 */
	// 新手指南
	@RequestMapping(value = "help/index")
	public String help() {
		return "help/help";
	}

	// 优蓝账户
	@RequestMapping(value = "help/ylaccount")
	public String ylaccount() {
		return "help/ylaccount";
	}
	
	// 求职流程
	@RequestMapping(value = "help/ylkz")
	public String ylkz() {
		return "help/ylkz";
	}

	// 求职流程
	@RequestMapping(value = "help/ylapplyJob")
	public String ylapplyJob() {
		return "help/ylapplyJob";
	}

	// 服务保障
	@RequestMapping(value = "help/consultant")
	public String consultant() {
		return "help/consultant";
	}

	// 联系客服、打工顾问
	@RequestMapping(value = "help/guarantee")
	public String guarantee() {
		return "help/guarantee";
	}

	/**
	 * 活动宣传静态页
	 * 
	 * @return
	 */
	// 悬赏聘
	@RequestMapping(value = "recommend/index")
	public String recommend() {
		return "recommend/recommend";
	}
	
	/**
	 * 蓝呗钱包
	 * @return
	 */
	@RequestMapping(value = "lanbei/index.html")
	public String lanbei(HttpServletRequest request) {
		branchService.getBranchByRequest(request, IPUtil.getIpAddr(request));
		return "lanbei/index";
	}

}
