package com.ylw.entity.vo;

import java.io.Serializable;

public class WanlixingProjectVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer projectId;
	private String ceoName;
	private String school;
	private Integer votingCount;
	private String title;
	private String subTitle;
	private String teamName;
	private String introduction;
	private String leadImage;
	private String pdfFile;
	public WanlixingProjectVo(Integer projectId, String ceoName, String school,
			Integer votingCount, String title, String subTitle, String teamName,
			String introduction, String leadImage, String pdfFile) {
		super();
		this.projectId = projectId;
		this.ceoName = ceoName;
		this.school = school;
		this.votingCount = votingCount;
		this.title = title;
		this.subTitle = subTitle;
		this.teamName = teamName;
		this.introduction = introduction;
		this.leadImage = leadImage;
		this.pdfFile = pdfFile;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getCeoName() {
		return ceoName;
	}
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Integer getVotingCount() {
		return votingCount;
	}
	public void setVotingCount(Integer votingCount) {
		this.votingCount = votingCount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getLeadImage() {
		return leadImage;
	}
	public void setLeadImage(String leadImage) {
		this.leadImage = leadImage;
	}
	public String getPdfFile() {
		return pdfFile;
	}
	public void setPdfFile(String pdfFile) {
		this.pdfFile = pdfFile;
	}
	

}
