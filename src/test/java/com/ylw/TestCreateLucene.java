//package com.ylw;
//
//import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
//import org.junit.Test;
//
//public class TestCreateLucene {
//
//	@Test
//	public void createLucene(){
//		 // 生成索引  client.invoke(1,2,3);
//		 // 3 false 非新增  true 新增
//		 // 2 1公司列表  2岗位列表
//		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//		org.apache.cxf.endpoint.Client client = dcf.createClient("http://localhost:18881/ws/soap/luceneSoapService?wsdl");
//		try {
//			client.invoke("reconstructionLucene","2",false);
////			client.invoke("addLucene","2",110);  /* addLucene deleteLucene  updateLucene*/
////			client.invoke("addLuceneByCreateTime","2","2015-5-20"); //addLuceneByUpateTime
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//}
