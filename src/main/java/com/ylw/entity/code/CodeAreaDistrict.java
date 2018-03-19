package com.ylw.entity.code;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ylw.entity.IdEntity;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "site_code_area_district")
public class CodeAreaDistrict extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	//alias
		public static final String TABLE_ALIAS = "CodeAreaDistrict";
		public static final String ALIAS_ID = "主键ID";
		public static final String ALIAS_PROVINCE_ID = "所属省份ID";
		public static final String ALIAS_CITY_ID = "所属市ID";
		public static final String ALIAS_SPELL = "拼音";
		public static final String ALIAS_ABBREVIATION = "简称";
		public static final String ALIAS_EN_ABBREVIATION = "英文简称";
		public static final String ALIAS_DISTRICT_NAME = "区县名称";
		public static final String ALIAS_DISTRICT_EN_NAME = "区县英文名称";
		public static final String ALIAS_ZIP_CODE = "邮政编码";
		public static final String ALIAS_STATUS = "状态(1:正常,2:不显示)";
		
		//date formats
		

		//columns START
		private java.lang.Integer provinceId;
		private java.lang.Integer cityId;
		private java.lang.String spell;
		private java.lang.String abbreviation;
		private java.lang.String enAbbreviation;
		private java.lang.String districtName;
		private java.lang.String districtEnName;
		private java.lang.String zipCode;
		private Integer status;
		//columns END


		public CodeAreaDistrict(){
		}
		
		
		@Column(name = "province_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
		public java.lang.Integer getProvinceId() {
			return this.provinceId;
		}
		
		public void setProvinceId(java.lang.Integer value) {
			this.provinceId = value;
		}
		
		@Column(name = "city_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
		public java.lang.Integer getCityId() {
			return this.cityId;
		}
		
		public void setCityId(java.lang.Integer value) {
			this.cityId = value;
		}
		
		@Column(name = "spell", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
		public java.lang.String getSpell() {
			return this.spell;
		}
		
		public void setSpell(java.lang.String value) {
			this.spell = value;
		}
		
		@Column(name = "abbreviation", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
		public java.lang.String getAbbreviation() {
			return this.abbreviation;
		}
		
		public void setAbbreviation(java.lang.String value) {
			this.abbreviation = value;
		}
		
		@Column(name = "en_abbreviation", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
		public java.lang.String getEnAbbreviation() {
			return this.enAbbreviation;
		}
		
		public void setEnAbbreviation(java.lang.String value) {
			this.enAbbreviation = value;
		}
		
		@Column(name = "district_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
		public java.lang.String getDistrictName() {
			return this.districtName;
		}
		
		public void setDistrictName(java.lang.String value) {
			this.districtName = value;
		}
		
		@Column(name = "district_en_name", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
		public java.lang.String getDistrictEnName() {
			return this.districtEnName;
		}
		
		public void setDistrictEnName(java.lang.String value) {
			this.districtEnName = value;
		}
		
		@Column(name = "zip_code", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
		public java.lang.String getZipCode() {
			return this.zipCode;
		}
		
		public void setZipCode(java.lang.String value) {
			this.zipCode = value;
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
				.append("ProvinceId",getProvinceId())
				.append("CityId",getCityId())
				.append("Spell",getSpell())
				.append("Abbreviation",getAbbreviation())
				.append("EnAbbreviation",getEnAbbreviation())
				.append("DistrictName",getDistrictName())
				.append("DistrictEnName",getDistrictEnName())
				.append("ZipCode",getZipCode())
				.append("Status",getStatus())
				.toString();
		}
		
		public int hashCode() {
			return new HashCodeBuilder()
				.append(getId())
				.toHashCode();
		}
		
		public boolean equals(Object obj) {
			if(obj instanceof CodeAreaDistrict == false) return false;
			if(this == obj) return true;
			CodeAreaDistrict other = (CodeAreaDistrict)obj;
			return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
		}
}

