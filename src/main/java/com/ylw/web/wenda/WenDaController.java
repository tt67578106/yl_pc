package com.ylw.web.wenda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ylw.entity.community.UserCommunityTalk;
import com.ylw.service.utm.UtmLogService;
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

import com.ylw.entity.base.Branch;
import com.ylw.entity.base.User;
import com.ylw.entity.job.JobBase;
import com.ylw.entity.vo.UserCommunityTalkVo;
import com.ylw.service.advert.AdvertAreaPositionService;
import com.ylw.service.base.AccountService;
import com.ylw.service.base.AdvisoryService;
import com.ylw.service.base.ArticleService;
import com.ylw.service.base.BranchService;
import com.ylw.service.base.CompanyService;
import com.ylw.service.job.JobBaseService;
import com.ylw.util.Constants;
import com.ylw.util.IPUtil;


@Controller
public class WenDaController {
	
	@Autowired
	private AdvisoryService advisoryService;
	@Autowired
	private AdvertAreaPositionService advertAreaPositionService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private JobBaseService jobBaseService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private UtmLogService utmLogService;

	
	
	/**
	 * 提问
	 * @param companyId
	 * @param jobId
	 * @return
	 */
	@RequestMapping(value="wenda/ask",method=RequestMethod.GET)
	public String advisory(@RequestParam(value="companyId",required=false)Integer companyId,
			@RequestParam(value="jobId",required=false)Integer jobId,
			@RequestParam(value="backUrl",required=false)String backUrl,
			Model model){
		model.addAttribute("jobId", jobId);
		if(jobId!=null&&companyId==null){
			JobBase job = jobBaseService.getJobBase(jobId);
			if(job!=null){
				companyId = job.getCompany().getId();
			}
		}
		model.addAttribute("companyId", companyId);
		model.addAttribute("backUrl", backUrl);
		return "wenda/ask";
	}

