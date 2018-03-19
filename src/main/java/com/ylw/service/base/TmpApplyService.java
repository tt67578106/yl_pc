package com.ylw.service.base;

import java.util.HashMap;
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

import com.ylw.entity.base.TmpApply;
import com.ylw.repository.TmpApplyDao;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class TmpApplyService {

	private TmpApplyDao tmpApplyDao;

	public TmpApply getTmpApply(java.lang.Integer id){
		return tmpApplyDao.findOne(id);
	}

	public void save(TmpApply entity){
		tmpApplyDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		tmpApplyDao.delete(id);
	}
	
	public List<TmpApply> findTmpApplysPage(Integer pageNumber,Integer pageSize)
	{
		Map<String, Object> searchParams = new HashMap<String, Object>();
		// 查询条件 GT 大于 ,LT 小于,GTE 大于等于, LTE 小于等于,EQ 等于, LIKE 包含
		Page<TmpApply> pages = getTmpApplysPage(searchParams, pageNumber, pageSize, "createtime");
		
		List<TmpApply> tmpApplyList=pages.getContent();
		
		return tmpApplyList;
	}

	public Page<TmpApply> getTmpApplysPage( Map<String,Object> searchParams, Integer pageNumber, Integer pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<TmpApply> spec = buildSpecification(searchParams);
		return tmpApplyDao.findAll(spec, pageRequest);
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
	private Specification<TmpApply> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<TmpApply> spec = DynamicSpecifications.bySearchFilter(filters.values(), TmpApply.class);
		return spec;
	}

	@Autowired
	public void setTmpApplyDao(TmpApplyDao tmpApplyDao) {
		this.tmpApplyDao = tmpApplyDao;
	}
}