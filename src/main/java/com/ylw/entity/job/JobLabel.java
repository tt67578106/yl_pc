package com.ylw.entity.job;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "site_job_label")
public class JobLabel extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "JobLabel";
	public static final String ALIAS_ID = "主键ID";
	public static final String ALIAS_JOBID = "职位ID";
	public static final String ALIAS_LABELID = "标签ID";
	
	//date formats
	

	//columns START
	private JobBase jobBase;
	private Labels label;
	//columns END


	public JobLabel(){
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jobid")
	public JobBase getJobBase() {
		return jobBase;
	}
	public void setJobBase(JobBase jobBase) {
		this.jobBase = jobBase;
	}
	
	@OneToOne
	@JoinColumn(name = "labelid",nullable=true)
	public Labels getLabel() {
		return label;
	}

	public void setLabel(Labels label) {
		this.label = label;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Jobid",getJobBase().getId())
			.append("Labelid",getLabel().getId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof JobLabel == false) return false;
		if(this == obj) return true;
		JobLabel other = (JobLabel)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

