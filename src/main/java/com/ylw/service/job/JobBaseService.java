package com.ylw.service.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import com.ylw.entity.base.Branch;
import com.ylw.entity.base.BranchCity;
import com.ylw.entity.base.Company;
import com.ylw.entity.base.CompanyScene;
import com.ylw.entity.base.Image;
import com.ylw.entity.job.JobBase;
import com.ylw.entity.job.JobConfig;
import com.ylw.entity.job.JobDetail;
import com.ylw.entity.vo.CompanyListVo;
import com.ylw.entity.vo.RecommendJobVo;
import com.ylw.repository.BranchDao;
import com.ylw.repository.CodeAreaCityDao;
import com.ylw.repository.CodeAreaDistrictDao;
import com.ylw.repository.CodeAreaProvinceDao;
import com.ylw.repository.ImageDao;
import com.ylw.repository.JobBaseDao;
import com.ylw.repository.JobConfigDao;
import com.ylw.repository.JobDetailDao;
import com.ylw.repository.UserDao;
import com.ylw.service.base.JobSeachLuceneService;
import com.ylw.service.base.RecommendJobService;
import com.ylw.util.Constants;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.HTMLInputFilter;
import com.ylw.util.MemcachedUtil;
import com.ylw.util.StoreCacheUtil;
import com.ylw.util.XMLUtil;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */
@Component
@Transactional
public class JobBaseService {
//	private Logger logger = LoggerFactory.getLogger(JobBaseService.class);
	
	@PersistenceContext
	private EntityManager em;
	
	private JobBaseDao jobBaseDao;
	
	private JobConfigDao jobConfigDao;
	
	private JobDetailDao jobDetailDao;
	
	private CodeAreaCityDao codeAreaCityDao;
	
	private CodeAreaDistrictDao codeAreaDistrictDao;
	
	private CodeAreaProvinceDao codeAreaProvinceDao;
	
	private ImageDao imageDao;
	
	private UserDao userDao;
	
	private BranchDao branchDao;
	
	private RecommendJobService recommendJobService;
	
	private JobSeachLuceneService jobSeachLuceneService;
	
	/**
	 * 通过id查询单个JobBase对象
	 * @param id
	 * @return
	 */
	public JobBase getJobBase(java.lang.Integer id){
		return id!=null?jobBaseDao.findOne(id):null;
	}
	/**
	 * 通过id查询单个已经上线的JobBase对象
	 * @param id
	 * @return
	 */
	public JobBase getJobBaseAndIsPublish(java.lang.Integer id){
		if(id != null){
			return id!=null?jobBaseDao.getByJobConfigIsPublishAndId(1, id):null;
		}else{
			return null;
		}
	}
	/**
	 * 通过id查询单个jobDetail对象
	 * @param id
	 * @return
	 */
	public JobDetail getJobDetail(Integer id){
		return jobDetailDao.findOne(id);
	}
    /**
     * 根据jobid和createuserid得到jobbase
     * @param id
     * @param userid
     * @return
     */
	public JobBase getByIdAndCreateUserId(Integer id,Integer userid)
	{
		return jobBaseDao.getByIdAndCreateUserId(id,userid);
	}
	
