package com.ylw.entity.community;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylw.entity.IdEntity;
import com.ylw.entity.base.Company;
import com.ylw.entity.base.User;
import com.ylw.entity.job.JobBase;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_user_community_talk")
@JsonIgnoreProperties({"community"})
public class UserCommunityTalk extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "UserCommunityTalk";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_COMMUNITY_ID = "对应社区id（企业id）";
	public static final String ALIAS_JOB_ID = "jobId";
	public static final String ALIAS_USER_ID = "用户id";
	public static final String ALIAS_TO_USER_ID = "指向用户id";
	public static final String ALIAS_PARENT_ID = "父id";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_CITY_ID = "cityId";
	public static final String ALIAS_TALK_TYPE = "发表类型 1 评论 2 点赞";
	public static final String ALIAS_TYPE = "话题类型 1：话题 2：推广 3：招聘 4咨询";
	public static final String ALIAS_POINT_OF_PRAISE = "pointOfPraise";
	public static final String ALIAS_COMMENT_OF_PRAISE = "commentOfPraise";
	public static final String ALIAS_TITLE = "话题标题";
	public static final String ALIAS_SUB_TITLE = "副标题";
	public static final String ALIAS_INTRODUCTION = "话题内容简介/导读";
	public static final String ALIAS_IMGPATH = "话题图片路径";
	public static final String ALIAS_ADDRESS = "话题发表地址";
	public static final String ALIAS_UPDATE_USER_ID = "更新人";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_STATUS = "话题状态";
	public static final String ALIAS_REPORT_COUNT = "被举报次数";
	public static final String ALIAS_IS_DELETE = "是否删除";
	public static final String ALIAS_URL = "企业推广h5链接";
	public static final String ALIAS_REPLY_CONTENT = "回复内容";
	public static final String ALIAS_REPLY_USER_ID = "回复人";
	public static final String ALIAS_REPLY_TIME = "回复时间";
	public static final String ALIAS_REPLY_UPDATE_TIME = "回复更新时间";
	public static final String ALIAS_IS_RECOMMEND = "是否推荐（0：不推荐 1：推荐）";
	public static final String ALIAS_SOURCE = "问答来源（1，用户提问；2，后台编辑）";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	public static final String FORMAT_REPLY_TIME = DATE_FORMAT;
	public static final String FORMAT_REPLY_UPDATE_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.Integer id;
	private User user;
	private JobBase jobBase;
	private Community community;
	private Company company;
	private String userName;
	private String userHeadPath;
	private java.lang.String toUserId;
	private java.lang.Integer parentId;
	private java.util.Date createTime;
	private java.lang.Integer cityId;
	private java.lang.Integer talkType;
	private java.lang.Integer type;
	private java.lang.Integer pointOfPraise;
	private java.lang.Integer commentOfPraise;
	private java.lang.String title;
	private java.lang.String subTitle;
	private java.lang.String introduction;
	private java.lang.String imgpath;
	private java.lang.String address;
	private java.lang.Integer updateUserId;
	private java.util.Date updateTime;
	private java.lang.Integer status;
	private java.lang.Integer reportCount;
	private java.lang.Integer isDelete;
	private java.lang.String url;
	private java.lang.String replyContent;
	private User replyUser;
	private java.util.Date replyTime;
	private java.util.Date replyUpdateTime;
	private Integer isRecommend;
	private Integer source;
	private Integer isReply;
	//columns END


	public UserCommunityTalk(){
	}

	public UserCommunityTalk(
		java.lang.Integer id
	){
		this.id = id;
	}

	
	@ManyToOne
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@ManyToOne
	@JoinColumn(name = "job_id")
	public JobBase getJobBase() {
		return jobBase;
	}

	public void setJobBase(JobBase jobBase) {
		this.jobBase = jobBase;
	}
	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	@ManyToOne
	@JoinColumn(name = "reply_user_id")
	public User getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(User replyUser) {
		this.replyUser = replyUser;
	}
	
	@ManyToOne
	@JoinColumn(name = "community_id")
	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "user_name", unique = false, nullable = true, insertable = true, updatable = true, length = 49)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_head_path", unique = false, nullable = true, insertable = true, updatable = true, length = 199)
	public String getUserHeadPath() {
		return userHeadPath;
	}

	public void setUserHeadPath(String userHeadPath) {
		this.userHeadPath = userHeadPath;
	}

	@Column(name = "to_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getToUserId() {
		return this.toUserId;
	}
	
	public void setToUserId(java.lang.String value) {
		this.toUserId = value;
	}
	
	@Column(name = "parent_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getParentId() {
		return this.parentId;
	}
	
	public void setParentId(java.lang.Integer value) {
		this.parentId = value;
	}
	
	@Transient
	public String getCreateTimeString() {
		if(getCreateTime()!=null){
			return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
		}else{
			return null;
		}
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	@Column(name = "city_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCityId() {
		return this.cityId;
	}
	
	public void setCityId(java.lang.Integer value) {
		this.cityId = value;
	}
	
	@Column(name = "talk_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getTalkType() {
		return this.talkType;
	}
	
	public void setTalkType(java.lang.Integer value) {
		this.talkType = value;
	}
	
	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	@Column(name = "point_of_praise", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPointOfPraise() {
		return this.pointOfPraise;
	}
	
	public void setPointOfPraise(java.lang.Integer value) {
		this.pointOfPraise = value;
	}
	
	@Column(name = "comment_of_praise", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCommentOfPraise() {
		return this.commentOfPraise;
	}
	
	public void setCommentOfPraise(java.lang.Integer value) {
		this.commentOfPraise = value;
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
	
	@Column(name = "introduction", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getIntroduction() {
		return this.introduction;
	}
	
	public void setIntroduction(java.lang.String value) {
		this.introduction = value;
	}
	
	@Column(name = "imgpath", unique = false, nullable = true, insertable = true, updatable = true, length = 15000)
	public java.lang.String getImgpath() {
		return this.imgpath;
	}
	
	public void setImgpath(java.lang.String value) {
		this.imgpath = value;
	}
	
	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	@Column(name = "update_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUpdateUserId() {
		return this.updateUserId;
	}
	
	public void setUpdateUserId(java.lang.Integer value) {
		this.updateUserId = value;
	}
	
	@Transient
	public String getUpdateTimeString() {
		return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
	}
	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,java.util.Date.class));
	}
	
	@Column(name = "update_time", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	@Column(name = "report_count", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getReportCount() {
		return this.reportCount;
	}
	
	public void setReportCount(java.lang.Integer value) {
		this.reportCount = value;
	}
	
	@Column(name = "is_delete", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(java.lang.Integer value) {
		this.isDelete = value;
	}
	
	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	@Column(name = "reply_content", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public java.lang.String getReplyContent() {
		return this.replyContent;
	}
	
	public void setReplyContent(java.lang.String value) {
		this.replyContent = value;
	}
	
	@Transient
	public String getReplyTimeString() {
		if(getReplyTime() != null){
		return DateConvertUtils.format(getReplyTime(), FORMAT_REPLY_TIME);
		}
		return "";
	}
	
	@Column(name = "reply_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getReplyTime() {
		return this.replyTime;
	}
	
	public void setReplyTime(java.util.Date value) {
		this.replyTime = value;
	}
	
	@Column(name = "reply_update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getReplyUpdateTime() {
		return this.replyUpdateTime;
	}
	
	public void setReplyUpdateTime(java.util.Date value) {
		this.replyUpdateTime = value;
	}
	
	@Column(name = "is_recommend", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	
	@Column(name = "source", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
	
	
	@Column(name = "is_reply", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsReply() {
		return isReply;
	}

	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("CommunityId",getCommunity())
			.append("JobId",getJobBase())
			.append("UserId",getUser())
			.append("ToUserId",getToUserId())
			.append("ParentId",getParentId())
			.append("CreateTime",getCreateTime())
			.append("CityId",getCityId())
			.append("TalkType",getTalkType())
			.append("Type",getType())
			.append("PointOfPraise",getPointOfPraise())
			.append("CommentOfPraise",getCommentOfPraise())
			.append("Title",getTitle())
			.append("SubTitle",getSubTitle())
			.append("Introduction",getIntroduction())
			.append("Imgpath",getImgpath())
			.append("Address",getAddress())
			.append("UpdateUserId",getUpdateUserId())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.append("ReportCount",getReportCount())
			.append("IsDelete",getIsDelete())
			.append("Url",getUrl())
			.append("ReplyContent",getReplyContent())
			.append("ReplyUserId",getReplyUser())
			.append("ReplyTime",getReplyTime())
			.append("ReplyUpdateTime",getReplyUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserCommunityTalk == false) return false;
		if(this == obj) return true;
		UserCommunityTalk other = (UserCommunityTalk)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