	/**
	 * 提交问题
	 * @param request
	 * @param cookieValue
	 * @param companyId
	 * @param jobId
	 * @param title
	 * @param comment
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "wenda/ask", method = RequestMethod.POST)
	public String postAdvisory(HttpServletRequest request,@CookieValue(value = Constants.COOKIE_KEY_USER_VERIFY, required = false) String cookieValue, 
			@RequestParam("companyId") Integer companyId, 
			@RequestParam(value="jobId",required = false) Integer jobId, 
			@RequestParam("title") String title,
			@RequestParam("comment") String comment) {
		User user = (User) accountService.validateLogin(cookieValue, request.getSession());
		//获取当前页面URL
		String utmUrl = request.getHeader("referer");
		if (user == null) {
			return Constants.RETURN_STATUS_ERROR;
		} else {
			Branch branch = branchService.getBranchByRequest(request, IPUtil.getIpAddr(request));
			List<Integer> city  = branchService.findBranchCityIdList(Integer.parseInt(branch.getId()+""));
			advisoryService.saveJobAdvisory(user.getId(), user.getUsername(), companyId, jobId,title,comment,city!=null&&city.size()>0?city.get(0):null);
			Integer id = advisoryService.getUserCommunityTalkBy(user.getId()).get(0).getId();
//			存储用户访问信息（问题发表页）
			utmLogService.saveUtmLog(null,null,cookieValue,"求职者", Long.valueOf(user.getId()),utmUrl,
					"PC", 4, companyId.toString(),null,"企业问题/职位问题","companyissue",
					"主动", "ylife_recruit", "00d55953f18911e594980800277a9591",String.valueOf(id),null);
			return Constants.RETURN_STATUS_SUCCESS;
		}

	}
	/**
	 * 根据企业id或岗位id跳转到问题列表
	 * @param companyId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="wenda",method=RequestMethod.GET)
	public String advisoryList(@RequestParam(value="companyId",required=false)Integer companyId,
			@RequestParam(value="jobId",required=false)Integer jobId,
			//@RequestParam(value = "sortType", defaultValue = "auto") String sortType, 
			@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="15")Integer pageSize,
			HttpServletRequest request,Model model){
		
		if(request.getServerName().indexOf(Constants.DOMAIN_NAME) > -1&&!request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "").equals("www")){
			String url = "http://www."+Constants.DOMAIN_NAME+"/wenda";
			if(companyId!=null&&companyId>0){
				if(url.contains("?")){
					url = url+"&companyId="+companyId;
				}else{
					url = url+"?companyId="+companyId;
				}
			}
			if(jobId!=null&&jobId>0){
				if(url.contains("?")){
					url = url+"&jobId="+jobId;
				}else{
					url = url+"?jobId="+jobId;
				}
			}
			return "redirect:/redirect?url="+url;
		}
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page<UserCommunityTalkVo> page = advisoryService.findUserCommunityTalkVo(jobId,companyId, searchParams, pageNumber, pageSize, "auto");
		model.addAttribute("advisorys",page);
		model.addAttribute("totalCount", page.getTotalElements());
		//热门搜索问答
		model.addAttribute("hotAdvisorys", advertAreaPositionService.findAdvertResourceRecordByCityIdAndPositionId(null, Constants.POSITION_CODE_HOT_ADVISORY_Code));
		//求职帮助 
		model.addAttribute("helps", articleService.findRecommendArticleCacheByArticleType(Constants.CACHE_KEY_HOME_ARTICLE_HELPS, 8, 3));
		if(companyId!=null&&companyId>0){
			model.addAttribute("company", companyService.getCompany(companyId));
		}
		if(jobId!=null&&jobId>0){
			model.addAttribute("jobBase", jobBaseService.getJobBase(jobId));
		}
		model.addAttribute("advisoryActive", "current");
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		//相关招聘
		model.addAttribute("relatedRecruitments", advertAreaPositionService.findAdvertResourceRecordByCityIdAndPositionId(null, Constants.POSITION_CODE_RELATED_RECRUITMENT_Code));
		//model.addAttribute("sortType", sortType);
		companyId = companyId!=null&&companyId>0?companyId:0;
		jobId = jobId!=null&&jobId>0?jobId:0;
		Object key = searchParams.get("LIKE_title")!=null&&!searchParams.get("LIKE_title").equals("0")?searchParams.get("LIKE_title"):0;
		model.addAttribute("pageUrl", "wenda/so_"+key+"_"+companyId+"_"+jobId);
		return "wenda/list";
	}
	
	/**
	 * 问答查询
	 * @param companyId
	 * @param jobId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="wenda/ajaxList",method=RequestMethod.POST)
	public Page<UserCommunityTalkVo> ajaxList(@RequestParam(value="companyId",required=false)Integer companyId,
			@RequestParam(value="jobId",required=false)Integer jobId,
			@RequestParam(value="pageNumber",defaultValue="1") Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="5")Integer pageSize,
			HttpServletRequest request){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		return advisoryService.findUserCommunityTalkVo(jobId,companyId, searchParams, pageNumber, pageSize, "auto");
	}
	
	/**
	 * 问答详情
	 * @param id
	 * @param key
	 * @param model
	 * @return
	 */
	@RequestMapping("wenda/{id}.html")
	public String advisoryDetail(@PathVariable("id")Integer id
			,@RequestParam(value="key",required=false)String key
			,Model model){
		UserCommunityTalkVo userCommunityTalkVo = advisoryService.getById(id,key);
		if(userCommunityTalkVo==null){
			return "redirect:/404";
		}
		model.addAttribute("advisory", userCommunityTalkVo);
		model.addAttribute("key", key);
		if(userCommunityTalkVo!=null){
			model.addAttribute("jobBase", jobBaseService.getJobBase(userCommunityTalkVo.getJobId()));
			model.addAttribute("company", companyService.getCompany(userCommunityTalkVo.getCompanyId()));
			model.addAttribute("averageSalary", companyService.getAverageSalary(userCommunityTalkVo.getCompanyId()));
			Page<UserCommunityTalkVo> page = advisoryService.findUserCommunityTalkVo(userCommunityTalkVo.getJobId(),userCommunityTalkVo.getCompanyId(), new HashMap<String, Object>(), 1, 8, "auto");
			model.addAttribute("similarAdvisorys", page.getContent());
		}
		//热门搜索问答
		model.addAttribute("hotAdvisorys", advertAreaPositionService.findAdvertResourceRecordByCityIdAndPositionId(null, Constants.POSITION_CODE_HOT_ADVISORY_Code));
		//招聘推荐
		model.addAttribute("relatedRecruitments", advertAreaPositionService.findAdvertResourceRecordByCityIdAndPositionId(null, Constants.POSITION_CODE_RELATED_RECRUITMENT_Code));
		return "wenda/detail";
	}
}
