package com.ylw.webservice.soap;

import java.io.IOException;
import java.util.Date;

import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.feature.Features;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import com.ylw.service.base.JobSeachLuceneService;
import com.ylw.util.DateConvertUtils;

@WebService(serviceName = "luceneSoapService", targetNamespace = WsConstants.NS)
@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class LuceneSoapServiceImpl implements ILuceneSoapService {

	private JobSeachLuceneService jobSeachLuceneService;
	
//	private CompanySeachLuceneService companySeachLuceneService;
	 
	@Override
	public boolean reconstructionLucene(String type,Boolean isCreate) {
		if(StringUtils.isNoneBlank(type) && type.equals("2")){
			try {
				jobSeachLuceneService.createIndexFile(isCreate);
				return true;
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean addLucene(String type, Integer id) {
		if(StringUtils.isNoneBlank(type) && id!=null){
			if(type.equals("1")){
				
			}else if(type.equals("2")){
				jobSeachLuceneService.addLucene(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateLucene(String type, Integer id) {
		if(StringUtils.isNoneBlank(type) && id!=null){
			if(type.equals("1")){
				
			}else if(type.equals("2")){
				jobSeachLuceneService.updateLucene(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteLucene(String type, Integer id) {
		if(StringUtils.isNoneBlank(type) && id!=null){
			if(type.equals("1")){
			}else if(type.equals("2")){
				jobSeachLuceneService.deleteLucene(id);
				return true;
			}
		}
		return false;
	}

	@Autowired
	public void setJobSeachLuceneService(JobSeachLuceneService jobSeachLuceneService) {
		this.jobSeachLuceneService = jobSeachLuceneService;
	}

	@Override
	public boolean addLuceneByCreateTime(String type, String create) {
		if(StringUtils.isNoneBlank(type) && create!=null){
			if(type.equals("1")){
			}else if(type.equals("2")){
				jobSeachLuceneService.addLuceneByCreateTime(create);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addLuceneByUpateTime(String type, String update) {
		if(StringUtils.isNoneBlank(type) && update!=null){
			if(type.equals("1")){
			}else if(type.equals("2")){
				jobSeachLuceneService.addLuceneByUpateTime(update);
				return true;
			}
		}
		return false;
	}

//	@Autowired
//	public void setCompanySeachLuceneService(
//			CompanySeachLuceneService companySeachLuceneService) {
//		this.companySeachLuceneService = companySeachLuceneService;
//	}
	
	

}
