package com.ylw.entity.enterprise;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ylw.entity.IdEntity;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_certification_work_order")
public class CertificationWorkOrder extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SiteCertificationWorkOrder";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CODE = "工单号";
	public static final String ALIAS_TITLE = "title";
	public static final String ALIAS_COMPANY_ID = "企业id";
	public static final String ALIAS_CERTIFICATION_ID = "认证信息id";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_CREATE_USER_ID = "创建人";
	public static final String ALIAS_VALIDATE_USER_ID = "审核人";
	public static final String ALIAS_VALIDATE_TIME = "审核时间";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_REMARK = "备注:一般由审核人备注填写";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_VALIDATE_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.String code;
	private java.lang.String title;
	private java.lang.Integer companyId;
	private java.lang.Integer certificationId;
	private java.util.Date createTime;
	private java.lang.Integer createUserId;
	private java.lang.Integer validateUserId;
	private java.util.Date validateTime;
	private Integer status;
	private java.lang.String remark;
	//columns END


	public CertificationWorkOrder(){
	}

	public CertificationWorkOrder(
		java.lang.Integer id
	){
		this.id = id;
	}

	
	
	
	@Column(name = "code", unique = true, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "company_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCompanyId() {
		return this.companyId;
	}
	
	public void setCompanyId(java.lang.Integer value) {
		this.companyId = value;
	}
	
	@Column(name = "certification_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCertificationId() {
		return this.certificationId;
	}
	
	public void setCertificationId(java.lang.Integer value) {
		this.certificationId = value;
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
	
	@Column(name = "create_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCreateUserId() {
		return this.createUserId;
	}
	
	public void setCreateUserId(java.lang.Integer value) {
		this.createUserId = value;
	}
	
	@Column(name = "validate_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getValidateUserId() {
		return this.validateUserId;
	}
	
	public void setValidateUserId(java.lang.Integer value) {
		this.validateUserId = value;
	}
	
	@Transient
	public String getValidateTimeString() {
		return DateConvertUtils.format(getValidateTime(), FORMAT_VALIDATE_TIME);
	}
	public void setValidateTimeString(String value) {
		setValidateTime(DateConvertUtils.parse(value, FORMAT_VALIDATE_TIME,java.util.Date.class));
	}
	
	@Column(name = "validate_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getValidateTime() {
		return this.validateTime;
	}
	
	public void setValidateTime(java.util.Date value) {
		this.validateTime = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Code",getCode())
			.append("Title",getTitle())
			.append("CompanyId",getCompanyId())
			.append("CertificationId",getCertificationId())
			.append("CreateTime",getCreateTime())
			.append("CreateUserId",getCreateUserId())
			.append("ValidateUserId",getValidateUserId())
			.append("ValidateTime",getValidateTime())
			.append("Status",getStatus())
			.append("Remark",getRemark())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CertificationWorkOrder == false) return false;
		if(this == obj) return true;
		CertificationWorkOrder other = (CertificationWorkOrder)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

