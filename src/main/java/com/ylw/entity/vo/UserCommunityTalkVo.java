package com.ylw.entity.vo;

import java.util.List;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

public class UserCommunityTalkVo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	private java.lang.Integer askId;
	private Integer jobId;
	private Integer companyId;
	private String companyAbbreviation;
	private String createTimeString;
	private String title;
	private java.lang.String introduction;
	private String askHeadImage;
	private String askUserName;
	List<UserCommunityTalkContentVo> userCommunityTalkContentVo;
	
	public UserCommunityTalkVo(Integer askId,
			String createTimeString,
			String title,
			String introduction,
			String askHeadImage,
			String askUserName,
			Integer jobId,
			Integer companyId,
			String companyAbbreviation,
			List<UserCommunityTalkContentVo> userCommunityTalkContentVo) {
		super();
		this.askId = askId;
		this.createTimeString = createTimeString;
		this.title = title;
		this.introduction = introduction;
		this.askHeadImage = askHeadImage;
		this.askUserName = askUserName;
		this.jobId = jobId;
		this.companyId = companyId;
		this.companyAbbreviation = companyAbbreviation;
		this.userCommunityTalkContentVo = userCommunityTalkContentVo;
	}

	public java.lang.Integer getAskId() {
		return askId;
	}

	public void setAskId(java.lang.Integer askId) {
		this.askId = askId;
	}

	public String getCreateTimeString() {
		return createTimeString;
	}

	public void setCreateTimeString(String createTimeString) {
		this.createTimeString = createTimeString;
	}

	public java.lang.String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(java.lang.String introduction) {
		this.introduction = introduction;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<UserCommunityTalkContentVo> getUserCommunityTalkContentVo() {
		return userCommunityTalkContentVo;
	}

	public void setUserCommunityTalkContent(List<UserCommunityTalkContentVo> userCommunityTalkContentVo) {
		this.userCommunityTalkContentVo = userCommunityTalkContentVo;
	}

	public String getAskHeadImage() {
		return askHeadImage;
	}

	public void setAskHeadImage(String askHeadImage) {
		this.askHeadImage = askHeadImage;
	}

	public String getAskUserName() {
		return askUserName;
	}

	public void setAskUserName(String askUserName) {
		this.askUserName = askUserName;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyAbbreviation() {
		return companyAbbreviation;
	}

	public void setCompanyAbbreviation(String companyAbbreviation) {
		this.companyAbbreviation = companyAbbreviation;
	}
}

