package com.ylw.web.job;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ylw.entity.base.Resume;
import com.ylw.entity.base.User;
import com.ylw.entity.job.JobApply;
import com.ylw.service.base.AccountService;
import com.ylw.service.base.ResumeService;
import com.ylw.service.base.UserService;
import com.ylw.service.job.JobApplyService;
import com.ylw.util.Constants;

@Controller
@RequestMapping("jobApply")
public class JobApplyController {
	private static final Integer PAGE_SIZE = 10;
	@Autowired
	private JobApplyService jobApplyService;
	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private ResumeService resumeService;
	/**
	 * ajax 根据用户id和type得到报名记录
	 * @param pageNumber
	 * @param type
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public Page<JobApply> recordAjax(@RequestParam int pageNumber,
			@RequestParam(value="type",required=false)Integer type,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(
				Constants.SESSION_KEY_USER);
		if (user == null) {// 没登陆
			return null;
		} else {// 登陆后
			return jobApplyService.findJobApplyPage(user.getId(),type, pageNumber, PAGE_SIZE);
		}
	}
	/**
	 * 根据报名id得到跳转到流程详情页
	 * @param applyId
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("flow/{applyId}.html")
	public Object myApplyFlowLog(@PathVariable("applyId") Integer applyId,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		User user = (User) request.getSession().getAttribute(
				Constants.SESSION_KEY_USER);
		if (user == null) {// 没登陆
			String backUrl=request.getRequestURI();
			redirectAttributes.addFlashAttribute("url", backUrl);
			return "redirect:/login";
		} else {// 登陆后
			JobApply jobApply=jobApplyService.getJobApply(applyId);
			//List<JobApplyFlowLog> flowLogList=jobApplyService.findApplyFlowLogById(applyId);
			model.addAttribute("number", jobApply.getNumberCreateTimeString()+applyId);
			Resume resume = resumeService.getByUserId(user);
			model.addAttribute("realname", resume.getName());
			model.addAttribute("apply",jobApply);
			//model.addAttribute("flowLogList",flowLogList);
		}
		return "account/myApplyFlow";
	}
	
	/**
	 * 根据报名id得到流程节点
	 * @param applyId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "flownode",method = RequestMethod.POST)
	public Object myApplyFlowNode(@RequestParam("applyId") Integer applyId) {
		return jobApplyService.findApplyFlowLogById(applyId);
	}
}
