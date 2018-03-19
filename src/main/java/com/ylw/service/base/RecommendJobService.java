package com.ylw.service.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

import com.ylw.entity.base.RecommendJob;
import com.ylw.entity.job.JobBase;
import com.ylw.entity.vo.RecommendJobVo;
import com.ylw.repository.RecommendJobDao;
import com.ylw.util.Constants;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.MemcachedUtil;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */
/**
 * 推荐岗位
 * @author Nicolas
 *
 */
// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class RecommendJobService {

	@PersistenceContext
	private EntityManager em;
	
	private RecommendJobDao recommendJobDao;

	public RecommendJob getRecommendJob(java.lang.Integer id){
		return recommendJobDao.findOne(id);
	}

	public void save(RecommendJob entity){
		recommendJobDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		recommendJobDao.delete(id);
	}

	public Page<RecommendJob> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<RecommendJob> spec = buildSpecification(userId.longValue(), searchParams);
		return recommendJobDao.findAll(spec, pageRequest);
	}

	/**
	 * 查询推荐岗位
	 * @param key 关键key
	 * @param positionId 位置ID
	 * @return
	 */
	public List<RecommendJobVo> findRecommendJobCache(String key, String recommendPositionCode,Integer branchId){
		List<RecommendJobVo> recommendJobVos;
			try {
				recommendJobVos = (List<RecommendJobVo>) MemcachedUtil.getCacheValue(key+branchId);
				if(recommendJobVos == null || recommendJobVos.size() == 0){
					List<RecommendJob> recommendJobs  = recommendJobDao.findByIsPublishAndRecommendPositionCodeAndOfflineTimeGreaterThan(2, recommendPositionCode, DateConvertUtils.getNow(),branchId,buildPageRequest(1,6,"sorting")).getContent();
					recommendJobVos = buildRecommendJobVO(recommendJobs);
					MemcachedUtil.setCache(key+branchId, 3600*24, recommendJobVos);
				}
				return recommendJobVos;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return null;
	}
	
	/**
	 * 查找优蓝精选里面的所有jobid
	 * @return
	 */
	public List<Integer> findYljxJobIdList(Integer branchId) {
		List<Integer> yljxJobIdList=new ArrayList<Integer>();
		List<RecommendJobVo> jobVoList=findRecommendJobCache(Constants.CACHE_KEY_RECOMMEND_JOB_YLJX, Constants.POSITION_CODE_RECOMMEND_JOBS_YLJX,branchId);
		if(jobVoList!=null&&jobVoList.size()>0)
		{
			for (RecommendJobVo recommendJobVo : jobVoList) {
				yljxJobIdList.add(recommendJobVo.getJobId());
			}
		}
		return yljxJobIdList;
	}
	/**
	 * 不走缓存直接查询
	 * @param key
	 * @param recommendPositionCode
	 * @param branchId
	 * @return
	 */
	public Page<RecommendJob> findRecommendJobPage(String key, String recommendPositionCode,Integer branchId){
		return recommendJobDao.findByIsPublishAndRecommendPositionCodeAndOfflineTimeGreaterThan(2, recommendPositionCode, DateConvertUtils.getNow(),branchId,buildPageRequest(1,6,"sorting"));
	}
	
	/**
	 * 从数据库 查询 RecommendJob数据 
	 * @param limit 数量
	 * @param provinceId 省id
	 * @param cityId 城市id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RecommendJob> findRecommendList(int isHot,  int limit,Integer provinceId, Integer branchId) {
		List<RecommendJob> recommendJobList = null;
		StringBuffer jpql = new StringBuffer("select job from RecommendJob job where job.isPublish = 2 and job.recommendPositionCode = :recommendPositionCode ");
		if(provinceId != null){
			jpql.append(" and job.provinceid = :provinceid ");
		}
			jpql.append(" AND job.branch.id = :branchId  ");
		if(isHot == 1){
	
		}
	    jpql.append(" and job.offlineTime >= :now  order by job.sorting ASC");
		Query query = em.createQuery(jpql.toString());
		if(provinceId != null){
			query.setParameter("provinceid", provinceId);
		}
		query.setParameter("branchId", branchId);
		query.setParameter("recommendPositionCode", Constants.POSITION_CODE_RECOMMEND_JOBS);
		query.setParameter("now", DateConvertUtils.getNow());
		query.setMaxResults(limit);
		recommendJobList = query.getResultList();
		return recommendJobList;
	}
	
	
	/**
	 * 相似岗位
	 * @param limit 数量
	 * @param cityId 城市id
	 * @param branchId 分站ID
	 * @param salarytotal 薪资分布
	 * @return
	 */
	public List<JobBase> findSimilarJob(Integer limit, Integer cityId,List<Integer> cityList, int salaryfrom,int salaryto) {
		StringBuffer jpql = new StringBuffer("select job from JobBase job,JobDetail detail where job.jobConfig.isPublish = 1 and job.jobConfig.isRecruitment = 0 ");
		if(cityId != null){
			jpql.append(" and job.city.id = :cityId ");
		}
		if(cityList != null){
			jpql.append(" and job.city.id in (:cityList) ");
		}
		if(salaryfrom != 0 && salaryto == 0){
			jpql.append(" AND :salaryfrom BETWEEN detail.salaryfrom AND detail.salaryto ");
		}
		if(salaryfrom != 0 && salaryto != 0){
			jpql.append(" and ((detail.salaryfrom BETWEEN :salaryfrom  AND :salaryto) or (detail.salaryto BETWEEN  :salaryfrom  AND :salaryto )) ");
		}
		jpql.append(" GROUP BY job.id ");
		Query query = em.createQuery(jpql.toString());
		if(cityId != null){
			query.setParameter("cityId", cityId);
		}
		if(cityList != null){
			query.setParameter("cityList",cityList);
		}
		if(salaryfrom != 0 && salaryto == 0){
			query.setParameter("salaryfrom",salaryfrom);
		}
		if(salaryfrom != 0 && salaryto != 0){
			query.setParameter("salaryfrom",salaryfrom);
			query.setParameter("salaryto",salaryto);
		}
		query.setFirstResult(0);
		query.setMaxResults(limit);
		return query.getResultList();
	}
	
	/**
	 * 封装recommengJob  vo 对象
	 */
	public List<RecommendJobVo> buildRecommendJobVO(List<RecommendJob> recommendJobs){
		List<RecommendJobVo> recommendJobVos = new ArrayList<RecommendJobVo>();
		for(RecommendJob recommendJob:recommendJobs){
			RecommendJobVo recommendJobEntity = new RecommendJobVo();
			recommendJobEntity.setId(recommendJob.getId());
			recommendJobEntity.setTitle(recommendJob.getTitle());
			recommendJobEntity.setIsUrgency(recommendJob.getIsUrgency());
			recommendJobEntity.setRemark(recommendJob.getRemark());
			recommendJobEntity.setJobid(recommendJob.getJob()==null?null:recommendJob.getJob().getId());
			recommendJobEntity.setTotalSalary(recommendJob.getJob()==null?"":recommendJob.getJob().getTotalsalary());
			recommendJobEntity.setApplyCount(recommendJob.getJob()==null||recommendJob.getJob().getJobDetail()==null||recommendJob.getJob().getJobDetail().getShowApplyCount()==null?0:recommendJob.getJob().getJobDetail().getShowApplyCount());
			recommendJobEntity.setJobName(recommendJob.getJob()==null?"":recommendJob.getJob().getJobname());
			recommendJobEntity.setJobCityName((recommendJob.getJob()==null||recommendJob.getJob().getCity()==null)?null:recommendJob.getJob().getCity().getAbbreviation());
			recommendJobEntity.setImgPath((recommendJob.getJob() == null || recommendJob.getJob().getThumbnialImage() == null) ? null: recommendJob.getJob().getThumbnialImage().getImgpath());
			recommendJobEntity.setCompanyName((recommendJob.getJob() == null || recommendJob.getJob().getCompany() == null)?"":recommendJob.getJob().getCompany().getName());
			recommendJobEntity.setJobTitle(recommendJob.getJob()==null?"":recommendJob.getJob().getTitle());
			recommendJobEntity.setCompanyId((recommendJob.getJob() == null || recommendJob.getJob().getCompany() == null)?null:recommendJob.getJob().getCompany().getId());
			recommendJobEntity.setCompanyLogo((recommendJob.getJob() == null || recommendJob.getJob().getCompany() == null ||recommendJob.getJob().getCompany().getLogo()==null)?"":recommendJob.getJob().getCompany().getLogo().getImgpath());
			recommendJobEntity.setSalaryfrom((recommendJob.getJob() == null || recommendJob.getJob().getJobDetail() == null )?null:recommendJob.getJob().getJobDetail().getSalaryfrom());
			recommendJobEntity.setSalaryTo((recommendJob.getJob() == null || recommendJob.getJob().getJobDetail() == null )?null:recommendJob.getJob().getJobDetail().getSalaryto());
			recommendJobEntity.setValidation((recommendJob.getJob() == null || recommendJob.getJob().getCompany() == null)?null:recommendJob.getJob().getCompany().getValidation());
			recommendJobEntity.setUpdateTime(recommendJob.getJob()==null?null:recommendJob.getJob().getUpdatetime());
			recommendJobEntity.setCreateTime(recommendJob.getJob()==null?null:recommendJob.getJob().getCreatetime());
			recommendJobEntity.setAgeFrom((recommendJob.getJob() == null || recommendJob.getJob().getJobDetail() == null )?null:recommendJob.getJob().getJobDetail().getAgefrom());
			recommendJobEntity.setAgeTo((recommendJob.getJob() == null || recommendJob.getJob().getJobDetail() == null )?null:recommendJob.getJob().getJobDetail().getAgeto());
			recommendJobEntity.setEducation((recommendJob.getJob() == null || recommendJob.getJob().getJobDetail() == null )?null:recommendJob.getJob().getJobDetail().getEducation());
			recommendJobEntity.setJobLabel(recommendJob.getJob()==null?null:recommendJob.getJob().getJobLabel());
			recommendJobEntity.setGender((recommendJob.getJob() == null || recommendJob.getJob().getJobDetail() == null )?null:recommendJob.getJob().getJobDetail().getGender());
			recommendJobEntity.setJobType(recommendJob.getJob()==null?null:recommendJob.getJob().getJobType());
			recommendJobEntity.setCountyId(recommendJob.getJob()==null?null:recommendJob.getJob().getCountyid());
			recommendJobVos.add(recommendJobEntity);
		}
		return recommendJobVos;
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
		}else if ("sorting".equals(sortType)) {
			sort = new Sort(Direction.ASC, "sorting");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<RecommendJob> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<RecommendJob> spec = DynamicSpecifications.bySearchFilter(filters.values(), RecommendJob.class);
		return spec;
	}

	@Autowired
	public void setRecommendJobDao(RecommendJobDao recommendJobDao) {
		this.recommendJobDao = recommendJobDao;
	}
}