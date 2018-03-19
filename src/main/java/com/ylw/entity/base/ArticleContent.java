package com.ylw.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ylw.entity.IdEntity;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_article_content")
public class ArticleContent extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ArticleContent";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_CONTENT = "内容";
	
	//date formats
	

	//columns START
	private java.lang.String content;
	//columns END


	public ArticleContent(){
	}
	
	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true, length = 16777215)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Content",getContent())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ArticleContent == false) return false;
		if(this == obj) return true;
		ArticleContent other = (ArticleContent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

