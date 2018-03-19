package com.ylw.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylw.entity.IdEntity;

/**
 * @author Nicolas.Cai
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_branch_city")
@JsonIgnoreProperties({"branch"})
public class BranchCity extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "BranchCity";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_BRANCH_ID = "branchId";
	public static final String ALIAS_CITY_ID = "默认城市";
	public static final String ALIAS_CITY_NAME = "节省开销，默认显示城市名字，可以修改";
	public static final String ALIAS_SORTING = "sorting";
	
	//date formats
	

	//columns START
	private Branch branch;
	private java.lang.Integer cityId;
	private java.lang.String cityName;
	private Integer sorting;
	//columns END


	public BranchCity(){
	}
	@ManyToOne
	@JoinColumn(name="branch_id")
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@Column(name = "city_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCityId() {
		return this.cityId;
	}
	
	public void setCityId(java.lang.Integer value) {
		this.cityId = value;
	}
	
	@Column(name = "city_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getCityName() {
		return this.cityName;
	}
	
	public void setCityName(java.lang.String value) {
		this.cityName = value;
	}
	
	@Column(name = "sorting", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getSorting() {
		return this.sorting;
	}
	
	public void setSorting(Integer value) {
		this.sorting = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("BranchId",getBranch().getId())
			.append("CityId",getCityId())
			.append("CityName",getCityName())
			.append("Sorting",getSorting())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BranchCity == false) return false;
		if(this == obj) return true;
		BranchCity other = (BranchCity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

