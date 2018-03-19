package com.ylw.entity.vo;

import java.io.Serializable;
import java.util.List;

import com.ylw.entity.advert.AdvertAreaPositionLink;

/**
 * 广告显示bean
 * @author ghost
 *
 */
public class AdvertPositionVo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private java.lang.Integer branchId;
	private java.lang.String areaName;
	private java.lang.String areaIcon;
	private java.lang.String advPositionCode;
	private java.lang.String areaColor;
	private java.lang.String linkUrl;
	private java.lang.String areaRemark;
	//区块list
	private List<AdvertAreaPositionLink> positionLinks;
	//广告位list
	private List<AdvResourcePubRecord>  advResourcePubRecordList;
	
	public AdvertPositionVo(Integer id,Integer branchId,String areaName,String areaIcon,
			String advPositionCode,String areaColor,String linkUrl,String areaRemark,
			List<AdvertAreaPositionLink> positionLinks,
			List<AdvResourcePubRecord>  advResourcePubRecordList){
		super();
		this.id = id;
		this.branchId = branchId;
		this.areaName = areaName;
		this.areaIcon = areaIcon;
		this.advPositionCode = advPositionCode;
		this.areaColor = areaColor;
		this.linkUrl = linkUrl;
		this.areaRemark = areaRemark;
		this.positionLinks = positionLinks;
		this.advResourcePubRecordList = advResourcePubRecordList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public java.lang.Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(java.lang.Integer branchId) {
		this.branchId = branchId;
	}

	public java.lang.String getAreaName() {
		return areaName;
	}

	public void setAreaName(java.lang.String areaName) {
		this.areaName = areaName;
	}

	public java.lang.String getAreaIcon() {
		return areaIcon;
	}

	public void setAreaIcon(java.lang.String areaIcon) {
		this.areaIcon = areaIcon;
	}

	public java.lang.String getAdvPositionCode() {
		return advPositionCode;
	}

	public void setAdvPositionCode(java.lang.String advPositionCode) {
		this.advPositionCode = advPositionCode;
	}

	public java.lang.String getAreaColor() {
		return areaColor;
	}

	public void setAreaColor(java.lang.String areaColor) {
		this.areaColor = areaColor;
	}

	public java.lang.String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(java.lang.String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public java.lang.String getAreaRemark() {
		return areaRemark;
	}

	public void setAreaRemark(java.lang.String areaRemark) {
		this.areaRemark = areaRemark;
	}

	public List<AdvertAreaPositionLink> getPositionLinks() {
		return positionLinks;
	}

	public void setPositionLinks(List<AdvertAreaPositionLink> positionLinks) {
		this.positionLinks = positionLinks;
	}

	public List<AdvResourcePubRecord> getAdvResourcePubRecordList() {
		return advResourcePubRecordList;
	}

	public void setAdvResourcePubRecordList(
			List<AdvResourcePubRecord> advResourcePubRecordList) {
		this.advResourcePubRecordList = advResourcePubRecordList;
	}
	
}
