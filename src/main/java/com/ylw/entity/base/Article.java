package com.ylw.entity.base;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ylw.util.DateConvertUtils;
import com.ylw.util.HTMLInputFilter;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_article")
public class Article implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Article";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TITLE = "title";
	public static final String ALIAS_PROMOTE_TITLE = "promoteTitle";
	public static final String ALIAS_PROMOTE_INTRODUCTION="introduction";
	public static final String ALIAS_SHORT_URL = "shortUrl";
	public static final String ALIAS_THUMBNIAL_IMAGE_ID = "thumbnialImageId";
	public static final String ALIAS_ARTICLE_TYPE = "articleType";
	public static final String ALIAS_CONTENT_ID = "contentId";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_CREATE_USER_ID = "createUserId";
	public static final String ALIAS_PUBLISH_TIME = "publishTime";
	public static final String ALIAS_PUBLISH_USER_ID = "publishUserId";
	public static final String ALIAS_VIEW_COUNT = "viewCount";
	public static final String ALIAS_PRAISE_COUNT = "praiseCount";
	public static final String ALIAS_IS_RECOMMEND = "isRecommend";
	public static final String ALIAS_RECOMMEND_SORT = "recommendSort";
	public static final String ALIAS_KEYWORDS = "keywords";
	public static final String ALIAS_SOURCE = "source";
	public static final String ALIAS_STATUS = "status";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_PUBLISH_TIME = "yyyy-MM-dd HH:mm";
	
	private Integer id;
	//columns START
	private java.lang.String title;
	private java.lang.String promoteTitle;
	private java.lang.String introduction;
	private java.lang.String shortUrl;
//	private java.lang.Integer thumbnialImageId;
	private Image thumbnialImage;
	private java.lang.Integer articleType;
	private ArticleContent content;
	private java.util.Date createTime;
	private java.lang.Integer createUserId;
	private java.util.Date publishTime;
	private java.lang.Integer publishUserId;
	private java.lang.Integer viewCount;
	private java.lang.Integer praiseCount;
	private java.lang.Integer isStandpage;
	private java.lang.Integer isRecommend;
	private java.lang.Integer recommendSort;
	private java.lang.String keywords;
	private java.lang.String source;
	private java.lang.Integer status;
	//columns END
	private java.lang.String contentPreview;
	private java.lang.String jxcontentPreview;

	public Article(){
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "promote_title", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getPromoteTitle() {
		return this.promoteTitle;
	}
	
	public void setPromoteTitle(java.lang.String value) {
		this.promoteTitle = value;
	}
	
	@Column(name = "introduction", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(java.lang.String introduction) {
		this.introduction = introduction;
	}
	@Column(name = "short_url", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public java.lang.String getShortUrl() {
		return this.shortUrl;
	}
	
	public void setShortUrl(java.lang.String value) {
		this.shortUrl = value;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="thumbnial_image_id")
	public Image getThumbnialImage() {
		return thumbnialImage;
	}

	public void setThumbnialImage(Image thumbnialImage) {
		this.thumbnialImage = thumbnialImage;
	}
	
	@Column(name = "article_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getArticleType() {
		return this.articleType;
	}
	
	public void setArticleType(java.lang.Integer value) {
		this.articleType = value;
	}
	
	@ManyToOne
	@JoinColumn(name = "content_id",nullable=true)
	public ArticleContent getContent() {
		return content;
	}

	public void setContent(ArticleContent content) {
		this.content = content;
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
	
	@Transient
	public String getPublishTimeString() {
		return DateConvertUtils.format(getPublishTime(), FORMAT_PUBLISH_TIME);
	}
	
	@Transient
	public String getSiteMapTimeString() {
		return DateConvertUtils.format(getPublishTime(), "yyyy-MM-dd");
	}
	
	public void setPublishTimeString(String value) {
		setPublishTime(DateConvertUtils.parse(value, FORMAT_PUBLISH_TIME,java.util.Date.class));
	}
	
	@Column(name = "publish_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getPublishTime() {
		return this.publishTime;
	}
	
	public void setPublishTime(java.util.Date value) {
		this.publishTime = value;
	}
	
	@Column(name = "publish_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPublishUserId() {
		return this.publishUserId;
	}
	
	public void setPublishUserId(java.lang.Integer value) {
		this.publishUserId = value;
	}
	
	@Column(name = "view_count", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getViewCount() {
		return this.viewCount;
	}
	
	public void setViewCount(java.lang.Integer value) {
		this.viewCount = value;
	}
	
	@Column(name = "praise_count", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPraiseCount() {
		return this.praiseCount;
	}
	
	public void setPraiseCount(java.lang.Integer value) {
		this.praiseCount = value;
	}
	
	@Column(name = "is_recommend", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsRecommend() {
		return this.isRecommend;
	}
	
	public void setIsRecommend(java.lang.Integer value) {
		this.isRecommend = value;
	}
	
	@Column(name = "recommend_sort", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getRecommendSort() {
		return this.recommendSort;
	}
	
	public void setRecommendSort(java.lang.Integer value) {
		this.recommendSort = value;
	}
	
	@Column(name = "keywords", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getKeywords() {
		return this.keywords;
	}
	
	public void setKeywords(java.lang.String value) {
		this.keywords = value;
	}
	
	@Column(name = "source", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getSource() {
		return this.source;
	}
	
	public void setSource(java.lang.String value) {
		this.source = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	@Column(name = "is_standpage", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsStandpage() {
		return isStandpage;
	}

	public void setIsStandpage(java.lang.Integer isStandpage) {
		this.isStandpage = isStandpage;
	}
	/**
	 * 暂定文本长度70
	 * @return
	 */
	@Transient
	public java.lang.String getContentPreview() {
		contentPreview = getContent().getContent();
		if(StringUtils.isNotBlank(contentPreview)){
			contentPreview = HTMLInputFilter.Html2Text(contentPreview);
			if(contentPreview.length()>120){
				contentPreview = contentPreview.substring(0, 120)+"...";
			}
		}
		return contentPreview;
	}
	
	/**
	 * 暂定文本长度70
	 * @return
	 */
	@Transient
	public java.lang.String getJxcontentPreview() {
		jxcontentPreview = getContent().getContent();
		if(StringUtils.isNotBlank(jxcontentPreview)){
			jxcontentPreview = HTMLInputFilter.Html2Text(jxcontentPreview);
			if(jxcontentPreview.length()>130){
				jxcontentPreview = jxcontentPreview.substring(0, 130)+"...";
			}
		}
		return jxcontentPreview;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("PromoteTitle",getPromoteTitle())
			.append("ShortUrl",getShortUrl())
			.append("ThumbnialImageId",getThumbnialImage().getId())
			.append("ArticleType",getArticleType())
			.append("ContentPreview",getContentPreview())
			.append("CreateTime",getCreateTime())
			.append("CreateUserId",getCreateUserId())
			.append("PublishTime",getPublishTime())
			.append("PublishUserId",getPublishUserId())
			.append("ViewCount",getViewCount())
			.append("PraiseCount",getPraiseCount())
			.append("IsRecommend",getIsRecommend())
			.append("RecommendSort",getRecommendSort())
			.append("Keywords",getKeywords())
			.append("Source",getSource())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Article == false) return false;
		if(this == obj) return true;
		Article other = (Article)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

