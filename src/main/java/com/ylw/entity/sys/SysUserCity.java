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
@Table(name = "site_sys_user_city")
public class SysUserCity extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SysUserCity";
	public static final String ALIAS_ID = "主键ID";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_CITY_ID = "城市ID";
	public static final String ALIAS_OPERATE_BY = "操作人";
	public static final String ALIAS_OPERATE_TIME = "操作时间";
	public static final String ALIAS_STATUS = "状态";
	
	//date formats
	public static final String FORMAT_OPERATE_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.Integer userId;
	private java.lang.Integer cityId;
	private java.lang.Integer operateBy;
	private java.util.Date operateTime;
	private Integer status;
	//columns END


	public SysUserCity(){
	}

	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	@Column(name = "city_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCityId() {
		return this.cityId;
	}
	
	public void setCityId(java.lang.Integer value) {
		this.cityId = value;
	}
	
	@Column(name = "operate_by", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getOperateBy() {
		return this.operateBy;
	}
	
	public void setOperateBy(java.lang.Integer value) {
		this.operateBy = value;
	}
	
	@Transient
	public String getOperateTimeString() {
		return DateConvertUtils.format(getOperateTime(), FORMAT_OPERATE_TIME);
	}
	public void setOperateTimeString(String value) {
		setOperateTime(DateConvertUtils.parse(value, FORMAT_OPERATE_TIME,java.util.Date.class));
	}
	
	@Column(name = "operate_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getOperateTime() {
		return this.operateTime;
	}
	
	public void setOperateTime(java.util.Date value) {
		this.operateTime = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("CityId",getCityId())
			.append("OperateBy",getOperateBy())
			.append("OperateTime",getOperateTime())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SysUserCity == false) return false;
		if(this == obj) return true;
		SysUserCity other = (SysUserCity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

