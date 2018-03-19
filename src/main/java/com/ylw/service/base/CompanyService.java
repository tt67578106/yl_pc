package com.ylw.service.base;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import com.ylw.entity.base.Article;
import com.ylw.entity.base.Branch;
import com.ylw.entity.base.BranchCity;
import com.ylw.entity.base.Company;
import com.ylw.entity.base.CompanyArticle;
import com.ylw.entity.base.CompanyScene;
import com.ylw.entity.base.Image;
import com.ylw.entity.job.JobBase;
import com.ylw.entity.vo.CompanyListVo;
import com.ylw.entity.vo.ImageVo;
import com.ylw.entity.vo.RecommendCompanyVo;
import com.ylw.repository.ArticleDao;
import com.ylw.repository.BranchDao;
import com.ylw.repository.CodeAreaCityDao;
import com.ylw.repository.CompanyArticleDao;
import com.ylw.repository.CompanyDao;
import com.ylw.repository.CompanySceneDao;
import com.ylw.repository.ImageDao;
import com.ylw.service.job.JobBaseService;
import com.ylw.util.Constants;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.HTMLInputFilter;
import com.ylw.util.MemcachedUtil;
import com.ylw.util.TextUtil;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */
// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class CompanyService {

	private CompanyDao companyDao;

	private BranchDao branchDao;

	private ArticleDao articleDao;

	private CodeAreaCityDao codeAreaCityDao;

	private ImageDao imageDao;

	private CompanyArticleDao companyArticleDao;

	private CompanySceneDao companySceneDao;

	private JobBaseService jobBaseService;

	@PersistenceContext
	private EntityManager em;

	/**
	 * 通过Id 查询企业对象
	 * 
	 * @param id
	 * @return
	 */
	public Company getCompany(java.lang.Integer id) {
		return id != null ? companyDao.findOne(id) : null;
	}
	/**
	 * 通过Id 查询企业对象
	 * 
	 * @param id
	 * @return
	 */
	public Company getOnlineCompany(java.lang.Integer id) {
		if(NumberUtils.isDigits("" + id)){
			return companyDao.getByIdAndIsdel(id, 1);
		}
		return null;
	}

	public Company getByCreateby(Integer userid) {
		List<Company> cList = companyDao.getByCreateby(userid);
		if (cList.size() > 0) {
			return cList.get(0);
		}
		return null;
	}

	/**
	 * 保存企业对象
	 * 
	 * @param entity
	 */
	public void save(Company entity) {
		companyDao.save(entity);
	}

	/**
	 * 获得所有的企业的数量
	 * 
	 * @return
	 */
	public Long countCompany() {
		Long count = (Long) MemcachedUtil.getCacheValue(Constants.CACHE_KEY_COMPANY_COUNT);
		if (count == null) {
			count = companyDao.count();
			MemcachedUtil.setCache(Constants.CACHE_KEY_COMPANY_COUNT, 3600 * 24, count);
		}
		return count;
	}

	/**
	 * 获得昨天新增企业的数量
	 * 
	 * @return
	 */
	public Long yesterdayCompany() {
		Long count = (Long) MemcachedUtil.getCacheValue(Constants.CACHE_KEY_YESTERDAY_COMPANY_COUNT);
		if (count == null || count == 0) {
			count = companyDao.getCountByTime(DateConvertUtils.getAnyDayDate(1));
			MemcachedUtil.setCache(Constants.CACHE_KEY_YESTERDAY_COMPANY_COUNT, 3600 * 24, count);
		}
		return count;
	}

	/**
	 * 首页大家都在找
	 * 
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Company> getCompanyApplyCache(Integer pageSize) {
		List<Company> companyList = new ArrayList<Company>();
		List<Company> compCachList = null;
		Object companyCache;
		// StoreCacheUtil.getCacheValue("allCompany", "all_company");
		companyCache = MemcachedUtil.getCacheValue(Constants.CACHE_KEY_TOP300_COMPANY);
		if (companyCache == null || ((List<Object>) companyCache).size() == 0) {// 从缓存中取数据，看是否有值
																				// ，如果没值创建缓存
			Map<String, Object> searchParams = new HashMap<String, Object>();
			// 查询条件 GT 大于 ,LT 小于,GTE 大于等于, LTE 小于等于,EQ 等于, LIKE 包含
			Page<Company> pages = getPage(searchParams, 1, 300, "createtime");
			compCachList = pages.getContent();
			MemcachedUtil.setCache(Constants.CACHE_KEY_TOP300_COMPANY, 3600 * 24, compCachList);
		} else {// 有值
			compCachList = (List<Company>) companyCache;
		}
		if (compCachList != null && compCachList.size() != 0) {
			Object companyApplyCache = MemcachedUtil.getCacheValue(Constants.CACHE_KEY_APPLY_COMPANY);
			// StoreCacheUtil.getCacheValue("pointOfPraiseCache",
			// "company_apply");
			if (companyApplyCache == null || ((List<Object>) companyApplyCache).size() < pageSize) {// 从缓存中取数据，看是否有值
																									// ，如果没值创建缓存
				Random random = new Random();
				int start = 0;// 产生从0 到总数减去size的随机数
				int end = 0;
				if (compCachList.size() > pageSize + 1) {
					int max = compCachList.size() - pageSize - 1;
					start = random.nextInt(max);
					end = start + pageSize;
				} else {
					end = compCachList.size() - 1;
				}
				for (int i = start; i <= end; i++) {// 从随机数开始取size个数据返回
					companyList.add(compCachList.get(i));
				}
				MemcachedUtil.setCache(Constants.CACHE_KEY_APPLY_COMPANY, 3600, companyList);
			} else {// 有值
				companyList = (List<Company>) companyApplyCache;
			}
		}
		return companyList;
	}

	/**
	 * 文章列表、详情页面的推荐企业
	 * 
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RecommendCompanyVo> getCompanyByBranchIdCache(Integer pageSize, Integer branchId) {
		List<RecommendCompanyVo> companyList = new ArrayList<RecommendCompanyVo>();
		List<Company> compCachList = null;
		Object companyCache = MemcachedUtil.getCacheValue(Constants.CACHE_KEY_TOP300_COMPANY+branchId);
		if (companyCache == null || ((List<Object>) companyCache).size() == 0) {// 从缓存中取数据，看是否有值
			// ，如果没值创建缓存
			List<Integer> cityList = findBranchCityIdList(branchId == null ? 1 : branchId);
			compCachList = companyDao.findByCity(cityList.size() > 0 ? cityList : null, buildPageRequest(1, 30, "createtime"));
			MemcachedUtil.setCache(Constants.CACHE_KEY_TOP300_COMPANY+branchId, 3600 * 24, compCachList);
		} else {// 有值
			compCachList = (List<Company>) companyCache;
		}
		if (compCachList != null && compCachList.size() != 0) {
			Random random = new Random();
			int start = 0;// 产生从0 到总数减去size的随机数
			int end = 0;// 最大值
			if (compCachList.size() > pageSize + 1) {
				int max = compCachList.size() - pageSize - 1;
				start = random.nextInt(max);
				end = start + pageSize;
			} else {
				end = compCachList.size() - 1;
			}
			for (int i = start; i <= end; i++) {// 从随机数开始取size个数据返回
				Company company = compCachList.get(i);
				Integer avgSalary = 0;
				avgSalary = getAverageSalary(company.getId());
				RecommendCompanyVo rcv = new RecommendCompanyVo(company.getId(), company.getLogo() == null ? "" : company.getLogo().getImgpath(), company.getName(), avgSalary + "", company.getAbbreviation());
				companyList.add(rcv);
			}
		}
		return companyList;
	}

	/**
	 * 查询分页对象
	 * 
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<Company> getPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Company> spec = buildSpecification(searchParams);
		return companyDao.findAll(spec, pageRequest);
	}

	/**
	 * 根据公司ID和类型得到图片集
	 * 
	 * @param type
	 *            -2 只图片数量名字,-1 企业首页显示 0 全部 企业实景、工作环境、生活环境、未分类
	 * @param companyId
	 * @return
	 */
	public List<ImageVo> findImageByTypeAndCompany(int type, Integer companyId) {
		Company company = getCompany(companyId);
		if (company == null) {
			return null;
		}
		List<ImageVo> imageList = new ArrayList<ImageVo>();
		if (type == -1) {// 企业主页显示，总数量，和最新图片
			// 根据时间排序取第一个
			CompanyScene companyScene = getCompanySceneByCompanyOrderByCreatetime(companyId);
			String ipath = (companyScene == null || companyScene.getImage() == null) ? "" : companyScene.getImage().getImgpath();
			// 去除ISshow=0的
			for (int count = 0; count < company.getScenes().size(); count++) {
				if (company.getScenes().get(count).getIsshow() != 1) {
					company.getScenes().remove(company.getScenes().get(count));
					count = count - 1;
				}
			}
			ImageVo imageVo = new ImageVo(0, ipath, "企业相册", company.getScenes() == null ? 0 : company.getScenes().size());
			imageList.add(imageVo);
		} else if (type == -2) {// 查询图片各类型数量
			imageList.add(buildImageVo("全部图片", 0, company.getScenes()));
			imageList.add(buildImageVo("企业实景", 1, company.getScenes()));
			imageList.add(buildImageVo("工作环境", 2, company.getScenes()));
			imageList.add(buildImageVo("生活环境", 6, company.getScenes()));
			imageList.add(buildImageVo("其他", 7, company.getScenes()));
		} else {// 通过类型查询对应图片集
			for (CompanyScene companyScene : company.getScenes()) {
				if (type == 0) {
					if (companyScene.getIsshow() == 1 && companyScene.getImage() != null) {
						ImageVo imageVo = new ImageVo(companyScene.getImage().getId(), companyScene.getImage().getImgpath(), companyScene.getTitle(), 1);
						imageList.add(imageVo);
					}
				} else {
					if (type == 7) {// 其他图片
						if ((companyScene.getCategory() == null || (companyScene.getCategory() != 1 && companyScene.getCategory() != 6 && companyScene.getCategory() != 2)) && companyScene.getIsshow() != null && companyScene.getIsshow() == 1) {
							if (companyScene.getImage() != null) {
								ImageVo imageVo = new ImageVo(companyScene.getImage().getId(), companyScene.getImage().getImgpath(), companyScene.getTitle(), 1);
								imageList.add(imageVo);
							}
						}
					} else // 对应图片
					if ((companyScene.getCategory() != null) && companyScene.getCategory() == type && (companyScene.getIsshow() != null && companyScene.getIsshow() == 1)) {
						if (companyScene.getImage() != null) {
							ImageVo imageVo = new ImageVo(companyScene.getImage().getId(), companyScene.getImage().getImgpath(), companyScene.getTitle(), 1);
							imageList.add(imageVo);
						}
					}
				}
			}
		}
		return imageList;
	}

	public ImageVo buildImageVo(String name, Integer type, List<CompanyScene> companyScenes) {
		List<Image> imageList = new ArrayList<Image>();
		for (CompanyScene companyScene : companyScenes) {
			if (type == 0) {
				if (companyScene.getIsshow() == 1) {
					if (companyScene.getImage() != null) {
						imageList.add(companyScene.getImage());
					}
				}
			} else {
				if (type == 7) {// 其他图片
					if ((companyScene.getCategory() == null || (companyScene.getCategory() != 1 && companyScene.getCategory() != 6 && companyScene.getCategory() != 2)) && companyScene.getIsshow() != null && companyScene.getIsshow() == 1) {
						if (companyScene.getImage() != null) {
							imageList.add(companyScene.getImage());
						}
					}
				} else {
					if (companyScene.getCategory() == type && companyScene.getIsshow() != null && companyScene.getIsshow() == 1) {
						if (companyScene.getImage() != null) {
							imageList.add(companyScene.getImage());
						}
					}
				}
			}
		}
		ImageVo imageVo = null;
		if (imageList.size() > 0) {
			imageVo = new ImageVo(type, imageList.get(0).getImgpath(), name, imageList.size());
		}
		return imageVo;
	}

	/**
	 * 根据公司Id得到文章列表
	 * 
	 * @param id
	 * @return
	 */
	public List<Article> findArticlesByCompanyId(Integer id, Integer limit) {
		List<Integer> ariticleIdList = findCompanyAriticleList(id);
		if (ariticleIdList != null && ariticleIdList.size() > 0) {
			List<Article> articleList = articleDao.findByIdIn(ariticleIdList, buildPageRequest(1, limit, "createTime")).getContent();
			return articleList;
		}
		return null;
	}

	/**
	 * 根据点赞数，得到最热的公司集
	 * 
	 * @param limit
	 * @return
	 */
	public List<Company> findCompanyByPointOfPraise(String cachePcKeyHotCompany, Integer limit, Integer branchId) {
		List<Company> companys;
		companys = (List<Company>) MemcachedUtil.getCacheValue(cachePcKeyHotCompany + branchId);
		if (companys == null || (companys.size() == 0)) {
			List<Integer> cityList = findBranchCityIdList(branchId == null ? 1 : branchId);
			companys = companyDao.findByCity(cityList.size() > 0 ? cityList : null, buildPageRequest(1, limit, "pointOfPraise"));
			MemcachedUtil.setCache(cachePcKeyHotCompany + branchId, 3600 * 24, companys);
		}
		return companys;
	}

	/**
	 * 查询出companid list 和对应的问答数
	 * 
	 * @param limit
	 * @return
	 */
	public List<Object[]> getComanyIdAndCount(Integer limit) {
		String hql = "SELECT advisory.companyid ,count(advisory.companyid)  from Advisory advisory " + " where advisory.checkstatus = 1 GROUP BY advisory.companyid ORDER BY count(advisory.companyid) DESC";
		Query query = em.createQuery(hql);
		query.setFirstResult(1);
		query.setMaxResults(limit);
		List<Object[]> list = query.getResultList();
		return list;
	}

	/**
	 * 咨询最多
	 * 
	 * @param cachePcKeyConsultingCompany
	 * @param limit
	 * @return
	 */
	public LinkedHashMap<Company, Long> findConsultingCompanyCache(String cachePcKeyConsultingCompany, Integer limit) {
		LinkedHashMap<Company, Long> companyMap;
		companyMap = (LinkedHashMap<Company, Long>) MemcachedUtil.getCacheValue(cachePcKeyConsultingCompany);
		List<Object[]> companyNums = getComanyIdAndCount(limit);
		if (companyMap == null || (companyMap.size() < limit)) {
			companyMap = new LinkedHashMap<Company, Long>();
			for (Object[] obj : companyNums) {
				if (obj.length == 2) {
					companyMap.put(companyDao.findOne(Integer.parseInt(obj[0] + "")), Long.parseLong(obj[1] + ""));
				}
			}
			MemcachedUtil.setCache(cachePcKeyConsultingCompany, 3600 * 24, companyMap);
		}
		return companyMap;
	}

	/**
	 * 得到同类型公司
	 * @param limit
	 * @return
	 */
	public List<RecommendCompanyVo> findSimilarCompany(Integer limit, Integer cityId, Integer industryid, Integer companyId) {
		List<RecommendCompanyVo> similarCompany = new ArrayList<RecommendCompanyVo>();
		StringBuffer jpql = new StringBuffer("select company.id from Company company "
				+ "where company.city.id =:cityId and company.industryid=:industryid "
				+ "and company.isdel=1 and company.id != :companyId order by updatetime desc");
		Query query = em.createQuery(jpql.toString());
		query.setParameter("cityId",cityId);
		query.setParameter("industryid",industryid);
		query.setParameter("companyId",companyId);
		query.setFirstResult(0);
		query.setMaxResults(3);
		List<Integer> companyIds = query.getResultList();
		//List<Integer> companyIds=companyDao.findByCityIdAndIndustryid(cityId, industryid, companyId, buildPageRequest(1, limit, "updatetime")).getContent();
		if(companyIds != null && companyIds.size() > 0){
			List<Company> companyList = companyDao.findByIds(companyIds);
			for (Company company : companyList) {
				Integer avgSalary = 0;
				avgSalary = getAverageSalary(company.getId());
				RecommendCompanyVo rcv = new RecommendCompanyVo(company.getId(), company.getLogo() == null ? "" : company.getLogo().getImgpath(), company.getName(), avgSalary + "", company.getAbbreviation());
				similarCompany.add(rcv);
			}
		}
		return similarCompany;
	}

	/**
	 * 得到公司所有岗位平均薪资
	 */
	public Integer getAverageSalary(Integer companyId) {
		List<JobBase> jobList = jobBaseService.findByCompanyId(companyId, 30).getContent();
		int allSalary = 0;
		for (JobBase job : jobList) {
			if (job.getJobDetail().getSalaryfrom() != null && job.getJobDetail().getSalaryto() == null) {
				allSalary += job.getJobDetail().getSalaryfrom();
			}
			if (job.getJobDetail().getSalaryto() != null && job.getJobDetail().getSalaryfrom() != null) {
				allSalary += (job.getJobDetail().getSalaryto() + job.getJobDetail().getSalaryfrom()) / 2;
			}
		}
		BigDecimal averageSalary = new BigDecimal(allSalary / (jobList.size() == 0 ? 1 : jobList.size()));
		return averageSalary.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
	}

	/**
	 * 公司列表
	 * 
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @param branchId
	 * @return
	 */
	public Page<CompanyListVo> getHqlPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType, Integer branchId) {
		StringBuffer hql = new StringBuffer("select company from Company company where company.isdel = 1 and company.validation = 1 and company.cooperationType in (1,2,3,4,5) ");
		for (String field : searchParams.keySet()) {
			String obj = (searchParams.get(field) + "").trim();
			if (!obj.equals("0")) {
				if ("LIKE_searchName".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj)) {// 企业名称、简称、地址
					hql.append(" and (company.name like :searchName or company.abbreviation like :searchName or company.address like :searchName) ");
				} else if ("EQ_staffscale".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj) && StringUtils.isNumeric(obj)) {
					hql.append(" and company.staffscale = :staffscale ");
				} else if ("EQ_countyid".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj)) {
					hql.append(" and company.countyid = :countyid");
				} else if ("EQ_industry".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj)) {
					hql.append(" and company.industryid = :industryid");
				}
			}
		}
		if (branchId != null) {
			hql.append(" and company.city.id = :defaultCityId ");
		}
		//临时处理昆山的情况
		if(branchId == 6){
			hql.append(" and company.countyid = :countyid");
		}
		if ("auto".equals(sortType)) {
			hql.append(" order by company.createtime desc ");
		} else if ("updatetime".equals(sortType)) {
			hql.append(" order by company.updatetime desc ");
		}
		Query countQuery = em.createQuery(hql.toString().replace("select company from", "select count(1) from"));
		Query query = em.createQuery(hql.toString());
		int defaultCityId = getDefaultCityId(branchId);
		for (String field : searchParams.keySet()) {
			String obj = (searchParams.get(field) + "").trim();
			if (!obj.equals("0")) {
				if ("LIKE_searchName".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj)) {// 企业名称、简称、地址
					query.setParameter("searchName", "%" + obj + "%");
					countQuery.setParameter("searchName", "%" + obj + "%");
				} else if ("EQ_staffscale".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj) && StringUtils.isNumeric(obj)) {
					query.setParameter("staffscale", Integer.parseInt(obj));
					countQuery.setParameter("staffscale", Integer.parseInt(obj));
				} else if ("EQ_cityid".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj) && StringUtils.isNumeric(obj)) {
					defaultCityId = Integer.parseInt(obj);
				} else if ("EQ_countyid".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj) && StringUtils.isNumeric(obj)) {
					//临时处理昆山的情况
					int countyid = Integer.parseInt(obj);
					if(defaultCityId == 346){
						defaultCityId = 78;
						countyid = 785;
					}
					query.setParameter("countyid", countyid);
					countQuery.setParameter("countyid", countyid);
				} else if ("EQ_industry".equalsIgnoreCase(field) && StringUtils.isNotBlank(obj) && StringUtils.isNumeric(obj)) {
					query.setParameter("industryid", Integer.parseInt(obj));
					countQuery.setParameter("industryid", Integer.parseInt(obj));
				}
			}
		}
		if (branchId != null) {
			if(defaultCityId == 346){
				defaultCityId = 78;
			}
			query.setParameter("defaultCityId", defaultCityId);
			countQuery.setParameter("defaultCityId", defaultCityId);
		}
		//临时处理昆山的情况
		if(branchId == 6){
			query.setParameter("countyid", 785);
			countQuery.setParameter("countyid", 785);
		}
		Long totalSize = (Long) countQuery.getSingleResult();
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		query.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		query.setMaxResults(pageRequest.getPageSize());
		Page<CompanyListVo> page = new PageImpl<CompanyListVo>(buildCompanyListVo(query.getResultList()), pageRequest, totalSize);
		return page;
	}

	/**
	 * 取同城市6条
	 */
	public List<CompanyListVo> findCompanyListByCity(Integer branchId) {
		List<Integer> cityIdList = findBranchCityIdList(branchId);
		List<Company> companyList = companyDao.findByCity(cityIdList.size() > 0 ? cityIdList : null, buildPageRequest(1, 6, "updatetime"));
		return buildCompanyListVo(companyList);
	}

	/**
	 * 封装前台显示vo
	 * @param resultList
	 * @return
	 */
	public List<CompanyListVo> buildCompanyListVo(List<Company> resultList) {
		List<CompanyListVo> cvList = new ArrayList<CompanyListVo>();
		for (Company company : resultList) {
			// 企业第一张实景图
			String senceImage = null;
			List<ImageVo> companySence = findImageByTypeAndCompany(-1, company.getId());
			if (companySence.size() > 0) {
				senceImage = companySence.get(0).getImgPath();
			}
			// 企业最新两条岗位
			List<JobBase> jobBases = jobBaseService.findByCompanyIdLimit(company.getId(), 2).getContent();
			CompanyListVo companyListVo = new CompanyListVo(company.getId(), company.getLogo() == null ? null : company.getLogo().getImgpath(), senceImage, company.getName(), company.getAbbreviation(), company.getValidation(), company.getIndustryid(), company.getStaffscale(), company.getAddress(), company.getIntroduction(), company.getSatisfaction(), company.getCity() == null ? "" : company.getCity().getId() + "", company.getCountyid() + "", company.getUpdatetime(), null, company.getProvinceid(), company.getCountyid(), company.getCity() == null ? "" : company.getCity().getCityName(), jobBases,company.getIsLoan(),company.getCooperationType());
			cvList.add(companyListVo);
		}
		return cvList;
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
		} else if ("pointOfPraise".equals(sortType)) {
			sort = new Sort(Direction.DESC, "pointOfPraise");
		} else if ("updatetime".equals(sortType)) {
			sort = new Sort(Direction.DESC, "updatetime");
		} else if ("createTime".equals(sortType)) {
			sort = new Sort(Direction.DESC, "createTime");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<Company> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ,
		// userId));
		Specification<Company> spec = DynamicSpecifications.bySearchFilter(filters.values(), Company.class);
		return spec;
	}

	/**
	 * 根据公司Id,满意度生成随机数，用作多少人评论
	 */
	public int getRandCount(String satisfaction, Integer id) {
		if (satisfaction == null || !TextUtil.isNumeric1(satisfaction) || satisfaction.trim().equals("")) {
			satisfaction = "95";
		}
		return Math.abs(Integer.parseInt(satisfaction) - 90) * 10 * (id % 10 == 0 ? 5 : id % 10) + id % 10;
	}

	@Autowired
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	/**
	 * 保存企业资料
	 * 
	 * @param userid
	 * @param enterprise
	 */
	public void saveEnterprise(Integer userid, Company enterprise) {
		Company company = null;
		if (getByCreateby(userid) == null) {
			company = new Company();
			company.setCreateby(userid);
			company.setCreatetime(DateConvertUtils.getNow());
			company.setIsdel(1);// 未删除
		} else {
			company = getByCreateby(userid);
		}
		company.setCooperationType(2);// 数据来源类型企业自录
		company.setValidation(-1);// 每修改一次把审核状态改为未验证
		company.setName(HTMLInputFilter.clean(enterprise.getName()));
		company.setAbbreviation(HTMLInputFilter.clean(enterprise.getAbbreviation()));
		company.setLogo(imageDao.findOne(enterprise.getLogo().getId()));
		company.setIndustryid(enterprise.getIndustryid());
		company.setStaffscale(enterprise.getStaffscale());
		company.setProvinceid(enterprise.getProvinceid());
		if (enterprise.getCity() != null) {
			company.setCity(codeAreaCityDao.findOne(enterprise.getCity().getId()));
		}
		company.setCountyid(enterprise.getCountyid());
		company.setAddress(HTMLInputFilter.clean(enterprise.getAddress()));
		company.setBrightSpot(HTMLInputFilter.clean(enterprise.getBrightSpot()));
		company.setAdvantages(enterprise.getAdvantages());
		company.setIntroduction(enterprise.getIntroduction());
		company.setContactPerson(HTMLInputFilter.clean(enterprise.getContactPerson()));
		company.setContactPhone(HTMLInputFilter.clean(enterprise.getContactPhone()));
		company.setUpdateby(userid);
		company.setUpdatetime(DateConvertUtils.getNow());
		companyDao.save(company);
	}

	/**
	 * 重新提交企业资料
	 * 
	 * @param certificationId
	 * @return
	 */
	public int refInformation(Integer enterpriseId) {
		int result = 0;
		result = companyDao.updateValidationById(enterpriseId);
		return result;
	}

	/**
	 * 单独修改企业logo
	 * 
	 * @param logo
	 * @param companyid
	 */
	public void uploadLogo(Image logo, Integer companyid) {
		Company company = companyDao.findOne(companyid);
		if (company != null) {
			company.setValidation(-1);// 改为未审核
			company.setLogo(logo);
			companyDao.save(company);
		}
	}

	/**
	 * 根据公司Id得到对应文章列表ID
	 * 
	 * @param id
	 * @return
	 */
	private List<Integer> findCompanyAriticleList(Integer id) {
		List<Integer> ariticleIdList = new ArrayList<Integer>();
		List<CompanyArticle> caList = companyArticleDao.findByCompanyIdAndStatus(id, 1);
		for (CompanyArticle companyArticle : caList) {
			ariticleIdList.add(companyArticle.getArticleId());
		}
		return ariticleIdList;
	}

	/**
	 * 根据所选分站查询城市列表
	 * 
	 * @param branchId
	 * @return
	 */
	public List<Integer> findBranchCityIdList(Integer branchId) {
		List<Integer> cityList = null;
		Branch branch = branchDao.findOne(branchId);
		if (branch != null && branch.getBranchCityList() != null) {
			List<BranchCity> bcityList = branch.getBranchCityList();
			cityList = new ArrayList<Integer>();
			for (BranchCity branchCity : bcityList) {
				cityList.add(branchCity.getCityId());
			}
		}
		return cityList;
	}

	/**
	 * 根据所选分站得到主城市
	 * 
	 * @param branchId
	 * @return
	 */
	public Integer getDefaultCityId(Integer branchId) {
		Branch branch = branchDao.findOne(branchId);
		Integer cityId = null;
		if (branch != null) {
			cityId = branch.getDefaultCityId();
		}
		return cityId;
	}

	/**
	 * 取公司最新图片
	 * 
	 * @param companyId
	 * @return
	 */
	public CompanyScene getCompanySceneByCompanyOrderByCreatetime(Integer companyId) {
		List<CompanyScene> companyScenes = companySceneDao.findByCompanyIdAndIsshow(companyId,1, buildPageRequest(1, 1, "createtime")).getContent();
		if (companyScenes.size() > 0) {
			return companyScenes.get(0);
		}
		return null;
	}

	/*
	 * 下一个公司
	 */
	public Company getNextCompany(Integer id) {
		Company company = companyDao.findOne(id);
		if (company == null) {
			return null;
		}
		Integer cityId = company.getCity() == null ? 0 : company.getCity().getId();
		StringBuffer jpql = new StringBuffer("select company.id from Company company where company.city.id = :cityId and company.isdel = 1 "
			+ " and validation = 1 and  company.createtime < :now ORDER BY	company.createtime  DESC");
		Query query = em.createQuery(jpql.toString());
		query.setParameter("cityId",cityId);
		query.setParameter("now",company.getCreatetime());
		query.setFirstResult(0);
		query.setMaxResults(1);
		List<Integer> companyIds = query.getResultList();
		if(companyIds != null && companyIds.size() > 0){
			List<Company> companyList = companyDao.findByIds(companyIds);
			return  companyList.size() > 0 ? companyList.get(0) : null;
		}
		return company;
		/*Page<Company> companyList = companyDao.getNextCompany(cityId, company.getCreatetime(), buildPageRequest(1, 1, "id"));
		if (companyList.getContent().size() <= 0) {
			companyList = companyDao.getLastCompany(cityId, buildPageRequest(1, 1, "id"));
		}
		return companyList.getContent().size() > 0 ? companyList.getContent().get(0) : null;*/
	}

	@Autowired
	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}

	@Autowired
	public void setCompanyArticleDao(CompanyArticleDao companyArticleDao) {
		this.companyArticleDao = companyArticleDao;
	}

	@Autowired
	public void setCodeAreaCityDao(CodeAreaCityDao codeAreaCityDao) {
		this.codeAreaCityDao = codeAreaCityDao;
	}

	@Autowired
	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}

	@Autowired
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	@Autowired
	public void setCompanySceneDao(CompanySceneDao companySceneDao) {
		this.companySceneDao = companySceneDao;
	}

	@Autowired
	public void setJobBaseService(JobBaseService jobBaseService) {
		this.jobBaseService = jobBaseService;
	}

	/**
	 * 获得分页企业列表
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Company> getPage(Integer pageNumber, Integer pageSize) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		// 查询条件 GT 大于 ,LT 小于,GTE 大于等于, LTE 小于等于,EQ 等于, LIKE 包含
		searchParams.put("EQ_validation", 1 + "");// 在线的企业
		searchParams.put("EQ_isdel", 1 + "");// 未删除
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto");
		Specification<Company> spec = null;
		spec = buildSpecification(searchParams);
		return companyDao.findAll(spec, pageRequest);
	}
	
	/**
	 * 获得分页企业id列表
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Integer> findCompanyIdPage(Integer pageNumber, Integer pageSize) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto");
		return companyDao.findIdByValidationAndIsdel(1,1,pageRequest);
	}
	/**
	 * 得到企业id和分站前缀拼接的字符串（sitemap使用）
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Slice<String> findCompanyIdAndWebPrefixPage(Integer pageNumber, Integer pageSize) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto");
		return companyDao.findIdAndWebPrefixByValidationAndIsdel(1,1,pageRequest);
	}
}