package com.ylw.entity.job;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ylw.entity.IdEntity;
import com.ylw.util.DateConvertUtils;

/**
 * @author Jack
 * @version 1.0.0
 * @since 1.0.0
 * @date 2017-7-12 15:09:32 
 */

@Entity
@Table(name = "site_interview_schedule")
public class InterviewSchedule extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "InterviewSchedule";
	public static final String ALIAS_ID = "主键Id";
	public static final String ALIAS_DATA_CODE = "数据CODE";
	public static final String ALIAS_BATCH_CODE = "批次编号(yyyyMMdd+branchId)";
	public static final String ALIAS_YL_COMPANY_ID = "优蓝网企业id";
	public static final String ALIAS_YL_JOB_BASE_ID = "优蓝网岗位id";
	public static final String ALIAS_COMPANY_NAME = "企业名称";
	public static final String ALIAS_COMPANY_LOGO = "企业Logo";
	public static final String ALIAS_COMPANY_ADDRESS = "企业地址";
	public static final String ALIAS_START_TIME = "开始时间";
	public static final String ALIAS_END_TIME = "结束时间";
	public static final String ALIAS_JOB_ID = "岗位id";
	public static final String ALIAS_JOB_NAME = "岗位名称";
	public static final String ALIAS_JOB_DESC = "岗位描述";
	public static final String ALIAS_JOB_SALARY = "岗位薪资";
	public static final String ALIAS_BRANCH_ID = "所属分站id";
	public static final String ALIAS_SCHEDULE_TYPE = "排期类型";
	public static final String ALIAS_DATE_SCHEDULE = "排期日期";
	public static final String ALIAS_SORTING = "排序";
	public static final String ALIAS_SYN_TIME = "同步时间";
	public static final String ALIAS_SYN_STATUS = "同步状态[-1删除/0同步/1编辑]";
	
	//date formats
	public static final String FORMAT_START_TIME = DATE_FORMAT;
	public static final String FORMAT_END_TIME = DATE_FORMAT;
	public static final String FORMAT_DATE_SCHEDULE = DATE_FORMAT;
	public static final String FORMAT_SYN_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.String dataCode;
	private java.lang.String batchCode;
	private java.lang.Integer ylCompanyId;
	private java.lang.Integer ylJobBaseId;
	private java.lang.String companyName;
	private java.lang.String companyLogo;
	private java.lang.String companyAddress;
	private java.util.Date startTime;
	private java.util.Date endTime;
	private java.lang.Integer jobId;
	private java.lang.String jobName;
	private java.lang.String jobDesc;
	private java.lang.String jobSalary;
	private java.lang.Integer branchId;
	private Integer scheduleType;
	private java.util.Date dateSchedule;
	private java.lang.Long sorting;
	private java.util.Date synTime;
	private Integer synStatus;
	//columns END


	public InterviewSchedule(){
	}

	public InterviewSchedule(
		java.lang.Integer id
	){
		this.id = id;
	}

	
	
	
	@Column(name = "data_code", unique = true, nullable = false, insertable = true, updatable = true, length = 120)
	public java.lang.String getDataCode() {
		return this.dataCode;
	}
	
	public void setDataCode(java.lang.String value) {
		this.dataCode = value;
	}
	
	@Column(name = "batch_code", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public java.lang.String getBatchCode() {
		return this.batchCode;
	}
	
	public void setBatchCode(java.lang.String value) {
		this.batchCode = value;
	}
	
	@Column(name = "yl_company_id", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getYlCompanyId() {
		return this.ylCompanyId;
	}
	
	public void setYlCompanyId(java.lang.Integer value) {
		this.ylCompanyId = value;
	}
	
	@Column(name = "yl_job_base_id", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getYlJobBaseId() {
		return this.ylJobBaseId;
	}
	
	public void setYlJobBaseId(java.lang.Integer value) {
		this.ylJobBaseId = value;
	}
	
	@Column(name = "company_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getCompanyName() {
		return this.companyName;
	}
	
	public void setCompanyName(java.lang.String value) {
		this.companyName = value;
	}
	
	@Column(name = "company_logo", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getCompanyLogo() {
		return this.companyLogo;
	}
	
	public void setCompanyLogo(java.lang.String value) {
		this.companyLogo = value;
	}
	
	@Column(name = "company_address", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getCompanyAddress() {
		return this.companyAddress;
	}
	
	public void setCompanyAddress(java.lang.String value) {
		this.companyAddress = value;
	}
	
	@Transient
	public String getStartTimeString() {
		return DateConvertUtils.format(getStartTime(), FORMAT_START_TIME);
	}
	public void setStartTimeString(String value) {
		setStartTime(DateConvertUtils.parse(value, FORMAT_START_TIME,java.util.Date.class));
	}
	
	@Column(name = "start_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(java.util.Date value) {
		this.startTime = value;
	}
	
	@Transient
	public String getEndTimeString() {
		return DateConvertUtils.format(getEndTime(), FORMAT_END_TIME);
	}
	public void setEndTimeString(String value) {
		setEndTime(DateConvertUtils.parse(value, FORMAT_END_TIME,java.util.Date.class));
	}
	
	@Column(name = "end_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(java.util.Date value) {
		this.endTime = value;
	}
	
	@Column(name = "job_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getJobId() {
		return this.jobId;
	}
	
	public void setJobId(java.lang.Integer value) {
		this.jobId = value;
	}
	
	@Column(name = "job_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getJobName() {
		return this.jobName;
	}
	
	public void setJobName(java.lang.String value) {
		this.jobName = value;
	}
	
	@Column(name = "job_desc", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getJobDesc() {
		return this.jobDesc;
	}
	
	public void setJobDesc(java.lang.String value) {
		this.jobDesc = value;
	}
	
	@Column(name = "job_salary", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getJobSalary() {
		return this.jobSalary;
	}
	
	public void setJobSalary(java.lang.String value) {
		this.jobSalary = value;
	}
	
	@Column(name = "branch_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getBranchId() {
		return this.branchId;
	}
	
	public void setBranchId(java.lang.Integer value) {
		this.branchId = value;
	}
	
	@Column(name = "schedule_type", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getScheduleType() {
		return this.scheduleType;
	}
	
	public void setScheduleType(Integer value) {
		this.scheduleType = value;
	}
	
	@Transient
	public String getDateScheduleString() {
		return DateConvertUtils.format(getDateSchedule(), FORMAT_DATE_SCHEDULE);
	}
	public void setDateScheduleString(String value) {
		setDateSchedule(DateConvertUtils.parse(value, FORMAT_DATE_SCHEDULE,java.util.Date.class));
	}
	
	@Column(name = "date_schedule", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getDateSchedule() {
		return this.dateSchedule;
	}
	
	public void setDateSchedule(java.util.Date value) {
		this.dateSchedule = value;
	}
	
	@Column(name = "sorting", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getSorting() {
		return this.sorting;
	}
	
	public void setSorting(java.lang.Long value) {
		this.sorting = value;
	}
	
	@Transient
	public String getSynTimeString() {
		return DateConvertUtils.format(getSynTime(), FORMAT_SYN_TIME);
	}
	public void setSynTimeString(String value) {
		setSynTime(DateConvertUtils.parse(value, FORMAT_SYN_TIME,java.util.Date.class));
	}
	
	@Column(name = "syn_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getSynTime() {
		return this.synTime;
	}
	
	public void setSynTime(java.util.Date value) {
		this.synTime = value;
	}
	
	@Column(name = "syn_status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getSynStatus() {
		return this.synStatus;
	}
	
	public void setSynStatus(Integer value) {
		this.synStatus = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("DataCode",getDataCode())
			.append("BatchCode",getBatchCode())
			.append("YlCompanyId",getYlCompanyId())
			.append("YlJobBaseId",getYlJobBaseId())
			.append("CompanyName",getCompanyName())
			.append("CompanyLogo",getCompanyLogo())
			.append("CompanyAddress",getCompanyAddress())
			.append("StartTime",getStartTime())
			.append("EndTime",getEndTime())
			.append("JobId",getJobId())
			.append("JobName",getJobName())
			.append("JobDesc",getJobDesc())
			.append("JobSalary",getJobSalary())
			.append("BranchId",getBranchId())
			.append("ScheduleType",getScheduleType())
			.append("DateSchedule",getDateSchedule())
			.append("Sorting",getSorting())
			.append("SynTime",getSynTime())
			.append("SynStatus",getSynStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof InterviewSchedule == false) return false;
		if(this == obj) return true;
		InterviewSchedule other = (InterviewSchedule)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

