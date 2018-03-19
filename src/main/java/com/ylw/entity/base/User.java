package com.ylw.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_user")
public class User implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String TABLE_ALIAS = "User";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_LOGINNAME = "登录名";
	public static final String ALIAS_USERNAME = "用户名";
	public static final String ALIAS_PASSWORD = "加密的密码";
	public static final String ALIAS_SALT = "加密串";
	public static final String ALIAS_REGISTERTIME = "注册时间";
	public static final String ALIAS_REGISTERTYPE = "注册类型:1手动、2：自动";
	public static final String ALIAS_REGISTER_SOURCE = "注册来源:1:pc/2:wap/3:android/4:IOS/5:签到/6:百日大战";
	public static final String ALIAS_FIRST_LOGIN_TIME = "首次登录时间";
	public static final String ALIAS_LAST_LOGIN_TIME = "最后登录时间";
	public static final String ALIAS_LAST_LOGIN_IP = "最后登录IP";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_SOURCE_ACTIVITY_ID="报名来源-活动id";
	public static final String ALIAS_HAS_DOWNLOAD_APP ="是否下载APP(1：已下载；2：未下载)";
	public static final String ALIAS_FROM_KEY = "加盟线Appkey";
	public static final String ALIAS_APP_CHANNEL_CODE = "加盟线渠道code";
	public static final String ALIAS_BEHAVIOR="用户行为";
	public static final String ALIAS_APP_SOURCE_CODE = "来源code";
	
	//date formats
	public static final String FORMAT_REGISTERTIME = DATE_FORMAT;
	public static final String FORMAT_FIRST_LOGIN_TIME = DATE_FORMAT;
	public static final String FORMAT_LAST_LOGIN_TIME = DATE_FORMAT;
	

	//columns START
	private Integer id;
	private java.lang.String loginname;
	private java.lang.String username;
	private java.lang.String password;
	private java.lang.String salt;
	private java.util.Date registertime;
	private java.lang.Integer registertype;
	private Integer registerSource;
	private Integer registerBranchId;
	private java.lang.String registerInviteCode;
	private java.lang.String inviteCode;
	private java.util.Date firstLoginTime;
	private java.util.Date lastLoginTime;
	private java.lang.String lastLoginIp;
	private java.lang.Integer status;
	private Integer sourceActivityId;
	private Integer hasDownloadApp;
	private String fromKey;
	private String appChannelCode;
	private String behavior;
	private String appSourceCode;
	//columns END
	private Integer isValidation; //手机号是否验证（0，未验证；1，已验证）
	private java.util.Date updateTime;//更新时间

	public User(){
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "loginname", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getLoginname() {
		return this.loginname;
	}
	
	public void setLoginname(java.lang.String value) {
		this.loginname = value;
	}
	
	@Column(name = "username", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getUsername() {
		return this.username;
	}
	public void setIsValidation(Integer isValidation) {
		this.isValidation = isValidation;
	}
	public Integer getIsValidation() {
		return isValidation;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	@Column(name = "password", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	@Column(name = "salt", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getSalt() {
		return this.salt;
	}
	
	public void setSalt(java.lang.String value) {
		this.salt = value;
	}
	
	@Transient
	public String getRegistertimeString() {
		return DateConvertUtils.format(getRegistertime(), FORMAT_REGISTERTIME);
	}
	public void setRegistertimeString(String value) {
		setRegistertime(DateConvertUtils.parse(value, FORMAT_REGISTERTIME,java.util.Date.class));
	}
	
	@Column(name = "registertime", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getRegistertime() {
		return this.registertime;
	}
	
	public void setRegistertime(java.util.Date value) {
		this.registertime = value;
	}
	
	@Column(name = "registertype", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getRegistertype() {
		return this.registertype;
	}
	
	public void setRegistertype(java.lang.Integer value) {
		this.registertype = value;
	}

	@Column(name = "register_source", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getRegisterSource() {
		return this.registerSource;
	}
	
	public void setRegisterSource(Integer value) {
		this.registerSource = value;
	}
	
	@Transient
	public String getFirstLoginTimeString() {
		if(getFirstLoginTime()!=null){
			return DateConvertUtils.format(getFirstLoginTime(), FORMAT_FIRST_LOGIN_TIME);
		}else{
			return null;
		}
	}
	public void setFirstLoginTimeString(String value) {
		setFirstLoginTime(DateConvertUtils.parse(value, FORMAT_FIRST_LOGIN_TIME,java.util.Date.class));
	}
	
	@Column(name = "first_login_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getFirstLoginTime() {
		return this.firstLoginTime;
	}
	
	public void setFirstLoginTime(java.util.Date value) {
		this.firstLoginTime = value;
	}
	
	@Transient
	public String getLastLoginTimeString() {
		if(getLastLoginTime()!=null){
			return DateConvertUtils.format(getLastLoginTime(), FORMAT_LAST_LOGIN_TIME);
		}else{
			return null;
		}
	}
	public void setLastLoginTimeString(String value) {
		setLastLoginTime(DateConvertUtils.parse(value, FORMAT_LAST_LOGIN_TIME,java.util.Date.class));
	}
	
	@Column(name = "last_login_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getLastLoginTime() {
		return this.lastLoginTime;
	}
	
	public void setLastLoginTime(java.util.Date value) {
		this.lastLoginTime = value;
	}
	
	@Column(name = "last_login_ip", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getLastLoginIp() {
		return this.lastLoginIp;
	}
	
	public void setLastLoginIp(java.lang.String value) {
		this.lastLoginIp = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	@Column(name = "register_branch_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getRegisterBranchId() {
		return registerBranchId;
	}

	public void setRegisterBranchId(Integer registerBranchId) {
		this.registerBranchId = registerBranchId;
	}

	@Column(name = "register_invite_code", unique = false, nullable = true, insertable = true, updatable = true, length = 60)
	public java.lang.String getRegisterInviteCode() {
		return registerInviteCode;
	}

	public void setRegisterInviteCode(java.lang.String registerInviteCode) {
		this.registerInviteCode = registerInviteCode;
	}

	@Column(name = "invite_code", unique = false, nullable = true, insertable = true, updatable = true, length = 60)
	public java.lang.String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(java.lang.String inviteCode) {
		this.inviteCode = inviteCode;
	}

	@Column(name = "source_activity_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getSourceActivityId() {
		return sourceActivityId;
	}

	public void setSourceActivityId(Integer sourceActivityId) {
		this.sourceActivityId = sourceActivityId;
	}
	
	@Column(name = "has_download_app", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getHasDownloadApp() {
		return hasDownloadApp;
	}

	public void setHasDownloadApp(Integer hasDownloadApp) {
		this.hasDownloadApp = hasDownloadApp;
	}

	@Column(name = "from_key", unique = false, nullable = true, insertable = true, updatable = true, length = 63)
	public String getFromKey() {
		return fromKey;
	}

	public void setFromKey(String fromKey) {
		this.fromKey = fromKey;
	}
	
	@Column(name = "app_channel_code", unique = false, nullable = true, insertable = true, updatable = true, length = 63)
	public String getAppChannelCode() {
		return appChannelCode;
	}

	public void setAppChannelCode(String appChannelCode) {
		this.appChannelCode = appChannelCode;
	}

	@Column(name = "behavior", unique = false, nullable = true, insertable = true, updatable = true, length = 299)
	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}

	@Column(name = "app_source_code", unique = false, nullable = true, insertable = true, updatable = true, length = 299)
	public String getAppSourceCode() {
		return appSourceCode;
	}

	public void setAppSourceCode(String appSourceCode) {
		this.appSourceCode = appSourceCode;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Loginname",getLoginname())
			.append("Username",getUsername())
//			.append("Password",getPassword())
//			.append("Salt",getSalt())
			.append("Registertime",getRegistertime())
			.append("Registertype",getRegistertype())
			.append("FirstLoginTime",getFirstLoginTime())
			.append("LastLoginTime",getLastLoginTime())
			.append("LastLoginIp",getLastLoginIp())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof User == false) return false;
		if(this == obj) return true;
		User other = (User)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

