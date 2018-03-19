package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.wanlixing.ActWanlixingGuest;

public interface ActWanlixingGuestDao extends PagingAndSortingRepository<ActWanlixingGuest,java.lang.Integer>, JpaSpecificationExecutor<ActWanlixingGuest>{

	@Query("select apg.actWanlixingGuestId from ActWanlixingPeriodGuest apg where apg.actWawnlixingPeriodId =:periodId")
	List<Integer> findByActWawnlixingPeriodId(@Param("periodId")Integer periodId);

	@Query("select awg from ActWanlixingGuest awg where awg.id in :awpgList and awg.status=1 ")
	List<ActWanlixingGuest> findByActWanlixingGuestIdInAndStatus(@Param("awpgList")List<Integer> awpgList);

}
