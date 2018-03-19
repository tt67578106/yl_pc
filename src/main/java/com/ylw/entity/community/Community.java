package com.ylw.entity.community;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ylw.entity.IdEntity;
import com.ylw.entity.base.Company;
import com.ylw.entity.base.Image;
import com.ylw.entity.sys.SysUser;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_community")
public class Community extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Community";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_COMPANY_ID = "企业id";
	public static final String ALIAS_CITY_ID = "城市id";
	public static final String ALIAS_COMMUNITY_NAME = "社区简称";
	public static final String ALIAS_CREATE_USER_ID = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_USER_ID = "更新人";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_FOLLOW_COUNT = "社区关注数量";
	public static final String ALIAS_ADD_COUNT = "社区加入数量";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	

	//columns START
	//private java.lang.Integer companyId;
	private Company company;
	private java.lang.Integer cityId;
	private java.lang.String communityName;
	private SysUser createUser;
	private String summary;
	//private java.lang.Integer createUserId;
	private java.util.Date createTime;
	//private java.lang.Integer updateUserId;
	private SysUser updateUser;
	private java.util.Date updateTime;
	private java.lang.Integer followCount;
	private java.lang.Integer addCount;
	private Image image;
	private String lng;
	private String lat;
	private Integer isDel;
	//columns END


	public Community(){
	}

	public Community(java.lang.Integer id) {
		this.id = id;
	}
	
//	@Column(name = "company_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
//	public java.lang.Integer getCompanyId() {
//		return this.companyId;
//	}
//	
//	public void setCompanyId(java.lang.Integer value) {
//		this.companyId = value;
//	}
	
	@OneToOne
	@JoinColumn(name="company_id")
	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne
	@JoinColumn(name = "img_id")
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Column(name = "city_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCityId() {
		return this.cityId;
	}
	
	public void setCityId(java.lang.Integer value) {
		this.cityId = value;
	}
	@Column(name = "lng", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
	@Column(name = "lat", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Column(name = "summary", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	
	@Column(name = "is_del", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	@Column(name = "community_name", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getCommunityName() {
		return this.communityName;
	}
	
	public void setCommunityName(java.lang.String value) {
		this.communityName = value;
	}
	
//	@Column(name = "create_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
//	public java.lang.Integer getCreateUserId() {
//		return this.createUserId;
//	}
//	
//	public void setCreateUserId(java.lang.Integer value) {
//		this.createUserId = value;
//	}
	
	@ManyToOne
	@JoinColumn(name = "create_user_id")
	public SysUser getCreateUser() {
		return createUser;
	}

	public void setCreateUser(SysUser createUser) {
		this.createUser = createUser;
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
	
//	@Column(name = "update_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
//	public java.lang.Integer getUpdateUserId() {
//		return this.updateUserId;
//	}
//	
//	public void setUpdateUserId(java.lang.Integer value) {
//		this.updateUserId = value;
//	}
	
	
	@ManyToOne
	@JoinColumn(name = "update_user_id")
	public SysUser getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(SysUser updateUser) {
		this.updateUser = updateUser;
	}
	
	@Transient
	public String getUpdateTimeString() {
		return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
	}
	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,java.util.Date.class));
	}
	
	@Column(name = "update_time", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	@Column(name = "follow_count", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getFollowCount() {
		return this.followCount;
	}
	
	public void setFollowCount(java.lang.Integer value) {
		this.followCount = value;
	}
	
	@Column(name = "add_count", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAddCount() {
		return this.addCount;
	}
	
	public void setAddCount(java.lang.Integer value) {
		this.addCount = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Company",getCompany())
			.append("CityId",getCityId())
			.append("CommunityName",getCommunityName())
			.append("CreateUser",getCreateUser())
			.append("CreateTime",getCreateTime())
			.append("UpdateUser",getUpdateUser())
			.append("UpdateTime",getUpdateTime())
			.append("FollowCount",getFollowCount())
			.append("AddCount",getAddCount())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Community == false) return false;
		if(this == obj) return true;
		Community other = (Community)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

