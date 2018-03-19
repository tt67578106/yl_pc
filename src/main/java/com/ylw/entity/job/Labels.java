package com.ylw.entity.job;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_labels")
public class Labels implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Labels";
	public static final String ALIAS_ID = "主键ID";
	public static final String ALIAS_LABEL_NAME = "标签名";
	public static final String ALIAS_ISDEL = "是否删除";
	
	//date formats
	

	//columns START
	private Integer id;
	private java.lang.String labelName;
	private Integer isdel;
	//columns END


	public Labels(){
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "label_name", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getLabelName() {
		return this.labelName;
	}
	
	public void setLabelName(java.lang.String value) {
		this.labelName = value;
	}
	
	@Column(name = "isdel", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsdel() {
		return this.isdel;
	}
	
	public void setIsdel(Integer value) {
		this.isdel = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("LabelName",getLabelName())
			.append("Isdel",getIsdel())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Labels == false) return false;
		if(this == obj) return true;
		Labels other = (Labels)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

