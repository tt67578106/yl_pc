package com.ylw.service.base;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ylw.entity.code.CodeAreaCity;
import com.ylw.repository.*;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.ylw.entity.base.Resume;
import com.ylw.entity.base.User;
import com.ylw.entity.base.UserProfile;
import com.ylw.entity.enterprise.CompanyResumeBox;
import com.ylw.entity.job.JobApply;
import com.ylw.entity.job.JobBase;
import com.ylw.util.Constants;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.RandomSeriNoUtils;
import com.ylw.util.SMSUtil;
import com.ylw.util.StoreCacheUtil;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Component
@Transactional
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	private static final int SALT_SIZE = 8;
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;

	private UserDao userDao;
	private ResumeDao resumeDao;
	private JobApplyDao jobApplyDao;
	private JobBaseDao jobBaseDao;
	private CompanyResumeBoxDao companyResumeBoxDao;
	private	UserProfileDao userProfileDao;
	private ApiAppDao apiAppDao;
	private CodeAreaCityDao codeAreaCityDao;
	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private ResumeMdbService resumeMdbService;
	
	@Autowired
	public void setApiAppDao(ApiAppDao apiAppDao) {
		this.apiAppDao = apiAppDao;
	}

	@Autowired
	public void setCodeAreaCityDao(CodeAreaCityDao codeAreaCityDao) { this.codeAreaCityDao = codeAreaCityDao; }

	public User getUser(java.lang.Integer id) {
		return userDao.findOne(id);
	}

	public User save(User entity) {
		return userDao.save(entity);
	}

	public void delete(java.lang.Integer id) {
		userDao.delete(id);
	}

	/**
	 * @description: 投递具体岗位
	 * @method: signUpJob
	 * @param fromKey
	 * @param appChannelCode
	 * @param jobid
	 * @param scheduleCode
	 * @param user
	 * @param type
	 * @param ip
	 * @param branchId
	 * @param cookieValue
	 * @param recSource
	 * @return: java.lang.Integer
	 */
	public Integer signUpJob(String fromKey,String appChannelCode,Integer jobid, String scheduleCode,User user,Integer type,String ip,Integer branchId,String cookieValue,String recSource) {

		List<JobApply> jobApplyList = getJobApplyByUserIdAndJobId(user.getId(), jobid);// 查找该岗位未处理报名的条数
		if (jobApplyList.size() == 0) {
			Resume resume = resumeService.getByUserId(user)!=null?resumeService.getByUserId(user):null;
			JobApply apply = new JobApply();
			//新增意向城市
			JobBase jobBase = jobBaseDao.getByJobId(jobid);
			if (resume == null) {// 没有该职位未处理的报名
				if(StringUtils.isNotEmpty(resume.getName())){
					apply.setName(resume.getName());
				}
				apply.setGender(resume.getGender());
				if(StringUtils.isNotEmpty(resume.getMobile())){
					apply.setMobile(resume.getMobile());
				}
				if(StringUtils.isNotEmpty(resume.getIdCard())){
					apply.setIdCard(resume.getIdCard());
				}
				if(resume.getEducation()!=null&&resume.getEducation()!=0){
					apply.setEducation(resume.getEducation());
				}
				/*if(resume.getJobTargetProvinceid()!=null&&resume.getJobTargetProvinceid()!=0){
					apply.setIntentionProvinceid(resume.getJobTargetProvinceid());
				}
				if(resume.getJobTargetCityid()!=null&&resume.getJobTargetCityid()!=0){
					apply.setIntentionCityid(resume.getJobTargetCityid());
				}*/
				if(resume.getIntentionPosition()!=null&&resume.getIntentionPosition()!=0){
					apply.setIntentionPosition(resume.getIntentionPosition());
				}
			}
			if (jobBase.getProvinceid() != null) {
				apply.setIntentionProvinceid(jobBase.getProvinceid());
			}
			if (jobBase.getCityid() != null) {
				apply.setIntentionCityid(jobBase.getCityid());
			}
			if (jobBase.getCountyid() != null) {
				apply.setIntentionCountyid(jobBase.getCountyid());
			}
			
			apply.setApplyIp(ip);
			apply.setCreateTime(DateConvertUtils.getNow());
			apply.setJobid(jobid);
			if(StringUtils.isNotEmpty(fromKey)){
				apply.setAppSourceCode(apiAppDao.getSourceCodeByAppKey(fromKey));
				apply.setFromKey(fromKey);
			}else{
				apply.setFromKey("00d55953f18911e594980800277a9591");
				apply.setAppSourceCode("ylife_recruit");
			}
			if(StringUtils.isNotEmpty(appChannelCode)){
				apply.setAppChannelCode(appChannelCode);
			}
			if(StringUtils.isNoneBlank(cookieValue)){
				apply.setSourceActivityId(4);//1，正常 2，30s报名 3，百日大战 4，百度推广 7,百度网盟
				if(recSource.equals("wu")){
					apply.setSourceActivityId(7);
				}
			}else{
				apply.setSourceActivityId(1);//1，正常 2，30s报名 3，百日大战 4，百度推广
			}
			apply.setType(type);
			apply.setBranchId(branchId);
			if (jobid != null) {
				JobBase job = getJobBase(jobid);
				apply.setCompanyid(job.getCompany().getId());
				if (job.getApplycount() == null) {
					job.setApplycount(1);
				} else {
					job.setApplycount(job.getApplycount()+1);
					if(job.getJobDetail()!=null){
						job.getJobDetail().setShowApplyCount(job.getJobDetail().getShowApplyCount()==null?0:job.getJobDetail().getShowApplyCount()+1);
					}
				}
//				apply.setBehavior("A_jobsignup");
				if(job!=null&&(job.getCooperationType()==1||job.getCooperationType()==2||job.getCooperationType()==5)){//真实岗位
					if(job.getJobConfig()!=null&&job.getJobConfig().getIsRecruitment()==0){//在招
						if (StringUtils.isNotBlank(scheduleCode)) {// 有排期
							apply.setBehavior("A_oninterviewsignup");
						} else {// 无排期
							apply.setBehavior("A_ontruejobsignup");
						}
					}else{//停招
						if (StringUtils.isNotBlank(scheduleCode)) {// 有排期
							apply.setBehavior("A_offinterviewsignup");
						} else {// 无排期
							apply.setBehavior("A_offtruejobsignup");
						}
					}
				}else{//假岗位
					apply.setBehavior("A_fakejobsignup");
				}
				saveJobBase(job);
//				CompanyResumeBox companyResumeBox=new CompanyResumeBox();
//				companyResumeBox.setCompanyId(job.getCompany().getId());
//				companyResumeBox.setJobBase(job);
//				companyResumeBox.setResume(resumeService.getByUserId(user));
//				saveCompanyResumeBox(companyResumeBox);
			}
			apply.setStatus(0);
			apply.setSource(1);
			apply.setUserid(user.getId());
			apply.setUpdateTime(DateConvertUtils.getNow());
			if(StringUtils.isNotBlank(scheduleCode)){
				apply.setScheduleDataCode(scheduleCode);
			}
			
			saveApply(apply);
			SMSUtil smsUtil =  new SMSUtil(user.getLoginname(), "signupSuccess");
			smsUtil.start();
			return apply.getId();
		} else {
			return 0;
		}
	}

	/**
	 * 注册用户（未激活状态）
	 * 
	 * @param loginname
	 * @param username
	 * @param password
	 * @param ip
	 */