	public void save(JobBase entity){
		jobBaseDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		jobBaseDao.delete(id);
	}
	/**
	 * 文章列表、详情页面的推荐岗位
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RecommendJobVo> getJobByBranchIdCache(Integer pageSize, Integer branchId) {
		List<JobBase> jobBaseList = new ArrayList<JobBase>();
		List<JobBase> jobBaseCachList = null;
		try {
			Object jobBaseCache = MemcachedUtil.getCacheValue(Constants.CACHE_KEY_TOP300_JOB+branchId);
			if (jobBaseCache == null || ((List<Object>) jobBaseCache).size() == 0) {// 从缓存中取数据，看是否有值
				List<Integer> cityList = findBranchCityIdList(branchId);// ，如果没值创建缓存
				Integer countyid = null;
				//临时处理昆山的情况
				if(branchId== 6 || cityList.contains(346)){
					countyid = 785;
				}
				jobBaseCachList = jobBaseDao.getHotAndNewByIsPublishAndCity(1, cityList.size()>0?cityList:null,countyid, buildPageRequest(1, 30, "updatetime")).getContent();
				MemcachedUtil.setCache(Constants.CACHE_KEY_TOP300_JOB+branchId, 3600 * 24, jobBaseCachList);
			} else {// 有值
				jobBaseCachList = (List<JobBase>)jobBaseCache;
			}
			if (jobBaseCachList != null && jobBaseCachList.size() != 0) {
				Random random = new Random();
				int start = 0;// 产生从0 到总数减去size的随机数
				int end=0;
				if(jobBaseCachList.size()>pageSize + 1){
					int max =jobBaseCachList.size() - pageSize - 1;
					start = random.nextInt(max);
					end=start + pageSize;
				}else{
					end=jobBaseCachList.size()-1;
				}
				for (int i = start; i <= end; i++) {// 从随机数开始取size个数据返回
					JobBase jobBase=jobBaseCachList.get(i);	
					jobBaseList.add(jobBase);
					
				}
			}
			return buildRecommendJobVO(jobBaseList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 从cache查询 jobbase数据 
	 * @param key 缓存的key
	 * @param branchId
	 * @return
	 */
	public List<RecommendJobVo> findJobCache(String key,Integer branchId,int pageNumber, int pageSize,String sortType,String type){
		try {
			List<RecommendJobVo> jobsCache =  (List<RecommendJobVo>) MemcachedUtil.getCacheValue(key+branchId);
			if (jobsCache == null || jobsCache.size() == 0) {
				List<Integer> yljxJobList = recommendJobService.findYljxJobIdList(branchId);
				List<Integer> cityList = new ArrayList<Integer>();
				Branch branch = branchDao.findOne(branchId);
				Integer countyid =null;
				if(branch!=null){
					cityList.add(branch.getDefaultCityId());
					//临时处理昆山的情况
					if(branch.getId()== 6 || branch.getDefaultCityId().equals(346)){
						countyid = 785;
					}
				}
				Page<JobBase> page = null;
				if (type == "bottom"||yljxJobList.size()==0) {
					page = jobBaseDao.getHotAndNewByIsPublishAndCity(1, cityList.size()>0?cityList:null,countyid, buildPageRequest(pageNumber, pageSize, sortType));
				} else {
					page = jobBaseDao.getByIsPublishAndCity(1, yljxJobList.size()>0?yljxJobList:null, cityList.size()>0?cityList:null,countyid, buildPageRequest(pageNumber, pageSize, sortType));
				}
				jobsCache = buildRecommendJobVO(page.getContent());
				MemcachedUtil.setCache(key + branchId, 3600 * 24, jobsCache);
			}
			return jobsCache;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 从cache查询 jobbase数据 
	 * @param isHot
	 * @param limit
	 * @param provinceId
	 * @param cityId
	 * @return
	 */
	public Object findHotJobCache(int isHot,int limit,Integer provinceId,Integer branchId){
		try {
			Object hotJobsCache = MemcachedUtil.getCacheValue(Constants.CACHE_KEY_HOME_HOT_JOB+branchId);
			if( hotJobsCache == null||((List<Object>)hotJobsCache).size()!=limit){
				hotJobsCache = findHotJobList(isHot, limit, provinceId, branchId);
				MemcachedUtil.setCache(Constants.CACHE_KEY_HOME_HOT_JOB+branchId, 3600*24, hotJobsCache);
			}
			return hotJobsCache;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 从数据库 查询 jobbase数据 
	 * @param isHost 1:热招  2：首页显示;3:名企热招
	 * @param limit 数量
	 * @param provinceId 省id
	 * @param cityId 城市id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<JobBase> findHotJobList(int isHot, int limit,Integer provinceId, Integer branchId) {
		List<JobBase> hotJobs = new ArrayList<JobBase>();
		StringBuffer jpql = new StringBuffer("select job from JobBase job where job.jobConfig.isPublish =1 ");
		if(provinceId != null){
			jpql.append(" and job.provinceid = :provinceid ");
		}
			jpql.append(" and job.city.id in(:cityList) ");
		jpql.append(" order by  job.applycount DESC,job.updatetime DESC");
		Query query = em.createQuery(jpql.toString());
		List<Integer> cityList = findBranchCityIdList(branchId==null?1:branchId);
		if(cityList.size()>0){
			query.setParameter("cityList",cityList);
		}else{
			query.setParameter("cityList",null);
		}
		if(provinceId != null){
			query.setParameter("provinceid", provinceId);
		}
		query.setMaxResults(limit);
		hotJobs = query.getResultList();
		return hotJobs;
	}
	/**
	 * 查询job缓存
	 * @param companyId
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object findByCompanyCache(Integer companyId,int limit){
		try {
			Object hotJobsCache = MemcachedUtil.getCacheValue(Constants.CACHE_KEY_COMPANY_JOB);
			if(hotJobsCache == null||((List<Object>)hotJobsCache).size()!=limit){
				hotJobsCache = findByCompanyId(companyId, limit);
				MemcachedUtil.setCache(Constants.CACHE_KEY_COMPANY_JOB, 3600, hotJobsCache);
			}
			return hotJobsCache;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获得所有的申请次数
	 * @return
	 */
	public Long countJobApply(){
		try {
			Long count = (Long) MemcachedUtil.getCacheValue(Constants.CACHE_KEY_APPLY_COUNT);
			if(count == null){
				//count = userDao.count()*99;
				count = userDao.count() + 9038192;
				MemcachedUtil.setCache(Constants.CACHE_KEY_APPLY_COUNT, 3600*24, count);
			}
			return count;
			//return 381927+count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}
	
	/**
	 * 获得所有的岗位的数量
	 * @return
	 */
	public Long getCountJob(){
		try {
			Long count = (Long) MemcachedUtil.getCacheValue(Constants.CACHE_KEY_JOB_COUNT);
			if(count == null){
				count = jobBaseDao.count();
				MemcachedUtil.setCache(Constants.CACHE_KEY_JOB_COUNT, 3600*24, count);
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}
	
	/**
	 * 获得所有工作岗位数量
	 * @return
	 */
	public Object allJobCount() {
		try {
			Long count = (Long) MemcachedUtil.getCacheValue(Constants.CACHE_KEY_ALL_JOB_COUNT);
			if(count == null){
				count = jobBaseDao.count();
				MemcachedUtil.setCache(Constants.CACHE_KEY_ALL_JOB_COUNT, 3600*24, count);
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}
		
	/**
	 * 根据companyId查询所有岗位
	 * @param companyId
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<JobBase> findByCompanyId(Integer companyId,int pageSize){
			String key=Constants.CACHE_KEY_FIND_JOB_BY_COMPANYID;
			Page<JobBase> jobsCache=null;
			jobsCache = (Page<JobBase>) StoreCacheUtil.getCacheValue("getJobByCompanyId", key+companyId);
			if (jobsCache == null || jobsCache.getContent().size() == 0) {
				StringBuffer jpql = new StringBuffer("select job from JobBase job where job.jobConfig.isPublish =1 and job.jobConfig.isRecruitment = 0");
				jpql.append(" and job.company.id = :companyId");
				Query query = em.createQuery(jpql.toString());
				Query countQuery = em.createQuery(jpql.toString().replace("select job from", "select count(1) from"));
				query.setParameter("companyId", companyId);
				countQuery.setParameter("companyId", companyId);
				PageRequest pageRequest = buildPageRequest(1, pageSize, "createtime");
				query.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
				query.setMaxResults(pageRequest.getPageSize());
				Long totalSize = (Long) countQuery.getSingleResult();
				jobsCache = new PageImpl<JobBase>(query.getResultList(), pageRequest, totalSize);
				StoreCacheUtil.putCache("getJobByCompanyId", key + companyId,jobsCache);
			}
			return jobsCache;
	}
	/**
	 * 根据companyId查询所有岗位
	 * @param companyId
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<JobBase> findByCompanyIdLimit(Integer companyId,int pageSize){
			String key=Constants.CACHE_KEY_FIND_JOB_BY_COMPANYID;
			Page<JobBase> jobsCache=null;
			jobsCache = (Page<JobBase>) StoreCacheUtil.getCacheValue("getJobByCompanyId", key+companyId);
			if (jobsCache == null || jobsCache.getContent().size() == 0) {
				StringBuffer jpql = new StringBuffer("select job from JobBase job where job.jobConfig.isPublish =1");
				jpql.append(" and job.company.id = :companyId");
				Query query = em.createQuery(jpql.toString());
				Query countQuery = em.createQuery(jpql.toString().replace("select job from", "select count(1) from"));
				query.setParameter("companyId", companyId);
				countQuery.setParameter("companyId", companyId);
				PageRequest pageRequest = buildPageRequest(1, pageSize, "createtime");
				query.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
				query.setMaxResults(pageRequest.getPageSize());
				jobsCache = new PageImpl<JobBase>(query.getResultList(), pageRequest, pageSize);
				StoreCacheUtil.putCache("getJobByCompanyId", key + companyId,jobsCache);
			}
			return jobsCache;
	}
	
	/**
	 * 根据companyId查询所有发布的岗位【不走缓存】
	 * @param companyId
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<JobBase> findByCompanyIdBySql(Integer companyId,int pageNumber,int pageSize){
		StringBuffer jpql = new StringBuffer("select job from JobBase job where job.jobConfig.isPublish =1");
		jpql.append(" and job.company.id = :companyId order by updatetime desc");
		Query query = em.createQuery(jpql.toString());
		Query countQuery = em.createQuery(jpql.toString().replace("select job from", "select count(1) from"));
		query.setParameter("companyId", companyId);
		countQuery.setParameter("companyId", companyId);
		Long totalSize = (Long) countQuery.getSingleResult();
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "updatetime");
		query.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		query.setMaxResults(pageRequest.getPageSize());
		Page<JobBase> page = new PageImpl<JobBase>(query.getResultList(), pageRequest, totalSize);
		return page;
	}
	
	
	/**
	 * 根据companyId查询所有岗位【不走缓存】
	 * @param companyId
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<JobBase> findByEnterpriseIdBySql(Integer companyId,int pageNumber,int pageSize){
		StringBuffer jpql = new StringBuffer("select job from JobBase job where ");
		jpql.append(" job.company.id = :companyId order by updatetime desc");
		Query query = em.createQuery(jpql.toString());
		Query countQuery = em.createQuery(jpql.toString().replace("select job from", "select count(1) from"));
		query.setParameter("companyId", companyId);
		countQuery.setParameter("companyId", companyId);
		Long totalSize = (Long) countQuery.getSingleResult();
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "updatetime");
		query.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		query.setMaxResults(pageRequest.getPageSize());
		Page<JobBase> page = new PageImpl<JobBase>(query.getResultList(), pageRequest, totalSize);
		return page;
	}
	
	/**
	 * 根据companyId查询所有在招岗位数量【不走缓存】
	 * @param companyId
	 * @return
	 */
	public Long findJobCountByCompanyIdBySql(Integer companyId,Date createtime){
		StringBuffer jpql = new StringBuffer("select count(1) from JobBase job ");
		jpql.append(" where job.company.id = :companyId");
		if (createtime!=null) {
			jpql.append(" and job.createtime >= :firstcreatetime");
			jpql.append(" and job.createtime <= :endcreatetime");
		}
		Query countQuery = em.createQuery(jpql.toString());
		if(createtime!=null){
			countQuery.setParameter("firstcreatetime",DateConvertUtils.firstDate(createtime));
			countQuery.setParameter("endcreatetime",DateConvertUtils.endDate(createtime));
		}
		countQuery.setParameter("companyId", companyId);
		Long totalSize = (Long) countQuery.getSingleResult();
		return totalSize;
	}
	
	/**
	 * 根据companyId查询该岗位下其他所有岗位
	 * @param companyId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<JobBase> findOtherJobsByCompanyId(Integer companyId,Integer jobId,int limit){
		StringBuffer jpql = new StringBuffer("select job from JobBase job where job.jobConfig.isPublish =1");
		jpql.append(" and job.company.id = :companyId and job.id<> :jobId order by job.updatetime desc");
		Query query = em.createQuery(jpql.toString());
		query.setParameter("companyId", companyId);
		query.setParameter("jobId", jobId);
		query.setMaxResults(limit);
		return query.getResultList();
	}
	
	
	/**
	 *  拼装hql语句查询page对象
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @param branchId 分站Id
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public Page<JobBase> getHqlPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType, Integer branchId){
		for (String tk : searchParams.keySet()) {
			if("0".equals(searchParams.get(tk)+"")){
				searchParams.put(tk, "");
			}
		}
		StringBuffer hql = new StringBuffer("select job from JobBase job where job.jobConfig.isPublish = 1 ");
		for (String field : searchParams.keySet()) {
			String obj = (searchParams.get(field) + "").trim();
			if("LIKE_jobname".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj)){//岗位名称
				hql.append(" and (job.jobname like :jobname or job.title like :jobname or job.jobType like :jobname) ");
			}else if("LIKE_company.name".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj)){
				hql.append(" and job.company.name like :companyname ");
			}else if("EQ_city.id".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj) && StringUtils.isNumeric(obj)){
				hql.append(" and job.city.id = :cityid ");
			}else if("LIKE_jobType".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj) && 
					(searchParams.get("LIKE_subJobType")==null || StringUtils.isBlank((searchParams.get("LIKE_subJobType") + "").trim())) ){
				hql.append(" and job.jobType like :jobType");
			}else if("LIKE_subJobType".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj) ){
				hql.append(" and job.jobType like :subJobType");
			}else if("EQ_countyid".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj)){
				hql.append(" and job.countyid = :countyid");
			}else if("LIKE_jobLabel".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj)){
				String splitLable[] = obj.split(",");
				StringBuffer labelHql = new StringBuffer( " and ( 1=1 ");
				for(String str : splitLable){
					labelHql.append(" and job.jobLabel like '%"+str+"%' ");
				}
				labelHql.append(")");
				hql.append(labelHql);
			}else if("LIKE_totalsalary".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj) && obj.split("-").length > 1){
				String[] salarys = obj.replaceAll("元", "").replaceAll("以", "").replaceAll("上", "").replaceAll("下", "").split("-");//高低薪资
				if(salarys.length>1&&StringUtils.isNumeric(salarys[0])&&StringUtils.isNumeric(salarys[1])){
					hql.append(" and ((job.jobDetail.salaryfrom BETWEEN :salaryfrom  AND :salaryto) or (job.jobDetail.salaryto BETWEEN  :salaryfrom  AND :salaryto ))");
				}
			}
		}
		List<Integer> cityIdList = findBranchCityIdList(branchId);
		if(branchId!=null && cityIdList.size()>0){
			hql.append(" and job.city.id in :cityList ");
		}
		hql.append(" order by job.jobConfig.isUrgency desc");
		if ("auto".equals(sortType)) {
			hql.append(",job.id asc ");
		} else if ("updatetime".equals(sortType)) {
			hql.append(",job.updatetime desc ");
		} else if("salaryasc".equals(sortType)){
			hql.append(",job.jobDetail.salaryfrom+job.jobDetail.salaryto asc ");
		}else if("salarydesc".equals(sortType)){
			hql.append(",job.jobDetail.salaryfrom+job.jobDetail.salaryto desc ");
		}
		hql.append(",job.city.id ");
		Query countQuery = em.createQuery(hql.toString().replace("select job from", "select count(1) from"));
		Query query = em.createQuery(hql.toString());
		for (String field : searchParams.keySet()) {
			String obj = (searchParams.get(field) + "").trim();
			if("LIKE_jobname".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj)){//岗位名称
				query.setParameter("jobname", "%"+obj+"%");
				countQuery.setParameter("jobname", "%"+obj+"%");
			}else if("LIKE_company.name".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj)){
				query.setParameter("companyname", "%"+obj+"%");
				countQuery.setParameter("companyname", "%"+obj+"%");
			}else if("EQ_city.id".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj) && StringUtils.isNumeric(obj)){
				query.setParameter("cityid", Integer.parseInt(obj));
				countQuery.setParameter("cityid", Integer.parseInt(obj));
			}else if("LIKE_jobType".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj) &&
					(searchParams.get("LIKE_subJobType")==null || StringUtils.isBlank((searchParams.get("LIKE_subJobType") + "").trim())) ){
				query.setParameter("jobType", "%"+obj+"%");
				countQuery.setParameter("jobType", "%"+obj+"%");
			}else if("LIKE_subJobType".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj)){
				query.setParameter("subJobType", "%"+obj+"%");
				countQuery.setParameter("subJobType", "%"+obj+"%");
			}else if("EQ_countyid".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj)){
				query.setParameter("countyid", Integer.parseInt(obj));
				countQuery.setParameter("countyid", Integer.parseInt(obj));
			}else if("LIKE_jobLabel".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj)){
				String splitLable[] = obj.split(",");
				StringBuffer labelHql = new StringBuffer( " and ( 1=1 ");
				for(String str : splitLable){
					labelHql.append(" and job.jobLabel like '%"+str+"%' ");
				}
				labelHql.append(")");
				hql.append(labelHql);
			}else if("LIKE_totalsalary".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj) && obj.split("-").length == 2){
				String[] salarys = obj.split("-");//高低薪资
				if(salarys.length >1 && StringUtils.isNumeric(salarys[0])&&StringUtils.isNumeric(salarys[1])){
					Integer salaryfrom = Integer.parseInt(salarys[0]);
					Integer salaryto = Integer.parseInt(salarys[1]);
					query.setParameter("salaryto", salaryto);
					countQuery.setParameter("salaryto", salaryto);
					query.setParameter("salaryfrom", salaryfrom);
					countQuery.setParameter("salaryfrom", salaryfrom);
				}
			}
		}
		if(branchId!=null && cityIdList.size()>0){
			query.setParameter("cityList", cityIdList);
			countQuery.setParameter("cityList", cityIdList);
		}
		Long totalSize = (Long) countQuery.getSingleResult();
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		query.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		query.setMaxResults(pageRequest.getPageSize());
		Page<JobBase> page = new PageImpl<JobBase>(query.getResultList(), pageRequest, totalSize);
		return page;
	}
	
	/**
	 *  拼装hql语句查询page对象
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @param branchId 分站Id
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public Page<JobBase> getHqlPageByAjax( int pageNumber, int pageSize, String sortType, Integer branchId,String jobType,String subJobType,
			String salary,String otherCity,String districts,String labels){
			//临时处理昆山的情况
			if(StringUtils.isNotBlank(otherCity) && otherCity.equals(346)){
				otherCity = "78";
				districts = "785";
			}
			StringBuffer hql = new StringBuffer("select job from JobBase job where 1 = 1 ");
			if(StringUtils.isNotBlank(labels)){
				String splitLable[] = labels.split(",");
				StringBuffer labelHql = new StringBuffer( " and ( 1=1 ");
				for(String str : splitLable){
					labelHql.append(" and job.jobLabel like '%"+str+"%' ");
				}
				labelHql.append(")");
				hql.append(labelHql);
			}
			if(StringUtils.isNotBlank(otherCity)){
				hql.append(" and job.city.id = :cityid ");
			}
			if(StringUtils.isNotBlank(jobType) && StringUtils.isBlank(subJobType)){
				hql.append(" and job.jobType like :jobType");
			}
			if(StringUtils.isNotBlank(subJobType)){
				hql.append(" and job.jobType like :subJobType");
			}
			if(StringUtils.isNotBlank(districts)){
				hql.append(" and job.countyid = :countyid");
			}
			if(StringUtils.isNotBlank(salary)){
				String[] salarys = salary.split("-");//高低薪资
				if(StringUtils.isNumeric(salarys[0])){
					hql.append(" and job.jobDetail.salaryfrom >= :salaryfrom ");
				}
				if(salarys.length>1&&StringUtils.isNumeric(salarys[1])){
					hql.append(" and job.jobDetail.salaryto <= :salaryto ");
				}
			}
		List<Integer> cityIdList = findBranchCityIdList(branchId);
		
		if(branchId!=null && cityIdList.size()>0){
			hql.append(" and job.city.id in :cityList ");
		}
		hql.append(" order by job.jobConfig.isUrgency desc, job.city.id ");
		if ("auto".equals(sortType)) {
			hql.append(",job.id asc ");
		} else if ("updatetime".equals(sortType)) {
			hql.append(",job.updatetime desc ");
		} else if("salaryasc".equals(sortType)){
			hql.append(",job.jobDetail.salaryfrom asc ");
		}else if("salarydesc".equals(sortType)){
			hql.append(",job.jobDetail.salaryfrom desc ");
		}
		Query countQuery = em.createQuery(hql.toString().replace("select job from", "select count(1) from"));
		Query query = em.createQuery(hql.toString());
		
		 if(StringUtils.isNotBlank(otherCity)){
			query.setParameter("cityid", Integer.parseInt(otherCity));
			countQuery.setParameter("cityid", Integer.parseInt(otherCity));
		}
		 if(StringUtils.isNotBlank(jobType) && StringUtils.isBlank(subJobType)){
			query.setParameter("jobType", "%"+jobType+"%");
			countQuery.setParameter("jobType", "%"+jobType+"%");
		}
		 if(StringUtils.isNotBlank(jobType)){
				query.setParameter("subJobType", "%"+jobType+"%");
				countQuery.setParameter("subJobType", "%"+jobType+"%");
			}
		 if(StringUtils.isNotBlank(districts)){
			query.setParameter("countyid", Integer.parseInt(districts));
			countQuery.setParameter("countyid", Integer.parseInt(districts));
		}
		if(StringUtils.isNotBlank(salary)){
			String[] salarys = salary.split("-");//高低薪资
			if(StringUtils.isNumeric(salarys[0])){
				Integer salaryfrom = Integer.parseInt(salarys[0]);
				hql.append(" and job.jobDetail.salaryfrom >= :salaryfrom ");
				query.setParameter("salaryfrom", salaryfrom);
				countQuery.setParameter("salaryfrom", salaryfrom);
			}
			if(salarys.length >1 && StringUtils.isNumeric(salarys[1])){
				Integer salaryto = Integer.parseInt(salarys[1]);
				hql.append("and job.jobDetail.salaryto <= :salaryto ");
				query.setParameter("salaryto", salaryto);
				countQuery.setParameter("salaryto", salaryto);
			}
		}
		if(branchId!=null && cityIdList.size()>0){
			query.setParameter("cityList", cityIdList);
			countQuery.setParameter("cityList", cityIdList);
		}
		Long totalSize = (Long) countQuery.getSingleResult();
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		query.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		query.setMaxResults(pageRequest.getPageSize());
		Page<JobBase> page = new PageImpl<JobBase>(query.getResultList(), pageRequest, totalSize);
		return page;
	}
	/**
	 * 岗位查询走lucene
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @param branchId
	 * @return
	 * @throws InvalidTokenOffsetsException 
	 */
	public Page<CompanyListVo> search(Map<String, Object> searchParams,int pageNumber, int pageSize, String sortType, Integer branchId){
   		//开始查询
		return jobSeachLuceneService.getJobListByLucene(searchParams,pageNumber,pageSize,sortType,getDefaultCityId(branchId));
	}
	
	/**
	 * 根据所选分站得到主城市
	 * @param branchId
	 * @return
	 */
	public String getDefaultCityId(Integer branchId){
		Branch branch = branchDao.findOne(branchId);
		String cityId=null;
		if(branch != null){
			cityId=branch.getDefaultCityId()+"";
		}
		return cityId;
	}
	
	
	/**
	 * 根据公司Id得到最近发布的工作
	 * @param companyId
	 * @param type
	 * @return
	 */
	public JobBase getJobBaseByCompanyIdOrderByCreatetime(Integer companyId) {
		List<JobBase> jobList = jobBaseDao.findJobBaseByCompanyIdOrderByCreatetime(companyId);
		if(jobList!=null &&jobList.size()>0){
			return jobList.get(0);
		}
		return new JobBase();
	}
	/**
	 * 根据公司Id 工作类型得到最近发布的工作
	 * @param companyId
	 * @param type
	 * @return
	 */
	public JobBase getJobBaseByCompanyIdAndJobTypeOrderByCreatetime(Integer companyId) {
		List<JobBase> jobList = jobBaseDao.findJobBaseByCompanyIdOrderByCreatetime(companyId);
		if(jobList!=null &&jobList.size()>0){
			return jobList.get(0);
		}
		return new JobBase();
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
		} else if ("updatetime".equals(sortType)) {
			sort = new Sort(Direction.DESC, "updatetime");
		} else if("salaryasc".equals(sortType)){
			sort = new Sort(Direction.ASC, "jobDetail.salaryfrom");
		}else if("salarydesc".equals(sortType)){
			sort = new Sort(Direction.DESC, "jobDetail.salaryfrom");
		}else if("applycount".equals(sortType))
		{
			sort = new Sort(Direction.DESC, "applycount");
		}
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}
	/**
	 * 封装recommengJob vo 对象
	 */
	public List<RecommendJobVo> buildRecommendJobVO(List<JobBase> jobBaseList){
		List<RecommendJobVo> recommendJobVos = new ArrayList<RecommendJobVo>();
		for(JobBase jobBase:jobBaseList){
			RecommendJobVo recommendJobEntity = new RecommendJobVo(jobBase.getId(),
				jobBase.getTitle(), 
				(Integer) (jobBase.getJobConfig()==null?null:jobBase.getJobConfig().getIsUrgency()), 
				null, 
				jobBase.getId(),
				jobBase.getTotalsalary(), 
				(jobBase.getJobDetail()==null||jobBase.getJobDetail().getShowApplyCount()==null)?0:jobBase.getJobDetail().getShowApplyCount(), 
				jobBase.getJobname(),
				jobBase.getCity()==null?null:jobBase.getCity().getAbbreviation(),
				jobBase.getThumbnialImage()==null?"":jobBase.getThumbnialImage().getImgpath(),
				jobBase.getCompany()==null?"":jobBase.getCompany().getName(),
				jobBase.getCompany()==null?"":jobBase.getCompany().getAbbreviation(),
				jobBase.getTitle(),
				jobBase.getCompany()==null?null:jobBase.getCompany().getId(),
				(jobBase.getCompany()==null||jobBase.getCompany().getLogo()==null)?"":jobBase.getCompany().getLogo().getImgpath(),
				jobBase.getJobDetail()==null?null:jobBase.getJobDetail().getSalaryfrom(),
				jobBase.getJobDetail()==null?null:jobBase.getJobDetail().getSalaryto(),
				jobBase.getJobType(),
				jobBase.getCountyid(),
				jobBase.getJobLabel());
			recommendJobVos.add(recommendJobEntity);
		}
		return recommendJobVos;
	}
	/**
	 * 修改发布在招状态
	 * @param jobid
	 * @param isRecruitment
	 */
	public int setIsRecruitment(Integer jobid, Integer isRecruitment) {
		int resule= jobConfigDao.updateIsRecruitment(isRecruitment,jobid);
		if(resule==1)
		{
			resule = jobConfigDao.updateUptatetime(DateConvertUtils.getNow(), jobid);
		}
		return resule;
	}
	/**
	 * 单独修改岗位缩略图
	 * @param logo
	 * @param companyid
	 */
	public void uploadLogo(Image thumbnialImage, Integer jobid) {
		JobBase jobBase = jobBaseDao.findOne(jobid);
		if(jobBase!=null)
		{
			jobBase.setThumbnialImage(thumbnialImage);
			jobBaseDao.save(jobBase);		
		}
	}
	/**
	 * 企业用户发布职位
	 * @param id
	 * @param job
	 */
	public void saveEnterpriseJob(Integer userid,Company company, JobBase job) {
		JobBase jobBase =null;
		JobDetail jobDetail=null;
		JobConfig jobConfig=null;
		if (job.getId() == null) {
			jobBase = new JobBase();
			jobBase.setCreateUserId(userid);
			jobBase.setCreatetime(DateConvertUtils.getNow());
			jobDetail=new JobDetail();
			jobConfig=new JobConfig();
		} else {
			jobBase=getByIdAndCreateUserId(job.getId(),userid);
			jobDetail=jobBase.getJobDetail();
			jobConfig=jobBase.getJobConfig();
		}
		//保存jobbase
		jobBase.setCooperationType(2);//数据来源类型企业自录
		jobBase.setCompany(company);
		jobBase.setTitle(HTMLInputFilter.clean(job.getTitle()));
		jobBase.setJobname(HTMLInputFilter.clean(job.getTitle()));
		jobBase.setJobType(HTMLInputFilter.clean(job.getJobType()));
		jobBase.setRecruitcount(job.getRecruitcount());
		jobBase.setProvinceid(job.getProvinceid());
		if (job.getCity().getId() != null) {
			jobBase.setCity(codeAreaCityDao.findOne(job.getCity().getId()));
		}
		jobBase.setCountyid(job.getCountyid());
		if (job.getJobDetail().getSalaryfrom()!=null&&job.getJobDetail().getSalaryfrom()>0&&job.getJobDetail().getSalaryto() != null && job.getJobDetail().getSalaryto() > 0) {
			jobBase.setTotalsalary(HTMLInputFilter.clean(job.getJobDetail().getSalaryfrom()+"-"+job.getJobDetail().getSalaryto()+"元"));
		}else if(job.getJobDetail().getSalaryfrom()!=null&&job.getJobDetail().getSalaryfrom()>0){
			jobBase.setTotalsalary(HTMLInputFilter.clean(job.getJobDetail().getSalaryfrom()+"元以上"));
		}
		jobBase.setAddress(HTMLInputFilter.clean(job.getAddress()));
		jobBase.setJobLabel(HTMLInputFilter.clean(job.getJobLabel()));
		if (job.getThumbnialImage().getId() != null) {
			jobBase.setThumbnialImage(imageDao.findOne(job.getThumbnialImage().getId()));
		}
		jobBase.setUpdatetime(DateConvertUtils.getNow());
		jobBase.setUpdateUserId(userid);
		jobBaseDao.save(jobBase);
		//保存jobdetail
		jobDetail.setJobBase(jobBase);
		jobDetail.setSalaryfrom(job.getJobDetail().getSalaryfrom());
		jobDetail.setSalaryto(job.getJobDetail().getSalaryto());
		jobDetail.setEducation(job.getJobDetail().getEducation());
		if (job.getJobDetail().getWorkExpFrom() > 0) {
			jobDetail.setWorkExpFrom(job.getJobDetail().getWorkExpFrom());
		}
		jobDetail.setGender(job.getJobDetail().getGender());
		jobDetail.setAgefrom(job.getJobDetail().getAgefrom());
		jobDetail.setAgeto(job.getJobDetail().getAgeto());
		jobDetail.setSalarydesc(job.getJobDetail().getSalarydesc());
		jobDetail.setWorkdesc(job.getJobDetail().getWorkdesc());
		jobDetail.setDemanddesc(job.getJobDetail().getDemanddesc());
		jobDetail.setMealsdesc(job.getJobDetail().getMealsdesc());
		jobDetail.setWelfaredesc(job.getJobDetail().getWelfaredesc());
		jobDetailDao.save(jobDetail);
		//保存jobconfig
		jobConfig.setJobBase(jobBase);
		jobConfig.setIsPublish(-1);//未审核
		jobConfig.setIsRecruitment(0);//在招
		jobConfigDao.save(jobConfig);
	}
	/**
	 * 岗位实景图
	 * @param jobBase
	 * @return
	 */
	public List<Image> getCompanySence(JobBase jobBase) {
		List<Image> imageList = new ArrayList<Image>();
		if(jobBase.getThumbnialImage()!=null && StringUtils.isNotBlank(jobBase.getThumbnialImage().getImgpath())){
			imageList.add(jobBase.getThumbnialImage());
		}
		if(jobBase.getCompany()!=null){
			for(CompanyScene scene:jobBase.getCompany().getScenes()){
				if(scene.getIsshow()==1 && scene.getImage()!=null && StringUtils.isNotBlank(scene.getImage().getImgpath())){
					Image image = scene.getImage();
					image.setComment(scene.getTitle());
					imageList.add(image);
				}
			}
		}
		return imageList;
	}
	
	/**
	 * 根据所选分站查询城市列表
	 * @param branchId
	 * @return
	 */
	public List<Integer> findBranchCityIdList(Integer branchId){
		List<Integer> cityList = new ArrayList<Integer>();
		Branch branch = branchDao.findOne(branchId);
		if(branch != null && branch.getBranchCityList()!=null){
			List<BranchCity> bcityList = branch.getBranchCityList();
			for (BranchCity branchCity : bcityList) {
				cityList.add(branchCity.getCityId());
			}
		}
		return cityList;
	}
	
	public Page<JobBase> getPage(Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<JobBase> spec = buildSpecification(searchParams);
		return jobBaseDao.findAll(spec, pageRequest);
	}
	
	/**
	 * 获取1千条职位信息 百度轻应用使用
	 * @return
	 */
	public Page<JobBase> getPage() {
		PageRequest pageRequest = buildPageRequest(1, 100,"auto");
		Specification<JobBase> spec = null;
		spec= buildSpecification(new HashMap<String, Object>());
		return jobBaseDao.findAll(spec, pageRequest);
	}
	
	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<JobBase> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<JobBase> spec = DynamicSpecifications.bySearchFilter(filters.values(), JobBase.class);
		return spec;
	}
	
	@Autowired
	public void setJobBaseDao(JobBaseDao jobBaseDao) {
		this.jobBaseDao = jobBaseDao;
	}
	
	@Autowired
	public void setJobConfigDao(JobConfigDao jobConfigDao) {
		this.jobConfigDao = jobConfigDao;
	}
	@Autowired
	public void setCodeAreaCityDao(CodeAreaCityDao codeAreaCityDao) {
		this.codeAreaCityDao = codeAreaCityDao;
	}
	
	@Autowired
	public void setJobDetailDao(JobDetailDao jobDetailDao) {
		this.jobDetailDao = jobDetailDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Autowired
	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}
	@Autowired
	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}
	@Autowired
	public void setRecommendJobService(RecommendJobService recommendJobService) {
		this.recommendJobService = recommendJobService;
	}
	@Autowired
	public void setJobSeachLuceneService(JobSeachLuceneService jobSeachLuceneService) {
		this.jobSeachLuceneService = jobSeachLuceneService;
	}
	@Autowired
	public void setCodeAreaDistrictDao(CodeAreaDistrictDao codeAreaDistrictDao) {
		this.codeAreaDistrictDao = codeAreaDistrictDao;
	}
	@Autowired
	public void setCodeAreaProvinceDao(CodeAreaProvinceDao codeAreaProvinceDao) {
		this.codeAreaProvinceDao = codeAreaProvinceDao;
	}
	/**
	 * 从缓存中随机取出30个热词
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Element> findHotWords() {
		List<Element> hotWordList = new ArrayList<Element>();
		List<Element> hotWordCachList=null;
		Object hotWordCach = StoreCacheUtil.getCacheValue("findhotWordCachList", "hotWordCachList");
		if (hotWordCach == null || ((List<Object>) hotWordCach).size() == 0) {// 从缓存中取数据，看是否有值
			try {
				hotWordCachList = XMLUtil.readXMLByElementName(JobBaseService.class.getClassLoader().getResource("").getPath() + "hotwords.xml", "word");
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StoreCacheUtil.putCache("findhotWordCachList", "hotWordCachList", hotWordCachList);
		} else {// 有值
			hotWordCachList = (List<Element>) StoreCacheUtil.getCacheValue("findhotWordCachList", "hotWordCachList");
		}
		if (hotWordCachList != null && hotWordCachList.size() != 0) {
			Random random = new Random();
			int start = 0;// 产生从0 到总数减去size的随机数
			int end=0;
			if(hotWordCachList.size()>30){
				int max =hotWordCachList.size() - 30;
				start = random.nextInt(max);
				end=start + 29;
			}else{
				end=hotWordCachList.size()-1;
			}
			for (int i = start; i <= end; i++) {// 从随机数开始取size个数据返回
				Element element=hotWordCachList.get(i);	
				hotWordList.add(element);
			}
		}
		return hotWordList;
	}
	public JobBase getNextJob(Integer id) {
		JobBase jobBase = jobBaseDao.findOne(id);
		if(jobBase==null){
			return null;
		}
		Integer cityId = jobBase.getCity()==null?0:jobBase.getCity().getId();
		StringBuffer jpql = new StringBuffer("select jobBase from JobBase jobBase where jobBase.city.id = :cityId  and jobBase.jobConfig.isPublish = 1 and  jobBase.createtime < :now ORDER BY jobBase.createtime  DESC ");
		Query query = em.createQuery(jpql.toString());
			query.setParameter("cityId",cityId);
			query.setParameter("now",jobBase.getCreatetime());
		query.setFirstResult(0);
		query.setMaxResults(1);
		List<JobBase> jobList = query.getResultList();
		return  jobList.size() > 0 ? jobList.get(0) : null;
	/*	Page<JobBase> jobList = jobBaseDao.getNextCompany(cityId,jobBase.getCreatetime(),buildPageRequest(1, 1, "id"));
		if(jobList.getContent().size()<=0){
			jobList = jobBaseDao.getLastJobBase(cityId, buildPageRequest(1, 1, "id"));
		}
		return jobList.getContent().size() > 0 ? jobList.getContent().get(0) : null;*/
	}
	/**
	 * 获得分页岗位列表
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<JobBase> getPage(Integer pageNumber,Integer pageSize) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		// 查询条件 GT 大于 ,LT 小于,GTE 大于等于, LTE 小于等于,EQ 等于, LIKE 包含
		searchParams.put("EQ_jobConfig.isDel", 1+"");//未删除的
		searchParams.put("EQ_jobConfig.isPublish", 1+"");//发布状态为发布的
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto");
		Specification<JobBase> spec =buildSpecification(searchParams);
		return jobBaseDao.findAll(spec, pageRequest);
	}
	
	
	/**
	 * 根据删除和发布状态查询岗位id
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Integer> findJobIdPage(Integer pageNumber,Integer pageSize) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto");
		return jobBaseDao.findIdByIsDelAndIsPublish(1,1,pageRequest);
	}
	/**
	 * 根据删除和发布状态查询岗位id和分站前缀
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<String> findJobIdAndWebPrefixPage(Integer pageNumber,Integer pageSize) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto");
		return jobBaseDao.findIdAndWebByIsDelAndIsPublish(1,1,pageRequest);
	}
	/**
	 * 根据岗位信息取得相似岗位(根据相同地区，相同岗位类别，发布时间最新，工资最相近的招聘信息数据。)
	 * @param pageNumber
	 * @param pageSize
	 * @param jobBase
	 * @return
	 * @author jack
	 */
	@SuppressWarnings("unchecked")
	public List<JobBase> findLikeJobs(JobBase jobBase,String status) {
		if(jobBase!=null){
			String jobType = jobBase.getJobType();
			StringBuffer strSql = new StringBuffer("select job from JobBase job where job.jobConfig.isPublish = 1 AND job.jobConfig.isRecruitment = 0");
			strSql.append(" and job.city.id = :cityId ");
			if(status != null && status.equals("most")){
				if(StringUtils.isNotEmpty(jobType)&&jobType.split(",").length > 0){
					strSql.append(" and job.jobType like :jobType ");
				}
				strSql.append(" and job.id != :jobId ");
				strSql.append(" order by job.applycount DESC");
				strSql.append(",job.updatetime DESC");
			}else if(status != null && status.equals("new")){
				strSql.append(" order by job.updatetime DESC");
			}else{
				if(StringUtils.isNotEmpty(jobType)&&jobType.split(",").length > 0){
					strSql.append(" and (job.cooperationType = 1 or job.cooperationType = 5) ");
					strSql.append(" and job.jobType like :jobType ");
				}
				strSql.append(" and job.id != :jobId ");
				strSql.append(" order by job.jobConfig.isUrgency DESC");
				strSql.append(",job.updatetime DESC");
			}
			Query query = em.createQuery(strSql.toString());
			query.setParameter("cityId", jobBase.getCity().getId());
			if(status == null || status.equals("most")){
				if(StringUtils.isNotEmpty(jobType)&&jobType.split(",").length>0){
					query.setParameter("jobType", "%"+jobBase.getJobType().split(",")[0]+"%");
				}
				query.setParameter("jobId", jobBase.getId());
			}
			query.setMaxResults(5);
			return query.getResultList();
		}
		return null;
	}
	
	/**
	 * 根据企业id得到企业下岗位的数量
	 * @param companyId
	 * @return
	 */
	public Integer countByCompanyId(Integer companyId) {
		return jobBaseDao.countByCompanyIdAndJobConfigIsPublish(companyId,1);
	}
}
