package com.ylw.service.base;

import java.util.ArrayList;
import java.util.Iterator;
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

import com.ylw.entity.base.DataDictionary;
import com.ylw.repository.DataDictionaryDao;
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
public class DataDictionaryService {

	private DataDictionaryDao dataDictionaryDao;
	
	public DataDictionary getDataDictionary(java.lang.Integer id){
		return dataDictionaryDao.findOne(id);
	}

	public void save(DataDictionary entity){
		dataDictionaryDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		dataDictionaryDao.delete(id);
	}
	
	/**
	 * 初始化到缓存
	 */
	public void initCache(){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		cache.remove(Constants.CACHE_KEY_DIC);
		cache.put(new Element(Constants.CACHE_KEY_DIC, dataDictionaryDao.findByStatus(1)));
	}

	/**
	 * 初始化规模缓存
	 */
	public void initDicStaffscale(){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		cache.remove(Constants.CACHE_KEY_DIC_STAFFSCALE);
		cache.put(new Element(Constants.CACHE_KEY_DIC_STAFFSCALE, dataDictionaryDao.findByStatusAndTypeid(1, 9)));
	}
	/**
	 * 初始化学历列表
	 */
	public void initDicEducation(){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		cache.remove(Constants.CACHE_KEY_EDUCATION);
		// 删除 1=不限   8=硕士     9=博士
		List<DataDictionary> findByStatus = dataDictionaryDao.findByStatusAndTypeid(1, 18);
			
		Iterator<DataDictionary> it = findByStatus.iterator();  
         while(it.hasNext()) {  
          DataDictionary s = it.next();  
          if(s.getName().equals("不限") ||s.getName().equals("硕士") ||s.getName().equals("博士")) {  
              it.remove();  
          }  
      }  
		cache.put(new Element(Constants.CACHE_KEY_EDUCATION, findByStatus));
	}
	
	/**
	 * 初始求职意向缓存
	 */
	public void initDicIndustry(){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		cache.remove(Constants.CACHE_KEY_DIC_INDUSTRY);
		cache.put(new Element(Constants.CACHE_KEY_DIC_INDUSTRY, dataDictionaryDao.findByStatusAndTypeid(1, 20)));
	}
	/**
	 * 初始行业
	 */
	public void initDicIndustryType(){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		cache.remove(Constants.CACHE_KEY_DIC_INDUSTRY_TYPE);
		cache.put(new Element(Constants.CACHE_KEY_DIC_INDUSTRY_TYPE, dataDictionaryDao.findByStatusAndTypeid(1, 14)));
	}
	/**
	 * 初始化薪水
	 */
	public void initDicSalary(){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		cache.remove(Constants.CACHE_KEY_DIC_SALARY);		
		cache.put(new Element(Constants.CACHE_KEY_DIC_SALARY, dataDictionaryDao.findByStatusAndTypeid(1, 19)));
	}
	/**
	 * 民族chushihua 
	 */
	public void initNation(){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		cache.remove(Constants.CACHE_KEY_DIC_NATION);
		cache.put(new Element(Constants.CACHE_KEY_DIC_NATION, dataDictionaryDao.findByStatusAndTypeid(1, 22)));
	}
	/**
	 * 求职状态 
	 */
	public void initEmploymentStatus (){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		cache.remove(Constants.CACHE_KEY_DIC_EMPLOYMENTSTATUS);
		cache.put(new Element(Constants.CACHE_KEY_DIC_EMPLOYMENTSTATUS, dataDictionaryDao.findByStatusAndTypeid(1, 25)));
	}
	/**
	 * 婚姻状况 
	 */
	public void initMaritalStatus(){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		cache.remove(Constants.CACHE_KEY_DIC_MARITALSTATUS);
		cache.put(new Element(Constants.CACHE_KEY_DIC_MARITALSTATUS, dataDictionaryDao.findByStatusAndTypeid(1, 27)));
	}
	/**
	 * 工作年限
	 */
	public void initWorkYear(){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		cache.remove(Constants.CACHE_KEY_DIC_WORKYEAR);
		cache.put(new Element(Constants.CACHE_KEY_DIC_WORKYEAR, dataDictionaryDao.findByStatusAndTypeid(1, 26)));
	}
	
	public Page<DataDictionary> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<DataDictionary> spec = buildSpecification(userId.longValue(), searchParams);
		return dataDictionaryDao.findAll(spec, pageRequest);
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
	 * 从缓存得到初始化对象
	 * @param cacheKeyDicIndustryType
	 * @return
	 */
	public List<DataDictionary> getIndustryTypeList(String cacheKeyDicIndustryType) {
		List<DataDictionary> dataList = new ArrayList<DataDictionary>();
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		dataList = (List<DataDictionary>) cache.get(cacheKeyDicIndustryType).getObjectValue();
		return dataList;
	}
	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<DataDictionary> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<DataDictionary> spec = DynamicSpecifications.bySearchFilter(filters.values(), DataDictionary.class);
		return spec;
	}

	@Autowired
	public void setDataDictionaryDao(DataDictionaryDao dataDictionaryDao) {
		this.dataDictionaryDao = dataDictionaryDao;
	}

	/**
	 * 得到所有企业行业的code（sitemap使用）
	 * @return
	 */
	public List<Integer> findCompanyIndustryids() {
		return dataDictionaryDao.findCompanyIndustryids();
	}
	/**
	 * 查询学历code
	 * @param name
	 * @return
	 */
	public String findCodeByNameAndTypeid(String name) {
		return dataDictionaryDao.findCodeByNameAndTypeid(name);
	}
	/**
	 * 查询民族code
	 * @param nation
	 * @return
	 */
	public String findNationCodeByNameAndTypeid(String nation) {
		return dataDictionaryDao.findNationCodeByNameAndTypeid(nation);
	}
	/**
	 * 查询职位code
	 * @param selfIntro
	 * @return
	 */
	public String findSelfIntroByNameAndTypeid(String selfIntro) {
		return dataDictionaryDao.findSelfIntroByNameAndTypeid(selfIntro);
	}
	/**
	 * 通过code查询薪资name
	 * @param bottledParameter
	 * @return
	 */
	public String findSalaryNameBycode(String bottledParameter) {
		return dataDictionaryDao.findSalaryNameBycode(bottledParameter);
	}
}