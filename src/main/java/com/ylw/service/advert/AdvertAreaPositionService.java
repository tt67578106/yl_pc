package com.ylw.service.advert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import com.alibaba.fastjson.JSON;
import com.ylw.entity.advert.AdvertAreaPosition;
import com.ylw.entity.base.Branch;
import com.ylw.entity.base.BranchCity;
import com.ylw.entity.job.JobBase;
import com.ylw.entity.vo.AdvResource;
import com.ylw.entity.vo.AdvResourcePubRecord;
import com.ylw.entity.vo.AdvertPositionVo;
import com.ylw.repository.BranchDao;
import com.ylw.repository.advert.AdvertAreaPositionDao;
import com.ylw.util.Constants;
import com.ylw.util.MemcachedUtil;

/**
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2015-7-16 19:56:28 
 */

@Component
@Transactional
public class AdvertAreaPositionService {
	/**
	 * Dao操作对象
	 */
	private AdvertAreaPositionDao advertAreaPositionDao;

	@PersistenceContext
	private EntityManager em;
	private BranchDao branchDao;
	private Map<String, String> defaultImgMap;
	/**
	 * 拿默认图片【小】
	 * @return
	 */
	public String getDefaultImgByName(String jobTypeName) {
		if(defaultImgMap == null){
			defaultImgMap = new HashMap<>();
			defaultImgMap.put("普工","http://img.youlanw.com/320/tmp/20150522142923207AH7.jpg");
			defaultImgMap.put("客服","http://img.youlanw.com/320/tmp/201505221642177547BE.jpg");
			defaultImgMap.put("销售","http://img.youlanw.com/320/tmp/20150522164153976XHF.jpg");
			defaultImgMap.put("服务员","http://img.youlanw.com/320/tmp/20150522164238952Q9I.jpg");
			defaultImgMap.put("物流仓储","http://img.youlanw.com/320/tmp/20150522171126601MOD.jpg");
			defaultImgMap.put("司机","http://img.youlanw.com/320/tmp/20150522164920504GI0.jpg");
			defaultImgMap.put("车床工","http://img.youlanw.com/320/tmp/20150522163946724FLU.jpg");
			defaultImgMap.put("机修汽修","http://img.youlanw.com/320/tmp/20150522164059452SDY.jpg");
			defaultImgMap.put("电焊工","http://img.youlanw.com/320/tmp/20150522163925751L5G.jpg");
			defaultImgMap.put("营业员","http://img.youlanw.com/320/tmp/20150522164429591AF4.jpg");
			defaultImgMap.put("市场公关","http://img.youlanw.com/320/tmp/20150522171651173FLL.jpg");
			defaultImgMap.put("财务会计","http://img.youlanw.com/320/tmp/20150522171617205GRG.jpg");
			defaultImgMap.put("行政后勤","http://img.youlanw.com/320/tmp/20150522171203988WKF.jpg");
			defaultImgMap.put("贸易采购","http://img.youlanw.com/320/tmp/2015052217163374916P.jpg");
			defaultImgMap.put("建筑工","http://img.youlanw.com/320/tmp/20150522164132412K3F.jpg");
			defaultImgMap.put("后厨","http://img.youlanw.com/320/tmp/201505221649557734GI.jpg");
			defaultImgMap.put("家政保洁","http://img.youlanw.com/320/tmp/20150522165247997D8E.jpg");
			defaultImgMap.put("印刷工","http://img.youlanw.com/320/tmp/20150522164115378MDF.jpg");
			defaultImgMap.put("纺织工","http://img.youlanw.com/320/tmp/20150522164003404NDH.jpg");
			defaultImgMap.put("其它","http://img.youlanw.com/320/tmp/20150522171144501IED.jpg");
		}
		String timg = defaultImgMap.get(jobTypeName);
		timg = timg==null?"http://img.youlanw.com/320/tmp/20150522171144501IED.jpg":timg;
		return timg;
	}

