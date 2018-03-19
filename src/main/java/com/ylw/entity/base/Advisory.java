package com.ylw.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylw.entity.IdEntity;
import com.ylw.entity.job.JobBase;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_advisory")
@JsonIgnoreProperties({"user","job"})
public class Advisory extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
		public static final String TABLE_ALIAS = "Advisory";
		public static final String ALIAS_ID = "ID";
		public static final String ALIAS_COMPANYID = "公司ID";
		public static final String ALIAS_JOBID = "岗位ID";
		public static final String ALIAS_COMMENT = "咨询内容";
		public static final String ALIAS_USERID = "咨询用户ID";
		public static final String ALIAS_USERNAME = "咨询用户";
		public static final String ALIAS_CREATETIME = "创建时间";
		public static final String ALIAS_CHECKUSER = "审核人";
		public static final String ALIAS_CHECKTIME = "审核时间";
		public static final String ALIAS_CHECKSTATUS = "审核状态";
		public static final String ALIAS_IS_REPLY = "是否已回复";
		public static final String ALIAS_IS_TOP = "isTop";
		public static final String ALIAS_TOP_INDEX = "topIndex";
		public static final String ALIAS_REPLY_USER_ID = "回复用户Id";
		public static final String ALIAS_REPLY_USER_NAME = "回复用户";
		public static final String ALIAS_REPLY_CONTENT = "回复内容";
		public static final String ALIAS_REPLY_TIME = "回复时间";
		public static final String ALIAS_REPLY_UPDATE_TIME = "回复修改时间";
		
		//date formats
		public static final String FORMAT_CREATETIME = DATE_FORMAT;
		public static final String FORMAT_CHECKTIME = DATE_FORMAT;
		public static final String FORMAT_REPLY_TIME = DATE_FORMAT_TIME;
		public static final String FORMAT_REPLY_UPDATE_TIME = DATE_FORMAT;
		
		

		//columns START
		private java.lang.Integer companyid;
		private java.lang.Integer jobid;
		private JobBase job;
		private java.lang.String comment;
		private java.lang.Integer userid;
