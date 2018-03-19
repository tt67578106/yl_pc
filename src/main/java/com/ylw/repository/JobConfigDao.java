package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.Date;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.job.JobConfig;

public interface JobConfigDao extends PagingAndSortingRepository<JobConfig,java.lang.Integer>, JpaSpecificationExecutor<JobConfig>{

	@Modifying
	@Query("update JobConfig set isRecruitment=:isRecruitment where jobBase.id=:jobid")
	int updateIsRecruitment(@Param("isRecruitment")Integer isRecruitment,@Param("jobid")Integer jobid);
	
	@Modifying
	@Query("update JobBase set updatetime=:updatetime where id=:jobid")
	int updateUptatetime(@Param("updatetime")Date updatetime,@Param("jobid")Integer jobid);

}
