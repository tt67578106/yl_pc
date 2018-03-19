package com.ylw.taglib;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import net.sf.ehcache.Cache;

import org.apache.commons.lang3.StringUtils;

import com.ylw.entity.base.DataDictionary;
import com.ylw.entity.job.JobType;
import com.ylw.util.ChineseNameMaker;
import com.ylw.util.Constants;
import com.ylw.util.DataAreaUtil;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.StoreCacheUtil;

public class DictionaryCodeTag {
	private static List<DataDictionary> staffscaleList;
	private static List<DataDictionary> industryList;
	private static List<DataDictionary> industryTypeList;
	private static List<DataDictionary> educationList;
	private static List<DataDictionary> nationList;
	private static List<DataDictionary> employmentStatusList;
	private static List<DataDictionary> workYearList;
	private static List<DataDictionary> maritalStatusList;
	public static List<JobType> jobTypeList;
	
	
	public static String convertDic(Integer typeId, Integer code){
		return null;
	}
	public static String converDicEducation(Integer code){
		for (DataDictionary dataDictionary : getEducationList()) {
			if(dataDictionary.getCode().equals(""+code)&&!dataDictionary.getCode().equals("1")){
				return dataDictionary.getName();
			}
		}
		return "学历不限";
	}
	/**
	 * 根据工作类型得到默认缩略图 原图
	 * @param levelCode
	 * @return
	 */
	public static String getImageByJobTypeO(String jobType){
		String image = null;
		for(JobType type :getJobTypeList()){
			if(jobType.contains(type.getJobName())){
				image = type.getThumbnialImage();
			}
		}
	
		return Constants.IMAGE_FILE_URL+"o/"+image;
	}
	
	/**
	 * 小写字母转大写
	 * @param lowerStr
	 * @return
	 */
	public static String strToUpperCase(String lowerStr){
		if(StringUtils.isNotEmpty(lowerStr)){
			return lowerStr.toUpperCase();
		}
		return "";
	}
	
	/**
	 * 根据工作类型得到默认缩略图 小图
	 * @param levelCode
	 * @return
	 */
	public static String getImageByJobType320(String jobType){
		String image = null;
		for(JobType type :getJobTypeList()){
			if(jobType.contains(type.getJobName())){
				image = type.getThumbnialImage();
			}
		}
	
		return Constants.IMAGE_FILE_URL+"320/"+image;
	}
	
	/**
	 * 根据订单状态得到流程节点
	 * @param status
	 * @return
	 */
	public static String getJobApplyStatus(Integer status){
		if(status == 0){
			return "正在处理";
		}else if(status == 1){
			return "参加面试";
		}else if(status == 2){
			return "准备入职";
		}else if(status == 3){
			return "入职成功";
		}else if(status == 4){
			return "已关闭";
		}
		return "正在处理";
	}
	
	/**
	 * 根据工作类型得到默认缩略图 中图
	 * @param levelCode
	 * @return
	 */
	public static String getImageByJobType720(String jobType){
		String image = null;
		for(JobType type :getJobTypeList()){
			if(jobType.contains(type.getJobName())){
				image = type.getThumbnialImage();
			}
		}
	
		return Constants.IMAGE_FILE_URL+"720/"+image;
	}
	
	/**
	 * 获得求职状态
	 * 
	 * @return
	 */
	public static List<DataDictionary> getEmploymentStatusList() {
		if(employmentStatusList == null){
			Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
			employmentStatusList = (List<DataDictionary>) cache.get(Constants.CACHE_KEY_DIC_EMPLOYMENTSTATUS).getObjectValue();
		}
		return employmentStatusList;
	}
	/**
	 * 根据code获取求职状态name
	 * @param employmentStatus
	 * @return
	 */
	public static String getEmploymentStatusName(Long employmentStatus) {
		List<DataDictionary> list = getEmploymentStatusList();
		for (DataDictionary nat : list) {
			if (nat.getCode().equals(employmentStatus.toString())) {
				return nat.getName();
			}
		}
		return "";
	}
	/**
	 * 获得工作年限
	 * 
	 * @return
	 */
	public static List<DataDictionary> getWorkYearList() {
		if(workYearList == null){
			Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
			workYearList = (List<DataDictionary>) cache.get(Constants.CACHE_KEY_DIC_WORKYEAR).getObjectValue();
		}
		return workYearList;
	}
	
