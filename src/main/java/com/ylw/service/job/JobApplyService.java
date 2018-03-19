package com.ylw.service.job;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springside.modules.persistence.SearchFilter.Operator;

import com.ylw.entity.base.User;
import com.ylw.entity.job.JobApply;
import com.ylw.entity.job.JobApplyFlowLog;
import com.ylw.entity.job.JobBase;
import com.ylw.entity.vo.ApplyVo;
import com.ylw.repository.JobApplyDao;
import com.ylw.repository.JobApplyFlowLogDao;
import com.ylw.repository.JobBaseDao;
import com.ylw.repository.UserDao;
import com.ylw.service.base.ResumeMdbService;
import com.ylw.service.base.UserService;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class JobApplyService {
	
	private JobApplyDao jobApplyDao;
	
	private JobBaseDao jobBaseDao;
	
	private UserDao userDao;
	
	private JobApplyFlowLogDao jobApplyFlowLogDao;
	@Autowired
	private ResumeMdbService resumeMdbService;

	public JobApply getJobApply(java.lang.Integer id) {
		return jobApplyDao.findOne(id);
	}

	public void save(JobApply entity) {
		jobApplyDao.save(entity);
	}

	public void delete(java.lang.Integer id) {
		jobApplyDao.delete(id);
	}
	
	/**
	 * 根据用户id和type得到报名数量
	 * @param userId
	 * @param type
	 * @return
	 */
	public Integer countByUserIdAndType(Integer userId,Integer type) {
		return jobApplyDao.countByUseridAndType(userId,type);
	}
	/**
	 * 根据报名id得到订单流程日志
	 * @param applyId
	 * @return
	 */
	public List<JobApplyFlowLog> findApplyFlowLogById(Integer applyId) {
		return jobApplyFlowLogDao.findByJobApplyId(applyId);
	}
	
	/**
	 * 根据用户id得到快速投递记录
	 * 
	 * type 类型（1：无登录报名、2：有登录报名） status 状态（0：未处理、1处理中、2：已处理）
	 * 
	 * @param userid
	 * @return
	 */
	public List<JobApply> getJobApplyByUserId(Integer userid) {
		return jobApplyDao.findByStatusAndUserid(0, userid);
	}

	/**
	 * 根据用户id得到快速投递记录
	 * 
	 * type 类型（1：无登录报名、2：有登录报名） status 状态（0：未处理、1处理中、2：已处理）
	 * 
	 * @param userid
	 * @return
	 */
	public List<JobApply> getJobApplyByUserIdAndJobId(Integer userid, Integer jobid) {
		return jobApplyDao.findByStatusAndUseridAndJobid(0, userid, jobid);
	}

	/**
	 * 登陆用户报名
	 * 
	 * @param userid
	 * @param jobid
	 * @param ip
	 */
	public void userSignUp(Integer userid, Integer jobid, String ip) {
		JobApply apply = new JobApply();
		JobBase job = jobBaseDao.findOne(jobid);
		apply.setApplyIp(ip);
		// apply.setAssigner(value);
		apply.setSource(1);
		apply.setCompanyid(job.getCompany().getId());
		apply.setCreateTime(DateConvertUtils.getNow());
		apply.setJobid(jobid);
		apply.setStatus(0);
		apply.setType(2);
		apply.setUpdateTime(DateConvertUtils.getNow());
		apply.setUserid(userid);
		if (job.getProvinceid() != null) {
			apply.setIntentionProvinceid(job.getProvinceid());
		}
		if (job.getCityid() != null) {
			apply.setIntentionProvinceid(job.getCityid());
		}
		if (job.getCountyid() != null) {
			apply.setIntentionProvinceid(job.getCountyid());
		}
		jobApplyDao.save(apply);
		if (job.getApplycount() == null) {
			job.setApplycount(1);
		} else {
			job.setApplycount(job.getApplycount() + 1);
		}
		jobBaseDao.save(job);
	}

	public void userSignUp(Integer userid, String ip, String intent) {
		JobApply apply = new JobApply();
		apply.setApplyIp(ip);
		// apply.setAssigner(value);
		apply.setCreateTime(DateConvertUtils.getNow());
		apply.setStatus(0);
		apply.setType(2);
		apply.setUpdateTime(DateConvertUtils.getNow());
		apply.setUserid(userid);
		apply.setIntention(intent);
		jobApplyDao.save(apply);
	}

	/**
	 * 未登录状态下，报名具体的职位
	 * 
	 * @param userid
	 * @param name
	 * @param jobid
	 * @param ip
	 */
	public void onlymobilesiginup(Integer userid, String name, Integer jobid, String ip) {
		JobApply apply = new JobApply();
		JobBase job = jobBaseDao.findOne(jobid);
		apply.setApplyIp(ip);
		apply.setSource(1);
		apply.setJobid(jobid);
		apply.setCompanyid(job.getCountyid());
		apply.setCreateTime(DateConvertUtils.getNow());
		apply.setStatus(0);
		apply.setType(1);
		apply.setUpdateTime(DateConvertUtils.getNow());
		apply.setUserid(userid);
		if (job.getProvinceid() != null) {
			apply.setIntentionProvinceid(job.getProvinceid());
		}
		if (job.getCityid() != null) {
			apply.setIntentionProvinceid(job.getCityid());
		}
		if (job.getCountyid() != null) {
			apply.setIntentionProvinceid(job.getCountyid());
		}
		jobApplyDao.save(apply);
		if (job.getApplycount() == null) {
			job.setApplycount(1);
		} else {
			job.setApplycount(job.getApplycount() + 1);
		}
		
		
		jobBaseDao.save(job);
	}

	/**
	 * 验证用户是否已经报名该职位
	 * 
	 * @param userid
	 * @param jobid
	 * @return false 已报名 ；true ：没报名
	 */
	public boolean ifUserSignedJob(Integer userid, Integer jobid) {
		if (userid != null && jobid != null) {
			return jobApplyDao.countByJobidAndUserid(jobid, userid) > 0 ? false : true;
		}
		return false;
	}

	/**
	 * 用户报名次数
	 * 
	 * @param userid
	 * @param jobid
	 * @return
	 */
	public int countByJobidAndUserid(Integer userid, Integer jobid) {
		if (userid != null && jobid != null) {
			return jobApplyDao.countByJobidAndUserid(jobid, userid);
		}
		return 0;
	}

	public Page<JobApply> findJobApplyPage(Integer userId, Integer type,  int pageNumber, int pageSize) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		// 查询条件 GT 大于 ,LT 小于,GTE 大于等于, LTE 小于等于,EQ 等于, LIKE 包含
		searchParams.put("GT_companyid", 0 + "");//查询公司id不为空的报名信息
		if (type != null && type != 0)// 报名类型 (1：快速报名，2：申请面试，3：悬赏聘 ,4: 预约职位)
		{
			searchParams.put("EQ_type", type+ "");
		}
		if (userId != null&&userId != 0)// 报名的用户id
		{
			searchParams.put("EQ_userid", userId + "");
		}
		Page<JobApply> pages = getApplyPage(searchParams, pageNumber, pageSize, "auto");
		return pages;
	}

	
	public Page<JobApply> getApplyPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<JobApply> spec = buildSpecification(searchParams);
		return jobApplyDao.findAll(spec, pageRequest);
	}

	/**
	 * 取该公司报名记录
	 * @param companyId
	 * @param hashMap
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public List<ApplyVo> getApplysByCompanyPage(Integer companyId,
			HashMap<String, Object> searchParams, int pageNumber, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<JobApply> spec = buildSpecificationCompany(companyId, searchParams);
		List<JobApply> applys = jobApplyDao.findAll(spec, pageRequest).getContent();
		List<ApplyVo> appList = new ArrayList<ApplyVo>();
		for(JobApply jobApply:applys){
			String userName ="优蓝网友";
			String jobName = "某岗位";
			if(jobApply.getUserid()!=null && !jobApply.getUserid().equals("")){
				if(userDao.findOne(jobApply.getUserid())!=null){
					userName=userDao.findOne(jobApply.getUserid()).getUsername();
				}
			}
			if(jobApply.getJobid()!=null && !jobApply.getJobid().equals("")){
				if(jobBaseDao.findOne(jobApply.getJobid())!=null){
					jobName=jobBaseDao.findOne(jobApply.getJobid()).getJobname();
				}
			}
			ApplyVo applyVo = new ApplyVo(jobApply.getJobid(),jobName,userName,jobApply.getCreateTime());
			appList.add(applyVo);
		}
		return appList;
	}
	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("createTime".equals(sortType)) {
			sort = new Sort(Direction.ASC, "createTime");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.-用户报名记录
	 */
	private Specification<JobApply> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<JobApply> spec = DynamicSpecifications.bySearchFilter(filters.values(), JobApply.class);
		return spec;
	}
	/**
	 * 创建动态查询条件组合.-公司报名列表
	 */
	private Specification<JobApply> buildSpecificationCompany(int companyid, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("companyid", new SearchFilter("companyid", Operator.EQ, companyid));
		Specification<JobApply> spec = DynamicSpecifications.bySearchFilter(filters.values(), JobApply.class);
		return spec;
	}

	@Autowired
	public void setJobApplyDao(JobApplyDao jobApplyDao) {
		this.jobApplyDao = jobApplyDao;
	}
	@Autowired
	public void setJobBaseDao(JobBaseDao jobBaseDao) {
		this.jobBaseDao = jobBaseDao;
	}
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Autowired
	public void setJobApplyFlowLogDao(JobApplyFlowLogDao jobApplyFlowLogDao) {
		this.jobApplyFlowLogDao = jobApplyFlowLogDao;
	}

	/**
	 * 根据登录的用户查询当天该用户已报名的数量
	 * @param userId
	 * @return
	 */
	public Integer countApplyByUserIdAndCreateDate(Integer userId) {
		return jobApplyDao.countApplyByUserIdAndCreateDate(userId);
	}

}