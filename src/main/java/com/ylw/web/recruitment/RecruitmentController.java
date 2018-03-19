package com.ylw.web.recruitment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ylw.entity.base.Branch;
import com.ylw.entity.base.User;
import com.ylw.entity.job.JobBase;
import com.ylw.service.base.AccountService;
import com.ylw.service.base.BranchService;
import com.ylw.service.base.ResumeService;
import com.ylw.service.job.JobBaseService;
import com.ylw.service.job.JobTypeService;
import com.ylw.service.recruitment.RecruitmentBranchService;
import com.ylw.service.recruitment.RecruitmentJobService;
import com.ylw.service.recruitment.RecruitmentService;
import com.ylw.util.Constants;
import com.ylw.util.IPUtil;
import com.ylw.util.StoreCacheUtil;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/recruitment")
public class RecruitmentController {
	
	@Autowired
	private RecruitmentService recruitmentService;
	@Autowired
	private RecruitmentJobService recruitmentJobService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private RecruitmentBranchService recruitmentBranchService;
	@Autowired
	private JobBaseService jobBaseService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private ResumeService resumeService;
	@Autowired
	private JobTypeService jobTypeService;
	
	private Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
	/**
	 * 招聘会推广首页
	 * @param recruitCode
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam("recruitCode")String  recruitCode ,Model model,HttpServletRequest request,HttpServletResponse response) {
		Branch branch = branchService.getByWebPrefix(request);
		model.addAttribute("branch", branch);
		model.addAttribute("recruitCode", recruitCode);
		model.addAttribute("branchList", branchService.findAllBranch());
		model.addAttribute("recruitment", recruitmentService.getByRecruitCodeAndIsBaidupro(recruitCode,0));
		model.addAttribute("jobList", recruitmentJobService.findJobsByRecruitCodeAndBranchAndIsBaidupro(recruitCode,branch,0,1,20,response));
		model.addAttribute("recruitmentBranchList", recruitmentBranchService.findByRecruitCodeAndIsBaidupro(recruitCode,0));
		// findJobCache 底部热门岗位
		model.addAttribute("hotJobs", jobBaseService.getJobByBranchIdCache(10,branch!=null?branch.getId():0));
		// findJobCache 底部今日新增
		model.addAttribute("newJobs", jobBaseService.getJobByBranchIdCache(10,branch!=null?branch.getId():0));
		//热词
		model.addAttribute("hotWords",jobBaseService.findHotWords());
		return "recruitment/index";
	}

	/**
	 * 推广单个岗位
	 * @param recruitCode
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="job/{id}.html",method = RequestMethod.GET)
	public String detail(@RequestParam(value="type",required=false)String type,
			@PathVariable("id")Integer  jobId ,
			@RequestParam(value="recSource",required=false)String recSource,
			@CookieValue(value = Constants.COOKIE_KEY_USER_VERIFY, required = false) String cookieValue,
			Model model,
			HttpServletRequest request,
			HttpServletResponse response) {
		User user = (User) accountService.validateLogin(cookieValue, request.getSession());
		if (user != null) {
			model.addAttribute("resumeBase", resumeService.getByUserId(user));
		}
		JobBase jobBase = recruitmentService.getByJobId(jobId,response,recSource);
		if (jobBase == null) {
			return "redirect:/404";
		}
		Branch branch = branchService.getBranchByCityId(jobBase.getCity().getId(),request,IPUtil.getIpAddr(request));
		if(branch !=null && request.getServerName().contains(Constants.DOMAIN_NAME)){
			String webPrefix=request.getServerName().substring(0, request.getServerName().indexOf(Constants.DOMAIN_NAME)).replace(".", "");
			if(!webPrefix.equals(branch.getWebPrefix())){
				String url = branch.getWebPrefix()+"."+Constants.DOMAIN_NAME+"/recruitment_"+jobId+".html";
				return "redirect:/redirect?url=http://"+url;
			}
		}
		model.addAttribute("imageList", jobBaseService.getCompanySence(jobBase));
		model.addAttribute("jobBase", jobBase);
		List<JobBase> likeJobs = recruitmentService.findLikeJobs(jobBase);
		model.addAttribute("educationList", StoreCacheUtil.getCacheValue("staticCache", Constants.CACHE_KEY_EDUCATION));
		model.addAttribute("jobTypes", jobTypeService.findJobTypeByParentId(0));
		model.addAttribute("likeJobs", likeJobs);
		model.addAttribute("jobDetail", jobBase.getJobDetail());
		model.addAttribute("type", type);
		return "recruitment/jobDetail";
	}
	
	@ModelAttribute 
	public void getProvinceList(Model model){
		model.addAttribute("provinceList", cache.get(Constants.CACHE_KEY_PROVINCE).getObjectValue());
	}
	
	@ModelAttribute
	public void getNationsList(Model model){
		model.addAttribute("nationsList", cache.get(Constants.CACHE_KEY_DIC_NATION).getObjectValue());
	}
}

