package com.ylw.service.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

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

import com.ylw.entity.code.CodeAreaCity;
import com.ylw.repository.CodeAreaCityDao;
import com.ylw.util.Constants;
import com.ylw.util.StoreCacheUtil;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class CodeAreaCityService {

	private CodeAreaCityDao codeAreaCityDao;

	public CodeAreaCity getCodeAreaCity(java.lang.Integer id){
		return codeAreaCityDao.findOne(id);
	}

	public void save(CodeAreaCity entity){
		codeAreaCityDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		codeAreaCityDao.delete(id);
	}
	
	public List<CodeAreaCity> findAll(){
		return (List<CodeAreaCity>) codeAreaCityDao.findAll();
	}
	
	/**
	 * 根据省查询城市列表
	 * @param provinceId
	 * @return
	 */
	public List<CodeAreaCity> findCityByProvinceId(java.lang.Integer provinceId){
		return codeAreaCityDao.findByProvinceId(provinceId);
	}
	
	public List<CodeAreaCity> findByProvinceId(Integer provinceid){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		ArrayList<CodeAreaCity> citys = (ArrayList<CodeAreaCity>)cache.get(Constants.CACHE_KEY_CITY).getObjectValue();
		ArrayList<CodeAreaCity> listcity = new ArrayList<CodeAreaCity>();
		for(CodeAreaCity city:citys){
			if(city.getProvinceId().equals(provinceid)){
				listcity.add(city);
			}
		}
		return listcity;
	}

	public void initCityCache(){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		cache.remove(Constants.CACHE_KEY_CITY);
		cache.put(new Element(Constants.CACHE_KEY_CITY, findAll()));
		
	}
	public Page<CodeAreaCity> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<CodeAreaCity> spec = buildSpecification(userId.longValue(), searchParams);
		return codeAreaCityDao.findAll(spec, pageRequest);
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
	private Specification<CodeAreaCity> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<CodeAreaCity> spec = DynamicSpecifications.bySearchFilter(filters.values(), CodeAreaCity.class);
		return spec;
	}

	@Autowired
	public void setCodeAreaCityDao(CodeAreaCityDao codeAreaCityDao) {
		this.codeAreaCityDao = codeAreaCityDao;
	}

	public CodeAreaCity getByBaiduCityId(Integer baiduCityId) {
//		codeAreaCityDao.getByBaiduCityId(baiduCityId);
		return null;
	}

	/**
	 * @description: 根据城市名称获取城市对象
	 * @method: getCodeAreaCity
	 * @author: Mark
	 * @date: 11:27 2018/3/1
	 * @param cityName
	 * @return: java.lang.Integer
	 */
	public CodeAreaCity findByAbbreviation(String cityName){
		return codeAreaCityDao.findByAbbreviation(cityName);
	}
}