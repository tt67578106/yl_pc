package com.ylw.entity.sys;

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
@Table(name = "site_sys_message")
public class SysMessage extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SysMessage";
	public static final String ALIAS_ID = "主键ID";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_TYPE = "消息类型";
	public static final String ALIAS_IS_READ = "是否已读";
	public static final String ALIAS_DETAIL_ID = "详情ID";
	public static final String ALIAS_READ_TIME = "阅读时间";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	
	//date formats
	public static final String FORMAT_READ_TIME = DATE_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.Integer userId;
	private Integer type;
	private Integer isRead;
	private java.lang.Integer detailId;
	private java.util.Date readTime;
	private java.util.Date createTime;
	//columns END


	public SysMessage(){
	}
	
	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer value) {
		this.type = value;
	}
	
	@Column(name = "is_read", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsRead() {
		return this.isRead;
	}
	
	public void setIsRead(Integer value) {
		this.isRead = value;
	}
	
	@Column(name = "detail_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDetailId() {
		return this.detailId;
	}
	
	public void setDetailId(java.lang.Integer value) {
		this.detailId = value;
	}
	
	@Transient
	public String getReadTimeString() {
		return DateConvertUtils.format(getReadTime(), FORMAT_READ_TIME);
	}
	public void setReadTimeString(String value) {
		setReadTime(DateConvertUtils.parse(value, FORMAT_READ_TIME,java.util.Date.class));
	}
	
	@Column(name = "read_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getReadTime() {
		return this.readTime;
	}
	
	public void setReadTime(java.util.Date value) {
		this.readTime = value;
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
			.append("UserId",getUserId())
			.append("Type",getType())
			.append("IsRead",getIsRead())
			.append("DetailId",getDetailId())
			.append("ReadTime",getReadTime())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SysMessage == false) return false;
		if(this == obj) return true;
		SysMessage other = (SysMessage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

