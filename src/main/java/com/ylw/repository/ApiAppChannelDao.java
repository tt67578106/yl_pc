package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.sys.ApiAppChannel;

public interface ApiAppChannelDao extends PagingAndSortingRepository<ApiAppChannel,java.lang.Long>, JpaSpecificationExecutor<ApiAppChannel>{

	ApiAppChannel getByCode(String code);

}
