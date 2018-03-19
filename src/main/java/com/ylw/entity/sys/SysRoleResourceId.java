package com.ylw.entity.sys;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Embeddable
public class SysRoleResourceId implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	private java.lang.Integer roleId;
	private java.lang.Integer resourceId;

	public SysRoleResourceId(){
	}

	public SysRoleResourceId(
		java.lang.Integer roleId,
	
		java.lang.Integer resourceId
	){
		this.roleId = roleId;
		this.resourceId = resourceId;
	}

	
	
	public void setRoleId(java.lang.Integer value) {
		this.roleId = value;
	}
	
	@Column(name = "role_id", unique = false, nullable = false, insertable = true, updatable = true, length = 10)	
	public java.lang.Integer getRoleId() {
		return this.roleId;
	}
	
	public void setResourceId(java.lang.Integer value) {
		this.resourceId = value;
	}
	
	@Column(name = "resource_id", unique = false, nullable = false, insertable = true, updatable = true, length = 10)	
	public java.lang.Integer getResourceId() {
		return this.resourceId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}
}