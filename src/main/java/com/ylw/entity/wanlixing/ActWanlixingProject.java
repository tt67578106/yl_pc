package com.ylw.entity.wanlixing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ylw.entity.IdEntity;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "act_wanlixing_project")
public class ActWanlixingProject extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ActWanlixingProject";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_SUB_TITLE = "副标题";
	public static final String ALIAS_IS_VALIDATE = "是否审核";
	public static final String ALIAS_VALIDATE_TIME = "审核时间";
	public static final String ALIAS_VALIDATE_USER_ID = "审核人";
	public static final String ALIAS_CEO_NAME = "ceo名字";
	public static final String ALIAS_CEO_USER_ID = "ceo用户id";
	public static final String ALIAS_SCHOOL = "学校";
	public static final String ALIAS_TEAM_NUMBER = "团队人数";
	public static final String ALIAS_VOTING_COUNT = "目前票数";
	public static final String ALIAS_VIEW_COUNTJ = "被查看次数";
	public static final String ALIAS_MAIN_IMAGE = "主图";
	public static final String ALIAS_PREVIEW_IMAGE = "预览图";
	public static final String ALIAS_VIDEO = "视频";
	public static final String ALIAS_INTRODUCTION = "简介";
	public static final String ALIAS_WEIBO_SCRIPT = "微博代码";
	public static final String ALIAS_ACT_WANLIXING_REGISTRATION_ID = "关联报名表";
	public static final String ALIAS_SELECTION_PROCESS = "当前进程)";
	public static final String ALIAS_SELECTION_PROCESS_DESC = "当前进程";
	public static final String ALIAS_SELECTION_RESULT = "当前进程结果";
	
	//date formats
	public static final String FORMAT_VALIDATE_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.String title;
	private java.lang.String subTitle;
	private Integer isValidate;
	private java.util.Date validateTime;
	private java.lang.Integer validateUserId;
	private java.lang.String ceoName;
	private java.lang.Integer ceoUserId;
	private java.lang.String school;
	private Integer teamNumber;
	private java.lang.Integer votingCount;
	private java.lang.Integer viewCountj;
	private java.lang.String mainImage;
	private java.lang.String previewImage;
	private java.lang.String video;
	private java.lang.String introduction;
	private java.lang.String weiboScript;
	private java.lang.Integer actWanlixingRegistrationId;
	private Integer selectionProcess;
	private java.lang.String selectionProcessDesc;
	private Integer selectionResult;
	private java.lang.String teamName;
	private java.lang.String pptFile;
	private java.lang.String pdfFile;
	//columns END


	public ActWanlixingProject(){
	}

	public ActWanlixingProject(
		java.lang.Integer id
	){
		this.id = id;
	}

	
	
	@Column(name = "team_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getTeamName() {
		return teamName;
	}

	public void setTeamName(java.lang.String teamName) {
		this.teamName = teamName;
	}

	@Column(name = "ppt_file", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getPptFile() {
		return pptFile;
	}

	public void setPptFile(java.lang.String pptFile) {
		this.pptFile = pptFile;
	}

	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "sub_title", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getSubTitle() {
		return this.subTitle;
	}
	
	public void setSubTitle(java.lang.String value) {
		this.subTitle = value;
	}
	
	@Column(name = "is_validate", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsValidate() {
		return this.isValidate;
	}
	
	public void setIsValidate(Integer value) {
		this.isValidate = value;
	}
	
	@Transient
	public String getValidateTimeString() {
		return DateConvertUtils.format(getValidateTime(), FORMAT_VALIDATE_TIME);
	}
	public void setValidateTimeString(String value) {
		setValidateTime(DateConvertUtils.parse(value, FORMAT_VALIDATE_TIME,java.util.Date.class));
	}
	
	@Column(name = "validate_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getValidateTime() {
		return this.validateTime;
	}
	
	public void setValidateTime(java.util.Date value) {
		this.validateTime = value;
	}
	
	@Column(name = "validate_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getValidateUserId() {
		return this.validateUserId;
	}
	
	public void setValidateUserId(java.lang.Integer value) {
		this.validateUserId = value;
	}
	
	@Column(name = "ceo_name", unique = false, nullable = true, insertable = true, updatable = true, length = 60)
	public java.lang.String getCeoName() {
		return this.ceoName;
	}
	
	public void setCeoName(java.lang.String value) {
		this.ceoName = value;
	}
	
	@Column(name = "ceo_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCeoUserId() {
		return this.ceoUserId;
	}
	
	public void setCeoUserId(java.lang.Integer value) {
		this.ceoUserId = value;
	}
	
	@Column(name = "school", unique = false, nullable = true, insertable = true, updatable = true, length = 120)
	public java.lang.String getSchool() {
		return this.school;
	}
	
	public void setSchool(java.lang.String value) {
		this.school = value;
	}
	
	@Column(name = "team_number", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getTeamNumber() {
		return this.teamNumber;
	}
	
	public void setTeamNumber(Integer value) {
		this.teamNumber = value;
	}
	
	@Column(name = "voting_count", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getVotingCount() {
		return this.votingCount;
	}
	
	public void setVotingCount(java.lang.Integer value) {
		this.votingCount = value;
	}
	
	@Column(name = "view_countj", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getViewCountj() {
		return this.viewCountj;
	}
	
	public void setViewCountj(java.lang.Integer value) {
		this.viewCountj = value;
	}
	
	@Column(name = "main_image", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getMainImage() {
		return this.mainImage;
	}
	
	public void setMainImage(java.lang.String value) {
		this.mainImage = value;
	}
	
	@Column(name = "preview_image", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getPreviewImage() {
		return this.previewImage;
	}
	
	public void setPreviewImage(java.lang.String value) {
		this.previewImage = value;
	}
	
	@Column(name = "video", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getVideo() {
		return this.video;
	}
	
	public void setVideo(java.lang.String value) {
		this.video = value;
	}
	
	@Column(name = "introduction", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getIntroduction() {
		return this.introduction;
	}
	
	public void setIntroduction(java.lang.String value) {
		this.introduction = value;
	}
	
	@Column(name = "weibo_script", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getWeiboScript() {
		return this.weiboScript;
	}
	
	public void setWeiboScript(java.lang.String value) {
		this.weiboScript = value;
	}
	
	@Column(name = "act_wanlixing_registration_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getActWanlixingRegistrationId() {
		return this.actWanlixingRegistrationId;
	}
	
	public void setActWanlixingRegistrationId(java.lang.Integer value) {
		this.actWanlixingRegistrationId = value;
	}
	
	@Column(name = "selection_process", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getSelectionProcess() {
		return this.selectionProcess;
	}
	
	public void setSelectionProcess(Integer value) {
		this.selectionProcess = value;
	}
	
	@Column(name = "selection_process_desc", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getSelectionProcessDesc() {
		return this.selectionProcessDesc;
	}
	
	public void setSelectionProcessDesc(java.lang.String value) {
		this.selectionProcessDesc = value;
	}
	
	@Column(name = "selection_result", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getSelectionResult() {
		return this.selectionResult;
	}
	
	public void setSelectionResult(Integer value) {
		this.selectionResult = value;
	}
	
	@Column(name = "pdf_file", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getPdfFile() {
		return pdfFile;
	}

	public void setPdfFile(java.lang.String pdfFile) {
		this.pdfFile = pdfFile;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("SubTitle",getSubTitle())
			.append("IsValidate",getIsValidate())
			.append("ValidateTime",getValidateTime())
			.append("ValidateUserId",getValidateUserId())
			.append("CeoName",getCeoName())
			.append("CeoUserId",getCeoUserId())
			.append("School",getSchool())
			.append("TeamNumber",getTeamNumber())
			.append("VotingCount",getVotingCount())
			.append("ViewCountj",getViewCountj())
			.append("MainImage",getMainImage())
			.append("PreviewImage",getPreviewImage())
			.append("Video",getVideo())
			.append("Introduction",getIntroduction())
			.append("WeiboScript",getWeiboScript())
			.append("ActWanlixingRegistrationId",getActWanlixingRegistrationId())
			.append("SelectionProcess",getSelectionProcess())
			.append("SelectionProcessDesc",getSelectionProcessDesc())
			.append("SelectionResult",getSelectionResult())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ActWanlixingProject == false) return false;
		if(this == obj) return true;
		ActWanlixingProject other = (ActWanlixingProject)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

