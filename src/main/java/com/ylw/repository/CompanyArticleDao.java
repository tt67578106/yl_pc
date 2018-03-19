package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ylw.entity.base.CompanyArticle;

public interface CompanyArticleDao extends PagingAndSortingRepository<CompanyArticle,java.lang.Integer>, JpaSpecificationExecutor<CompanyArticle>{

	List<CompanyArticle> findByCompanyIdAndStatus(Integer id, int status);


}
