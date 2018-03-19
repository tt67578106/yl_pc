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
@Table(name = "site_company_article")
public class CompanyArticle extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "CompanyArticle";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_COMPANY_ID = "companyId";
	public static final String ALIAS_ARTICLE_ID = "articleId";
	public static final String ALIAS_STATUS = "status";
	
	//date formats
	

	//columns START
	private java.lang.Integer companyId;
	private java.lang.Integer articleId;
	private java.lang.Integer status;
	//columns END


	public CompanyArticle(){
	}
	
	@Column(name = "company_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCompanyId() {
		return this.companyId;
	}
	
	public void setCompanyId(java.lang.Integer value) {
		this.companyId = value;
	}
	
	@Column(name = "article_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getArticleId() {
		return this.articleId;
	}
	
	public void setArticleId(java.lang.Integer value) {
		this.articleId = value;
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
			.append("CompanyId",getCompanyId())
			.append("ArticleId",getArticleId())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CompanyArticle == false) return false;
		if(this == obj) return true;
		CompanyArticle other = (CompanyArticle)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

