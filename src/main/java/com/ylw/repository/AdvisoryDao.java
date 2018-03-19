package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.base.Advisory;

public interface AdvisoryDao extends PagingAndSortingRepository<Advisory,java.lang.Integer>, JpaSpecificationExecutor<Advisory>{

}
