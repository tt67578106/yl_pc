package com.ylw.service.community;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
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
import com.ylw.entity.community.Community;
import com.ylw.repository.CompanyDao;
import com.ylw.repository.ImageDao;
import com.ylw.repository.SysUserDao;
import com.ylw.repository.community.CommunityDao;
import com.ylw.util.Constants;

/**
 * @author Frank.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class CommunityService {

	private CommunityDao communityDao;
	private CompanyDao companyDao;
	private SysUserDao sysUserDao;
	private ImageDao imageDao;

	public Community getCommunity(java.lang.Integer id){
		return communityDao.findOne(id);
	}

	public void save(Community entity){
		communityDao.save(entity);
	}
	/**
	 * 删除企业社区
	 * @param id
	 * @return
	 */
	public  String delete(java.lang.Integer id){
		if(id != null){
			Community community = communityDao.findOne(id);
			community.setIsDel(2);
			communityDao.save(community);
			return Constants.RETURN_STATUS_SUCCESS;
		}else{
			return Constants.RETURN_STATUS_FAILURE;
		}
	}
	/**
	 * 新增社区
	 * @param cityId
	 * @param companyId
	 * @param userId
	 * @return
	 */
	public int saveCommunity(Integer cityId,Integer companyId,Integer userId,String summary,String communityName,Integer imgId,String lng,String lat) {
		if(communityDao.findByCompanyId(companyId).size() > 0 ){
			return -1;
		}else{
			Community community = new Community();
			Company company = companyDao.findOne(companyId);
			community.setCityId(cityId);
			community.setCompany(company);
			community.setSummary(summary);
			if(communityName != null && !communityName.equals("")){
				community.setCommunityName(communityName);
			}else{
				community.setCommunityName(company.getAbbreviation());
			}
			if(imgId != null){
				community.setImage(imageDao.findOne(imgId) == null?null:imageDao.findOne(imgId));
			}
			community.setCreateTime(new Date());
			community.setCreateUser(sysUserDao.findOne(userId));
			community.setAddCount(0);
			community.setFollowCount(0);
			community.setLng(lng);
			community.setLat(lat);
			community.setIsDel(1);
			communityDao.save(community);
			return community.getId();
		}
	}
	/**
	 * 修改社区
	 * @param id
	 * @param cityId
	 * @param companyId
	 * @param userId
	 * @return
	 */
	public int updateCommunity(Integer id,Integer cityId,Integer companyId,Integer userId,String summary,String communityName,Integer imgId,String lng,String lat) {
		Company company = companyDao.findOne(companyId);  //临时这样处理
		if(id != null){
			Community community = communityDao.findOne(id);
			community.setCityId(cityId);
			community.setCompany(company);
			community.setSummary(summary);
			if(communityName != null && !communityName.equals("")){
				community.setCommunityName(communityName);
			}else{
				community.setCommunityName(company.getAbbreviation());
			}
			if(imgId != null){
				community.setImage(imageDao.findOne(imgId) == null?null:imageDao.findOne(imgId));
			}
			community.setUpdateTime(new Date());
			community.setLng(lng);
			community.setLat(lat);
			community.setUpdateUser(sysUserDao.findOne(userId));
			communityDao.save(community);
			return community.getId();
		}else{
			Community community = new Community();
			community.setCityId(cityId);
			community.setCompany(company);
			community.setSummary(summary);
			if(communityName != null && !communityName.equals("")){
				community.setCommunityName(communityName);
			}else{
				community.setCommunityName(company.getAbbreviation());
			}
			community.setCreateTime(new Date());
			community.setCreateUser(sysUserDao.findOne(userId));
			community.setAddCount(0);
			community.setLng(lng);
			community.setLat(lat);
			community.setFollowCount(0);
			communityDao.save(community);
			return community.getId();
		}
	}
	/**
	 * 分页查询社区信息
	 * @param userId
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 * @throws Exception 
	 */
	public Page<Community> getUserPage(Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){
		if(searchParams.containsKey("communityName"))//ID 名称 简称
		{
			if(searchParams.get("communityName").toString() != ""){
				if(!StringUtils.isNumeric(searchParams.get("communityName").toString())){
					searchParams.put("LIKE_communityName", searchParams.get("communityName").toString());
				}else {
					searchParams.put("EQ_id", searchParams.get("communityName").toString());
				}
			}
			searchParams.remove("communityName");
		}
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Community> spec = buildSpecification(searchParams);
//		List<Community> pageList = (List<Community>) communityDao.findAll();
//		for (Community community : pageList) {
//			Map<String, Double> map=new HashMap<String, Double>();
//			map = getLngAndLat(community.getCompany().getAddress());
//			System.out.println("经度："+map.get("lng")+"---纬度："+map.get("lat"));
//			communityDao.updateLngLat(community.getId(), map.get("lng")+"", map.get("lat")+"");
//		}
		return communityDao.findAll(spec, pageRequest);
	}
	
	/**
	 * 根据companyName查询企业名称
	 * @param isdel
	 * @return
	 */
	public List<Community> findByCommunityName(String companyName){
		return communityDao.findByCommunityNameLike(companyName.trim());
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
	private Specification<Community> buildSpecification(Map<String, Object> searchParams) {
		searchParams.put("EQ_isDel","1");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Community> spec = DynamicSpecifications.bySearchFilter(filters.values(), Community.class);
		return spec;
	}

	@Autowired
	public void setCommunityDao(CommunityDao communityDao) {
		this.communityDao = communityDao;
	}
	@Autowired
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	@Autowired
	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	@Autowired
	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}
	
}