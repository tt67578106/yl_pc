package com.ylw.service.base;

import java.util.Map;

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

import com.alibaba.fastjson.JSONObject;
import com.dl.mc.kit.YoulanWechatKit;
import com.ylw.entity.base.Feedback;
import com.ylw.repository.FeedbackDao;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.HTMLInputFilter;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class FeedbackService {

	private FeedbackDao feedbackDao;

	public Feedback getFeedback(java.lang.Integer id){
		return feedbackDao.findOne(id);
	}

	public void save(Feedback entity,String ip){
		entity.setSource("pc");
		entity.setStatus(1);
		entity.setCreateIp(ip);
		entity.setCreateTime(DateConvertUtils.getNow());
		entity.setContactInfo(HTMLInputFilter.Html2Text(entity.getContactInfo()));
		entity.setContent(HTMLInputFilter.Html2Text(entity.getContent()));
		feedbackDao.save(entity);
		noticeRelevantPerson(entity.getContactInfo(), entity.getContent());
	}

	public void delete(java.lang.Integer id){
		feedbackDao.delete(id);
	}

	public Page<Feedback> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Feedback> spec = buildSpecification(userId.longValue(), searchParams);
		return feedbackDao.findAll(spec, pageRequest);
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
	private Specification<Feedback> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<Feedback> spec = DynamicSpecifications.bySearchFilter(filters.values(), Feedback.class);
		return spec;
	}

	@Autowired
	public void setFeedbackDao(FeedbackDao feedbackDao) {
		this.feedbackDao = feedbackDao;
	}
	
	/**
	 * 收到反馈后通知相关人
	 * 王肖勇  olwfojhzRSthnKsOS_jVdJNLidiA
	 * 王志伟  olwfojuhOH-sZx2tfSmSYXY-G8sE
	 * 邵聪聪  olwfojg9DYkbHsB1i7fUKhugKHCc
	 * 蔡冬梅  olwfojt6pTiQzZWlMZoSh1Ve9t9c
	 * 朱春花  olwfojpnAcQ_voNOCATmM2aY0hg4
	 * 我      olwfojkPCbiVYbeL2hRKHgWxQ3mY
	 * @param contact
	 * @param comment
	 */
	private void noticeRelevantPerson(final String contact, final String comment){
		final String[] openIdArray = new String[]{"olwfojhzRSthnKsOS_jVdJNLidiA","olwfojuhOH-sZx2tfSmSYXY-G8sE","olwfojg9DYkbHsB1i7fUKhugKHCc","olwfojt6pTiQzZWlMZoSh1Ve9t9c","olwfojpnAcQ_voNOCATmM2aY0hg4","olwfojkPCbiVYbeL2hRKHgWxQ3mY"};
		final String templateId = "VqExeeteP55_Oye9AM9XP-VQnM88psu1X359guVBrKo";
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("first", "优蓝网留言反馈提醒");
				jsonObj.put("keyword1", "优蓝网用户反馈");
				jsonObj.put("keyword2", (StringUtils.isBlank(contact)?"无":contact));
				jsonObj.put("keyword3", DateConvertUtils.format(DateConvertUtils.getNow(), "yyyy-MM-dd HH:mm:ss"));
				jsonObj.put("keyword4", comment);
				jsonObj.put("remark", "本消息为系统自动发出，请相关人员即时登录系统进行处理");
				for (String toUserOpenId : openIdArray) {
					YoulanWechatKit.pushYlTemplateMsg(templateId, toUserOpenId, null, jsonObj.toJSONString());
				}
			}
		});
		thread.start();
	}

}