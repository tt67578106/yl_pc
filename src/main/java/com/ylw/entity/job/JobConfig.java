package com.ylw.entity.job;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "site_job_config")
public class JobConfig extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "JobConfig";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JOB_BASE_ID = "岗位id";
	public static final String ALIAS_IS_PUBLISH = "是否发布0:未发布1:已发布";
	public static final String ALIAS_IS_RECRUITMENT = "是否在招0:在招1:停招";
	public static final String ALIAS_ACT_RECOMMEND_ID = "活动表ID";
	public static final String ALIAS_IS_URGENCY = "紧急度（1 普通、2 紧急、3 特急）";
	public static final String ALIAS_IS_DEL="是否删除";
	//date formats
	

	//columns START
	private JobBase jobBase;
	private Integer isPublish;
	private Integer isRecruitment;
	private java.lang.Integer actRecommendId;
	private java.lang.Integer isUrgency;
	private java.lang.Integer isDel;
	//columns END


	public JobConfig(){
	}
	
	@OneToOne(optional=false,cascade=CascadeType.REFRESH)
	@JoinColumn(name="job_base_id",referencedColumnName="id")
	public JobBase getJobBase() {
		return jobBase;
	}

	public void setJobBase(JobBase jobBase) {
		this.jobBase = jobBase;
	}

	@Column(name = "is_publish", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsPublish() {
		return this.isPublish;
	}
	
	public void setIsPublish(Integer value) {
		this.isPublish = value;
	}
	
	@Column(name = "is_recruitment", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsRecruitment() {
		return this.isRecruitment;
	}
	
	public void setIsRecruitment(Integer value) {
		this.isRecruitment = value;
	}
	
	@Column(name = "act_recommend_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getActRecommendId() {
		return this.actRecommendId;
	}
	
	public void setActRecommendId(java.lang.Integer value) {
		this.actRecommendId = value;
	}
	
	@Column(name = "is_urgency", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsUrgency() {
		return this.isUrgency;
	}
	
	public void setIsUrgency(java.lang.Integer value) {
		this.isUrgency = value;
	}
	
	@Column(name = "is_del", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(java.lang.Integer isDel) {
		this.isDel = isDel;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
//			.append("JobBaseId",getJobBaseId())
			.append("IsPublish",getIsPublish())
			.append("IsRecruitment",getIsRecruitment())
			.append("ActRecommendId",getActRecommendId())
			.append("IsUrgency",getIsUrgency())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof JobConfig == false) return false;
		if(this == obj) return true;
		JobConfig other = (JobConfig)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}
