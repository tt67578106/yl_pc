package com.ylw.webservice.soap;

import javax.jws.WebService;

import org.apache.cxf.feature.Features;
import org.springframework.beans.factory.annotation.Autowired;

import com.ylw.service.job.JobTypeService;
import com.ylw.taglib.DictionaryCodeTag;
import com.ylw.util.MemcachedUtil;

@WebService(serviceName = "memcachedSoapService", targetNamespace = WsConstants.NS)
@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class MemcachedSoapServiceImpl implements IMemcachedSoapService {

	@Autowired
	private JobTypeService jobTypeService;
	
	@Override
	public boolean refreshCache(String key) {
		try {
			String[] keys = key.split(",");
			for (String k : keys) {
				MemcachedUtil.deleteCache(k);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean refreshJobTypeCache() {
		jobTypeService.initCache();
		DictionaryCodeTag.jobTypeList = null;
		System.out.println("岗位类型缓存清除成功");
		return true;
	}

	@Override
	public boolean refreshAllCache() {
		// TODO Auto-generated method stub
		return false;
	}



}