//	public User quickregister(Resume resume, String ip, Integer branchId) {
//		User user = null;
//		if (resume.getUserid() == null) {
//			user = new User();
//			user.setStatus(0);
//			entryptPassword(user,  RandomSeriNoUtils.genCodeByTime(""));
//			user.setRegistertype(2);// 快捷注册
//		} else {
//			user = userDao.findOne(resume.getUserid());
//		}
//		user.setFirstLoginTime(DateConvertUtils.getNow());
//		user.setLastLoginIp(ip);
//		user.setLastLoginTime(DateConvertUtils.getNow());
//		user.setLoginname(resume.getMobile());
//		user.setUsername(resume.getName());
//		user.setRegistertime(DateConvertUtils.getNow());
//		user.setRegisterSource(1);
//		user.setRegisterBranchId(branchId);
//		user = userDao.save(user);
//		Resume newResume = null;
//		if (getByUserId(user.getId()) == null) {
//			newResume = new Resume();
//			newResume.setCreatetime(DateConvertUtils.getNow());
//			newResume.setIsSynHr(1);
//		} else {
//			newResume = getByUserId(user.getId());
//		}
//		newResume.setMobile(resume.getMobile());
//		newResume.setUserid(user.getId());
//		newResume.setName(resume.getName());
//		newResume.setGender(resume.getGender());
//		newResume.setEducation(resume.getEducation());
//		newResume.setResumeCode(RandomSeriNoUtils.generateUUIDResumeCode());
//		newResume.setIsSynHr(1);
//		resumeDao.save(newResume);
//		UserProfile userProfile = userProfileDao.getByUserId(user.getId());
//		if (userProfile == null) {
//			userProfile = new UserProfile();// 注册添加服务卡
//			userProfile.setUserId(user.getId());
//		}
//		userProfile.setMobile(resume.getMobile());
//		userProfileDao.save(userProfile);
//		return user;
//	}

	/**
	 * 
	 * 30s快速报名
	 * 
	 * @return
	 */

	public String signUp30s(String behavior,String thekey,String fromKey,String appChannelCode,String mobile, String ip, Integer gender, String name, String intention, HttpServletRequest request, HttpServletResponse response, int branchId,String cookieValue,String recSource) {
		CodeAreaCity city = null;
		if(StringUtils.isNotEmpty(intention)){
			String[] intention_array = intention.split(",");
			city = codeAreaCityDao.findByAbbreviation(intention_array[0]);
		}
		User user = getByLoginname(mobile);
		if (user == null) {// 没注册
			 Cache cache = StoreCacheUtil.getCacheManager().getCache("smscache");
			 Element ele = cache.get(mobile);
			 if (ele == null) {
				 return Constants.RETURN_STATUS_FAILURE;
			 }
			 String rightkey = (String) ele.getObjectValue();
			 if (!rightkey.equals(thekey)) {// 验证码出错
				 return Constants.RETURN_STATUS_FAILURE;
			 }
			String password = RandomSeriNoUtils.genCodeByTime("");//"123456";// 设置默认密码
			// try {
			// password = SMSUtil.sendSMS(mobile, "quick", ip);
			// } catch (Exception e) {// 短信发送失败，
			// e.printStackTrace();
			// }
			// if (password == null || password.length() == 5) {// 出错
			// return Constants.RETURN_STATUS_ERROR;
			// }
			user = quickregister(fromKey,appChannelCode,mobile, gender, name, password, ip, branchId,cookieValue,recSource,city);
			JobApply apply = new JobApply();
			apply.setBranchId(branchId);
			apply.setApplyIp(ip);
			apply.setCreateTime(DateConvertUtils.getNow());
			apply.setIntention(intention);
			apply.setStatus(0);
			apply.setType(1);
			apply.setSource(1);
			apply.setMobile(mobile);
			if(StringUtils.isNotBlank(behavior)){
				apply.setBehavior(behavior);
			}else{
				apply.setBehavior("A_fastsignup");
			}
			apply.setName(name);
			apply.setUserid(user.getId());
			apply.setUpdateTime(DateConvertUtils.getNow());
			if(StringUtils.isNotEmpty(fromKey)){
				apply.setFromKey(fromKey);
				apply.setAppSourceCode(apiAppDao.getSourceCodeByAppKey(fromKey));
			}else{
				apply.setAppSourceCode("ylife_recruit");
				apply.setFromKey("00d55953f18911e594980800277a9591");
			}
			if(StringUtils.isNotEmpty(appChannelCode)){
				apply.setAppChannelCode(appChannelCode);
			}
			if(StringUtils.isNotBlank(cookieValue)){
				apply.setSourceActivityId(4);//1，正常 2，30s报名 3，百日大战 4，百度推广,7百度网盟
				if(recSource.equals("wu")){
					apply.setSourceActivityId(7);
				}
			}else{
				apply.setSourceActivityId(2);//1，正常 2，30s报名 3，百日大战 4，百度推广
			}
			if(city != null){
				apply.setIntentionCityid(city.getId());	//意愿工作城市ID
				apply.setIntentionProvinceid(city.getProvinceId());	//意愿工作省直辖市ID
			}
			saveApply(apply);
			//发送密码
//			SMSUtil smsUtil = new SMSUtil(mobile, password,"quick");
//			smsUtil.start();
		} else {// 已注册
			List<JobApply> jobApplyList = getJobApplyByUserId(user.getId());
//			user.setUsername(name);
//			userDao.save(user);
//			Resume resume = null;// 简历在注册是要创建，这里认为resume不会为null
//			if (getByUserId(user.getId()) == null) {
//				resume = new Resume();
//				resume.setCreatetime(DateConvertUtils.getNow());
//				resume.setIsSynHr(1);
//			} else {
//				resume = getByUserId(user.getId());
//			}
//			if (resume.getResumeCode() == null || resume.getResumeCode().isEmpty()) {
//				resume.setResumeCode(RandomSeriNoUtils.generateUUIDResumeCode());
//			}
//			resume.setMobile(mobile);
//			resume.setUserid(user.getId());
//			resume.setName(name);
//			resume.setGender(gender);
//			resume.setIsSynHr(1);
			/*resumeDao.save(resume);*/
			//--------------------同步简历到Mdb-----------------------------------------
			if(StringUtils.isNoneBlank(String.valueOf(user.getId())) && StringUtils.isNoneBlank(mobile)){
				boolean flag = false;
				JSONObject resultMap = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(user.getId()), mobile, null,name);
				if(resultMap.get("status").equals(Constants.RETURN_STATUS_SUCCESS)){
					// 获取data
					net.sf.json.JSONObject dataObject = resultMap.getJSONObject("data");
					// 获取data里面 resume_info_id
					final String resume_info_id = (String) dataObject.get("id");
					Map<String, String> resumeMap = new HashMap<String, String>();
					String sex = null;
					if(gender != null){
						if(gender == 1){
						sex="男";
						}
						if(gender == 2){
						sex="女";
						}
						resumeMap.put("gender",sex);
						flag = true;
					}
					if(name != null){
						resumeMap.put("full_name",name);
						flag = true;
					}
					resumeMdbService.modifyResumeBaseMdb(String.valueOf(user.getId()),mobile,resume_info_id,resumeMap);
				}
			}
			
			if (jobApplyList.size() == 0) {// 如果不存在未处理的报名信息则新增一条
				JobApply apply = new JobApply();
				apply.setApplyIp(ip);
				apply.setCreateTime(DateConvertUtils.getNow());
				apply.setIntention(intention);
				apply.setStatus(0);
				apply.setType(1);
				apply.setSource(1);
				apply.setUserid(user.getId());
				apply.setBranchId(branchId);
				apply.setName(name);
				apply.setUpdateTime(DateConvertUtils.getNow());
				if(StringUtils.isNotBlank(behavior)){
					apply.setBehavior(behavior);
				}else{
					apply.setBehavior("A_fastsignup");
				}
				if(StringUtils.isNotEmpty(fromKey)){
					apply.setAppSourceCode(apiAppDao.getSourceCodeByAppKey(fromKey));
					apply.setFromKey(fromKey);
				}else{
					apply.setAppSourceCode("ylife_recruit");
					apply.setFromKey("00d55953f18911e594980800277a9591");
				}
				if(StringUtils.isNotEmpty(appChannelCode)){
					apply.setAppChannelCode(appChannelCode);
				}
				if(StringUtils.isNotBlank(cookieValue)){
					apply.setSourceActivityId(4);//1，正常 2，30s报名 3，百日大战 4，百度推广,7百度网盟
					if(recSource.equals("wu")){
						apply.setSourceActivityId(7);
					}
				}else{
					apply.setSourceActivityId(2);//1，正常 2，30s报名 3，百日大战 4，百度推广
				}
				if(city != null){
					apply.setIntentionCityid(city.getId());	//意愿工作城市ID
					apply.setIntentionProvinceid(city.getProvinceId());	//意愿工作省直辖市ID
				}
				saveApply(apply);
			} else {// 如果存在
				JobApply jobApply = jobApplyList.get(jobApplyList.size() - 1);
				if (jobApply.getUpdateTime()!=null&&DateConvertUtils.hoursBetween(DateConvertUtils.getNow(), jobApply.getUpdateTime()) > 1) {// 判断是否更新时间大于1小时，如果大于一小时更新修改时间
					jobApply.setUpdateTime(DateConvertUtils.getNow());
					jobApply.setIntention(intention);
					if (city != null) {
						jobApply.setIntentionCityid(city.getId());
						jobApply.setIntentionProvinceid(city.getProvinceId());
					}
					saveApply(jobApply);
				}
				if(jobApply.getUpdateTime()==null){
					if(StringUtils.isNotBlank(cookieValue)){
						jobApply.setSourceActivityId(4);//1，正常 2，30s报名 3，百日大战 4，百度推广 7,百度网盟
						if(recSource.equals("wu")){
							jobApply.setSourceActivityId(7);
						}
					}else{
						jobApply.setSourceActivityId(2);//1，正常 2，30s报名 3，百日大战 4，百度推广
					}
					jobApply.setUpdateTime(DateConvertUtils.getNow());
					if (city != null) {
						jobApply.setIntentionCityid(city.getId());
						jobApply.setIntentionProvinceid(city.getProvinceId());
					}
					saveApply(jobApply);
				}
			}
		}
		// accountService.loginUser(user, request, response); 未激活状态，不能登录
		return Constants.RETURN_STATUS_SUCCESS;
	}

	/**
	 * @description: 30秒报名注册用户 （整理后）
	 * @method: registerUser
	 * @author: Mark
	 * @date: 11:35 2018/3/6
	 * @param fromKey
	 * @param loginname
	 * @param username
	 * @param password
	 * @param ip
	 * @param branchId
	 * @param cookieValue
	 * @param recSource
	 * @return: java.lang.Long
	 */
	public User registerUser(String fromKey,String loginname, String username, String password, String ip, Integer branchId,String cookieValue,String recSource){
		logger.info("=============创建新用户=============");
		User user = null;
		try {
			user = new User();
			entryptPassword(user, password);
			user.setFirstLoginTime(DateConvertUtils.getNow());
			user.setLastLoginIp(ip);
			user.setLastLoginTime(DateConvertUtils.getNow());
			user.setLoginname(loginname);
			user.setUsername(username);
			user.setRegistertime(DateConvertUtils.getNow());
			user.setRegisterSource(1);
			user.setBehavior("B_register");
			user.setRegistertype(2);// 快捷注册
			user.setRegisterBranchId(branchId);
			user.setStatus(1);
			if(StringUtils.isNotEmpty(fromKey)){
                user.setAppSourceCode(apiAppDao.getSourceCodeByAppKey(fromKey));
                user.setFromKey(fromKey);
            }else{
                user.setAppSourceCode("ylife_recruit");
                user.setFromKey("00d55953f18911e594980800277a9591");
            }
			if(StringUtils.isNoneBlank(cookieValue)){
                user.setSourceActivityId(4);//1，正常 2，30s报名 3，百日大战 4，百度推广 7,百度网盟
                if(recSource.equals("wu")){
                    user.setSourceActivityId(7);
                }
            }else{
                user.setSourceActivityId(2);//1，正常 2，30s报名 3，百日大战 4，百度推广
            }
			user.setHasDownloadApp(2);//是否下载APP(1：已下载；2：未下载)
			user.setIsValidation(1);
			user = userDao.save(user);

			UserProfile userProfile=new UserProfile();//注册添加服务卡
			userProfile.setUserId(user.getId());
			userProfile.setMobile(loginname);
			userProfileDao.save(userProfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * @description: 同步简历到Mdb
	 * @method: findResumeMdb
	 * @author: Mark
	 * @date: 10:52 2018/3/6
	 * @param user
	 * @param mobile
	 * @param name
	 * @param gender
	 * @param city
	 * @return: net.sf.json.JSONObject
	 */
	public JSONObject findResumeMdb(final User user, final String mobile, final String name, Integer gender, CodeAreaCity city) {
		JSONObject resultMap = null;
		//--------------------同步简历到Mdb-----------------------------------------
		if(StringUtils.isNoneBlank(String.valueOf(user.getId())) && StringUtils.isNoneBlank(mobile)){
			logger.info("=============同步简历到Mdb=============");
			boolean flag = false;
			resultMap = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(user.getId()), mobile, null,name,city);
			if(resultMap.get("status").equals(Constants.RETURN_STATUS_SUCCESS)){
				// 获取data
				net.sf.json.JSONObject dataObject = resultMap.getJSONObject("data");
				// 获取data里面 resume_info_id
				final String resume_info_id = (String) dataObject.get("id");
				Map<String, String> resumeMap = new HashMap<String, String>();
				String sex = null;
				if(gender != null){
					if(gender == 1){
						sex="男";
					}
					if(gender == 2){
						sex="女";
					}
					resumeMap.put("gender",sex);
					flag = true;
				}
				if(name != null){
					resumeMap.put("full_name",name);
					flag = true;
				}
				if(city != null){
					resumeMap.put("intention_city", JSONObject.fromObject(city).toString());
				}
				resultMap =  resumeMdbService.modifyResumeBaseMdb(String.valueOf(user.getId()),mobile,resume_info_id,resumeMap);
			}
		}
		return resultMap;
	}


	/**
	 * @description: 30秒快速报名（整理后）
	 * @method: signUp30s
	 * @author: Mark
	 * @date: 12:05 2018/3/6
	 * @param user
	 * @param behavior
	 * @param fromKey
	 * @param appChannelCode
	 * @param mobile
	 * @param ip
	 * @param name
	 * @param intention
	 * @param branchId
	 * @param cookieValue
	 * @param recSource
	 * @param intentionCity
	 * @return: java.lang.String
	 */
	public String signUp30s(User user, String behavior, String fromKey, String appChannelCode, String mobile, String ip, String name, String intention, Integer branchId,String cookieValue,String recSource, CodeAreaCity intentionCity) {
		logger.info("=============生成报名记录=============");
		try {
			if (user == null) {// 没注册
                JobApply apply = new JobApply();
                apply.setBranchId(branchId);
                apply.setApplyIp(ip);
                apply.setCreateTime(DateConvertUtils.getNow());
                apply.setIntention(intention);
                apply.setStatus(0);
                apply.setType(1);
                apply.setSource(1);
                apply.setMobile(mobile);
                if(StringUtils.isNotBlank(behavior)){
                    apply.setBehavior(behavior);
                }else{
                    apply.setBehavior("A_fastsignup");
                }
                apply.setName(name);
                apply.setUserid(user.getId());
                apply.setUpdateTime(DateConvertUtils.getNow());
                if(StringUtils.isNotEmpty(fromKey)){
                    apply.setFromKey(fromKey);
                    apply.setAppSourceCode(apiAppDao.getSourceCodeByAppKey(fromKey));
                }else{
                    apply.setAppSourceCode("ylife_recruit");
                    apply.setFromKey("00d55953f18911e594980800277a9591");
                }
                if(StringUtils.isNotEmpty(appChannelCode)){
                    apply.setAppChannelCode(appChannelCode);
                }
                if(StringUtils.isNotBlank(cookieValue)){
                    apply.setSourceActivityId(4);//1，正常 2，30s报名 3，百日大战 4，百度推广,7百度网盟
                    if(recSource.equals("wu")){
                        apply.setSourceActivityId(7);
                    }
                }else{
                    apply.setSourceActivityId(2);//1，正常 2，30s报名 3，百日大战 4，百度推广
                }
                if(intentionCity != null){
                    apply.setIntentionCityid(intentionCity.getId());	//意愿工作城市ID
                    apply.setIntentionProvinceid(intentionCity.getProvinceId());	//意愿工作省直辖市ID
                }
                saveApply(apply);
                //发送密码
    //			SMSUtil smsUtil = new SMSUtil(mobile, password,"quick");
    //			smsUtil.start();
            } else {// 已注册
                List<JobApply> jobApplyList = getJobApplyByUserId(user.getId());

                if (jobApplyList.size() == 0) {// 如果不存在未处理的报名信息则新增一条
                    JobApply apply = new JobApply();
                    apply.setApplyIp(ip);
                    apply.setCreateTime(DateConvertUtils.getNow());
                    apply.setIntention(intention);
                    apply.setStatus(0);
                    apply.setType(1);
                    apply.setSource(1);
                    apply.setMobile(mobile);
                    apply.setUserid(user.getId());
                    apply.setBranchId(branchId);
                    apply.setName(name);
                    apply.setUpdateTime(DateConvertUtils.getNow());
                    if(StringUtils.isNotBlank(behavior)){
                        apply.setBehavior(behavior);
                    }else{
                        apply.setBehavior("A_fastsignup");
                    }
                    if(StringUtils.isNotEmpty(fromKey)){
                        apply.setAppSourceCode(apiAppDao.getSourceCodeByAppKey(fromKey));
                        apply.setFromKey(fromKey);
                    }else{
                        apply.setAppSourceCode("ylife_recruit");
                        apply.setFromKey("00d55953f18911e594980800277a9591");
                    }
                    if(StringUtils.isNotEmpty(appChannelCode)){
                        apply.setAppChannelCode(appChannelCode);
                    }
                    if(StringUtils.isNotBlank(cookieValue)){
                        apply.setSourceActivityId(4);//1，正常 2，30s报名 3，百日大战 4，百度推广,7百度网盟
                        if(recSource.equals("wu")){
                            apply.setSourceActivityId(7);
                        }
                    }else{
                        apply.setSourceActivityId(2);//1，正常 2，30s报名 3，百日大战 4，百度推广
                    }
                    if(intentionCity != null){
                        apply.setIntentionCityid(intentionCity.getId());	//意愿工作城市ID
                        apply.setIntentionProvinceid(intentionCity.getProvinceId());	//意愿工作省直辖市ID
                    }
                    saveApply(apply);
                } else {// 如果存在
                    JobApply jobApply = jobApplyList.get(jobApplyList.size() - 1);
                    if (jobApply.getUpdateTime()!=null&&DateConvertUtils.hoursBetween(DateConvertUtils.getNow(), jobApply.getUpdateTime()) > 1) {// 判断是否更新时间大于1小时，如果大于一小时更新修改时间
                        jobApply.setUpdateTime(DateConvertUtils.getNow());
                        jobApply.setIntention(intention);
                        if(intentionCity != null){
                        	jobApply.setIntentionCityid(intentionCity.getId());	//意愿工作城市ID
                            jobApply.setIntentionProvinceid(intentionCity.getProvinceId());	//意愿工作省直辖市ID
                        }
                        saveApply(jobApply);
                    }
                    if(jobApply.getUpdateTime()==null){
                        if(StringUtils.isNotBlank(cookieValue)){
                            jobApply.setSourceActivityId(4);//1，正常 2，30s报名 3，百日大战 4，百度推广 7,百度网盟
                            if(recSource.equals("wu")){
                                jobApply.setSourceActivityId(7);
                            }
                        }else{
                            jobApply.setSourceActivityId(2);//1，正常 2，30s报名 3，百日大战 4，百度推广
                        }
                        jobApply.setUpdateTime(DateConvertUtils.getNow());
                        if(intentionCity != null){
                        	jobApply.setIntentionCityid(intentionCity.getId());	//意愿工作城市ID
                        	jobApply.setIntentionProvinceid(intentionCity.getProvinceId());	//意愿工作省直辖市ID
                        }
                        saveApply(jobApply);
                    }
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.RETURN_STATUS_SUCCESS;
	}

	public List<Integer> getApplyId(Integer userId,String mobile) {
		List applyIdList = jobApplyDao.getApplyId(userId, mobile);
		return applyIdList;
	}
	/**
	 * 根据登陆名获得用户
	 * 
	 * @param loginname
	 * @return
	 */
	public User getByLoginname(String loginname) {
		return userDao.getByLoginname(loginname);
	}

	/**
	 * 验证验证码是否正确，激活账户
	 * 
	 * @param mobile
	 * @param thekey
	 * @return
	 */
	public String activateAccount(String mobile, String thekey, String password, HttpServletRequest request, HttpServletResponse response) {
		Cache cache = StoreCacheUtil.getCacheManager().getCache("smscache");
		Element ele = cache.get(mobile);
		if (ele == null || (ele != null && !((String) ele.getObjectValue()).equals(thekey))) {// 验证码出错
			return Constants.RETURN_STATUS_FAILURE;
		} else {
			User user = getByLoginname(mobile);
			entryptPassword(user, password);
			userDao.activateAccount(user.getId());
//			accountService.loginUser(user, request, response);//注释自动登录
			return Constants.RETURN_STATUS_SUCCESS;
		}
	}

	/**
	 * 30s快速找工作
	 * 
	 * @param loginname
	 * @param gender
	 * @param username
	 * @param password
	 * @param ip
	 */
	public User quickregister(String fromKey,String appChannelCode,String loginname, Integer gender, String username, String password, String ip, Integer branchId,String cookieValue,String recSource,CodeAreaCity city) {
		User user = new User();
		entryptPassword(user, password);
		user.setFirstLoginTime(DateConvertUtils.getNow());
		user.setLastLoginIp(ip);
		user.setLastLoginTime(DateConvertUtils.getNow());
		user.setLoginname(loginname);
		user.setUsername(username);
		user.setRegistertime(DateConvertUtils.getNow());
		user.setRegisterSource(1);
		user.setBehavior("B_register");
		user.setRegistertype(2);// 快捷注册
		user.setRegisterBranchId(branchId);
		user.setStatus(0);
		if(StringUtils.isNotEmpty(fromKey)){
			user.setAppSourceCode(apiAppDao.getSourceCodeByAppKey(fromKey));
			user.setFromKey(fromKey);
		}else{
			user.setAppSourceCode("ylife_recruit");
			user.setFromKey("00d55953f18911e594980800277a9591");
		}
		if(StringUtils.isNoneBlank(cookieValue)){
			user.setSourceActivityId(4);//1，正常 2，30s报名 3，百日大战 4，百度推广 7,百度网盟
			if(recSource.equals("wu")){
				user.setSourceActivityId(7);
			}
		}else{
			user.setSourceActivityId(2);//1，正常 2，30s报名 3，百日大战 4，百度推广
		}
		user.setHasDownloadApp(2);//是否下载APP(1：已下载；2：未下载)
		user.setIsValidation(1);
		user = userDao.save(user);
		//------------------------30S同步简历到Mdb-------------------------------
	/*	Resume resume = new Resume();
		
		resume.setGender(gender);
		resume.setMobile(loginname);
		resume.setUserid(user.getId());
		resume.setName(username);
		resume.setCreatetime(DateConvertUtils.getNow());
		resume.setResumeCode(RandomSeriNoUtils.generateUUIDResumeCode());
		resume.setIsSynHr(1);	*/	
		/*resumeDao.save(resume);*/
		
		if(StringUtils.isNoneBlank(String.valueOf(user.getId())) && StringUtils.isNoneBlank(loginname)){
			boolean flag = false;
			JSONObject resultMap = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(user.getId()), loginname, null,username,city);
			if(resultMap.get("status").equals(Constants.RETURN_STATUS_SUCCESS)){
				// 获取data
				net.sf.json.JSONObject dataObject = resultMap.getJSONObject("data");
				// 获取data里面 resume_info_id
				final String resume_info_id = (String) dataObject.get("id");
				Map<String, String> resumeMap = new HashMap<String, String>();
				if(gender != null){
					resumeMap.put("gender",String.valueOf(gender));
					flag = true;
				}
				if(!StringUtils.isBlank(username)){
					resumeMap.put("full_name",username);
					flag = true;
				}
				if(city != null){
					resultMap.put("intention_city", JSONObject.fromObject(city).toString());
				}
				if(flag == true){
					resumeMdbService.modifyResumeBaseMdb(String.valueOf(user.getId()),loginname,resume_info_id,resumeMap);
				}
				
			}
		}
		
		
		//--------------------------------------------------
		UserProfile userProfile=new UserProfile();//注册添加服务卡
		userProfile.setUserId(user.getId());
		userProfile.setMobile(loginname);
		userProfileDao.save(userProfile);
		return user;
	}
	/**
	 * 快速注册用户
	 * @param loginname
	 * @param password
	 * @param ip
	 * @param branchId
	 */
	public User quickregister(String loginname, String password, String ip, Integer branchId) {
		User user = new User();
		entryptPassword(user, password);
		user.setFirstLoginTime(DateConvertUtils.getNow());
		user.setLastLoginIp(ip);
		user.setLastLoginTime(DateConvertUtils.getNow());
		user.setLoginname(loginname);
		user.setUsername("YL_"+RandomSeriNoUtils.generateLowerString(5));
		user.setRegistertime(DateConvertUtils.getNow());
		user.setRegisterSource(1);
		user.setRegistertype(2);// 快捷注册
		user.setRegisterBranchId(branchId);
		user.setStatus(1);
		user.setHasDownloadApp(2);//是否下载APP(1：已下载；2：未下载)
		user.setIsValidation(1);
		user = userDao.save(user);
		Resume resume = new Resume();
		resume.setMobile(loginname);
		resume.setUserid(user.getId());
		resume.setName(user.getUsername());
		resume.setCreatetime(DateConvertUtils.getNow());
		resume.setIsSynHr(1);
		resumeDao.save(resume);
		return user;
	}

	public Page<User> getUserPage(java.lang.Integer userId, Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<User> spec = buildSpecification(userId.longValue(), searchParams);
		return userDao.findAll(spec, pageRequest);
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
	private Specification<User> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ,
		// userId));
		Specification<User> spec = DynamicSpecifications.bySearchFilter(filters.values(), User.class);
		return spec;
	}
	
	public void saveCompanyResumeBox(CompanyResumeBox entity){
		companyResumeBoxDao.save(entity);
	}

	private void saveJobBase(JobBase job) {
			jobBaseDao.save(job);
	}
//	private Resume getByUserId(Integer userId) {
//		List<Resume> tlist = resumeDao.findByUserid(userId);
//		return tlist.size()>0?tlist.get(0):null;
//	}
	
	private JobBase getJobBase(Integer jobid) {
		return jobid!=null?jobBaseDao.findOne(jobid):null;
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
	
	public void saveApply(JobApply entity) {
		jobApplyDao.save(entity);
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setResumeDao(ResumeDao resumeDao) {
		this.resumeDao = resumeDao;
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
	public void setCompanyResumeBoxDao(CompanyResumeBoxDao companyResumeBoxDao) {
		this.companyResumeBoxDao = companyResumeBoxDao;
	}
	@Autowired
	public void setUserProfileDao(UserProfileDao userProfileDao) {
		this.userProfileDao = userProfileDao;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user, String password) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	public void update(User users) {
		
	}

	public void updateUser(Date lastLoginTime, Date updateTime, Integer isValidation, Integer id) {
		userDao.updateUser(lastLoginTime,updateTime,isValidation,id);
		
	}

}