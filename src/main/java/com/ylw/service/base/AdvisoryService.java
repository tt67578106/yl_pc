package com.ylw.service.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

import com.ylw.entity.base.Advisory;
import com.ylw.entity.base.Company;
import com.ylw.entity.base.Image;
import com.ylw.entity.base.Resume;
import com.ylw.entity.base.User;
import com.ylw.entity.community.Community;
import com.ylw.entity.community.UserCommunityTalk;
import com.ylw.entity.community.UserCommunityTalkContent;
import com.ylw.entity.vo.UserCommunityTalkContentVo;
import com.ylw.entity.vo.UserCommunityTalkVo;
import com.ylw.repository.AdvisoryDao;
import com.ylw.repository.CompanyDao;
import com.ylw.repository.JobBaseDao;
import com.ylw.repository.ResumeDao;
import com.ylw.repository.UserDao;
import com.ylw.repository.community.CommunityDao;
import com.ylw.repository.community.UserCommunityTalkContentDao;
import com.ylw.repository.community.UserCommunityTalkDao;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.HTMLInputFilter;
import com.ylw.util.MemcachedUtil;

import net.sf.json.JSONObject;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class AdvisoryService {

	private AdvisoryDao advisoryDao;
	
	private UserCommunityTalkDao userCommunityTalkDao;
	
	private UserCommunityTalkContentDao userCommunityTalkContentDao;
	
	private UserDao userDao;
	
	private ResumeDao resumeDao;
	
	private JobBaseDao jobBaseDao;
	
	private CompanyDao companyDao;
	
	private CommunityDao communityDao;
	@Autowired
	private ResumeMdbService resumeMdbService;

	public Advisory getAdvisory(java.lang.Integer id){
		return advisoryDao.findOne(id);
	}

	/**
	 * 保存问答 job
	 * @param userId
	 * @param jobId
	 * @param comment
	 */
	public void saveJobAdvisory(Integer userId,String userName,Integer companyId, Integer jobId,String title,String comment,Integer cityId) {
		UserCommunityTalk userCommunityTalk = new UserCommunityTalk();
		if(userId!=null){
			userCommunityTalk.setCreateTime(new Date());
			userCommunityTalk.setUser(userDao.findOne(userId));
		}
		userCommunityTalk.setCityId(cityId);
		userCommunityTalk.setParentId(0);
		userCommunityTalk.setType(5);
		userCommunityTalk.setSource(1);
		userCommunityTalk.setTitle(HTMLInputFilter.clean(title));
		userCommunityTalk.setIntroduction(HTMLInputFilter.clean(comment));
		userCommunityTalk.setIsDelete(1);
		if(jobId!=null){
			companyId = jobBaseDao.findOne(jobId).getCompany() == null?null:jobBaseDao.findOne(jobId).getCompany().getId();
			List<Community> list = communityDao.findByCompanyId(companyId);
			userCommunityTalk.setCommunity(list.size() > 0?list.get(0):null);
		}
		if(companyId != null && jobId == null){
			List<Community> list = communityDao.findByCompanyId(companyId);
			Community community = null;
			if(list.size() > 0 && list.get(0) != null){
				community = list.get(0);
			}
			userCommunityTalk.setCommunity(community);
		}
		userCommunityTalk.setCompany(companyId==null?null:companyDao.findOne(companyId));
		userCommunityTalk.setJobBase(jobId==null?null:jobBaseDao.findOne(jobId));
		userCommunityTalkDao.save(userCommunityTalk);
	}
	/**
	 * 保存问答company
	 * @param userId
	 * @param companyId
	 * @param comment
	 */
	public void saveCompanyAdvisory(Integer userId,String userName, Integer companyId,String comment,Integer cityId) {
		UserCommunityTalk userCommunityTalk = new UserCommunityTalk();
		if(userId!=null){
			userCommunityTalk.setCreateTime(new Date());
			userCommunityTalk.setUser(userDao.findOne(userId));
		}
		userCommunityTalk.setCityId(cityId);
		userCommunityTalk.setParentId(0);
		userCommunityTalk.setType(4);
		userCommunityTalk.setIntroduction(HTMLInputFilter.clean(comment));
		userCommunityTalk.setIsDelete(1);
		if(companyId != null){
			userCommunityTalk.setCompany(companyId==null?null:companyDao.findOne(companyId));
			List<Community> list = communityDao.findByCompanyId(companyId);
			userCommunityTalk.setCommunity(list != null?list.get(0):null);
		}
		userCommunityTalkDao.save(userCommunityTalk);
	}
	public void save(Advisory entity){
		advisoryDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		advisoryDao.delete(id);
	}

	/**
	 * 根据岗位id得到岗位的问题数量
	 * @param jobId
	 * @return
	 */
	public Integer countByJobBaseIdAndType(Integer jobId){
		return userCommunityTalkDao.countByJobBaseIdAndType(jobId,5);
	}
	
	/**
	 * 得到所有的问答
	 * @param jobId
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<UserCommunityTalkVo> findUserCommunityTalkVo(java.lang.Integer jobId,java.lang.Integer companyId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Page<UserCommunityTalk> userCommunityTalks = null;
		if(searchParams.containsKey("LIKE_title")&&searchParams.get("LIKE_title").equals("0")){
			searchParams.remove("LIKE_title");
		}
		searchParams.put("EQ_isReply", "1");
		searchParams.put("EQ_type", "5");
		if(companyId!=null&&companyId>0){
//			Integer communityId = 0;
//			List<Community> list = communityDao.findByCompanyId(companyId);
//			if(list!=null&&list.size() > 0){
//				communityId = list.get(0).getId();
//			}
			searchParams.put("EQ_company.id", companyId+"");
//			searchParams.put("EQ_community.id", communityId+"");
		}else if(jobId!=null&&jobId>0){
			searchParams.put("EQ_jobBase.id", jobId+"");
		} 
		userCommunityTalks = getAdvisotyPage(searchParams, pageNumber, pageSize, sortType);
		Page<UserCommunityTalkVo> page = new PageImpl<UserCommunityTalkVo>(buildUserCommunityTalkListVo(userCommunityTalks.getContent()), pageRequest, userCommunityTalks.getTotalElements());
		return page;
	}
	
	/**
	 * 根据岗位id得到问答
	 * @param jobId
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
//	public Page<UserCommunityTalkVo> getJobPage(java.lang.Integer jobId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
//		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
//		Page<UserCommunityTalk> userCommunityTalks = userCommunityTalkDao.findByJobIdAndType(jobId,5,pageRequest);
//		Page<UserCommunityTalkVo> page = new PageImpl<UserCommunityTalkVo>(buildUserCommunityTalkListVo(userCommunityTalks.getContent()), pageRequest, userCommunityTalks.getTotalElements());
//		return page;
//	}
	
	/**
	 * 根据企业id得到问答
	 * @param companyId
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
//	public Page<UserCommunityTalkVo> getCompanyPage(java.lang.Integer companyId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
//		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
//		Integer communityId = 0;
//		if(companyId != null){
//			List<Community> list = communityDao.findByCompanyId(companyId);
//			if(list!=null&&list.size() > 0){
//				communityId = list.get(0).getId();
//			}
//		}
//		Page<UserCommunityTalk> userCommunityTalks = userCommunityTalkDao.findByCommunityIdAndType(communityId,5,pageRequest);
//		Page<UserCommunityTalkVo> page = new PageImpl<UserCommunityTalkVo>(buildUserCommunityTalkListVo(userCommunityTalks.getContent()), pageRequest, userCommunityTalks.getTotalElements());
//		return page;
//	}

	/**
	 * 得到问答列表，根据提交时间
	 * @param companyId
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<UserCommunityTalk> getAdvisotyPage(Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<UserCommunityTalk> spec = buildCreateTimeSpecification(searchParams);
		return userCommunityTalkDao.findAll(spec, pageRequest);
	}
	/**
	 * 得到最新五条问答
	 * @param cachePcKeyHotCompany
	 * @param limit
	 * @param branchId
	 * @return
	 */
	public List<UserCommunityTalk> getAdvisotyPageCache(String cachePcKeyLATESTADVISORY,Integer limit) {
		List<UserCommunityTalk> advisorys;
		advisorys = (List<UserCommunityTalk>) MemcachedUtil.getCacheValue(cachePcKeyLATESTADVISORY);
		if(advisorys == null || (advisorys.size() < limit)){
			advisorys=getAdvisotyPage(new HashMap<String, Object>(),1,6,"auto").getContent();
			MemcachedUtil.setCache(cachePcKeyLATESTADVISORY, 3600*24, advisorys);
		}
		return advisorys;
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
	 * 创建动态查询条件组合.时间
	 */
	private Specification<UserCommunityTalk> buildCreateTimeSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("isDelete", new SearchFilter("isDelete", Operator.EQ, 1));
		Specification<UserCommunityTalk> spec = DynamicSpecifications.bySearchFilter(filters.values(), UserCommunityTalk.class);
		return spec;
	}

	@Autowired
	public void setAdvisoryDao(AdvisoryDao advisoryDao) {
		this.advisoryDao = advisoryDao;
	}
	@Autowired
	public void setUserCommunityTalkDao(UserCommunityTalkDao userCommunityTalkDao) {
		this.userCommunityTalkDao = userCommunityTalkDao;
	}
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Autowired
	public void setJobBaseDao(JobBaseDao jobBaseDao) {
		this.jobBaseDao = jobBaseDao;
	}
	@Autowired
	public void setCommunityDao(CommunityDao communityDao) {
		this.communityDao = communityDao;
	}

	@Autowired
	public void setResumeDao(ResumeDao resumeDao) {
		this.resumeDao = resumeDao;
	}
	@Autowired
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	@Autowired
	public void setUserCommunityTalkContentDao(UserCommunityTalkContentDao userCommunityTalkContentDao) {
		this.userCommunityTalkContentDao = userCommunityTalkContentDao;
	}

	/**
	 * 生成问答vo
	 * @param userCommunityTalks
	 * @return
	 */
	public List<UserCommunityTalkVo> buildUserCommunityTalkListVo(List<UserCommunityTalk> userCommunityTalks){
		List<UserCommunityTalkVo> userCommunityTalkVos = new ArrayList<UserCommunityTalkVo>();
		for (UserCommunityTalk userCommunityTalk : userCommunityTalks) {
			List<UserCommunityTalkContent> userCommunityTalkContents = userCommunityTalkContentDao.findByUserCommunityTalkIdAndIsDelOrderByCreateTimeDesc(userCommunityTalk.getId(),1);
			List<UserCommunityTalkContentVo> userCommunityTalkContentVos = new ArrayList<UserCommunityTalkContentVo>();
			//回复
			for (UserCommunityTalkContent userCommunityTalkContent : userCommunityTalkContents) {
				UserCommunityTalkContentVo userCommunityTalkContentVo= new UserCommunityTalkContentVo(
						userCommunityTalkContent.getId(),
						userCommunityTalkContent.getCreateTimeString(),
						userCommunityTalkContent.getContent(),
						null,
						//StringUtils.isNotBlank(userCommunityTalkContent.getUserHeadPath())?userCommunityTalkContent.getUserHeadPath():getUserHeadPath(userCommunityTalkContent.getUser()),
						userCommunityTalkContent.getUser()!=null?userCommunityTalkContent.getUser().getId():null,
						userCommunityTalkContent.getUser()!=null?userCommunityTalkContent.getUser().getUsername():userCommunityTalkContent.getUserName()
					);
				userCommunityTalkContentVos.add(userCommunityTalkContentVo);
			}
			//问题vo
			Company company = null;
			if(userCommunityTalk.getCompany()!=null){
				company = userCommunityTalk.getCompany();
			}
			if(company==null&&userCommunityTalk.getJobBase()!=null){
				company = userCommunityTalk.getJobBase().getCompany();
			}
			UserCommunityTalkVo userCommunityTalkVo= new UserCommunityTalkVo(
					userCommunityTalk.getId(),
					userCommunityTalk.getCreateTimeString(),
					userCommunityTalk.getTitle(),
					userCommunityTalk.getIntroduction(),
					null,
//					StringUtils.isNotBlank(userCommunityTalk.getUserHeadPath())?userCommunityTalk.getUserHeadPath():getUserHeadPath(userCommunityTalk.getUser()),
					userCommunityTalk.getUser()!=null?userCommunityTalk.getUser().getUsername():userCommunityTalk.getUserName(),
					userCommunityTalk.getJobBase()!=null?userCommunityTalk.getJobBase().getId():null,
					company!=null?company.getId():null,		
					company!=null?company.getAbbreviation():null,		
					userCommunityTalkContentVos
				);
			userCommunityTalkVos.add(userCommunityTalkVo);
		}
		return userCommunityTalkVos;
	}
	
	/**
	 * 根据用户id得到用户的头像地址
	 * @param userId
	 * @return
	 */
	public String getUserHeadPath(User user){
		String askHeadImage = null;
		try {
			if(user!=null){
				JSONObject resultMap = resumeMdbService.getResumeMdbByUserIdAndMobile(String.valueOf(user.getId()), user.getLoginname(), null,null);
				Resume resume = resumeMdbService.findResume(resultMap);
				if(resume!=null){
					Image head = resume.getImage();
					if(head!=null){
						askHeadImage = head.getImgpath();
					}
				}
			}
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
		return askHeadImage;
	}

	/**
	 * 保存问题的回复
	 * @param user
	 * @param userCommunityTalkId
	 * @param content
	 */
	public void saveReply(User user, Integer userCommunityTalkId, String content) {
		UserCommunityTalk userCommunityTalk = userCommunityTalkDao.findOne(userCommunityTalkId);
		if(userCommunityTalk!=null){
			UserCommunityTalkContent userCommunityTalkContent = new UserCommunityTalkContent();
			userCommunityTalkContent.setUserCommunityTalkId(userCommunityTalkId);
			userCommunityTalkContent.setContent(HTMLInputFilter.clean(content));
			userCommunityTalkContent.setUser(user);
			userCommunityTalkContent.setCreateTime(DateConvertUtils.getNow());
			userCommunityTalkContent.setUpdateTime(DateConvertUtils.getNow());
			userCommunityTalkContent.setIsDel(1);
			userCommunityTalkContent.setSource(1);
			userCommunityTalkContentDao.save(userCommunityTalkContent);
			//更新问题的updatetime
			userCommunityTalk.setIsReply(1);
			userCommunityTalk.setUpdateTime(DateConvertUtils.getNow());
			userCommunityTalkDao.save(userCommunityTalk);
		}
	}

	/**
	 * 根据id得到问题详情
	 * @param id
	 * @return
	 */
	public UserCommunityTalkVo getById(Integer id,String key) {
		UserCommunityTalk userCommunityTalk = userCommunityTalkDao.getByIdAndIsDelete(id,1);
		if(userCommunityTalk!=null){
			Map<String,Object> searchParams = new HashMap<String, Object>();
			searchParams.put("EQ_userCommunityTalkId", id+"");
			searchParams.put("EQ_isDel", "1");
			if(StringUtils.isNotBlank(key)){
				searchParams.put("LIKE_content", key);
			}
			List<UserCommunityTalkContent> userCommunityTalkContents = findUserCommunityTalkContentList(searchParams);
			List<UserCommunityTalkContentVo> userCommunityTalkContentVos = new ArrayList<UserCommunityTalkContentVo>();
			//回复
			for (UserCommunityTalkContent userCommunityTalkContent : userCommunityTalkContents) {
				UserCommunityTalkContentVo userCommunityTalkContentVo= new UserCommunityTalkContentVo(
						userCommunityTalkContent.getId(),
						userCommunityTalkContent.getCreateTimeString(),
						userCommunityTalkContent.getContent(),
						StringUtils.isNotBlank(userCommunityTalkContent.getUserHeadPath())?userCommunityTalkContent.getUserHeadPath():getUserHeadPath(userCommunityTalkContent.getUser()),
						userCommunityTalkContent.getUser()!=null?userCommunityTalkContent.getUser().getId():null,
						userCommunityTalkContent.getUser()!=null?userCommunityTalkContent.getUser().getUsername():userCommunityTalkContent.getUserName()
						);
				userCommunityTalkContentVos.add(userCommunityTalkContentVo);
			}
			//问题vo
			Company company = null;
			if(userCommunityTalk.getCompany()!=null){
				company = userCommunityTalk.getCompany();
			}
			if(company==null&&userCommunityTalk.getJobBase()!=null){
				company = userCommunityTalk.getJobBase().getCompany();
			}
			UserCommunityTalkVo userCommunityTalkVo= new UserCommunityTalkVo(
					userCommunityTalk.getId(),
					userCommunityTalk.getCreateTimeString(),
					userCommunityTalk.getTitle(),
					userCommunityTalk.getIntroduction(),
					StringUtils.isNotBlank(userCommunityTalk.getUserHeadPath())?userCommunityTalk.getUserHeadPath():getUserHeadPath(userCommunityTalk.getUser()),
					userCommunityTalk.getUser()!=null?userCommunityTalk.getUser().getUsername():userCommunityTalk.getUserName(),
					userCommunityTalk.getJobBase()!=null?userCommunityTalk.getJobBase().getId():null,
					company!=null?company.getId():null,		
					company!=null?company.getAbbreviation():null,	
					userCommunityTalkContentVos
					);
			return userCommunityTalkVo;
		}
		return null;
	}

	
	/**
	 * 根据条件查询回复
	 * @param searchParams
	 * @return
	 */
	private List<UserCommunityTalkContent> findUserCommunityTalkContentList(Map<String,Object> searchParams){
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<UserCommunityTalkContent> spec = DynamicSpecifications.bySearchFilter(filters.values(),UserCommunityTalkContent.class);
		return userCommunityTalkContentDao.findAll(spec);
	}

	/**
	 * 分页得到所有的有问答的社区
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Integer> findCommunityPage(Integer pageNumber, Integer pageSize) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto");
		return userCommunityTalkDao.findCommunityPage(pageRequest);
	}

	/**
	 * 分页得到所有有问答的岗位
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Integer> findJobBasePage(Integer pageNumber, Integer pageSize) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto");
		return userCommunityTalkDao.findJobBasePage(pageRequest);
	}
	
	/**
	 * 分页得到所有问答的id
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Integer> findWenDaIdPage(Integer pageNumber, Integer pageSize) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "auto");
		return userCommunityTalkDao.findWenDaIdPage(pageRequest);
	}

	public List<UserCommunityTalk> getUserCommunityTalkBy(Integer userId){
		return userCommunityTalkDao.getUserCommunityTalkBy(userId);
	}



}