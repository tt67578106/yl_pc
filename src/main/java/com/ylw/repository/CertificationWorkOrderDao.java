package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.enterprise.CertificationWorkOrder;

public interface CertificationWorkOrderDao extends PagingAndSortingRepository<CertificationWorkOrder,java.lang.Integer>, JpaSpecificationExecutor<CertificationWorkOrder>{

	List<CertificationWorkOrder> findByCertificationIdAndStatusOrderByCreateTimeDesc(Integer certificationId,Integer status);
}
