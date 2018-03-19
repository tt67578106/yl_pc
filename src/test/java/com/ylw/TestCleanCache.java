//package com.ylw;
//
//import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
//import org.junit.Test;
//
//public class TestCleanCache {
//
//
//	/**
//	 * 首页轮播推荐位 缓存key
//	 */
//	public static final String CACHE_KEY_HOME_SHUFFLING_JOBS = "cache_pc_key_recommend_company";
//
//	/**
//	 * 首页活动
//	 */
//	public static final String CACHE_KEY_HOME_ARTICLE_ACTIVITIES= "cache_key_home_article_activities";
//	@Test
//	public void cleanCache(){
//		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//		org.apache.cxf.endpoint.Client client = dcf.createClient("http://10.0.11.66:8001/ws/soap/memcachedSoapService?wsdl");
//		try {
//			/*StringBuffer sb = new StringBuffer();
//			for (int i = 1; i <= 20; i++) {
//				sb.append(Constants.CACHE_KEY_JOB_DYYH+i).append(",");
//			}*/
//			//client.invoke("refreshCache", "CACHE_KEY_HOME_TOP_AD");
//			client.invoke("refreshAllCache");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
