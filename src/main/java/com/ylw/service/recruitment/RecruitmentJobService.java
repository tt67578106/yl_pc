package com.ylw.service.recruitment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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

import com.ylw.entity.base.Branch;
import com.ylw.entity.recruitment.Recruitment;
import com.ylw.entity.recruitment.RecruitmentBranch;
import com.ylw.entity.recruitment.RecruitmentJob;
import com.ylw.repository.recruitment.RecruitmentBranchDao;
import com.ylw.repository.recruitment.RecruitmentDao;
import com.ylw.repository.recruitment.RecruitmentJobDao;
import com.ylw.util.Constants;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class RecruitmentJobService {

	private RecruitmentJobDao recruitmentJobDao;
	
	private RecruitmentBranchDao recruitmentBranchDao;
	
	private RecruitmentDao recruitmentDao;

	public void save(RecruitmentJob entity){
		recruitmentJobDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		recruitmentJobDao.delete(id);
	}
	
	/**
	 * 根据分站得到招聘会下的所有岗位
	 * @param recruitId
	 * @param branch
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public List<RecruitmentJob> findJobsByRecruitCodeAndBranchAndIsBaidupro(String recruitCode, Branch branch,Integer isBaidupro, int pageNumber, int pageSize,HttpServletResponse response ) {
		//添加cookie 标记为从百度推广进来的
		String cookieValue = recruitCode.concat(System.currentTimeMillis() + "");//
		if (response != null) {
			Cookie cookie = new Cookie(Constants.COOKIE_KEY_RECRUITMENT_CODE, cookieValue);
			cookie.setMaxAge(36000 * 24);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		List<RecruitmentJob> recruitmentJobList = null;
		Map<String,Object> searchParams = new HashMap<String, Object>();
		// 查询条件 GT 大于 ,LT 小于,GTE 大于等于, LTE 小于等于,EQ 等于, LIKE 包含
		Recruitment recruitment = recruitmentDao.getByRecruitCodeAndIsBaidupro(recruitCode,isBaidupro);
		if(recruitment != null){
			searchParams.put("EQ_recruitmentId", recruitment.getId()+"");
			if(branch != null){
				searchParams.put("EQ_branchId", branch.getId()+"");
			}
			recruitmentJobList = getRecruitmentJobPage(searchParams,pageNumber,pageSize,"sort").getContent();
		}
		return recruitmentJobList;
	}

	public Page<RecruitmentJob> getRecruitmentJobPage(Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<RecruitmentJob> spec = buildSpecification(searchParams);
		return recruitmentJobDao.findAll(spec, pageRequest);
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
		}else if("sort".equals(sortType)){
			sort = new Sort(Direction.ASC, "sort");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<RecruitmentJob> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<RecruitmentJob> spec = DynamicSpecifications.bySearchFilter(filters.values(), RecruitmentJob.class);
		return spec;
	}

	@Autowired
	public void setRecruitmentJobDao(RecruitmentJobDao recruitmentJobDao) {
		this.recruitmentJobDao = recruitmentJobDao;
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