package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.code.CodeAreaDistrict;

public interface CodeAreaDistrictDao extends PagingAndSortingRepository<CodeAreaDistrict,java.lang.Integer>, JpaSpecificationExecutor<CodeAreaDistrict>{

	List<CodeAreaDistrict> findByStatus(int i);

}