	@Autowired
	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}
	
	/** 
	 * 注入Dao操作对象
	 */
	@Autowired
	public void setAdvertAreaPositionDao(AdvertAreaPositionDao advertAreaPositionDao) {
		this.advertAreaPositionDao = advertAreaPositionDao;
	}

	/**
	 * 通过Id查询对象
	 * @param id
	 * @return
	 */
	public AdvertAreaPosition getAdvertAreaPosition(java.lang.Integer id){
		return advertAreaPositionDao.findOne(id);
	}

	/**
	 * 保存单个对象
	 * @param entity
	 */
	public void save(AdvertAreaPosition entity){
		advertAreaPositionDao.save(entity);
	}

	/**
	 * 根据Id删除单个对象
	 * @param id
	 */
	public void delete(java.lang.Integer id){
		advertAreaPositionDao.delete(id);
	}
	
	
	/**
	 * 查询各种首页广告区块
	 * @param advPositionCode
	 * @param pageSize
	 * @param branchId
	 * @param rand
	 * @return
	 */
	public List<AdvertPositionVo> findAdvertAreaCache(String cacheKey, Integer branchId,int pageSize,boolean...rand){
		List<AdvertAreaPosition> advertAreaPositions = advertAreaPositionDao.findByBranchId(branchId,buildPageRequest(1,pageSize,"sorting")).getContent();
		//封装vo对象
		List<AdvertPositionVo> advertPositionVos = buildAdvertPositionVo(advertAreaPositions);
		//如果没有，直接用上海的
		if(advertPositionVos.size() == 0 && rand!=null && rand.length == 0){
			return findAdvertAreaCache(cacheKey,1, pageSize, true);
		}
		return advertPositionVos;
	}
	
	
	/**
	 * 封装AdvertPosition  vo 对象
	 */
	public List<AdvertPositionVo> buildAdvertPositionVo(List<AdvertAreaPosition> advertAreaPositions){
		List<AdvertPositionVo> advertPositionVos = new ArrayList<AdvertPositionVo>();
		for (AdvertAreaPosition areaPosition : advertAreaPositions) {
			AdvertPositionVo advertPositionVo = new AdvertPositionVo(
					areaPosition.getId(), 
					areaPosition.getBranchId(), 
					areaPosition.getAreaName(), 
					areaPosition.getAreaIcon(), 
					areaPosition.getAdvPositionCode(), 
					areaPosition.getAreaColor(), 
					areaPosition.getLinkUrl(), 
					areaPosition.getAreaRemark(), 
					areaPosition.getLinkList(),
					makeAdvertResourceRecordByCityAndPosition(areaPosition.getBranchId(),areaPosition.getAdvPositionCode(), areaPosition.getAreaName())
//					findAdvertResourceRecordByCityIdAndPositionId(areaPosition.getBranchId(),areaPosition.getAdvPositionCode())
					);
			advertPositionVos.add(advertPositionVo);
		}
		return advertPositionVos;
	}
	/**
	 * 汉字变成简化版的Unicode
	 * @param gbString
	 * @return
	 */
	private String gbEncodingLte(final String gbString) {
		char[] utfBytes = gbString.toCharArray();
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
//			unicodeBytes = unicodeBytes + "\\u" + hexB;
			unicodeBytes = unicodeBytes + "u" + hexB;
		}
		return unicodeBytes;
	}
	/**
	 * 新逻辑 20170831
	 * 除名企招聘之外，其他所有右侧中间的广告位使用数据库查询关键字,数量不超过7个
	 * 
	 * @param branchId
	 * @param positionId
	 * @param areaName
	 * @return
	 */
	private List<AdvResourcePubRecord> makeAdvertResourceRecordByCityAndPosition(Integer branchId, String positionId, String areaName){
		List<AdvResourcePubRecord> rtList = null;// new ArrayList<>();
		String rtListStr = null;
		String cacheKey = "adv_pc_home_20170831_" + positionId + branchId + gbEncodingLte(areaName);
		rtListStr = (String) MemcachedUtil.getCacheValue(cacheKey);

		if ("名企招聘".equals(areaName)) {
			rtList = findAdvertResourceRecordByCityIdAndPositionId(branchId, positionId);
			return rtList;
		} else {
			if (StringUtils.isBlank(rtListStr) || rtListStr.length() < 4) {
				List<AdvResourcePubRecord> _tList = new ArrayList<>();
				Page<JobBase> dbPage = getHqlPage(areaName, 1, 7, "updatetime", branchId);
				//区块广告位没有就获取""
				if (dbPage == null || dbPage.getTotalElements() == 0) {
					dbPage = getHqlPage("", 1,7 , "updatetime", branchId);
				}
				//有   但是  <7 的时候     已经有的职位+""职位
				if(dbPage == null || dbPage.getTotalElements()  < 7){
					dbPageCirculationList(_tList, dbPage);
					long page = dbPage.getTotalElements();
					int a = Integer.parseInt(String.valueOf(page)); 
						int c=7-a;
						dbPage = getHqlPage("", 2,c , "updatetime", branchId);
					
				}
				
				dbPageCirculationList(_tList, dbPage);
				rtList = _tList;
			} else {
				rtList = JSON.parseArray(rtListStr, AdvResourcePubRecord.class);
				return rtList;
			}
		}
		if (rtList != null) {
			rtListStr = JSON.toJSONString(rtList);
			MemcachedUtil.setCache(cacheKey, 43200, rtListStr);
		}
		return rtList;
	}
	//循环区块广告位
	private void dbPageCirculationList(List<AdvResourcePubRecord> _tList, Page<JobBase> dbPage) {
		for (JobBase jobBase : dbPage) {
			AdvResourcePubRecord record = new AdvResourcePubRecord();
			AdvResource resource = new AdvResource();
			resource.setResourceType(1);
			resource.setResourceValue(jobBase.getId() + "");
			String title = StringUtils.isNotBlank(jobBase.getTitle()) ? jobBase.getTitle()
					: jobBase.getJobname();
			resource.setTitle(title);
			resource.setDescription(jobBase.getJobLabel());
			String jobType = jobBase.getJobType();
			if (StringUtils.isNotBlank(jobType)) {
				int endIndex = jobType.indexOf(",");
				endIndex = endIndex > 0 ? endIndex : jobType.length();
				jobType = jobType.substring(0, endIndex);
			}
			resource.setLabel1(jobType);
			resource.setSubtitle("￥" + jobBase.getTotalsalary());
			String thumbImageUrl = jobBase.getThumbnialImage() != null
					? jobBase.getThumbnialImage().getImgpath() : "";
			if (StringUtils.isBlank(thumbImageUrl)) {
				thumbImageUrl = getDefaultImgByName(jobType);
			} else {
				//三种情况 1全路径  2半路径   3半路径前面有一个 /
				if(thumbImageUrl.indexOf("http")!=-1){  
					resource.setThumbImageUrl(thumbImageUrl);
				}else{ 
					if(thumbImageUrl.startsWith("/")){
						thumbImageUrl= thumbImageUrl.substring(1);
					}
				thumbImageUrl = "http://img.youlanw.com/320/" + thumbImageUrl;
				} 
			}
			resource.setThumbImageUrl(thumbImageUrl);
			record.setResource(resource);
			_tList.add(record);
		}
	}
	/**
	 * 拼接数据库语句
	 * @param jobNameKeywords
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @param branchId
	 * @return
	 */
	private Page<JobBase> getHqlPage(String jobNameKeywords, int pageNumber, int pageSize, String sortType, Integer branchId){
		StringBuffer hql = new StringBuffer("select job from JobBase job where job.jobConfig.isPublish = 1 ");
		if(StringUtils.isNotBlank(jobNameKeywords)){
			hql.append(" and (job.jobname like :jobname or job.title like :jobname or job.jobType like :jobname) ");
		}
		List<Integer> cityIdList = findBranchCityIdList(branchId);
		if(branchId!=null && cityIdList.size()>0){
			hql.append(" and job.city.id in :cityList ");
		}
		hql.append(" order by job.jobConfig.isUrgency desc");
		if ("auto".equals(sortType)) {
			hql.append(",job.id asc ");
		} else if ("updatetime".equals(sortType)) {
			hql.append(",job.updatetime desc ");
		} else if("salaryasc".equals(sortType)){
			hql.append(",job.jobDetail.salaryfrom+job.jobDetail.salaryto asc ");
		}else if("salarydesc".equals(sortType)){
			hql.append(",job.jobDetail.salaryfrom+job.jobDetail.salaryto desc ");
		}
		hql.append(",job.city.id ");
		Query countQuery = em.createQuery(hql.toString().replace("select job from", "select count(1) from"));
		Query query = em.createQuery(hql.toString());
		if(StringUtils.isNotBlank(jobNameKeywords)){
			query.setParameter("jobname", "%"+jobNameKeywords+"%");
			countQuery.setParameter("jobname", "%"+jobNameKeywords+"%");
		}
		if(branchId!=null && cityIdList.size()>0){
			query.setParameter("cityList", cityIdList);
			countQuery.setParameter("cityList", cityIdList);
		}
		Long totalSize = (Long) countQuery.getSingleResult();
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		query.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		query.setMaxResults(pageRequest.getPageSize());
		Page<JobBase> page = new PageImpl<JobBase>(query.getResultList(), pageRequest, totalSize);
		return page;
	}
	/**
	 * 根据所选分站查询城市列表
	 * @param branchId
	 * @return
	 */
	private List<Integer> findBranchCityIdList(Integer branchId){
		List<Integer> cityList = new ArrayList<Integer>();
		Branch branch = branchDao.findOne(branchId);
		if(branch != null && branch.getBranchCityList()!=null){
			List<BranchCity> bcityList = branch.getBranchCityList();
			for (BranchCity branchCity : bcityList) {
				cityList.add(branchCity.getCityId());
			}
		}
		return cityList;
	}
	/**
	 * 根据分站id 和广告位code得到广告位信息列表
	 * @param branchId
	 * @param positionId
	 * @param rand
	 * @return
	 */
	public List<AdvResourcePubRecord> findAdvertResourceRecordByCityIdAndPositionId(Integer branchId, String positionId){
		try {
			String advResourcePubRecordStr = null; 
			advResourcePubRecordStr = (String) MemcachedUtil.getCacheValue(positionId+branchId);
			if(StringUtils.isBlank(advResourcePubRecordStr)||advResourcePubRecordStr.replaceAll(" ", "").length() < 3){
				RestTemplate restTemplate=new RestTemplate();
				if(branchId!=null&&branchId>0){
					Branch branch = branchDao.findOne(branchId);
					if(branch!=null&&branch.getDefaultCityId()!=null&&branch.getDefaultCityId()>0){
						advResourcePubRecordStr=restTemplate.getForObject(Constants.SEND_APP_API_URL+"adv/findAdvertResourceRecordByCityIdAndPositionId?limit=50&cityId="+branch.getDefaultCityId()+"&positionId="+positionId, String.class );
					}
				}
				//如果没有，默认全国
				if(StringUtils.isBlank(advResourcePubRecordStr)||advResourcePubRecordStr.replaceAll(" ", "").length() < 3){
					advResourcePubRecordStr=restTemplate.getForObject(Constants.SEND_APP_API_URL+"adv/findAdvertResourceRecordByCityIdAndPositionId?limit=50&cityId=&positionId="+positionId, String.class );
				}
				MemcachedUtil.setCache(positionId+branchId, 43200, advResourcePubRecordStr);//从半小时改成半天
			}
			return JSON.parseArray(advResourcePubRecordStr, AdvResourcePubRecord.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询分页对象
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<AdvertAreaPosition> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<AdvertAreaPosition> spec = buildSpecification(userId.longValue(), searchParams);
		return advertAreaPositionDao.findAll(spec, pageRequest);
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
		} else if ("sorting".equals(sortType)){
			sort = new Sort(Direction.ASC, "sorting");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}
	
	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<AdvertAreaPosition> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<AdvertAreaPosition> spec = DynamicSpecifications.bySearchFilter(filters.values(), AdvertAreaPosition.class);
		return spec;
	}
}