package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.code.CodeAreaCity;

public interface CodeAreaCityDao extends PagingAndSortingRepository<CodeAreaCity,java.lang.Integer>, JpaSpecificationExecutor<CodeAreaCity>{
	/**
	 * 通过百度cityCode查询
	 * @param baiduCityId
	 * @return
	 */
	List<CodeAreaCity> findByBaiduCityId(Integer baiduCityId);

	List<CodeAreaCity> findByStatus(int i);
	/**
	 * 根据省Id查询所有的城市
	 * @param provinceId
	 * @return
	 */
	List<CodeAreaCity> findByProvinceId(Integer provinceId);

	/**
	 * @description: 根据城市名获取城市对象
	 * @method:
	 * @author: Mark
	 * @date: 13:59 2018/3/1
	 * @param cityName
	 * @return:
	 */
	CodeAreaCity findByAbbreviation(String cityName);
}
