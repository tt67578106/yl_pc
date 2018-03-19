package com.ylw.webservice.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.cxf.feature.Features;

@WebService(serviceName = "memcachedSoapService", targetNamespace = WsConstants.NS)
@Features(features = "org.apache.cxf.feature.LoggingFeature")
public interface IMemcachedSoapService {
	/**
	 * 刷新Memcached缓存专用
	 * @param key
	 * @return
	 */
	@WebMethod
	public boolean refreshCache(@WebParam(name="key") String key);
	
	/**
	 * 刷新岗位类型Cache缓存专用
	 * @param key
	 * @return
	 */
	public boolean refreshJobTypeCache();
	
	/**
	 * 刷新默认所有缓存
	 * @return
	 */
	@WebMethod
	public boolean refreshAllCache();
}
