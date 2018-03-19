package com.ylw.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ylw.entity.sys.ApiApp;
import com.ylw.entity.sys.ApiAppChannel;
import com.ylw.repository.ApiAppChannelDao;
import com.ylw.repository.ApiAppDao;

/**
 * 记录用户访问路
 *  @author jack
 */
@Component
@Transactional
public class AppKeyService {

	private ApiAppDao apiAppDao;
	
	private ApiAppChannelDao apiAppChannelDao;
	
	@Autowired
	public void setApiAppDao(ApiAppDao apiAppDao) {
		this.apiAppDao = apiAppDao;
	}

	@Autowired
	public void setApiAppChannelDao(ApiAppChannelDao apiAppChannelDao) {
		this.apiAppChannelDao = apiAppChannelDao;
	}


	/**
	 * 校验APPkey 是否有效
	 * @param fromKey
	 * @return
	 */
	public boolean validAppKey(String fromKey) {
		boolean result = false;
		ApiApp apiApp = apiAppDao.getByAppKey(fromKey);
		if(apiApp!=null&&apiApp.getStatusNo()==1){
			result = true;
		}
		return result;
	}

	/**
	 * 校验子渠道是否可用
	 * @param appChannelCode
	 * @return
	 */
	public boolean validAppChannelCode(String appChannelCode) {
		boolean result=false;
		ApiAppChannel apiAppChannel = apiAppChannelDao.getByCode(appChannelCode);
		if(apiAppChannel!=null&&apiAppChannel.getStatusNo()==1){
			result = true;
		}
		return result;
	}
}
