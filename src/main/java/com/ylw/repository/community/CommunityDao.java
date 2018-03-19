package com.ylw.repository.community;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.community.Community;

public interface CommunityDao extends PagingAndSortingRepository<Community,java.lang.Integer>, JpaSpecificationExecutor<Community>{
	/**
	 * 根据社区名称模糊查询
	 * @return
	 */
	List<Community> findByCommunityNameLike(String CommunityName);
	
	List<Community> findByCompanyId(Integer companyId);
	
	@Modifying
	@Query("update Community set lng =?2,lat =?3 where id = ?1 ")
	void updateLngLat(Integer id,String lng,String lat);

}
