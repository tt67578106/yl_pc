package com.ylw.entity.base;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylw.entity.IdEntity;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_recommend_ad")
@JsonIgnoreProperties({ "createTime", "createUserId", "updateTime", "updateUserId" })
public class RecommendAd extends IdEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "RecommendAd";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_SUBTITLE = "副标题";
	public static final String ALIAS_IMG_ID = "主图id";
	public static final String ALIAS_IMG_ALT = "主图描述";
	public static final String ALIAS_STYLE = "特殊样式";
	public static final String ALIAS_LINK = "链接地址";
	public static final String ALIAS_RECOMMEND_POSITION_CODE = "广告类型(位置_code)";
	public static final String ALIAS_CITY_ID = "城市ID";
	public static final String ALIAS_IS_PUBLISH = "是否发布：1未发布、2已发布";
	public static final String ALIAS_PUBLISH_TIME = "上线时间";
	public static final String ALIAS_OFFLINE_TIME = "下线时间";
	public static final String ALIAS_SORTING = "排序";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_CREATE_USER_ID = "createUserId";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String ALIAS_UPDATE_USER_ID = "updateUserId";

	// date formats
	public static final String FORMAT_PUBLISH_TIME = DATE_FORMAT;
	public static final String FORMAT_OFFLINE_TIME = DATE_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;

	// columns START
	private java.lang.String title;
	private java.lang.String subtitle;
	private Image img;
	private java.lang.String imgAlt;
	private java.lang.String style;
	private java.lang.String link;
	private java.lang.String recommendPositionCode;
	private java.lang.Integer branchId;
	private Integer isPublish;
	private java.util.Date publishTime;
	private java.util.Date offlineTime;
	private java.lang.Integer sorting;
	private java.util.Date createTime;
	private java.lang.Integer createUserId;
	private java.util.Date updateTime;
	private java.lang.Integer updateUserId;

	// columns END

	public RecommendAd() {
	}

	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getTitle() {
		return this.title;
	}

	public void setTitle(java.lang.String value) {
		this.title = value;
	}

	@Column(name = "subtitle", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getSubtitle() {
		return this.subtitle;
	}

	public void setSubtitle(java.lang.String value) {
		this.subtitle = value;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "img_id")
	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	@Column(name = "img_alt", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getImgAlt() {
		return this.imgAlt;
	}

	public void setImgAlt(java.lang.String value) {
		this.imgAlt = value;
	}

	@Column(name = "style", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getStyle() {
		return this.style;
	}

	public void setStyle(java.lang.String value) {
		this.style = value;
	}

	@Column(name = "link", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getLink() {
		return this.link;
	}

	public void setLink(java.lang.String value) {
		this.link = value;
	}

	@Column(name = "recommend_position_code", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getRecommendPositionCode() {
		return this.recommendPositionCode;
	}

	public void setRecommendPositionCode(java.lang.String value) {
		this.recommendPositionCode = value;
	}

	@Column(name = "branch_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getBranchId() {
		return this.branchId;
	}

	public void setBranchId(java.lang.Integer value) {
		this.branchId = value;
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
		setPublishTime(DateConvertUtils.parse(value, FORMAT_PUBLISH_TIME, java.util.Date.class));
	}

	@Column(name = "publish_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(java.util.Date value) {
		this.publishTime = value;
	}

	@Transient
	public String getOfflineTimeString() {
		return DateConvertUtils.format(getOfflineTime(), FORMAT_OFFLINE_TIME);
	}

	public void setOfflineTimeString(String value) {
		setOfflineTime(DateConvertUtils.parse(value, FORMAT_OFFLINE_TIME, java.util.Date.class));
	}

	@Column(name = "offline_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getOfflineTime() {
		return this.offlineTime;
	}

	public void setOfflineTime(java.util.Date value) {
		this.offlineTime = value;
	}

	@Column(name = "sorting", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSorting() {
		return this.sorting;
	}

	public void setSorting(java.lang.Integer value) {
		this.sorting = value;
	}

	@Transient
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}

	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME, java.util.Date.class));
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
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME, java.util.Date.class));
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

	@Override
	public String toString() {
		return "RecommendAd [title=" + title + ", subtitle=" + subtitle
				+ ", img=" + img + ", imgAlt=" + imgAlt + ", style=" + style
				+ ", link=" + link + ", recommendPositionCode="
				+ recommendPositionCode + ", branchId=" + branchId
				+ ", isPublish=" + isPublish + ", publishTime=" + publishTime
				+ ", offlineTime=" + offlineTime + ", sorting=" + sorting
				+ ", createTime=" + createTime + ", createUserId="
				+ createUserId + ", updateTime=" + updateTime
				+ ", updateUserId=" + updateUserId + "]";
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof RecommendAd == false)
			return false;
		if (this == obj)
			return true;
		RecommendAd other = (RecommendAd) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}
}
