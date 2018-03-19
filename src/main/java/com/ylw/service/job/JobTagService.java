package com.ylw.service.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ylw.entity.job.JobTag;
import com.ylw.repository.JobTagDao;

/**
 * @author Nicolas.Cai
 * @version 1.0
 * @since 1.0
 */

@Component
@Transactional
public class JobTagService {

	private JobTagDao jobTagDao;
	
	public JobTag getJobTag(java.lang.Integer id){
		return jobTagDao.findOne(id);
	}

	public void save(JobTag entity){
		jobTagDao.save(entity);
	}

	public void delete(String ids){
		String[] idArray = ids.split(",");
		for (String idStr : idArray) {
			jobTagDao.delete(Integer.parseInt(idStr));
		}
	}

	/**
	 * 根据状态，父类型查询标签list
	 * @return
	 */
	public Page<JobTag> findJobTagList(Integer pageNumber,Integer pageSize,Integer Status, Integer parentId,String sortType){
		Page<JobTag> tagList =  jobTagDao.findByStatusAndParentId(Status,parentId,buildPageRequest(pageNumber,pageSize,"sorting"));
		return tagList;
	}

	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("sorting".equals(sortType)) {
			sort = new Sort(Direction.ASC, "sorting");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}
	
	@Autowired
	public void setJobTagDao(JobTagDao jobTagDao) {
		this.jobTagDao = jobTagDao;
	}
	/**
	 * 根据标签名和状态查询对象集合
	 * @param tagName
	 * @return
	 */
	public List<JobTag> findByTagName(String tagName) {
		return jobTagDao.findByStatusAndTagNameLike(1,tagName);
	}
	
	
	/**
	 * 根据标签父类id和状态查询对象集合
	 * @param tagName
	 * @return
	 */
	public List<JobTag> findByParentId(Integer parentId) {
		return jobTagDao.findByStatusAndParentId(1,parentId);
	}

	/**
	 * 根据福利标签id得到福利标签名字（sitemap使用）
	 * @param tagId
	 * @return
	 */
	public String getJobTageNameById(Integer tagId) {
		return jobTagDao.getTagNameByIdAndStatus(tagId,1);
	}

	/**
	 * 得到所有福利标签的id
	 * @return
	 */
	public List<Integer> findJobTagIds() {
		return jobTagDao.findJobTagIds();
	}

}