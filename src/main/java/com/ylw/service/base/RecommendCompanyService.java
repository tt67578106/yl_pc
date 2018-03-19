package com.ylw.service.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

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

import com.ylw.entity.base.Company;
import com.ylw.entity.base.CompanyScene;
import com.ylw.entity.base.RecommendCompany;
import com.ylw.entity.job.JobBase;
import com.ylw.entity.vo.RecommendCompanyVo;
import com.ylw.repository.CompanySceneDao;
import com.ylw.repository.JobBaseDao;
import com.ylw.repository.RecommendCompanyDao;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.MemcachedUtil;

/**
 * @author Nicolas.Cai
 * @version 1.0
 * @since 1.0
 */

@Component
@Transactional
public class RecommendCompanyService {
	
	private RecommendCompanyDao recommendCompanyDao;
	
	private JobBaseDao jobBaseDao;
	
	private CompanySceneDao companySceneDao;


	public RecommendCompany getRecommendCompany(java.lang.Integer id){
		return recommendCompanyDao.findOne(id);
	}

	public void save(RecommendCompany entity){
		recommendCompanyDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		recommendCompanyDao.delete(id);
	}
	
	
	/**
	 * 查询推荐岗位
	 * @param key 关键key
	 * @param positionId 位置ID
	 * @return
	 */
	public List<RecommendCompanyVo>  findRecommendCompanyCache(String key, String recommendPositionCode,Integer branchId,boolean...rand){
			List<RecommendCompanyVo> recommendCompanyVo;
			try {
				recommendCompanyVo =  (List<RecommendCompanyVo>) MemcachedUtil.getCacheValue(key+branchId);
				if(recommendCompanyVo == null || recommendCompanyVo.size() == 0){
					List<RecommendCompany> RecommendCompanys = recommendCompanyDao.findByIsPublishAndRecommendPositionCodeAndOfflineTimeGreaterThan(2, recommendPositionCode, DateConvertUtils.getNow(),branchId,buildPageRequest(1,18,"sorting")).getContent();
					recommendCompanyVo = buildRecommendCompanyVo(RecommendCompanys);
					if(RecommendCompanys.size() == 0 && rand!=null && rand.length == 0){//如果没有，直接用上海的
						return findRecommendCompanyCache(key, recommendPositionCode, 1, true);
					}
					MemcachedUtil.setCache(key+branchId, 3600*24, recommendCompanyVo);
				}
				return recommendCompanyVo;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	/**
	 * 不走缓存直接查询
	 * @param key
	 * @param recommendPositionCode
	 * @param branchId
	 * @return
	 */
	public Page<RecommendCompany> findRecommendCompanyPage(String key, String recommendPositionCode,Integer branchId){
		return recommendCompanyDao.findByIsPublishAndRecommendPositionCodeAndOfflineTimeGreaterThan(2, recommendPositionCode, DateConvertUtils.getNow(),branchId,buildPageRequest(1,18,"sorting"));
	}

	public Page<RecommendCompany> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<RecommendCompany> spec = buildSpecification(userId.longValue(), searchParams);
		return recommendCompanyDao.findAll(spec, pageRequest);
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
		}else if("sorting".equals(sortType)){
			sort = new Sort(Direction.ASC, "sorting");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<RecommendCompany> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<RecommendCompany> spec = DynamicSpecifications.bySearchFilter(filters.values(), RecommendCompany.class);
		return spec;
	}

	/**
	 * 根据type查询推荐公司
	 * @param cachePcKeyCompanyRecommendtype
	 * @param positionCodeCompanyRecommendtypeCode
	 * @param limit
	 * @param branchId
	 * @return
	 */
	public List<RecommendCompanyVo> findRecommendCompanyByTypeCache(String cachePcKeyCompanyRecommendtype,String positionCodeCompanyRecommendtypeCode, int limit, Integer branchId,String type,boolean...rand) {
		List<RecommendCompanyVo> RecommendCompanyVo;
		try {
			RecommendCompanyVo = (List<com.ylw.entity.vo.RecommendCompanyVo>) MemcachedUtil.getCacheValue(cachePcKeyCompanyRecommendtype+branchId);
			if(RecommendCompanyVo == null || (RecommendCompanyVo.size() < limit)){
				List<RecommendCompany> recommendCompany = recommendCompanyDao.findByIsPublishAndRecommendPositionCodeAndOfflineTimeGreaterThan(2, positionCodeCompanyRecommendtypeCode, DateConvertUtils.getNow(),branchId,buildPageRequest(1,limit,"sorting")).getContent();
				RecommendCompanyVo = buildRecommendCompanyVo(recommendCompany,type);
				//如果没有，直接用上海的
				if(recommendCompany.size() == 0 && rand!=null && rand.length == 0){
					return findRecommendCompanyByTypeCache(cachePcKeyCompanyRecommendtype, positionCodeCompanyRecommendtypeCode, limit, 1,type,true);
				}
				MemcachedUtil.setCache(cachePcKeyCompanyRecommendtype+branchId, 3600*24, RecommendCompanyVo);
			}
			return RecommendCompanyVo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 通过recommendList产生VO集合[较少参数]
	 * @param recommendCompanyList
	 * @return
	 */
	private List<RecommendCompanyVo> buildRecommendCompanyVo(List<RecommendCompany> recommendCompanyList) {
		List<RecommendCompanyVo> recommendCompanyVos = new ArrayList<RecommendCompanyVo>();
		for(RecommendCompany recommendCompany:recommendCompanyList){
			Company company =new Company();
			if(recommendCompany.getCompany()!=null){
				company=recommendCompany.getCompany();
			}
			RecommendCompanyVo recommendCompanyVo = new RecommendCompanyVo(
					recommendCompany.getId(),
					company.getId(),
					company.getLogo()==null?null:company.getLogo().getImgpath(),
					company.getName(),
					company.getAbbreviation(),
					company.getPointOfPraise());
			recommendCompanyVos.add(recommendCompanyVo);
		}
		return recommendCompanyVos;
	}
	/**
	 * 通过recommendList产生VO集合
	 * @param recommendCompanyList
	 * @return
	 */
	private List<com.ylw.entity.vo.RecommendCompanyVo> buildRecommendCompanyVo(List<RecommendCompany> recommendCompany,String type) {
		List<RecommendCompanyVo> recommendCompanyVos = new ArrayList<RecommendCompanyVo>();
		for(RecommendCompany recommendCompanyEntity:recommendCompany){
			Company company =new Company();
			JobBase jobBase =new JobBase();
			String companysence = "";
			if(recommendCompanyEntity.getCompany()!=null){
				company=recommendCompanyEntity.getCompany();
				CompanyScene companyScene =  getCompanySceneByCompanyOrderByCreatetime(company.getId());
				companysence = (companyScene==null || companyScene.getImage()==null)?"":companyScene.getImage().getImgpath();
				jobBase= getJobBaseByCompanyIdOrderByCreatetime(company.getId());
			}
			RecommendCompanyVo recommendCompanyVo = new RecommendCompanyVo(
					recommendCompanyEntity.getId(),
					recommendCompanyEntity.getTitle(),
					recommendCompanyEntity.getRemark(),
					company.getId(),
					company.getLogo()==null?"":company.getLogo().getImgpath(),
							companysence,
					company.getName(),
					company.getAbbreviation(),
					jobBase.getId(),
					jobBase.getJobType(),
					jobBase.getJobLabel(),
					jobBase.getTitle(),
					jobBase.getTotalsalary(),
					jobBase.getJobDetail()==null?null:jobBase.getJobDetail().getSalaryfrom(),
					jobBase.getJobDetail()==null?null:jobBase.getJobDetail().getSalaryto());
			recommendCompanyVos.add(recommendCompanyVo);
		}
		return recommendCompanyVos;
	}

	/**
	 * 根据公司Id得到最近发布的工作
	 * @param companyId
	 * @param type
	 * @return
	 */
	public JobBase getJobBaseByCompanyIdOrderByCreatetime(Integer companyId) {
		List<JobBase> jobList = jobBaseDao.findJobBaseByCompanyIdOrderByCreatetime(companyId);
		if(jobList!=null &&jobList.size()>0){
			return jobList.get(0);
		}
		return new JobBase();
	}
	/**
	 * 取公司最新图片
	 * @param companyId
	 * @return
	 */
	public CompanyScene getCompanySceneByCompanyOrderByCreatetime(Integer companyId){
		List<CompanyScene> companyScenes = companySceneDao.findByCompanyIdAndIsshow(companyId,1,buildPageRequest(1,1,"createtime")).getContent();
		if(companyScenes.size()>0){
			return companyScenes.get(0);
		}
		return null;
	}
	@Autowired
	public void setRecommendCompanyDao(RecommendCompanyDao recommendCompanyDao) {
		this.recommendCompanyDao = recommendCompanyDao;
	}
	
	@Autowired
	public void setJobBaseDao(JobBaseDao jobBaseDao) {
		this.jobBaseDao = jobBaseDao;
	}
	@Autowired
	public void setCompanySceneDao(CompanySceneDao companySceneDao) {
		this.companySceneDao = companySceneDao;
	}
}