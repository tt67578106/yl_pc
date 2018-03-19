package com.ylw.entity.wanlixing;

import java.util.Date;

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
@Table(name = "act_wanlixing_registration")
public class ActWanlixingRegistration extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ActWanlixingRegistration";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CODE = "报名表编号";
	public static final String ALIAS_USER_ID = "网站用户id";
	public static final String ALIAS_INSITUTION = "院校名";
	public static final String ALIAS_TEAM_NAME = "团队名称";
	public static final String ALIAS_FULL_NAME = "发起人姓名";
	public static final String ALIAS_POST = "职务";
	public static final String ALIAS_TELEPHONE = "电话";
	public static final String ALIAS_MOBILE = "手机";
	public static final String ALIAS_EMAIL = "Email";
	public static final String ALIAS_DEPARTMENT_AND_PROFESSIONAL = "部门及所在系";
	public static final String ALIAS_PRINT_SCAN = "文件图片";
	
	//date formats
	

	//columns START
	private java.lang.String code;
	private java.lang.Integer userId;
	private java.lang.String insitution;
	private java.lang.String teamName;
	private java.lang.String fullName;
	private java.lang.String post;
	private java.lang.String telephone;
	private java.lang.String mobile;
	private java.lang.String email;
	private java.lang.String departmentAndProfessional;
	private java.lang.String printScan;
	private java.lang.String idCardScan;
	private java.lang.String studentCardScan;
	private java.lang.String specialty;
	private Date createTime;
	//columns END


	public ActWanlixingRegistration(){
	}

	public ActWanlixingRegistration(
		java.lang.Integer id
	){
		this.id = id;
	}

	
	
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "code", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	@Column(name = "user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	@Column(name = "insitution", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getInsitution() {
		return this.insitution;
	}
	
	public void setInsitution(java.lang.String value) {
		this.insitution = value;
	}
	
	@Column(name = "team_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getTeamName() {
		return this.teamName;
	}
	
	public void setTeamName(java.lang.String value) {
		this.teamName = value;
	}
	
	@Column(name = "full_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getFullName() {
		return this.fullName;
	}
	
	public void setFullName(java.lang.String value) {
		this.fullName = value;
	}
	
	@Column(name = "post", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getPost() {
		return post;
	}

	public void setPost(java.lang.String post) {
		this.post = post;
	}
	
	@Column(name = "telephone", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
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
	
	@Column(name = "email", unique = false, nullable = true, insertable = true, updatable = true, length = 60)
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	@Column(name = "department_and_professional", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getDepartmentAndProfessional() {
		return this.departmentAndProfessional;
	}
	
	public void setDepartmentAndProfessional(java.lang.String value) {
		this.departmentAndProfessional = value;
	}
	
	@Column(name = "print_scan", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getPrintScan() {
		return this.printScan;
	}
	
	public void setPrintScan(java.lang.String value) {
		this.printScan = value;
	}
	

	public java.lang.String getIdCardScan() {
		return idCardScan;
	}

	public void setIdCardScan(java.lang.String idCardScan) {
		this.idCardScan = idCardScan;
	}

	public java.lang.String getStudentCardScan() {
		return studentCardScan;
	}

	public void setStudentCardScan(java.lang.String studentCardScan) {
		this.studentCardScan = studentCardScan;
	}

	public java.lang.String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(java.lang.String specialty) {
		this.specialty = specialty;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Code",getCode())
			.append("UserId",getUserId())
			.append("Insitution",getInsitution())
			.append("TeamName",getTeamName())
			.append("FullName",getFullName())
			.append("Post",getPost())
			.append("Telephone",getTelephone())
			.append("Mobile",getMobile())
			.append("Email",getEmail())
			.append("DepartmentAndProfessional",getDepartmentAndProfessional())
			.append("PrintScan",getPrintScan())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ActWanlixingRegistration == false) return false;
		if(this == obj) return true;
		ActWanlixingRegistration other = (ActWanlixingRegistration)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

