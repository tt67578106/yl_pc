package com.ylw.service.wanlixing;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import com.ylw.entity.vo.ImageVo;
import com.ylw.entity.wanlixing.ActWanlixingProject;
import com.ylw.entity.wanlixing.ActWanlixingProjectImage;
import com.ylw.entity.wanlixing.ActWanlixingRegistration;
import com.ylw.repository.ActWanlixingProjectDao;
import com.ylw.repository.ActWanlixingProjectImageDao;
import com.ylw.repository.ActWanlixingRegistrationDao;
import com.ylw.util.HTMLInputFilter;
import com.ylw.util.StoreCacheUtil;
import com.ylw.util.TextUtil;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class ActWanlixingRegistrationService {

	private ActWanlixingRegistrationDao actWanlixingRegistrationDao;
	
	private ActWanlixingProjectDao actWanlixingProjectDao;
	
	private ActWanlixingProjectImageDao actWanlixingProjectImageDao;

	public ActWanlixingRegistration getActWanlixingRegistration(java.lang.Integer id){
		return actWanlixingRegistrationDao.findOne(id);
	}

	public void save(ActWanlixingRegistration entity){
		actWanlixingRegistrationDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		actWanlixingRegistrationDao.delete(id);
	}


	public ActWanlixingRegistration getByUserId(Integer userId) {
		List<ActWanlixingRegistration> apList = actWanlixingRegistrationDao.findByUserId(userId);
		if(apList.size()>0){
			return apList.get(0);
		}
		return null;
	}
	
	public Page<ActWanlixingRegistration> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<ActWanlixingRegistration> spec = buildSpecification(userId.longValue(), searchParams);
		return actWanlixingRegistrationDao.findAll(spec, pageRequest);
	}

	public ImageVo buildRegistrationUrl(String url) {
		ImageVo wp = new ImageVo();
		if(StringUtils.isNotBlank(url)){
			String key = new Random().nextInt()+"";
			StoreCacheUtil.putCache("updateImagePath",key,url);
			wp = new ImageVo(url,key);
		}
		return wp;
	}
	
	public ImageVo buildRegistrationUrl(ActWanlixingProjectImage image) {
		ImageVo wp = new ImageVo();
		if(image!=null){
			String key = new Random().nextInt()+"";
			StoreCacheUtil.putCache("updateImagePath",key,image.getImagePath());
			wp = new ImageVo(image.getImagePath(),image.getImageAlt(),key,image.getIntroduction());
		}
		return wp;
	}
	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<ActWanlixingRegistration> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<ActWanlixingRegistration> spec = DynamicSpecifications.bySearchFilter(filters.values(), ActWanlixingRegistration.class);
		return spec;
	}

	public void save(Integer userId, ActWanlixingRegistration registration,ActWanlixingProject project,String leaderImage,
			String leaderTeam) {
		cleanXSSRegistration(registration);
		registration.setCreateTime(new Date());
		registration.setUserId(userId);
		registration.setPrintScan(getImagePath(registration.getPrintScan()));
		registration.setIdCardScan(getImagePath(registration.getIdCardScan()));
		registration.setStudentCardScan(getImagePath(registration.getStudentCardScan()));
		actWanlixingRegistrationDao.save(registration);
		
		cleanXSSProject(project);
		project.setCeoUserId(userId);
		project.setCeoName(registration.getFullName());
		project.setActWanlixingRegistrationId(registration.getId());
		project.setPptFile(getImagePath(project.getPptFile()));
		project.setIsValidate(1);
		actWanlixingProjectDao.save(project);
		
		if(StringUtils.isNotBlank(leaderImage)){
				ActWanlixingProjectImage image = new ActWanlixingProjectImage();
				image.setProjectId(project.getId());
				image.setImagePath(getImagePath(leaderImage));
				image.setImageType(4);
				image.setCreateTime(new Date());
				actWanlixingProjectImageDao.save(image);
		}
		
		if(leaderTeam!=null){
				for(String str:leaderTeam.split(",")){
					if(str.split("#").length>0){
						ActWanlixingProjectImage imageFor = new ActWanlixingProjectImage();
						imageFor.setProjectId(project.getId());
						imageFor.setImagePath(getImagePath(str.split("#")[0]));
						if(str.split("#").length>1){
							imageFor.setIntroduction(HTMLInputFilter.clean(str.split("#")[1]));
						}
						if(str.split("#").length>2 && TextUtil.isNumeric1(str.split("#")[2])){
							imageFor.setSorting(Integer.parseInt(str.split("#")[2]));
						}
						imageFor.setImageType(1);
						imageFor.setCreateTime(new Date());
						actWanlixingProjectImageDao.save(imageFor);
					}
				}
		}}
	/**
	 * 根据缓存key取图片地址
	 * @param key
	 * @return
	 */
	public String getImagePath(String key){
		return StoreCacheUtil.getCacheValue("updateImagePath", key)==null?null:StoreCacheUtil.getCacheValue("updateImagePath", key).toString();
	}
	public ActWanlixingRegistration cleanXSSRegistration(ActWanlixingRegistration registration) {
		registration.setInsitution(HTMLInputFilter.clean(registration.getInsitution()));
		registration.setTeamName(HTMLInputFilter.clean(registration.getTeamName()));
		registration.setFullName(HTMLInputFilter.clean(registration.getFullName()));
		registration.setPost(HTMLInputFilter.clean(registration.getPost()));
		registration.setTelephone(HTMLInputFilter.clean(registration.getTelephone()));
		registration.setMobile(HTMLInputFilter.clean(registration.getMobile()));
		registration.setEmail(HTMLInputFilter.clean(registration.getEmail()));
		registration.setDepartmentAndProfessional(HTMLInputFilter.clean(registration.getDepartmentAndProfessional()));
		return registration;
	}
	public ActWanlixingProject cleanXSSProject(ActWanlixingProject project) {
		project.setSchool(project.getSchool());
		project.setIntroduction(project.getIntroduction());
		project.setVideo(project.getVideo());
		project.setTeamName(project.getTeamName());
		project.setTitle(project.getTitle());
		return project;
	}
	@Autowired
	public void setActWanlixingRegistrationDao(ActWanlixingRegistrationDao actWanlixingRegistrationDao) {
		this.actWanlixingRegistrationDao = actWanlixingRegistrationDao;
	}
	@Autowired
	public void setActWanlixingProjectDao(
			ActWanlixingProjectDao actWanlixingProjectDao) {
		this.actWanlixingProjectDao = actWanlixingProjectDao;
	}
	@Autowired
	public void setActWanlixingProjectImageDao(
			ActWanlixingProjectImageDao actWanlixingProjectImageDao) {
		this.actWanlixingProjectImageDao = actWanlixingProjectImageDao;
	}

	
}