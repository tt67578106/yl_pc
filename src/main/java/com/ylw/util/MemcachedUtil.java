package com.ylw.util;

import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;

public class MemcachedUtil {
	/**
	 * 操作对象
	 */
	private static MemcachedClient memcachedClient;
	/**
	 * 是否在生产环境
	 */
	public static boolean inProductionEnv = false;
	/**
	 * 通过key查询对象
	 * @param key
	 * @return
	 */
	public static Object getCacheValue(String key){
		if(StringUtils.isNotBlank(key)){
			return getMemcachedClient().get(key);
		}
		return null;
	}
	/**
	 * 将对象存到memcached,设置值时,无论有无都添加
	 * @param key
	 * @param time
	 * @param value
	 */
	public static void addCache(String key, int time, Object value){
		getMemcachedClient().add(key, time, value);
	}
	/**
	 * 将对象存到memcached,设置值时,有则更新，无则添加
	 * @param key
	 * @param time
	 * @param value
	 */
	public static void setCache(String key, int time, Object value){
		getMemcachedClient().set(key, time, value);
	}
	/**
	 * 根据key删除对象
	 * @param key
	 */
	public static void deleteCache(String key){
		getMemcachedClient().delete(key);
	}
	/**
	 * client内部使用
	 * @return
	 */
	private static MemcachedClient getMemcachedClient() {
		if (memcachedClient == null) {
			ResourceBundle configResource = ResourceBundle.getBundle("application");
			String env = configResource.getString("memcached.server.env");
			if ("production".equals(env)) {// 生产环境
				inProductionEnv = true;
			}
			String host = configResource.getString("memcached.server.host");
			String port = configResource.getString("memcached.server.port");
			String username = configResource.getString("memcached.server.username");
			String password = configResource.getString("memcached.server.password");
			MemcachedClient cache = null;
			try {
				if (inProductionEnv) {
					AuthDescriptor authDesc = new AuthDescriptor(new String[] { "PLAIN" },
							new PlainCallbackHandler(username, password));
					cache = new MemcachedClient(new ConnectionFactoryBuilder().setProtocol(Protocol.BINARY)
							.setAuthDescriptor(authDesc).build(), AddrUtil.getAddresses(host + ":" + port));
				} else {
					cache = new MemcachedClient(new BinaryConnectionFactory(),
							AddrUtil.getAddresses(host + ":" + port));
				}
				return cache;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return memcachedClient;
	}
	/**
	 * client启动时赋值
	 */
	public static void initmemcachedClient() {
		ResourceBundle configResource = ResourceBundle.getBundle("application");
		String env = configResource.getString("memcached.server.env");
		if("production".equals(env)){//生产环境
			inProductionEnv = true;
		}
		String host = configResource.getString("memcached.server.host");
		String port = configResource.getString("memcached.server.port");
		String username = configResource.getString("memcached.server.username");
		String password = configResource.getString("memcached.server.password");
		MemcachedClient cache = null;
		try {
			if(inProductionEnv){
				AuthDescriptor authDesc = new AuthDescriptor(new String[] { "PLAIN" }, new PlainCallbackHandler(username, password));
				cache = new MemcachedClient(
						new ConnectionFactoryBuilder().setProtocol(Protocol.BINARY).setAuthDescriptor(authDesc).build(),
						AddrUtil.getAddresses(host + ":" + port));
			}else{
				cache = new MemcachedClient(new BinaryConnectionFactory(), AddrUtil.getAddresses(host + ":" + port));
			}			memcachedClient = cache;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 清空
	 */
	public static void freeMemcachedClient(){
		if(MemcachedUtil.memcachedClient != null){
			MemcachedUtil.memcachedClient.shutdown();
		}
	}
}
