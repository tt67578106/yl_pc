package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.job.JobBase;

public interface JobBaseDao extends PagingAndSortingRepository<JobBase,java.lang.Integer>, JpaSpecificationExecutor<JobBase>{
	
	/**
	 * 根据发布状态和id查询数据
	 * @param isPublish
	 * @param id
	 * @return
	 */
	public JobBase getByJobConfigIsPublishAndId(Integer isPublish,Integer id);
	
	/**
	 * 根据城市和发布状态查询【优蓝精选】
	 * @param isPublish
	 * @param yljxJobList
	 * @param cityList
	 * @param page
	 * @return
	 */
	@Query("select job from JobBase job where job.jobConfig.isPublish =:isPublish "
			+ " and (job.city.id in (:cityList) or job.countyid = :countyid) and job.id not in (:yljxJobList) group by job.company.id order by job.jobConfig.isUrgency desc,"
			+ " job.jobDetail.salaryfrom desc")
	Page<JobBase> getByIsPublishAndCity(@Param("isPublish") Integer isPublish,@Param("yljxJobList") List<Integer> yljxJobList,@Param("cityList") List<Integer> cityList,@Param("countyid")Integer countyid,Pageable page);

	/**
	 * 根据城市和发布状态查询
	 * @param isPublish
	 * 
	 * @param cityList
	 * @param page
	 * @return
	 */
	@Query("select job from JobBase job where job.cooperationType in(1,2,3,4,5) and job.jobConfig.isPublish =:isPublish "
			+ " and (job.city.id in :cityList or job.countyid = :countyid) order by (case job.cooperationType when 5 then 1 when 1 then 2 when 2 then 3 when 3 then 4 when 4 then 5 else job.cooperationType end)  ASC "
			+ ",job.jobConfig.isRecruitment ASC ,job.jobConfig.isUrgency DESC ,job.updatetime DESC ")
	Page<JobBase> getHotAndNewByIsPublishAndCity(@Param("isPublish") Integer isPublish,@Param("cityList") List<Integer> cityList,@Param("countyid") Integer countyid,Pageable page);

	/**
	 * 根据企业和岗位类型查询
	 * @param companyId
	 * @param type
	 * @return
	 */
	@Query("select job from JobBase job where job.jobConfig.isPublish = 1 and job.jobType  =:type "
			+ "and  job.company.id =:companyId order by job.createtime")
	public List<JobBase> findJobBaseByCompanyIdAndJobTypeOrderByCreatetime(@Param("companyId")Integer companyId, @Param("type")String type);
	/**
	 * 通过企业id按创建时间排序
	 * @param companyId
	 * @return
	 */
	@Query("select job from JobBase job where job.jobConfig.isPublish = 1 and  job.company.id =:companyId order by job.createtime")
	public List<JobBase> findJobBaseByCompanyIdOrderByCreatetime(@Param("companyId")Integer companyId);

	/**
	 * 通过id和创建人查询对象
	 * @param id
	 * @param userid
	 * @return
	 */
	public JobBase getByIdAndCreateUserId(Integer id, Integer userid);
	
	@Query("select jobBase from JobBase jobBase where jobBase.city.id = :cityId  and jobBase.jobConfig.isPublish = 1 "
			+ " and  jobBase.createtime < :now ORDER BY	jobBase.createtime  DESC")
	public Page<JobBase> getNextCompany(@Param("cityId")Integer cityId, @Param("now")Date createtime,
			Pageable buildPageRequest);

	@Query("select jobBase from JobBase jobBase where jobBase.city.id = :cityId  and jobBase.jobConfig.isPublish = 1 "
			+ " ORDER BY jobBase.createtime  DESC")
	public Page<JobBase> getLastJobBase(@Param("cityId")Integer cityId,Pageable buildPageRequest);

	/**
	 * 根据企业id得到企业下岗位的数量
	 * @param companyId
	 * @return
	 */
	public Integer countByCompanyIdAndJobConfigIsPublish(Integer companyId, int isPublish);

	/**
	 * 根据删除和发布状态查询岗位id
	 * @param isDel
	 * @param isPublish
	 * @param page
	 * @return
	 */
	@Query("select jobBase.id from JobBase jobBase where jobBase.jobConfig.isPublish = :isPublish and jobBase.jobConfig.isDel = :isDel ")
	public Page<Integer> findIdByIsDelAndIsPublish(@Param("isDel")Integer isDel, @Param("isPublish")Integer isPublish, Pageable page);

	@Query("select CONCAT(jobBase.id,'_',branch.webPrefix) from JobBase jobBase,Branch branch where jobBase.city.id = branch.defaultCityId and branch.isPublish = 2 and jobBase.jobConfig.isPublish = :isPublish and jobBase.jobConfig.isDel = :isDel ")
	public Page<String> findIdAndWebByIsDelAndIsPublish(@Param("isPublish")Integer isPublish, @Param("isDel")Integer isDel, Pageable page);
	
	@Query("select jobBase from JobBase jobBase where jobBase.id = :jobid ")
	public JobBase getByJobId(@Param("jobid")Integer jobid);

}
