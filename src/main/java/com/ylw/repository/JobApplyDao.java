package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.job.JobApply;

import javax.servlet.http.HttpServletResponse;

public interface JobApplyDao extends PagingAndSortingRepository<JobApply, java.lang.Integer>, JpaSpecificationExecutor<JobApply> {

	int countByJobidAndUserid(int jobid, int userid);

	List<JobApply> findByStatusAndUserid(int status, int userid);

	List<JobApply> findByStatusAndUseridAndJobid(int status, int userid, int jobid);

	Integer countByUseridAndType(int userid,int type);

	@Query("select count(0) from JobApply where userid = :userId and jobid is not null and DATE_FORMAT(create_time,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') ")
	Integer countApplyByUserIdAndCreateDate(@Param("userId")Integer userId);


	@Query(value = "select id from JobApply where userid =:userId or mobile=:mobile order by contactTime")
	List<Integer> getApplyId(@Param("userId")Integer userId,@Param("mobile")String mobile);
}
