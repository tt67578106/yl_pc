package com.ylw.entity.recruitment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.StringUtils;

import com.ylw.entity.IdEntity;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.HTMLInputFilter;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_recruitment")
public class Recruitment extends IdEntity implements java.io.Serializable{
private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Recruitment";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_TITLE = "招聘会名称/主题";
	public static final String ALIAS_HOLDING_DATE = "举办日期";
	public static final String ALIAS_MORNING_AFTERNOON = "上午下午[1:上午/2:下午]";
	public static final String ALIAS_SPONSOR = "主办方";
	public static final String ALIAS_TALENT_MARKET = "所属人才市场";
	public static final String ALIAS_CONTACT_INFO = "联系信息";
	public static final String ALIAS_PROVINCE_ID = "所属省份";
	public static final String ALIAS_CITY_ID = "所属城市";
	public static final String ALIAS_ADDRESS = "地址";
	public static final String ALIAS_INTRODUCTION = "招聘会介绍";
	public static final String ALIAS_STATUS = "状态[0:初始/1:执行中/2:执行完毕]";
	public static final String ALIAS_CREATE_USER_ID = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_USER_ID = "修改人";
	public static final String ALIAS_UPDATE_TIME = "修改时间";
	public static final String ALIAS_START_TIME = "招聘会开始时间";
	public static final String ALIAS_END_TIME = "招聘会结束时间";
	public static final String ALIAS_VIEW_COUNT = "浏览次数";
	public static final String ALIAS_SHOW_VIEW_COUNT = "显示浏览次数";
	public static final String ALIAS_APPLY_COUNT = "报名次数";
	public static final String ALIAS_SHOW_APPLY_COUNT = "显示报名次数";
	public static final String ALIAS_PAGE_TITLE = "三要素 标题";
	public static final String ALIAS_PAGE_KEYWORDS = "三要素关键字";
	public static final String ALIAS_PAGE_DESCRIPTION = "三要素描述";
	public static final String ALIAS_RECRUIT_CODE = "招聘会唯一识别码";
	public static final String ALIAS_IS_BAIDUPRO = "是否设置为百度推广(0 : 是 1：否)";
	public static final String ALIAS_IMG_URL = "招聘会图片";
	
	//date formats
	public static final String FORMAT_HOLDING_DATE = DATE_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	public static final String FORMAT_START_TIME = DATE_FORMAT;
	public static final String FORMAT_END_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.String title;
	private java.util.Date holdingDate;
	private Integer morningAfternoon;
	private java.lang.String sponsor;
	private java.lang.String talentMarket;
	private java.lang.String contactInfo;
	private java.lang.Integer provinceId;
	private java.lang.Integer cityId;
	private java.lang.String address;
	private java.lang.String introduction;
	private Integer status;
	private java.lang.Integer createUserId;
	private java.util.Date createTime;
	private java.lang.Integer updateUserId;
	private java.util.Date updateTime;
	private java.util.Date startTime;
	private java.util.Date endTime;
	private java.lang.Integer viewCount;
	private java.lang.Integer showViewCount;
	private java.lang.Integer applyCount;
	private java.lang.Integer showApplyCount;
	private java.lang.String pageTitle;
	private java.lang.String pageKeywords;
	private java.lang.String pageDescription;
	private java.lang.String recruitCode;
	private java.lang.Integer isBaidupro;
	private java.lang.String imgUrl;
	private String introductionPreview;
	//columns END


	public Recruitment(){
	}

	public Recruitment(
		java.lang.Integer id
	){
		this.id = id;
	}

	
	
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Transient
	public String getHoldingDateString() {
		return DateConvertUtils.format(getHoldingDate(), FORMAT_HOLDING_DATE);
	}
	public void setHoldingDateString(String value) {
		setHoldingDate(DateConvertUtils.parse(value, FORMAT_HOLDING_DATE,java.util.Date.class));
	}
	
