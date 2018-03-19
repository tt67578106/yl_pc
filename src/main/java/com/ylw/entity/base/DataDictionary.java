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
@Table(name = "site_data_dictionary")
public class DataDictionary extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "DataDictionary";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_CODE = "值";
	public static final String ALIAS_TYPEID = "类型ID";
	public static final String ALIAS_SORT_ORDER = "排序";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_CREATETIME = "创建时间";
	
	//date formats
	public static final String FORMAT_CREATETIME = DATE_FORMAT;
	

	//columns START
	private java.lang.String name;
	private java.lang.String code;
	private java.lang.Integer typeid;
	private java.lang.Integer sortOrder;
	private java.lang.Integer status;
	private java.util.Date createtime;
	//columns END


	public DataDictionary(){
	}

	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	@Column(name = "code", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	@Column(name = "typeid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getTypeid() {
		return this.typeid;
	}
	
	public void setTypeid(java.lang.Integer value) {
		this.typeid = value;
	}
	
	@Column(name = "sort_order", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSortOrder() {
		return this.sortOrder;
	}
	
	public void setSortOrder(java.lang.Integer value) {
		this.sortOrder = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	@Transient
	public String getCreatetimeString() {
		return DateConvertUtils.format(getCreatetime(), FORMAT_CREATETIME);
	}
	public void setCreatetimeString(String value) {
		setCreatetime(DateConvertUtils.parse(value, FORMAT_CREATETIME,java.util.Date.class));
	}
	
	@Column(name = "createtime", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Code",getCode())
			.append("Typeid",getTypeid())
			.append("SortOrder",getSortOrder())
			.append("Status",getStatus())
			.append("Createtime",getCreatetime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DataDictionary == false) return false;
		if(this == obj) return true;
		DataDictionary other = (DataDictionary)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

