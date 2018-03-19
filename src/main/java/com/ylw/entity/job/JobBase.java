package com.ylw.entity.job;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylw.entity.base.Company;
import com.ylw.entity.base.Image;
import com.ylw.entity.code.CodeAreaCity;
import com.ylw.entity.recruitment.RecruitmentJobDesc;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.HTMLInputFilter;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_job_base")
@JsonIgnoreProperties({"jobConfig","jobLabels"})
public class JobBase implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	//alias
	public Integer id;
	public static final String TABLE_ALIAS = "JobBase";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_COMPANYID = "公司ID";
	public static final String ALIAS_JOBNAME = "岗位名称";
	public static final String ALIAS_TITLE = "岗位标题";
	public static final String ALIAS_TOTALSALARY = "综合薪资";
	public static final String ALIAS_PROVINCEID = "所在省份";
	public static final String ALIAS_CITYID = "所在城市";
	public static final String ALIAS_COUNTYID = "所在区县";
	public static final String ALIAS_ADDRESS = "工作地点";
	public static final String ALIAS_RECRUITCOUNT = "招聘人数";
	public static final String ALIAS_APPLYCOUNT = "已申请人数";
	public static final String ALIAS_IS_PUBLISH = "是否发布";
	public static final String ALIAS_DEADLINE = "截止日期";
	public static final String ALIAS_COOPERATION_TYPE = "合作类型1:ERP同步2:企业自录3:网站自录4:抓取 5:优蓝快招";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_DETAIL_ID = "详情ID";
	public static final String ALIAS_JOB_TYPE = "岗位类型,多个以逗号分隔";
	public static final String ALIAS_JOB_LABEL = "岗位标签[多个以逗号分隔]";

	//date formats
	public static final String FORMAT_DEADLINE = DATE_FORMAT;
	public static final String FORMAT_CREATETIME = DATE_FORMAT;
	public static final String FORMAT_UPDATETIME = DATE_FORMAT;
	

	//columns START
	private Company company;
	private java.lang.String jobname;
	private java.lang.String title;
	private java.lang.String totalsalary;
	private java.lang.Integer provinceid;
	private CodeAreaCity city;
	private Image thumbnialImage;
	private java.lang.Integer countyid;
	private java.lang.String address;
	private java.lang.Integer recruitcount;
	private java.lang.Integer applycount;
	private JobConfig jobConfig;
	private java.util.Date deadline;
	private java.util.Date createtime;
	private java.util.Date updatetime;
	private JobDetail jobDetail;
	private java.lang.String jobType;
	private java.lang.String jobLabel;
	private java.lang.Integer createUserId;
	private java.lang.Integer updateUserId;
	private java.lang.Integer cooperationType;
	private RecruitmentJobDesc recruitmentJobDesc;
	private String titleSub;
	
	private java.lang.Integer cityid;
	//columns END

	public JobBase(){
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "companyid")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Column(name = "jobname", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getJobname() {
		return this.jobname;
	}
	
	public void setJobname(java.lang.String value) {
		this.jobname = value;
	}
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "totalsalary", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getTotalsalary() {
		return this.totalsalary;
	}
	
	public void setTotalsalary(java.lang.String value) {
		this.totalsalary = value;
	}
	
	@Column(name = "provinceid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getProvinceid() {
		return this.provinceid;
	}
	
	public void setProvinceid(java.lang.Integer value) {
		this.provinceid = value;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="thumbnial_image_id")
	public Image getThumbnialImage() {
		return thumbnialImage;
	}

	public void setThumbnialImage(Image thumbnialImage) {
		this.thumbnialImage = thumbnialImage;
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
	
	@Column(name = "recruitcount", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getRecruitcount() {
		return this.recruitcount;
	}
	
	public void setRecruitcount(java.lang.Integer value) {
		this.recruitcount = value;
	}
	
	@Column(name = "applycount", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getApplycount() {
		return this.applycount;
	}
	
	public void setApplycount(java.lang.Integer value) {
		this.applycount = value;
	}
	
	@OneToOne(optional=true,cascade=CascadeType.ALL, mappedBy="jobBase")
	public JobConfig getJobConfig() {
		return jobConfig;
	}

	public void setJobConfig(JobConfig jobConfig) {
		this.jobConfig = jobConfig;
	}

	@OneToOne(optional=true,cascade=CascadeType.ALL, mappedBy="jobBase")
	public JobDetail getJobDetail() {
		return jobDetail;
	}

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}
	
	@OneToOne(optional=true,cascade=CascadeType.ALL, mappedBy="jobBase")
	public RecruitmentJobDesc getRecruitmentJobDesc() {
		return recruitmentJobDesc;
	}

	public void setRecruitmentJobDesc(RecruitmentJobDesc recruitmentJobDesc) {
		this.recruitmentJobDesc = recruitmentJobDesc;
	}
	
	@Column(name = "deadline", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(java.util.Date value) {
		this.deadline = value;
	}
	
	@Column(name = "createtime", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	@Transient
	public String getCreatetimeString() {
		return DateConvertUtils.format(getCreatetime(), FORMAT_CREATETIME);
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	@Column(name = "updatetime", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}
	@Transient
	public String getUpdatetimeString() {
		if(getUpdatetime() !=null){
			return DateConvertUtils.format(getUpdatetime(), "yyyy-MM-dd");
		}else{
			return null;
		}
	}
	

	@Column(name = "job_type", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getJobType() {
		return this.jobType;
	}
	public void setJobType(java.lang.String jobType) {
		this.jobType = jobType;
	}

	@Column(name = "job_label", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getJobLabel() {
		return this.jobLabel;
	}
	
	public void setJobLabel(java.lang.String value) {
		this.jobLabel = value;
	}
	
	@Column(name = "create_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(java.lang.Integer createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "update_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(java.lang.Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	
	@Column(name = "cooperation_type", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public java.lang.Integer getCooperationType() {
		return cooperationType;
	}

	public void setCooperationType(java.lang.Integer cooperationType) {
		this.cooperationType = cooperationType;
	}
	
	@Transient
	public String getTitleSub(){
		titleSub = getTitle();
		if(StringUtils.isNotBlank(titleSub)){
			titleSub = HTMLInputFilter.Html2Text(titleSub);
			if(titleSub.length()>16){
				titleSub = titleSub.substring(0, 16)+"...";
			}
		}
		return titleSub;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Companyid",getCompany().getId())
			.append("Jobname",getJobname())
			.append("Title",getTitle())
			.append("Totalsalary",getTotalsalary())
			.append("jobType",getJobType())
			.append("Provinceid",getProvinceid())
			.append("Cityid",getCity())
			.append("Countyid",getCountyid())
			.append("Address",getAddress())
			.append("Recruitcount",getRecruitcount())
			.append("Applycount",getApplycount())
			.append("Deadline",getDeadline())
			.append("Createtime",getCreatetime())
			.append("Updatetime",getUpdatetime())
//			.append("DetailId",getDetailId())
			.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof JobBase == false) return false;
		if(this == obj) return true;
		JobBase other = (JobBase)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@Column(name = "cityid", unique = false, nullable = true, insertable = false, updatable = false, length = 10)
	public java.lang.Integer getCityid() {
		return cityid;
	}

	public void setCityid(java.lang.Integer cityid) {
		this.cityid = cityid;
	}

}

