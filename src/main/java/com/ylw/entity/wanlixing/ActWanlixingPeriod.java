package com.ylw.entity.wanlixing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.ylw.entity.IdEntity;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "act_wanlixing_period")
public class ActWanlixingPeriod extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ActWanlixingPeriod";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TITLE = "主标题";
	public static final String ALIAS_SUB_TITLE = "副标题";
	public static final String ALIAS_CITY_ID = "所属城市";
	public static final String ALIAS_ADDRESS = "详细地址";
	public static final String ALIAS_PART_NUMBER = "参与人数";
	public static final String ALIAS_IS_PUBLISH = "是否发布";
	public static final String ALIAS_PUBLISH_TIME = "发布时间";
	public static final String ALIAS_START_DATE = "开始日期";
	public static final String ALIAS_END_DATE = "结束日期";
	public static final String ALIAS_SOURCE = "来源";
	public static final String ALIAS_INTRODUCTION = "简介";
	public static final String ALIAS_MAIN_IMAGE = "主图";
	public static final String ALIAS_PREVIEW_IMAGE = "预览图";
	public static final String ALIAS_CREATE_USER_ID = "createUserId";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_UPDATE_USER_ID = "updateUserId";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	
	//date formats
	public static final String FORMAT_PUBLISH_TIME = DATE_FORMAT;
	public static final String FORMAT_START_DATE = DATE_FORMAT;
	public static final String FORMAT_END_DATE = DATE_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.String title;
	private java.lang.String subTitle;
	private java.lang.Integer cityId;
	private java.lang.Integer provinceId;
	private java.lang.String address;
	private java.lang.Integer partNumber;
	private Integer isPublish;
	private java.util.Date publishTime;
	private java.util.Date startDate;
	private java.util.Date endDate;
	private java.lang.String source;
	private java.lang.String introduction;
	private java.lang.String mainImage;
	private java.lang.String previewImage;
	private java.lang.Integer createUserId;
	private java.util.Date createTime;
	private java.lang.Integer updateUserId;
	private java.util.Date updateTime;
	//columns END


	public ActWanlixingPeriod(){
	}

	public ActWanlixingPeriod(
		java.lang.Integer id
	){
		this.id = id;
	}

	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "sub_title", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getSubTitle() {
		return this.subTitle;
	}
	
	public void setSubTitle(java.lang.String value) {
		this.subTitle = value;
	}
	
	@Column(name = "city_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCityId() {
		return this.cityId;
	}
	
	public void setCityId(java.lang.Integer value) {
		this.cityId = value;
	}
	
	@Column(name = "province_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(java.lang.Integer provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	@Column(name = "part_number", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPartNumber() {
		return this.partNumber;
	}
	
	public void setPartNumber(java.lang.Integer value) {
		this.partNumber = value;
	}
	
	@Column(name = "is_publish", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsPublish() {
		return this.isPublish;
	}
	
	public void setIsPublish(Integer value) {
		this.isPublish = value;
	}
	
	@Transient
	public String getPublishTimeString() {
		return DateConvertUtils.format(getPublishTime(), FORMAT_PUBLISH_TIME);
	}
	public void setPublishTimeString(String value) {
		setPublishTime(DateConvertUtils.parse(value, FORMAT_PUBLISH_TIME,java.util.Date.class));
	}
	
	@Column(name = "publish_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getPublishTime() {
		return this.publishTime;
	}
	
	public void setPublishTime(java.util.Date value) {
		this.publishTime = value;
	}
	
	@Transient
	public String getStartDateString() {
		return DateConvertUtils.format(getStartDate(), FORMAT_START_DATE);
	}
	public void setStartDateString(String value) {
		setStartDate(DateConvertUtils.parse(value, FORMAT_START_DATE,java.util.Date.class));
	}
	
	@Column(name = "start_date", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	
	public void setStartDate(java.util.Date value) {
		this.startDate = value;
	}
	
	@Transient
	public String getEndDateString() {
		return DateConvertUtils.format(getEndDate(), FORMAT_END_DATE);
	}
	public void setEndDateString(String value) {
		setEndDate(DateConvertUtils.parse(value, FORMAT_END_DATE,java.util.Date.class));
	}
	
	@Column(name = "end_date", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getEndDate() {
		return this.endDate;
	}
	
	public void setEndDate(java.util.Date value) {
		this.endDate = value;
	}
	
	@Column(name = "source", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getSource() {
		return this.source;
	}
	
	public void setSource(java.lang.String value) {
		this.source = value;
	}
	
	@Column(name = "introduction", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getIntroduction() {
		return this.introduction;
	}
	
	public void setIntroduction(java.lang.String value) {
		this.introduction = value;
	}
	
	@Column(name = "main_image", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getMainImage() {
		return this.mainImage;
	}
	
	public void setMainImage(java.lang.String value) {
		this.mainImage = value;
	}
	
	@Column(name = "preview_image", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getPreviewImage() {
		return this.previewImage;
	}
	
	public void setPreviewImage(java.lang.String value) {
		this.previewImage = value;
	}
	
	@Column(name = "create_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCreateUserId() {
		return this.createUserId;
	}
	
	public void setCreateUserId(java.lang.Integer value) {
		this.createUserId = value;
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
	
	@Column(name = "update_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUpdateUserId() {
		return this.updateUserId;
	}
	
	public void setUpdateUserId(java.lang.Integer value) {
		this.updateUserId = value;
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
	

	
	
	@Override
	public String toString() {
		return "ActWanlixingPeriod [title=" + title + ", subTitle=" + subTitle
				+ ", cityId=" + cityId + ", provinceId=" + provinceId
				+ ", address=" + address + ", partNumber=" + partNumber
				+ ", isPublish=" + isPublish + ", publishTime=" + publishTime
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", source=" + source + ", introduction=" + introduction
				+ ", mainImage=" + mainImage + ", previewImage=" + previewImage
				+ ", createUserId=" + createUserId + ", createTime="
				+ createTime + ", updateUserId=" + updateUserId
				+ ", updateTime=" + updateTime + "]";
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ActWanlixingPeriod == false) return false;
		if(this == obj) return true;
		ActWanlixingPeriod other = (ActWanlixingPeriod)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

