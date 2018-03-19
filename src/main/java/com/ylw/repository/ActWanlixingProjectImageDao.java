package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.wanlixing.ActWanlixingProjectImage;

public interface ActWanlixingProjectImageDao extends PagingAndSortingRepository<ActWanlixingProjectImage,java.lang.Integer>, JpaSpecificationExecutor<ActWanlixingProjectImage>{

	List<ActWanlixingProjectImage> findByprojectIdAndImageTypeOrderByCreateTimeDesc(Integer id,int type);

	Integer deleteByProjectId(Integer id);

	List<ActWanlixingProjectImage> findByprojectIdAndImageTypeOrderBySortingAsc(Integer id, int type);

}
