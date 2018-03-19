package com.ylw.entity.community;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "site_user_community_talk_comment")
public class UserCommunityTalkComment extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "UserCommunityTalkComment";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_USER_COMMUNITY_TALK_ID = "对应社区话题id";
	public static final String ALIAS_CONTENT = "评论内容";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_CREATE_USER_ID = "创建人";
	public static final String ALIAS_STATUS = "状态";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.Integer id;
	//private java.lang.Integer userCommunityTalkId;
	private java.lang.String content;
	private java.util.Date createTime;
	private java.lang.Integer createUserId;
	private Integer status;
	private UserCommunityTalk userCommunityTalk;
	//columns END


	public UserCommunityTalkComment(){
	}

	public UserCommunityTalkComment(
		java.lang.Integer id
	){
		this.id = id;
	}

	
	
	
//	@Column(name = "user_community_talk_id", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
//	public java.lang.Integer getUserCommunityTalkId() {
//		return this.userCommunityTalkId;
//	}
//	
//	public void setUserCommunityTalkId(java.lang.Integer value) {
//		this.userCommunityTalkId = value;
//	}
	
	
	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getContent() {
		return this.content;
	}
	
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	@OneToOne()
	@JoinColumn(name="user_community_talk_id")
	public UserCommunityTalk getUserCommunityTalk() {
		return userCommunityTalk;
	}

	public void setUserCommunityTalk(UserCommunityTalk userCommunityTalk) {
		this.userCommunityTalk = userCommunityTalk;
	}

	@Transient
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
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
	
	@Column(name = "create_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCreateUserId() {
		return this.createUserId;
	}
	
	public void setCreateUserId(java.lang.Integer value) {
		this.createUserId = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserCommunityTalk",getUserCommunityTalk())
			.append("Content",getContent())
			.append("CreateTime",getCreateTime())
			.append("CreateUserId",getCreateUserId())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserCommunityTalkComment == false) return false;
		if(this == obj) return true;
		UserCommunityTalkComment other = (UserCommunityTalkComment)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

