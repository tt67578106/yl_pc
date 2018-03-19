package com.ylw.entity.base;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ylw.entity.IdEntity;
import com.ylw.entity.code.CodeAreaCity;
import com.ylw.entity.code.CodeAreaDistrict;
import com.ylw.entity.code.CodeAreaProvince;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_resume")
public class Resume extends IdEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "Resume";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_USERID = "用户ID";
	public static final String ALIAS_NAME = "姓名";
	public static final String ALIAS_GENDER = "性别";
	public static final String ALIAS_AGE = "年龄";
	public static final String ALIAS_NATION = "民族";
	public static final String ALIAS_NATIVE_PROVINCEID = "籍贯-省";
	public static final String ALIAS_NATIVE_CITYID = "籍贯-市";
	public static final String ALIAS_NATIVE_COUNTYID = "籍贯-区县";
	public static final String ALIAS_RESIDENT_PROVINCEID = "居住地-省";
	public static final String ALIAS_RESIDENT_CITYID = "居住地-市";
	public static final String ALIAS_RESIDENT_COUNTYID = "居住地-区县";
	public static final String ALIAS_IMGID = "照片";
	public static final String ALIAS_RESIDENT_ADDRESS = "居住地-详细";
	public static final String ALIAS_MOBILE = "电话";
	public static final String ALIAS_QQ = "QQ";
	public static final String ALIAS_ID_CARD = "身份证号";
	public static final String ALIAS_INTENTION_INDUSTRY = "求职意向-行业";
	public static final String ALIAS_INTENTION_POSITION = "求职意向-岗位";
	public static final String ALIAS_INTENTION_SALARY = "期望薪资";
	public static final String ALIAS_COMMENT = "简历备注";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_EDUCATION = "学历";
	public static final String ALIAS_RESUMECODE = "简历唯一性";

	
	// date formats
	public static final String FORMAT_CREATETIME = DATE_FORMAT;
	public static final String FORMAT_UPDATETIME = DATE_FORMAT;

	// columns START
	private java.lang.Integer userid;
	private java.lang.String name;
	private java.lang.Integer gender;
	private java.lang.Integer age;
	private java.lang.Integer nation;
	private java.lang.Integer nativeProvinceid;
	private CodeAreaProvince nativeprovince;
	private java.lang.Integer nativeCityid;
	private CodeAreaCity nativecity;
	private java.lang.Integer nativeCountyid;
	private CodeAreaDistrict nativecountry;
	private java.lang.Integer residentProvinceid;
	private CodeAreaProvince residentprovince;
	private java.lang.Integer residentCityid;
	private CodeAreaCity residentcity;
	private java.lang.Integer residentCountyid;
	private CodeAreaDistrict residentcounty;
	private Image image;
	private java.lang.String residentAddress;
	private java.lang.String mobile;
	private java.lang.String qq;
	private java.lang.String idCard;
	private java.lang.Integer intentionIndustry;
	private java.lang.Integer intentionPosition;
	private java.lang.String intentionSalary;
	private java.lang.String comment;
	private java.util.Date createtime;
	private java.util.Date updatetime;
	private java.lang.Integer education;
	private String resumeCode; //ERP交互字段
	private java.lang.Integer jobTargetProvinceid;
	private java.lang.Integer jobTargetCityid;
	private java.lang.Integer jobTargetCountyid;
	private String jobTarget;
	private Integer isSynHr;
	
	private java.lang.String startWorkYear;//工作年限
	private java.lang.Integer employmentStatus;//求职状态
	private java.lang.Integer maritalStatus;//婚姻状况
	
	private java.lang.String nativeProvinceName;
	private java.lang.String nativeCityName;
	private java.lang.String nativeCountyName;
	private java.lang.String residentProvinceName;
	private java.lang.String residentCityName;
	private java.lang.String residentCountyName;
	private java.lang.String jobTargetProvinceName;
	private java.lang.String jobTargetCityName;
	private java.lang.String jobTargetCountyName;
	// columns END

	@Column(name = "education", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getEducation() {
		return education;
	}

	public void setEducation(java.lang.Integer education) {
		this.education = education;
	}

	@Column(name = "is_syn_hr", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getIsSynHr() {
		return isSynHr;
	}

	public void setIsSynHr(Integer isSynHr) {
		this.isSynHr = isSynHr;
	}

	public Resume() {
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "native_provinceid", insertable = false, updatable = false)
	public CodeAreaProvince getNativeprovince() {
		return nativeprovince;
	}

	public void setNativeprovince(CodeAreaProvince nativeprovince) {
		this.nativeprovince = nativeprovince;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "native_cityid", insertable = false, updatable = false)
	public CodeAreaCity getNativecity() {
		return nativecity;
	}

	public void setNativecity(CodeAreaCity nativecity) {
		this.nativecity = nativecity;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "native_countyid", insertable = false, updatable = false)
	public CodeAreaDistrict getNativecountry() {
		return nativecountry;
	}

	public void setNativecountry(CodeAreaDistrict nativecountry) {
		this.nativecountry = nativecountry;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "resident_provinceid", insertable = false, updatable = false)
	public CodeAreaProvince getResidentprovince() {
		return residentprovince;
	}

	public void setResidentprovince(CodeAreaProvince residentprovince) {
		this.residentprovince = residentprovince;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "resident_cityid", insertable = false, updatable = false)
	public CodeAreaCity getResidentcity() {
		return residentcity;
	}

	public void setResidentcity(CodeAreaCity residentcity) {
		this.residentcity = residentcity;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "resident_countyid", insertable = false, updatable = false)
	public CodeAreaDistrict getResidentcounty() {
		return residentcounty;
	}
	@Column(name = "job_target", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getJobTarget() {
		return jobTarget;
	}

	public void setJobTarget(String jobTarget) {
		this.jobTarget = jobTarget;
	}

	public void setResidentcounty(CodeAreaDistrict residentcounty) {
		this.residentcounty = residentcounty;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "imgid")
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Column(name = "userid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer value) {
		this.userid = value;
	}

	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	@Column(name = "gender", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getGender() {
		return this.gender;
	}

	public void setGender(java.lang.Integer value) {
		this.gender = value;
	}

	@Column(name = "age", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAge() {
		return this.age;
	}

	public void setAge(java.lang.Integer value) {
		this.age = value;
	}

	@Column(name = "nation", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getNation() {
		return this.nation;
	}

	public void setNation(java.lang.Integer value) {
		this.nation = value;
	}

	@Column(name = "native_provinceid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getNativeProvinceid() {
		return this.nativeProvinceid;
	}

	public void setNativeProvinceid(java.lang.Integer value) {
		this.nativeProvinceid = value;
	}

	@Column(name = "native_cityid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getNativeCityid() {
		return this.nativeCityid;
	}

	public void setNativeCityid(java.lang.Integer value) {
		this.nativeCityid = value;
	}

	@Column(name = "native_countyid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getNativeCountyid() {
		return this.nativeCountyid;
	}

	public void setNativeCountyid(java.lang.Integer value) {
		this.nativeCountyid = value;
	}

	@Column(name = "resident_provinceid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getResidentProvinceid() {
		return this.residentProvinceid;
	}

	public void setResidentProvinceid(java.lang.Integer value) {
		this.residentProvinceid = value;
	}

	@Column(name = "resident_cityid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getResidentCityid() {
		return this.residentCityid;
	}

	public void setResidentCityid(java.lang.Integer value) {
		this.residentCityid = value;
	}

	@Column(name = "resident_countyid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getResidentCountyid() {
		return this.residentCountyid;
	}

	public void setResidentCountyid(java.lang.Integer value) {
		this.residentCountyid = value;
	}

	@Column(name = "resident_address", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getResidentAddress() {
		return this.residentAddress;
	}

	public void setResidentAddress(java.lang.String value) {
		this.residentAddress = value;
	}

	@Column(name = "mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public java.lang.String getMobile() {
		return this.mobile;
	}

	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}

	@Column(name = "qq", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getQq() {
		return this.qq;
	}

	public void setQq(java.lang.String value) {
		this.qq = value;
	}

	@Column(name = "id_card", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public java.lang.String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(java.lang.String value) {
		this.idCard = value;
	}

	@Column(name = "intention_industry", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIntentionIndustry() {
		return this.intentionIndustry;
	}

	public void setIntentionIndustry(java.lang.Integer value) {
		this.intentionIndustry = value;
	}

	@Column(name = "intention_position", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIntentionPosition() {
		return this.intentionPosition;
	}

	public void setIntentionPosition(java.lang.Integer value) {
		this.intentionPosition = value;
	}

	@Column(name = "intention_salary", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public java.lang.String getIntentionSalary() {
		return this.intentionSalary;
	}

	public void setIntentionSalary(java.lang.String value) {
		this.intentionSalary = value;
	}

	@Column(name = "comment", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getComment() {
		return this.comment;
	}

	public void setComment(java.lang.String value) {
		this.comment = value;
	}

	
	
	@Column(name = "job_target_provinceid", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.Integer getJobTargetProvinceid() {
		return jobTargetProvinceid;
	}

	public void setJobTargetProvinceid(java.lang.Integer jobTargetProvinceid) {
		this.jobTargetProvinceid = jobTargetProvinceid;
	}
	@Column(name = "job_target_cityid", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.Integer getJobTargetCityid() {
		return jobTargetCityid;
	}

	public void setJobTargetCityid(java.lang.Integer jobTargetCityid) {
		this.jobTargetCityid = jobTargetCityid;
	}
	
	@Column(name = "job_target_countyid", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.Integer getJobTargetCountyid() {
		return jobTargetCountyid;
	}

	public void setJobTargetCountyid(java.lang.Integer jobTargetCountyid) {
		this.jobTargetCountyid = jobTargetCountyid;
	}

	@Transient
	public String getCreatetimeString() {
		if(getCreatetime()!=null){
			return DateConvertUtils.format(getCreatetime(), FORMAT_CREATETIME);
		}else{
			return null;
		}
	}

	public void setCreatetimeString(String value) {
		setCreatetime(DateConvertUtils.parse(value, FORMAT_CREATETIME, java.util.Date.class));
	}

	@Column(name = "createtime", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	@Transient
	public String getUpdatetimeString() {
		if(getUpdatetime()!=null){
			return DateConvertUtils.format(getUpdatetime(), FORMAT_UPDATETIME);
		}else{
			return null;
		}
	}

	public void setUpdatetimeString(String value) {
		setUpdatetime(DateConvertUtils.parse(value, FORMAT_UPDATETIME, java.util.Date.class));
	}

	@Column(name = "updatetime", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("Id", getId()).append("Userid", getUserid()).append("Name", getName()).append("Gender", getGender()).append("Age", getAge()).append("Nation", getNation()).append("NativeProvinceid", getNativeProvinceid()).append("NativeCityid", getNativeCityid()).append("NativeCountyid", getNativeCountyid()).append("ResidentProvinceid", getResidentProvinceid()).append("ResidentCityid", getResidentCityid()).append("ResidentCountyid", getResidentCountyid())
		// .append("Imgid",getImgid())
				.append("ResidentAddress", getResidentAddress()).append("Mobile", getMobile()).append("Qq", getQq()).append("IdCard", getIdCard()).append("IntentionIndustry", getIntentionIndustry()).append("IntentionPosition", getIntentionPosition()).append("IntentionSalary", getIntentionSalary()).append("Comment", getComment()).append("Createtime", getCreatetime()).append("Updatetime", getUpdatetime()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Resume == false)
			return false;
		if (this == obj)
			return true;
		Resume other = (Resume) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	public String getResumeCode() {
		return resumeCode;
	}

	public void setResumeCode(String resumeCode) {
		this.resumeCode = resumeCode;
	}

	public java.lang.String getStartWorkYear() {
		return startWorkYear;
	}

	public void setStartWorkYear(java.lang.String startWorkYear) {
		this.startWorkYear = startWorkYear;
	}

	public java.lang.Integer getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(java.lang.Integer employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public java.lang.Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(java.lang.Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public java.lang.String getNativeProvinceName() {
		return nativeProvinceName;
	}

	public void setNativeProvinceName(java.lang.String nativeProvinceName) {
		this.nativeProvinceName = nativeProvinceName;
	}

	public java.lang.String getNativeCityName() {
		return nativeCityName;
	}

	public void setNativeCityName(java.lang.String nativeCityName) {
		this.nativeCityName = nativeCityName;
	}

	public java.lang.String getNativeCountyName() {
		return nativeCountyName;
	}

	public void setNativeCountyName(java.lang.String nativeCountyName) {
		this.nativeCountyName = nativeCountyName;
	}

	public java.lang.String getResidentProvinceName() {
		return residentProvinceName;
	}

	public void setResidentProvinceName(java.lang.String residentProvinceName) {
		this.residentProvinceName = residentProvinceName;
	}

	public java.lang.String getResidentCityName() {
		return residentCityName;
	}

	public void setResidentCityName(java.lang.String residentCityName) {
		this.residentCityName = residentCityName;
	}

	public java.lang.String getResidentCountyName() {
		return residentCountyName;
	}

	public void setResidentCountyName(java.lang.String residentCountyName) {
		this.residentCountyName = residentCountyName;
	}

	public java.lang.String getJobTargetProvinceName() {
		return jobTargetProvinceName;
	}

	public void setJobTargetProvinceName(java.lang.String jobTargetProvinceName) {
		this.jobTargetProvinceName = jobTargetProvinceName;
	}

	public java.lang.String getJobTargetCityName() {
		return jobTargetCityName;
	}

	public void setJobTargetCityName(java.lang.String jobTargetCityName) {
		this.jobTargetCityName = jobTargetCityName;
	}

	public java.lang.String getJobTargetCountyName() {
		return jobTargetCountyName;
	}

	public void setJobTargetCountyName(java.lang.String jobTargetCountyName) {
		this.jobTargetCountyName = jobTargetCountyName;
	}
}
