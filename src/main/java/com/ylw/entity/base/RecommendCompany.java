package com.ylw.entity.base;

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
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.Cai
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_recommend_company")
public class RecommendCompany extends IdEntity implements java.io.Serializable{
	
	private static final long serialVersionUID = -8120215110256397701L;
	//alias
	public static final String TABLE_ALIAS = "RecommendCompany";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_COMPANY_ID = "companyId";
	public static final String ALIAS_RECOMMEND_POSITION_CODE = "recommendPositionCode";
	public static final String ALIAS_BRANCH_ID = "branchId";
	public static final String ALIAS_IS_PUBLISH = "isPublish";
	public static final String ALIAS_PUBLISH_TIME = "publishTime";
	public static final String ALIAS_OFFLINE_TIME = "offlineTime";
	public static final String ALIAS_REMARK = "remark";
	public static final String ALIAS_SORTING = "sorting";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_CREATE_USER_ID = "createUserId";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	public static final String ALIAS_UPDATE_USER_ID = "updateUserId";
	
	//date formats

	//columns START
	private java.lang.String title;
	private Company company;
	private java.lang.String recommendPositionCode;
	private java.lang.Integer branchId;
	private Integer isPublish;
	private java.util.Date publishTime;
	private java.util.Date offlineTime;
	private java.lang.String remark;
	private java.lang.Integer sorting;
	private java.util.Date createTime;
	private java.lang.Integer createUserId;
	private java.util.Date updateTime;
	private java.lang.Integer updateUserId;
	//columns END


	public RecommendCompany(){
	}
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Column(name = "recommend_position_code", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getRecommendPositionCode() {
		return this.recommendPositionCode;
	}
	
	public void setRecommendPositionCode(java.lang.String value) {
		this.recommendPositionCode = value;
	}
	
	@Column(name = "branch_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getBranchId() {
		return this.branchId;
	}
	
	public void setBranchId(java.lang.Integer value) {
		this.branchId = value;
	}
	
	@Column(name = "is_publish", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getIsPublish() {
		return this.isPublish;
	}
	
	public void setIsPublish(Integer value) {
		this.isPublish = value;
	}
	@Column(name = "publish_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getPublishTime() {
		return this.publishTime;
	}
	
	public void setPublishTime(java.util.Date value) {
		this.publishTime = value;
	}
	
	
	@Column(name = "offline_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getOfflineTime() {
		return this.offlineTime;
	}
	
	public void setOfflineTime(java.util.Date value) {
		this.offlineTime = value;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	@Column(name = "sorting", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSorting() {
		return this.sorting;
	}
	
	public void setSorting(java.lang.Integer value) {
		this.sorting = value;
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
	
	@Column(name = "update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	@Column(name = "update_user_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUpdateUserId() {
		return this.updateUserId;
	}
	
	public void setUpdateUserId(java.lang.Integer value) {
		this.updateUserId = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("CompanyId",getCompany().getId())
			.append("RecommendPositionCode",getRecommendPositionCode())
			.append("BranchId",getBranchId())
			.append("IsPublish",getIsPublish())
			.append("PublishTime",getPublishTime())
			.append("OfflineTime",getOfflineTime())
			.append("Remark",getRemark())
			.append("Sorting",getSorting())
			.append("CreateTime",getCreateTime())
			.append("CreateUserId",getCreateUserId())
			.append("UpdateTime",getUpdateTime())
			.append("UpdateUserId",getUpdateUserId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RecommendCompany == false) return false;
		if(this == obj) return true;
		RecommendCompany other = (RecommendCompany)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
/*	@Autowired
	private JobBaseService jobBaseService;
	*//**
	 * 取得公司下岗位---发布时间排序--用作公司首页 [做**，最好的企业]
	 *//*
	public JobBase getJObBaseByCompany(Integer companyId,String type){
		return jobBaseService.getJobBaseByCompanyIdAndTypeInJobTypeOrderByCreatetime(companyId,type);
	}*/
}

