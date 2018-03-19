package com.ylw.web.enterprise;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.ylw.entity.base.Company;
import com.ylw.entity.base.Image;
import com.ylw.entity.code.CodeAreaCity;
import com.ylw.entity.code.CodeAreaDistrict;
import com.ylw.entity.enterprise.Certification;
import com.ylw.entity.enterprise.CertificationWorkOrder;
import com.ylw.entity.enterprise.CompanyResumeBox;
import com.ylw.entity.sys.SysUser;
import com.ylw.service.base.CompanyService;
import com.ylw.service.base.ImageService;
import com.ylw.service.code.CodeAreaCityService;
import com.ylw.service.code.CodeAreaDistrictService;
import com.ylw.service.code.CodeAreaProvinceService;
import com.ylw.service.enterprise.CertificationService;
import com.ylw.service.enterprise.CertificationWorkOrderService;
import com.ylw.service.enterprise.CompanyResumeBoxService;
import com.ylw.service.job.JobBaseService;
import com.ylw.service.sys.SysUserService;
import com.ylw.util.Constants;
import com.ylw.util.StoreCacheUtil;
import com.ylw.util.HTMLInputFilter;

@Controller
@RequestMapping("enterprise")
public class EnterpriseController {
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
	private CertificationWorkOrderService certificationWorkOrderService;
	private static final String PAGE_SIZE = "15";
	
