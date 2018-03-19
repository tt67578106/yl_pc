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
@Table(name = "site_apply_follow_up")
public class ApplyFollowUp extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ApplyFollowUp";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_APPLY_ID = "报名信息ID";
	public static final String ALIAS_RESPONSEBY = "负责跟进人";
	public static final String ALIAS_IS_CONTACT = "是否已联系";
	public static final String ALIAS_CONTACT_TIME = "最后联系时间";
	public static final String ALIAS_CONTACT_RESULT = "联系结果";
	public static final String ALIAS_CONTACT_CONTENT = "联系内容";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_ASSIGNER = "分配人";
	
	//date formats
	public static final String FORMAT_CONTACT_TIME = DATE_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.Integer applyId;
	private java.lang.Integer responseby;
	private Integer isContact;
	private java.util.Date contactTime;
	private Integer contactResult;
	private java.lang.String contactContent;
	private java.lang.String remark;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private java.lang.Integer assigner;
	//columns END


	public ApplyFollowUp(){
	}

	@Column(name = "apply_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getApplyId() {
		return this.applyId;
	}
	
	public void setApplyId(java.lang.Integer value) {
		this.applyId = value;
	}
	
	@Column(name = "responseby", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getResponseby() {
		return this.responseby;
	}
	
	public void setResponseby(java.lang.Integer value) {
		this.responseby = value;
	}
	
	@Column(name = "is_contact", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsContact() {
		return this.isContact;
	}
	
	public void setIsContact(Integer value) {
		this.isContact = value;
	}
	
	@Transient
	public String getContactTimeString() {
		return DateConvertUtils.format(getContactTime(), FORMAT_CONTACT_TIME);
	}
	public void setContactTimeString(String value) {
		setContactTime(DateConvertUtils.parse(value, FORMAT_CONTACT_TIME,java.util.Date.class));
	}
	
	@Column(name = "contact_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getContactTime() {
		return this.contactTime;
	}
	
	public void setContactTime(java.util.Date value) {
		this.contactTime = value;
	}
	
	@Column(name = "contact_result", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getContactResult() {
		return this.contactResult;
	}
	
	public void setContactResult(Integer value) {
		this.contactResult = value;
	}
	
	@Column(name = "contact_content", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getContactContent() {
		return this.contactContent;
	}
	
	public void setContactContent(java.lang.String value) {
		this.contactContent = value;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
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
	
	@Column(name = "assigner", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAssigner() {
		return this.assigner;
	}
	
	public void setAssigner(java.lang.Integer value) {
		this.assigner = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ApplyId",getApplyId())
			.append("Responseby",getResponseby())
			.append("IsContact",getIsContact())
			.append("ContactTime",getContactTime())
			.append("ContactResult",getContactResult())
			.append("ContactContent",getContactContent())
			.append("Remark",getRemark())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Assigner",getAssigner())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ApplyFollowUp == false) return false;
		if(this == obj) return true;
		ApplyFollowUp other = (ApplyFollowUp)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

