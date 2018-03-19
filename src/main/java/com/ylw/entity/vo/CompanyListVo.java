package com.ylw.entity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ylw.entity.job.JobBase;

public class CompanyListVo implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * aaa
	 */
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
	private Integer cooperationType;
	private Integer isLoan;
	private Date createTime;
	private Date updateTime;
	
	
	private java.lang.Integer provinceId;
	private java.lang.Integer icountyid;
	private java.lang.String cityName;
	
	private List<JobBase> jobBases;
	
	public CompanyListVo() {
	}
	
	public CompanyListVo(Integer companyId, String companyLogo,
			String companyImage, String companyName,String abbreviation, Integer validation,
			Integer industryid, Integer staffscale, String address,
			String introduction, String satisfaction, List<JobBase> jobBases,Integer cooperationType) {
		super();
		this.companyId = companyId;
		this.companyLogo = companyLogo;
		this.companyImage = companyImage;
		this.companyName = companyName;
		this.abbreviation=abbreviation;
		this.validation = validation;
		this.industryid = industryid;
		this.staffscale = staffscale;
		this.address = address;
		this.introduction = introduction;
		this.satisfaction = satisfaction;
		this.jobBases = jobBases;
		this.cooperationType = cooperationType;
	}
	
	public CompanyListVo(Integer companyId, String companyLogo,
			String companyImage, String companyName, String abbreviation,
			Integer validation, Integer industryid, Integer staffscale,
			String address, String introduction, String satisfaction,
			String cityId, String countyid, Date createTime, Date updateTime,
			Integer provinceId, Integer icountyid, String cityName,
			List<JobBase> jobBases,Integer isLoan,Integer cooperationType) {
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
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.provinceId = provinceId;
		this.icountyid = icountyid;
		this.cityName = cityName;
		this.jobBases = jobBases;
		this.isLoan = isLoan;
		this.cooperationType = cooperationType;
	}

	public java.lang.Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(java.lang.Integer provinceId) {
		this.provinceId = provinceId;
	}

	public java.lang.String getCityName() {
		return cityName;
	}

	public void setCityName(java.lang.String cityName) {
		this.cityName = cityName;
	}

	public java.lang.Integer getIcountyid() {
		return icountyid;
	}

	public void setIcountyid(java.lang.Integer icountyid) {
		this.icountyid = icountyid;
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
	public List<JobBase> getJobBases() {
		return jobBases;
	}
	public void setJobBases(List<JobBase> jobBases) {
		this.jobBases = jobBases;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsLoan() {
		return isLoan;
	}

	public void setIsLoan(Integer isLoan) {
		this.isLoan = isLoan;
	}

	public Integer getCooperationType() {
		return cooperationType;
	}

	public void setCooperationType(Integer cooperationType) {
		this.cooperationType = cooperationType;
	}
	
}
