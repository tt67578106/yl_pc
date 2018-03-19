package com.ylw.entity.recruitment;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylw.entity.IdEntity;
import com.ylw.entity.job.JobBase;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_recruitment_job_desc")
@JsonIgnoreProperties({"jobBase"})
public class RecruitmentJobDesc extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "RecruitmentJobDesc";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JOB_ID = "jobId";
	public static final String ALIAS_JOB_TITLE = "jobTitle";
	public static final String ALIAS_JOB_DESCRIPTION = "jobDescription";
	
	//date formats
	

	//columns START
	private JobBase jobBase;
	private java.lang.String jobTitle;
	private java.lang.String jobDescription;
	//columns END


	public RecruitmentJobDesc(){
	}

	public RecruitmentJobDesc(
		java.lang.Integer id
	){
		this.id = id;
	}
	
	@OneToOne(optional=false,cascade=CascadeType.REFRESH)
	@JoinColumn(name="job_id",referencedColumnName="id")
	public JobBase getJobBase() {
		return jobBase;
	}

	public void setJobBase(JobBase jobBase) {
		this.jobBase = jobBase;
	}
	
	@Column(name = "job_title", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getJobTitle() {
		return this.jobTitle;
	}
	
	public void setJobTitle(java.lang.String value) {
		this.jobTitle = value;
	}
				
	@Column(name = "job_description", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getJobDescription() {
		return this.jobDescription;
	}
	
	public void setJobDescription(java.lang.String value) {
		this.jobDescription = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("JobTitle",getJobTitle())
			.append("JobDescription",getJobDescription())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RecruitmentJobDesc == false) return false;
		if(this == obj) return true;
		RecruitmentJobDesc other = (RecruitmentJobDesc)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

