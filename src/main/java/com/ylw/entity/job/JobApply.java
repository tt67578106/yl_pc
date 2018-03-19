package com.ylw.entity.job;

import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylw.entity.IdEntity;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_job_apply")
public class JobApply extends IdEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "JobApply";
	public static final String ALIAS_ID = "主键ID";
	public static final String ALIAS_COMPANYID = "公司ID";
	public static final String ALIAS_JOBID = "职位ID";
	public static final String ALIAS_USERID = "用户ID";
	public static final String ALIAS_INTENTION = "报名意向";
	public static final String ALIAS_TYPE = "类型";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_APPLY_IP = "投递IP";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	public static final String ALIAS_ASSIGNER = "分配人";
	public static final String ALIAS_SOURCE = "报名来源";// （1：PC 2：WAP 3：ADMIN
														// 4：IOS 5：Android）
	public static final String ALIAS_NAME = "姓名";
	public static final String ALIAS_GENDER = "性别（1，男 2，女）";
	public static final String ALIAS_MOBILE = "手机";
	public static final String ALIAS_ID_CARD = "身份证号";
	public static final String ALIAS_EDUCATION = "学历（typeid=18）";
	public static final String ALIAS_INTENTION_PROVINCEID = "求职意向-省";
	public static final String ALIAS_INTENTION_CITYID = "求职意向-市";
	public static final String ALIAS_INTENTION_COUNTYID = "求职意向-区";
	public static final String ALIAS_INTENTION_POSITION = "求职意向-岗位类别";
	public static final String ALIAS_IS_UPDATE_RESUME = "是否同步到简历（0 ，不同步 1，同步）";
	public static final String ALIAS_IS_RECOMMEND = "是否推荐（1、自己报名 2、推荐别人报名）";
	public static final String ALIAS_RECOMMEND_USER_ID = "被推荐人id";
	public static final String ALIAS_SOURCE_ACTIVITY_ID = "报名来源-活动id";
	public static final String ALIAS_FROM_KEY = "加盟线appkey";
	public static final String ALIAS_APP_CHANNEL_CODE = "加盟线渠道code";
	public static final String ALIAS_BEHAVIOR="用户行为";
	public static final String ALIAS_APP_SOURCE_CODE = "来源code";

	// date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	public static final String FORMAT_MOMENT_TIME = "HH:mm";

	// columns START
	private java.lang.Integer companyid;
	private java.lang.Integer jobid;
	private JobBase jobbase;
	private java.lang.Integer userid;
	private java.lang.String intention;
	private Integer type;
	private java.lang.Integer status; 
	private java.lang.String applyIp;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private java.lang.Integer assigner;
	private Integer source;
	private String contactTime;
	private Integer isAacceptRec;
	private Integer branchId;
	private java.lang.String name;
	private java.lang.Integer gender;
	private java.lang.String mobile;
	private java.lang.String idCard;
	private java.lang.Integer education;
	private java.lang.Integer intentionProvinceid;
	private java.lang.Integer intentionCityid;
	private java.lang.Integer intentionCountyid;
	private java.lang.Integer intentionPosition;
	private Integer isUpdateResume;
	private Integer isRecommend;
	private java.lang.Integer recommendUserId;
	private Integer sourceActivityId;
	private String fromKey;
	private Date lastSynHrTime;
	private String appChannelCode;
	private String behavior;
	private String appSourceCode;
	private String scheduleDataCode;

	// columns END


	public JobApply() {
	}
	@Column(name = "source", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getSource() {
		return source;
	}
	
	public void setSource(Integer source) {
		this.source = source;
	}
	
	@Column(name = "branch_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	@Column(name = "is_accept_rec", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getIsAacceptRec() {
		return isAacceptRec;
	}
	
	public void setIsAacceptRec(Integer isAacceptRec) {
		this.isAacceptRec = isAacceptRec;
	}

	@Column(name = "companyid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(java.lang.Integer value) {
		this.companyid = value;
	}

	@Column(name = "jobid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getJobid() {
		return this.jobid;
	}

	public void setJobid(java.lang.Integer value) {
		this.jobid = value;
	}

	@Column(name = "userid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserid() {
		return this.userid;
	}

	public void setUserid(java.lang.Integer value) {
		this.userid = value;
	}

	@Column(name = "intention", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public java.lang.String getIntention() {
		return this.intention;
	}

	public void setIntention(java.lang.String value) {
		this.intention = value;
	}

	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer value) {
		this.type = value;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}

	@Column(name = "apply_ip", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getApplyIp() {
		return this.applyIp;
	}

	public void setApplyIp(java.lang.String value) {
		this.applyIp = value;
	}

	@Transient
	public String getCreateTimeString() {
		if(getCreateTime()!=null){
			return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
		}else{
			return null;
		}
	}

	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,
				java.util.Date.class));
	}

	@Transient
	public String getCreateMomentString() {
		if(getCreateTime()!=null){
			return DateConvertUtils.format(getCreateTime(), FORMAT_MOMENT_TIME);
		}else{
			return null;
		}
	}

	public void setCreateMomentString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_MOMENT_TIME,
				java.util.Date.class));
	}

	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	@Column(name = "last_syn_hr_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public Date getLastSynHrTime() {
		return lastSynHrTime;
	}
	public void setLastSynHrTime(Date lastSynHrTime) {
		this.lastSynHrTime = lastSynHrTime;
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
	
	@Column(name = "schedule_data_code", unique = false, nullable = true, insertable = true, updatable = true, length = 119)
	public String getScheduleDataCode() {
		return scheduleDataCode;
	}
	public void setScheduleDataCode(String scheduleDataCode) {
		this.scheduleDataCode = scheduleDataCode;
	}
	@Transient
	public String getLastSynHrTimeString() {
		if(getLastSynHrTime()!=null){
			return DateConvertUtils.format(getLastSynHrTime(), FORMAT_CREATE_TIME);
		}else{
			return null;
		}
	}

	public void setLastSynHrTimeString(String value) {
		setLastSynHrTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,
				java.util.Date.class));
	}

	@Transient
	public String getLastSynHrTimeMomentString() {
		if(getLastSynHrTime()!=null){
			return DateConvertUtils.format(getLastSynHrTime(), FORMAT_MOMENT_TIME);
		}else{
			return null;
		}
	}

	public void setLastSynHrTimeMomentString(String value) {
		setLastSynHrTime(DateConvertUtils.parse(value, FORMAT_MOMENT_TIME,
				java.util.Date.class));
	}
	
	@Transient
	public String getUpdateTimeString() {
		if(getUpdateTime()!=null){
		return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
		}
		return "";
	}

	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,
				java.util.Date.class));
	}
	
	@Transient
	public String getNumberCreateTimeString() {
		if(getCreateTime()!=null){
		return DateConvertUtils.format(getCreateTime(), "yyyyMMdd");
		}
		return "";
	}

	@Column(name = "update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}

	@Column(name = "assigner", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAssigner() {
		return this.assigner;
	}

	public void setAssigner(java.lang.Integer value) {
		this.assigner = value;
	}

	
	@Column(name = "contact_time", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public String getContactTime() {
		return contactTime;
	}

	public void setContactTime(String contactTime) {
		this.contactTime = contactTime;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("Id", getId())
				.append("Companyid", getCompanyid())
				// .append("Jobid",getJobid())
				.append("Userid", getUserid())
				.append("Intention", getIntention()).append("Type", getType())
				.append("Status", getStatus()).append("ApplyIp", getApplyIp())
				.append("CreateTime", getCreateTime())
				.append("UpdateTime", getUpdateTime())
				.append("Assigner", getAssigner()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof JobApply == false)
			return false;
		if (this == obj)
			return true;
		JobApply other = (JobApply) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	@ManyToOne
	@JoinColumn(name = "jobid", insertable = false, updatable = false)
	public JobBase getJobbase() {
		return jobbase;
	}

	public void setJobbase(JobBase jobbase) {
		this.jobbase = jobbase;
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
	
	@Column(name = "mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	@Column(name = "id_card", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public java.lang.String getIdCard() {
		return this.idCard;
	}
	
	public void setIdCard(java.lang.String value) {
		this.idCard = value;
	}
	
	@Column(name = "education", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getEducation() {
		return this.education;
	}
	
	public void setEducation(java.lang.Integer value) {
		this.education = value;
	}
	
	@Column(name = "intention_provinceid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIntentionProvinceid() {
		return this.intentionProvinceid;
	}
	
	public void setIntentionProvinceid(java.lang.Integer value) {
		this.intentionProvinceid = value;
	}
	
	@Column(name = "intention_cityid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIntentionCityid() {
		return this.intentionCityid;
	}
	
	public void setIntentionCityid(java.lang.Integer value) {
		this.intentionCityid = value;
	}
	
	@Column(name = "intention_countyid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIntentionCountyid() {
		return this.intentionCountyid;
	}
	
	public void setIntentionCountyid(java.lang.Integer value) {
		this.intentionCountyid = value;
	}
	
	@Column(name = "intention_position", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIntentionPosition() {
		return this.intentionPosition;
	}
	
	public void setIntentionPosition(java.lang.Integer value) {
		this.intentionPosition = value;
	}
	
	@Column(name = "is_update_resume", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsUpdateResume() {
		return this.isUpdateResume;
	}
	
	public void setIsUpdateResume(Integer value) {
		this.isUpdateResume = value;
	}
	
	@Column(name = "is_recommend", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsRecommend() {
		return this.isRecommend;
	}
	
	public void setIsRecommend(Integer value) {
		this.isRecommend = value;
	}
	
	@Column(name = "recommend_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getRecommendUserId() {
		return this.recommendUserId;
	}
	
	public void setRecommendUserId(java.lang.Integer value) {
		this.recommendUserId = value;
	}
	@Column(name = "source_activity_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getSourceActivityId() {
		return sourceActivityId;
	}
	public void setSourceActivityId(Integer sourceActivityId) {
		this.sourceActivityId = sourceActivityId;
	}
	@Column(name = "from_key", unique = false, nullable = true, insertable = true, updatable = true, length = 63)
	public String getFromKey() {
		return fromKey;
	}
	public void setFromKey(String fromKey) {
		this.fromKey = fromKey;
	}
	
}
