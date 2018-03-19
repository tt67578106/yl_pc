package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.sys.SysUserProfile;

public interface SysUserProfileDao extends PagingAndSortingRepository<SysUserProfile,java.lang.Integer>, JpaSpecificationExecutor<SysUserProfile>{
	
	@Query("select profile from SysUserProfile profile where profile.mobile = :mobile")
	SysUserProfile getByMobile(@Param("mobile")String mobile);
}
