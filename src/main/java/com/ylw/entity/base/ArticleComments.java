package com.ylw.entity.base;

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
@Table(name = "site_article_comments")
public class ArticleComments extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ArticleComments";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ARTICLE_ID = "articleId";
	public static final String ALIAS_AT_FLOOR_ID = "atFloorId";
	public static final String ALIAS_TITLE = "title";
	public static final String ALIAS_CONTENT = "content";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_CREATE_USER_ID = "createUserId";
	public static final String ALIAS_IS_RECOMMEND = "isRecommend";
	public static final String ALIAS_STATUS = "status";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.Integer articleId;
	private java.lang.Integer atFloorId;
	private java.lang.String title;
	private java.lang.String content;
	private java.util.Date createTime;
	private java.lang.Integer createUserId;
	private java.lang.Integer isRecommend;
	private java.lang.Integer status;
	//columns END


	public ArticleComments(){
	}

	
	@Column(name = "article_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getArticleId() {
		return this.articleId;
	}
	
	public void setArticleId(java.lang.Integer value) {
		this.articleId = value;
	}
	
	@Column(name = "at_floor_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAtFloorId() {
		return this.atFloorId;
	}
	
	public void setAtFloorId(java.lang.Integer value) {
		this.atFloorId = value;
	}
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	@Transient
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	@Column(name = "create_time", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
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
	
	@Column(name = "is_recommend", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsRecommend() {
		return this.isRecommend;
	}
	
	public void setIsRecommend(java.lang.Integer value) {
		this.isRecommend = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ArticleId",getArticleId())
			.append("AtFloorId",getAtFloorId())
			.append("Title",getTitle())
			.append("Content",getContent())
			.append("CreateTime",getCreateTime())
			.append("CreateUserId",getCreateUserId())
			.append("IsRecommend",getIsRecommend())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ArticleComments == false) return false;
		if(this == obj) return true;
		ArticleComments other = (ArticleComments)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

