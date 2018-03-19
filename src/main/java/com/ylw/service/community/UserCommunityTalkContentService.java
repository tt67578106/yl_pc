package com.ylw.service.community;


import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ylw.entity.community.UserCommunityTalkContent;
import com.ylw.repository.community.UserCommunityTalkContentDao;
import com.ylw.repository.community.UserCommunityTalkDao;
import com.ylw.util.Constants;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.persistence.DynamicSpecifications;
import com.ylw.util.persistence.SearchFilter;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class UserCommunityTalkContentService {

	private UserCommunityTalkContentDao userCommunityTalkContentDao;
	
	private UserCommunityTalkDao userCommunityTalkDao;

	public UserCommunityTalkContent getUserCommunityTalkContent(java.lang.Integer id){
		return userCommunityTalkContentDao.findOne(id);
	}

	public void save(UserCommunityTalkContent entity){
		userCommunityTalkContentDao.save(entity);
	}
	/**
	 * 删除话题评论
	 * @param id
	 * @return
	 */
	public String delete(java.lang.Integer id){
		userCommunityTalkContentDao.delete(id);
		return Constants.RETURN_STATUS_SUCCESS;
	}

	public Page<UserCommunityTalkContent> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType,String replyTime){
		SearchFilter[] orList = null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} - \\d{4}-\\d{2}-\\d{2}");
		Pattern patternToday = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
		Matcher isTime = pattern.matcher(replyTime);
		if((StringUtils.isNotBlank(replyTime) && isTime.matches())){
			Date startTime = DateConvertUtils.firstDate(replyTime.substring(0, 10));
			Date endTime = DateConvertUtils.endDate(replyTime.substring(13));
			if(DateConvertUtils.daysBetween(endTime, startTime)>=0){
				searchParams.put("DATEGTE_userCommunityTalk.createTime", replyTime.substring(0, 10));
				searchParams.put("DATELTE_userCommunityTalk.createTime", replyTime.substring(13));
			}
		}else if((StringUtils.isNotBlank(replyTime) && patternToday.matcher(replyTime).matches())){
			searchParams.put("DATEGTE_userCommunityTalk.createTime", replyTime);
			searchParams.put("DATELTE_userCommunityTalk.createTime", replyTime);
		}
		
		if(searchParams.containsKey("content"))//ID 名称 简称
		{
			if(searchParams.get("content").toString() != ""){
				if(!StringUtils.isNumeric(searchParams.get("content").toString())){
					searchParams.put("LIKE_content", searchParams.get("content").toString());
				}else {
					searchParams.put("EQ_id", searchParams.get("content").toString());
				}
			}
			searchParams.remove("content");
		}
		Specification<UserCommunityTalkContent> spec = buildSpecification(searchParams, orList);
		return userCommunityTalkContentDao.findAll(spec, pageRequest);
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
	private Specification<UserCommunityTalkContent> buildSpecification(Map<String, Object> searchParams, SearchFilter... orList) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<UserCommunityTalkContent> spec = DynamicSpecifications.bySearchFilterOr(filters.values(),UserCommunityTalkContent.class,orList);
		return spec;
	}

	@Autowired
	public void setUserCommunityTalkContentDao(UserCommunityTalkContentDao userCommunityTalkContentDao) {
		this.userCommunityTalkContentDao = userCommunityTalkContentDao;
	}
	@Autowired
	public void setUserCommunityTalkDao(UserCommunityTalkDao userCommunityTalkDao) {
		this.userCommunityTalkDao = userCommunityTalkDao;
	}
	
	
}