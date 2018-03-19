package com.ylw.entity.wanlixing;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.crypto.Data;

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
@Table(name = "act_wanlixing_project_image")
public class ActWanlixingProjectImage extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ActWanlixingProjectImage";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PROJECT_ID = "projectId";
	public static final String ALIAS_IMAGE_PATH = "图片路径";
	public static final String ALIAS_IMAGE_ALT = "图片alt";
	public static final String ALIAS_INTRODUCTION = "图片介绍";
	public static final String ALIAS_IMAGE_TYPE = "图片类型[1:团队风采/2:网页宣传图/3:项目展示图/4:发起人头像]";
	
	//date formats
	

	//columns START
	private java.lang.Integer projectId;
	private java.lang.String imagePath;
	private java.lang.String imageAlt;
	private java.lang.String introduction;
	private Integer imageType;
	private Date createTime;
	private Integer sorting;
	//columns END


	public ActWanlixingProjectImage(){
	}

	public ActWanlixingProjectImage(
		java.lang.Integer id
	){
		this.id = id;
	}

	
	
	
	@Column(name = "project_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getProjectId() {
		return this.projectId;
	}
	
	public void setProjectId(java.lang.Integer value) {
		this.projectId = value;
	}
	
	@Column(name = "image_path", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getImagePath() {
		return this.imagePath;
	}
	
	public void setImagePath(java.lang.String value) {
		this.imagePath = value;
	}
	
	@Column(name = "image_alt", unique = false, nullable = true, insertable = true, updatable = true, length = 60)
	public java.lang.String getImageAlt() {
		return this.imageAlt;
	}
	
	public void setImageAlt(java.lang.String value) {
		this.imageAlt = value;
	}
	
	@Column(name = "introduction", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getIntroduction() {
		return this.introduction;
	}
	
	public void setIntroduction(java.lang.String value) {
		this.introduction = value;
	}
	@Column(name = "image_type", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getImageType() {
		return this.imageType;
	}
	
	public void setImageType(Integer value) {
		this.imageType = value;
	}
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date date) {
		this.createTime = date;
	}
	
	@Column(name = "sorting", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getSorting() {
		return sorting;
	}

	public void setSorting(Integer sorting) {
		this.sorting = sorting;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ProjectId",getProjectId())
			.append("ImagePath",getImagePath())
			.append("ImageAlt",getImageAlt())
			.append("Introduction",getIntroduction())
			.append("ImageType",getImageType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ActWanlixingProjectImage == false) return false;
		if(this == obj) return true;
		ActWanlixingProjectImage other = (ActWanlixingProjectImage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}


}

