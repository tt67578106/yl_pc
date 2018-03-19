package com.ylw.service.enterprise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import com.ylw.entity.base.Company;
import com.ylw.entity.enterprise.CompanyResumeBox;
import com.ylw.repository.CompanyDao;
import com.ylw.repository.CompanyResumeBoxDao;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class CompanyResumeBoxService {

	@PersistenceContext
	private EntityManager em;
	
	private CompanyResumeBoxDao companyResumeBoxDao;
	
	private CompanyDao companyDao;

	public CompanyResumeBox getById(Integer id){
		return companyResumeBoxDao.findOne(id);
	}

	public void save(CompanyResumeBox entity){
		companyResumeBoxDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		companyResumeBoxDao.delete(id);
	}

	/**
	 * 根据companyId查询收到简历的数量【不走缓存】
	 * @param companyId
	 * @return
	 */
	public Long findResumeCountByCompanyIdBySql(Integer companyId){
		StringBuffer jpql = new StringBuffer("select count(1) from CompanyResumeBox box where companyId = :companyId");
		Query countQuery = em.createQuery(jpql.toString());
		countQuery.setParameter("companyId", companyId);
		Long totalSize = (Long) countQuery.getSingleResult();
		return totalSize;
	}
	
	/**
	 * 根据companyId查询所有收到的简历【不走缓存】
	 * @param companyId
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<CompanyResumeBox> findResumesByCompanyIdBySql(Integer companyId,int pageNumber,int pageSize){
		StringBuffer jpql = new StringBuffer("select comResBox from CompanyResumeBox comResBox "
				+ " where comResBox.companyId=:companyId order by comResBox.resume.updatetime desc");
		Query query = em.createQuery(jpql.toString());
		Query countQuery = em.createQuery(jpql.toString().replace("select comResBox from", "select count(1) from"));
		query.setParameter("companyId", companyId);
		countQuery.setParameter("companyId", companyId);
		Long totalSize = (Long) countQuery.getSingleResult();
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto");
		query.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		query.setMaxResults(pageRequest.getPageSize());
		Page<CompanyResumeBox> page = new PageImpl<CompanyResumeBox>(query.getResultList(), pageRequest, totalSize);
		return page;
	}
	
	
	public Page<CompanyResumeBox> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<CompanyResumeBox> spec = buildSpecification(userId.longValue(), searchParams);
		return companyResumeBoxDao.findAll(spec, pageRequest);
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
	 * 根据id删除收到的简历
	 * @param id
	 * @return
	 */
	public Integer delById(String strIds,Integer userid)
	{
		List<Integer> idlist=new ArrayList<Integer>();
		if (strIds != null) {
			if (strIds.contains(",")) {
				String[] ids = strIds.split(",");
				for (String str : ids) {
					if (!str.matches("^([0-9])*$")) {
						continue;
					}
					int i = Integer.parseInt(str);
					idlist.add(i);
				}
			} else {
				if (strIds.matches("^([0-9])*$")) {
					idlist.add(Integer.parseInt(strIds));
				}
			}
		}
		Integer result=0;
		Integer boxId=idlist.get(0);
		CompanyResumeBox companyResumeBox=getById(boxId);
		if (companyResumeBox != null) {
			Company company = companyDao.findOne(companyResumeBox.getCompanyId());
			if(company!=null)
			{
				if(company.getCreateby()==userid)
				{
					result=companyResumeBoxDao.deleteById(idlist);
				}
			}
		}
		return result;
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<CompanyResumeBox> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<CompanyResumeBox> spec = DynamicSpecifications.bySearchFilter(filters.values(), CompanyResumeBox.class);
		return spec;
	}

	@Autowired
	public void setSiteCompanyResumeBoxDao(CompanyResumeBoxDao companyResumeBoxDao) {
		this.companyResumeBoxDao = companyResumeBoxDao;
	}
	
	@Autowired
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
}