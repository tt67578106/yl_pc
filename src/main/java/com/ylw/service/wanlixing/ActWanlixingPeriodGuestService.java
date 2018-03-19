package com.ylw.service.wanlixing;

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

import com.ylw.entity.wanlixing.ActWanlixingPeriodGuest;
import com.ylw.repository.ActWanlixingPeriodGuestDao;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class ActWanlixingPeriodGuestService {

	private ActWanlixingPeriodGuestDao actWanlixingPeriodGuestDao;

	public ActWanlixingPeriodGuest getActWanlixingPeriodGuest(java.lang.Integer id){
		return actWanlixingPeriodGuestDao.findOne(id);
	}

	public void save(ActWanlixingPeriodGuest entity){
		actWanlixingPeriodGuestDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		actWanlixingPeriodGuestDao.delete(id);
	}

	public Page<ActWanlixingPeriodGuest> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<ActWanlixingPeriodGuest> spec = buildSpecification(userId.longValue(), searchParams);
		return actWanlixingPeriodGuestDao.findAll(spec, pageRequest);
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
	private Specification<ActWanlixingPeriodGuest> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<ActWanlixingPeriodGuest> spec = DynamicSpecifications.bySearchFilter(filters.values(), ActWanlixingPeriodGuest.class);
		return spec;
	}

	@Autowired
	public void setActWanlixingPeriodGuestDao(ActWanlixingPeriodGuestDao actWanlixingPeriodGuestDao) {
		this.actWanlixingPeriodGuestDao = actWanlixingPeriodGuestDao;
	}
}