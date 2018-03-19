package com.ylw.repository;

/**
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2015-7-16 19:56:28 
 */

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.base.ArticleKeywords;

public interface ArticleKeywordsDao extends PagingAndSortingRepository<ArticleKeywords,java.lang.Integer>, JpaSpecificationExecutor<ArticleKeywords>{

}
