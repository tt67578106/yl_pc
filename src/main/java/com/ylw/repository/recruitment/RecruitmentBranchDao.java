package com.ylw.repository.recruitment;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.recruitment.RecruitmentBranch;

public interface RecruitmentBranchDao extends PagingAndSortingRepository<RecruitmentBranch,java.lang.Integer>, JpaSpecificationExecutor<RecruitmentBranch>{

	RecruitmentBranch getByBranchId(Integer branchId);

	List<RecruitmentBranch> findByRecruitId(Integer recruitId);

}
