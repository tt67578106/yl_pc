package com.ylw.repository.recruitment;
/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.recruitment.Recruitment;

public interface RecruitmentDao extends PagingAndSortingRepository<Recruitment,java.lang.Integer>, JpaSpecificationExecutor<Recruitment>{

	Recruitment getByRecruitCodeAndIsBaidupro(String recruitCode,Integer isBaidupro);

}
