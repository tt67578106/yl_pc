package com.ylw.entity.advert;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylw.entity.IdEntity;

/**
 * @author Walter
 * @version 1.0.0
 * @since 1.0.0
 * @date 2015-7-16 19:56:28 
 */

@Entity
@Table(name = "advert_area_position")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler","linkList"})
public class AdvertAreaPosition extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "AdvertAreaPosition";
	public static final String ALIAS_ID = "主键Id";
	public static final String ALIAS_BRANCH_ID = "分站Id";
	public static final String ALIAS_AREA_NAME = "专区名称";
	public static final String ALIAS_AREA_ICON = "专区图标";
	public static final String ALIAS_ADV_POSITION_CODE = "专区广告位编号";
	public static final String ALIAS_AREA_COLOR = "颜色色值";
	public static final String ALIAS_LINK_URL = "链接地址";
	public static final String ALIAS_AREA_REMARK = "专区简介";
	public static final String ALIAS_SORTING = "排序";
	public static final String ALIAS_STATUS = "状态(0:失效/1:正常)";
	
	//date formats
	

	//columns START
	private java.lang.Integer branchId;
	private java.lang.String areaName;
	private java.lang.String areaIcon;
	private java.lang.String advPositionCode;
	private java.lang.String areaColor;
	private java.lang.String linkUrl;
	private java.lang.String areaRemark;
	private java.lang.Integer sorting;
	private Integer status;
	//columns END
	
	private List<AdvertAreaPositionLink> linkList;


	public AdvertAreaPosition(){
	}

	public AdvertAreaPosition(
		java.lang.Integer id
	){
		this.id = id;
	}
	
	
	@OneToMany( mappedBy = "areaPosition")
	public List<AdvertAreaPositionLink> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<AdvertAreaPositionLink> linkList) {
		this.linkList = linkList;
	}

	@Column(name = "branch_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getBranchId() {
		return this.branchId;
	}
	
	public void setBranchId(java.lang.Integer value) {
		this.branchId = value;
	}
	
	@Column(name = "area_name", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public java.lang.String getAreaName() {
		return this.areaName;
	}
	
	public void setAreaName(java.lang.String value) {
		this.areaName = value;
	}
	
	@Column(name = "area_icon", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public java.lang.String getAreaIcon() {
		return this.areaIcon;
	}
	
	public void setAreaIcon(java.lang.String value) {
		this.areaIcon = value;
	}
	
	@Column(name = "adv_position_code", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public java.lang.String getAdvPositionCode() {
		return this.advPositionCode;
	}
	
	public void setAdvPositionCode(java.lang.String value) {
		this.advPositionCode = value;
	}
	
	@Column(name = "area_color", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getAreaColor() {
		return this.areaColor;
	}
	
	public void setAreaColor(java.lang.String value) {
		this.areaColor = value;
	}
	
	@Column(name = "link_url", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getLinkUrl() {
		return this.linkUrl;
	}
	
	public void setLinkUrl(java.lang.String value) {
		this.linkUrl = value;
	}
	
	@Column(name = "area_remark", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getAreaRemark() {
		return this.areaRemark;
	}
	
	public void setAreaRemark(java.lang.String value) {
		this.areaRemark = value;
	}
	
	@Column(name = "sorting", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSorting() {
		return this.sorting;
	}
	
	public void setSorting(java.lang.Integer value) {
		this.sorting = value;
	}
	
	@Column(name = "status", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("BranchId",getBranchId())
			.append("AreaName",getAreaName())
			.append("AreaIcon",getAreaIcon())
			.append("AdvPositionCode",getAdvPositionCode())
			.append("AreaColor",getAreaColor())
			.append("LinkUrl",getLinkUrl())
			.append("AreaRemark",getAreaRemark())
			.append("Sorting",getSorting())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AdvertAreaPosition == false) return false;
		if(this == obj) return true;
		AdvertAreaPosition other = (AdvertAreaPosition)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

