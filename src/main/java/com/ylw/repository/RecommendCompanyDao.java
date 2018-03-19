package com.ylw.repository;

/**
 * @author Nicolas.Cai
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

import com.ylw.entity.base.RecommendCompany;

public interface RecommendCompanyDao extends PagingAndSortingRepository<RecommendCompany,java.lang.Integer>, JpaSpecificationExecutor<RecommendCompany>{

	@Query("select recommendCompany from RecommendCompany recommendCompany "
			+ "where recommendCompany.isPublish = :isPublish "
			+ "and recommendCompany.recommendPositionCode = :positionCodeCompanyRecommendListCode "
			+ "and recommendCompany.branchId  = :branchId "
			+ "and (recommendCompany.offlineTime is null or recommendCompany.offlineTime >= :now)")
	Page<RecommendCompany> findByIsPublishAndRecommendPositionCodeAndOfflineTimeGreaterThan(
			@Param("isPublish")Integer isPublish,@Param("positionCodeCompanyRecommendListCode")String positionCodeCompanyRecommendListCode,@Param("now") Date now,@Param("branchId")Integer branchId, Pageable page);

}
