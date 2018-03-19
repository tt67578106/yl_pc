package com.ylw.repository.advert;

/**
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2015-7-16 19:56:28 
 */


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.advert.AdvertAreaPosition;

public interface AdvertAreaPositionDao extends PagingAndSortingRepository<AdvertAreaPosition,java.lang.Integer>, JpaSpecificationExecutor<AdvertAreaPosition>{

	@Query("select advertAreaPosition from AdvertAreaPosition advertAreaPosition where advertAreaPosition.status = 1 and advertAreaPosition.branchId = :branchId order by advertAreaPosition.sorting ASC  ")
	Page<AdvertAreaPosition> findByBranchId(@Param("branchId")Integer branchId,Pageable page);

}
