package com.ylw.service.job;

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

import com.ylw.entity.job.JobType;
import com.ylw.repository.JobTypeDao;
import com.ylw.util.Constants;
import com.ylw.util.StoreCacheUtil;

/**
 * @author Nicolas.Cai
 * @version 1.0
 * @since 1.0
 */

@Component
@Transactional
public class JobTypeService {

	private JobTypeDao jobTypeDao;

	public JobType getJobType(java.lang.Integer id){
		return jobTypeDao.findOne(id);
	}

	public void save(JobType entity){
		jobTypeDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		jobTypeDao.delete(id);
	}
	/**
	 * 初始化缓存到ehcached
	 */
	public void initCache(){
		List<JobType> jobTypeList = jobTypeDao.findByStatusAndParentIdOrderBySortingAsc(1, 0);
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		cache.remove(Constants.CACHE_KEY_JOBTYPE);
		cache.put(new Element(Constants.CACHE_KEY_JOBTYPE, jobTypeList));
	}
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(String ids){
		String[] idArray = ids.split(",");
		for (String idStr : idArray) {
			jobTypeDao.delete(Integer.parseInt(idStr));
		}
	}
	/**
	 * 根据父id和状态查询
	 * @return
	 */
	public List<JobType> findJobTypeByParentId(Integer parentId){
		return jobTypeDao.findByStatusAndParentIdOrderBySortingAsc(1, parentId);
	}
	
	public Page<JobType> getPage(Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<JobType> spec = buildSpecification(searchParams);
		return jobTypeDao.findAll(spec, pageRequest);
	}
	/**
	 * 通过岗位名称模糊查询
	 * @param jobName
	 * @return
	 */
	public List<JobType> findByJobName(String jobName) {
		return jobTypeDao.findByStatusAndJobNameLike(1, jobName);
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
	private Specification<JobType> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<JobType> spec = DynamicSpecifications.bySearchFilter(filters.values(), JobType.class);
		return spec;
	}

	@Autowired
	public void setJobTypeDao(JobTypeDao jobTypeDao) {
		this.jobTypeDao = jobTypeDao;
	}
	/**
	 * 根据jobtype得到job的缩略图
	 * @param jobType
	 * @return
	 */
	public String getImageByJobType(String jobType) {
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		List<JobType> jobTypeList = (List<JobType>) cache.get(Constants.CACHE_KEY_JOBTYPE).getObjectValue();
		String image = null;
		for(JobType type :jobTypeList){
			if(jobType.contains(type.getJobName())){
				image = type.getThumbnialImage();
			}
		}
		return image;
	}

	/**
	 * 根据jobtypeid得到typename（sitemap使用）
	 * @param typeId
	 * @return
	 */
	public String getJobTypeNameById(Integer typeId) {
		return jobTypeDao.getTypeNameByIdAndStatus(typeId,1);
	}

	/**
	 * 根据父类id得到所有岗位类别的id列表
	 * @return
	 */
	public List<Integer> findJobTypeIdByParentIds(Integer parentId) {
		return jobTypeDao.findJobTypeIdByParentIds(parentId);
	}
}