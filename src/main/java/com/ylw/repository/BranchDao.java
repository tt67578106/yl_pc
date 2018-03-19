package com.ylw.repository;

/**
 * @author Nicolas.Cai
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.base.Branch;

public interface BranchDao extends PagingAndSortingRepository<Branch,java.lang.Integer>, JpaSpecificationExecutor<Branch>{
	/**
	 * 根据发布状态查询所有的分站
	 * @param isPublish
	 * @return
	 */
	public List<Branch> findByIsPublishOrderByWebPrefixAsc(Integer isPublish);
	/**
	 * 通过分站缩写查询分站名称
	 * @param abbr
	 * @return
	 */
	public List<Branch> findByAbbr(String abbr);
	/**
	 * 根据城市查询分站集合
	 * @param cityId
	 * @return
	 */
	@Query("select b from Branch b,BranchCity bc where b.id = bc.branch.id and bc.cityId = :cityId group by bc.branch.id order by bc.sorting")
	public List<Branch> findByCityId(@Param("cityId")Integer cityId);
	/**
	 * 查询是否有该id的分站
	 * @param id
	 * @return
	 */
	public Integer countById(Integer id);
	/**
	 * 根据webPrefix查询集合
	 * @param webPrefix
	 * @return
	 */
	public List<Branch> findByWebPrefix(String webPrefix);
	
	/**
	 * 得到分站所有的前缀（做sitemap用）
	 * @return
	 */
	@Query("select b.webPrefix from Branch b where b.isPublish=2  order by b.webPrefix ASC ")
	public List<String> findAllBranchWebPrefix();
	
	@Query("select b.webPrefix from Branch b where b.defaultCityId= :defaultCityId and b.isPublish=2 ")
	public String getWebPrefixByDefaultCityIdAndIsPublish(@Param("defaultCityId")Integer defaultCityId);
}
