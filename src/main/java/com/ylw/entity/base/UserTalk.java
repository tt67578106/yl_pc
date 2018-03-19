package com.ylw.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ylw.entity.IdEntity;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_user_talk")
public class UserTalk extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "UserTalk";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_COMPANYID = "公司ID";
	public static final String ALIAS_JOBID = "岗位ID";
	public static final String ALIAS_USERID = "用户ID";
	public static final String ALIAS_USERNAME = "用户名称";
	public static final String ALIAS_POSITIONNAME = "岗位名称";
	public static final String ALIAS_COMPANYNAME = "公司名称";
	public static final String ALIAS_ONBOARDDATE = "入职时间";
	public static final String ALIAS_SORTING = "排序";
	public static final String ALIAS_TITLE = "说说标题";
	public static final String ALIAS_CONTENT_ID = "说说内容ID";
	public static final String ALIAS_TYPE = "说说类型";
	public static final String ALIAS_VIEW_COUNT = "浏览次数";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_PUBLISH_DATE = "发布时间";
	public static final String ALIAS_CREATETIME = "创建时间";
	
	//date formats
	public static final String FORMAT_ONBOARDDATE = DATE_FORMAT;
	public static final String FORMAT_PUBLISH_DATE = DATE_FORMAT;
	public static final String FORMAT_CREATETIME = DATE_FORMAT;
	

	//columns START
	private java.lang.Integer companyid;
	private java.lang.Integer jobid;
	private java.lang.Integer userid;
	private java.lang.String username;
	private java.lang.String positionname;
	private java.lang.String companyname;
	private java.util.Date onboarddate;
	private java.lang.Integer sorting;
	private java.lang.String title;
	private java.lang.Integer contentId;
	private Integer type;
	private java.lang.Integer viewCount;
	private java.lang.Integer status;
	private java.util.Date publishDate;
	private java.util.Date createtime;
	//columns END


	public UserTalk(){
	}

	@Column(name = "companyid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCompanyid() {
		return this.companyid;
	}
	
	public void setCompanyid(java.lang.Integer value) {
		this.companyid = value;
	}
	
	@Column(name = "jobid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getJobid() {
		return this.jobid;
	}
	
	public void setJobid(java.lang.Integer value) {
		this.jobid = value;
	}
	
	@Column(name = "userid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserid() {
		return this.userid;
	}
	
	public void setUserid(java.lang.Integer value) {
		this.userid = value;
	}
	
	@Column(name = "username", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getUsername() {
		return this.username;
	}
	
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	@Column(name = "positionname", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getPositionname() {
		return this.positionname;
	}
	
	public void setPositionname(java.lang.String value) {
		this.positionname = value;
	}
	
	@Column(name = "companyname", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getCompanyname() {
		return this.companyname;
	}
	
	public void setCompanyname(java.lang.String value) {
		this.companyname = value;
	}
	
	@Transient
	public String getOnboarddateString() {
		return DateConvertUtils.format(getOnboarddate(), FORMAT_ONBOARDDATE);
	}
	public void setOnboarddateString(String value) {
		setOnboarddate(DateConvertUtils.parse(value, FORMAT_ONBOARDDATE,java.util.Date.class));
	}
	
	@Column(name = "onboarddate", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getOnboarddate() {
		return this.onboarddate;
	}
	
	public void setOnboarddate(java.util.Date value) {
		this.onboarddate = value;
	}
	
	@Column(name = "sorting", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSorting() {
		return this.sorting;
	}
	
	public void setSorting(java.lang.Integer value) {
		this.sorting = value;
	}
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "content_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getContentId() {
		return this.contentId;
	}
	
	public void setContentId(java.lang.Integer value) {
		this.contentId = value;
	}
	
	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer value) {
		this.type = value;
	}
	
	@Column(name = "view_count", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getViewCount() {
		return this.viewCount;
	}
	
	public void setViewCount(java.lang.Integer value) {
		this.viewCount = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	@Transient
	public String getPublishDateString() {
		return DateConvertUtils.format(getPublishDate(), FORMAT_PUBLISH_DATE);
	}
	public void setPublishDateString(String value) {
		setPublishDate(DateConvertUtils.parse(value, FORMAT_PUBLISH_DATE,java.util.Date.class));
	}
	
	@Column(name = "publish_date", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getPublishDate() {
		return this.publishDate;
	}
	
	public void setPublishDate(java.util.Date value) {
		this.publishDate = value;
	}
	
	@Transient
	public String getCreatetimeString() {
		return DateConvertUtils.format(getCreatetime(), FORMAT_CREATETIME);
	}
	public void setCreatetimeString(String value) {
		setCreatetime(DateConvertUtils.parse(value, FORMAT_CREATETIME,java.util.Date.class));
	}
	
	@Column(name = "createtime", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Companyid",getCompanyid())
			.append("Jobid",getJobid())
			.append("Userid",getUserid())
			.append("Username",getUsername())
			.append("Positionname",getPositionname())
			.append("Companyname",getCompanyname())
			.append("Onboarddate",getOnboarddate())
			.append("Sorting",getSorting())
			.append("Title",getTitle())
			.append("ContentId",getContentId())
			.append("Type",getType())
			.append("ViewCount",getViewCount())
			.append("Status",getStatus())
			.append("PublishDate",getPublishDate())
			.append("Createtime",getCreatetime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserTalk == false) return false;
		if(this == obj) return true;
		UserTalk other = (UserTalk)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

