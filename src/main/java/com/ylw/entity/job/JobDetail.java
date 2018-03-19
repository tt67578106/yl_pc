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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylw.entity.IdEntity;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_job_detail")
@JsonIgnoreProperties({"jobBase"})
public class JobDetail extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "JobDetail";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JOB_BASE_ID = "岗位id";
	public static final String ALIAS_WORK_EXP_FROM = "工作经验最小限制";
	public static final String ALIAS_WORK_EXP_TO = "工作经验最大限制";
	public static final String ALIAS_SALARYFROM = "薪资开始";
	public static final String ALIAS_SALARYTO = "薪资结束";
	public static final String ALIAS_SALARYDESC = "薪资描述";
	public static final String ALIAS_SHOW_APPLY_COUNT = "显示报名数量";
	public static final String ALIAS_SHOW_VIEW_COUNT = "显示浏览次数";
	public static final String ALIAS_WORKDESC = "工作描述";
	public static final String ALIAS_EDUCATION = "学历要求";
	public static final String ALIAS_GENDER = "性别要求";
	public static final String ALIAS_AGEFROM = "年龄范围-开始";
	public static final String ALIAS_AGETO = "年龄范围-结束";
	public static final String ALIAS_DEMANDDESC = "工作要求";
	public static final String ALIAS_MEALSDESC = "吃住情况";
	public static final String ALIAS_WELFAREDESC = "福利情况";
	
	//date formats
	

	//columns START
	private JobBase jobBase;
	private java.lang.Integer workExpFrom;
	private java.lang.Integer workExpTo;
	private java.lang.Integer salaryfrom;
	private java.lang.Integer salaryto;
	private java.lang.String salarydesc;
	private java.lang.Integer showApplyCount;
	private java.lang.Integer showViewCount;
	private java.lang.String workdesc;
	private java.lang.Integer education;
	private java.lang.Integer gender;
	private java.lang.Integer agefrom;
	private java.lang.Integer ageto;
	private java.lang.String demanddesc;
	private java.lang.String mealsdesc;
	private java.lang.String welfaredesc;
	
	

	//工资情况字段
	private java.lang.String basicSalary;
	private java.lang.String overtimeSalary;
	private java.lang.String tryoutSalary;
	private java.lang.String workerSalary;
	private java.lang.String attendanceSalary;
	private java.lang.String floatSalary;
	//columns END

	public JobDetail(){
	}

	@OneToOne(optional=false,cascade=CascadeType.REFRESH)
	@JoinColumn(name="job_base_id",referencedColumnName="id")
	public JobBase getJobBase() {
		return jobBase;
	}

	public void setJobBase(JobBase jobBase) {
		this.jobBase = jobBase;
	}
	
	@Column(name = "work_exp_from", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getWorkExpFrom() {
		return this.workExpFrom;
	}
	
	public void setWorkExpFrom(java.lang.Integer value) {
		this.workExpFrom = value;
	}
	
	@Column(name = "work_exp_to", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getWorkExpTo() {
		return this.workExpTo;
	}
	
	public void setWorkExpTo(java.lang.Integer value) {
		this.workExpTo = value;
	}
	
	@Column(name = "salaryfrom", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSalaryfrom() {
		return this.salaryfrom;
	}
	
	public void setSalaryfrom(java.lang.Integer value) {
		this.salaryfrom = value;
	}
	
	@Column(name = "salaryto", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSalaryto() {
		return this.salaryto;
	}
	
	public void setSalaryto(java.lang.Integer value) {
		this.salaryto = value;
	}
	
	@Column(name = "salarydesc", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getSalarydesc() {
		return this.salarydesc;
	}
	
	public void setSalarydesc(java.lang.String value) {
		this.salarydesc = value;
	}
	
	@Column(name = "show_apply_count", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getShowApplyCount() {
		return this.showApplyCount;
	}
	
	public void setShowApplyCount(java.lang.Integer value) {
		this.showApplyCount = value;
	}
	@Column(name = "basic_salary", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(java.lang.String basicSalary) {
		this.basicSalary = basicSalary;
	}
	@Column(name = "overtime_salary", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getOvertimeSalary() {
		return overtimeSalary;
	}

	public void setOvertimeSalary(java.lang.String overtimeSalary) {
		this.overtimeSalary = overtimeSalary;
	}
	@Column(name = "tryout_salary", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getTryoutSalary() {
		return tryoutSalary;
	}

	public void setTryoutSalary(java.lang.String tryoutSalary) {
		this.tryoutSalary = tryoutSalary;
	}
	@Column(name = "worker_salary", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getWorkerSalary() {
		return workerSalary;
	}

	public void setWorkerSalary(java.lang.String workerSalary) {
		this.workerSalary = workerSalary;
	}
	@Column(name = "attendance_salary", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getAttendanceSalary() {
		return attendanceSalary;
	}

	public void setAttendanceSalary(java.lang.String attendanceSalary) {
		this.attendanceSalary = attendanceSalary;
	}
	@Column(name = "float_salary", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getFloatSalary() {
		return floatSalary;
	}

	public void setFloatSalary(java.lang.String floatSalary) {
		this.floatSalary = floatSalary;
	}

	@Column(name = "show_view_count", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getShowViewCount() {
		return this.showViewCount;
	}
	
	public void setShowViewCount(java.lang.Integer value) {
		this.showViewCount = value;
	}
	
	@Column(name = "workdesc", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getWorkdesc() {
		return this.workdesc;
	}
	
	public void setWorkdesc(java.lang.String value) {
		this.workdesc = value;
	}
	
	@Column(name = "education", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getEducation() {
		return this.education;
	}
	
	public void setEducation(java.lang.Integer value) {
		this.education = value;
	}
	
	@Column(name = "gender", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getGender() {
		return this.gender;
	}
	
	public void setGender(java.lang.Integer value) {
		this.gender = value;
	}
	
	@Column(name = "agefrom", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAgefrom() {
		return this.agefrom;
	}
	
	public void setAgefrom(java.lang.Integer value) {
		this.agefrom = value;
	}
	
	@Column(name = "ageto", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAgeto() {
		return this.ageto;
	}
	
	public void setAgeto(java.lang.Integer value) {
		this.ageto = value;
	}
	
	@Column(name = "demanddesc", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getDemanddesc() {
		return this.demanddesc;
	}
	
	public void setDemanddesc(java.lang.String value) {
		this.demanddesc = value;
	}
	
	@Column(name = "mealsdesc", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getMealsdesc() {
		return this.mealsdesc;
	}
	
	public void setMealsdesc(java.lang.String value) {
		this.mealsdesc = value;
	}
	
	@Column(name = "welfaredesc", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getWelfaredesc() {
		return this.welfaredesc;
	}
	
	public void setWelfaredesc(java.lang.String value) {
		this.welfaredesc = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
//			.append("JobBaseId",getJobBaseId())
			.append("WorkExpFrom",getWorkExpFrom())
			.append("WorkExpTo",getWorkExpTo())
			.append("Salaryfrom",getSalaryfrom())
			.append("Salaryto",getSalaryto())
			.append("Salarydesc",getSalarydesc())
			.append("ShowApplyCount",getShowApplyCount())
			.append("ShowViewCount",getShowViewCount())
			.append("Workdesc",getWorkdesc())
			.append("Education",getEducation())
			.append("Gender",getGender())
			.append("Agefrom",getAgefrom())
			.append("Ageto",getAgeto())
			.append("Demanddesc",getDemanddesc())
			.append("Mealsdesc",getMealsdesc())
			.append("Welfaredesc",getWelfaredesc())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof JobDetail == false) return false;
		if(this == obj) return true;
		JobDetail other = (JobDetail)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}
