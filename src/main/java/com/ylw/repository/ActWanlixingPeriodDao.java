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

import com.ylw.entity.wanlixing.ActWanlixingPeriod;

public interface ActWanlixingPeriodDao extends PagingAndSortingRepository<ActWanlixingPeriod,java.lang.Integer>, JpaSpecificationExecutor<ActWanlixingPeriod>{

	@Query("select awp.provinceId from ActWanlixingPeriod awp where awp.isPublish = 1 and awp.provinceId is not null ")
	List<Integer> findByisPublish();

	List<ActWanlixingPeriod> findByisPublishAndProvinceId(Integer isPublish,Integer provinceId);

}
