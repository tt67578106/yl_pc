package com.ylw.service.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser.Operator;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.store.LockObtainFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.ylw.entity.base.Image;
import com.ylw.entity.code.CodeAreaCity;
import com.ylw.entity.job.JobBase;
import com.ylw.entity.job.JobConfig;
import com.ylw.entity.job.JobDetail;
import com.ylw.entity.vo.CompanyListVo;
import com.ylw.repository.JobBaseDao;
import com.ylw.util.Constants;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.HTMLInputFilter;
import com.ylw.util.luceneManage.JobManager;
import com.ylw.util.persistence.DynamicSpecifications;
import com.ylw.util.persistence.SearchFilter;

@Component
public class JobSeachLuceneService {
	private   JobManager indexManger=null;
	//创建简单中文分析器 创建索引使用的分词器必须和查询时候使用的分词器一样，否则查询不到想要的结果
	private  Analyzer analyzer = null;
	private  Logger logger = LoggerFactory.getLogger(JobSeachLuceneService.class);
	private  ReadWriteLock lock = new ReentrantReadWriteLock(false);
	private  boolean isrunning=false;
	private String indexPath=null;
	private  JobBaseDao jobBaseDao;
	public JobSeachLuceneService() {
		indexPath = Constants.LUCENE_JOB;
	}
	/**
     * 索引和分词器初始化
     */
    public  void initLucene()
    {
   	 if(indexManger==null)
   	 {
   		 indexManger=JobManager.getInstance(indexPath, false);
   		 analyzer=indexManger.getAnalyzer();

   	 }
    }
    /**
     * 工作列表搜索条件
     * @param pageSize 
     * @param pageNumber 
     * @throws ParseException 
     * @throws IOException 
     * @throws InvalidTokenOffsetsException 
     */
    public  Page<CompanyListVo> getJobListByLucene(Map<String, Object> searchParams, int pageNumber, int pageSize,String sortType,String defaultCityId){
    	initLucene();
		List<CompanyListVo> baseList = new ArrayList<CompanyListVo>();
		IndexSearcher searcher = indexManger.getSearcher();
		BooleanQuery bquery=new BooleanQuery();
		BooleanQuery salaryQuery=null;
		int totalCount = 0;
		ScoreDoc[] hits =null;
		Object serachName = searchParams.get("LIKE_searchName");
		Object jobLabel = searchParams.get("LIKE_jobLabel");
		Object jobType = searchParams.get("LIKE_jobType");
		Object totalSalary = searchParams.get("LIKE_totalsalary");
		Object cityId = searchParams.get("EQ_city.id");
		Object countyid = searchParams.get("EQ_countyid");
		//临时处理昆山的情况
		if(cityId!=null&&cityId.equals("346")){
			cityId = "78";
			countyid = "785";
		}
		if(StringUtils.isNotBlank(defaultCityId)&&defaultCityId.equals("346")){
			defaultCityId = "78";
			countyid = "785";
		}
		try {
		if(serachName != null &&!serachName.equals("0") && StringUtils.isNotBlank(HTMLInputFilter.filterSimple(serachName.toString()))){//搜索岗位名称，岗位title，岗位类型，公司名，公司简称
			//大字段 全文检索
			Query querykeyword=null;
			MultiFieldQueryParser parserresume = new MultiFieldQueryParser(
					new String[]{"companyName","jobname","jobTitle","companyAbbreviation","jobType","jobLabel"},
					analyzer);
			parserresume.setDefaultOperator(Operator.OR);
			parserresume.setPhraseSlop(0);
				querykeyword=parserresume.parse(HTMLInputFilter.filterSimple(serachName.toString()));
			bquery.add(querykeyword, BooleanClause.Occur.MUST);
		}
		if(jobLabel != null && !jobLabel.equals("0") &&  StringUtils.isNotBlank(HTMLInputFilter.filterSimple(jobLabel.toString()))){
			Query queryjobLabel=null;
			MultiFieldQueryParser parserresume = new MultiFieldQueryParser(
					new String[]{"jobLabel"},
					analyzer);
			parserresume.setDefaultOperator(Operator.AND);
			parserresume.setPhraseSlop(0);
			queryjobLabel=parserresume.parse(HTMLInputFilter.filterSimple(jobLabel.toString()));
			bquery.add(queryjobLabel, BooleanClause.Occur.MUST);
		}
		if( jobType != null && !jobType.equals("0")&& !jobType.equals("job") && StringUtils.isNotBlank(jobType.toString())){
				Query queryjobLabel=null;
				MultiFieldQueryParser parserresume = new MultiFieldQueryParser(
						new String[]{"jobType"},
						analyzer);
				parserresume.setDefaultOperator(Operator.AND);
				parserresume.setPhraseSlop(0);
				queryjobLabel=parserresume.parse(HTMLInputFilter.filterSimple(jobType.toString()));
				bquery.add(queryjobLabel, BooleanClause.Occur.MUST);
		}
		if( countyid != null  && !countyid.equals("0") && StringUtils.isNotBlank(HTMLInputFilter.filterSimple(countyid.toString()))){
			Query queryJobTargetCountyid=null;
			MultiFieldQueryParser parserQueryJobCountId = new MultiFieldQueryParser(
					new String[]{"countyid"},
					analyzer);    
			queryJobTargetCountyid=parserQueryJobCountId.parse(HTMLInputFilter.filterSimple(countyid.toString()));
			bquery.add(queryJobTargetCountyid, BooleanClause.Occur.MUST);
		}
		//薪资
		if(totalSalary != null && !totalSalary.equals("0") && StringUtils.isNoneBlank(HTMLInputFilter.filterSimple(totalSalary.toString()))){
			String[] salarys = totalSalary.toString().split("-");
			if(salarys.length >1 && StringUtils.isNumeric(salarys[0])&&StringUtils.isNumeric(salarys[1])){
				salaryQuery = new BooleanQuery();
				int salaryFromValue = Integer.parseInt(salarys[0]);
				int salaryToValue = Integer.parseInt(salarys[1]);
				Query salaryFromquery = NumericRangeQuery.newIntRange("salaryFromInt", salaryFromValue,salaryToValue, true, true);
				Query salaryToquery = NumericRangeQuery.newIntRange("salaryToInt", salaryFromValue,salaryToValue, true, true);
				salaryQuery.add(salaryFromquery, BooleanClause.Occur.SHOULD);
				salaryQuery.add(salaryToquery, BooleanClause.Occur.SHOULD);
			}
	    }
		if( cityId != null && !cityId.equals("0") && StringUtils.isNotBlank(HTMLInputFilter.filterSimple(cityId.toString()))){
			cityId = cityId.toString();
		}else{
			cityId = defaultCityId;//默认城市
		}
		Query queryCompanyTargetCityId=null;
		MultiFieldQueryParser parserQueryCityId = new MultiFieldQueryParser(
				new String[]{"cityId"},
				analyzer);    
		queryCompanyTargetCityId=parserQueryCityId.parse(cityId.toString());
		bquery.add(queryCompanyTargetCityId, BooleanClause.Occur.MUST);
		Query queryJobTargetIsPublish=null;
		MultiFieldQueryParser parserQueryPublish = new MultiFieldQueryParser(
				new String[]{"isPublish"},
				analyzer);    
		queryJobTargetIsPublish=parserQueryPublish.parse("1");
		bquery.add(queryJobTargetIsPublish, BooleanClause.Occur.MUST);
		if(salaryQuery!=null){
			bquery.add(salaryQuery,BooleanClause.Occur.MUST);
		}
//		String sortFied = "createtime";
//		if(StringUtils.isNoneBlank(sortType) && sortType.equals("updatetime")){
//			sortFied = "updatetime";
//		} else if(StringUtils.isNoneBlank(sortType) && sortType.equals("salarydesc")){
//			sortFied = "salaryFrom";
//		}
		Sort sort = new Sort(new SortField("updatetime",Type.LONG,true),SortField.FIELD_SCORE, new SortField("companyId",Type.LONG,true), SortField.FIELD_SCORE);//排序
		TopFieldCollector topFieldCollector = TopFieldCollector.create(sort, ((pageNumber - 1) * pageSize + pageSize), false, false, false);//分页
		searcher.search(bquery, topFieldCollector);
		hits = topFieldCollector.topDocs((pageNumber - 1) * pageSize,pageSize).scoreDocs;;
		totalCount = topFieldCollector.getTotalHits();
//		System.out.println(totalCount);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Document doc;
		CompanyListVo  companyVo = null;
		List<JobBase> jobBaseList = new ArrayList<JobBase>();
		String jobTitle,isUrgency,jobId,jobnameValue,provinceid,jobCityName,imgPath,recruitcount
		,createtime,updatetime,jobTypeValue,jobLabelValue,companyNameValue,companyId,companyLogo,companyAbbreviation,validation
		,salaryFrom,ageTo,salaryTo,ageFrom,education,gender,isLoan,industryid,staffscale,companyCityName,companyProvinceId,companyCountyid,cooperationType;
//        RecommendJobVo recommendJobVo = null;
        try {
        String companyVoId = "";
        for (ScoreDoc scoreDoc : hits) {
        	int docId = scoreDoc.doc;
			doc = searcher.doc(docId);
			jobTitle = doc.get("jobTitle");
			isUrgency=doc.get("isUrgency");
			jobId=doc.get("jobId");
			jobnameValue=doc.get("jobname");
			provinceid=doc.get("provinceid");
			jobCityName=doc.get("jobCityName");
			imgPath=doc.get("imgPath");
	        createtime=doc.get("createtime");
	        updatetime=doc.get("updatetime");
	        jobTypeValue=doc.get("jobType");
	        jobLabelValue=doc.get("jobLabel");
	        companyNameValue=doc.get("companyName");
	        companyId=doc.get("companyId");
	        companyLogo=doc.get("companyLogo");
	        companyAbbreviation=doc.get("companyAbbreviation");
	        industryid = doc.get("companyIndustryid");
	        staffscale = doc.get("companyStaffscale");
	        companyCityName = doc.get("companyCityName"); 
	        companyProvinceId = doc.get("companyProvinceId"); 
	        companyCountyid = doc.get("companyCountyid"); 
	        validation=doc.get("validation");
	        salaryFrom=doc.get("salaryFrom");
	        ageTo=doc.get("ageTo");
	        salaryTo=doc.get("salaryTo");
	        ageFrom=doc.get("ageFrom");
	        education=doc.get("education");
	        gender=doc.get("gender");
	        countyid=doc.get("countyid");
	        isLoan = doc.get("isLoan");
	        cooperationType = doc.get("cooperationType");
	        Date create =  DateConvertUtils.parse(createtime,"yyyy-MM-dd");
	        Date update = DateConvertUtils.parse(updatetime,"yyyy-MM-dd");
        	JobBase jobBase = new JobBase();
 	        jobBase.setTitle(jobTitle);
 	        jobBase.setJobname(jobnameValue);
 	        CodeAreaCity city = new CodeAreaCity();
 	        city.setCityName(jobCityName);
 	        jobBase.setCity(city);
 	        Image image = new Image();
 	        image.setImgpath(imgPath);
 	        jobBase.setThumbnialImage(image);
 	        jobBase.setJobType(jobTypeValue);
 	        jobBase.setJobLabel(jobLabelValue);
 	        if(StringUtils.isNoneBlank(countyid.toString()) && StringUtils.isNumeric(countyid.toString()))jobBase.setCountyid(Integer.parseInt(countyid.toString()));
 	        JobConfig config = new JobConfig();
 			if(StringUtils.isNoneBlank(isUrgency) && StringUtils.isNumeric(isUrgency))config.setIsUrgency(Integer.parseInt(isUrgency));
 			if(StringUtils.isNoneBlank(jobId) && StringUtils.isNumeric(jobId))jobBase.setId(Integer.parseInt(jobId));
 			JobDetail detail = new JobDetail();
 			if(StringUtils.isNoneBlank(salaryFrom)&& StringUtils.isNumeric(salaryFrom))detail.setSalaryfrom(Integer.parseInt(salaryFrom));
 			if(StringUtils.isNoneBlank(salaryTo)&& StringUtils.isNumeric(salaryTo))detail.setSalaryto(Integer.parseInt(salaryTo));
 			if(StringUtils.isNoneBlank(ageFrom)&& StringUtils.isNumeric(ageFrom))detail.setAgefrom(Integer.parseInt(ageFrom));
 			if(StringUtils.isNoneBlank(ageTo)&& StringUtils.isNumeric(ageTo))detail.setAgeto(Integer.parseInt(ageTo));
 			if(StringUtils.isNoneBlank(education)&& StringUtils.isNumeric(education))detail.setEducation(Integer.parseInt(education));
 			if(StringUtils.isNoneBlank(gender)&& StringUtils.isNumeric(gender))detail.setGender(Integer.parseInt(gender));
 			jobBase.setJobConfig(config);
 			jobBase.setJobDetail(detail);
        	if(companyId!=null){
        		if(!companyId.equals(companyVoId)){
        			if(companyVo!=null){
        				companyVo.setJobBases(jobBaseList);
        				baseList.add(companyVo); 
        				jobBaseList = new ArrayList<JobBase>();
        			}
        			companyVo = new CompanyListVo();
	               	if(create!=null){
	               		companyVo.setCreateTime(create);
	     	        }
	     	        if(update!=null){
	     	        	companyVo.setUpdateTime(update);
	     	        }
        			if(StringUtils.isNoneBlank(companyId) && StringUtils.isNumeric(companyId))companyVo.setCompanyId(Integer.parseInt(companyId));
        			companyVo.setCompanyLogo(companyLogo);
        			companyVo.setCompanyImage(companyLogo);
        			companyVo.setCompanyName(companyNameValue);
        			if(StringUtils.isNoneBlank(validation)&& StringUtils.isNumeric(validation))companyVo.setValidation(Integer.parseInt(validation));
        			if(StringUtils.isNoneBlank(industryid)&& StringUtils.isNumeric(industryid))companyVo.setIndustryid(Integer.parseInt(industryid));
        			if(StringUtils.isNoneBlank(staffscale)&& StringUtils.isNumeric(staffscale))companyVo.setStaffscale(Integer.parseInt(staffscale));
        			companyVo.setCityName(companyCityName);
        			if(StringUtils.isNoneBlank(companyProvinceId)&& StringUtils.isNumeric(companyProvinceId))companyVo.setProvinceId(Integer.parseInt(companyProvinceId));
        			if(StringUtils.isNoneBlank(companyCountyid)&& StringUtils.isNumeric(companyCountyid))companyVo.setIcountyid(Integer.parseInt(companyCountyid));
        			if(StringUtils.isNoneBlank(staffscale)&& StringUtils.isNumeric(staffscale))companyVo.setStaffscale(Integer.parseInt(staffscale));
        			if(StringUtils.isNoneBlank(staffscale)&& StringUtils.isNumeric(staffscale))companyVo.setStaffscale(Integer.parseInt(staffscale));
        			if(StringUtils.isNoneBlank(isLoan)&& StringUtils.isNumeric(isLoan))companyVo.setIsLoan(Integer.parseInt(isLoan));
        			companyVo.setAbbreviation(companyAbbreviation);
        			if(StringUtils.isNoneBlank(cooperationType)&& StringUtils.isNumeric(cooperationType))companyVo.setCooperationType(Integer.parseInt(cooperationType));
        			companyVoId = companyId;
        		}
        		jobBaseList.add(jobBase);
        	}
        }} catch (IOException e) {
        	e.printStackTrace();
        }
        if(baseList.size()==0&&companyVo!=null){
			companyVo.setJobBases(jobBaseList);
			baseList.add(companyVo); 
			jobBaseList = new ArrayList<JobBase>();
		}
        PageRequest pageable = new PageRequest(pageNumber - 1, pageSize,null);
    	Page<CompanyListVo> page = new PageImpl<CompanyListVo>(baseList, pageable, totalCount);
    	return page;
    }
    
    /**
     * 初始化索引
     * @throws IOException 
     */
    public  void createIndexFile(Boolean isCreate) throws ParseException, IOException {
		lock.writeLock().lock();
		isrunning=true;
		Integer pagesize=500;
		IndexWriter indexWriter = null ;
		try{
			initLucene();
			indexWriter = indexManger.getWriter();
			if (!isCreate){
				logger.info("正在重建索引！");
				indexWriter.deleteAll();
				indexWriter.commit();
				logger.info("清空索引！");
			}else{
				logger.info("开始新建索引");
			}
	        Map<String, Object> map = new HashMap<String, Object>();
			int i=1;
			int totalpages=0;
			boolean first=true;
			long lastid=0;
			long t=0;
			do{
				logger.info("索引处理,获取数据！每页"+pagesize);
				t=System.currentTimeMillis(); 
				Page<JobBase> pages=getJobPage(map, i, pagesize, "auto");
				logger.info("获取数据时间:"+ String.valueOf((t-System.currentTimeMillis())/1000));
				t=System.currentTimeMillis(); 
				
				if (first){
					totalpages=pages.getTotalPages();
					first=false;
					if(totalpages==0){
						break;
					}
				}
				lastid=pages.getContent().get(pages.getContent().size()-1).getId();
				logger.info("索引处理,共"+totalpages+"页,正在处理第"+i+"页");
				lastid=getJobBaseDoc(JobManager.reCreateIndex,pages.getContent(),indexWriter);
				logger.info("处理索引时间:"+ String.valueOf((t-System.currentTimeMillis())/1000));
				
				//em.clear();
				logger.info("索引处理,共"+totalpages+"页,处理完成第"+i+"页***");
				i++;
				pages=null;
				if (i%10==0){
					System.gc();
					logger.info("回收");
				}
			}while(totalpages>=i);
				logger.info("新建索引结束");
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			isrunning=false;
			lock.writeLock().unlock();
		}
	}
    
    /**
     * 初始化索引
     * @throws IOException 
     */
    public  void addIndexFile(Boolean createOrUpate,String time) throws ParseException, IOException {
		lock.writeLock().lock();
		isrunning=true;
		Integer pagesize=500;
		IndexWriter indexWriter = null ;
		try{
			initLucene();
			indexWriter = indexManger.getWriter();
				logger.info("开始新增索引");
	        Map<String, Object> map = new HashMap<String, Object>();
	        List<Integer> cooperationTypes = new ArrayList<Integer>();
	        cooperationTypes.add(5);
	        cooperationTypes.add(1);
	        cooperationTypes.add(2);
	        cooperationTypes.add(3);
	        cooperationTypes.add(4);
	        map.put("IN_cooperationType", cooperationTypes);
	        if(createOrUpate){
				map.put("DATEGTE_createtime", time);//新增时间
			}else{
				map.put("DATEGTE_updatetime", time);//更新时间
			}
			int i=1;
			int totalpages=0;
			boolean first=true;
			long lastid=0;
			long t=0;
			do{
				logger.info("索引处理,获取数据！每页"+pagesize);
				t=System.currentTimeMillis(); 
				Page<JobBase> pages=getJobPage(map, i, pagesize, "auto");
				for(JobBase job:pages){
					dealLucene(0,job);
				}
				logger.info("获取数据时间:"+ String.valueOf((t-System.currentTimeMillis())/1000));
				t=System.currentTimeMillis(); 
				if (first){
					totalpages=pages.getTotalPages();
					first=false;
					if(totalpages==0){
						break;
					}
				}
				//lastid=pages.getContent().get(pages.getContent().size()-1).getId();
				logger.info("索引处理,共"+totalpages+"页,正在处理第"+i+"页");
				//lastid=getJobBaseDoc(JobManager.reCreateIndex,pages.getContent(),indexWriter);
				logger.info("处理索引时间:"+ String.valueOf((t-System.currentTimeMillis())/1000));
				
				//em.clear();
				logger.info("索引处理,共"+totalpages+"页,处理完成第"+i+"页***");
				i++;
				pages=null;
				if (i%10==0){
					System.gc();
					logger.info("回收");
				}
			}while(totalpages>=i);
				logger.info("新建索引结束");
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			isrunning=false;
			lock.writeLock().unlock();
		}
	}
    
    /**
	 * 获取索引记录
	 * @param list
	 * @param indexWriter
	 * @throws IOException
	 */
	public  long getJobBaseDoc(boolean isReCreateIndex, List<JobBase> list,IndexWriter indexWriter) throws IOException{
		long lastid=0;
		for (JobBase jobBase : list) {
			Document doc=JobToDocument(jobBase);
			lastid=jobBase.getId();
			//增加时，先删除
			if (!isReCreateIndex){
				indexWriter.deleteDocuments(new Term("id",jobBase.getId()+""));
			}
			indexWriter.addDocument(doc); 
		}
		if (list.size()>0)
		{
			indexWriter.forceMerge(500);
			indexWriter.commit();
		}
		return lastid;
	}
	
	/**
   	 * 将岗位列表添加到索引文件
   	 * @param jobBase
   	 * @return
   	 */
   	private  Document JobToDocument(JobBase jobBase)
   	{
        Document doc=new Document(); 
		try {  
			doc.add(new Field("jobTitle", jobBase.getTitle()==null?"":jobBase.getTitle(), TextField.TYPE_STORED));
			doc.add(new Field("isPublish", jobBase.getJobConfig()==null?"":jobBase.getJobConfig().getIsPublish()+"", TextField.TYPE_STORED));
			doc.add(new Field("isUrgency", jobBase.getJobConfig()==null?"":jobBase.getJobConfig().getIsUrgency()+"", TextField.TYPE_STORED));
			doc.add(new NumericDocValuesField("isUrgency",(jobBase.getJobConfig() == null || jobBase.getJobConfig().getIsUrgency() ==null)?0:jobBase.getJobConfig().getIsUrgency()));
			doc.add(new Field("jobId", jobBase.getId()+"", TextField.TYPE_STORED));
			doc.add(new Field("jobname", jobBase.getJobname(), TextField.TYPE_STORED));
			doc.add(new Field("totalsalary", jobBase.getTotalsalary()+"", TextField.TYPE_STORED));
			doc.add(new Field("provinceid", jobBase.getProvinceid()+"", TextField.TYPE_STORED));
			doc.add(new Field("jobCityName", jobBase.getCity()==null?"":jobBase.getCity().getCityName(), TextField.TYPE_STORED));
			doc.add(new Field("cityId", jobBase.getCity()==null?"":jobBase.getCity().getId()+"", TextField.TYPE_STORED));
			doc.add(new Field("countyid", jobBase.getCountyid()+"", TextField.TYPE_STORED));
			doc.add(new Field("imgPath", jobBase.getThumbnialImage()==null?"":jobBase.getThumbnialImage().getImgpath()+"", TextField.TYPE_STORED));
			doc.add(new Field("applycount", jobBase.getApplycount()+"", TextField.TYPE_STORED));
			doc.add(new Field("jobType", jobBase.getJobType()==null?"":jobBase.getJobType(), TextField.TYPE_STORED));
			doc.add(new Field("jobLabel", jobBase.getJobLabel()==null?"":jobBase.getJobLabel(), TextField.TYPE_STORED));
			doc.add(new Field("companyName", jobBase.getCompany()==null?"":jobBase.getCompany().getName(), TextField.TYPE_STORED));
			doc.add(new Field("companyCityName", (jobBase.getCompany()==null||jobBase.getCompany().getCity()==null)?"":jobBase.getCompany().getCity().getCityName(), TextField.TYPE_STORED));
			doc.add(new Field("companyId", jobBase.getCompany()==null?"":jobBase.getCompany().getId()+"", TextField.TYPE_STORED));
			doc.add(new NumericDocValuesField("companyId",(jobBase.getCompany() == null || jobBase.getCompany().getId() ==null)?0:jobBase.getCompany().getId()));
			doc.add(new Field("companyProvinceId", jobBase.getCompany()==null?"":jobBase.getCompany().getProvinceid()+"", TextField.TYPE_STORED));
			doc.add(new Field("companyCountyid", jobBase.getCompany()==null?"":jobBase.getCompany().getCountyid()+"", TextField.TYPE_STORED));
			doc.add(new Field ("createtime", (jobBase.getCompany()==null||jobBase.getCompany().getCreatetime() == null)?"":jobBase.getCompany().getCreatetimeString(), TextField.TYPE_STORED));
			doc.add(new NumericDocValuesField("createtime",(jobBase.getCompany()==null||jobBase.getCompany().getCreatetime() == null)?0:jobBase.getCompany().getCreatetime().getTime()));
			doc.add(new Field("updatetime", (jobBase.getCompany()==null||jobBase.getCompany().getUpdatetime()==null)?"":jobBase.getCompany().getUpdatetimeString(), TextField.TYPE_STORED));
			doc.add(new NumericDocValuesField("updatetime",(jobBase.getCompany()==null||jobBase.getCompany().getUpdatetime() == null)?0:jobBase.getCompany().getUpdatetime().getTime()));
			doc.add(new Field("companyLogo", (jobBase.getCompany()==null || jobBase.getCompany().getLogo()==null||jobBase.getCompany().getLogo().getImgpath()==null)?"":jobBase.getCompany().getLogo().getImgpath(), TextField.TYPE_STORED));
			doc.add(new Field("companyAbbreviation", jobBase.getCompany()==null?"":jobBase.getCompany().getAbbreviation(), TextField.TYPE_STORED));
			doc.add(new Field("companyIndustryid", jobBase.getCompany()==null?"":jobBase.getCompany().getIndustryid()+"", TextField.TYPE_STORED));
			doc.add(new Field("companyStaffscale", jobBase.getCompany()==null?"":jobBase.getCompany().getStaffscale()+"", TextField.TYPE_STORED));
			doc.add(new Field("validation", (jobBase.getCompany() == null)?"":jobBase.getCompany().getValidation()+"", TextField.TYPE_STORED));
			doc.add(new Field("isLoan", (jobBase.getCompany() == null)?"":jobBase.getCompany().getIsLoan()+"", TextField.TYPE_STORED));
			doc.add(new Field("salaryFrom", jobBase.getJobDetail()==null?"":jobBase.getJobDetail().getSalaryfrom()+"", TextField.TYPE_STORED));
			doc.add(new IntField("salaryFromInt",(jobBase.getJobDetail() ==null || jobBase.getJobDetail().getSalaryfrom() == null)?0:jobBase.getJobDetail().getSalaryfrom(),Field.Store.YES));
			doc.add(new NumericDocValuesField("salaryFrom",(jobBase.getJobDetail() ==null || jobBase.getJobDetail().getSalaryfrom() == null)?0:jobBase.getJobDetail().getSalaryfrom()));
			doc.add(new Field("salaryTo", jobBase.getJobDetail()==null?"":jobBase.getJobDetail().getSalaryto()+"", TextField.TYPE_STORED));
			doc.add(new IntField("salaryToInt",(jobBase.getJobDetail() ==null || jobBase.getJobDetail().getSalaryto() == null)?0:jobBase.getJobDetail().getSalaryto(), Field.Store.YES));
			doc.add(new Field("ageFrom", jobBase.getJobDetail()==null?"":jobBase.getJobDetail().getAgefrom()+"", TextField.TYPE_STORED));
			doc.add(new Field("ageTo", jobBase.getJobDetail()==null?"":jobBase.getJobDetail().getAgeto()+"", TextField.TYPE_STORED));
			doc.add(new Field("education", jobBase.getJobDetail()==null?"":jobBase.getJobDetail().getEducation()+"", TextField.TYPE_STORED));
			doc.add(new Field("gender", jobBase.getJobDetail()==null?"":jobBase.getJobDetail().getGender()+"", TextField.TYPE_STORED));
			doc.add(new Field("cooperationType", jobBase.getCooperationType()==null?"":jobBase.getCooperationType()+"", TextField.TYPE_STORED));
		}catch(Exception e){
			logger.info(jobBase.toString());
			e.printStackTrace();
        	return null;
        }
        return doc;
   	}
   	private void dealLucene(int dotype,JobBase jobBase){
    	initLucene();
        IndexWriter indexWriter=null;  
         try {  
        	 indexWriter = indexManger.getWriter();
        	 if(!indexWriter.isOpen()){
        		 indexWriter = JobManager.getInstance(indexPath, true).getWriter();
        	 }
             Document doc=null;
             switch(dotype)
             {
             	case -1:indexWriter.deleteDocuments(new Term("jobId",jobBase.getId()+""));
             			break;
             	case  0:doc=JobToDocument(jobBase);
             			indexWriter.updateDocument(new Term("jobId", jobBase.getId()+""), doc);
             			break;
             	default:doc=JobToDocument(jobBase);
             			indexWriter.deleteDocuments(new Term("jobId",jobBase.getId()+""));
             			indexWriter.addDocument(doc);
             			break;
             }     
             indexManger.commitWriter();
         } catch (CorruptIndexException e) {  
             e.printStackTrace();  
         } catch (LockObtainFailedException e) {  
             e.printStackTrace();  
         } catch (IOException e) {  
             e.printStackTrace();  
         }  
    }
   	public void addLucene(Integer id)
	{
   		JobBase jobBase = jobBaseDao.findOne(id);
   		if(jobBase!=null){
   			dealLucene(1,jobBase);
   		}
	}
   	public void updateLucene(Integer id)  
    {
    	JobBase jobBase = jobBaseDao.findOne(id);
    	if(jobBase!=null){
    		dealLucene(0,jobBase);
    	}
	} 
  
   	public void deleteLucene(Integer id)  
	{ 
    	JobBase jobBase = jobBaseDao.findOne(id);
    	if(jobBase!=null){
    		dealLucene(-1,jobBase);
    	}
	}
   	public void addLuceneByCreateTime(String createTime) {
   		try {
			addIndexFile(true,createTime);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void addLuceneByUpateTime(String upateTime) {
		try {
			addIndexFile(false,upateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    public Page<JobBase> getJobPage(Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){
    	PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
    	Specification<JobBase> spec = buildSpecification(searchParams);
		return jobBaseDao.findAll(spec,pageRequest);
	}
	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<JobBase> buildSpecification(Map<String, Object> searchParams ,SearchFilter... orList) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<JobBase> spec = DynamicSpecifications.bySearchFilter(filters.values(),JobBase.class,false);
		return spec;
	}
	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		return new PageRequest(pageNumber - 1, pagzSize);
	}
	@Autowired
	public void setJobBaseDao(JobBaseDao jobBaseDao) {
		this.jobBaseDao = jobBaseDao;
	}
	
}