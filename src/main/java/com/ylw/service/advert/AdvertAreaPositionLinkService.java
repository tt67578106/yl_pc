package com.ylw.service.advert;

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
import org.springframework.web.client.RestTemplate;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import com.alibaba.fastjson.JSON;
import com.ylw.entity.advert.AdvertAreaPositionLink;
import com.ylw.entity.vo.AdvResourcePubRecord;
import com.ylw.repository.advert.AdvertAreaPositionLinkDao;
import com.ylw.util.Constants;

/**
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2015-7-16 19:56:28 
 */

@Component
@Transactional
public class AdvertAreaPositionLinkService {
	/**
	 * Dao操作对象
	 */
	private AdvertAreaPositionLinkDao advertAreaPositionLinkDao;
	
	/** 
	 * 注入Dao操作对象
	 */
	@Autowired
	public void setAdvertAreaPositionLinkDao(AdvertAreaPositionLinkDao advertAreaPositionLinkDao) {
		this.advertAreaPositionLinkDao = advertAreaPositionLinkDao;
	}

	/**
	 * 通过Id查询对象
	 * @param id
	 * @return
	 */
	public AdvertAreaPositionLink getAdvertAreaPositionLink(java.lang.Integer id){
		return advertAreaPositionLinkDao.findOne(id);
	}

	/**
	 * 保存单个对象
	 * @param entity
	 */
	public void save(AdvertAreaPositionLink entity){
		advertAreaPositionLinkDao.save(entity);
	}

	/**
	 * 根据Id删除单个对象
	 * @param id
	 */
	public void delete(java.lang.Integer id){
		advertAreaPositionLinkDao.delete(id);
	}

	/**
	 * 查询分页对象
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<AdvertAreaPositionLink> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<AdvertAreaPositionLink> spec = buildSpecification(userId.longValue(), searchParams);
		return advertAreaPositionLinkDao.findAll(spec, pageRequest);
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
		} else if("sorting".equals(sortType)){
			sort = new Sort(Direction.ASC, "sorting");
		}
		

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<AdvertAreaPositionLink> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<AdvertAreaPositionLink> spec = DynamicSpecifications.bySearchFilter(filters.values(), AdvertAreaPositionLink.class);
		return spec;
	}
	
}