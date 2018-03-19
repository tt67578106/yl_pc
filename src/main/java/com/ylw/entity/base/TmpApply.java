package com.ylw.entity.base;

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
@Table(name = "tmp_apply")
public class TmpApply extends IdEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "TmpApply";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_USERNAME = "用户名";
	public static final String ALIAS_COMPANYID = "企业id";
	public static final String ALIAS_COMPANYNAME = "企业名";
	public static final String ALIAS_JOBID = "岗位id";
	public static final String ALIAS_JOBNAME = "岗位名";
	public static final String ALIAS_CREATETIME = "创建时间";

	// date formats
	public static final String FORMAT_CREATETIME = "HH:mm";

	// columns START
	private java.lang.Integer userid;
	private java.lang.String username;
	private java.lang.Integer companyid;
	private java.lang.String companyname;
	private java.lang.Integer jobid;
	private java.lang.String jobname;
	private java.util.Date createtime;

	// columns END

	public TmpApply() {
	}

	@Column(name = "userid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserid() {
		return this.userid;
	}

	public void setUserid(java.lang.Integer value) {
		this.userid = value;
	}

	@Column(name = "username", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getUsername() {
		return this.username;
	}

	public void setUsername(java.lang.String value) {
		this.username = value;
	}

	@Column(name = "companyid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(java.lang.Integer value) {
		this.companyid = value;
	}

	@Column(name = "companyname", unique = false, nullable = true, insertable = true, updatable = true, length = 250)
	public java.lang.String getCompanyname() {
		return this.companyname;
	}

	public void setCompanyname(java.lang.String value) {
		this.companyname = value;
	}

	@Column(name = "jobid", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getJobid() {
		return this.jobid;
	}

	public void setJobid(java.lang.Integer value) {
		this.jobid = value;
	}

	@Column(name = "jobname", unique = false, nullable = true, insertable = true, updatable = true, length = 250)
	public java.lang.String getJobname() {
		return this.jobname;
	}

	public void setJobname(java.lang.String value) {
		this.jobname = value;
	}

	@Transient
	public String getCreatetimeString() {
		return DateConvertUtils.format(getCreatetime(), FORMAT_CREATETIME);
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

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("Id", getId()).append("Userid", getUserid()).append("Username", getUsername()).append("Companyid", getCompanyid()).append("Companyname", getCompanyname()).append("Jobid", getJobid()).append("Jobname", getJobname()).append("Createtime", getCreatetime()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TmpApply == false)
			return false;
		if (this == obj)
			return true;
		TmpApply other = (TmpApply) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}
}
