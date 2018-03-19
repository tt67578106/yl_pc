package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.base.CompanyScene;

public interface CompanySceneDao extends PagingAndSortingRepository<CompanyScene,java.lang.Integer>, JpaSpecificationExecutor<CompanyScene>{

	Page<CompanyScene> findByCompanyIdAndIsshow(Integer companyId,Integer isshow,Pageable page);
	/**
	 * 跟进相册类别获取企业实景图
	 * @param category
	 * @return
	 */
	List<CompanyScene> findByCompanyIdAndCategoryAndIsshow(Integer companyid,Integer category,Integer isshow);
	
	/**
	 * 根据企业ID获取企业实景图
	 * @param companyid
	 * @return
	 */
	List<CompanyScene> findByCompanyIdAndIsshow(Integer companyid,Integer isshow);
	/**
	 * 统计每一种相册分类的数量
	 * @param companyid
	 * @param category
	 * @param isshow
	 * @return
	 */
	Integer countByCompanyIdAndCategoryAndIsshow(Integer companyid,Integer category,Integer isshow);
	
	/**
	 * 设置企业实景图(1显示/2不显示)
	 * @param id
	 * @param isshow
	 * @return
	 */
	@Modifying
	@Query("update CompanyScene set isshow = :isshow where id = :id ")
	int updateIsShow(@Param("id")Integer id,@Param("isshow")Integer isshow);
}
