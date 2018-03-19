package com.ylw.entity.utm;



import com.ylw.entity.IdEntity;
import com.ylw.util.DateConvertUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */


@Entity
@Table(name = "utm_log")
public class UtmLog extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "UtmLog";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_PROJECT_ID = "项目ID";
	public static final String ALIAS_LOG_TIME = "发生时间";
	public static final String ALIAS_VISITOR_ID = "访客ID";
	public static final String ALIAS_USERS_TYPE = "用户类型";
	public static final String ALIAS_USERS_CODE = "用户code";
	public static final String ALIAS_DOMAIN_NAME = "域名";
	public static final String ALIAS_PLATFORM_NAME = "平台";
	public static final String ALIAS_OBJECT_TYPE = "对象类型（1：简历，2：职位，3：订单，4：企业，5：问题，6：反馈，7：话题，8：积分，9：借贷）";
	public static final String ALIAS_OBJECT_ID = "对象ID";
	public static final String ALIAS_OBJECT_CODE = "对象code";
	public static final String ALIAS_OBJECT_PROPERTY = "对象属性";
	public static final String ALIAS_EVENT_TYPE = "事件类型(\"A_login\" : \"登录\", \"A_register\" : \"主动注册\"；\"invoke\" : \"账号激活\")";
	public static final String ALIAS_EVENT_NATURE = "事件属性";
	public static final String ALIAS_OAPP_SOURCE = "原source";
	public static final String ALIAS_OAPP_API_KEY = "原APPkey";
	public static final String ALIAS_OAPP_GENERALIZE_CODE= "推广code";
	public static final String ALIAS_PUTM_SOURCE = "channelcode";
	public static final String ALIAS_PUTM_MEDIUM = "媒介";
	public static final String ALIAS_PUTM_CAMPAIGN = "名称";
	public static final String ALIAS_PUTM_CONTENT = "内容";
	public static final String ALIAS_PUTM_KEY_WORD = "关键字";
	public static final String ALIAS_UUID = "唯一id";
	public static final String ALIAS_NET_ENV = "网络环境";
	public static final String ALIAS_NET_ADDR = "网络地址";
	public static final String ALIAS_CLIENT_ENV = "客户环境";
	public static final String ALIAS_HOST_PROJECT = "操作的项目";
	public static final String ALIAS_HOST_PROJECT_ADDR = "主机项目地址";
	public static final String ALIAS_OAPP_CHANNEL_CODE = "原channel_code";
	public static final String ALIAS_OAPP_SOURCE_CODE = "原source_code";
	public static final String ALIAS_PUTM_TERM = "周期";
	public static final String ALIAS_PUP1 = "data_id 新增的数据id";
	public static final String ALIAS_PUP2 = "自定义参数";
	public static final String ALIAS_PUP3 = "自定义参数";
	public static final String ALIAS_PUP4 = "pup4";
	public static final String ALIAS_PUP5 = "pup5";
	public static final String ALIAS_PUP6 = "pup6";
	public static final String ALIAS_PUP7 = "pup7";
	public static final String ALIAS_PUP8 = "pup8";
	public static final String ALIAS_PUP9 = "pup9";
	public static final String ALIAS_PUP10 = "pup10";
	public static final String ALIAS_RES_UID_INT = "int用户id";
	public static final String ALIAS_RES_UID_STR = "用户的code";
	public static final String ALIAS_RES_RESUME_ID = "优蓝的简历id";
	public static final String ALIAS_RES_RESUME_CD = "优蓝的简历code";
	public static final String ALIAS_RES_APPLY_ID = "报名投递id";
	
	//date formats
	public static final String FORMAT_LOG_TIME = DATE_FORMAT;
	

	//columns START
	private Long id;
	private Long projectId;
	private java.util.Date logTime;
	private String visitorId;
	private String usersType;
	private String usersCode;
	private String domainName;
	private String platformName;
	private Integer objectType;
	private String objectId;
	private String objectCode;
	private String objectProperty;
	private String eventType;
	private String eventNature;
	private String oappSource;
	private String oappApiKey;
	private String generalizeCode;
	private String putmSource;
	private String putmMedium;
	private String putmCampaign;
	private String putmContent;
	private String putmKeyWord;
	private String uuid;
	private String netEnv;
	private String netAddr;
	private String clientEnv;
	private String hostProject;
	private String hostProjectAddr;
	private String oappChannelCode;
	private String oappSourceCode;
	private String putmTerm;
	private String pup1;
	private String pup2;
	private String pup3;
	private String pup4;
	private String pup5;
	private String pup6;
	private String pup7;
	private String pup8;
	private String pup9;
	private String pup10;
	private Long resUidInt;
	private String resUidStr;
	private Integer resResumeId;
	private String resResumeCd;
	private Integer resApplyId;
	//columns END
	@Column(name = "generalize_code", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getGeneralizeCode() {
		return generalizeCode;
	}

	public void setGeneralizeCode(String generalizeCode) {
		this.generalizeCode = generalizeCode;
	}

	public UtmLog(){
	}

	public UtmLog(
		Long id
	){
		this.id = id;
	}

	
	
	
	@Column(name = "project_id", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Long getProjectId() {
		return this.projectId;
	}
	
	public void setProjectId(Long value) {
		this.projectId = value;
	}
	
	@Transient
	public String getLogTimeString() {
		return DateConvertUtils.format(getLogTime(), FORMAT_LOG_TIME);
	}
	public void setLogTimeString(String value) {
		setLogTime(DateConvertUtils.parse(value, FORMAT_LOG_TIME,java.util.Date.class));
	}
	
	@Column(name = "log_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getLogTime() {
		return this.logTime;
	}
	
	public void setLogTime(java.util.Date value) {
		this.logTime = value;
	}
	
	@Column(name = "visitor_id", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getVisitorId() {
		return this.visitorId;
	}
	
	public void setVisitorId(String value) {
		this.visitorId = value;
	}
	
	@Column(name = "users_type", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getUsersType() {
		return this.usersType;
	}
	
	public void setUsersType(String value) {
		this.usersType = value;
	}
	
	@Column(name = "users_code", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getUsersCode() {
		return this.usersCode;
	}
	
	public void setUsersCode(String value) {
		this.usersCode = value;
	}
	
	@Column(name = "domain_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getDomainName() {
		return this.domainName;
	}
	
	public void setDomainName(String value) {
		this.domainName = value;
	}
	
	@Column(name = "platform_name", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getPlatformName() {
		return this.platformName;
	}
	
	public void setPlatformName(String value) {
		this.platformName = value;
	}
	
	@Column(name = "object_type", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getObjectType() {
		return this.objectType;
	}
	
	public void setObjectType(Integer value) {
		this.objectType = value;
	}
	
	@Column(name = "object_id", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getObjectId() {
		return this.objectId;
	}
	
	public void setObjectId(String value) {
		this.objectId = value;
	}
	
	@Column(name = "object_code", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getObjectCode() {
		return this.objectCode;
	}
	
	public void setObjectCode(String value) {
		this.objectCode = value;
	}
	
	@Column(name = "object_property", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getObjectProperty() {
		return this.objectProperty;
	}
	
	public void setObjectProperty(String value) {
		this.objectProperty = value;
	}
	
	@Column(name = "event_type", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public String getEventType() {
		return this.eventType;
	}
	
	public void setEventType(String value) {
		this.eventType = value;
	}
	
	@Column(name = "event_nature", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getEventNature() {
		return this.eventNature;
	}
	
	public void setEventNature(String value) {
		this.eventNature = value;
	}
	
	@Column(name = "o_app_source", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getOappSource() {
		return this.oappSource;
	}
	
	public void setOappSource(String value) {
		this.oappSource = value;
	}
	
	@Column(name = "o_app_api_key", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getOappApiKey() {
		return this.oappApiKey;
	}
	
	public void setOappApiKey(String value) {
		this.oappApiKey = value;
	}
	
	@Column(name = "p_utm_source", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPutmSource() {
		return this.putmSource;
	}
	
	public void setPutmSource(String value) {
		this.putmSource = value;
	}
	
	@Column(name = "p_utm_medium", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPutmMedium() {
		return this.putmMedium;
	}
	
	public void setPutmMedium(String value) {
		this.putmMedium = value;
	}
	
	@Column(name = "p_utm_campaign", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPutmCampaign() {
		return this.putmCampaign;
	}
	
	public void setPutmCampaign(String value) {
		this.putmCampaign = value;
	}
	
	@Column(name = "p_utm_content", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPutmContent() {
		return this.putmContent;
	}
	
	public void setPutmContent(String value) {
		this.putmContent = value;
	}
	
	@Column(name = "p_utm_key_word", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPutmKeyWord() {
		return this.putmKeyWord;
	}
	
	public void setPutmKeyWord(String value) {
		this.putmKeyWord = value;
	}
	
	@Column(name = "uuid", unique = false, nullable = true, insertable = true, updatable = true, length = 33)
	public String getUuid() {
		return this.uuid;
	}
	
	public void setUuid(String value) {
		this.uuid = value;
	}
	
	@Column(name = "net_env", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getNetEnv() {
		return this.netEnv;
	}
	
	public void setNetEnv(String value) {
		this.netEnv = value;
	}
	
	@Column(name = "net_addr", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getNetAddr() {
		return this.netAddr;
	}
	
	public void setNetAddr(String value) {
		this.netAddr = value;
	}
	
	@Column(name = "client_env", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getClientEnv() {
		return this.clientEnv;
	}
	
	public void setClientEnv(String value) {
		this.clientEnv = value;
	}
	
	@Column(name = "host_project", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getHostProject() {
		return this.hostProject;
	}
	
	public void setHostProject(String value) {
		this.hostProject = value;
	}
	
	@Column(name = "host_project_addr", unique = false, nullable = true, insertable = true, updatable = true, length = 60)
	public String getHostProjectAddr() {
		return this.hostProjectAddr;
	}
	
	public void setHostProjectAddr(String value) {
		this.hostProjectAddr = value;
	}
	
	@Column(name = "o_app_channel_code", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getOappChannelCode() {
		return this.oappChannelCode;
	}
	
	public void setOappChannelCode(String value) {
		this.oappChannelCode = value;
	}
	
	@Column(name = "o_app_source_code", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getOappSourceCode() {
		return this.oappSourceCode;
	}
	
	public void setOappSourceCode(String value) {
		this.oappSourceCode = value;
	}
	
	@Column(name = "p_utm_term", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPutmTerm() {
		return this.putmTerm;
	}
	
	public void setPutmTerm(String value) {
		this.putmTerm = value;
	}
	
	@Column(name = "p_u_p_1", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPup1() {
		return this.pup1;
	}
	
	public void setPup1(String value) {
		this.pup1 = value;
	}
	
	@Column(name = "p_u_p_2", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPup2() {
		return this.pup2;
	}
	
	public void setPup2(String value) {
		this.pup2 = value;
	}
	
	@Column(name = "p_u_p_3", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPup3() {
		return this.pup3;
	}
	
	public void setPup3(String value) {
		this.pup3 = value;
	}
	
	@Column(name = "p_u_p_4", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPup4() {
		return this.pup4;
	}
	
	public void setPup4(String value) {
		this.pup4 = value;
	}
	
	@Column(name = "p_u_p_5", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPup5() {
		return this.pup5;
	}
	
	public void setPup5(String value) {
		this.pup5 = value;
	}
	
	@Column(name = "p_u_p_6", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPup6() {
		return this.pup6;
	}
	
	public void setPup6(String value) {
		this.pup6 = value;
	}
	
	@Column(name = "p_u_p_7", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPup7() {
		return this.pup7;
	}
	
	public void setPup7(String value) {
		this.pup7 = value;
	}
	
	@Column(name = "p_u_p_8", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPup8() {
		return this.pup8;
	}
	
	public void setPup8(String value) {
		this.pup8 = value;
	}
	
	@Column(name = "p_u_p_9", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPup9() {
		return this.pup9;
	}
	
	public void setPup9(String value) {
		this.pup9 = value;
	}
	
	@Column(name = "p_u_p_10", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPup10() {
		return this.pup10;
	}
	
	public void setPup10(String value) {
		this.pup10 = value;
	}
	
	@Column(name = "res_uid_int", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Long getResUidInt() {
		return this.resUidInt;
	}
	
	public void setResUidInt(Long value) {
		this.resUidInt = value;
	}
	
	@Column(name = "res_uid_str", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getResUidStr() {
		return this.resUidStr;
	}
	
	public void setResUidStr(String value) {
		this.resUidStr = value;
	}
	
	@Column(name = "res_resume_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getResResumeId() {
		return this.resResumeId;
	}
	
	public void setResResumeId(Integer value) {
		this.resResumeId = value;
	}
	
	@Column(name = "res_resume_cd", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getResResumeCd() {
		return this.resResumeCd;
	}
	
	public void setResResumeCd(String value) {
		this.resResumeCd = value;
	}
	
	@Column(name = "res_apply_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getResApplyId() {
		return this.resApplyId;
	}
	
	public void setResApplyId(Integer value) {
		this.resApplyId = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ProjectId",getProjectId())
			.append("LogTime",getLogTime())
			.append("VisitorId",getVisitorId())
			.append("UsersType",getUsersType())
			.append("UsersCode",getUsersCode())
			.append("DomainName",getDomainName())
			.append("PlatformName",getPlatformName())
			.append("ObjectType",getObjectType())
			.append("ObjectId",getObjectId())
			.append("ObjectCode",getObjectCode())
			.append("ObjectProperty",getObjectProperty())
			.append("EventType",getEventType())
			.append("EventNature",getEventNature())
			.append("OappSource",getOappSource())
			.append("OappApiKey",getOappApiKey())
			.append("PutmSource",getPutmSource())
			.append("PutmMedium",getPutmMedium())
			.append("PutmCampaign",getPutmCampaign())
			.append("PutmContent",getPutmContent())
			.append("PutmKeyWord",getPutmKeyWord())
			.append("Uuid",getUuid())
			.append("NetEnv",getNetEnv())
			.append("NetAddr",getNetAddr())
			.append("ClientEnv",getClientEnv())
			.append("HostProject",getHostProject())
			.append("HostProjectAddr",getHostProjectAddr())
			.append("OappChannelCode",getOappChannelCode())
			.append("OappSourceCode",getOappSourceCode())
			.append("PutmTerm",getPutmTerm())
			.append("Pup1",getPup1())
			.append("Pup2",getPup2())
			.append("Pup3",getPup3())
			.append("Pup4",getPup4())
			.append("Pup5",getPup5())
			.append("Pup6",getPup6())
			.append("Pup7",getPup7())
			.append("Pup8",getPup8())
			.append("Pup9",getPup9())
			.append("Pup10",getPup10())
			.append("ResUidInt",getResUidInt())
			.append("ResUidStr",getResUidStr())
			.append("ResResumeId",getResResumeId())
			.append("ResResumeCd",getResResumeCd())
			.append("ResApplyId",getResApplyId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UtmLog == false) return false;
		if(this == obj) return true;
		UtmLog other = (UtmLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

