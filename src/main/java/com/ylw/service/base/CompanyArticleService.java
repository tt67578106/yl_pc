package com.ylw.service.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.ylw.entity.base.CompanyArticle;
import com.ylw.repository.CompanyArticleDao;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class CompanyArticleService {

	private CompanyArticleDao companyArticleDao;

	public CompanyArticle getCompanyArticle(java.lang.Integer id){
		return companyArticleDao.findOne(id);
	}

	public void save(CompanyArticle entity){
		companyArticleDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		companyArticleDao.delete(id);
	}

	public Page<CompanyArticle> findUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<CompanyArticle> spec = buildSpecification(userId, searchParams);
		return companyArticleDao.findAll(spec, pageRequest);
	}


	public List<Integer> findCompanyAriticleList(Integer id) {
		List<Integer> ariticleIdList = new ArrayList<Integer>();
		List<CompanyArticle> caList = companyArticleDao.findByCompanyIdAndStatus(id,1);
		for(CompanyArticle companyArticle:caList){
			ariticleIdList.add(companyArticle.getArticleId());
		}
		return ariticleIdList;
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
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<CompanyArticle> buildSpecification(Integer userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<CompanyArticle> spec = DynamicSpecifications.bySearchFilter(filters.values(), CompanyArticle.class);
		return spec;
	}

	@Autowired
	public void setCompanyArticleDao(CompanyArticleDao companyArticleDao) {
		this.companyArticleDao = companyArticleDao;
	}
}