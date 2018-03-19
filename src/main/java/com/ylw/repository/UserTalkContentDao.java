package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.base.UserTalkContent;

public interface UserTalkContentDao extends PagingAndSortingRepository<UserTalkContent,java.lang.Integer>, JpaSpecificationExecutor<UserTalkContent>{

}
