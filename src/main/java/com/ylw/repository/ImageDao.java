package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.base.Image;

public interface ImageDao extends PagingAndSortingRepository<Image,java.lang.Integer>, JpaSpecificationExecutor<Image>{

	List<Image> findByType(int type);

}