	private Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");

	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(){
			return "enterprise/index";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String loginForm(HttpServletRequest request,Model model){
		SysUser user = (SysUser) sysUserService.validateSysUserLogin(request.getSession());
		if(user == null){
			model.addAttribute("role", "enterprise");
			return "redirect:/login";
		}else{
			Company company=companyService.getByCreateby(user.getId());
			Long jobCount=0L;
			Long resumeCount=0L;
			if (company != null) {
				jobCount = jobBaseService.findJobCountByCompanyIdBySql(company.getId(),null);
				resumeCount=companyResumeBoxService.findResumeCountByCompanyIdBySql(company.getId());
				model.addAttribute("certification",certificationService.getByCompanyId(company.getId()));
			}else{
				model.addAttribute("error", "请完善您的企业基本信息");
			}
			model.addAttribute("resumeCount",resumeCount);
			model.addAttribute("jobCount",jobCount);
			model.addAttribute("lastLoginTime", user.getLastLoginTimeString());
			model.addAttribute("enterprise",company );
			model.addAttribute("enterpriseActive", "current");
			Integer value = new Random().nextInt();
			HTMLInputFilter.setToken("information",value);
			model.addAttribute("informationToken",value);
			return "enterprise/information";
		}
	}
	
	/**
	 * logo上传
	 * @param file
	 * @param resumeid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/upload/logo",method=RequestMethod.POST)
	public Image companyLogoUpload(@RequestParam("file")MultipartFile file,@RequestParam Integer companyid){
		Image img = imageService.uploadImage(file,"企业logo",Constants.ALIYUN_COMPANY_LOGO_SAVE_PATH,0,0);
		if(companyid!=null&&companyid>0)
		{
			companyService.uploadLogo(img, companyid);
		}
		return img;
	}
	
	/**
	 * 上传申请认证所需要的证件图片
	 * @param file
	 * @param resumeid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/upload/certifyImage",method=RequestMethod.POST)
	public String certifyImageUpload(@RequestParam("file")MultipartFile file,@RequestParam String key,HttpServletRequest request,Model model){
		String imagePath="";
		SysUser user = (SysUser) sysUserService.validateSysUserLogin(request.getSession());
		if(user == null){
			model.addAttribute("role", "enterprise");
			return "redirect:/login";
		}else{
			Company company=companyService.getByCreateby(user.getId());
			if (company != null) {
				imagePath=certificationService.uploadImage(key,user.getId(),company.getId(),file,Constants.ALIYUN_CERTIFICATION_IMAGE_SAVE_PATH);
			}else{
				return "redirect:/enterprise/information";
			}
		}
		return imagePath;
	}
	/**
	 * 保存企业资料
	 * @param request
	 * @param enterprise
	 * @param model
	 * @return
	 */
	@RequestMapping(value="information",method=RequestMethod.POST)
	public String saveEnterprise(HttpServletRequest request,String informationToken,Company enterprise,Model model)
	{
		SysUser user = (SysUser) sysUserService.validateSysUserLogin(request.getSession());
		if(user == null){
			model.addAttribute("role", "enterprise");
			return "redirect:/login";
		}else{
			//防止跨站提交
			if(!HTMLInputFilter.isLegal("information", informationToken)){
				return "redirect:/enterprise";
			}
			companyService.saveEnterprise(user.getId(),enterprise);
			return "redirect:/enterprise";
		}
	}
	/**
	 * 重新提交企业资料
	 * @param jobid
	 * @param isRecruitment
	 * @return
	 */
	@RequestMapping(value="reinformation",method=RequestMethod.POST)
	public String refInformation(Integer enterpriseId,RedirectAttributes redirectAttributes)
	{
		int result=companyService.refInformation(enterpriseId);
		if (result != 1) {
			redirectAttributes.addFlashAttribute("error", "重新申请认证失败，请稍后重试！");
		}
		 return "redirect:/enterprise";
	}
	
	/**
	 * 申请认证	
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="certify",method=RequestMethod.GET)
	public String certify(HttpServletRequest request,Model model){
		SysUser user = (SysUser) sysUserService.validateSysUserLogin(request.getSession());
		if(user == null){
			model.addAttribute("role", "enterprise");
			return "redirect:/login";
		}else{
			Company company=companyService.getByCreateby(user.getId());
			Long jobCount=0L;
			Long resumeCount=0L;
			if (company != null) {
				jobCount = jobBaseService.findJobCountByCompanyIdBySql(company.getId(),null);
				resumeCount=companyResumeBoxService.findResumeCountByCompanyIdBySql(company.getId());
				Certification certification=certificationService.getByCompanyId(company.getId());
				if(certification!=null)
				{
					CertificationWorkOrder certificationWorkOrder=certificationWorkOrderService.findByCertificationIdAndStatus(certification.getId(),2);
					model.addAttribute("certificationWorkOrder", certificationWorkOrder);
				}
				model.addAttribute("certification",certification );
			}else{
				return "redirect:/enterprise";
			}
			model.addAttribute("resumeCount",resumeCount);
			model.addAttribute("jobCount",jobCount);
			model.addAttribute("lastLoginTime", user.getLastLoginTimeString());
			model.addAttribute("enterprise",company );
			model.addAttribute("certifyActive", "current");
			Integer value = new Random().nextInt();
			HTMLInputFilter.setToken("certify",value);
			model.addAttribute("certifyToken",value);
			return "enterprise/certification";
		}
	}
	
	/**
	 * 提交申请认证资料
	 * @param request
	 * @param certification
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="certify",method=RequestMethod.POST)
	public String saveCertification(HttpServletRequest request,String certifyToken,Certification certification,Model model)
	{
		SysUser user = (SysUser) sysUserService.validateSysUserLogin(request.getSession());
		if(user == null){
			model.addAttribute("role", "enterprise");
			return "redirect:/login";
		}else{
			//防止跨站提交
			if(!HTMLInputFilter.isLegal("certify", certifyToken)){
				return "redirect:/enterprise/certify";
			}
			Company company = companyService.getByCreateby(user.getId());
			if (company != null) {
				certificationService.saveCertification(user,company.getId(), certification);
			}
			return Constants.RETURN_STATUS_SUCCESS;
		}
	}
	
	/**
	 * 重新认证
	 * @param certificationId
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="recertify",method=RequestMethod.POST)
	public String refCertify(Integer certificationId,RedirectAttributes redirectAttributes)
	{
		 int result=certificationService.refCertify(certificationId);
		if (result != 1) {
			redirectAttributes.addFlashAttribute("error", "重新申请认证失败，请稍后重试！");
		}
		 return "redirect:/enterprise/certify";
	}
	
	/**
	 * 收到简历	
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="resumes",method=RequestMethod.GET)
	public String resumes(@RequestParam(value = "page", defaultValue = "1") int pageNumber, @RequestParam(value = "page.size", defaultValue =PAGE_SIZE) int pageSize,HttpServletRequest request,Model model){
		SysUser user = (SysUser) sysUserService.validateSysUserLogin(request.getSession());
		if(user == null){
			model.addAttribute("role", "enterprise");
			return "redirect:/login";
		}else{
			Company company=companyService.getByCreateby(user.getId());
			Long jobCount=0L;
			Long resumeCount=0L;
			if (company != null) {
				jobCount = jobBaseService.findJobCountByCompanyIdBySql(company.getId(),null);
				resumeCount=companyResumeBoxService.findResumeCountByCompanyIdBySql(company.getId());
				model.addAttribute("certification", certificationService.getByCompanyId(company.getId()));
			}else{
				return "redirect:/enterprise";
			}
			Page<CompanyResumeBox> page = companyResumeBoxService.findResumesByCompanyIdBySql(company.getId(),pageNumber,pageSize);
			model.addAttribute("page", page);
			model.addAttribute("resumeCount",resumeCount);
			model.addAttribute("jobCount",jobCount);
			model.addAttribute("lastLoginTime", user.getLastLoginTimeString());
			model.addAttribute("enterprise",company );
			model.addAttribute("resumesActive", "current");
			return "enterprise/resumes";
		}
	}
	/**
	 * 根据id删除收到的简历
	 * @return
	 */
	@RequestMapping(value = "delResume", method = RequestMethod.POST)
	public String deleteResume(HttpServletRequest request,String idList, RedirectAttributes redirectAttributes,Model model) {
		SysUser user = (SysUser) sysUserService.validateSysUserLogin(request.getSession());
		int result = 0;
		if (user != null) {
			result = companyResumeBoxService.delById(idList, user.getId());
		} else {
			model.addAttribute("role", "enterprise");
			return "redirect:/login";
		}
		if (result < 1) {
			redirectAttributes.addFlashAttribute("error", "删除失败，请稍后重试！");
		}else{
			redirectAttributes.addFlashAttribute("error", "删除成功！");
		}
		return "redirect:/enterprise/resumes";
	}
	/**
	 * 去改密码
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("changePassword")
	public String toChangePassword(HttpServletRequest request,Model model){
		SysUser user = (SysUser) sysUserService.validateSysUserLogin(request.getSession());
		if(user == null){
			model.addAttribute("role", "enterprise");
			return "redirect:/login";
		}else{
			Company company=companyService.getByCreateby(user.getId());
			Long jobCount=0L;
			Long resumeCount=0L;
			if (company != null) {
				jobCount = jobBaseService.findJobCountByCompanyIdBySql(company.getId(),null);
				resumeCount=companyResumeBoxService.findResumeCountByCompanyIdBySql(company.getId());
				model.addAttribute("certification",certificationService.getByCompanyId(company.getId()));
			}
			model.addAttribute("resumeCount",resumeCount);
			model.addAttribute("jobCount",jobCount);
			model.addAttribute("lastLoginTime", user.getLastLoginTimeString());
			model.addAttribute("enterprise",company );
			model.addAttribute("changepwdActive", "current");
			return "enterprise/changePassword";
		}
	}
	/**
	 * 更改密码
	 * @param model
	 * @return
	 */
	@RequestMapping("changepwd")
	public String changePassword(HttpServletRequest request,RedirectAttributes redirectAttributes,Model model,
			@RequestParam String oldpwd,
			@RequestParam String newpwd,
			@RequestParam String newpwd2){
		SysUser user = (SysUser) sysUserService.validateSysUserLogin(request.getSession());
		if(!newpwd.equals(newpwd2)){
			redirectAttributes.addFlashAttribute("err_msg", "两次输入的密码不正确！");
			return "redirect:/enterprise/changePassword";
		}
		if(user!=null&&user.getId()!=null){//登录状态
			if(sysUserService.changepwd(oldpwd, newpwd, user.getId())==1){
				redirectAttributes.addFlashAttribute("err_msg", "更新密码成功！");
			}else{
				redirectAttributes.addFlashAttribute("err_msg", "原密码错误！");
			}
		}else{
			model.addAttribute("role", "enterprise");
			return "redirect:/login";
		}
		return "redirect:/enterprise/changePassword";
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
	 * 所属行业list
	 * @param model
	 */
	@ModelAttribute
	public void getIntentList(Model model){
		model.addAttribute("intentList", cache.get(Constants.CACHE_KEY_DIC_INDUSTRY_TYPE).getObjectValue());
	}
	/**
	 * 企业规模list
	 * @param model
	 */
	@ModelAttribute 
	public void getStaffscaleList(Model model){
		model.addAttribute("staffscaleList", cache.get(Constants.CACHE_KEY_DIC_STAFFSCALE).getObjectValue());
	}
	
	/**
	 * 登出
	 * @param valiCookie
	 * @param request
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(@CookieValue(value=Constants.COOKIE_KEY_ENTERPRISE_USER_VERIFY,required=false)String valiCookie,HttpServletRequest request,RedirectAttributes redirect){
		sysUserService.clearLogin(valiCookie, request.getSession());
		redirect.addFlashAttribute("clean", true);
		return "redirect:/login";
	}

}
