package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.base.RecommendJob;

public interface RecommendJobDao extends PagingAndSortingRepository<RecommendJob,java.lang.Integer>, JpaSpecificationExecutor<RecommendJob>{

	@Query("select recommendJob from RecommendJob recommendJob where recommendJob.isPublish = :isPublish and recommendJob.recommendPositionCode = :recommendPositionCode "
			+ "and recommendJob.branch.id  = :branchId   and (recommendJob.offlineTime is null or recommendJob.offlineTime >= :now) order by recommendJob.sorting ASC")
	Page<RecommendJob> findByIsPublishAndRecommendPositionCodeAndOfflineTimeGreaterThan(@Param("isPublish") Integer isPublish,@Param("recommendPositionCode") String recommendPositionCode,@Param("now") Date now,@Param("branchId") Integer branchId,Pageable page);
}