	/**
	 * 获得婚姻状况
	 * 
	 * @return
	 */
	public static List<DataDictionary> getMaritalStatus() {
		if(maritalStatusList == null){
			Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
			maritalStatusList = (List<DataDictionary>) cache.get(Constants.CACHE_KEY_DIC_MARITALSTATUS).getObjectValue();
		}
		return maritalStatusList;
	}
	/**
	 * 根据code获得婚姻状况name
	 * @param maritalStatus
	 * @return
	 */
	public static String getMaritalStatusName(Long maritalStatus) {
		List<DataDictionary> list = getMaritalStatus();
		for (DataDictionary nat : list) {
			if (nat.getCode().equals(maritalStatus.toString())) {
				return nat.getName();
			}
		}
		return "";
	}
	
	/**
	 * 根据工作类型得到默认缩略图 大图
	 * @param levelCode
	 * @return
	 */
	public static String getImageByJobType1080(String jobType){
		String image = null;
		for(JobType type :getJobTypeList()){
			if(jobType.contains(type.getJobName())){
				image = type.getThumbnialImage();
			}
		}
	
		return Constants.IMAGE_FILE_URL+"1080/"+image;
	}
	/**
	 * 根据等级code得到用户等级
	 * @param levelCode
	 * @return
	 */
	public static String getCertificationLevelByCode(Integer levelCode)
	{
		if (levelCode == null) {
			return "普通用户";
		} else if (levelCode == 1) {
			return "VIP用户";
		} else if (levelCode == 2) {
			return "钻石用户";
		}
		return "普通用户";
	}
	
	public static String getImagePathO(String path){
		if(StringUtils.isNotBlank(path)&&!path.contains("http://")){
			return Constants.IMAGE_FILE_URL+"o/"+path;
		}else{
			return path;
		}
	}
	
	public static String getImagePath320(String path){
		if(StringUtils.isNotBlank(path)&&!path.contains("http://")){
			return Constants.IMAGE_FILE_URL+"320/"+path;
		}else{
			return path;
		}
	}
	
	public static String getImagePath720(String path){
		if(StringUtils.isNotBlank(path)&&!path.contains("http://")){
			return Constants.IMAGE_FILE_URL+"720/"+path;
		}else{
			return path;
		}
	}
	
	public static String getImagePath1080(String path){
		if(StringUtils.isNotBlank(path)&&!path.contains("http://")){
			return Constants.IMAGE_FILE_URL+"1080/"+path;
		}else{
			return path;
		}
	}
	
	public static String getArticleTypeByCode(Integer typeCode){
		if(typeCode == null){
			return "文章列表";
		}else if(typeCode == 1){
			return "行业新闻";
		}else if(typeCode == 2){
			return "小蓝有约";
		}else if(typeCode == 3){
			return "求职帮助";
		}else if(typeCode == 4){
			return "优蓝访谈";
		}else if(typeCode == 5){
			return "优蓝现场";
		}else if(typeCode == 6){
			return "优蓝动态";
		}
		return "文章列表";
	}
	
	public static String getShortArticleTypeByCode(Integer typeCode){
		if(typeCode == null){
			return "文章";
		}else if(typeCode == 1){
			return "新闻";
		}else if(typeCode == 2){
			return "有约";
		}else if(typeCode == 3){
			return "帮助";
		}else if(typeCode == 4){
			return "访谈";
		}else if(typeCode == 5){
			return "现场";
		}else if(typeCode == 6){
			return "动态";
		}
		return "文章";
	}
	
	public static String converCountyName(String preText, Integer countyId){
		String countyName = DataAreaUtil.getDistrictById(countyId);
		if(StringUtils.isNotBlank(countyName)){
			return preText+countyName;
		}
		return "";
	}
	
	/**
	 * 根据id得到区名称
	 * @param preText
	 * @param countyId
	 * @return
	 */
	public static String converDistrictName(Integer countyId){
		String countyName = DataAreaUtil.getDistrictById(countyId);
		if(StringUtils.isNotBlank(countyName)){
			return countyName;
		}
		return "";
	}
	
