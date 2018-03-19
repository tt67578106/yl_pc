package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.enterprise.CompanyResumeBox;

public interface CompanyResumeBoxDao extends PagingAndSortingRepository<CompanyResumeBox,java.lang.Integer>, JpaSpecificationExecutor<CompanyResumeBox>{
	
	@Modifying
	@Query("delete from CompanyResumeBox where id in (:idList) ")
	Integer deleteById(@Param("idList")List<Integer> idList);

}
