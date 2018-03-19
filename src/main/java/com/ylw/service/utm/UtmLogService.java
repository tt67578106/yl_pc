package com.ylw.service.utm;


/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import com.ylw.entity.community.UserCommunityTalk;
import com.ylw.entity.utm.UtmLog;
import com.ylw.repository.community.UserCommunityTalkDao;
import com.ylw.repository.utm.UtmLogDao;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.HttpRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Transactional
public class UtmLogService {
	private UtmLogDao utmLogDao;
	@Autowired
	private UserCommunityTalkDao userCommunityTalkDao;
	private Logger logger = LoggerFactory.getLogger(UtmLogService.class);

	public UserCommunityTalk getUserCommunityTalk(Integer id, Integer idDelect){
		UserCommunityTalk byUserCommunityTalk = userCommunityTalkDao.getByIdAndIsDelete(id,idDelect);
		return byUserCommunityTalk;
	}

	public void saveUtmLog(Long projectId, Date logTime, String visitorId, String usersType, Long resUidInt, String domainName,
						   String platformName,Integer objectType, String objectId,String objectCode, String objectProperty,
						   String eventType, String eventNature, String oappSource, String oappApiKey,String pup1,Integer resApplyId){
		UtmLog utmLog=new UtmLog();
		utmLog.setProjectId(projectId);
		utmLog.setLogTime(DateConvertUtils.getNow());
		utmLog.setVisitorId(visitorId);
		utmLog.setUsersType(usersType);
		utmLog.setResUidInt(resUidInt);
		utmLog.setDomainName(domainName);
		utmLog.setPlatformName(platformName);
		utmLog.setObjectType(objectType);
		utmLog.setObjectId(objectId);
		utmLog.setObjectCode(objectCode);
		utmLog.setObjectProperty(objectProperty);
		utmLog.setEventType(eventType);
		utmLog.setEventNature(eventNature);
		utmLog.setOappSource(oappSource);
		utmLog.setOappApiKey(oappApiKey);
		utmLog.setPup1(pup1);
		utmLog.setResApplyId(resApplyId);
		//解析domainName Url获取utmGeneralize参数值
		utmLog.setPutmSource(HttpRequestUtil.parse(domainName).getParameter("cc"));
		utmLog.setPutmMedium(HttpRequestUtil.parse(domainName).getParameter("utmGeneralizeCode"));
		utmLog.setGeneralizeCode(HttpRequestUtil.parse(domainName).getParameter("utmGeneralizeCode"));
		utmLog.setPutmCampaign(HttpRequestUtil.parse(domainName).getParameter("utmGeneralizeCode"));
		utmLog.setPutmContent(HttpRequestUtil.parse(domainName).getParameter("utmGeneralizeCode"));
		utmLog.setPutmKeyWord(HttpRequestUtil.parse(domainName).getParameter("utmGeneralizeCode"));
		utmLogDao.save(utmLog);
	}
	@Autowired
	private void setUtmLogDao(UtmLogDao utmLogDao){
		this.utmLogDao=utmLogDao;
	}
}