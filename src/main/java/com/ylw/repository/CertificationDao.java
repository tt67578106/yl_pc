package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.enterprise.Certification;

public interface CertificationDao extends PagingAndSortingRepository<Certification,java.lang.Integer>, JpaSpecificationExecutor<Certification>{

	Certification getByCompanyId(Integer companyId);

	@Modifying
	@Query(" update Certification set status=3 where id=:certificationId")
	Integer updateStatusById(@Param("certificationId")Integer certificationId);
}
