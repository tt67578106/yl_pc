package com.ylw.service.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

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

import com.ylw.entity.job.Labels;
import com.ylw.repository.LabelsDao;
import com.ylw.util.Constants;
import com.ylw.util.MemcachedUtil;
import com.ylw.util.StoreCacheUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class LabelsService {
	
	private LabelsDao labelsDao;

	public List<Labels> findAll(){
		return (List<Labels>) labelsDao.findAll();
	}
	public Labels getLabels(java.lang.Integer id){
		return labelsDao.findOne(id);
	}

	public void save(Labels entity){
		labelsDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		labelsDao.delete(id);
	}
	/**
	 * 从缓存中查询查询热门标签
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Object findLablesCache(int pageNumber, int pageSize, String sortType){
		try {
			List<Labels> hotLables =  (List<Labels>) MemcachedUtil.getCacheValue(Constants.CACHE_KEY_HOME_HOT_LABELS);
			if(hotLables == null || hotLables.size() != pageSize){
				Page<Labels> page = getPage(new HashMap<String, Object>(), pageNumber, pageSize, sortType);
				MemcachedUtil.setCache(Constants.CACHE_KEY_HOME_HOT_LABELS, 3600, page.getContent());
				hotLables = page.getContent();
			}
			return hotLables;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询page对象
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<Labels> getPage(Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Labels> spec = buildSpecification(searchParams);
		return labelsDao.findAll(spec, pageRequest);
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
	private Specification<Labels> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Labels> spec = DynamicSpecifications.bySearchFilter(filters.values(), Labels.class);
		return spec;
	}

	@Autowired
	public void setLabelsDao(LabelsDao labelsDao) {
		this.labelsDao = labelsDao;
	}
	
	public void initLabels(){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		cache.remove(Constants.CACHE_KEY_HOME_HOT_LABELS);
		cache.put(new Element(Constants.CACHE_KEY_HOME_HOT_LABELS, labelsDao.findAll()));
	}
}