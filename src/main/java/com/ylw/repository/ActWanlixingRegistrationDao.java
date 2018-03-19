package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.wanlixing.ActWanlixingRegistration;

public interface ActWanlixingRegistrationDao extends PagingAndSortingRepository<ActWanlixingRegistration,java.lang.Integer>, JpaSpecificationExecutor<ActWanlixingRegistration>{

	List<ActWanlixingRegistration> findByUserId(Integer id);

}
