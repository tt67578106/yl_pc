package com.ylw.web.wanlixing;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ylw.entity.base.User;
import com.ylw.entity.wanlixing.ActWanlixingProject;
import com.ylw.entity.wanlixing.ActWanlixingRegistration;
import com.ylw.service.base.AccountService;
import com.ylw.service.wanlixing.ActWanlixingProjectImageService;
import com.ylw.service.wanlixing.ActWanlixingProjectService;
import com.ylw.service.wanlixing.ActWanlixingRegistrationService;
import com.ylw.util.FtpUploadUtil;
import com.ylw.util.Constants;
import com.ylw.util.HTMLInputFilter;
import com.ylw.util.StoreCacheUtil;

/**
 * 报名，创建活动
 * @author ghost
 *
 */
@Controller
@RequestMapping("/trip10000/registration")
public class RegistrationContrller {

	@Autowired
	private AccountService accountService;
	@Autowired
	private ActWanlixingRegistrationService actWanlixingRegistrationService;
	@Autowired
	private ActWanlixingProjectService actWanlixingProjectService;
	@Autowired
	private ActWanlixingProjectImageService actWanlixingProjectImageService;
	
	/**
	 * 报名
	 * @return
	 */
	@RequestMapping(value ="signUp")
	public String Registration(@CookieValue(value=Constants.COOKIE_KEY_USER_VERIFY,required=false)String cookieValue,
			Model model,HttpServletRequest request,RedirectAttributes redirectAttributes){
		User user = (User) accountService.validateLogin(cookieValue, request.getSession());
		if(user == null || user.getId() == null){
			String backUrl=request.getRequestURI();
			redirectAttributes.addFlashAttribute("url", backUrl);
			return "redirect:/login";
		}else{
			if(actWanlixingRegistrationService.getByUserId(user.getId())!=null){
				return "redirect:/trip10000/registration/updateProject";
			}
			Integer value = new Random().nextInt();
			HTMLInputFilter.setToken("signUp",value);
			model.addAttribute("signUpToken",value);
			return "wanlixing/signUp";
		}
	}
	/**
	 * 提交报名，项目信息
	 * @return
	 */
	@RequestMapping(value ="submitSignUp",method=RequestMethod.POST)
	public String SubmitRegistration(@Valid ActWanlixingRegistration registration,@Valid ActWanlixingProject project,
			String leaderImage,String leaderTeam,String signUpToken,@CookieValue(value=Constants.COOKIE_KEY_USER_VERIFY,required=false)String cookieValue,
			HttpServletRequest request){
		User user = (User) accountService.validateLogin(cookieValue, request.getSession());
		if(user==null){
			return "redirect:/login";
		}
		if(registration.getUserId()==user.getId()){
			return "redirect:/trip10000/registration/updateProject";
		}
		//防止重复提交
		if(actWanlixingRegistrationService.getByUserId(user.getId())!=null){
			return "redirect:/trip10000/registration/updateProject";
		}
		//防止跨站提交
		if(!HTMLInputFilter.isLegal("signUp", signUpToken)){
			return "redirect:/trip10000/registration/signUp";
		}
		//提交数据，并做二次校验防止xss攻击
		actWanlixingRegistrationService.save(user.getId(),registration,project,leaderImage,leaderTeam);
		return "redirect:/trip10000/registration/updateProject";
	}
	
	/**
	 * 修改报名信息
	 * @return
	 */
	@RequestMapping(value ="updateProject",method=RequestMethod.POST)
	public String updateProject(@Valid @ModelAttribute("project") ActWanlixingProject project,String RstudentCardScan,String RidCardScanRprintScan,String RprintScan,
			String leaderImage,String leaderTeam,String signUpToken,String imageIds,@CookieValue(value=Constants.COOKIE_KEY_USER_VERIFY,required=false)String cookieValue,
			HttpServletRequest request){
		User user = (User) accountService.validateLogin(cookieValue, request.getSession());
		if(user==null){
			return "redirect:/login";
		}
		//提交数据，并做二次校验防止xss攻击
		actWanlixingProjectService.cleanXSSProject(project);
		//防止跨站提交
		if(!HTMLInputFilter.isLegal("updateProject", signUpToken)){
			return "redirect:/trip10000/registration/updateProject";
		}
		actWanlixingProjectService.updateProject(user.getId(),project,RstudentCardScan,RidCardScanRprintScan,RprintScan,leaderImage,leaderTeam);
		return "redirect:/trip10000/registration/updateProject";
	}
	
