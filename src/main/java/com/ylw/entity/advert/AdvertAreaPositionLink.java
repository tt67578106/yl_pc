package com.ylw.entity.advert;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "advert_area_position_link")
public class AdvertAreaPositionLink extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "AdvertAreaPositionLink";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_AREA_POSITION_ID = "专区位置id";
	public static final String ALIAS_SORTING = "排序";
	public static final String ALIAS_LINK_TITLE = "linkTitle";
	public static final String ALIAS_LINK_URL = "linkUrl";
	
	//date formats
	

	//columns START
	private AdvertAreaPosition areaPosition;
	private java.lang.Integer sorting;
	private java.lang.String linkTitle;
	private java.lang.String linkUrl;
	//columns END


	public AdvertAreaPositionLink(){
	}

	public AdvertAreaPositionLink(
		java.lang.Integer id
	){
		this.id = id;
	}

	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "area_position_id")
	public AdvertAreaPosition getAreaPosition() {
		return areaPosition;
	}

	public void setAreaPosition(AdvertAreaPosition areaPosition) {
		this.areaPosition = areaPosition;
	}

	@Column(name = "sorting", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSorting() {
		return this.sorting;
	}
	
	public void setSorting(java.lang.Integer value) {
		this.sorting = value;
	}
	
	@Column(name = "link_title", unique = false, nullable = true, insertable = true, updatable = true, length = 60)
	public java.lang.String getLinkTitle() {
		return this.linkTitle;
	}
	
	public void setLinkTitle(java.lang.String value) {
		this.linkTitle = value;
	}
	
	@Column(name = "link_url", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getLinkUrl() {
		return this.linkUrl;
	}
	
	public void setLinkUrl(java.lang.String value) {
		this.linkUrl = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("AreaPosition",getAreaPosition())
			.append("Sorting",getSorting())
			.append("LinkTitle",getLinkTitle())
			.append("LinkUrl",getLinkUrl())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AdvertAreaPositionLink == false) return false;
		if(this == obj) return true;
		AdvertAreaPositionLink other = (AdvertAreaPositionLink)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

