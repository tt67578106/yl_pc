package com.ylw.service.base;

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
import org.springside.modules.persistence.SearchFilter.Operator;

import com.ylw.entity.base.CompanyScene;
import com.ylw.repository.CompanySceneDao;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class CompanySceneService {	

	private CompanySceneDao companySceneDao;

	public CompanyScene getCompanyScene(java.lang.Integer id){
		return companySceneDao.findOne(id);
	}

	public void save(CompanyScene entity){
		companySceneDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		companySceneDao.delete(id);
	}

	public Integer countByCompanyIdAndCategoryAndIsshow(Integer companyId,Integer category){
		return companySceneDao.countByCompanyIdAndCategoryAndIsshow(companyId,category,1);
	}
	/**
	 * 设置照片是否显示
	 * @param id
	 * @param isshow
	 * @return
	 */
	public int updateIsShow(Integer id , Integer isshow){
		return companySceneDao.updateIsShow(id, isshow);
	}
	/**
	 * 取公司最新图片
	 * @param companyId
	 * @return
	 */
	public CompanyScene getCompanySceneByCompanyOrderByCreatetime(Integer companyId){
		List<CompanyScene> companyScenes = companySceneDao.findByCompanyIdAndIsshow(companyId,1,buildPageRequest(1,1,"createtime")).getContent();
		if(companyScenes.size()>0){
			return companyScenes.get(0);
		}
		return null;
	}
	/**
	 * 企业相册中获取点击的选项卡选中
	 * @param category
	 * @return
	 */
	public String getCurrent(Integer category){
		if(category == 0 || category == null)
			return "current";
		return "current"+category;
	}
	/**
	 * 分页查询企业相册
	 * @param userId
	 * @param companyId
	 * @param category
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<CompanyScene> getUserPage(java.lang.Integer userId,Integer companyId,Integer category, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<CompanyScene> spec = buildSpecification(userId.longValue(),companyId,category,searchParams);
		return companySceneDao.findAll(spec, pageRequest);
	}
	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("createtime".equals(sortType)) {
			sort = new Sort(Direction.DESC, "createtime");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<CompanyScene> buildSpecification(Long userId, Integer companyId, Integer category,Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("company.id", new SearchFilter("company.id", Operator.EQ, companyId));
		filters.put("isshow", new SearchFilter("isshow", Operator.EQ, 1));
		if(category != 0){
			filters.put("category", new SearchFilter("category", Operator.EQ, category));
		}
		Specification<CompanyScene> spec = DynamicSpecifications.bySearchFilter(filters.values(), CompanyScene.class);
		return spec;
	}
	/**
	 * 根据相册类型获取企业实景图
	 * @return
	 */
	public List<CompanyScene> findByCategoryList(Integer companyid,Integer category) {
		List<CompanyScene> list = companySceneDao.findByCompanyIdAndCategoryAndIsshow(companyid,category,1);
		return list;
	}
	/**
	 * 根据企业ID获取企业实景图
	 * @return
	 */
	public List<CompanyScene> findCompanySceneList(Integer companyid) {
		List<CompanyScene> list = companySceneDao.findByCompanyIdAndIsshow(companyid,1);
		return list;
	}
	@Autowired
	public void setCompanySceneDao(CompanySceneDao companySceneDao) {
		this.companySceneDao = companySceneDao;
	}
}