	/**
	 * 修改项目信息
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="updateProject",method=RequestMethod.GET)
	public String updateProject(Model model,@CookieValue(value=Constants.COOKIE_KEY_USER_VERIFY,required=false)String cookieValue,
			HttpServletRequest request){
		User user = (User) accountService.validateLogin(cookieValue, request.getSession());
		if(user==null){
			return "redirect:/login";
		}
		ActWanlixingRegistration registration = actWanlixingRegistrationService.getByUserId(user.getId());
		if(registration == null){
			return "redirect:/trip10000/registration/signUp";
		}
		ActWanlixingProject project = actWanlixingProjectService.getActWanlixingProjectByUserId(user.getId());
		//URL 显示给用户，缓存key赋值给隐藏表单
		model.addAttribute("registrationPrintScan", actWanlixingRegistrationService.buildRegistrationUrl(registration.getPrintScan()));
		model.addAttribute("registrationIdCardScan", actWanlixingRegistrationService.buildRegistrationUrl(registration.getIdCardScan()));
		model.addAttribute("registrationStudentCardScan", actWanlixingRegistrationService.buildRegistrationUrl(registration.getStudentCardScan()));
		model.addAttribute("project",project);
		if(project != null){
			model.addAttribute("registrationPPT", actWanlixingRegistrationService.buildRegistrationUrl(project.getPptFile()));
			model.addAttribute("images", actWanlixingProjectImageService.findImagesByProjectAndType(project.getId(),1,"sorting"));
			model.addAttribute("image", actWanlixingProjectImageService.getImageByProjectAndType(project.getId(),4));
		}else{
			model.addAttribute("registrationPPT", null);
			model.addAttribute("images", null);
			model.addAttribute("image", null);
		}
		Integer value = new Random().nextInt();
		HTMLInputFilter.setToken("updateProject",value);
		model.addAttribute("signUpToken",value);
		return "wanlixing/updateProject";
	}
	
	/**
	 * 投票
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addVotingCount",method = RequestMethod.POST)
	public String addPointOfPraise(@CookieValue(value=Constants.COOKIE_KEY_USER_VERIFY,required=false)String cookieValue,
			HttpServletRequest request,@RequestParam("projectId") Integer projectId){
		User user = (User) accountService.validateLogin(cookieValue, request.getSession());
		ActWanlixingProject project = actWanlixingProjectService.getActWanlixingProject(projectId);
		if(user==null){
			return Constants.RETURN_STATUS_ERROR;
		}else if(project==null){
			return Constants.RETURN_STATUS_NULL;
		}else{
			Integer count = 1;
			String countStr = null==StoreCacheUtil.getCacheValue("wanlixingVote",user.getId()+"")?"1":StoreCacheUtil.getCacheValue("wanlixingVote",user.getId()+"").toString();
			if(Integer.parseInt(countStr)<=0){
				return Constants.RETURN_STATUS_FAILURE;
			}else{
				StoreCacheUtil.putCache("wanlixingVote",user.getId()+"", Integer.parseInt(countStr)-count);
				project.setVotingCount(project.getVotingCount()==null?1:project.getVotingCount()+1);
				actWanlixingProjectService.save(project);
				return project.getVotingCount().toString();
			}
		}
	}
	/**
	 * 报名图片上传
	 * @param file
	 * @param resumeid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/upload/registrationImage",method=RequestMethod.POST)
	public String resumeheadupload(@RequestParam("file")MultipartFile file){
		//将文件名保存在ehcache，防止前端修改
		String imgpath = null;
		imgpath = FtpUploadUtil.upload(Constants.ALIYUN_WANLIXING_SAVE_PATH,file);
		String key = new Random().nextInt()+"";
		StoreCacheUtil.putCache("updateImagePath",key,imgpath);
		return key+"/t"+imgpath;
	}
	
	/**
	 * PPT上传
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/upload/registrationPPT",method=RequestMethod.POST)
	public String resumeheaduploadPPT(@RequestParam("file")MultipartFile file){
		//将文件名保存在ehcache，防止前端修改
		String imgpath = null;
		imgpath = FtpUploadUtil.upload(Constants.ALIYUN_WANLIXING_PPT_SAVE_PATH,file);
		String key = new Random().nextInt()+"";
		StoreCacheUtil.putCache("updateImagePath",key,imgpath);
		return key+"/t"+imgpath;
	}
	
	
	@ModelAttribute
	public void getEntity(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
		if (id != -1) {
			model.addAttribute("project", actWanlixingProjectService.getActWanlixingProject(id));
		}
	}
}
