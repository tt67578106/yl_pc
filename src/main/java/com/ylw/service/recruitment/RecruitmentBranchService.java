package com.ylw.service.recruitment;

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

import com.ylw.entity.recruitment.Recruitment;
import com.ylw.entity.recruitment.RecruitmentBranch;
import com.ylw.repository.recruitment.RecruitmentBranchDao;
import com.ylw.repository.recruitment.RecruitmentDao;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class RecruitmentBranchService {

	private RecruitmentBranchDao recruitmentBranchDao;
	
	private RecruitmentDao recruitmentDao;

	public RecruitmentBranch getRecruitmentBranch(java.lang.Integer id){
		return recruitmentBranchDao.findOne(id);
	}

	public void save(RecruitmentBranch entity){
		recruitmentBranchDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		recruitmentBranchDao.delete(id);
	}
	/**
	 * 根据招聘会id得到招聘会所关联的所有分站
	 * @param recruitCode
	 * @return
	 */
	public List<RecruitmentBranch> findByRecruitCodeAndIsBaidupro(String recruitCode,Integer isBaidupro) {
		Recruitment recruitment = recruitmentDao.getByRecruitCodeAndIsBaidupro(recruitCode,isBaidupro);
		if(recruitment != null){
			return recruitmentBranchDao.findByRecruitId(recruitment.getId());
		}
		return null;
	}

	public Page<RecruitmentBranch> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<RecruitmentBranch> spec = buildSpecification(userId.longValue(), searchParams);
		return recruitmentBranchDao.findAll(spec, pageRequest);
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
	private Specification<RecruitmentBranch> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<RecruitmentBranch> spec = DynamicSpecifications.bySearchFilter(filters.values(), RecruitmentBranch.class);
		return spec;
	}

	@Autowired
	public void setRecruitmentBranchDao(RecruitmentBranchDao recruitmentBranchDao) {
		this.recruitmentBranchDao = recruitmentBranchDao;
	}
	@Autowired
	public void setRecruitmentDao(RecruitmentDao recruitmentDao) {
		this.recruitmentDao = recruitmentDao;
	}

}