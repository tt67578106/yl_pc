package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.base.Resume;

public interface ResumeDao extends PagingAndSortingRepository<Resume,java.lang.Integer>, JpaSpecificationExecutor<Resume>{

//	List<Resume> findByUserid(int userId);
//
	int countByMobile(String mobile);
}
