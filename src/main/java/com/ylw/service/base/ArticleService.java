package com.ylw.service.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

import com.google.common.collect.Maps;
import com.ylw.entity.base.Article;
import com.ylw.entity.base.ArticleKeywords;
import com.ylw.repository.ArticleDao;
import com.ylw.repository.ArticleKeywordsDao;
import com.ylw.util.Constants;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.MemcachedUtil;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class ArticleService {
	@PersistenceContext
	private EntityManager em;

	private ArticleDao articleDao;
	
	private ArticleKeywordsDao keyWordsDao;

	public Article getArticle(java.lang.Integer id) {
		return articleDao.findOne(id);
	}

	/**
	 * 查看并累计次数
	 * 
	 * @param id
	 *            articleType 分类列表， 不为0，全部列表
	 * @return
	 */
	public HashMap<String, Article> getArticle4view(java.lang.Integer id, Integer articleType) {
		Article article = articleDao.findOne(id);
		HashMap<String, Article> threeArticle = new HashMap<String, Article>();
		if (article != null) {
			if (articleType == 0) {
				articleType = article.getArticleType();
			}
			if (article.getViewCount() == null) {
				article.setViewCount(1);
			} else {
				article.setViewCount(article.getViewCount() + 1);
			}
			save(article);
			Page<Article> prepage = articleDao.findByArticleTypeLessThan(articleType, article.getPublishTime(), buildPageRequest(1, 1, "id"));
			Page<Article> nextpage = articleDao.findByArticleTypeGreaterThan(articleType, article.getPublishTime(), buildPageRequest(1, 1, "id"));
			threeArticle.put("current", article);
			// 上一页，下一页
			threeArticle.put("pre", prepage.getContent().size() > 0 ? prepage.getContent().get(0) : null);
			threeArticle.put("next", nextpage.getContent().size() > 0 ? nextpage.getContent().get(0) : null);
			return threeArticle;
		} else {
			threeArticle.put("current", null);
			threeArticle.put("pre", null);
			threeArticle.put("next", null);
		}
		return threeArticle;
	}

	public void save(Article entity) {
		articleDao.save(entity);
	}

	public void delete(java.lang.Integer id) {
		articleDao.delete(id);
	}

	/**
	 * 首页 求职帮助
	 * 
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object findRecommendArticleCacheByArticleType(String key, int limit, int articleType) {
		Object employeeArticle = MemcachedUtil.getCacheValue(key);
		if (employeeArticle == null || ((List<Object>) employeeArticle).size() < limit) {
			Page<Article> page = articleDao.findByArticleTypeAndIsRecommendGreaterThan(articleType, DateConvertUtils.getNow(), buildPageRequest(1, limit, "auto"));
			employeeArticle = page.getContent();
			MemcachedUtil.setCache(key, 3600 * 24, employeeArticle);
		}
		return employeeArticle;
	}

	/**
	 * 首页 小蓝有约
	 * 
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object findRecommendArticleCacheByXiaolan(String key, int limit, Integer articleType, Integer is_recommend) {
		Object employeeArticle = null;//MemcachedUtil.get(key);
		if (employeeArticle == null || ((List<Object>) employeeArticle).size() == 0) {
			employeeArticle = findArticleByType(articleType,is_recommend,1,limit,"publishTime");//articleDao.findByArticleTypeAndIsRecommend(articleType, is_recommend, buildPageRequest(1, limit, "publishTime"));
			MemcachedUtil.setCache(key, 3600, employeeArticle);
		}
		return employeeArticle;
	}

	/**
	 * 入职风采模块 searchParams
	 * ：类型(1、热点专题（活动专题类文章）；2、小蓝有约；3、求职帮助；4、优蓝访谈（访谈类文章）；5、优蓝现场（招聘现场类文章）
	 */
	public Page<Article> getPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Article> spec = buildSpecification(searchParams);
		return articleDao.findAll(spec, pageRequest);
	}

	/**
	 * 获取文章列表
	 * @param articleType
	 * @param isRecommend
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public List<Article> findArticleByType(Integer articleType, Integer isRecommend,  int pageNumber, int pageSize, String sortType) {

		Map<String, Object> searchParams = new HashMap<String, Object>();
		// 查询条件 GT 大于 ,LT 小于,GTE 大于等于, LTE 小于等于,EQ 等于, LIKE 包含
		searchParams.put("EQ_status", 5 + "");//发布状态
		if (articleType != null && articleType != 0)// 文章类型 (1、热点专题（活动专题类文章）；2、小蓝有约；3、求职帮助；4、优蓝访谈（访谈类文章）；5、优蓝现场（招聘现场类文章）
		{
			searchParams.put("EQ_articleType", articleType + "");
		}
		if (isRecommend != null)// 推荐设置[0或空:不推荐,1:推荐到首页;2:首页下左轮播;3:首页下中推荐(单条推荐)]
		{
			searchParams.put("EQ_isRecommend", isRecommend + "");
		}
		Page<Article> pages = getArticlePage(searchParams, pageNumber, pageSize, sortType);
		return pages.getContent();
	}

	public Page<Article> getArticlePage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Article> spec = buildSpecification1(searchParams);
		return articleDao.findAll(spec, pageRequest);
	}
	
	

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<Article> buildSpecification1(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Article> spec = DynamicSpecifications.bySearchFilter(filters.values(), Article.class);
		return spec;
	}


	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		} else if ("publishTime".equals(sortType)) {
			sort = new Sort(Direction.DESC, "publishTime");
		} else if("sorting".equals(sortType)){
			sort = new Sort(Direction.DESC, "sorting");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合[员工风采].
	 */
	private Specification<Article> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
