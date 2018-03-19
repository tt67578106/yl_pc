package com.ylw.entity.job;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ylw.entity.IdEntity;

/**
 * @author Nicolas.Cai
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_job_type")
public class JobType extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "JobType";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PARENT_ID = "parentId";
	public static final String ALIAS_JOB_NAME = "jobName";
	public static final String ALIAS_SORTING = "sorting";
	public static final String ALIAS_IS_HOT = "isHot";
	public static final String ALIAS_STATUS = "status";
	
	//date formats
	

	//columns START
	private java.lang.Integer parentId;
	private java.lang.String jobName;
	private java.lang.Integer sorting;
	private Integer isHot;
	private Integer status;
	private String thumbnialImage;
	//columns END


	public JobType(){
	}

	@Column(name = "parent_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getParentId() {
		return this.parentId;
	}
	
	public void setParentId(java.lang.Integer value) {
		this.parentId = value;
	}
	
	@Column(name = "job_name", unique = false, nullable = true, insertable = true, updatable = true, length = 60)
	public java.lang.String getJobName() {
		return this.jobName;
	}
	
	public void setJobName(java.lang.String value) {
		this.jobName = value;
	}
	
	@Column(name = "sorting", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSorting() {
		return this.sorting;
	}
	
	public void setSorting(java.lang.Integer value) {
		this.sorting = value;
	}
	
	@Column(name = "is_hot", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsHot() {
		return this.isHot;
	}
	
	public void setIsHot(Integer value) {
		this.isHot = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	
	@Column(name = "thumbnial_image", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public String getThumbnialImage() {
		return thumbnialImage;
	}

	public void setThumbnialImage(String thumbnialImage) {
		this.thumbnialImage = thumbnialImage;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ParentId",getParentId())
			.append("JobName",getJobName())
			.append("Sorting",getSorting())
			.append("IsHot",getIsHot())
			.append("Status",getStatus())
			.append("thumbnialImage",getThumbnialImage())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof JobType == false) return false;
		if(this == obj) return true;
		JobType other = (JobType)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

