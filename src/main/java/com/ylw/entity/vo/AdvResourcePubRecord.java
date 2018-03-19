package com.ylw.entity.vo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.ylw.util.DateConvertUtils;

/**
 * 广告发布记录详细
 * @author Nicolas.Cai
 *
 */
public class AdvResourcePubRecord {

	private String id;
	/**
	 * 资源
	 */
	private AdvResource resource;
	/**
	 * 是否全国的
	 */
	private Boolean isNational;
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
	 * 省份id
	 */
	private Integer provinceId;
	/**
	 * 城市id
	 */
	private Integer cityId;
	/**
	 * 地区id
	 */
	private Integer districtId;
	/**
	 * 省份地区编号
	 */
	private String provinceNo;
	/**
	 * 城市地区编号
	 */
	private String cityNo;
	/**
	 * 区县地区编号
	 */
	private String districtNo;
	/**
	 * 省份名称
	 */
	private String provinceName;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 地区名称
	 */
	private String districtName;

	/**
	 * 是否删除
	 */
	private Boolean isDelete;
	/**
	 * 发布时间
	 */
	private Date publishTime;
	/**
	 * 上线时间
	 */
	private Date onlineTime;
	/**
	 * 下线时间
	 */
	private Date offlineTime;
	/**
	 * 排序方式
	 */
	private Long sorting;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public AdvResource getResource() {
		return resource;
	}
	public void setResource(AdvResource resource) {
		this.resource = resource;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public String getProvinceNo() {
		return provinceNo;
	}
	public void setProvinceNo(String provinceNo) {
		this.provinceNo = provinceNo;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getDistrictNo() {
		return districtNo;
	}
	public void setDistrictNo(String districtNo) {
		this.districtNo = districtNo;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Long getSorting() {
		return sorting;
	}
	public void setSorting(Long sorting) {
		this.sorting = sorting;
	}
	public Date getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}
	public Date getOfflineTime() {
		return offlineTime;
	}
	public void setOfflineTime(Date offlineTime) {
		this.offlineTime = offlineTime;
	}

	public Boolean getIsNational() {
		return isNational;
	}
	public void setIsNational(Boolean isNational) {
		this.isNational = isNational;
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
	public String getOnlineTimeString(){
		return DateConvertUtils.format(this.getOnlineTime(), "yyyy-MM-dd HH:mm:ss");
	}
	public String getOfflineTimeString(){
		return DateConvertUtils.format(this.getOfflineTime(), "yyyy-MM-dd HH:mm:ss");
	}
	public void setOnlineTimeString(String arg){
		if(StringUtils.isNotBlank(arg)){
			this.setOnlineTime(DateConvertUtils.parse(arg, "yyyy-MM-dd HH:mm:ss"));
		}
	}
	public void setOfflineTimeString(String arg){
		if(StringUtils.isNotBlank(arg)){
			this.setOfflineTime(DateConvertUtils.parse(arg, "yyyy-MM-dd HH:mm:ss"));
		}
	}
}
