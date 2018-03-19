package com.ylw.entity.vo;

import java.io.Serializable;
import java.util.Date;

public class LuceneCompanyListVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private java.lang.Integer companyId;
	private java.lang.String companyLogo;
	private java.lang.String companyImage;
	private java.lang.String companyName;
	private String abbreviation;
	private java.lang.Integer validation;
	private java.lang.Integer industryid;	
	private java.lang.Integer staffscale;
	private java.lang.String address;
	private java.lang.String introduction;
	private java.lang.String satisfaction;
	private java.lang.String cityId;
	private java.lang.String countyid;
	
	private Integer jobId_one;
	private String jobTitle_one;
	private Integer gender_one;
	private Integer ageFrom_one;
	private Integer ageTo_one;
	private Integer education_one;
	private Integer salaryFrom_one;
	private Integer salaryTo_one;
	
	private Integer jobId_two;
	private String jobTitle_two;
	private Integer gender_two;
	private Integer ageFrom_two;
	private Integer ageTo_two;
	private Integer education_two;
	private Integer salaryFrom_two;
	private Integer salaryTo_two;
	private Date updateTime;
	private Date createTime;
	
	public LuceneCompanyListVo(Integer companyId, String companyLogo,
			String companyImage, String companyName, String abbreviation,
			Integer validation, Integer industryid, Integer staffscale,
			String address, String introduction, String satisfaction,
			String cityId, String countyid, Integer jobId_one,
			String jobTitle_one, Integer gender_one, Integer ageFrom_one,
			Integer ageTo_one, Integer education_one, Integer salaryFrom_one,
			Integer salaryTo_one, Integer jobId_two, String jobTitle_two,
			Integer gender_two, Integer ageFrom_two, Integer ageTo_two,
			Integer education_two, Integer salaryFrom_two,
			Integer salaryTo_two, Date updateTime,Date createTime) {
		super();
		this.companyId = companyId;
		this.companyLogo = companyLogo;
		this.companyImage = companyImage;
		this.companyName = companyName;
		this.abbreviation = abbreviation;
		this.validation = validation;
		this.industryid = industryid;
		this.staffscale = staffscale;
		this.address = address;
		this.introduction = introduction;
		this.satisfaction = satisfaction;
		this.cityId = cityId;
		this.countyid = countyid;
		this.jobId_one = jobId_one;
		this.jobTitle_one = jobTitle_one;
		this.gender_one = gender_one;
		this.ageFrom_one = ageFrom_one;
		this.ageTo_one = ageTo_one;
		this.education_one = education_one;
		this.salaryFrom_one = salaryFrom_one;
		this.salaryTo_one = salaryTo_one;
		this.jobId_two = jobId_two;
		this.jobTitle_two = jobTitle_two;
		this.gender_two = gender_two;
		this.ageFrom_two = ageFrom_two;
		this.ageTo_two = ageTo_two;
		this.education_two = education_two;
		this.salaryFrom_two = salaryFrom_two;
		this.salaryTo_two = salaryTo_two;
		this.updateTime = updateTime;
		this.createTime = createTime;
	}
	
	public LuceneCompanyListVo(Integer companyId, String companyLogo,
			String companyImage, String companyName, String abbreviation,
			Integer validation, Integer industryid, Integer staffscale,
			String address, String introduction, String satisfaction,
			String cityId, String countyid, Integer jobId_one,
			String jobTitle_one, Integer gender_one, Integer ageFrom_one,
			Integer ageTo_one, Integer education_one, Integer salaryFrom_one,
			Integer salaryTo_one, Integer jobId_two, String jobTitle_two,
			Integer gender_two, Integer ageFrom_two, Integer ageTo_two,
			Integer education_two, Integer salaryFrom_two,
			Integer salaryTo_two) {
		super();
		this.companyId = companyId;
		this.companyLogo = companyLogo;
		this.companyImage = companyImage;
		this.companyName = companyName;
		this.abbreviation = abbreviation;
		this.validation = validation;
		this.industryid = industryid;
		this.staffscale = staffscale;
		this.address = address;
		this.introduction = introduction;
		this.satisfaction = satisfaction;
		this.cityId = cityId;
		this.countyid = countyid;
		this.jobId_one = jobId_one;
		this.jobTitle_one = jobTitle_one;
		this.gender_one = gender_one;
		this.ageFrom_one = ageFrom_one;
		this.ageTo_one = ageTo_one;
		this.education_one = education_one;
		this.salaryFrom_one = salaryFrom_one;
		this.salaryTo_one = salaryTo_one;
		this.jobId_two = jobId_two;
		this.jobTitle_two = jobTitle_two;
		this.gender_two = gender_two;
		this.ageFrom_two = ageFrom_two;
		this.ageTo_two = ageTo_two;
		this.education_two = education_two;
		this.salaryFrom_two = salaryFrom_two;
		this.salaryTo_two = salaryTo_two;
	}

	public java.lang.Integer getCompanyId() {
		return companyId;
	}


	public void setCompanyId(java.lang.Integer companyId) {
		this.companyId = companyId;
	}


	public java.lang.String getCompanyLogo() {
		return companyLogo;
	}


	public void setCompanyLogo(java.lang.String companyLogo) {
		this.companyLogo = companyLogo;
	}


	public java.lang.String getCompanyImage() {
		return companyImage;
	}


	public void setCompanyImage(java.lang.String companyImage) {
		this.companyImage = companyImage;
	}


	public java.lang.String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(java.lang.String companyName) {
		this.companyName = companyName;
	}


	public String getAbbreviation() {
		return abbreviation;
	}


	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}


	public java.lang.Integer getValidation() {
		return validation;
	}


	public void setValidation(java.lang.Integer validation) {
		this.validation = validation;
	}


	public java.lang.Integer getIndustryid() {
		return industryid;
	}


	public void setIndustryid(java.lang.Integer industryid) {
		this.industryid = industryid;
	}


	public java.lang.Integer getStaffscale() {
		return staffscale;
	}


	public void setStaffscale(java.lang.Integer staffscale) {
		this.staffscale = staffscale;
	}


	public java.lang.String getAddress() {
		return address;
	}


	public void setAddress(java.lang.String address) {
		this.address = address;
	}


	public java.lang.String getIntroduction() {
		return introduction;
	}


	public void setIntroduction(java.lang.String introduction) {
		this.introduction = introduction;
	}


	public java.lang.String getSatisfaction() {
		return satisfaction;
	}


	public void setSatisfaction(java.lang.String satisfaction) {
		this.satisfaction = satisfaction;
	}


	public java.lang.String getCityId() {
		return cityId;
	}


	public void setCityId(java.lang.String cityId) {
		this.cityId = cityId;
	}


	public java.lang.String getCountyid() {
		return countyid;
	}


	public void setCountyid(java.lang.String countyid) {
		this.countyid = countyid;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public Integer getJobId_one() {
		return jobId_one;
	}


	public void setJobId_one(Integer jobId_one) {
		this.jobId_one = jobId_one;
	}


	public String getJobTitle_one() {
		return jobTitle_one;
	}


	public void setJobTitle_one(String jobTitle_one) {
		this.jobTitle_one = jobTitle_one;
	}


	public Integer getGender_one() {
		return gender_one;
	}


	public void setGender_one(Integer gender_one) {
		this.gender_one = gender_one;
	}


	public Integer getAgeFrom_one() {
		return ageFrom_one;
	}


	public void setAgeFrom_one(Integer ageFrom_one) {
		this.ageFrom_one = ageFrom_one;
	}


	public Integer getAgeTo_one() {
		return ageTo_one;
	}


	public void setAgeTo_one(Integer ageTo_one) {
		this.ageTo_one = ageTo_one;
	}


	public Integer getEducation_one() {
		return education_one;
	}


	public void setEducation_one(Integer education_one) {
		this.education_one = education_one;
	}


	public Integer getSalaryFrom_one() {
		return salaryFrom_one;
	}


	public void setSalaryFrom_one(Integer salaryFrom_one) {
		this.salaryFrom_one = salaryFrom_one;
	}


	public Integer getSalaryTo_one() {
		return salaryTo_one;
	}


	public void setSalaryTo_one(Integer salaryTo_one) {
		this.salaryTo_one = salaryTo_one;
	}


	public Integer getJobId_two() {
		return jobId_two;
	}


	public void setJobId_two(Integer jobId_two) {
		this.jobId_two = jobId_two;
	}


	public String getJobTitle_two() {
		return jobTitle_two;
	}


	public void setJobTitle_two(String jobTitle_two) {
		this.jobTitle_two = jobTitle_two;
	}


	public Integer getGender_two() {
		return gender_two;
	}


	public void setGender_two(Integer gender_two) {
		this.gender_two = gender_two;
	}


	public Integer getAgeFrom_two() {
		return ageFrom_two;
	}


	public void setAgeFrom_two(Integer ageFrom_two) {
		this.ageFrom_two = ageFrom_two;
	}


	public Integer getAgeTo_two() {
		return ageTo_two;
	}


	public void setAgeTo_two(Integer ageTo_two) {
		this.ageTo_two = ageTo_two;
	}


	public Integer getEducation_two() {
		return education_two;
	}


	public void setEducation_two(Integer education_two) {
		this.education_two = education_two;
	}


	public Integer getSalaryFrom_two() {
		return salaryFrom_two;
	}


	public void setSalaryFrom_two(Integer salaryFrom_two) {
		this.salaryFrom_two = salaryFrom_two;
	}


	public Integer getSalaryTo_two() {
		return salaryTo_two;
	}


	public void setSalaryTo_two(Integer salaryTo_two) {
		this.salaryTo_two = salaryTo_two;
	}


	public LuceneCompanyListVo() {
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
