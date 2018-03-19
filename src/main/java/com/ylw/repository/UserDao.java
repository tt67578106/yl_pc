package com.ylw.repository;

import java.util.Date;

import org.jboss.logging.annotations.Param;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.base.User;

public interface UserDao extends PagingAndSortingRepository<User, java.lang.Integer>, JpaSpecificationExecutor<User> {
	/**
	 * 通过loginname查询对象
	 * 
	 * @param loginname
	 * @return
	 */
	User getByLoginname(String loginname);

	int countByLoginname(String loginName);

	@Modifying
	@Query("update User set status = 1 where id = ?1 ")
	void activateAccount(Integer id);
	
	@Modifying
	@Query("update User set lastLoginTime=?1 ,updateTime =?2 ,isValidation=?3  where id =?4")
	void updateUser(Date lastLoginTime, Date updateTime, Integer isValidation, Integer id);
}
