package com.ylw.entity.sys;

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
@Table(name = "site_sys_role_resource")
public class SysRoleResource extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SysRoleResource";
	public static final String ALIAS_ROLE_ID = "角色ID";
	public static final String ALIAS_RESOURCE_ID = "资源ID";
	
	//date formats
	
	private SysRoleResourceId id;

	public SysRoleResource(){
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SysRoleResource == false) return false;
		if(this == obj) return true;
//		SysRoleResource other = (SysRoleResource)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}

