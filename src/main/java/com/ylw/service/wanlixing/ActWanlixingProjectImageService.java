package com.ylw.service.wanlixing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.ylw.entity.wanlixing.ActWanlixingProjectImage;
import com.ylw.repository.ActWanlixingProjectImageDao;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class ActWanlixingProjectImageService {

	private ActWanlixingProjectImageDao actWanlixingProjectImageDao;
	
	private ActWanlixingRegistrationService actWanlixingRegistrationService;

	public ActWanlixingProjectImage getActWanlixingProjectImage(java.lang.Integer id){
		return actWanlixingProjectImageDao.findOne(id);
	}

	public void save(ActWanlixingProjectImage entity){
		actWanlixingProjectImageDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		actWanlixingProjectImageDao.delete(id);
	}

	/**
	 * 得到单张图片地址
	 * @param id
	 * @param type
	 * @return
	 */
	public ImageVo getImageByProjectAndType(Integer id, int type) {
		List<ActWanlixingProjectImage> images = actWanlixingProjectImageDao.findByprojectIdAndImageTypeOrderBySortingAsc(id,type);
		if(images.size()>0){
			return actWanlixingRegistrationService.buildRegistrationUrl(images.get(0).getImagePath());
		}
		return new ImageVo();
	}
	
	/**
	 * 得到多张图片信息
	 * @param id
	 * @param i
	 * @return
	 */
	public List<ImageVo> findImagesByProjectAndType(Integer id, int type,String sort) {
		List<ActWanlixingProjectImage> api = new LinkedList<ActWanlixingProjectImage>();
		if(sort.equals("time")){
			 api = actWanlixingProjectImageDao.findByprojectIdAndImageTypeOrderByCreateTimeDesc(id,type);
		}else if(sort.equals("sorting")){
			 api = actWanlixingProjectImageDao.findByprojectIdAndImageTypeOrderBySortingAsc(id,type);
		}
		List<ImageVo> wpvs = new ArrayList<ImageVo>();
		for(ActWanlixingProjectImage actWanlixingProjectImage :api){
			if(actWanlixingProjectImage.getImagePath()!=null){
				wpvs.add(actWanlixingRegistrationService.buildRegistrationUrl(actWanlixingProjectImage));
			}
		}
		return wpvs;
	}
	
	public Page<ActWanlixingProjectImage> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<ActWanlixingProjectImage> spec = buildSpecification(userId.longValue(), searchParams);
		return actWanlixingProjectImageDao.findAll(spec, pageRequest);
	}

	public Integer deleteImageByProject(Integer id,String imageIds) {
		return actWanlixingProjectImageDao.deleteByProjectId(id);
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
	private Specification<ActWanlixingProjectImage> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<ActWanlixingProjectImage> spec = DynamicSpecifications.bySearchFilter(filters.values(), ActWanlixingProjectImage.class);
		return spec;
	}

	@Autowired
	public void setActWanlixingProjectImageDao(ActWanlixingProjectImageDao actWanlixingProjectImageDao) {
		this.actWanlixingProjectImageDao = actWanlixingProjectImageDao;
	}

	@Autowired
	public void setActWanlixingRegistrationService(
			ActWanlixingRegistrationService actWanlixingRegistrationService) {
		this.actWanlixingRegistrationService = actWanlixingRegistrationService;
	}

	public void save(List<ActWanlixingProjectImage> projectImageList) {
		actWanlixingProjectImageDao.save(projectImageList);
	}


}