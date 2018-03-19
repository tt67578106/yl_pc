package com.ylw.service.code;

import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

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

import com.ylw.entity.code.CodeAreaProvince;
import com.ylw.repository.CodeAreaProvinceDao;
import com.ylw.util.Constants;
import com.ylw.util.StoreCacheUtil;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class CodeAreaProvinceService {

	private CodeAreaProvinceDao codeAreaProvinceDao;

	public CodeAreaProvince getCodeAreaProvince(java.lang.Integer id){
		return codeAreaProvinceDao.findOne(id);
	}

	public void save(CodeAreaProvince entity){
		codeAreaProvinceDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		codeAreaProvinceDao.delete(id);
	}

	public List<CodeAreaProvince> findAllProvince(){
		return (List<CodeAreaProvince>) codeAreaProvinceDao.findAll();
	}
	
	public List<CodeAreaProvince> findProvinces(){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		return (List<CodeAreaProvince>)cache.get(Constants.CACHE_KEY_PROVINCE).getObjectValue();
	}
	
	public void initAllProvinceCache(){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		cache.remove(Constants.CACHE_KEY_PROVINCE);
		cache.put(new Element(Constants.CACHE_KEY_PROVINCE, findAllProvince()));
	}
	
	public Page<CodeAreaProvince> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<CodeAreaProvince> spec = buildSpecification(userId.longValue(), searchParams);
		return codeAreaProvinceDao.findAll(spec, pageRequest);
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
	private Specification<CodeAreaProvince> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<CodeAreaProvince> spec = DynamicSpecifications.bySearchFilter(filters.values(), CodeAreaProvince.class);
		return spec;
	}

	@Autowired
	public void setCodeAreaProvinceDao(CodeAreaProvinceDao codeAreaProvinceDao) {
		this.codeAreaProvinceDao = codeAreaProvinceDao;
	}
}