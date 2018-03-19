package com.ylw.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ylw.entity.base.Branch;
import com.ylw.service.base.BranchService;
import com.ylw.service.base.DataDictionaryService;
import com.ylw.service.code.CodeAreaCityService;
import com.ylw.service.code.CodeAreaDistrictService;
import com.ylw.service.code.CodeAreaProvinceService;
import com.ylw.service.job.JobTypeService;
import com.ylw.service.job.LabelsService;

/**
 * auto run
 * @author Flame
 *
 */
public class AutoRunListener implements ServletContextListener{
	private static Logger logger = LoggerFactory.getLogger(AutoRunListener.class);
	private DataDictionaryService dataDictionaryService;
	private CodeAreaProvinceService provinceService;
	private CodeAreaCityService cityService;
	private CodeAreaDistrictService districtService;
	private LabelsService labelsService;
	private BranchService branchService;
	private JobTypeService jobTypeService;
	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("自启动开始执行：初始化缓存-加载操作对象-加载缓存-加载任务");
		new StoreCacheUtil().init();
		MemcachedUtil.initmemcachedClient();
		manualwired(event);
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		StoreCacheUtil.destroy();
		dataDictionaryService = null;
		logger.info("自启动产生的对象已经销毁");
	}
	/**
	 * 手动初始化缓存
	 * @param event
	 */
	private void manualwired(ServletContextEvent event){
		ServletContext servletContext = event.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		dataDictionaryService = wac.getBean(DataDictionaryService.class);
		provinceService = wac.getBean(CodeAreaProvinceService.class);
		cityService = wac.getBean(CodeAreaCityService.class);
		districtService = wac.getBean(CodeAreaDistrictService.class);
		labelsService = wac.getBean(LabelsService.class);
		branchService = wac.getBean(BranchService.class);
		jobTypeService = wac.getBean(JobTypeService.class);
		try {
			List<Branch> branchList = branchService.findAllBranch();
			StringBuffer sb = new StringBuffer();
			for (Branch branch : branchList) {
				sb.append(Constants.CACHE_KEY_RECOMMEND_JOB+branch.getId()).append(",")
				.append(Constants.CACHE_KEY_HOME_HOT_JOB).append(branch.getId()).append(",")
				.append(Constants.CACHE_KEY_RECOMMEND_COMPANY).append(branch.getId()).append(",")
				.append(Constants.CACHE_KEY_RECOMMEND_JOB_YLJX).append(branch.getId()).append(",")
				.append(Constants.CACHE_KEY_JOB_YLJX_BOTTOM).append(branch.getId()).append(",")
				.append(Constants.CACHE_KEY_JOB_DYYH).append(branch.getId()).append(",")
				.append(Constants.CACHE_KEY_JOB_DYYH_BOTTOM).append(branch.getId()).append(",")
				.append(Constants.CACHE_KEY_JOB_HOT_BOTTOM).append(branch.getId()).append(",")
				.append(Constants.CACHE_KEY_JOB_NEW_BOTTOM).append(branch.getId()).append(",");
			}
			String[] cleanKeys = {Constants.MEMCACHED_KEY_SHORT_RECOMMEND_JOBS,Constants.CACHE_KEY_APPLY_COUNT,Constants.CACHE_KEY_ARTICLE_RIGHT_AD,
					Constants.CACHE_KEY_CENTER_INDEX_ARTICLE,Constants.CACHE_KEY_CITY,Constants.CACHE_KEY_COMPANY_JOB,Constants.CACHE_KEY_DIC,
					Constants.CACHE_KEY_DIC_INDUSTRY,Constants.CACHE_KEY_DIC_NATION,Constants.CACHE_KEY_DIC_SALARY,Constants.CACHE_KEY_DIC_STAFFSCALE,
					Constants.CACHE_KEY_DISTRICT,Constants.CACHE_KEY_EDUCATION,Constants.CACHE_KEY_HOME_ARTICLE_ACTIVITIES,
					Constants.CACHE_KEY_HOME_ARTICLE_EMPLOYEE,Constants.CACHE_KEY_HOME_ARTICLE_HELPS,Constants.CACHE_KEY_HOME_EVERYONE_SEARCHING,
					Constants.CACHE_KEY_HOME_HOT_JOB,Constants.CACHE_KEY_HOME_HOT_LABELS,Constants.CACHE_KEY_HOME_QUESTION,Constants.CACHE_KEY_HOME_SEX_ARTICLE,
					Constants.CACHE_KEY_HOME_SHUFFLING_JOBS,Constants.CACHE_KEY_HOME_SHUFFLING_JOBS,Constants.CACHE_KEY_LEFT_INDEX_ARTICLE,
					Constants.CACHE_KEY_PROVINCE,Constants.CACHE_KEY_RECOMMEND_JOB,Constants.CACHE_KEY_RIGHT_INDEX_AD,Constants.CACHE_KEY_HOME_CENTER_JOBS,
					Constants.CACHE_KEY_RECOMMEND_COMPANY,Constants.CACHE_KEY_RECOMMEND_JOB_YLJX,Constants.CACHE_KEY_JOB_YLJX_BOTTOM,
					Constants.CACHE_KEY_JOB_DYYH,Constants.CACHE_KEY_JOB_DYYH_BOTTOM,Constants.CACHE_KEY_JOB_HOT_BOTTOM,
					Constants.CACHE_KEY_JOB_NEW_BOTTOM
					};
			for (String key : cleanKeys) {
				MemcachedUtil.deleteCache(key);
			}
			String[] branchKeys = sb.toString().split(",");
			for (String key : branchKeys) {
				MemcachedUtil.deleteCache(key);
			}
			logger.info("PC端使用memcache缓存对应的Key被清空");
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataDictionaryService.initCache();
		dataDictionaryService.initDicStaffscale();
		dataDictionaryService.initDicIndustry();
		dataDictionaryService.initDicIndustryType();
		dataDictionaryService.initDicSalary();
		dataDictionaryService.initDicEducation();
		dataDictionaryService.initNation();
		dataDictionaryService.initEmploymentStatus();
		dataDictionaryService.initWorkYear();
		dataDictionaryService.initMaritalStatus();
		provinceService.initAllProvinceCache();
		cityService.initCityCache();
		districtService.initDistrictCache();
		labelsService.initLabels();
		jobTypeService.initCache();
	}
}
