package com.ylw.service.sys;

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

import com.ylw.entity.sys.SysRoleResource;
import com.ylw.repository.SysRoleResourceDao;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class SysRoleResourceService {

	private SysRoleResourceDao sysRoleResourceDao;

	public SysRoleResource getSysRoleResource(java.lang.Integer id){
		return sysRoleResourceDao.findOne(id);
	}

	public void save(SysRoleResource entity){
		sysRoleResourceDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		sysRoleResourceDao.delete(id);
	}

	public Page<SysRoleResource> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<SysRoleResource> spec = buildSpecification(userId.longValue(), searchParams);
		return sysRoleResourceDao.findAll(spec, pageRequest);
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
	private Specification<SysRoleResource> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<SysRoleResource> spec = DynamicSpecifications.bySearchFilter(filters.values(), SysRoleResource.class);
		return spec;
	}

	@Autowired
	public void setSysRoleResourceDao(SysRoleResourceDao sysRoleResourceDao) {
		this.sysRoleResourceDao = sysRoleResourceDao;
	}
}