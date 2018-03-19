package com.ylw.entity.enterprise;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ylw.entity.IdEntity;
import com.ylw.entity.base.Resume;
import com.ylw.entity.job.JobBase;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_company_resume_box")
public class CompanyResumeBox extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SiteCompanyResumeBox";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_COMPANY_ID = "企业id";
	public static final String ALIAS_RESUME_ID = "简历id";
	public static final String ALIAS_JOB_ID = "岗位";
	
	//date formats
	

	//columns START
	private java.lang.Integer companyId;
	private JobBase jobBase;
	private Resume resume;
	//columns END


	public CompanyResumeBox(){
	}
	
	@Column(name = "company_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCompanyId() {
		return this.companyId;
	}
	
	public void setCompanyId(java.lang.Integer value) {
		this.companyId = value;
	}
	
	@OneToOne(optional=false,cascade=CascadeType.REFRESH)
	@JoinColumn(name="resume_id",referencedColumnName="id")
	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}
	
	@OneToOne(optional=false,cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="job_id",referencedColumnName="id",nullable=true)
	public JobBase getJobBase() {
		return jobBase;
	}

	public void setJobBase(JobBase jobBase) {
		this.jobBase = jobBase;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("CompanyId",getCompanyId())
			.append("Resume",getResume())
			.append("JobBase",getJobBase())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CompanyResumeBox == false) return false;
		if(this == obj) return true;
		CompanyResumeBox other = (CompanyResumeBox)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

