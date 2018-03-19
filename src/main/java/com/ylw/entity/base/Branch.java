package com.ylw.entity.base;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylw.entity.IdEntity;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.Cai
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_branch")
@JsonIgnoreProperties({"createTime","createUserId","updateTime","updateUserId","branchCityList","isPublish"})
public class Branch extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Branch";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_WEB_PREFIX = "网站分站前缀";
	public static final String ALIAS_BRANCH_NAME = "分站名称";
	public static final String ALIAS_BRANCH_TYPE = "分站类型";
	public static final String ALIAS_ABBR = "缩写";
	public static final String ALIAS_DEFAULT_CITY_ID = "默认城市";
	public static final String ALIAS_IS_PUBLISH = "是否发布 1：未发布2：已发布";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_CREATE_USER_ID = "createUserId";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String ALIAS_UPDATE_USER_ID = "updateUserId";
	public static final String ALIAS_QQ_GROUP = "qq群号码";
	public static final String ALIAS_QQ_JOIN_CODE = "qq加群代码";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	

	//columns START
	private String webPrefix;
	private java.lang.String branchName;
	private Integer branchType;
	private java.lang.String abbr;
	private java.lang.Integer defaultCityId;
	private Integer isPublish;
	private java.util.Date createTime;
	private java.lang.Integer createUserId;
	private java.util.Date updateTime;
	private java.lang.Integer updateUserId;
	private List<BranchCity> branchCityList;
	private String qqGroup;
	private String qqJoinCode;
	//columns END



	public Branch(){
	}
	
	public Branch(Integer id,String branchName) {
		super();
		this.id = id;
		this.branchName = branchName;
	}

	@OneToMany(cascade={CascadeType.ALL},mappedBy="branch")
	public List<BranchCity> getBranchCityList() {
		return branchCityList;
	}
	public void setBranchCityList(List<BranchCity> branchCityList) {
		this.branchCityList = branchCityList;
	}

	@Column(name = "web_prefix", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getWebPrefix() {
		return this.webPrefix;
	}
	
	public void setWebPrefix(java.lang.String value) {
		this.webPrefix = value;
	}
	
	@Column(name = "branch_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getBranchName() {
		return this.branchName;
	}
	
	public void setBranchName(java.lang.String value) {
		this.branchName = value;
	}
	
	@Column(name = "branch_type", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getBranchType() {
		return this.branchType;
	}
	
	public void setBranchType(Integer value) {
		this.branchType = value;
	}

	@Column(name = "abbr", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getAbbr() {
		return this.abbr;
	}
	
	public void setAbbr(java.lang.String value) {
		this.abbr = value;
	}
	
	@Column(name = "qq_group", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getQqGroup() {
		return qqGroup;
	}

	public void setQqGroup(String qqGroup) {
		this.qqGroup = qqGroup;
	}

	@Column(name = "qq_join_code", unique = false, nullable = true, insertable = true, updatable = true, length = 16777215)
	public String getQqJoinCode() {
		return qqJoinCode;
	}

	public void setQqJoinCode(String qqJoinCode) {
		this.qqJoinCode = qqJoinCode;
	}

	@Column(name = "default_city_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDefaultCityId() {
		return this.defaultCityId;
	}
	
	public void setDefaultCityId(java.lang.Integer value) {
		this.defaultCityId = value;
	}
	
	@Column(name = "is_publish", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsPublish() {
		return this.isPublish;
	}
	
	public void setIsPublish(Integer value) {
		this.isPublish = value;
	}
	@Transient
	@JsonIgnore
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
	
	@Column(name = "create_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCreateUserId() {
		return this.createUserId;
	}
	
	public void setCreateUserId(java.lang.Integer value) {
		this.createUserId = value;
	}
	
	@Transient
	@JsonIgnore
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
	
	@Column(name = "update_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUpdateUserId() {
		return this.updateUserId;
	}
	
	public void setUpdateUserId(java.lang.Integer value) {
		this.updateUserId = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("BranchName",getBranchName())
			.append("BranchType",getBranchType())
			.append("Abbr",getAbbr())
			.append("DefaultCityId",getDefaultCityId())
			.append("IsPublish",getIsPublish())
			.append("CreateTime",getCreateTime())
			.append("CreateUserId",getCreateUserId())
			.append("UpdateTime",getUpdateTime())
			.append("UpdateUserId",getUpdateUserId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Branch == false) return false;
		if(this == obj) return true;
		Branch other = (Branch)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

