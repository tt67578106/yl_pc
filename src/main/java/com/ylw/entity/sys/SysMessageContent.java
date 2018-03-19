package com.ylw.entity.sys;

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
@Table(name = "site_sys_message_content")
public class SysMessageContent extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SysMessageContent";
	public static final String ALIAS_ID = "主键ID";
	public static final String ALIAS_TITLE = "消息主题";
	public static final String ALIAS_CONTENT = "消息内容";
	public static final String ALIAS_URL = "消息跳转链接";
	
	//date formats
	

	//columns START
	private java.lang.String title;
	private java.lang.String content;
	private java.lang.String url;
	//columns END


	public SysMessageContent(){
	}
	
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("Content",getContent())
			.append("Url",getUrl())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SysMessageContent == false) return false;
		if(this == obj) return true;
		SysMessageContent other = (SysMessageContent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

