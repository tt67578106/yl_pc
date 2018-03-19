package com.ylw.entity.vo;

import java.io.Serializable;

/**
 * 公司推荐类，显示bean
 * @author ghost
 *
 */
public class RecommendCompanyVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private java.lang.Integer id;
	private java.lang.String title;
	private java.lang.String remark;

	/**
	 * 相关企业属性
	 */
	private java.lang.Integer companyId;
	private java.lang.String companyLogo;
	private java.lang.String companysence;
	private java.lang.String companyName;
	private java.lang.String abbreviation;
	private java.lang.String introduction;
	private java.lang.Integer pointOfPraise;

	
	/**
	 * 企业下，岗位属性
	 */
	private java.lang.Integer jobId;
	private java.lang.String jobType;
	private java.lang.String jobLabel;
	private java.lang.String jobTitle;
	private java.lang.String totalsalary;
	private java.lang.Integer salaryFrom;
	private java.lang.Integer salaryTo;
	
	
	public RecommendCompanyVo(Integer companyId, String companyLogo,
			String companyName,Integer pointOfPraise,
			Integer jobId, String jobType, String jobLabel, String jobTitle,
			String totalsalary,Integer salaryFrom,Integer salaryTo) {
		super();
		this.companyId = companyId;
		this.companyLogo = companyLogo;
		this.companyName = companyName;
		this.pointOfPraise = pointOfPraise;
		this.jobId = jobId;
		this.jobType = jobType;
		this.jobLabel = jobLabel;
		this.jobTitle = jobTitle;
		this.totalsalary = totalsalary;
		this.salaryFrom = salaryFrom;
		this.salaryTo = salaryTo;
	}


	public RecommendCompanyVo(Integer id, String title, String remark,
			Integer companyId, String companyLogo,String companysence, String companyName,String abbreviation,
			 Integer jobId, String jobType,
			String jobLabel, String jobTitle, String totalsalary,Integer salaryFrom,Integer salaryTo) {
		super();
		this.id = id;
		this.title = title;
		this.remark = remark;
		this.companyId = companyId;
		this.companyLogo = companyLogo;
		this.companysence = companysence;
		this.companyName = companyName;
		this.abbreviation=abbreviation;
		this.jobId = jobId;
		this.jobType = jobType;
		this.jobLabel = jobLabel;
		this.jobTitle = jobTitle;
		this.totalsalary = totalsalary;
		this.salaryFrom = salaryFrom;
		this.salaryTo = salaryTo;
	}
	
	
	public RecommendCompanyVo(Integer id, Integer companyId, String companyLogo, String companyName
			,String abbreviation,Integer pointOfPraise) {
		super();
		this.id=id;
		this.companyId = companyId;
		this.companyLogo = companyLogo;
		this.companyName = companyName;
		this.abbreviation=abbreviation;
		this.pointOfPraise=pointOfPraise;
	}
	public RecommendCompanyVo(Integer companyId, String companyLogo,
			String companyName, String totalsalary,String abbreviation) {
		super();
		this.companyId = companyId;
		this.companyLogo = companyLogo;
		this.companyName = companyName;
		this.totalsalary = totalsalary;
		this.abbreviation=abbreviation;
	}


	public java.lang.Integer getPointOfPraise() {
		return pointOfPraise;
	}


	public void setPointOfPraise(java.lang.Integer pointOfPraise) {
		this.pointOfPraise = pointOfPraise;
	}
	public java.lang.String getAbbreviation() {
		return abbreviation;
	}


	public void setAbbreviation(java.lang.String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public java.lang.String getTitle() {
		return title;
	}
	public void setTitle(java.lang.String title) {
		this.title = title;
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
	public java.lang.String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(java.lang.String companyName) {
		this.companyName = companyName;
	}
	public java.lang.String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(java.lang.String introduction) {
		this.introduction = introduction;
	}
	public java.lang.Integer getJobId() {
		return jobId;
	}
	public void setJobId(java.lang.Integer jobId) {
		this.jobId = jobId;
	}
	public java.lang.String getJobType() {
		return jobType;
	}
	public void setJobType(java.lang.String jobType) {
		this.jobType = jobType;
	}
	public java.lang.String getJobLabel() {
		return jobLabel;
	}
	public void setJobLabel(java.lang.String jobLabel) {
		this.jobLabel = jobLabel;
	}
	public java.lang.String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(java.lang.String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public java.lang.String getTotalsalary() {
		return totalsalary;
	}
	public void setTotalsalary(java.lang.String totalsalary) {
		this.totalsalary = totalsalary;
	}
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}


	public java.lang.String getCompanysence() {
		return companysence;
	}


	public void setCompanysence(java.lang.String companysence) {
		this.companysence = companysence;
	}
	
	public java.lang.Integer getSalaryFrom() {
		return salaryFrom;
	}


	public void setSalaryFrom(java.lang.Integer salaryFrom) {
		this.salaryFrom = salaryFrom;
	}


	public java.lang.Integer getSalaryTo() {
		return salaryTo;
	}


	public void setSalaryTo(java.lang.Integer salaryTo) {
		this.salaryTo = salaryTo;
	}
}
