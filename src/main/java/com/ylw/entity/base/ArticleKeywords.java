package com.ylw.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ylw.entity.IdEntity;

/**
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2015-7-16 19:56:28 
 */

@Entity
@Table(name = "site_article_keywords")
public class ArticleKeywords extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ArticleKeywords";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_KEY_WORD = "文章关键字";
	public static final String ALIAS_SORTING = "排序";
	public static final String ALIAS_IS_DELETE = "是否删除（1，未删除 2，删除）";
	
	//date formats
	

	//columns START
	private java.lang.String keyWord;
	private java.lang.Integer sorting;
	private java.lang.Long isDelete;
	//columns END


	public ArticleKeywords(){
	}

	public ArticleKeywords(
		java.lang.Integer id
	){
		this.id = id;
	}

	
	
	
	@Column(name = "key_word", unique = true, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getKeyWord() {
		return this.keyWord;
	}
	
	public void setKeyWord(java.lang.String value) {
		this.keyWord = value;
	}
	
	@Column(name = "sorting", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSorting() {
		return this.sorting;
	}
	
	public void setSorting(java.lang.Integer value) {
		this.sorting = value;
	}
	
	@Column(name = "is_delete", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(java.lang.Long value) {
		this.isDelete = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("KeyWord",getKeyWord())
			.append("Sorting",getSorting())
			.append("IsDelete",getIsDelete())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ArticleKeywords == false) return false;
		if(this == obj) return true;
		ArticleKeywords other = (ArticleKeywords)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