//		private User user;
		private java.lang.String username;
		private java.util.Date createtime;
		private java.lang.Integer checkuser;
		private java.util.Date checktime;
		private Integer checkstatus;
		private Integer isReply;
		private Integer isTop;
		private Integer topIndex;
		private java.lang.Integer replyUserId;
		private java.lang.String replyUserName;
		private java.lang.String replyContent;
		private java.util.Date replyTime;
		private java.util.Date replyUpdateTime;
		//columns END

		public Advisory(){
		}
		@Transient
		public String getJobName() {
			if(getJob() != null && getJob().getDeadline() !=null){
				if(DateConvertUtils.getNow().getTime() > getJob().getDeadline().getTime()){
					return "相关岗位已过期";
				}
				return getJob().getJobname();
			}
			return "";
		}
		@ManyToOne
		@JoinColumn(name="jobid",insertable=false, updatable=false)
		public JobBase getJob() {
			return job;
		}
		public void setJob(JobBase job) {
			this.job = job;
		}
		@Column(name = "companyid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
		public java.lang.Integer getCompanyid() {
			return this.companyid;
		}
		
		
		public void setCompanyid(java.lang.Integer value) {
			this.companyid = value;
		}
		
		@Column(name = "jobid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
		public java.lang.Integer getJobid() {
			return this.jobid;
		}
		
		public void setJobid(java.lang.Integer value) {
			this.jobid = value;
		}
		
		@Column(name = "comment", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
		public java.lang.String getComment() {
			return this.comment;
		}
		
		public void setComment(java.lang.String value) {
			this.comment = value;
		}
		
		@Column(name = "userid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
		public java.lang.Integer getUserid() {
			return this.userid;
		}
		
		public void setUserid(java.lang.Integer value) {
			this.userid = value;
		}
		
		@Column(name = "username", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
		public java.lang.String getUsername() {
			return this.username;
		}
		
		public void setUsername(java.lang.String value) {
			this.username = value;
		}
		
		@Column(name = "createtime", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
		public java.util.Date getCreatetime() {
			return this.createtime;
		}
		
		public void setCreatetime(java.util.Date value) {
			this.createtime = value;
		}

		@Transient
		public String getCreatetimeString() {
			if(getCreatetime()!=null){
				return DateConvertUtils.format(getCreatetime(), DATE_FORMAT_TIME);
			}
			return "";
		}
		public void setCreatetimeString(String value) {
			setCreatetime(DateConvertUtils.parse(value, DATE_FORMAT_TIME,java.util.Date.class));
		}
		
		@Column(name = "checkuser", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
		public java.lang.Integer getCheckuser() {
			return this.checkuser;
		}
		
		public void setCheckuser(java.lang.Integer value) {
			this.checkuser = value;
		}
		
		@Column(name = "checktime", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
		public java.util.Date getChecktime() {
			return this.checktime;
		}
		
		public void setChecktime(java.util.Date value) {
			this.checktime = value;
		}
		
		@Column(name = "checkstatus", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
		public Integer getCheckstatus() {
			return this.checkstatus;
		}
		
		public void setCheckstatus(Integer value) {
			this.checkstatus = value;
		}
		
		@Column(name = "is_reply", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
		public Integer getIsReply() {
			return this.isReply;
		}
		
		public void setIsReply(Integer value) {
			this.isReply = value;
		}
		
		@Column(name = "is_top", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
		public Integer getIsTop() {
			return this.isTop;
		}
		
		public void setIsTop(Integer value) {
			this.isTop = value;
		}
		
		@Column(name = "top_index", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
		public Integer getTopIndex() {
			return this.topIndex;
		}
		
		public void setTopIndex(Integer value) {
			this.topIndex = value;
		}
		
		@Column(name = "reply_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
		public java.lang.Integer getReplyUserId() {
			return this.replyUserId;
		}
		
		public void setReplyUserId(java.lang.Integer value) {
			this.replyUserId = value;
		}
		
		@Column(name = "reply_user_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
		public java.lang.String getReplyUserName() {
			return this.replyUserName;
		}
		
		public void setReplyUserName(java.lang.String value) {
			this.replyUserName = value;
		}
		
		@Column(name = "reply_content", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
		public java.lang.String getReplyContent() {
			return this.replyContent;
		}
		
		public void setReplyContent(java.lang.String value) {
			this.replyContent = value;
		}
		
		@Column(name = "reply_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
		public java.util.Date getReplyTime() {
			return this.replyTime;
		}
		
		public void setReplyTime(java.util.Date value) {
			this.replyTime = value;
		}

		@Transient
		public String getReplyTimeString() {
			if(getReplyTime() != null){
			return DateConvertUtils.format(getReplyTime(), FORMAT_REPLY_TIME);
			}
			return "";
		}
		
		@Column(name = "reply_update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
		public java.util.Date getReplyUpdateTime() {
			return this.replyUpdateTime;
		}
		
		public void setReplyUpdateTime(java.util.Date value) {
			this.replyUpdateTime = value;
		}
		

		public String toString() {
			return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("Id",getId())
				.append("Companyid",getCompanyid())
				.append("Jobid",getJobid())
				.append("Comment",getComment())
				.append("Userid",getUserid())
				.append("Username",getUsername())
				.append("Createtime",getCreatetime())
				.append("Checkuser",getCheckuser())
				.append("Checktime",getChecktime())
				.append("Checkstatus",getCheckstatus())
				.append("IsReply",getIsReply())
				.append("IsTop",getIsTop())
				.append("TopIndex",getTopIndex())
				.append("ReplyUserId",getReplyUserId())
				.append("ReplyUserName",getReplyUserName())
				.append("ReplyContent",getReplyContent())
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
			if(obj instanceof Advisory == false) return false;
			if(this == obj) return true;
			Advisory other = (Advisory)obj;
			return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
		}
	}

