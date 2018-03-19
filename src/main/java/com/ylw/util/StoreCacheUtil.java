package com.ylw.util;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 持久化缓存工具类
 * 
 * @author Nicolas Create Date 2014年4月4日
 */
public class StoreCacheUtil {

	private static CacheManager cacheManager;
	public static CacheManager getCacheManager() {
		return cacheManager;
	}
	/**
	 * 根据缓存名称和key查询对象
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static Object getCacheValue(String cacheName, String key){
		Cache cache = cacheManager.getCache(cacheName);
		Element ele = cache.get(key);
		if (ele!=null) {
			return ele.getObjectValue();
		}
		return null;
	}
	/**
	 * 根据缓存名保存cache对象
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public static void setCacheValue(String cacheName, String key,Object value){
		Cache cache = cacheManager.getCache(cacheName);
		if(cache!=null){
			cache.put(new Element(key, value));
		}
	}
	/**
	 * 查询缓存名称
	 * @param cacheName
	 * @return
	 */
	public static Cache getCache(String cacheName){
		return cacheManager.getCache(cacheName);
	}

	/**
	 * 存储到缓存
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public static void putCache(String cacheName,String key, Object value){
		Cache cache = cacheManager.getCache(cacheName);
		cache.putQuiet(new Element(key, value));
	}
	/**
	 * 初始化
	 */
	public void init() {
		URL url = getClass().getResource("/cache/ehcache-store.xml");
		cacheManager = new CacheManager(url);
	}
	/**
	 * 销毁
	 */
	public static void destroy() {
		cacheManager.shutdown();
	}
}
