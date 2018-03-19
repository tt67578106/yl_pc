package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.code.CodeAreaProvince;

public interface CodeAreaProvinceDao extends PagingAndSortingRepository<CodeAreaProvince,java.lang.Integer>, JpaSpecificationExecutor<CodeAreaProvince>{


	@Query("from CodeAreaProvince cap where cap.status = :status and cap.id in :awpList ")
	Page<CodeAreaProvince> findByIdInAndStatus(@Param("awpList") List<Integer> awpList,@Param("status") Integer status,Pageable page);

	List<CodeAreaProvince> findByStatus(int i);
}
