package com.ylw.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ylw.entity.IdEntity;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_sys_role_user")
public class SysRoleUser extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SysRoleUser";
	public static final String ALIAS_ROLE_ID = "角色ID";
	public static final String ALIAS_USER_ID = "用户ID";
	
	//date formats
	

	//columns START
	private java.lang.Integer roleId;
	private java.lang.Integer userId;
	//columns END


	public SysRoleUser(){
	}
	
	@Column(name = "role_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(java.lang.Integer value) {
		this.roleId = value;
	}
	
	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("RoleId",getRoleId())
			.append("UserId",getUserId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SysRoleUser == false) return false;
		if(this == obj) return true;
//		SysRoleUser other = (SysRoleUser)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}

