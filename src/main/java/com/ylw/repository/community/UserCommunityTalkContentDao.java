package com.ylw.repository.community;

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

import com.ylw.entity.community.UserCommunityTalkContent;

public interface UserCommunityTalkContentDao extends PagingAndSortingRepository<UserCommunityTalkContent,java.lang.Integer>, JpaSpecificationExecutor<UserCommunityTalkContent>{

	List<UserCommunityTalkContent> findByUserCommunityTalkIdAndIsDelOrderByCreateTimeDesc(Integer userCommunityTalkId,Integer isDel);

	/**
	 * 根据问题id和搜索的key 查询答案
	 * @param id
	 * @param key
	 * @return
	 */
	List<UserCommunityTalkContent> findByUserCommunityTalkIdAndContentLikeAndIsDelOrderByCreateTimeDesc(Integer id, Integer isDel, String key);

}
