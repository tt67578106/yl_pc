package com.ylw.repository.advert;

/**
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2015-7-16 19:56:28 
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.advert.AdvertAreaPositionLink;

public interface AdvertAreaPositionLinkDao extends PagingAndSortingRepository<AdvertAreaPositionLink,java.lang.Integer>, JpaSpecificationExecutor<AdvertAreaPositionLink>{

}
