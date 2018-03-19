package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.wanlixing.ActWanlixingProject;

public interface ActWanlixingProjectDao extends PagingAndSortingRepository<ActWanlixingProject,java.lang.Integer>, JpaSpecificationExecutor<ActWanlixingProject>{

	List<ActWanlixingProject> findByCeoUserId(Integer userId);

}
