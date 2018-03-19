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

import com.ylw.entity.base.RecommendAd;
import com.ylw.entity.vo.RecommendAdVo;
import com.ylw.repository.RecommendAdDao;
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
public class RecommendAdService {

	private RecommendAdDao recommendAdDao;

	public RecommendAd getRecommendAd(java.lang.Integer id){
		return recommendAdDao.findOne(id);
	}

	public void save(RecommendAd entity){
		recommendAdDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		recommendAdDao.delete(id);
	}
	/**
	 * 查询各种首页广告
	 * @param key 关键key
	 * @param positionId 位置ID
	 * @return
	 */
	public List<RecommendAdVo> findRecommendAdCache(String key, String recommendPositionCode,int limit,int branchId,boolean...rand){
		List<RecommendAdVo> recommendAdVos = null;
		recommendAdVos = (List<RecommendAdVo>) MemcachedUtil.getCacheValue(key+branchId);
		if(recommendAdVos == null || recommendAdVos.size() ==0){
			List<RecommendAd> recommend = recommendAdDao.findByIsPublishAndRecommendPositionCodeAndOfflineTimeGreaterThan(2, recommendPositionCode, DateConvertUtils.getNow(),branchId,buildPageRequest(1,limit,"sorting")).getContent();
			//封装vo对象
			recommendAdVos = buildRecommendAdVO(recommend);
			//如果没有，直接用上海的
			if(recommend.size() == 0 && rand!=null && rand.length == 0){
				return findRecommendAdCache(key, recommendPositionCode, limit, 1, true);
			}
			MemcachedUtil.setCache(key+branchId, 3600, recommendAdVos);
		}
		return recommendAdVos;
	}
	/**
	 * 万里行首页推荐位
	 * @param cacheKeyHomeShufflingJobs
	 * @param positionCodeHomeShufflingJobsCode
	 * @param i
	 * @return
	 */
	public List<RecommendAdVo> findRecommendAdCache(String key,String cacheKeyWanlixingType, int limit) {
		List<RecommendAdVo> recommendAdVos = null;
		recommendAdVos =  (List<RecommendAdVo>) MemcachedUtil.getCacheValue(key);
		if(recommendAdVos == null || recommendAdVos.size() < limit){
			List<RecommendAd> recommend = recommendAdDao.findByIsPublishAndRecommendPositionCodeAndOfflineTimeGreaterThan(2, cacheKeyWanlixingType, DateConvertUtils.getNow(),buildPageRequest(1,limit,"sorting")).getContent();
			//封装vo对象
			recommendAdVos = buildRecommendAdVO(recommend);
			MemcachedUtil.setCache(key, 3600, recommendAdVos);
		}
		return recommendAdVos;
	}
	/**
	 * 查询各种首页广告[不走缓存]
	 * @param key
	 * @param recommendPositionCode
	 * @param limit
	 * @param branchId
	 * @return
	 */
	public List<RecommendAdVo> findRecommendAd(String key, String recommendPositionCode,int limit,int branchId,boolean...rand){
		List<RecommendAd> adList = recommendAdDao.findByIsPublishAndRecommendPositionCodeAndOfflineTimeGreaterThan(2, recommendPositionCode, DateConvertUtils.getNow(),branchId,buildPageRequest(1,limit,"sorting")).getContent();
		if(adList.size() == 0 && rand!=null && rand.length == 0){//如果没有，直接用上海的
			return findRecommendAd(key, recommendPositionCode, limit, 1, true);
		}
		return null;
//		return adList;
	}
	
	public Page<RecommendAd> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<RecommendAd> spec = buildSpecification(userId.longValue(), searchParams);
		return recommendAdDao.findAll(spec, pageRequest);
	}

	/**
	 * 封装recommengAd  vo 对象
	 */
	public List<RecommendAdVo> buildRecommendAdVO(List<RecommendAd> recommend){
		List<RecommendAdVo> recommendAdVos = new ArrayList<RecommendAdVo>();
		for(RecommendAd recommendAdEntity:recommend){
			RecommendAdVo recommendAdVo = new RecommendAdVo(
					recommendAdEntity.getId(),
					recommendAdEntity.getTitle(), 
					recommendAdEntity.getSubtitle(), 
					recommendAdEntity.getImg()==null?"":recommendAdEntity.getImg().getImgpath(), 
					recommendAdEntity.getImgAlt(), 
					recommendAdEntity.getStyle(),
					recommendAdEntity.getLink());
			recommendAdVos.add(recommendAdVo);
		}
		return recommendAdVos;
	}
	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("sorting".equals(sortType)) {
			sort = new Sort(Direction.ASC, "sorting");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<RecommendAd> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<RecommendAd> spec = DynamicSpecifications.bySearchFilter(filters.values(), RecommendAd.class);
		return spec;
	}

	@Autowired
	public void setRecommendAdDao(RecommendAdDao recommendAdDao) {
		this.recommendAdDao = recommendAdDao;
	}

}