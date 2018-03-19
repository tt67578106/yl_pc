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
@Table(name = "site_feedback")
public class Feedback extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Feedback";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TYPES = "留言类型";
	public static final String ALIAS_NICKNAME = "昵称";
	public static final String ALIAS_CONTACT_INFO = "联系方式";
	public static final String ALIAS_CONTENT = "留言内容";
	public static final String ALIAS_REFERENCE = "参考信息";
	public static final String ALIAS_SOURCE = "信息来源";
	public static final String ALIAS_STATUS = "状态：（1:未读未处理、2、已读未处理、3已读已处理）";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_CREATE_IP = "留言人的IP地址";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	

	//columns START
	private Integer types;
	private java.lang.String nickname;
	private java.lang.String contactInfo;
	private java.lang.String content;
	private java.lang.String reference;
	private java.lang.String source;
	private Integer status;
	private java.util.Date createTime;
	private java.lang.String createIp;
	//columns END


	public Feedback(){
	}
	
	@Column(name = "types", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getTypes() {
		return this.types;
	}
	
	public void setTypes(Integer value) {
		this.types = value;
	}
	
	@Column(name = "nickname", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getNickname() {
		return this.nickname;
	}
	
	public void setNickname(java.lang.String value) {
		this.nickname = value;
	}
	
	@Column(name = "contact_info", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getContactInfo() {
		return this.contactInfo;
	}
	
	public void setContactInfo(java.lang.String value) {
		this.contactInfo = value;
	}
	
	@Column(name = "content", unique = false, nullable = false, insertable = true, updatable = true, length = 65535)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	@Column(name = "reference", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getReference() {
		return this.reference;
	}
	
	public void setReference(java.lang.String value) {
		this.reference = value;
	}
	
	@Column(name = "source", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getSource() {
		return this.source;
	}
	
	public void setSource(java.lang.String value) {
		this.source = value;
	}
	
	@Column(name = "status", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	@Transient
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	@Column(name = "create_time", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	@Column(name = "create_ip", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getCreateIp() {
		return this.createIp;
	}
	
	public void setCreateIp(java.lang.String value) {
		this.createIp = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Types",getTypes())
			.append("Nickname",getNickname())
			.append("ContactInfo",getContactInfo())
			.append("Content",getContent())
			.append("Reference",getReference())
			.append("Source",getSource())
			.append("Status",getStatus())
			.append("CreateTime",getCreateTime())
			.append("CreateIp",getCreateIp())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Feedback == false) return false;
		if(this == obj) return true;
		Feedback other = (Feedback)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

