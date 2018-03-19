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
@Table(name = "site_sys_resource")
public class SysResource extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SysResource";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_MODUAL = "类型";
	public static final String ALIAS_EN_NAME = "资源英文名";
	public static final String ALIAS_NAME = "资源名";
	public static final String ALIAS_PAGE_URL = "路径";
	public static final String ALIAS_MIAOSHU = "描述";
	public static final String ALIAS_IS_DELETE = "是否删除";
	public static final String ALIAS_IS_ADMIN = "是否管理员权限";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_PARENT_ID = "父级菜单";
	public static final String ALIAS_CREATEBY = "创建人";
	public static final String ALIAS_UPDATEBY = "创建时间";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.String modual;
	private java.lang.String enName;
	private java.lang.String name;
	private java.lang.String pageUrl;
	private java.lang.String miaoshu;
	private java.lang.Boolean isDelete;
	private java.lang.Boolean isAdmin;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private java.lang.Integer parentId;
	private java.lang.Long createby;
	private java.lang.Long updateby;
	//columns END


	public SysResource(){
	}

	
	
	@Column(name = "modual", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getModual() {
		return this.modual;
	}
	
	public void setModual(java.lang.String value) {
		this.modual = value;
	}
	
	@Column(name = "en_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getEnName() {
		return this.enName;
	}
	
	public void setEnName(java.lang.String value) {
		this.enName = value;
	}
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	@Column(name = "page_url", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getPageUrl() {
		return this.pageUrl;
	}
	
	public void setPageUrl(java.lang.String value) {
		this.pageUrl = value;
	}
	
	@Column(name = "miaoshu", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getMiaoshu() {
		return this.miaoshu;
	}
	
	public void setMiaoshu(java.lang.String value) {
		this.miaoshu = value;
	}
	
	@Column(name = "is_delete", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.lang.Boolean getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(java.lang.Boolean value) {
		this.isDelete = value;
	}
	
	@Column(name = "is_admin", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.lang.Boolean getIsAdmin() {
		return this.isAdmin;
	}
	
	public void setIsAdmin(java.lang.Boolean value) {
		this.isAdmin = value;
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
	
	@Column(name = "parent_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getParentId() {
		return this.parentId;
	}
	
	public void setParentId(java.lang.Integer value) {
		this.parentId = value;
	}
	
	@Column(name = "createby", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getCreateby() {
		return this.createby;
	}
	
	public void setCreateby(java.lang.Long value) {
		this.createby = value;
	}
	
	@Column(name = "updateby", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getUpdateby() {
		return this.updateby;
	}
	
	public void setUpdateby(java.lang.Long value) {
		this.updateby = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Modual",getModual())
			.append("EnName",getEnName())
			.append("Name",getName())
			.append("PageUrl",getPageUrl())
			.append("Miaoshu",getMiaoshu())
			.append("IsDelete",getIsDelete())
			.append("IsAdmin",getIsAdmin())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("ParentId",getParentId())
			.append("Createby",getCreateby())
			.append("Updateby",getUpdateby())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SysResource == false) return false;
		if(this == obj) return true;
		SysResource other = (SysResource)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

