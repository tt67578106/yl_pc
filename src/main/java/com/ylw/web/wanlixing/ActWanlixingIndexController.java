package com.ylw.web.wanlixing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import com.ylw.entity.wanlixing.ActWanlixingPeriod;
import com.ylw.entity.wanlixing.ActWanlixingProject;
import com.ylw.service.base.RecommendAdService;
import com.ylw.service.wanlixing.ActWanlixingGuestService;
import com.ylw.service.wanlixing.ActWanlixingPeriodService;
import com.ylw.service.wanlixing.ActWanlixingProjectImageService;
import com.ylw.service.wanlixing.ActWanlixingProjectService;
import com.ylw.util.Constants;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/trip10000")
public class ActWanlixingIndexController {
	
	@Autowired
	private RecommendAdService recommendAdService;
	@Autowired
	private ActWanlixingPeriodService actWanlixingPeriodService;
	@Autowired
	private ActWanlixingGuestService actWanlixingGuestService;
	@Autowired
	private ActWanlixingProjectService actWanlixingProjectService;
	@Autowired
	private ActWanlixingProjectImageService actWanlixingProjectImageService;
	
	private static final String PAGE_SIZE = "5";
	/**
	 * 万里行首页
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String index(Model model){
		model.addAttribute("itemsList", recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_WANLIXING_PK_TYPE,Constants.POSITION_CODE_WANLIXING_PK_CODE,45));//项目大pk，展示列
		model.addAttribute("articleList", recommendAdService.findRecommendAdCache(Constants.CACHE_KEY_WANLIXING_TYPE,Constants.POSITION_CODE_WANLIXING_CODE,5));
		model.addAttribute("provinceList", actWanlixingPeriodService.findPrivinceList());//首页有活动的省
		model.addAttribute("wlxIndexActive", "current");
		return "wanlixing/index";
	}
	
	/**
	 * 根据省得到对应的活动信息list
	 * @param provinceId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="findActivityByProvinceId",method=RequestMethod.POST)
	public List<ActWanlixingPeriod> findActivityByProvinceId(Integer provinceId){
		return actWanlixingPeriodService.getPeriodPage(provinceId,new HashMap<String, Object>(),1,100,"startDate").getContent();
	}
	
	/**
	 * CEO/项目列表
	 * @return
	 */
	@RequestMapping(value="projects",method=RequestMethod.GET)
	public String projects(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			 Model model,HttpServletRequest request){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		model.addAttribute("projectList", actWanlixingProjectService.getHqlPage(pageNumber, pageSize,searchParams));
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		model.addAttribute("projectsActive", "current");
		return "wanlixing/projects";
	}
	/**
	 * 详情页
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="detail",method=RequestMethod.GET)
	public String detail(Model model,HttpServletRequest request,Integer projectId){
		ActWanlixingProject project = actWanlixingProjectService.getActWanlixingProject(projectId);
		if(project==null){
			return "redirect:/trip10000";
		}
		project.setViewCountj(project.getViewCountj()==null?1:project.getViewCountj()+1);
		actWanlixingProjectService.save(project);
		model.addAttribute("project", project);
		model.addAttribute("images", actWanlixingProjectImageService.findImagesByProjectAndType(project.getId(),1,"time"));
		model.addAttribute("image", actWanlixingProjectImageService.getImageByProjectAndType(project.getId(),4));
		model.addAttribute("pptImage", actWanlixingProjectImageService.findImagesByProjectAndType(project.getId(),3,"sorting"));
		return "wanlixing/detail";
	}
	/**
	 * 讲座详情页
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="lectureDetail",method=RequestMethod.GET)
	public String detail(Model model,@RequestParam("periodId")Integer periodId){
		model.addAttribute("actWanlixingPeriod",actWanlixingPeriodService.getActWanlixingPeriod(periodId));
		model.addAttribute("guestList",actWanlixingGuestService.findGuestsByPeriodId(periodId));
		return "wanlixing/lectureDetail";
	}
	
	/**
	 * 评委列表
	 * @param model
	 * @return
	 */
	@RequestMapping("itemsList")
	public String itemsList(Model model){
		model.addAttribute("itemsListActive", "current");
		return "wanlixing/itemsList";
	}
	
	/**
	 * 导师
	 * @param model
	 * @return
	 */
	@RequestMapping("tutors")
	public String tutors(Model model){
		model.addAttribute("tutorsActive", "current");
		return "wanlixing/tutors";
	}
	
	/**
	 * 赛事说明
	 * @param model
	 * @return
	 */
	@RequestMapping("rules")
	public String gameInstructions(Model model){
		model.addAttribute("rulesActive", "current");
		return "wanlixing/rules";
	}
	
	/**
	 * 奖品设置
	 * @param model
	 * @return
	 */
	@RequestMapping("prizeSetting")
	public String prizeSetting(Model model){
		model.addAttribute("prizeSettingActive", "current");
		return "wanlixing/prizeSetting";
	}
	
}

