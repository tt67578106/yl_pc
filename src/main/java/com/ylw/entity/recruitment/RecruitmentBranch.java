package com.ylw.entity.recruitment;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ylw.entity.IdEntity;
import com.ylw.entity.base.Branch;
import com.ylw.util.DateConvertUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_recruitment_branch")
public class RecruitmentBranch extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "RecruitmentBranch";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_RECRUIT_ID = "招聘会ID";
	public static final String ALIAS_BRANCH_ID = "分站ID";
	public static final String ALIAS_CREATE_USER = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	

	//columns START
	private java.lang.Integer recruitId;
	private Branch branch;
	private java.lang.Integer createUser;
	private java.util.Date createTime;
	//columns END


	public RecruitmentBranch(){
	}

	public RecruitmentBranch(
		java.lang.Integer id
	){
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "branch_id")
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	
	@Column(name = "recruit_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getRecruitId() {
		return this.recruitId;
	}
	
	public void setRecruitId(java.lang.Integer value) {
		this.recruitId = value;
	}
	
	@Column(name = "create_user", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCreateUser() {
		return this.createUser;
	}
	
	public void setCreateUser(java.lang.Integer value) {
		this.createUser = value;
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
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("RecruitId",getRecruitId())
			.append("CreateUser",getCreateUser())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RecruitmentBranch == false) return false;
		if(this == obj) return true;
		RecruitmentBranch other = (RecruitmentBranch)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

