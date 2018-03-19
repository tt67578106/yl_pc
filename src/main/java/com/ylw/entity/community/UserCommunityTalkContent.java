package com.ylw.entity.community;


import java.util.Date;

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

import com.ylw.entity.IdEntity;
import com.ylw.entity.base.User;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_user_community_talk_content")
public class UserCommunityTalkContent extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "UserCommunityTalkContent";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_USER_COMMUNITY_TALK_ID = "对应社区话题id";
	public static final String ALIAS_CONTENT = "话题内容";
	public static final String ALIAS_USER_ID = "回复用户id";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_SORT = "排序";
	public static final String ALIAS_IS_DEL = "是否删除（0 删除 1 不删除）";
	public static final String ALIAS_SOURCE = "回复来源（1，用户回复；2，后台回复）";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT_TIME;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	public static final String FORMAT_REPLY_TIME = DATE_FORMAT;
	public static final String FORMAT_REPLY_UPDATE_TIME = DATE_FORMAT;

	//columns START
	private java.lang.String content;
	private Integer userCommunityTalkId;
	private String userName;
	private String userHeadPath;
	private User user;
	private Date createTime;
	private Date updateTime;
	private Integer sort;
	private Integer isDel; 
	private Integer source;
	//columns END


	public UserCommunityTalkContent(){
	}

	public UserCommunityTalkContent(
		java.lang.Integer id
	){
		this.id = id;
	}

	@Column(name = "user_community_talk_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getUserCommunityTalkId() {
		return userCommunityTalkId;
	}

	public void setUserCommunityTalkId(Integer userCommunityTalkId) {
		this.userCommunityTalkId = userCommunityTalkId;
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
	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true, length = 16777215)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	@Column(name = "create_time", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Transient
	public String getCreateTimeString() {
		if(getCreateTime()!=null){
			return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
		}
		return null;
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
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
	
	@Column(name = "sort", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "is_del", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	@Column(name = "source", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserCommunityTalkId",getUserCommunityTalkId())
			.append("Content",getContent())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserCommunityTalkContent == false) return false;
		if(this == obj) return true;
		UserCommunityTalkContent other = (UserCommunityTalkContent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

