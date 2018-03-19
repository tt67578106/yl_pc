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
@Table(name = "site_certification")
public class Certification extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SiteCertification";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CERTIFICATION_TYPE = "认证类型";
	public static final String ALIAS_CERTIFICATION_METHOD = "认证方式";
	public static final String ALIAS_CERTIFICATION_LEVEL = "认证等级";
	public static final String ALIAS_ENTERPRISE_FULL_NAME = "企业全称";
	public static final String ALIAS_ORGANIZATION_CODE = "组织机构代码";
	public static final String ALIAS_BUSINESS_LICENSE_NUM = "工商执照注册号";
	public static final String ALIAS_OPENING_PERMIT_IMG = "开户许可证";
	public static final String ALIAS_LEGAL_REPRESENTATIVE = "法定代表人/企业负责人姓名";
	public static final String ALIAS_LEGAL_ID_CARD = "法人身份证号";
	public static final String ALIAS_GENERAL_BUSINESS_SCOPE = "经营范围(一般经营范围)";
	public static final String ALIAS_PRE_LICENSING_SCOPE = "经营范围(前置许可经营范围)";
	public static final String ALIAS_ENTERPRISE_SCALE = "企业规模";
	public static final String ALIAS_ENTERPRISE_ACCOUNT_NAME = "企业开户名称";
	public static final String ALIAS_ENTERPRISE_BANK = "企业开户银行";
	public static final String ALIAS_ENTERPRISE_BANK_BRANCH = "开户行支行";
	public static final String ALIAS_ENTERPRISE_BANK_ACCOUNT = "企业银行账号";
	public static final String ALIAS_APPLICANT_NAME = "认证申请者姓名";
	public static final String ALIAS_APPLICANT_DEPARTMENT_AND_POST = "认证申请者部门与职位";
	public static final String ALIAS_APPLICANT_MOBILE = "认证申请者手机号码";
	public static final String ALIAS_APPLICANT_TELEPHONE = "认证申请者座机";
	public static final String ALIAS_APPLICANT_EMAIL = "认证申请者电子邮箱";
	public static final String ALIAS_APPLICANT_ID_CARD = "认证申请者身份证号码";
	public static final String ALIAS_APPLICANT_ID_CARD_FRONT_IMG = "认证申请者身份证件(正面)";
	public static final String ALIAS_APPLICANT_ID_CARD_BACK_IMG = "认证申请者身份证件(反面)";
	public static final String ALIAS_ORGANIZATION_CODE_IMG = "组织机构代码证";
	public static final String ALIAS_BUSINESS_LICENSE_IMG = "企业工商营业执照";
	public static final String ALIAS_APPLICATION_LETER_IMG = "申请公函";
	public static final String ALIAS_OTHER_DOCUMENT_IMG = "其他证明材料";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_STATUS = "验证状态";
	public static final String ALIAS_LEGAL_ID_CARD_FRONT_IMG="法人身份证正面";
	public static final String ALIAS_LEGAL_ID_CARD_BACK_IMG="法人身份证反面";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT_TIME;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	

	//columns START
	private Integer certificationType;
	private Integer certificationMethod;
	private Integer companyId;
	private Integer certificationLevel;
	private java.lang.String enterpriseFullName;
	private java.lang.String organizationCode;
	private java.lang.String businessLicenseNum;
	private java.lang.String openingPermitImg;
	private java.lang.String legalRepresentative;
	private java.lang.String legalIdCard;
	private java.lang.String generalBusinessScope;
	private java.lang.String preLicensingScope;
	private java.lang.Integer enterpriseScale;
	private java.lang.String enterpriseAccountName;
	private java.lang.String enterpriseBank;
	private java.lang.String enterpriseBankBranch;
	private java.lang.String enterpriseBankAccount;
	private java.lang.String applicantName;
	private java.lang.String applicantDepartmentAndPost;
	private java.lang.String applicantMobile;
	private java.lang.String applicantTelephone;
	private java.lang.String applicantEmail;
	private java.lang.String applicantIdCard;
	private java.lang.String applicantIdCardFrontImg;
	private java.lang.String applicantIdCardBackImg;
	private java.lang.String organizationCodeImg;
	private java.lang.String businessLicenseImg;
	private java.lang.String applicationLeterImg;
	private java.lang.String otherDocumentImg;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private Integer status;
	private String legalIdCardFrontImg;
	private String legalIdCardBackImg;
	//columns END


	public Certification(){
	}

	public Certification(java.lang.Integer id) {
		this.id = id;
	}

	
	
	
	@Column(name = "certification_type", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getCertificationType() {
		return this.certificationType;
	}
	
	public void setCertificationType(Integer value) {
		this.certificationType = value;
	}
	
	@Column(name = "certification_method", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getCertificationMethod() {
		return this.certificationMethod;
	}
	
	public void setCertificationMethod(Integer value) {
		this.certificationMethod = value;
	}
	
	@Column(name = "certification_level", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getCertificationLevel() {
		return this.certificationLevel;
	}
	
	public void setCertificationLevel(Integer value) {
		this.certificationLevel = value;
	}
	
	@Column(name = "enterprise_full_name", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getEnterpriseFullName() {
		return this.enterpriseFullName;
	}
	
	public void setEnterpriseFullName(java.lang.String value) {
		this.enterpriseFullName = value;
	}
	
	@Column(name = "organization_code", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getOrganizationCode() {
		return this.organizationCode;
	}
	
	public void setOrganizationCode(java.lang.String value) {
		this.organizationCode = value;
	}
	
	@Column(name = "business_license_num", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getBusinessLicenseNum() {
		return this.businessLicenseNum;
	}
	
	public void setBusinessLicenseNum(java.lang.String value) {
		this.businessLicenseNum = value;
	}
	
	@Column(name = "opening_permit_img", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getOpeningPermitImg() {
		return this.openingPermitImg;
	}
	
	public void setOpeningPermitImg(java.lang.String value) {
		this.openingPermitImg = value;
	}
	
	@Column(name = "legal_representative", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getLegalRepresentative() {
		return this.legalRepresentative;
	}
	
	public void setLegalRepresentative(java.lang.String value) {
		this.legalRepresentative = value;
	}
	
	@Column(name = "legal_id_card", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getLegalIdCard() {
		return this.legalIdCard;
	}
	
	public void setLegalIdCard(java.lang.String value) {
		this.legalIdCard = value;
	}
	
	@Column(name = "general_business_scope", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getGeneralBusinessScope() {
		return this.generalBusinessScope;
	}
	
	public void setGeneralBusinessScope(java.lang.String value) {
		this.generalBusinessScope = value;
	}
	
	@Column(name = "pre_licensing_scope", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getPreLicensingScope() {
		return this.preLicensingScope;
	}
	
	public void setPreLicensingScope(java.lang.String value) {
		this.preLicensingScope = value;
	}
	
	@Column(name = "enterprise_scale", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getEnterpriseScale() {
		return this.enterpriseScale;
	}
	
	public void setEnterpriseScale(java.lang.Integer value) {
		this.enterpriseScale = value;
	}
	
	@Column(name = "company_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
	@Column(name = "enterprise_account_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getEnterpriseAccountName() {
		return this.enterpriseAccountName;
	}
	
	public void setEnterpriseAccountName(java.lang.String value) {
		this.enterpriseAccountName = value;
	}
	
	@Column(name = "enterprise_bank", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getEnterpriseBank() {
		return this.enterpriseBank;
	}
	
	public void setEnterpriseBank(java.lang.String value) {
		this.enterpriseBank = value;
	}
	
	@Column(name = "enterprise_bank_branch", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getEnterpriseBankBranch() {
		return this.enterpriseBankBranch;
	}
	
	public void setEnterpriseBankBranch(java.lang.String value) {
		this.enterpriseBankBranch = value;
	}
	
	@Column(name = "enterprise_bank_account", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getEnterpriseBankAccount() {
		return this.enterpriseBankAccount;
	}
	
	public void setEnterpriseBankAccount(java.lang.String value) {
		this.enterpriseBankAccount = value;
	}
	
	@Column(name = "applicant_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getApplicantName() {
		return this.applicantName;
	}
	
	public void setApplicantName(java.lang.String value) {
		this.applicantName = value;
	}
	
	@Column(name = "applicant_department_and_post", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getApplicantDepartmentAndPost() {
		return this.applicantDepartmentAndPost;
	}
	
	public void setApplicantDepartmentAndPost(java.lang.String value) {
		this.applicantDepartmentAndPost = value;
	}
	
	@Column(name = "applicant_mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getApplicantMobile() {
		return this.applicantMobile;
	}
	
	public void setApplicantMobile(java.lang.String value) {
		this.applicantMobile = value;
	}
	
	@Column(name = "applicant_telephone", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getApplicantTelephone() {
		return this.applicantTelephone;
	}
	
	public void setApplicantTelephone(java.lang.String value) {
		this.applicantTelephone = value;
	}
	
	@Column(name = "applicant_email", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getApplicantEmail() {
		return this.applicantEmail;
	}
	
	public void setApplicantEmail(java.lang.String value) {
		this.applicantEmail = value;
	}
	
	@Column(name = "applicant_id_card", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getApplicantIdCard() {
		return this.applicantIdCard;
	}
	
	public void setApplicantIdCard(java.lang.String value) {
		this.applicantIdCard = value;
	}
	
	@Column(name = "applicant_id_card_front_img", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getApplicantIdCardFrontImg() {
		return this.applicantIdCardFrontImg;
	}
	
	public void setApplicantIdCardFrontImg(java.lang.String value) {
		this.applicantIdCardFrontImg = value;
	}
	@Column(name = "legal_id_card_front_img", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public String getLegalIdCardFrontImg() {
		return legalIdCardFrontImg;
	}

	public void setLegalIdCardFrontImg(String legalIdCardFrontImg) {
		this.legalIdCardFrontImg = legalIdCardFrontImg;
	}
	@Column(name = "legal_id_card_back_img", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public String getLegalIdCardBackImg() {
		return legalIdCardBackImg;
	}

	public void setLegalIdCardBackImg(String legalIdCardBackImg) {
		this.legalIdCardBackImg = legalIdCardBackImg;
	}

	
	@Column(name = "applicant_id_card_back_img", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getApplicantIdCardBackImg() {
		return this.applicantIdCardBackImg;
	}
	
	public void setApplicantIdCardBackImg(java.lang.String value) {
		this.applicantIdCardBackImg = value;
	}
	
	@Column(name = "organization_code_img", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getOrganizationCodeImg() {
		return this.organizationCodeImg;
	}
	
	public void setOrganizationCodeImg(java.lang.String value) {
		this.organizationCodeImg = value;
	}
	
	@Column(name = "business_license_img", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getBusinessLicenseImg() {
		return this.businessLicenseImg;
	}
	
	public void setBusinessLicenseImg(java.lang.String value) {
		this.businessLicenseImg = value;
	}
	
	@Column(name = "application_leter_img", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getApplicationLeterImg() {
		return this.applicationLeterImg;
	}
	
	public void setApplicationLeterImg(java.lang.String value) {
		this.applicationLeterImg = value;
	}
	
	@Column(name = "other_document_img", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getOtherDocumentImg() {
		return this.otherDocumentImg;
	}
	
	public void setOtherDocumentImg(java.lang.String value) {
		this.otherDocumentImg = value;
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
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("CertificationType",getCertificationType())
			.append("CertificationMethod",getCertificationMethod())
			.append("CertificationLevel",getCertificationLevel())
			.append("EnterpriseFullName",getEnterpriseFullName())
			.append("OrganizationCode",getOrganizationCode())
			.append("BusinessLicenseNum",getBusinessLicenseNum())
			.append("OpeningPermitImg",getOpeningPermitImg())
			.append("LegalRepresentative",getLegalRepresentative())
			.append("LegalIdCard",getLegalIdCard())
			.append("GeneralBusinessScope",getGeneralBusinessScope())
			.append("PreLicensingScope",getPreLicensingScope())
			.append("EnterpriseScale",getEnterpriseScale())
			.append("EnterpriseAccountName",getEnterpriseAccountName())
			.append("EnterpriseBank",getEnterpriseBank())
			.append("EnterpriseBankBranch",getEnterpriseBankBranch())
			.append("EnterpriseBankAccount",getEnterpriseBankAccount())
			.append("ApplicantName",getApplicantName())
			.append("ApplicantDepartmentAndPost",getApplicantDepartmentAndPost())
			.append("ApplicantMobile",getApplicantMobile())
			.append("ApplicantTelephone",getApplicantTelephone())
			.append("ApplicantEmail",getApplicantEmail())
			.append("ApplicantIdCard",getApplicantIdCard())
			.append("ApplicantIdCardFrontImg",getApplicantIdCardFrontImg())
			.append("ApplicantIdCardBackImg",getApplicantIdCardBackImg())
			.append("OrganizationCodeImg",getOrganizationCodeImg())
			.append("BusinessLicenseImg",getBusinessLicenseImg())
			.append("ApplicationLeterImg",getApplicationLeterImg())
			.append("OtherDocumentImg",getOtherDocumentImg())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Certification == false) return false;
		if(this == obj) return true;
		Certification other = (Certification)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