	@Column(name = "holding_date", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getHoldingDate() {
		return this.holdingDate;
	}
	
	public void setHoldingDate(java.util.Date value) {
		this.holdingDate = value;
	}
	
	@Column(name = "morning_afternoon", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getMorningAfternoon() {
		return this.morningAfternoon;
	}
	
	public void setMorningAfternoon(Integer value) {
		this.morningAfternoon = value;
	}
	
	@Column(name = "sponsor", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getSponsor() {
		return this.sponsor;
	}
	
	public void setSponsor(java.lang.String value) {
		this.sponsor = value;
	}
	
	@Column(name = "talent_market", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getTalentMarket() {
		return this.talentMarket;
	}
	
	public void setTalentMarket(java.lang.String value) {
		this.talentMarket = value;
	}
	
	@Column(name = "contact_info", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public java.lang.String getContactInfo() {
		return this.contactInfo;
	}
	
	public void setContactInfo(java.lang.String value) {
		this.contactInfo = value;
	}
	
	@Column(name = "province_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getProvinceId() {
		return this.provinceId;
	}
	
	public void setProvinceId(java.lang.Integer value) {
		this.provinceId = value;
	}
	
	@Column(name = "city_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCityId() {
		return this.cityId;
	}
	
	public void setCityId(java.lang.Integer value) {
		this.cityId = value;
	}
	
	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	@Column(name = "introduction", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getIntroduction() {
		return this.introduction;
	}
	
	public void setIntroduction(java.lang.String value) {
		this.introduction = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	@Column(name = "create_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCreateUserId() {
		return this.createUserId;
	}
	
	public void setCreateUserId(java.lang.Integer value) {
		this.createUserId = value;
	}
	
	@Transient
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	@Column(name = "update_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUpdateUserId() {
		return this.updateUserId;
	}
	
	public void setUpdateUserId(java.lang.Integer value) {
		this.updateUserId = value;
	}
	
	@Transient
	public String getUpdateTimeString() {
		return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
	}
	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,java.util.Date.class));
	}
	
	@Column(name = "update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	@Transient
	public String getStartTimeString() {
		return DateConvertUtils.format(getStartTime(), FORMAT_START_TIME);
	}
	public void setStartTimeString(String value) {
		setStartTime(DateConvertUtils.parse(value, FORMAT_START_TIME,java.util.Date.class));
	}
	
	@Column(name = "start_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(java.util.Date value) {
		this.startTime = value;
	}
	
	@Transient
	public String getEndTimeString() {
		return DateConvertUtils.format(getEndTime(), FORMAT_END_TIME);
	}
	public void setEndTimeString(String value) {
		setEndTime(DateConvertUtils.parse(value, FORMAT_END_TIME,java.util.Date.class));
	}
	
	@Column(name = "end_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(java.util.Date value) {
		this.endTime = value;
	}
	
	@Column(name = "view_count", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getViewCount() {
		return this.viewCount;
	}
	
	public void setViewCount(java.lang.Integer value) {
		this.viewCount = value;
	}
	
	@Column(name = "show_view_count", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getShowViewCount() {
		return this.showViewCount;
	}
	
	public void setShowViewCount(java.lang.Integer value) {
		this.showViewCount = value;
	}
	
	@Column(name = "apply_count", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getApplyCount() {
		return this.applyCount;
	}
	
	public void setApplyCount(java.lang.Integer value) {
		this.applyCount = value;
	}
	
	@Column(name = "show_apply_count", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getShowApplyCount() {
		return this.showApplyCount;
	}
	
	public void setShowApplyCount(java.lang.Integer value) {
		this.showApplyCount = value;
	}
	
	@Column(name = "page_title", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getPageTitle() {
		return this.pageTitle;
	}
	
	public void setPageTitle(java.lang.String value) {
		this.pageTitle = value;
	}
	
	@Column(name = "page_keywords", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public java.lang.String getPageKeywords() {
		return this.pageKeywords;
	}
	
	public void setPageKeywords(java.lang.String value) {
		this.pageKeywords = value;
	}
	
	@Column(name = "page_description", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public java.lang.String getPageDescription() {
		return this.pageDescription;
	}
	
	public void setPageDescription(java.lang.String value) {
		this.pageDescription = value;
	}
	
	@Column(name = "recruit_code", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getRecruitCode() {
		return this.recruitCode;
	}
	
	public void setRecruitCode(java.lang.String value) {
		this.recruitCode = value;
	}
	
	@Column(name = "is_baidupro", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsBaidupro() {
		return this.isBaidupro;
	}
	
	public void setIsBaidupro(java.lang.Integer value) {
		this.isBaidupro = value;
	}
	
	@Column(name = "imgUrl", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getImgUrl() {
		return this.imgUrl;
	}
	
	public void setImgUrl(java.lang.String value) {
		this.imgUrl = value;
	}
	
	/**
	 * 暂定文本长度70
	 * @return
	 */
	@Transient
	public java.lang.String getIntroductionPreview() {
		introductionPreview = getIntroduction();
		if(StringUtils.isNotBlank(introductionPreview)){
			introductionPreview = HTMLInputFilter.Html2Text(introductionPreview);
			if(introductionPreview.length()>70){
				introductionPreview = introductionPreview.substring(0, 70)+"...";
			}
		}
		return introductionPreview;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("HoldingDate",getHoldingDate())
			.append("MorningAfternoon",getMorningAfternoon())
			.append("Sponsor",getSponsor())
			.append("TalentMarket",getTalentMarket())
			.append("ContactInfo",getContactInfo())
			.append("ProvinceId",getProvinceId())
			.append("CityId",getCityId())
			.append("Address",getAddress())
			.append("Introduction",getIntroduction())
			.append("Status",getStatus())
			.append("CreateUserId",getCreateUserId())
			.append("CreateTime",getCreateTime())
			.append("UpdateUserId",getUpdateUserId())
			.append("UpdateTime",getUpdateTime())
			.append("StartTime",getStartTime())
			.append("EndTime",getEndTime())
			.append("ViewCount",getViewCount())
			.append("ShowViewCount",getShowViewCount())
			.append("ApplyCount",getApplyCount())
			.append("ShowApplyCount",getShowApplyCount())
			.append("PageTitle",getPageTitle())
			.append("PageKeywords",getPageKeywords())
			.append("PageDescription",getPageDescription())
			.append("RecruitCode",getRecruitCode())
			.append("IsBaidupro",getIsBaidupro())
			.append("ImgUrl",getImgUrl())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Recruitment == false) return false;
		if(this == obj) return true;
		Recruitment other = (Recruitment)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

