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

import com.ylw.entity.job.JobType;

public interface JobTypeDao extends PagingAndSortingRepository<JobType,java.lang.Integer>, JpaSpecificationExecutor<JobType>{

	List<JobType> findByStatusAndJobNameLike(Integer status,String jobName);

	List<JobType> findByStatusAndParentIdOrderBySortingAsc(Integer status, Integer parentId);

	/**
	 * 根据typeid和状态得到type的名称
	 * @param typeId
	 * @param status
	 * @return
	 */
	@Query("select jobName from JobType where id = :typeId and status = :status ")
	String getTypeNameByIdAndStatus(@Param("typeId")Integer typeId, @Param("status")Integer status);

	@Query("select id from JobType where parentId = :parentId and status = 1 order by sorting asc ")
	List<Integer> findJobTypeIdByParentIds(@Param("parentId")Integer parentId);

}
