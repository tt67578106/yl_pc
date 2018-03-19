package com.ylw.service.recruitment;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

import com.ylw.entity.job.JobBase;
import com.ylw.entity.recruitment.Recruitment;
import com.ylw.repository.JobBaseDao;
import com.ylw.repository.recruitment.RecruitmentDao;
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
public class RecruitmentService {

	private RecruitmentDao recruitmentDao;
	
	private JobBaseDao jobBaseDao;
	
	@PersistenceContext
	private EntityManager em;
	
	

	public Recruitment getRecruitment(java.lang.Integer id){
		return recruitmentDao.findOne(id);
	}

	public void save(Recruitment entity){
		recruitmentDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		recruitmentDao.delete(id);
	}
	
	/**
	 * 根据code得到招聘会
	 * @param recruitCode
	 * @return
	 */
	public Recruitment getByRecruitCodeAndIsBaidupro(String recruitCode,Integer isBaidupro) {
		return recruitmentDao.getByRecruitCodeAndIsBaidupro(recruitCode,isBaidupro);
	}
	
	public Page<Recruitment> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Recruitment> spec = buildSpecification(userId.longValue(), searchParams);
		return recruitmentDao.findAll(spec, pageRequest);
	}
	
	/**
	 * 根据id得到推广岗位
	 * @param jobId
	 * @return
	 */
	public JobBase getByJobId(Integer jobId,HttpServletResponse response,String recSource) {
		if(jobId != null){
			//添加cookie 标记为从百度推广2进来的
			String cookieValue = jobId.toString().concat(System.currentTimeMillis() + "");//
			if (response != null) {
				Cookie cookie = new Cookie(Constants.COOKIE_KEY_RECRUITMENT_DETAIL_CODE, cookieValue);
				cookie.setMaxAge(36000 * 24);
				cookie.setPath("/");
				response.addCookie(cookie);
				Cookie sourceCookie = new Cookie(Constants.COOKIE_KEY_RECRUITMENT_DETAIL_RECSOURCE, recSource);
				sourceCookie.setMaxAge(36000 * 24);
				sourceCookie.setPath("/");
				response.addCookie(sourceCookie);
			}
			return jobBaseDao.getByJobConfigIsPublishAndId(1, jobId);
		}else{
			return null;
		}
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
	private Specification<Recruitment> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<Recruitment> spec = DynamicSpecifications.bySearchFilter(filters.values(), Recruitment.class);
		return spec;
	}

	@Autowired
	public void setRecruitmentDao(RecruitmentDao recruitmentDao) {
		this.recruitmentDao = recruitmentDao;
	}

	@Autowired
	public void setJobBaseDao(JobBaseDao jobBaseDao) {
		this.jobBaseDao = jobBaseDao;
	}

	/**
	 * 相似岗位
	 * @param jobBase
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<JobBase> findLikeJobs(JobBase jobBase) {
		if(jobBase!=null){
			StringBuffer strSql = new StringBuffer("select job from JobBase job where job.jobConfig.isPublish = 1 ");
			strSql.append(" and (job.cooperationType = 1 or job.cooperationType = 5) and job.city.id = :cityId ");
			strSql.append(" and job.id != :jobId  ");
			strSql.append(" order by job.updatetime DESC");
			Query query = em.createQuery(strSql.toString());
			query.setParameter("cityId", jobBase.getCity().getId());
			query.setParameter("jobId", jobBase.getId());
			query.setMaxResults(3);
			List<JobBase> jobList = query.getResultList();
			if(jobList == null||jobList.size()==0){
				StringBuffer strSql2 = new StringBuffer("select job from JobBase job where job.jobConfig.isPublish = 1 ");
				strSql2.append(" and (job.cooperationType = 1 or job.cooperationType = 5) ");
				strSql2.append(" and job.id != :jobId  ");
				strSql2.append(" order by job.updatetime DESC");
				Query query2 = em.createQuery(strSql2.toString());
				query2.setParameter("jobId", jobBase.getId());
				query2.setMaxResults(3);
				jobList = query2.getResultList();
			}
			return jobList;
		}
		return null;
	}
}