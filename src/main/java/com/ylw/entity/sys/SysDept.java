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
@Table(name = "site_sys_dept")
public class SysDept extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SysDept";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_NAME = "部门名";
	public static final String ALIAS_DEPT_NO = "部门编号";
	public static final String ALIAS_PARENT_ID = "上级部门";
	public static final String ALIAS_IS_DELETE = "是否删除";
	public static final String ALIAS_IS_INUSE = "是否在使用";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_LEAVEL = "部门级别";
	public static final String ALIAS_DEPT_DESC = "部门描述";
	public static final String ALIAS_CREATEBY = "创建人";
	public static final String ALIAS_UPDATEBY = "更新人";
	public static final String ALIAS_MODUAL = "类型";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.String name;
	private java.lang.String deptNo;
	private java.lang.Integer parentId;
	private Integer isDelete;
	private Integer isInuse;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private java.lang.String leavel;
	private java.lang.String deptDesc;
	private java.lang.Integer createby;
	private java.lang.Integer updateby;
	private java.lang.String modual;
	//columns END


	public SysDept(){
	}
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	@Column(name = "dept_no", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDeptNo() {
		return this.deptNo;
	}
	
	public void setDeptNo(java.lang.String value) {
		this.deptNo = value;
	}
	
	@Column(name = "parent_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getParentId() {
		return this.parentId;
	}
	
	public void setParentId(java.lang.Integer value) {
		this.parentId = value;
	}
	
	@Column(name = "is_delete", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}
	
	@Column(name = "is_inuse", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsInuse() {
		return this.isInuse;
	}
	
	public void setIsInuse(Integer value) {
		this.isInuse = value;
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
	
	@Column(name = "leavel", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getLeavel() {
		return this.leavel;
	}
	
	public void setLeavel(java.lang.String value) {
		this.leavel = value;
	}
	
	@Column(name = "dept_desc", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getDeptDesc() {
		return this.deptDesc;
	}
	
	public void setDeptDesc(java.lang.String value) {
		this.deptDesc = value;
	}
	
	@Column(name = "createby", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCreateby() {
		return this.createby;
	}
	
	public void setCreateby(java.lang.Integer value) {
		this.createby = value;
	}
	
	@Column(name = "updateby", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUpdateby() {
		return this.updateby;
	}
	
	public void setUpdateby(java.lang.Integer value) {
		this.updateby = value;
	}
	
	@Column(name = "modual", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getModual() {
		return this.modual;
	}
	
	public void setModual(java.lang.String value) {
		this.modual = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("DeptNo",getDeptNo())
			.append("ParentId",getParentId())
			.append("IsDelete",getIsDelete())
			.append("IsInuse",getIsInuse())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Leavel",getLeavel())
			.append("DeptDesc",getDeptDesc())
			.append("Createby",getCreateby())
			.append("Updateby",getUpdateby())
			.append("Modual",getModual())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SysDept == false) return false;
		if(this == obj) return true;
		SysDept other = (SysDept)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

