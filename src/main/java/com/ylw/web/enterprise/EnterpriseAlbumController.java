package com.ylw.web.enterprise;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ylw.entity.base.Company;
import com.ylw.entity.base.Image;
import com.ylw.entity.sys.SysUser;
import com.ylw.service.base.CompanySceneService;
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
/**
 * 企业相册控制类
 * @author Frank
 */
@Controller
@RequestMapping("album")
public class EnterpriseAlbumController {
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
	private CompanySceneService companySceneService;
	@Autowired
	private CertificationService certificationService;
	@Autowired
	private CodeAreaProvinceService areaProvinceService; 
	@Autowired
	private CertificationWorkOrderService certificationWorkOrderService;
	
	private static final String PAGE_SIZE = "15";
	private Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");

	/**
	 * 加载企业相册
	 * @param request
	 * @param model
	 * @param category
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String loginForm(HttpServletRequest request,Model model,@RequestParam(value="sortType",defaultValue="auto") String sortType,@RequestParam(value = "page", defaultValue = "1") int pageNumber, @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,@RequestParam(value = "category", defaultValue = "0") Integer category){
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
				//企业相册分类数量统计
				model.addAttribute("scene1",companySceneService.countByCompanyIdAndCategoryAndIsshow(company.getId(), 1));
				model.addAttribute("scene2",companySceneService.countByCompanyIdAndCategoryAndIsshow(company.getId(), 2));
				model.addAttribute("scene3",companySceneService.countByCompanyIdAndCategoryAndIsshow(company.getId(), 3));
				model.addAttribute("scene4",companySceneService.countByCompanyIdAndCategoryAndIsshow(company.getId(), 4));
				model.addAttribute("scene5",companySceneService.countByCompanyIdAndCategoryAndIsshow(company.getId(), 5));
				model.addAttribute("scene6",companySceneService.countByCompanyIdAndCategoryAndIsshow(company.getId(), 6));
				
				model.addAttribute("resumeCount",resumeCount);
				model.addAttribute("jobCount",jobCount);
				model.addAttribute("enterprise",company);
				model.addAttribute("lastLoginTime", user.getLastLoginTimeString());
				model.addAttribute("page",companySceneService.getUserPage(user.getId(), company.getId(), category, new HashMap<String, Object>(), pageNumber, pageSize,sortType));
				model.addAttribute("albumActive", "current");
				model.addAttribute("category", category);
				//企业相册当前选项卡选中
				model.addAttribute(companySceneService.getCurrent(category),"current");
				model.addAttribute("certification",certificationService.getByCompanyId(company.getId()));
			}else{
				model.addAttribute("error", "请完善您的企业基本信息");
			}
			return "enterprise/album";
		}
	}
	/**
	 * 企业相册批量上传
	 * @param file
	 * @param resumeid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/upload/companyAlbum",method=RequestMethod.POST)
	public Image companyLogoUpload(@RequestParam("file") MultipartFile file,@RequestParam Integer companyid,@RequestParam Integer category){
		Image img = imageService.uploadImage(file,"企业相册",Constants.ALIYUN_COMPANY_SCENE_SAVE_PATH,category,companyid);
		return img;
	}
	/**
	 * 删除企业相册图片(假删isshow = 2)
	 * @param id
	 * @param isshow
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteImg" , method=RequestMethod.GET)
	public Object deleteImg(@RequestParam("id") Integer id){
		int result =  companySceneService.updateIsShow(id, 2);
		return result > 0 ? Constants.RETURN_STATUS_SUCCESS : Constants.RETURN_STATUS_FAILURE;
	}
	
	/**
	 * 修改相册名称
	 * @param request
	 * @param enterprise
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="updateAlbum",method=RequestMethod.POST)
	public String updateAlbum(HttpServletRequest request,@RequestParam Integer id,@RequestParam String comment,Model model)
	{
		SysUser user = (SysUser) sysUserService.validateSysUserLogin(request.getSession());
		if(user == null){
			model.addAttribute("role", "enterprise");
			return "redirect:/login";
		}
		imageService.updateComment(id,comment);
		return comment;
	}
}
