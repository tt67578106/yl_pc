package com.ylw.service.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ylw.entity.code.CodeAreaCity;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ylw.entity.base.Image;
import com.ylw.entity.base.Resume;
import com.ylw.entity.base.User;
import com.ylw.repository.DataDictionaryDao;
import com.ylw.repository.UserDao;
import com.ylw.taglib.DictionaryCodeTag;
import com.ylw.util.CheckIdCardUtils;
import com.ylw.util.Constants;

@Component
@Transactional
public class ResumeMdbService {
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private DataDictionaryService dataDictionaryService;
	
	private DataDictionaryDao dataDictionaryDao;
	
	@Autowired
	public void setDataDictionaryDao(DataDictionaryDao dataDictionaryDao) {
		this.dataDictionaryDao = dataDictionaryDao;
	}
	private UserDao userDao;
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	private static Logger logger = LoggerFactory.getLogger(ResumeMdbService.class);
	//根据优蓝网用户id和手机号查询简历
	private static final String FIND_RESUMEB_BY_WEBURL = Constants.MDB_URL+"findResumeByWeb";
	//修改简历基本信息
	private static final String MODIFY_RESUME_BASEBY_WEB = Constants.MDB_URL+"modifyResumeBaseByWeb";
	//修改简历求职意向[post]
	private static final String MODIFY_RESUME_INTENTION_BYWEB = Constants.MDB_URL+"modifyResumeIntentionByWeb";
	//修改简历头像
	private static final String MODIFY_RESUME_AVATAR_BYWEB =Constants.MDB_URL+"modifyResumeAvatarByWeb";
	//修改简历教育经验
	private static final String SAVE_EDU_EXP_BY_WEB_URL = Constants.MDB_URL+"exp/saveEduExpByWeb";
	
