package com.ylw.repository.community;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.community.UserCommunityTalkComment;

public interface UserCommunityTalkCommentDao extends PagingAndSortingRepository<UserCommunityTalkComment,java.lang.Integer>, JpaSpecificationExecutor<UserCommunityTalkComment>{
	
	UserCommunityTalkComment getByUserCommunityTalkId(Integer id);

}
