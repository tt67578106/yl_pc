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
@Table(name = "site_recommend_position")
public class RecommendPosition extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "RecommendPosition";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CODE = "唯一code";
	public static final String ALIAS_PLATFORM = "平台";
	public static final String ALIAS_PAGE = "所在页面";
	public static final String ALIAS_POSITION = "位置";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_CAPACITY = "容量";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_CREATE_USER_ID = "createUserId";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String ALIAS_UPDATE_USER_ID = "updateUserId";
	public static final String ALIAS_IS_DELETE = "1:正常；2:删除";
	public static final String ALIAS_SKETCH_MAP_IMG_ID = "示意图";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.String code;
	private java.lang.String platform;
	private java.lang.String page;
	private java.lang.String position;
	private java.lang.String remark;
	private java.lang.Integer capacity;
	private java.util.Date createTime;
	private java.lang.Integer createUserId;
	private java.util.Date updateTime;
	private java.lang.Integer updateUserId;
	private Integer isDelete;
	private java.lang.Integer sketchMapImgId;
	//columns END


	public RecommendPosition(){
	}

	@Column(name = "code", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	@Column(name = "platform", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getPlatform() {
		return this.platform;
	}
	
	public void setPlatform(java.lang.String value) {
		this.platform = value;
	}
	
	@Column(name = "page", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getPage() {
		return this.page;
	}
	
	public void setPage(java.lang.String value) {
		this.page = value;
	}
	
	@Column(name = "position", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getPosition() {
		return this.position;
	}
	
	public void setPosition(java.lang.String value) {
		this.position = value;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	@Column(name = "capacity", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCapacity() {
		return this.capacity;
	}
	
	public void setCapacity(java.lang.Integer value) {
		this.capacity = value;
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
	
	@Column(name = "create_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCreateUserId() {
		return this.createUserId;
	}
	
	public void setCreateUserId(java.lang.Integer value) {
		this.createUserId = value;
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
	
	@Column(name = "update_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUpdateUserId() {
		return this.updateUserId;
	}
	
	public void setUpdateUserId(java.lang.Integer value) {
		this.updateUserId = value;
	}
	
	@Column(name = "is_delete", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}
	@Column(name = "sketch_map_img_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSketchMapImgId() {
		return this.sketchMapImgId;
	}
	
	public void setSketchMapImgId(java.lang.Integer value) {
		this.sketchMapImgId = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Code",getCode())
			.append("Platform",getPlatform())
			.append("Page",getPage())
			.append("Position",getPosition())
			.append("Remark",getRemark())
			.append("Capacity",getCapacity())
			.append("CreateTime",getCreateTime())
			.append("CreateUserId",getCreateUserId())
			.append("UpdateTime",getUpdateTime())
			.append("UpdateUserId",getUpdateUserId())
			.append("IsDelete",getIsDelete())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RecommendPosition == false) return false;
		if(this == obj) return true;
		RecommendPosition other = (RecommendPosition)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