	private String getAppkey() {
		try {
			// 读取 配置文件
			final InputStream inputSteam = this.getClass().getClassLoader()
					.getResourceAsStream("application.properties");
			// 读取 appkey
			Properties properties = new Properties();
			properties.load(inputSteam);
			String appkey_resume = properties.getProperty("appkey_resume");
			inputSteam.close();
			return appkey_resume;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}
	

	/**
	 * 保存简历(同步web)
	 * @param userId
	 * @param resume
	 */
	public void saveProfileWeb(Integer userId, Resume resume) {
		if(userId != null){
			String strURL = FIND_RESUMEB_BY_WEBURL + "?appkey={appkey}&yl_user_id={yl_user_id}&mobile={mobile}";
			RestTemplate restTemplate = new RestTemplate();
			Map<String, String> rultiValueMap = new HashMap<String, String>();
			rultiValueMap.put("appkey", this.getAppkey());
			rultiValueMap.put("yl_user_id", String.valueOf(userId));
			User user = userDao.findOne(userId);
			rultiValueMap.put("mobile", user.getLoginname());
			/*rultiValueMap.put("is_syn_hr", String.valueOf(3));*/
			JSONObject json = restTemplate.getForObject(strURL, JSONObject.class, rultiValueMap);
//			System.out.println(json);
			Object status = json.get("status");
			Object data = json.get("data");
			if(status.equals(Constants.RETURN_STATUS_SUCCESS)){
				//修改简历基本信息[post]
				String URL = MODIFY_RESUME_BASEBY_WEB;
					RestTemplate restTemplates = new RestTemplate();
					MultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<String, String>();
					linkedMultiValueMap.add("appkey", this.getAppkey());
					linkedMultiValueMap.add("yl_user_id", String.valueOf(userId));	
					  
						linkedMultiValueMap.add("mobile",user.getLoginname());
//						linkedMultiValueMap.add("mobile", resume.getMobile());
					linkedMultiValueMap.add("resume_info_id",((JSONObject) data).getString("id"));
					if(!StringUtils.isBlank(resume.getName())){
						linkedMultiValueMap.add("full_name",resume.getName());
					}
//					if (resume.getEducation() != null) {
//						linkedMultiValueMap.add("education_title",String.valueOf(resume.getEducation()));
//					}
//					if (resume.getGender() != null) {
//						linkedMultiValueMap.add("gender",String.valueOf(resume.getGender()));
//					}
					
					//求职状态
					if(resume.getEmploymentStatus()!=null&&StringUtils.isNotBlank(resume.getEmploymentStatus().toString())){
						linkedMultiValueMap.add("employment_status", String.valueOf(resume.getEmploymentStatus()));
					}
					//工作年限
					if(resume.getStartWorkYear()!=null&&StringUtils.isNotBlank(resume.getStartWorkYear().toString())){
						linkedMultiValueMap.add("work_years", String.valueOf(resume.getStartWorkYear()));
					}
					//婚姻状况
					if(resume.getMaritalStatus()!=null&&StringUtils.isNotBlank(resume.getMaritalStatus().toString())){
						linkedMultiValueMap.add("marital_status", String.valueOf(resume.getMaritalStatus()));
					}
					//户籍地址
					if (resume.getNativeProvinceid() != null && StringUtils.isNotBlank(resume.getNativeProvinceid().toString())) {
						linkedMultiValueMap.add("reg_province_id", String.valueOf(resume.getNativeProvinceid()));
					}
					if(StringUtils.isNotBlank(resume.getNativeProvinceName())){
						linkedMultiValueMap.add("reg_province_name", String.valueOf(resume.getNativeProvinceName()));
					}
					if (resume.getNativeCityid() != null && StringUtils.isNotBlank(resume.getNativeCityid().toString())) {
						linkedMultiValueMap.add("reg_city_id", String.valueOf(resume.getNativeCityid()));
					}
					if(StringUtils.isNotBlank(resume.getNativeCityName())){
						linkedMultiValueMap.add("reg_city_name", String.valueOf(resume.getNativeCityName()));
					}
					//现居地址
					if (resume.getResidentProvinceid() != null && StringUtils.isNotBlank(resume.getResidentProvinceid().toString())) {
						linkedMultiValueMap.add("place_province_id", String.valueOf(resume.getResidentProvinceid()));
					}
					if(StringUtils.isNotBlank(resume.getResidentProvinceName())){
						linkedMultiValueMap.add("place_province_name", String.valueOf(resume.getResidentProvinceName()));
					}
					
					if (resume.getResidentCityid() != null && StringUtils.isNotBlank(resume.getResidentCityid().toString())) {
						linkedMultiValueMap.add("place_city_id", String.valueOf(resume.getResidentCityid()));
					}
					if(StringUtils.isNotBlank(resume.getResidentCityName())){
						linkedMultiValueMap.add("place_city_name", String.valueOf(resume.getResidentCityName()));
					}
					
					if (!StringUtils.isBlank(resume.getResumeCode())) {
						linkedMultiValueMap.add("resume_code",resume.getResumeCode());
					}
					if(!StringUtils.isBlank(resume.getQq())&& resume.getQq().matches("[1-9][0-9]{4,14}")){
						linkedMultiValueMap.add("qq",resume.getQq());
					}
					if(!StringUtils.isBlank(resume.getIdCard()) && CheckIdCardUtils.validateCard(resume.getIdCard())){
						linkedMultiValueMap.add("id_card",resume.getIdCard());
						//判断身份证是否正确
						/*if(CheckIdCardUtils.validateCard(resume.getIdCard())){
							//年月日
							linkedMultiValueMap.add("birth_year",String.valueOf(CheckIdCardUtils.getYearByIdCard(resume.getIdCard())));
							linkedMultiValueMap.add("birth_month",String.valueOf(CheckIdCardUtils.getMonthByIdCard(resume.getIdCard())));
							linkedMultiValueMap.add("birth_day",String.valueOf(CheckIdCardUtils.getDateByIdCard(resume.getIdCard())));
						  }*/
					   }
					if(resume.getNation() != null){
						String findNation = dataDictionaryDao.findNationByNameAndTypeid(String.valueOf(resume.getNation()));
						linkedMultiValueMap.add("nation",findNation);
					}
					JSONObject json1 = restTemplates.postForObject(URL, linkedMultiValueMap, JSONObject.class);
//					System.out.println(json1);
					
					boolean flag = false;
					//修改简历求职意向[post]
					String intentionURL=MODIFY_RESUME_INTENTION_BYWEB;
					RestTemplate restTemplate1 = new RestTemplate();
					MultiValueMap<String, String> linkedMap = new LinkedMultiValueMap<String, String>();
					linkedMap.add("appkey", this.getAppkey());
					linkedMap.add("resume_info_id",((JSONObject) data).getString("id"));
					linkedMap.add("yl_user_id", String.valueOf(userId));
					/*Integer integer =resume.getJobTargetProvinceid();*/
					
					if (resume.getJobTargetProvinceid() != null
							&& StringUtils.isNotBlank(resume.getJobTargetProvinceid().toString())) {
						flag = true;
						linkedMap.add("province_id", resume.getJobTargetProvinceid().toString());
					}
					if(StringUtils.isNotBlank(resume.getJobTargetProvinceName())){
						flag = true;
						linkedMap.add("province_name", resume.getJobTargetProvinceName());
					}
					
					if (resume.getJobTargetCityid() != null
							&& StringUtils.isNotBlank(resume.getJobTargetCityid().toString())) {
						flag = true;
						linkedMap.add("city_id", resume.getJobTargetCityid().toString());
					}
					
					if(StringUtils.isNotBlank(resume.getJobTargetCityName())){
						flag = true;
						linkedMap.add("city_name", resume.getJobTargetCityName());
					}

//					if(resume.getJobTargetProvinceid() != null){
//						linkedMap.add("province_id", String.valueOf(resume.getJobTargetProvinceid()));
//						linkedMap.add("city_id","-1");
//						flag = true;
//					}
//					/*Integer i=resume.getJobTargetCountyid();*/
					if( resume.getJobTargetCountyid()!= null ){
						linkedMap.add("area_id", String.valueOf(resume.getJobTargetCountyid()));
						flag = true;
					}
					if(resume.getIntentionIndustry() != null){
						linkedMap.add("industry_id", String.valueOf(resume.getIntentionIndustry()));
						flag = true;
					}
					//职位
					if(resume.getIntentionPosition() != null){
						String findNation = dataDictionaryDao.findIntentionPositionByNameAndTypeid(String.valueOf(resume.getIntentionPosition()));
						linkedMap.add("position_id",findNation);
						flag = true;
					}
					
					if (resume.getIntentionSalary() != null && StringUtils.isNotBlank(resume.getIntentionSalary())) {
						String salary =  resume.getIntentionSalary();
						//判断里面是否包括这个字符串
						boolean statu = salary.contains("元/月"); 
						if(statu){  
//							System.out.println("包含"); 
							//删除字符串里面的 元/月
							String b=salary.replace("元/月","");
							
							if(b.equals("小于2000")){
								linkedMap.add("salary_from", "0");
								linkedMap.add("salary_to", "2000");
								flag = true;
							}else if(b.equals("大于8000")){
								linkedMap.add("salary_from", "8000");
								linkedMap.add("salary_to", "0");
								flag = true;
							}else{
								final String[] salaryRegions = b.split("-");
								if (salaryRegions.length > 0) {
									linkedMap.add("salary_from", salaryRegions[0]);
								}
								if (salaryRegions.length > 1) {
									linkedMap.add("salary_to", salaryRegions[1]);
								}
								flag = true;
							}  
							
							
						}else{  
							if(salary.equals("不限")){
								linkedMap.add("salary_from", "0");
								linkedMap.add("salary_to", "0");
								flag = true;
							}else{
							
								final String[] salaryRegions = resume.getIntentionSalary().split("-");
								if (salaryRegions.length > 0) {
									linkedMap.add("salary_from", salaryRegions[0]);
								}
								if (salaryRegions.length > 1) {
									linkedMap.add("salary_to", salaryRegions[1]);
								}
								flag = true;
							}
						}  
						
						
					}
					if(!StringUtils.isBlank(resume.getJobTarget())){
						linkedMap.add("job_target", resume.getJobTarget());
						flag = true;
					}
					if(flag == true){
						JSONObject json2 = restTemplate1.postForObject(intentionURL, linkedMap, JSONObject.class);
						/*System.out.println(json2);*/
					}
				
					/*modifyJobIntension(resume, data, linkedMultiValueMap);*/
					//修改简历头像
					String AvatarURL=MODIFY_RESUME_AVATAR_BYWEB;
					RestTemplate templatet = new RestTemplate();
					MultiValueMap<String, String>  multiValueMap= new LinkedMultiValueMap<String,String>();
					multiValueMap.add("appkey", this.getAppkey());
					multiValueMap.add("yl_user_id", String.valueOf(userId));
					multiValueMap.add("resume_info_id",((JSONObject) data).getString("id"));
					//判断有简历头像就修改
					if(resume.getImage() != null){
						Image head=imageService.getImage(resume.getImage().getId());
						multiValueMap.add("avatar", head.getImgpath());
						JSONObject temp = templatet.postForObject(AvatarURL,multiValueMap,JSONObject.class);
					}
					//修改教育经历
					String EducationURL=SAVE_EDU_EXP_BY_WEB_URL;
					RestTemplate EducationRestTemplate = new RestTemplate();
					MultiValueMap<String, String>  educationMap= new LinkedMultiValueMap<String,String>();
					educationMap.add("appkey", this.getAppkey());
					educationMap.add("yl_user_id", String.valueOf(userId));
					educationMap.add("resume_id",((JSONObject) data).getString("id"));
					//判断有学历就修改
					if(resume.getEducation() != null){
						String findNation = dataDictionaryDao.findEducationByNameAndTypeid(String.valueOf(resume.getEducation()));
						educationMap.add("degree_name",findNation);
						JSONObject teate = EducationRestTemplate.postForObject(EducationURL,educationMap,JSONObject.class);
						}
					}
		}
		
	}

	/**
	 * 根据用户 userId、mobile 查询对应的简历信息
	 * 
	 * @param userid
	 *            用户Id
	 * @param mobile
	 *            手机号码
	 * @param is_syn_hr
	 *            是否同步
	 * @return
	 */
	public JSONObject getResumeMdbByUserIdAndMobile(final String userid, final String mobile, final String is_syn_hr,final String name) {
		String strURL = FIND_RESUMEB_BY_WEBURL + "?appkey={appkey}&yl_user_id={yl_user_id}&mobile={mobile}";

		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> rultiValueMap = new HashMap<String, String>();
		rultiValueMap.put("appkey", this.getAppkey());
		rultiValueMap.put("yl_user_id", String.valueOf(userid));
		rultiValueMap.put("mobile", mobile);
		if(StringUtils.isNotBlank(name)){
			strURL = strURL + "&full_name={full_name}";
			rultiValueMap.put("full_name", StringUtils.stripToEmpty(name));
		}
	/*	rultiValueMap.put("is_syn_hr", String.valueOf(3));*/

		return restTemplate.getForObject(strURL, JSONObject.class, rultiValueMap);
	}

	/**
	 * @description:	userId、mobile 查询对应的简历信息(新增意愿城市id相关字段，用于EHR进行简历分区)
	 * @method: getResumeMdbByUserIdAndMobile
	 * @author: Mark
	 * @date: 11:32 2018/3/2
	 * @param userid	用户Id
	 * @param mobile	手机号码
	 * @param is_syn_hr	是否同步
	 * @param name		全称
	 * @param city		意愿城市信息
	 * @return: net.sf.json.JSONObject
	 */
	public JSONObject getResumeMdbByUserIdAndMobile(final String userid, final String mobile, final String is_syn_hr, final String name, CodeAreaCity city) {
		String strURL = FIND_RESUMEB_BY_WEBURL + "?appkey={appkey}&yl_user_id={yl_user_id}&mobile={mobile}";

		RestTemplate restTemplate = null;
		Map<String, String> rultiValueMap = null;
		try {
			restTemplate = new RestTemplate();
			rultiValueMap = new HashMap<String, String>();
			rultiValueMap.put("appkey", this.getAppkey());
			rultiValueMap.put("yl_user_id", String.valueOf(userid));
			rultiValueMap.put("mobile", mobile);
			if(StringUtils.isNotBlank(name)){
                strURL = strURL + "&full_name={full_name}";
                rultiValueMap.put("full_name", StringUtils.stripToEmpty(name));
            }
            if(city != null){
				strURL += "&intention_city={intention_city}";
				rultiValueMap.put("intention_city", JSONObject.fromObject(city).toString());
			}
			/*	rultiValueMap.put("is_syn_hr", String.valueOf(3));*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return restTemplate.getForObject(strURL, JSONObject.class, rultiValueMap);
	}

	/**
	 * 修改简历信息
	 * 
	 * @param userid
	 *            用户ID
	 * @param mobile
	 *            手机号码
	 * @param resume_info_id
	 *            简历ID
	 * @param resumeBaseMap
	 *            修改参数
	 * @return
	 */
	public JSONObject modifyResumeBaseMdb(final String userid, final String mobile, final String resume_info_id,
			final Map<String, String> resumeBaseMap) {
		String strURL = MODIFY_RESUME_BASEBY_WEB;

		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> rultiValueMap = new LinkedMultiValueMap<String, String>();
		rultiValueMap.add("appkey", this.getAppkey());
		rultiValueMap.add("yl_user_id", String.valueOf(userid));
		rultiValueMap.add("mobile", mobile);
		rultiValueMap.add("resume_info_id", resume_info_id);

		// 更新字段
		if (!StringUtils.isBlank(resumeBaseMap.get("full_name"))) {
			rultiValueMap.add("full_name", resumeBaseMap.get("full_name"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("education_title"))) {
			rultiValueMap.add("education_title", resumeBaseMap.get("education_title"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("gender"))) {
			rultiValueMap.add("gender", resumeBaseMap.get("gender"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("resume_code"))) {
			rultiValueMap.add("resume_code", resumeBaseMap.get("resume_code"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("employment_status"))) {
			rultiValueMap.add("employment_status", resumeBaseMap.get("employment_status"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("birth_year"))) {
			rultiValueMap.add("birth_year", resumeBaseMap.get("birth_year"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("birth_month"))) {
			rultiValueMap.add("birth_month", resumeBaseMap.get("birth_month"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("birth_day"))) {
			rultiValueMap.add("birth_day", resumeBaseMap.get("birth_day"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("email"))) {
			rultiValueMap.add("email", resumeBaseMap.get("email"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("qq"))) {
			rultiValueMap.add("qq", resumeBaseMap.get("qq"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("id_card"))) {
			rultiValueMap.add("id_card", resumeBaseMap.get("id_card"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("nation"))) {
			rultiValueMap.add("nation", resumeBaseMap.get("nation"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("reg_province_id"))) {
			rultiValueMap.add("reg_province_id", resumeBaseMap.get("reg_province_id"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("reg_city_id"))) {
			rultiValueMap.add("reg_city_id", resumeBaseMap.get("reg_city_id"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("place_province_id"))) {
			rultiValueMap.add("place_province_id", resumeBaseMap.get("place_province_id"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("place_city_id"))) {
			rultiValueMap.add("place_city_id", resumeBaseMap.get("place_city_id"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("place_area_id"))) {
			rultiValueMap.add("place_area_id", resumeBaseMap.get("place_area_id"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("avatar"))) {
			rultiValueMap.add("avatar", resumeBaseMap.get("avatar"));
		}
		if (!StringUtils.isBlank(resumeBaseMap.get("intention_city"))){
			rultiValueMap.add("intention_city",resumeBaseMap.get("intention_city"));
		}

		return restTemplate.postForObject(strURL, rultiValueMap, JSONObject.class);
	}

	/**
	 * 保存简历赋值
	 * @param resultMap
	 * @return
	 */
	public Resume findResume(JSONObject resultMap) {
		if(resultMap.get("status").equals(Constants.RETURN_STATUS_SUCCESS)){
			// 获取data
			JSONObject dataObject = resultMap.getJSONObject("data");
			// 用户出生地所在信息
			JSONObject registeredPlaceObject = dataObject.getJSONObject("registeredPlace");
			// 现居住地所在信息
			net.sf.json.JSONObject presentPlace = dataObject.getJSONObject("presentPlace");
			// 求职意向信息
			JSONObject intention = dataObject.getJSONObject("intention");
			// 获取data里面 resume_info_id
			final String resume_info_id = (String) dataObject.get("id");
			/*** 设置基本信息 start ***/
			Resume resume = new Resume();
			if(getValue(dataObject, "avatar") != null ){
				Image image = new Image();
				image.setImgpath(getValue(dataObject, "avatar").toString());
				resume.setImage(image);
			}
			
			resume.setName(
					getValue(dataObject, "fullName") == null ? null : getValue(dataObject, "fullName").toString());
			//身份证
			resume.setIdCard(
					getValue(dataObject, "idCard") == null ? null : getValue(dataObject, "idCard").toString());
			//QQ
			resume.setQq(getValue(dataObject, "qq") == null ? null : getValue(dataObject, "qq").toString());
			//学历
			Object lastEducationTitle = getValue(dataObject, "lastEducationTitle");
			if(!isEmpty(lastEducationTitle)){
				if(lastEducationTitle.equals("硕士") || lastEducationTitle.equals("博士")){
					lastEducationTitle="本科及以上";
				}
				if(lastEducationTitle.equals("不限")){
					lastEducationTitle="中专/技校";
				}
				if(isNumericzidai(lastEducationTitle.toString())){
					resume.setEducation(new Integer(getValue(dataObject, "lastEducationTitle").toString()));
				}else{
					String educationCode = dataDictionaryService.findCodeByNameAndTypeid(lastEducationTitle.toString());
					if(StringUtils.isNotEmpty(educationCode)&&isNumericzidai(educationCode)){
						resume.setEducation(new Integer(educationCode));
					}
				}
			}
			
			//求职状态
			resume.setEmploymentStatus(getValue(dataObject, "employmentStatus") == null||StringUtils.isBlank(getValue(dataObject, "employmentStatus").toString()) ? null
					: new Integer(getValue(dataObject, "employmentStatus").toString()));
			//婚姻状况
			resume.setMaritalStatus(getValue(dataObject, "maritalStatus") == null||StringUtils.isBlank(getValue(dataObject, "maritalStatus").toString()) ? null
					: new Integer(getValue(dataObject, "maritalStatus").toString()));
			//工作年限
			resume.setStartWorkYear(getValue(dataObject, "startWorkingYear") == null ? null
					: getValue(dataObject, "startWorkingYear").toString());
		
			//籍贯
			if (registeredPlaceObject != null && !registeredPlaceObject.isNullObject()){
				String nativeProvinceidStr = getValue(registeredPlaceObject, "provinceId")==null?null:getValue(registeredPlaceObject, "provinceId").toString();
				String nativeCityidStr = getValue(registeredPlaceObject, "cityId")==null?null:getValue(registeredPlaceObject, "cityId").toString();
				resume.setNativeProvinceid(StringUtils.isNotBlank(nativeProvinceidStr)&&NumberUtils.isDigits(nativeProvinceidStr)?Integer.parseInt(nativeProvinceidStr):null);
				resume.setNativeCityid(StringUtils.isNotBlank(nativeCityidStr)&&NumberUtils.isDigits(nativeCityidStr)?Integer.parseInt(nativeCityidStr):null);
				if(getValue(registeredPlaceObject, "provinceName")!=null&&StringUtils.isNotBlank(getValue(registeredPlaceObject, "provinceName").toString())){
					resume.setNativeProvinceName(getValue(registeredPlaceObject, "provinceName").toString());
				}else{
					resume.setNativeProvinceName(StringUtils.isNotBlank(nativeProvinceidStr)&&NumberUtils.isDigits(nativeProvinceidStr)?DictionaryCodeTag.convertProvinceAbbreviation(Integer.parseInt(nativeProvinceidStr)):null);
				}
				if(getValue(registeredPlaceObject, "cityName")!=null&&StringUtils.isNotBlank(getValue(registeredPlaceObject, "cityName").toString())){
					resume.setNativeCityName(getValue(registeredPlaceObject, "cityName").toString());
				}else{
					resume.setNativeCityName(StringUtils.isNotBlank(nativeCityidStr)&&NumberUtils.isDigits(nativeCityidStr)?DictionaryCodeTag.convertCityAbbreviation(Integer.parseInt(nativeCityidStr)):null);
				}
			}
			
			//现居地址
			if (presentPlace != null && !presentPlace.isNullObject()){
				String residentProvinceidStr = getValue(presentPlace, "provinceId")==null?null:getValue(presentPlace, "provinceId").toString();
				String residentCityidStr = getValue(presentPlace, "cityId")==null?null:getValue(presentPlace, "cityId").toString();
				resume.setResidentProvinceid(StringUtils.isNotBlank(residentProvinceidStr)&&NumberUtils.isDigits(residentProvinceidStr)?Integer.parseInt(residentProvinceidStr):null);
				resume.setResidentCityid(StringUtils.isNotBlank(residentCityidStr)&&NumberUtils.isDigits(residentCityidStr)?Integer.parseInt(residentCityidStr):null);
				if(getValue(presentPlace, "provinceName")!=null&&StringUtils.isNotBlank(getValue(presentPlace, "provinceName").toString())){
					resume.setResidentProvinceName(getValue(presentPlace, "provinceName").toString());
				}else{
					resume.setResidentProvinceName(StringUtils.isNotBlank(residentProvinceidStr)&&NumberUtils.isDigits(residentProvinceidStr)?DictionaryCodeTag.convertProvinceAbbreviation(Integer.parseInt(residentProvinceidStr)):null);
				}
				if(getValue(presentPlace, "cityName")!=null&&StringUtils.isNotBlank(getValue(presentPlace, "cityName").toString())){
					resume.setResidentCityName(getValue(presentPlace, "cityName").toString());
				}else{
					resume.setResidentCityName(StringUtils.isNotBlank(residentCityidStr)&&NumberUtils.isDigits(residentCityidStr)?DictionaryCodeTag.convertCityAbbreviation(Integer.parseInt(residentCityidStr)):null);
				}
			}

			//民族
			Object nation = getValue(dataObject, "nation");
			//超过5个长度    声明数据有问题
			if(nation!=null&&nation.toString().length() < 5){
				if(!isEmpty(nation)){
					resume.setNation(isNumericzidai(nation.toString()) ? new Integer(getValue(dataObject, "nation").toString())
							: Integer.parseInt(dataDictionaryService.findNationCodeByNameAndTypeid(getValue(dataObject, "nation").toString())));
				}
			}
			
			

			
			if (intention != null && !intention.isNullObject())
			{	
				//选择求职意向1城市
				net.sf.json.JSONObject workingPlace = intention.getJSONObject("workingPlace");
				if (workingPlace != null && !workingPlace.isNullObject()) {
					String jobTargetProvinceidStr = getValue(workingPlace, "provinceId")==null?null:getValue(workingPlace, "provinceId").toString();
					String jobTargetCityidStr = getValue(workingPlace, "cityId")==null?null:getValue(workingPlace, "cityId").toString();
					resume.setJobTargetProvinceid(StringUtils.isNotBlank(jobTargetProvinceidStr)&&NumberUtils.isDigits(jobTargetProvinceidStr)?Integer.parseInt(jobTargetProvinceidStr):null);
					resume.setJobTargetCityid(StringUtils.isNotBlank(jobTargetCityidStr)&&NumberUtils.isDigits(jobTargetCityidStr)?Integer.parseInt(jobTargetCityidStr):null);
					if(getValue(workingPlace, "provinceName")!=null&&StringUtils.isNotBlank(getValue(workingPlace, "provinceName").toString())){
						resume.setJobTargetProvinceName(getValue(workingPlace, "provinceName").toString());
					}else{
						resume.setJobTargetProvinceName(StringUtils.isNotBlank(jobTargetProvinceidStr)&&NumberUtils.isDigits(jobTargetProvinceidStr)?DictionaryCodeTag.convertProvinceAbbreviation(Integer.parseInt(jobTargetProvinceidStr)):null);
					}
					if(getValue(workingPlace, "cityName")!=null&&StringUtils.isNotBlank(getValue(workingPlace, "cityName").toString())){
						resume.setJobTargetCityName(getValue(workingPlace, "cityName").toString());
					}else{
						resume.setJobTargetCityName(StringUtils.isNotBlank(jobTargetCityidStr)&&NumberUtils.isDigits(jobTargetCityidStr)?DictionaryCodeTag.convertCityAbbreviation(Integer.parseInt(jobTargetCityidStr)):null);
					}
				}
				
				//职位--------------------------------------------------------------------------------------------
				Object expectFunc =getValue(intention, "expectFunc");
					if (expectFunc != null && !(expectFunc instanceof JSONNull) && StringUtils.isNoneBlank(expectFunc.toString())) {
						final JSONArray expectFuncArray = JSONArray.fromObject(expectFunc);
						String expectFuncStr="";
						  for (int i = 0; i < expectFuncArray.size(); i++) {  
//				                                           根据解析的数据类型使用该类型的get方法得到该值，打印输出  
				                String string = expectFuncArray.getString(i);
				                 
				                 expectFuncStr = expectFuncStr +string + " ";
				               
				            }  
						  resume.setJobTarget(expectFuncStr);
					}
				
				
				/*resume.setJobTarget(getValue(intention, "selfIntro") == null ? null
						: getValue(intention, "selfIntro").toString());*/
				
				Object salaryFroms = getValue(intention, "salaryFrom");
				Object salaryTos = getValue(intention, "salaryTo");
				/*System.out.println(salaryFroms);*/
				if(salaryFroms != null || salaryTos !=  null ){
					if(salaryFroms.equals(0) && salaryTos.equals(0)){
						// 期望薪资上限
						final String salaryFrom = getValue(intention, "salaryFrom") == null ? ""
								: getValue(intention, "salaryFrom").toString();
						// 期望薪资下限
					
						final String salaryTo = getValue(intention, "salaryTo") == null ? ""
								: getValue(intention, "salaryTo").toString();
						String  bottledParameter= salaryFrom + "-" + salaryTo;
						String findSalaryName = dataDictionaryService.findSalaryNameBycode(bottledParameter);								
						resume.setIntentionSalary(findSalaryName);
					}else {
						// 期望薪资上限
						final String salaryFrom = getValue(intention, "salaryFrom") == null ? ""
								: getValue(intention, "salaryFrom").toString();
						// 期望薪资下限
					
						final String salaryTo = getValue(intention, "salaryTo") == null ? ""
								: getValue(intention, "salaryTo").toString();
						String  bottledParameter= salaryFrom + "-" + salaryTo;
						String findSalaryName = dataDictionaryService.findSalaryNameBycode(bottledParameter);
						resume.setIntentionSalary(findSalaryName+"元/月");
					}
				
				}
				
				
			
			}
			
			return resume;
		}
		return null;
	}
	/**
	 * 返回有效的非空 JSON 值
	 * 
	 * @param intention
	 *            查询的JSON对象
	 * @param key
	 *            查询对象对应的Key
	 * @return
	 */
	private Object getValue(final net.sf.json.JSONObject intention, final String key) {
		if (intention != null && StringUtils.isNoneBlank(key)) {
			Object value = intention.get(key);

			// 不能是 null 或者 JSONNull
			if (!(value instanceof JSONNull)) {
				return value;
			}
		}

		return null;
	}
	
	/**
	 * 判断参数是否是数字
	 * @param str
	 * @return
	 */
	 public static boolean isNumericzidai(String str) {
	        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
	        Matcher isNum = pattern.matcher(str);
	        if (!isNum.matches()) {
	            return false;
	        }
	        return true;
	    }
	 
	 /**
		 * 判断是否为null
		 */
		public static boolean isEmpty(Object obj)  
	    {  
	        if (obj == null)  
	        {  
	            return true;  
	        }  
	        if ((obj instanceof List))  
	        {  
	            return ((List) obj).size() == 0;  
	        }  
	        if ((obj instanceof String))  
	        {  
	            return ((String) obj).trim().equals("");  
	        }  
	        return false;  
	    }  
		
}


