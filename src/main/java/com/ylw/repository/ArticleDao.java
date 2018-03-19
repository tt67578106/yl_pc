package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.base.Article;

public interface ArticleDao extends PagingAndSortingRepository<Article,java.lang.Integer>, JpaSpecificationExecutor<Article>{
	//读取7天内点击量最高的前5条【求职帮助】
	@Query("select article from Article article where article.articleType = :articleType  and article.status = 5 "
			+ "  and  article.publishTime <= :now   order by article.publishTime  DESC  ")
	Page<Article> findByArticleTypeAndIsRecommendGreaterThan(@Param("articleType") int articleType,@Param("now") Date now,Pageable page);

	//首页读取文章按推荐状态
	@Query("select article from Article article where article.articleType = :articleType and article.status = 5 and  isRecommend = :isRecommend  ")
	Page<Article> findByArticleTypeAndIsRecommend(@Param("articleType") int articleType,@Param("isRecommend")Integer isRecommend,Pageable page);
	
	//首页读取文章最新的不按是否推荐
	@Query("select article from Article article where article.articleType = :articleType and article.status = 5  ")
	Page<Article> findByArticleType(@Param("articleType") int articleType,Pageable page);
	
	//文章上一页，下一页
	@Query("select article from Article article where article.articleType = :articleType and article.status = 5 "
			+ " and article.publishTime < :now ORDER BY article.publishTime DESC")
	Page<Article> findByArticleTypeGreaterThan(@Param("articleType") int articleType,@Param("now") Date now,Pageable page);

	@Query("select article from Article article where article.articleType = :articleType and article.status = 5 "
			+ " and article.publishTime > :now ORDER BY	article.publishTime  ASC")
	Page<Article> findByArticleTypeLessThan(@Param("articleType") int articleType,@Param("now")Date publishTime, Pageable page);
	
	Page<Article> findByIdIn(List<Integer> ariticleIdList,Pageable page);

	/**
	 * 根据文章的发布状态得到文章的id
	 * @param status
	 * @param pageRequest
	 * @return
	 */
	@Query("select article.id from Article article where article.status = :status ")
	Page<Integer> findIdByStatus(@Param("status")Integer status, Pageable pageRequest);

}
