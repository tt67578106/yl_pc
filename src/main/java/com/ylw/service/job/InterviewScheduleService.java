package com.ylw.service.job;

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

import com.ylw.entity.job.InterviewSchedule;
import com.ylw.repository.InterviewScheduleDao;

/**
 * @author Jack
 * @version 1.0.0
 * @since 1.0.0
 * @date 2017-7-12 15:10:05
 */

@Component
@Transactional
public class InterviewScheduleService {
	/**
	 * Dao操作对象
	 */
	private InterviewScheduleDao interviewScheduleDao;
	
	/** 
	 * 注入Dao操作对象
	 */
	@Autowired
	public void setInterviewScheduleDao(InterviewScheduleDao interviewScheduleDao) {
		this.interviewScheduleDao = interviewScheduleDao;
	}

	/**
	 * 通过Id查询对象
	 * @param id
	 * @return
	 */
	public InterviewSchedule getInterviewSchedule(java.lang.Integer id){
		return interviewScheduleDao.findOne(id);
	}

	/**
	 * 保存单个对象
	 * @param entity
	 */
	public void save(InterviewSchedule entity){
		interviewScheduleDao.save(entity);
	}

	/**
	 * 根据Id删除单个对象
	 * @param id
	 */
	public void delete(java.lang.Integer id){
		interviewScheduleDao.delete(id);
	}

	/**
	 * 查询分页对象
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<InterviewSchedule> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<InterviewSchedule> spec = buildSpecification(userId.longValue(), searchParams);
		return interviewScheduleDao.findAll(spec, pageRequest);
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
	private Specification<InterviewSchedule> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<InterviewSchedule> spec = DynamicSpecifications.bySearchFilter(filters.values(), InterviewSchedule.class);
		return spec;
	}

	/**
	 * 根据岗位id查询排期
	 * @param jobId
	 * @return
	 */
	public List<InterviewSchedule> findScheduleByJobId(Integer jobId) {
		return interviewScheduleDao.findScheduleByJobId(jobId);
	}
	
}