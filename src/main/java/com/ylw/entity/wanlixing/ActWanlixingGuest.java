package com.ylw.entity.wanlixing;

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
@Table(name = "act_wanlixing_guest")
public class ActWanlixingGuest extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ActWanlixingGuest";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_FULL_NAME = "姓名";
	public static final String ALIAS_HEAD_IMAGE = "头像";
	public static final String ALIAS_RESUME = "简历简介";
	public static final String ALIAS_GUEST_TYPE = "嘉宾类型";
	public static final String ALIAS_STATUS = "状态";
	
	//date formats
	

	//columns START
	private java.lang.String fullName;
	private java.lang.String headImage;
	private java.lang.String resume;
	private Integer guestType;
	private Integer status;
	//columns END


	public ActWanlixingGuest(){
	}

	public ActWanlixingGuest(
		java.lang.Integer id
	){
		this.id = id;
	}

	
	
	
	@Column(name = "full_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getFullName() {
		return this.fullName;
	}
	
	public void setFullName(java.lang.String value) {
		this.fullName = value;
	}
	
	@Column(name = "head_image", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getHeadImage() {
		return this.headImage;
	}
	
	public void setHeadImage(java.lang.String value) {
		this.headImage = value;
	}
	
	@Column(name = "resume", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getResume() {
		return this.resume;
	}
	
	public void setResume(java.lang.String value) {
		this.resume = value;
	}
	
	@Column(name = "guest_type", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getGuestType() {
		return this.guestType;
	}
	
	public void setGuestType(Integer value) {
		this.guestType = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("FullName",getFullName())
			.append("HeadImage",getHeadImage())
			.append("Resume",getResume())
			.append("GuestType",getGuestType())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ActWanlixingGuest == false) return false;
		if(this == obj) return true;
		ActWanlixingGuest other = (ActWanlixingGuest)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

