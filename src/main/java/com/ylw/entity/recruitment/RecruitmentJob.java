package com.ylw.entity.recruitment;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ylw.entity.IdEntity;
import com.ylw.entity.job.JobBase;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_recruitment_job")
public class RecruitmentJob extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "RecruitmentJob";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_RECRUITMENT_ID = "招聘会id";
	public static final String ALIAS_JOB_ID = "岗位id";
	public static final String ALIAS_SORT = "排序";
	public static final String ALIAS_JOB_TITLE="岗位标题";
	public static final String ALIAS_JOB_DESCRIPTION = "岗位描述";
	public static final String ALIAS_BRANCH_ID = "分站id";
	
	//date formats
	

	//columns START
	private java.lang.Integer recruitmentId;
	private JobBase job;
	private java.lang.Integer sort;
	private Integer branchId;
	//columns END


	public RecruitmentJob(){
	}

	public RecruitmentJob(
		java.lang.Integer id
	){
		this.id = id;
	}

	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "job_id")
	public JobBase getJob() {
		return job;
	}

	public void setJob(JobBase job) {
		this.job = job;
	}
	
	@Column(name = "recruitment_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getRecruitmentId() {
		return this.recruitmentId;
	}
	
	public void setRecruitmentId(java.lang.Integer value) {
		this.recruitmentId = value;
	}
	
	@Column(name = "sort", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSort() {
		return this.sort;
	}
	
	public void setSort(java.lang.Integer value) {
		this.sort = value;
	}
	
	@Column(name = "branch_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("RecruitmentId",getRecruitmentId())
			.append("Sort",getSort())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RecruitmentJob == false) return false;
		if(this == obj) return true;
		RecruitmentJob other = (RecruitmentJob)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

