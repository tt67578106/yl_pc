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
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ylw.entity.IdEntity;
import com.ylw.entity.job.JobBase;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_recommend_job")
public class RecommendJob extends IdEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "RecommendJob";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TITLE = "岗位标题";
	public static final String ALIAS_JOB_ID = "岗位id";
	public static final String ALIAS_RECOMMENDPOSITIONCODE = "推荐位置id";
	public static final String ALIAS_CITY_ID = "城市id";
	public static final String ALIAS_IS_URGENCY = "紧急度(1:普通;2:紧急;3:特急)";
	public static final String ALIAS_IS_PUBLISH = "是否发布：1未发布、2已发布";
	public static final String ALIAS_PUBLISH_TIME = "发布时间";
	public static final String ALIAS_OFFLINE_TIME = "下线时间";
	public static final String ALIAS_REMARK = "备注";
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
	private JobBase job;
	private java.lang.String recommendPositionCode;
	private Branch branch;
	private Integer isUrgency;
	private Integer isPublish;
	private java.util.Date publishTime;
	private java.util.Date offlineTime;
	private java.lang.String remark;
	private java.lang.Integer sorting;
	private java.util.Date createTime;
	private java.lang.Integer createUserId;
	private java.util.Date updateTime;
	private java.lang.Integer updateUserId;

	// columns END

	public RecommendJob() {
	}

	@Column(name = "recommend_position_code", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getRecommendPositionCode() {
		return this.recommendPositionCode;
	}

	public void setRecommendPositionCode(java.lang.String value) {
		this.recommendPositionCode = value;
	}

	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public java.lang.String getTitle() {
		return this.title;
	}

	public void setTitle(java.lang.String value) {
		this.title = value;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "job_id")
	public JobBase getJob() {
		return job;
	}

	public void setJob(JobBase job) {
		this.job = job;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "branch_id")
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@Column(name = "is_urgency", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsUrgency() {
		return this.isUrgency;
	}

	public void setIsUrgency(Integer value) {
		this.isUrgency = value;
	}

	@Column(name = "is_publish", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
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

	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
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

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("Id", getId()).append("Title", getTitle()).append("JobId", getJob()).append("RecommendPositionId", getRecommendPositionCode()).append("branchId", getBranch()).append("IsUrgency", getIsUrgency()).append("IsPublish", getIsPublish()).append("PublishTime", getPublishTime()).append("OfflineTime", getOfflineTime()).append("Remark", getRemark()).append("Sorting", getSorting()).append("CreateTime", getCreateTime()).append("CreateUserId", getCreateUserId()).append("UpdateTime", getUpdateTime()).append("UpdateUserId", getUpdateUserId()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof RecommendJob == false)
			return false;
		if (this == obj)
			return true;
		RecommendJob other = (RecommendJob) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

}