	/**
	 * 根据省id得到省简称
	 * @param provinceId
	 * @return
	 */
	public static String convertProvinceAbbreviation(Integer provinceId){
		String provinceAbbreviation = DataAreaUtil.getProvinceAbbreviationById(provinceId);
		if(StringUtils.isNotBlank(provinceAbbreviation)){
			return provinceAbbreviation;
		}
		return "";
	}
	
	
	/**
	 * 根据省id得到市简称
	 * @param provinceId
	 * @return
	 */
	public static String convertCityAbbreviation(Integer cityId){
		String cityName = DataAreaUtil.getCityAbbreviationById(cityId);
		if(StringUtils.isNotBlank(cityName)){
			return cityName;
		}
		return "";
	}
	
	/**
	 * 根据省id得到省全称
	 * @param provinceId
	 * @return
	 */
	public static String convertProvinceName(Integer provinceId){
		String provinceAbbreviation = DataAreaUtil.getProvinceNameById(provinceId);
		if(StringUtils.isNotBlank(provinceAbbreviation)){
			return provinceAbbreviation;
		}
		return "";
	}
	
	public static String convertLabels(Integer labid){
		return DataAreaUtil.getLabelsNameById(labid);
	}
	
	/**
	 * 转换企业规模
	 * @param code
	 * @return
	 */
	public static String convertDicStaffscale(Integer code){
		for (DataDictionary dataDictionary : getStaffscaleList()) {
			if(dataDictionary.getCode().equals(""+code)){
				return dataDictionary.getName();
			}
		}
		return null;
	}
	/**
	 * 随机产生姓名
	 * @return
	 */
	public static String getName()
	{
		return ChineseNameMaker.generateName();
		//return ChineseName.getName();
	}
	/**
	 * 得到当前时间  时分
	 * @return
	 */
	public static String getTime(Integer arg)
	{
		arg=-(15-arg);
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.MINUTE, arg);
		Date now = calendar.getTime();
		return DateConvertUtils.format(now, "HH:mm");
	}
	/**
	 * 转换求职意向
	 * @param code
	 * @return
	 */
	public static String convertDicIndustry(Integer code){
		for (DataDictionary dataDictionary : getIndustryList()) {
			if(dataDictionary.getCode().equals(code + "")){
				return dataDictionary.getName();
			}
		}
		return null;
	}
	/**
	 * 转换行业
	 * @param code
	 * @return
	 */
	public static String convertDicIndustryType(Integer code){
		for (DataDictionary dataDictionary : getIndustryTypeList()) {
			if(dataDictionary.getCode().equals(code + "")){
				return dataDictionary.getName();
			}
		}
		return null;
	}
	/**
	 * 转换民族 
	 * @param code
	 * @return
	 */
	public static String converDicNation(Integer code){
		for (DataDictionary dataDictionary : getNationList()) {
			if(dataDictionary.getCode().equals(code + "")){
				return dataDictionary.getName();
			}
		}
		return null;
	}
	
	private static List<DataDictionary> getNationList() {
		if(nationList == null){
			Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
			nationList = (List<DataDictionary>) cache.get(Constants.CACHE_KEY_DIC_NATION).getObjectValue();
		}
		return nationList;
	}
	/**
	 * 
	 * @return
	 */
	private static List<DataDictionary> getStaffscaleList() {
		if(staffscaleList == null){
			Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
			staffscaleList = (List<DataDictionary>) cache.get(Constants.CACHE_KEY_DIC_STAFFSCALE).getObjectValue();
		}
		return staffscaleList;
	}
	
	private static List<DataDictionary> getIndustryList() {
		if(industryList == null){
			Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
			industryList = (List<DataDictionary>) cache.get(Constants.CACHE_KEY_DIC_INDUSTRY).getObjectValue();
		}
		return industryList;
	}
	private static List<DataDictionary> getIndustryTypeList() {
		if(industryTypeList == null){
			Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
			industryTypeList = (List<DataDictionary>) cache.get(Constants.CACHE_KEY_DIC_INDUSTRY_TYPE).getObjectValue();
		}
		return industryTypeList;
	}
	private static List<DataDictionary> getEducationList(){
		if(educationList == null){
			Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
			educationList = (List<DataDictionary>) cache.get(Constants.CACHE_KEY_EDUCATION).getObjectValue();
		}
		return educationList;
	}
	private static List<JobType> getJobTypeList(){
		if(jobTypeList == null){
			Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
			jobTypeList = (List<JobType>) cache.get(Constants.CACHE_KEY_JOBTYPE).getObjectValue();
		}
		return jobTypeList;
	}
}
