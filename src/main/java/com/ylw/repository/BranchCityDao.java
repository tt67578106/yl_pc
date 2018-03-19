package com.ylw.repository;

/**
 * @author Nicolas.Cai
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.base.BranchCity;

public interface BranchCityDao extends PagingAndSortingRepository<BranchCity,java.lang.Integer>, JpaSpecificationExecutor<BranchCity>{
	/**
	 * 通过分站查询所有城市列表 按sorting排序
	 * @param branchId
	 * @return
	 */
	public List<BranchCity> findByBranchIdOrderBySortingAsc(Integer branchId);
	/**
	 *  通过分站查询所有城市列表 按sorting排序,排除指定城市
	 * @param branchId
	 * @param cityId
	 * @return
	 */
	public List<BranchCity> findByBranchIdAndCityIdNotOrderBySortingAsc(Integer branchId,Integer cityId);
}
