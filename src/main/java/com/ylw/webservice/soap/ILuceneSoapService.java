package com.ylw.webservice.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.cxf.feature.Features;

@WebService(serviceName = "luceneSoapService", targetNamespace = WsConstants.NS)
@Features(features = "org.apache.cxf.feature.LoggingFeature")
public interface ILuceneSoapService {
	/**
	 * 重建索引 type =1 公司  type = 2 岗位
	 * @param type
	 * @param isCreate
	 * @return
	 */
	@WebMethod
	public boolean reconstructionLucene(@WebParam(name="type") String type,@WebParam(name="isCreate") Boolean isCreate);
	
	/**
	 * 增加一条索引
	 * @param type
	 * @param id
	 * @return
	 */
	public boolean addLucene(@WebParam(name="type") String type,@WebParam(name="id") Integer id);
	
	/**
	 * 更新一条索引
	 * @param type
	 * @param id
	 * @return
	 */
	public boolean updateLucene(@WebParam(name="type") String type,@WebParam(name="id") Integer id);
	
	
	/**
	 * 删除一条索引
	 * @param type
	 * @param id
	 * @return
	 */
	public boolean deleteLucene(@WebParam(name="type") String type,@WebParam(name="id") Integer id);
	

	/**
	 * 根据新增时间批量更新
	 * @param type
	 * @param create
	 * @return
	 */
	public boolean addLuceneByCreateTime(@WebParam(name="type") String type,@WebParam(name="createTime") String create);
	
	/**
	 * 根据修改时间批量更新
	 * @param type
	 * @param upate
	 * @return
	 */
	public boolean addLuceneByUpateTime(@WebParam(name="type") String type,@WebParam(name="upateTime") String upate);
}
