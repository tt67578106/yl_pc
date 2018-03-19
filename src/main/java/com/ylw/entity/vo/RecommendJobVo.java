package com.ylw.entity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 工作推荐位显示bean  lucene 岗位显示属性
 * @author ghost
 *
 */
public class RecommendJobVo  implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private java.lang.String title;
	private Integer isUrgency;
	private java.lang.String remark;
	
	/**
	 * 相关岗位属性
	 */
	private java.lang.Integer jobId;
	private java.lang.String totalSalary;
	private java.lang.Integer applyCount;
	private java.lang.String jobName;
	private java.lang.String jobCityName;
	private java.lang.String imgPath;
	private String companyName;
	private String jobTitle;
	private Integer companyId;
	private String companyLogo;
	private String companyAbbreviation;
	private java.lang.Integer salaryFrom;
	private java.lang.Integer salaryTo;
	private Integer validation;
	private Date updateTime;
	private Date createTime;
	private Integer ageFrom;
	private Integer ageTo;
	private Integer education;
	private String jobLabel;
	private Integer gender;
	private String jobType;
	private Integer countyId;
	
	public RecommendJobVo() {
		super();
	}


	public RecommendJobVo(Integer id, String title, Integer isUrgency,
			String remark, Integer jobId, String totalSalary,
			Integer applyCount, String jobName, String jobCityName,
			String imgPath, String companyName, String jobTitle,
			Integer companyId, String companyLogo, Integer salaryFrom,
			Integer salaryTo, Integer validation, Date updateTime,
			Date createTime, Integer ageFrom, Integer ageTo, Integer education,
			String jobLabel,Integer gender,String jobType) {
		super();
		this.id = id;
		this.title = title;
		this.isUrgency = isUrgency;
		this.remark = remark;
		this.jobId = jobId;
		this.totalSalary = totalSalary;
		this.applyCount = applyCount;
		this.jobName = jobName;
		this.jobCityName = jobCityName;
		this.imgPath = imgPath;
		this.companyName = companyName;
		this.jobTitle = jobTitle;
		this.companyId = companyId;
		this.companyLogo = companyLogo;
		this.salaryFrom = salaryFrom;
		this.salaryTo = salaryTo;
		this.validation = validation;
		this.updateTime = updateTime;
		this.createTime = createTime;
		this.ageFrom = ageFrom;
		this.ageTo = ageTo;
		this.education = education;
		this.jobLabel = jobLabel;
		this.gender= gender;
		this.jobType=jobType;
	}
	
	
	public RecommendJobVo(Integer id, String title, Integer isUrgency,
			String remark, Integer jobId, String totalSalary,
			Integer applyCount, String jobName, String jobCityName,
			String imgPath, String companyName,String companyAbbreviation, String jobTitle,
			Integer companyId, String companyLogo, Integer salaryFrom,
			Integer salaryTo,String jobType,Integer countyId,String jobLabel) {
		super();
		this.id = id;
		this.title = title;
		this.isUrgency = isUrgency;
		this.remark = remark;
		this.jobId = jobId;
		this.totalSalary = totalSalary;
		this.applyCount = applyCount;
		this.jobName = jobName;
		this.jobCityName = jobCityName;
		this.imgPath = imgPath;
		this.companyName = companyName;
		this.companyAbbreviation=companyAbbreviation;
		this.jobTitle = jobTitle;
		this.companyId = companyId;
		this.companyLogo = companyLogo;
		this.salaryFrom = salaryFrom;
		this.salaryTo = salaryTo;
		this.jobType=jobType;
		this.countyId = countyId;
		this.jobLabel = jobLabel;
	}

	public java.lang.String getTotalSalary() {
		return totalSalary;
	}
	public void setTotalSalary(java.lang.String totalSalary) {
		this.totalSalary = totalSalary;
	}
	public java.lang.Integer getApplyCount() {
		return applyCount;
	}
	public void setApplyCount(java.lang.Integer applyCount) {
		this.applyCount = applyCount;
	}
	public java.lang.String getJobName() {
		return jobName;
	}
	public void setJobName(java.lang.String jobName) {
		this.jobName = jobName;
	}
	public java.lang.String getImgPath() {
		return imgPath;
	}
	public void setImgPath(java.lang.String imgPath) {
		this.imgPath = imgPath;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public void setJobId(java.lang.Integer jobId) {
		this.jobId = jobId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public java.lang.String getTitle() {
		return title;
	}
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	public Integer getIsUrgency() {
		return isUrgency;
	}
	public void setIsUrgency(Integer isUrgency) {
		this.isUrgency = isUrgency;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public java.lang.Integer getJobId() {
		return jobId;
	}
	public void setJobid(java.lang.Integer jobId) {
		this.jobId = jobId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}
	public java.lang.String getJobCityName() {
		return jobCityName;
	}
	public void setJobCityName(java.lang.String jobCityName) {
		this.jobCityName = jobCityName;
	}
	public java.lang.Integer getSalaryFrom() {
		return salaryFrom;
	}
	public void setSalaryfrom(java.lang.Integer salaryFrom) {
		this.salaryFrom = salaryFrom;
	}
	public java.lang.Integer getSalaryTo() {
		return salaryTo;
	}
	public void setSalaryTo(java.lang.Integer salaryTo) {
		this.salaryTo = salaryTo;
	}
	public Integer getValidation() {
		return validation;
	}
	public void setValidation(Integer validation) {
		this.validation = validation;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getAgeFrom() {
		return ageFrom;
	}
	public void setAgeFrom(Integer ageFrom) {
		this.ageFrom = ageFrom;
	}
	public Integer getAgeTo() {
		return ageTo;
	}
	public void setAgeTo(Integer ageTo) {
		this.ageTo = ageTo;
	}
	public Integer getEducation() {
		return education;
	}
	public void setEducation(Integer education) {
		this.education = education;
	}
	public String getJobLabel() {
		return jobLabel;
	}
	public void setJobLabel(String jobLabel) {
		this.jobLabel = jobLabel;
	}
	public void setSalaryFrom(java.lang.Integer salaryFrom) {
		this.salaryFrom = salaryFrom;
	}


	public Integer getGender() {
		return gender;
	}


	public void setGender(Integer gender) {
		this.gender = gender;
	}


	public String getJobType() {
		return jobType;
	}


	public void setJobType(String jobType) {
		this.jobType = jobType;
	}


	public String getCompanyAbbreviation() {
		return companyAbbreviation;
	}


	public void setCompanyAbbreviation(String companyAbbreviation) {
		this.companyAbbreviation = companyAbbreviation;
	}


	public Integer getCountyId() {
		return countyId;
	}


	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}
	

}
