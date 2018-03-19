package com.ylw.entity.base;

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
@Table(name = "site_user_profile")
public class UserProfile extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SiteUserProfile";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USER_ID = "用户id";
	public static final String ALIAS_REAL_NAME = "真实姓名";
	public static final String ALIAS_GENDER = "性别1男2女";
	public static final String ALIAS_ROLE = "角色";
	public static final String ALIAS_AVATAR = "头像";
	public static final String ALIAS_SIGNATURE = "签名";
	public static final String ALIAS_INTEGRAL = "积分";
	public static final String ALIAS_GOLD = "金币";
	public static final String ALIAS_BIRTH_YEAR = "出生年";
	public static final String ALIAS_BIRTH_MONTH = "出生月";
	public static final String ALIAS_BIRTH_DAY = "出生日";
	public static final String ALIAS_TELEPHONE = "座机";
	public static final String ALIAS_MOBILE = "手机号";
	public static final String ALIAS_ALIPAY = "支付宝帐号";
	public static final String ALIAS_QQ = "qq";
	public static final String ALIAS_YY = "yy";
	
	//date formats
	

	//columns START
	private java.lang.Integer userId;
	private java.lang.String realName;
	private Integer gender;
	private java.lang.Integer role;
	private java.lang.String avatar;
	private java.lang.String signature;
	private java.lang.Integer integral;
	private java.lang.Integer gold;
	private Integer birthYear;
	private Integer birthMonth;
	private Integer birthDay;
	private java.lang.String telephone;
	private java.lang.String mobile;
	private java.lang.String alipay;
	private java.lang.String qq;
	private java.lang.String yy;
	//columns END


	public UserProfile(){
	}

	public UserProfile(
		java.lang.Integer id
	){
		this.id = id;
	}

	
	
	
	@Column(name = "user_id", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	@Column(name = "real_name", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getRealName() {
		return this.realName;
	}
	
	public void setRealName(java.lang.String value) {
		this.realName = value;
	}
	
	@Column(name = "gender", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getGender() {
		return this.gender;
	}
	
	public void setGender(Integer value) {
		this.gender = value;
	}
	
	@Column(name = "role", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getRole() {
		return this.role;
	}
	
	public void setRole(java.lang.Integer value) {
		this.role = value;
	}
	
	@Column(name = "avatar", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getAvatar() {
		return this.avatar;
	}
	
	public void setAvatar(java.lang.String value) {
		this.avatar = value;
	}
	
	@Column(name = "signature", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getSignature() {
		return this.signature;
	}
	
	public void setSignature(java.lang.String value) {
		this.signature = value;
	}
	
	@Column(name = "integral", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIntegral() {
		return this.integral;
	}
	
	public void setIntegral(java.lang.Integer value) {
		this.integral = value;
	}
	
	@Column(name = "gold", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getGold() {
		return this.gold;
	}
	
	public void setGold(java.lang.Integer value) {
		this.gold = value;
	}
	
	@Column(name = "birth_year", unique = false, nullable = true, insertable = true, updatable = true, length = 5)
	public Integer getBirthYear() {
		return this.birthYear;
	}
	
	public void setBirthYear(Integer value) {
		this.birthYear = value;
	}
	
	@Column(name = "birth_month", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getBirthMonth() {
		return this.birthMonth;
	}
	
	public void setBirthMonth(Integer value) {
		this.birthMonth = value;
	}
	
	@Column(name = "birth_day", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getBirthDay() {
		return this.birthDay;
	}
	
	public void setBirthDay(Integer value) {
		this.birthDay = value;
	}
	
	@Column(name = "telephone", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public java.lang.String getTelephone() {
		return this.telephone;
	}
	
	public void setTelephone(java.lang.String value) {
		this.telephone = value;
	}
	
	@Column(name = "mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	@Column(name = "alipay", unique = false, nullable = true, insertable = true, updatable = true, length = 60)
	public java.lang.String getAlipay() {
		return this.alipay;
	}
	
	public void setAlipay(java.lang.String value) {
		this.alipay = value;
	}
	
	@Column(name = "qq", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getQq() {
		return this.qq;
	}
	
	public void setQq(java.lang.String value) {
		this.qq = value;
	}
	
	@Column(name = "yy", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getYy() {
		return this.yy;
	}
	
	public void setYy(java.lang.String value) {
		this.yy = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("RealName",getRealName())
			.append("Gender",getGender())
			.append("Role",getRole())
			.append("Avatar",getAvatar())
			.append("Signature",getSignature())
			.append("Integral",getIntegral())
			.append("Gold",getGold())
			.append("BirthYear",getBirthYear())
			.append("BirthMonth",getBirthMonth())
			.append("BirthDay",getBirthDay())
			.append("Telephone",getTelephone())
			.append("Mobile",getMobile())
			.append("Alipay",getAlipay())
			.append("Qq",getQq())
			.append("Yy",getYy())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserProfile == false) return false;
		if(this == obj) return true;
		UserProfile other = (UserProfile)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

