package com.ylw.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ylw.entity.IdEntity;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.Cai
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "sys_user")
public class SysUser extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SysUser";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_LOGIN_NAME = "登录名";
	public static final String ALIAS_USER_NAME = "用户名";
	public static final String ALIAS_PASSWORD = "密码";
	public static final String ALIAS_SALT = "加密串";
	public static final String ALIAS_ORGANIZATION_ID = "所属组织结构";
	public static final String ALIAS_ROLE_IDS = "角色列表";
	public static final String ALIAS_LAST_LOGIN_TIME = "最后登录时间";
	public static final String ALIAS_LAST_LOGIN_IP = "最后登录IP";
	public static final String ALIAS_LOCKED = "锁定状态";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	
	//date formats
	public static final String FORMAT_LAST_LOGIN_TIME = FORMAT_CREATE_TIME;
	public static final String FORMAT_DATE_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.String loginName;
	private java.lang.String userName;
	private String plainPassword;
	private java.lang.String password;
	private java.lang.String salt;
	private java.lang.Integer organizationId;
	private java.lang.String roleIds;
	private java.util.Date lastLoginTime;
	private java.lang.String lastLoginIp;
	private java.lang.Boolean locked = Boolean.FALSE;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	//columns END

	public SysUser(){
	}

	@Column(name = "login_name", unique = true, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getLoginName() {
		return this.loginName;
	}
	
	public void setLoginName(java.lang.String value) {
		this.loginName = value;
	}
	
	@Column(name = "user_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	@Column(name = "password", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getPassword() {
		return this.password;
	}
	@Transient
	@JsonIgnore
	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	@Column(name = "salt", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getSalt() {
		return this.salt;
	}
	
	public void setSalt(java.lang.String value) {
		this.salt = value;
	}
	
	@Column(name = "organization_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getOrganizationId() {
		return this.organizationId;
	}
	
	public void setOrganizationId(java.lang.Integer value) {
		this.organizationId = value;
	}
	
	@Column(name = "role_ids", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getRoleIds() {
		return this.roleIds == null?"":this.roleIds;
	}
	
	public void setRoleIds(java.lang.String value) {
		this.roleIds = value;
	}
	/**
	 * 查询角色id数组
	 * @return
	 */
	@Transient
	@JsonIgnore
	public String[] getRoleIdArray() {
		return getRoleIds().split("/");
	}
	@Transient
	public String getLastLoginTimeString() {
		if(getLastLoginTime() != null){
		return DateConvertUtils.format(getLastLoginTime(), FORMAT_LAST_LOGIN_TIME);
		}
		return "";
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@Column(name = "last_login_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getLastLoginTime() {
		return this.lastLoginTime;
	}
	
	public void setLastLoginTime(java.util.Date value) {
		this.lastLoginTime = value;
	}
	
	@Column(name = "last_login_ip", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getLastLoginIp() {
		return this.lastLoginIp;
	}
	
	public void setLastLoginIp(java.lang.String value) {
		this.lastLoginIp = value;
	}
	
	@Column(name = "locked", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.lang.Boolean getLocked() {
		return this.locked;
	}
	
	public void setLocked(java.lang.Boolean value) {
		this.locked = value;
	}
	
	@Transient
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	@Transient
	public String getUpdateTimeString() {
		if(getUpdateTime() != null){
			return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
		}
		return "";
	}
	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,java.util.Date.class));
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@Column(name = "update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("LoginName",getLoginName())
			.append("UserName",getUserName())
			.append("Password",getPassword())
			.append("Salt",getSalt())
			.append("OrganizationId",getOrganizationId())
			.append("RoleIds",getRoleIds())
			.append("LastLoginTime",getLastLoginTime())
			.append("LastLoginIp",getLastLoginIp())
			.append("Locked",getLocked())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SysUser == false) return false;
		if(this == obj) return true;
		SysUser other = (SysUser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

