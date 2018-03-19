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

import com.ylw.entity.base.RecommendAd;

public interface RecommendAdDao extends PagingAndSortingRepository<RecommendAd,java.lang.Integer>, JpaSpecificationExecutor<RecommendAd>{

	@Query("select recommendAd from RecommendAd recommendAd where recommendAd.isPublish = :isPublish and recommendAd.recommendPositionCode = :recommendPositionCode "
			+ "and (recommendAd.offlineTime is null or recommendAd.offlineTime >= :now)  AND recommendAd.branchId = :branchId order by "
			+ " recommendAd.sorting ASC, recommendAd.publishTime DESC ")
	Page<RecommendAd> findByIsPublishAndRecommendPositionCodeAndOfflineTimeGreaterThan(@Param("isPublish")Integer isPublish,@Param("recommendPositionCode")String recommendPositionCode,@Param("now") Date now,@Param("branchId") Integer branchId,Pageable page);

	@Query("select recommendAd from RecommendAd recommendAd where recommendAd.isPublish = :isPublish and recommendAd.recommendPositionCode = :recommendPositionCode "
			+ "and (recommendAd.offlineTime is null or recommendAd.offlineTime >= :now)   order by "
			+ " recommendAd.sorting ASC, recommendAd.publishTime DESC ")
	Page<RecommendAd> findByIsPublishAndRecommendPositionCodeAndOfflineTimeGreaterThan(@Param("isPublish")Integer isPublish, @Param("recommendPositionCode")String cacheKeyWanlixingType, @Param("now")Date now,Pageable buildPageRequest);

}
