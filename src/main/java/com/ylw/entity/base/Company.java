package com.ylw.entity.base;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylw.entity.code.CodeAreaCity;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.HTMLInputFilter;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_company")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler","scenes","introduction","city"})
public class Company implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	//alias
	public static final String TABLE_ALIAS = "Company";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_NAME = "企业名称";
	public static final String ALIAS_ABBREVIATION = "简称";
	public static final String ALIAS_VALIDATION = "是否已验证";
	public static final String ALIAS_INDUSTRYID = "所属行业";
	public static final String ALIAS_STAFFSCALE = "人员规模";
	public static final String ALIAS_PROVINCEID = "所在省份";
	public static final String ALIAS_CITYID = "所在城市";
	public static final String ALIAS_COUNTYID = "所在区县";
	public static final String ALIAS_ADDRESS = "详细地址";
	public static final String ALIAS_INTRODUCTION = "公司简介";
	public static final String ALIAS_ADVANTAGES = "企业优势";
	public static final String ALIAS_LOGO = "企业Logo";
	public static final String ALIAS_COOPERATION_TYPE = "合作类型1:ERP同步2:企业自录3:网站自录4:抓取 5:优蓝快招";
	public static final String ALIAS_ONE_SENTENCE = "一句话描述";
	public static final String ALIAS_BRIGHT_SPOT = "公司亮点";
	public static final String ALIAS_IS_STANDPAGE = "isStandpage";
	public static final String ALIAS_CERTIFICATION_ID = "认证详情";
	public static final String ALIAS_ISDEL = "是否已删除";
	public static final String ALIAS_CREATEBY = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATEBY = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_CONTACT_PERSON = "联系人";
	public static final String ALIAS_CONTACT_PHONE = "联系电话";
	public static final String ALIAS_EMAIL = "邮箱";
	public static final String ALIAS_WEBSITE = "网址";
	public static final String ALIAS_POINT_OF_PRAISE = "点赞数";
	public static final String ALIAS_SATISFACTION = "满意度";
	public static final String ALIAS_RECRUITMENT_JOB_COUNT = "在招岗位数量";
	
	
	//date formats
	public static final String FORMAT_CREATETIME = DATE_FORMAT;
	public static final String FORMAT_UPDATETIME = DATE_FORMAT;
	

	//columns START
	private Integer id;
	private java.lang.String name;
	private java.lang.String abbreviation;
	private Integer validation;
	private java.lang.Integer industryid;
	private Integer staffscale;
	private java.lang.Integer provinceid;
	private CodeAreaCity city;
	private java.lang.Integer countyid;
	private java.lang.String address;
	private java.lang.String introduction;
	private java.lang.String introductionView;
	private java.lang.String advantages;
	private Image logo;
	private java.lang.Integer isStandpage;
	private Integer cooperationType;
	private java.lang.String oneSentence;
	private java.lang.String brightSpot;
	private java.lang.String contactPerson;
	private java.lang.String contactPhone;
	private java.lang.String contactMobile;
	private java.lang.String email;
	private java.lang.String website;
	private java.lang.Integer pointOfPraise;
	private java.lang.String satisfaction;
	private java.lang.Integer isdel;
	private java.lang.Integer createby;
	private java.util.Date createtime;
	private java.lang.Integer updateby;
	private java.util.Date updatetime; 
	private Integer recruitmentJobCount;
	private Integer isLoan;
	//columns END
	private List<CompanyScene> scenes;


	public Company(){
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@OneToMany( mappedBy = "company")	
	public List<CompanyScene> getScenes() {
		return scenes;
	}

	public void setScenes(List<CompanyScene> scenes) {
		this.scenes = scenes;
	}



	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	@Column(name = "abbreviation", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getAbbreviation() {
		return this.abbreviation;
	}
	
	public void setAbbreviation(java.lang.String value) {
		this.abbreviation = value;
	}
	
	@Column(name = "validation", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getValidation() {
		return this.validation;
	}
	
	public void setValidation(Integer value) {
		this.validation = value;
	}
	
	@Column(name = "industryid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIndustryid() {
		return this.industryid;
	}
	@Column(name = "contact_mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(java.lang.String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public void setIndustryid(java.lang.Integer value) {
		this.industryid = value;
	}
	
	@Column(name = "staffscale", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getStaffscale() {
		return staffscale;
	}

	public void setStaffscale(Integer staffscale) {
		this.staffscale = staffscale;
	}

	@Column(name = "provinceid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getProvinceid() {
		return this.provinceid;
	}
	
	public void setProvinceid(java.lang.Integer value) {
		this.provinceid = value;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cityid")
	public CodeAreaCity getCity() {
		return city;
	}

	public void setCity(CodeAreaCity city) {
		this.city = city;
	}
	
	
	@Column(name = "countyid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCountyid() {
		return this.countyid;
	}
	
	public void setCountyid(java.lang.Integer value) {
		this.countyid = value;
	}
	
	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
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
	
	/**
	 * 暂定文本长度90
	 * @return
	 */
	@Transient
	public java.lang.String getintroductionView() {
		introductionView = getIntroduction();
		if(StringUtils.isNotBlank(introductionView)){
			introductionView = HTMLInputFilter.Html2Text(introductionView);
			if(introductionView.length()>50){
				introductionView = introductionView.substring(0, 50)+"...";
			}
		}
		return introductionView;
	}
	
	@Column(name = "advantages", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getAdvantages() {
		return this.advantages;
	}
	
	public void setAdvantages(java.lang.String value) {
		this.advantages = value;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="logo")
	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}
  
	@Column(name = "cooperation_type", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getCooperationType() {
		return this.cooperationType;
	}
	
	public void setCooperationType(Integer value) {
		this.cooperationType = value;
	}
	
	@Column(name = "one_sentence", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getOneSentence() {
		return this.oneSentence;
	}
	
	public void setOneSentence(java.lang.String value) {
		this.oneSentence = value;
	}
	
	@Column(name = "bright_spot", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getBrightSpot() {
		return this.brightSpot;
	}
	
	public void setBrightSpot(java.lang.String value) {
		this.brightSpot = value;
	}

	@Column(name = "is_standpage", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsStandpage() {
		return this.isStandpage;
	}

	public void setIsStandpage(java.lang.Integer value) {
		this.isStandpage = value;
	}
	@Column(name = "contact_person", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getContactPerson() {
		return this.contactPerson;
	}
	
	public void setContactPerson(java.lang.String value) {
		this.contactPerson = value;
	}
	
	@Column(name = "contact_phone", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getContactPhone() {
		if(StringUtils.isNotBlank(this.contactPhone)){
			return this.contactPhone.replace("null", "").replace("--", "");
		}else{
			return "4008-777-816";
		}
	}
	
	public void setContactPhone(java.lang.String value) {
		this.contactPhone = value;
	}
	
	@Column(name = "email", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	@Column(name = "website", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getWebsite() {
		return this.website;
	}
	
	public void setWebsite(java.lang.String value) {
		this.website = value;
	}
	
	@Column(name = "point_of_praise", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPointOfPraise() {
		return this.pointOfPraise;
	}
	
	public void setPointOfPraise(java.lang.Integer value) {
		this.pointOfPraise = value;
	}
	
	@Column(name = "isdel", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsdel() {
		return this.isdel;
	}
	
	public void setIsdel(java.lang.Integer value) {
		this.isdel = value;
	}
	
	@Column(name = "is_loan", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsLoan() {
		return isLoan;
	}

	public void setIsLoan(Integer isLoan) {
		this.isLoan = isLoan;
	}

	@Column(name = "createby", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCreateby() {
		return this.createby;
	}
	
	public void setCreateby(java.lang.Integer value) {
		this.createby = value;
	}
	
	@Transient
	public String getCreatetimeString() {
		return DateConvertUtils.format(getCreatetime(), FORMAT_CREATETIME);
	}
	public void setCreatetimeString(String value) {
		setCreatetime(DateConvertUtils.parse(value, FORMAT_CREATETIME,java.util.Date.class));
	}
	
	@Column(name = "createtime", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	@Column(name = "updateby", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUpdateby() {
		return this.updateby;
	}
	
	public void setUpdateby(java.lang.Integer value) {
		this.updateby = value;
	}

	@Transient
	public String getUpdatetimeString() {
		if(getUpdatetime() != null){
			return DateConvertUtils.format(getUpdatetime(), FORMAT_UPDATETIME);
		}
		return null;
	}
	public void setUpdatetimeString(String value) {
		setUpdatetime(DateConvertUtils.parse(value, FORMAT_UPDATETIME,java.util.Date.class));
	}
	
	@Column(name = "updatetime", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}

	@Column(name = "satisfaction", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getSatisfaction() {
		return this.satisfaction;
	}
	
	public void setSatisfaction(java.lang.String value) {
		this.satisfaction = value;
	}

	
	@Column(name = "recruitment_job_count", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getRecruitmentJobCount() {
		return recruitmentJobCount;
	}

	public void setRecruitmentJobCount(Integer recruitmentJobCount) {
		this.recruitmentJobCount = recruitmentJobCount;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Abbreviation",getAbbreviation())
			.append("Validation",getValidation())
			.append("Industryid",getIndustryid())
			.append("Staffscale",getStaffscale())
			.append("Provinceid",getProvinceid())
			.append("Cityid",getCity().getId())
			.append("Countyid",getCountyid())
			.append("Address",getAddress())
			.append("Introduction",getIntroduction())
			.append("Advantages",getAdvantages())
			.append("Logo",getLogo())
			.append("Isdel",getIsdel())
			.append("Createby",getCreateby())
			.append("Createtime",getCreatetime())
			.append("Updateby",getUpdateby())
			.append("Updatetime",getUpdatetime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Company == false) return false;
		if(this == obj) return true;
		Company other = (Company)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

