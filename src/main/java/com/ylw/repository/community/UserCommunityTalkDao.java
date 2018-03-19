package com.ylw.repository.community;


/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.community.UserCommunityTalk;

import java.util.List;

public interface UserCommunityTalkDao extends PagingAndSortingRepository<UserCommunityTalk,java.lang.Integer>, JpaSpecificationExecutor<UserCommunityTalk>{

	/**
	 * 根据岗位id和类型查询网友咨询
	 * @param jobId
	 * @param type
	 * @param page
	 * @return
	 */
	@Query("from UserCommunityTalk uct where uct.jobBase.id = :jobId and uct.type =:type and isDelete = 1 order by isRecommend desc,updateTime desc ")
	Page<UserCommunityTalk> findByJobIdAndType(@Param("jobId")Integer jobId, @Param("type")Integer type,Pageable page);
	/**
	 * 根据社区id查询网友咨询
	 * @param communityId
	 * @param type
	 * @param pageRequest
	 * @return
	 */
	@Query("from UserCommunityTalk uct where uct.community.id = :communityId and uct.type =:type and isDelete = 1 order by isRecommend desc,updateTime desc ")
	Page<UserCommunityTalk> findByCommunityIdAndType(@Param("communityId")Integer communityId, @Param("type")Integer type, Pageable page);
	
	Integer countByJobBaseIdAndType(Integer jobId, Integer type);
	
	/**
	 * 查找问答的企业(sitemap使用)
	 * @param pageRequest
	 * @return
	 */
	@Query("select distinct c.company.id from UserCommunityTalk uct join uct.community c  where  uct.type =5 and uct.isDelete = 1 and uct.community is not null and uct.jobBase is null ")
	Page<Integer> findCommunityPage(Pageable page);
	
	@Query("select distinct job.company.id from UserCommunityTalk uct join uct.jobBase job  where  uct.type =5 and uct.isDelete = 1 and uct.jobBase is not null ")
	Page<Integer> findJobBasePage(Pageable page);
	
	@Query("select uct.id from UserCommunityTalk uct  where  uct.type =5 and uct.isDelete = 1 ")
	Page<Integer> findWenDaIdPage(Pageable page);
	
	/**
	 * 根据id得到问题
	 * @param id
	 * @param isDelete
	 * @return
	 */
	UserCommunityTalk getByIdAndIsDelete(Integer id, Integer isDelete);


	@Query("from UserCommunityTalk uct where uct.user.id = :userId order by create_time desc")
	List<UserCommunityTalk> getUserCommunityTalkBy(@Param("userId") Integer userId);

}
