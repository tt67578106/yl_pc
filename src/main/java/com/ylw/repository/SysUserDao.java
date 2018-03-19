package com.ylw.repository;


/**
 * @author Nicolas.Cai
 * @version 1.0
 * @since 1.0
 */

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.sys.SysUser;

public interface SysUserDao extends PagingAndSortingRepository<SysUser,java.lang.Integer>, JpaSpecificationExecutor<SysUser>{
	
	SysUser findByLoginName(String loginName);
	
	SysUser findByLoginNameAndRoleIds(String loginName,String roleIds);
	/**
	 * 通过id查询名称
	 * @param id
	 * @return
	 */
	@Query("select userName from SysUser where id = :id")
	String getUserNameById(@Param("id")Integer id);

}
