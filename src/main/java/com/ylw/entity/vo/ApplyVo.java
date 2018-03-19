package com.ylw.entity.vo;

import java.io.Serializable;
import java.util.Date;

public class ApplyVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer jobId;
	private String jobName;
	private Integer companyId;
	private String companyName;
	private Integer userId;
	private String userName;
	private Date createTime;
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public ApplyVo(Integer jobId, String jobName, String userName,
			Date createTime) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
		this.userName = userName;
		this.createTime = createTime;
	}
	public ApplyVo(Integer jobId, String jobName, Integer companyId,
			String companyName, Integer userId, String userName, Date createTime) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
		this.companyId = companyId;
		this.companyName = companyName;
		this.userId = userId;
		this.userName = userName;
		this.createTime = createTime;
	}
	
}
