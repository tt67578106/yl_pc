package com.ylw.entity.base;

import javax.persistence.CascadeType;
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
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_company_scene")
public class CompanyScene extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "CompanyScene";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_COMPANYID = "公司ID";
	public static final String ALIAS_IMGID = "图片ID";
	public static final String ALIAS_TITLE = "图片标题";
	public static final String ALIAS_COMMENT = "图片描述";
	public static final String ALIAS_TYPE = "实景类型";
	public static final String ALIAS_ISSHOW = "是否显示";
	public static final String ALIAS_CREATETIME = "创建时间";
	
	//date formats
	public static final String FORMAT_CREATETIME = DATE_FORMAT;
	

	//columns START
//	private java.lang.Integer companyid;
	private Company company;
//	private java.lang.Integer imgid;
	private Image image;
	private java.lang.String title;
	private java.lang.String comment;
	private Integer type;
	private Integer isshow;
	private java.util.Date createtime;
	//相册类别相册类别（企业实景、工作环境、生活环境、未分类)
	private Integer category; 
	//columns END

	public CompanyScene(){
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "companyid")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	/*
	@Column(name = "companyid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCompanyid() {
		return this.companyid;
	}
	
	public void setCompanyid(java.lang.Integer value) {
		this.companyid = value;
	}*/
	/*
	@Column(name = "imgid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getImgid() {
		return this.imgid;
	}
	
	public void setImgid(java.lang.Integer value) {
		this.imgid = value;
	}*/
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "imgid")
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "comment", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getComment() {
		return this.comment;
	}
	
	public void setComment(java.lang.String value) {
		this.comment = value;
	}
	
	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer value) {
		this.type = value;
	}
	
	@Column(name = "isshow", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsshow() {
		return this.isshow;
	}
	
	public void setIsshow(Integer value) {
		this.isshow = value;
	}
	
	@Transient
	public String getCreatetimeString() {
		return DateConvertUtils.format(getCreatetime(), FORMAT_CREATETIME);
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
	@Column(name = "category", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
//			.append("Companyid",getCompany().getId())
//			.append("Imgid",getImgid())
			.append("Title",getTitle())
			.append("Comment",getComment())
			.append("Type",getType())
			.append("Isshow",getIsshow())
			.append("Createtime",getCreatetime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CompanyScene == false) return false;
		if(this == obj) return true;
		CompanyScene other = (CompanyScene)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