//		String type = searchParams.get("articleType") + "";
//		if (searchParams.get("articleType") != null && StringUtils.isNotBlank(type)) {
//			if (type.indexOf(";") > 0) {
//				type = type.substring(0, type.indexOf(";"));
//			}
//			filters.put("articleType", new SearchFilter("articleType", Operator.EQ, type));
//		}
		filters.put("status", new SearchFilter("status", Operator.EQ, 5));
		Specification<Article> spec = DynamicSpecifications.bySearchFilter(filters.values(), Article.class);
		return spec;
	}
	/**
	 * 获取文章列表TDK title
	 * @param articleType
	 * @return
	 */
	public Map<String, String> getArticleTDK(Integer articleType) {
		Map<String, String> data = new HashMap<String, String>();
		if(articleType == 1){
			data.put("title", Constants.HOME_TITLE_1);
			data.put("keyword", Constants.HOME_KEYWORD_1);
			data.put("description",  Constants.HOME_DESCRIPTION_1);
			return data;
		}else if(articleType == 2){
			data.put("title", Constants.HOME_TITLE_2);
			data.put("keyword", Constants.HOME_KEYWORD_2);
			data.put("description",  Constants.HOME_DESCRIPTION_2);
			return data;
		}else if(articleType == 3){
			data.put("title", Constants.HOME_TITLE_3);
			data.put("keyword", Constants.HOME_KEYWORD_3);
			data.put("description",  Constants.HOME_DESCRIPTION_3);
			return data;
		}else if(articleType == 5){
			data.put("title", Constants.HOME_TITLE_5);
			data.put("keyword", Constants.HOME_KEYWORD_5);
			data.put("description",  Constants.HOME_DESCRIPTION_5);
			return data;
		}else if(articleType == 6){
			data.put("title", Constants.HOME_TITLE_6);
			data.put("keyword", Constants.HOME_KEYWORD_6);
			data.put("description",  Constants.HOME_DESCRIPTION_6);
			return data;
		}else if(articleType == 7){
			data.put("title", Constants.HOME_TITLE_7);
			data.put("keyword", Constants.HOME_KEYWORD_7);
			data.put("description",  Constants.HOME_DESCRIPTION_7);
			return data;
		}else if(articleType == 8){
			data.put("title", Constants.HOME_TITLE_8);
			data.put("keyword", Constants.HOME_KEYWORD_8);
			data.put("description",  Constants.HOME_DESCRIPTION_8);
			return data;
		}
		else{
			data.put("title", Constants.HOME_TITLE_0);
			data.put("keyword", Constants.HOME_KEYWORD_0);
			data.put("description",  Constants.HOME_DESCRIPTION_0);
			return data;
		}
	}
	@Autowired
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	@Autowired
	public void setKeyWordsDao(ArticleKeywordsDao keyWordsDao) {
		this.keyWordsDao = keyWordsDao;
	}

		/**
	 * 获得分页企业列表
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Article> getPage(Integer pageNumber,Integer pageSize) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto");
		Map<String, Object> searchParams = new HashMap<String, Object>();
		// 查询条件 GT 大于 ,LT 小于,GTE 大于等于, LTE 小于等于,EQ 等于, LIKE 包含
		searchParams.put("EQ_status", 5+"");//发布状态的文章
		Specification<Article> spec = null;
		spec = buildSpecification(searchParams);
		return articleDao.findAll(spec, pageRequest);
	}
	
	/**
	 *  根据文章的发布状态得到文章的id
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Integer> findArticleIdPage(Integer pageNumber,Integer pageSize) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto");
		return articleDao.findIdByStatus(5, pageRequest);
	}

	/**
	 * 搜索关键词列表
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<ArticleKeywords> getKeyWordsPage(int pageNumber, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Map<String, SearchFilter> filters = Maps.newHashMap();
		filters.put("isDelete", new SearchFilter("isDelete", Operator.EQ, 1));
		Specification<ArticleKeywords> spec = DynamicSpecifications.bySearchFilter(filters.values(), ArticleKeywords.class);
		return keyWordsDao.findAll(spec, pageRequest);
	}
}