package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.sys.ApiApp;

public interface ApiAppDao extends PagingAndSortingRepository<ApiApp,java.lang.Long>, JpaSpecificationExecutor<ApiApp>{

	ApiApp getByAppKey(String appKey);
	
	@Query("select sourceCode from ApiApp where appKey = :appKey")
	String getSourceCodeByAppKey(@Param("appKey") String appKey);

}
