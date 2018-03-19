package com.ylw.entity.vo;

import java.util.Date;
import java.util.List;

/**
 * 广告素材
 * @author nicolas
 *
 */
/**
 * @author Nicolas.Cai
 *
 */
public class AdvResource {
	private String id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 别名
	 */
	private String alias;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 副标题
	 */
	private String subtitle;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 拓展标签1
	 */
	private String label1;
	/**
	 * 拓展标签2
	 */
	private String label2;
	/**
	 * 大图
	 */
	private String largeImgUrl;
	/**
	 * 中图
	 */
	private String normalImgUrl;
	/**
	 * 小图
	 */
	private String smallImgUrl;
	/**
	 * 缩略图
	 */
	private String thumbImageUrl;
	/**
	 * 图片alt
	 */
	private String imgAlt;
	/**
	 * 类型
	 * 1:职位/2:圈子/3:话题/4:网页
	 */
	private Integer resourceType;
	/**
	 * 链接地址
	 */
	private String linkUrl;
	/**
	 * 内容
	 */
	private String resourceValue;
	/**
	 * 默认广告位
	 */
	private String defaultPositionId;
	/**
	 * 广告位所在平台
	 */
	private String positionPlatform;
	/**
	 * 限制角色
	 */
	private String limitRole;
	/**
	 * 限制职业
	 * 5/1/-1
	 */
	private Integer limitWorkingLife;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 预览内容
	 */
	private String prevContent;
	/**
	 * 是否可用
	 */
	private Boolean isAvalable;
	/**
	 * 地区
	 */
	private List<String> divisionList;
	private Date createTime;
	private Date updateTime;
	private Long createUserId;
	private Long updateUserId;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLabel1() {
		return label1;
	}
	public void setLabel1(String label1) {
		this.label1 = label1;
	}
	public String getLabel2() {
		return label2;
	}
	public void setLabel2(String label2) {
		this.label2 = label2;
	}
	public String getLargeImgUrl() {
		return largeImgUrl;
	}
	public void setLargeImgUrl(String largeImgUrl) {
		this.largeImgUrl = largeImgUrl;
	}
	public String getNormalImgUrl() {
		return normalImgUrl;
	}
	public void setNormalImgUrl(String normalImgUrl) {
		this.normalImgUrl = normalImgUrl;
	}
	public String getSmallImgUrl() {
		return smallImgUrl;
	}
	public void setSmallImgUrl(String smallImgUrl) {
		this.smallImgUrl = smallImgUrl;
	}
	public String getThumbImageUrl() {
		return thumbImageUrl;
	}
	public void setThumbImageUrl(String thumbImageUrl) {
		this.thumbImageUrl = thumbImageUrl;
	}
	public String getImgAlt() {
		return imgAlt;
	}
	public void setImgAlt(String imgAlt) {
		this.imgAlt = imgAlt;
	}
	public Integer getResourceType() {
		return resourceType;
	}
	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getResourceValue() {
		return resourceValue;
	}
	public void setResourceValue(String resourceValue) {
		this.resourceValue = resourceValue;
	}
	public String getDefaultPositionId() {
		return defaultPositionId;
	}
	public void setDefaultPositionId(String defaultPositionId) {
		this.defaultPositionId = defaultPositionId;
	}
	public String getPositionPlatform() {
		return positionPlatform;
	}
	public void setPositionPlatform(String positionPlatform) {
		this.positionPlatform = positionPlatform;
	}
	public String getLimitRole() {
		return limitRole;
	}
	public void setLimitRole(String limitRole) {
		this.limitRole = limitRole;
	}
	public Integer getLimitWorkingLife() {
		return limitWorkingLife;
	}
	public void setLimitWorkingLife(Integer limitWorkingLife) {
		this.limitWorkingLife = limitWorkingLife;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPrevContent() {
		return prevContent;
	}
	public void setPrevContent(String prevContent) {
		this.prevContent = prevContent;
	}
	public Boolean getIsAvalable() {
		return isAvalable;
	}
	public void setIsAvalable(Boolean isAvalable) {
		this.isAvalable = isAvalable;
	}
	public List<String> getDivisionList() {
		return divisionList;
	}
	public void setDivisionList(List<String> divisionList) {
		this.divisionList = divisionList;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public Long getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	@Override
	public String toString() {
		return "AdvResource [id=" + id + ", name=" + name + ", alias=" + alias + ", title=" + title + ", subtitle="
				+ subtitle + ", description=" + description + ", label1=" + label1 + ", label2=" + label2
				+ ", largeImgUrl=" + largeImgUrl + ", normalImgUrl=" + normalImgUrl + ", smallImgUrl=" + smallImgUrl
				+ ", thumbImageUrl=" + thumbImageUrl + ", imgAlt=" + imgAlt + ", resourceType=" + resourceType
				+ ", linkUrl=" + linkUrl + ", resourceValue=" + resourceValue + ", defaultPositionId="
				+ defaultPositionId + ", limitRole=" + limitRole + ", limitWorkingLife=" + limitWorkingLife
				+ ", content=" + content + ", prevContent=" + prevContent + ", isAvalable=" + isAvalable
				+ ", divisionList=" + divisionList 
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", createUserId=" + createUserId
				+ ", updateUserId=" + updateUserId + "]";
	}
	
}
