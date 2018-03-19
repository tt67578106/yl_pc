package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.job.JobApplyFlowLog;

public interface JobApplyFlowLogDao extends PagingAndSortingRepository<JobApplyFlowLog,java.lang.Integer>, JpaSpecificationExecutor<JobApplyFlowLog>{

	List<JobApplyFlowLog> findByJobApplyId(Integer applyId);

}
