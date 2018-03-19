package com.ylw.service.base;

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

import com.ylw.entity.base.Resume;
import com.ylw.entity.base.User;
import com.ylw.repository.ResumeDao;
import com.ylw.repository.UserDao;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class ResumeService {

	private ResumeDao resumeDao;
	
	private UserDao userDao;
	@Autowired
	private ResumeMdbService resumeMdbService;

	public Resume getByUserId(User user){
		if(user!=null){
			JSONObject resultMap = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(user.getId()), user.getLoginname(), null,null);
			Resume resume = resumeMdbService.findResume(resultMap);
			return resume;
		}
		return null;
	}
	
	public Resume getResume(java.lang.Integer id){
		return resumeDao.findOne(id);
	}

	public void save(Resume entity){
		resumeDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		resumeDao.delete(id);
	}
	
//	public void addheadimg(Image image,Integer userId){
//		List<Resume> resumes = resumeDao.findByUserid(userId);
//		if(resumes!=null&&resumes.size()>0){
//			resumes.get(0).setImage(image);
//			resumes.get(0).setIsSynHr(1);
//			resumeDao.save(resumes.get(0));
//		}
//	}

	public Page<Resume> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Resume> spec = buildSpecification(userId.longValue(), searchParams);
		return resumeDao.findAll(spec, pageRequest);
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
	private Specification<Resume> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<Resume> spec = DynamicSpecifications.bySearchFilter(filters.values(), Resume.class);
		return spec;
	}
	/**
	 * 处理所有没有resumeCode的简历
	 */
	public void excuteAllResumeCode(){
		
	}

	@Autowired
	public void setResumeDao(ResumeDao resumeDao) {
		this.resumeDao = resumeDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 报名和预约岗位是判断简历中的必填字段是否完善
	 * @param userid
	 * @param type
	 * @return
	 */
	public Boolean resumeIsComplete(Integer userid, Integer type) {
		Boolean integrity = true;
		User user = userDao.findOne(userid);
		Resume resume = getByUserId(user);
		if(resume!=null){
			if(type==2){
				if (StringUtils.isEmpty(resume.getName())) {//姓名
					integrity=false;
				}
				if (resume.getGender() == null||resume.getGender()==0) {//性别
					integrity=false;
				}
				if (StringUtils.isEmpty(resume.getIdCard())) {//身份证号
					integrity=false;
				}
				if (resume.getEducation() == null||resume.getEducation()==0) {//学历
					integrity=false;
				}
				if (resume.getNativeProvinceid()==null||resume.getNativeProvinceid()==0||resume.getNativeCityid() == null||resume.getNativeCityid()==0) {//籍贯
					integrity=false;
				}
				if (resume.getNation()==null||resume.getNation()==0) {//民族
					integrity=false;
				}
			}
			if(type==4){
				if (StringUtils.isEmpty(resume.getName())) {//姓名
					integrity=false;
				}
				if (resume.getJobTargetProvinceid()==null||resume.getJobTargetProvinceid()==0||resume.getJobTargetCityid()==null||resume.getJobTargetCityid()==0) {//意向城市
					integrity=false;
				}
				if (StringUtils.isEmpty(resume.getJobTarget())) {//意向职位
					integrity=false;
				}
			}
		}else{
			integrity=false;
		}
		return integrity;
	}
}