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
@Table(name = "site_image")
public class Image extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Image";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TYPE = "类型";
	public static final String ALIAS_IMGPATH = "图片地址";
	public static final String ALIAS_EXTNAME = "图片拓展名";
	public static final String ALIAS_COMMENT = "图片描述";
	public static final String ALIAS_CREATETIME = "创建时间";
	
	//date formats
	public static final String FORMAT_CREATETIME = DATE_FORMAT;
	

	//columns START
	private java.lang.Integer type;
	private java.lang.String imgpath;
	private java.lang.String extname;
	private java.lang.String comment;
	private java.util.Date createtime;
	//columns END


	public Image(){
	}
	
	
	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	@Column(name = "imgpath", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getImgpath() {
		return this.imgpath;
	}
	
	public void setImgpath(java.lang.String value) {
		this.imgpath = value;
	}
	
	@Column(name = "extname", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getExtname() {
		return this.extname;
	}
	
	public void setExtname(java.lang.String value) {
		this.extname = value;
	}
	
	@Column(name = "comment", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getComment() {
		return this.comment;
	}
	
	public void setComment(java.lang.String value) {
		this.comment = value;
	}
	
	@Transient
	public String getCreatetimeString() {
		if(getCreatetime()!=null){
			return DateConvertUtils.format(getCreatetime(), FORMAT_CREATETIME);
		}else{
			return null;
		}
	}
	public void setCreatetimeString(String value) {
		setCreatetime(DateConvertUtils.parse(value, FORMAT_CREATETIME,java.util.Date.class));
	}
	
	@Column(name = "createtime", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Type",getType())
			.append("Imgpath",getImgpath())
			.append("Extname",getExtname())
			.append("Comment",getComment())
			.append("Createtime",getCreatetime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Image == false) return false;
		if(this == obj) return true;
		Image other = (Image)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

