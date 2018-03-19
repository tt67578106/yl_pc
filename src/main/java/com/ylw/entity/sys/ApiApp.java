package com.ylw.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ylw.entity.IdEntity;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "api_app")
public class ApiApp extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ApiApp";
	public static final String ALIAS_ID = "主键ID";
	public static final String ALIAS_APP_KEY = "AppKey";
	public static final String ALIAS_SOURCE_CODE = "来源Code";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_ABBREVIATION = "简称";
	public static final String ALIAS_APP_SECRET = "AppSecret";
	public static final String ALIAS_APP_TOKEN = "AppToken";
	public static final String ALIAS_ENCRYPT_TYPE = "API加密类型";
	public static final String ALIAS_ENCRYPT_KEY = "API加密密钥";
	public static final String ALIAS_API_URL = "Api调用路径";
	public static final String ALIAS_STATUS_NO = "状态";
	public static final String ALIAS_RULES = "规则";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_UPDATE_TIME = "最后修改时间";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	
	//date formats
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.Long id;
	private java.lang.String appKey;
	private java.lang.String sourceCode;
	private java.lang.String name;
	private java.lang.String abbreviation;
	private java.lang.String appSecret;
	private java.lang.String appToken;
	private java.lang.String encryptType;
	private java.lang.String encryptKey;
	private java.lang.String apiUrl;
	private Integer statusNo;
	private java.lang.String rules;
	private java.lang.String remark;
	private java.util.Date updateTime;
	private java.util.Date createTime;
	//columns END


	public ApiApp(){
	}

	public ApiApp(
		java.lang.Long id
	){
		this.id = id;
	}

	
	
	
	@Column(name = "app_key", unique = true, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getAppKey() {
		return this.appKey;
	}
	
	public void setAppKey(java.lang.String value) {
		this.appKey = value;
	}
	
	@Column(name = "source_code", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getSourceCode() {
		return this.sourceCode;
	}
	
	public void setSourceCode(java.lang.String value) {
		this.sourceCode = value;
	}
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	@Column(name = "abbreviation", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getAbbreviation() {
		return this.abbreviation;
	}
	
	public void setAbbreviation(java.lang.String value) {
		this.abbreviation = value;
	}
	
	@Column(name = "app_secret", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getAppSecret() {
		return this.appSecret;
	}
	
	public void setAppSecret(java.lang.String value) {
		this.appSecret = value;
	}
	
	@Column(name = "app_token", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getAppToken() {
		return this.appToken;
	}
	
	public void setAppToken(java.lang.String value) {
		this.appToken = value;
	}
	
	@Column(name = "encrypt_type", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getEncryptType() {
		return this.encryptType;
	}
	
	public void setEncryptType(java.lang.String value) {
		this.encryptType = value;
	}
	
	@Column(name = "encrypt_key", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getEncryptKey() {
		return this.encryptKey;
	}
	
	public void setEncryptKey(java.lang.String value) {
		this.encryptKey = value;
	}
	
	@Column(name = "api_url", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getApiUrl() {
		return this.apiUrl;
	}
	
	public void setApiUrl(java.lang.String value) {
		this.apiUrl = value;
	}
	
	@Column(name = "status_no", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatusNo() {
		return this.statusNo;
	}
	
	public void setStatusNo(Integer value) {
		this.statusNo = value;
	}
	
	@Column(name = "rules", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public java.lang.String getRules() {
		return this.rules;
	}
	
	public void setRules(java.lang.String value) {
		this.rules = value;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	@Transient
	public String getUpdateTimeString() {
		return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
	}
	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,java.util.Date.class));
	}
	
	@Column(name = "update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	@Transient
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("AppKey",getAppKey())
			.append("SourceCode",getSourceCode())
			.append("Name",getName())
			.append("Abbreviation",getAbbreviation())
			.append("AppSecret",getAppSecret())
			.append("AppToken",getAppToken())
			.append("EncryptType",getEncryptType())
			.append("EncryptKey",getEncryptKey())
			.append("ApiUrl",getApiUrl())
			.append("StatusNo",getStatusNo())
			.append("Rules",getRules())
			.append("Remark",getRemark())
			.append("UpdateTime",getUpdateTime())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ApiApp == false) return false;
		if(this == obj) return true;
		ApiApp other = (ApiApp)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

