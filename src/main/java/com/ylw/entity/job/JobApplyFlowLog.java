package com.ylw.entity.job;

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
@Table(name = "site_job_apply_flow_log")
public class JobApplyFlowLog extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SiteJobApplyFlowLog";
	public static final String ALIAS_ID = "主键ID";
	public static final String ALIAS_JOB_APPLY_ID = "岗位投递ID";
	public static final String ALIAS_FLOW_TYPE = "类型(冗余)";
	public static final String ALIAS_FLOW_NODE = "流程节点";
	public static final String ALIAS_FLOW_OPERATE_TIME = "流程操作时间";
	public static final String ALIAS_FLOW_OPERATE_BY = "流程操作人(冗余)";
	public static final String ALIAS_COOPERATION_TYPE = "合作系统(冗余)";
	public static final String ALIAS_OPERATE_TYPE = "操作内容-类型(1:文本,2:JSON对象,3:JSON数组)";
	public static final String ALIAS_OPERATE_CONTENT = "操作内容";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	
	//date formats
	public static final String FORMAT_FLOW_OPERATE_TIME = DATE_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_MOMENT_TIME = "HH:mm";
	

	//columns START
	private java.lang.Integer jobApplyId;
	private java.lang.Integer flowType;
	private java.lang.Integer flowNode;
	private java.util.Date flowOperateTime;
	private java.lang.String flowOperateBy;
	private java.lang.Integer cooperationType;
	private java.lang.Integer operateType;
	private java.lang.String operateContent;
	private java.util.Date createTime;
	//columns END


	public JobApplyFlowLog(){
	}

	public JobApplyFlowLog(
		java.lang.Integer id
	){
		this.id = id;
	}
	
	@Column(name = "job_apply_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getJobApplyId() {
		return this.jobApplyId;
	}
	
	public void setJobApplyId(java.lang.Integer value) {
		this.jobApplyId = value;
	}
	
	@Column(name = "flow_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getFlowType() {
		return this.flowType;
	}
	
	public void setFlowType(java.lang.Integer value) {
		this.flowType = value;
	}
	
	@Column(name = "flow_node", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getFlowNode() {
		return this.flowNode;
	}
	
	public void setFlowNode(java.lang.Integer value) {
		this.flowNode = value;
	}
	
	@Transient
	public String getFlowOperateTimeString() {
		return DateConvertUtils.format(getFlowOperateTime(), FORMAT_FLOW_OPERATE_TIME);
	}
	public void setFlowOperateTimeString(String value) {
		setFlowOperateTime(DateConvertUtils.parse(value, FORMAT_FLOW_OPERATE_TIME,java.util.Date.class));
	}
	
	@Column(name = "flow_operate_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getFlowOperateTime() {
		return this.flowOperateTime;
	}
	
	public void setFlowOperateTime(java.util.Date value) {
		this.flowOperateTime = value;
	}
	
	@Column(name = "flow_operate_by", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getFlowOperateBy() {
		return this.flowOperateBy;
	}
	
	public void setFlowOperateBy(java.lang.String value) {
		this.flowOperateBy = value;
	}
	
	@Column(name = "cooperation_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCooperationType() {
		return this.cooperationType;
	}
	
	public void setCooperationType(java.lang.Integer value) {
		this.cooperationType = value;
	}
	
	@Column(name = "operate_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getOperateType() {
		return this.operateType;
	}
	
	public void setOperateType(java.lang.Integer value) {
		this.operateType = value;
	}
	
	@Column(name = "operate_content", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getOperateContent() {
		return this.operateContent;
	}
	
	public void setOperateContent(java.lang.String value) {
		this.operateContent = value;
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
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	@Transient
	public String getCreateMomentString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_MOMENT_TIME);
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
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("JobApplyId",getJobApplyId())
			.append("FlowType",getFlowType())
			.append("FlowNode",getFlowNode())
			.append("FlowOperateTime",getFlowOperateTime())
			.append("FlowOperateBy",getFlowOperateBy())
			.append("CooperationType",getCooperationType())
			.append("OperateType",getOperateType())
			.append("OperateContent",getOperateContent())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof JobApplyFlowLog == false) return false;
		if(this == obj) return true;
		JobApplyFlowLog other = (JobApplyFlowLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

