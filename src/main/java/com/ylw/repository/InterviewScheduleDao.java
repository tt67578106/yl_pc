package com.ylw.repository;

/**
 * @author Jack
 * @version 1.0.0
 * @since 1.0.0
 * @date 2017-7-12 15:09:23 
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.job.InterviewSchedule;

public interface InterviewScheduleDao extends PagingAndSortingRepository<InterviewSchedule,java.lang.Integer>, JpaSpecificationExecutor<InterviewSchedule>{
	/**
	 * 根据岗位id查找排期
	 * @param jobId
	 * @return
	 */
	@Query("select interviewSchedule from InterviewSchedule interviewSchedule,JobConfig jobConfig where interviewSchedule.ylJobBaseId = jobConfig.jobBase.id and jobConfig.isRecruitment = 0 and interviewSchedule.ylJobBaseId = :jobId"
			
			+ " and interviewSchedule.endTime >= SYSDATE() order by interviewSchedule.startTime ASC ")
	List<InterviewSchedule> findScheduleByJobId(@Param("jobId")Integer jobId);

}
