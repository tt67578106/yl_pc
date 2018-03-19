package com.ylw.web.enterprise;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ylw.entity.base.Company;
import com.ylw.entity.base.Image;
import com.ylw.entity.code.CodeAreaCity;
import com.ylw.entity.code.CodeAreaDistrict;
import com.ylw.entity.enterprise.Certification;
import com.ylw.entity.job.JobBase;
import com.ylw.entity.job.JobType;
import com.ylw.entity.sys.SysUser;
import com.ylw.service.base.CompanyService;
import com.ylw.service.base.ImageService;
import com.ylw.service.code.CodeAreaCityService;
import com.ylw.service.code.CodeAreaDistrictService;
import com.ylw.service.code.CodeAreaProvinceService;
import com.ylw.service.enterprise.CertificationService;
import com.ylw.service.enterprise.CompanyResumeBoxService;
import com.ylw.service.job.JobBaseService;
import com.ylw.service.job.JobTypeService;
import com.ylw.service.sys.SysUserService;
import com.ylw.util.Constants;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.HTMLInputFilter;
import com.ylw.util.StoreCacheUtil;

@Controller
@RequestMapping("enterpriseJob")
public class EnterpriseJobController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private JobBaseService jobBaseService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyResumeBoxService companyResumeBoxService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private CodeAreaDistrictService areaDistrictService;
	@Autowired
	private CodeAreaCityService areaCityService;
	@Autowired
	private CertificationService certificationService;
	@Autowired
	private CodeAreaProvinceService areaProvinceService; 
	@Autowired
	private JobTypeService jobTypeService;
	private static final String PAGE_SIZE = "15";
	
	private Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");

	/**
	 * 在招职位
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String jobList(@RequestParam(value = "page", defaultValue = "1") int pageNumber, @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,HttpServletRequest request,Model model){
		SysUser user = (SysUser) sysUserService.validateSysUserLogin(request.getSession());
		if(user == null){
			model.addAttribute("role", "enterprise");
			return "redirect:/login";
		}else{
			Company company=companyService.getByCreateby(user.getId());
			Long jobCount=0L;
			Long resumeCount=0L;
			Long todayPostJobCount=0L;
			if (company != null) {
				jobCount = jobBaseService.findJobCountByCompanyIdBySql(company.getId(),null);
				todayPostJobCount=jobBaseService.findJobCountByCompanyIdBySql(company.getId(),DateConvertUtils.getNow());
				resumeCount=companyResumeBoxService.findResumeCountByCompanyIdBySql(company.getId());
				model.addAttribute("certification", certificationService.getByCompanyId(company.getId()));
			}else{//企业信息为空跳转到完善企业资料信息页面
				return "redirect:/enterprise";
			}
			Page<JobBase> page = jobBaseService.findByEnterpriseIdBySql(company.getId(),pageNumber,pageSize);
			model.addAttribute("page", page);
			model.addAttribute("todayPostJobCount",todayPostJobCount);
			model.addAttribute("resumeCount",resumeCount);
			model.addAttribute("jobCount",jobCount);
			model.addAttribute("lastLoginTime", user.getLastLoginTimeString());
			model.addAttribute("enterprise",company );
			model.addAttribute("jobListActive", "current");
			return "enterprise/jobList";
		}
	}
	
	/**
	 * 岗位缩略图上传
	 * @param file
	 * @param resumeid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/upload/thumbnialImage",method=RequestMethod.POST)
	public Image jobImgUpload(@RequestParam("file")MultipartFile file,@RequestParam Integer jobid){
		Image img = imageService.uploadImage(file,"岗位缩略图",Constants.ALIYUN_JOB_THUMBNIALIMAGE_SAVE_PATH,0,0);
		if(jobid!=null&&jobid>0)
		{
			jobBaseService.uploadLogo(img, jobid);
		}
		return img;
	}
	
	
	/**
	 * 发布职位
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="jobPost",method=RequestMethod.GET)
	public String jobPost(@RequestParam(value="type",required=false)String type,
			@RequestParam(value="jobid",required=false)Integer jobid,HttpServletRequest request,Model model,RedirectAttributes redirectAttributes){
		SysUser user = (SysUser) sysUserService.validateSysUserLogin(request.getSession());
		if(user == null){
			model.addAttribute("role", "enterprise");
			return "redirect:/login";
		}else{
			Company company=companyService.getByCreateby(user.getId());
			Long jobCount=0L;
			Long resumeCount=0L;
			Long todayPostJobCount=0L;
			if (company != null) {
				jobCount = jobBaseService.findJobCountByCompanyIdBySql(company.getId(),null);
				todayPostJobCount=jobBaseService.findJobCountByCompanyIdBySql(company.getId(),DateConvertUtils.getNow());
				if((type==null||!type.equals("edit"))&&todayPostJobCount>=10)
				{
					redirectAttributes.addFlashAttribute("error", "您今天发布的职位一到上限，请明天再来!");				
					return "redirect:/enterpriseJob";
				}
				resumeCount=companyResumeBoxService.findResumeCountByCompanyIdBySql(company.getId());
				Certification certification=certificationService.getByCompanyId(company.getId());
				if (certification != null && certification.getStatus() == 1) {//判断是否认证通过
					model.addAttribute("certification", certification);
				}else{//如果为通过，跳到申请认证页面
					redirectAttributes.addFlashAttribute("error", "请认证您的企业信息，只有认证通过后才可以发布职位");				
					return "redirect:/enterprise/certify";
				}
			}else{//企业信息为空跳转到完善企业资料信息页面
				return "redirect:/enterprise";
			}
			if (jobid != null && jobid > 0) {
				model.addAttribute("job", jobBaseService.getJobBase(jobid));
			}
			List<JobType> jobTypes = jobTypeService.findJobTypeByParentId(0);
			model.addAttribute("typeList", jobTypes);//岗位类型
			model.addAttribute("todayPostJobCount",todayPostJobCount);
			model.addAttribute("resumeCount",resumeCount);
			model.addAttribute("jobCount",jobCount);
			model.addAttribute("lastLoginTime", user.getLastLoginTimeString());
			model.addAttribute("enterprise",company );
			model.addAttribute("jobPostActive", "current");
			Integer value = new Random().nextInt();
			HTMLInputFilter.setToken("jobPost",value);
			model.addAttribute("jobPostToken",value);
			return "enterprise/jobPost";
		}
	}
	
	@RequestMapping(value="jobPost",method=RequestMethod.POST)
	public String postJob(String jobPostToken,HttpServletRequest request,Model model,JobBase job,RedirectAttributes redirectAttributes){
		SysUser user = (SysUser) sysUserService.validateSysUserLogin(request.getSession());
		if(user == null){
			model.addAttribute("role", "enterprise");
			return "redirect:/login";
		}else{
			//防止跨站提交
			if(!HTMLInputFilter.isLegal("jobPost", jobPostToken)){
				return "redirect:/enterpriseJob";
			}
			Company company=companyService.getByCreateby(user.getId());
			if (company != null) {
				jobBaseService.saveEnterpriseJob(user.getId(),company,job);
				return "redirect:/enterpriseJob";
			}else{//企业信息为空跳转到完善企业资料信息页面
				redirectAttributes.addFlashAttribute("error", "请先完善企业信息!");
				return "redirect:/enterprise";
			}
		}
	}
	/**
	 * 改变在招停招状态
	 * @param jobid
	 * @param isRecruitment
	 * @return
	 */
	@RequestMapping(value="isRecruitment",method=RequestMethod.GET)
	public String setIsRecruitment(Integer jobid,Integer isRecruitment,RedirectAttributes redirectAttributes)
	{
		 int result=jobBaseService.setIsRecruitment(jobid,isRecruitment);
		if (result != 1) {
			redirectAttributes.addFlashAttribute("error", "操作失败，请稍后重试！");
		}
		 return "redirect:/enterpriseJob";
	}
	
	/**
	 * 根据职位类别的父类id得到子类的职位类别
	 * @param provinceid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="jobTypes",method=RequestMethod.POST)
	public List<JobType> getChildJobType(@RequestParam int parentId){
		return jobTypeService.findJobTypeByParentId(parentId);
	}
	/**
	 * 根据省id得到市列表
	 * @param provinceid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="citys",method=RequestMethod.POST)
	public List<CodeAreaCity> gainCityAjax(@RequestParam int provinceid){
		return areaCityService.findByProvinceId(provinceid);
	}
	/**
	 * 根据市id得到区列表
	 * @param cityid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="districts",method=RequestMethod.POST)
	public List<CodeAreaDistrict> gainDistrictAjax(@RequestParam int cityid){
		return areaDistrictService.findBycityId(cityid);
	}
	/**
	 * 省list
	 * @param model
	 */
	@ModelAttribute
	public void getProvinceList(Model model){
		model.addAttribute("provinceList", cache.get(Constants.CACHE_KEY_PROVINCE).getObjectValue());
	}
	/**
	 * 市list
	 * @param model
	 */
	@ModelAttribute
	public void getCityList(Model model){
		model.addAttribute("cityList", cache.get(Constants.CACHE_KEY_CITY).getObjectValue());
	}
	/**
	 * 学历要求
	 * @param model
	 */
	@ModelAttribute
	public void getEducationList(Model model)
	{
		model.addAttribute("educationList", cache.get(Constants.CACHE_KEY_EDUCATION).getObjectValue());
	}
}
