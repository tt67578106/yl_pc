package com.ylw.repository;

/**
 * @author Nicolas.Cai
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.ylw.entity.job.JobTag;

public interface JobTagDao extends PagingAndSortingRepository<JobTag,java.lang.Integer>, JpaSpecificationExecutor<JobTag>{

	List<JobTag> findByStatusAndTagNameLike(int status, String tagName);

	Page<JobTag> findByStatusAndParentId(int status, Integer parentId,Pageable page);

	@Query("select jobTag from JobTag jobTag where status= :status and parentId= :parentId order by sorting asc ")
	List<JobTag> findByStatusAndParentId(@Param("status")Integer status,@Param("parentId")Integer parentId);

	/**
	 * 根据id和状态得到福利标签名称（sitemap使用）
	 * @param tagId
	 * @param status
	 * @return
	 */
	@Query("select tagName from JobTag where id = :tagId and status = :status ")
	String getTagNameByIdAndStatus(@Param("tagId")Integer tagId, @Param("status")Integer status);

	@Query("select id from JobTag where status=1 order by sorting asc")
	List<Integer> findJobTagIds();
}
