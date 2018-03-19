package com.ylw.entity.code;

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
@Table(name = "site_code_area_city")
public class CodeAreaCity extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "CodeAreaCity";
	public static final String ALIAS_ID = "主键ID";
	public static final String ALIAS_PROVINCE_ID = "所属省ID";
	public static final String ALIAS_SPELL = "拼音";
	public static final String ALIAS_ABBREVIATION = "简称";
	public static final String ALIAS_EN_ABBREVIATION = "英文简称";
	public static final String ALIAS_CITY_NAME = "市名称";
	public static final String ALIAS_CITY_EN_NAME = "市英文名称";
	public static final String ALIAS_AREA_CODE = "电话区号";
	public static final String ALIAS_ZIP_CODE = "邮政编码";
	public static final String ALIAS_STATUS = "状态(1:正常,2:不显示)";
	public static final String ALIAS_BAIDU_CITY_ID = "baiduCityId";
	
	//date formats
	
	//columns START
		private java.lang.Integer provinceId;
		private java.lang.String spell;
		private java.lang.String abbreviation;
		private java.lang.String enAbbreviation;
		private java.lang.String cityName;
		private java.lang.String cityEnName;
		private java.lang.String areaCode;
		private java.lang.String zipCode;
		private Integer status;
		private java.lang.Integer baiduCityId;
		//columns END


		public CodeAreaCity(){
		}

		
		@Column(name = "province_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
		public java.lang.Integer getProvinceId() {
			return this.provinceId;
		}
		
		public void setProvinceId(java.lang.Integer value) {
			this.provinceId = value;
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
		
		@Column(name = "en_abbreviation", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
		public java.lang.String getEnAbbreviation() {
			return this.enAbbreviation;
		}
		
		public void setEnAbbreviation(java.lang.String value) {
			this.enAbbreviation = value;
		}
		
		@Column(name = "city_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
		public java.lang.String getCityName() {
			return this.cityName;
		}
		
		public void setCityName(java.lang.String value) {
			this.cityName = value;
		}
		
		@Column(name = "city_en_name", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
		public java.lang.String getCityEnName() {
			return this.cityEnName;
		}
		
		public void setCityEnName(java.lang.String value) {
			this.cityEnName = value;
		}
		
		@Column(name = "area_code", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
		public java.lang.String getAreaCode() {
			return this.areaCode;
		}
		
		public void setAreaCode(java.lang.String value) {
			this.areaCode = value;
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
		
		@Column(name = "baidu_city_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
		public java.lang.Integer getBaiduCityId() {
			return this.baiduCityId;
		}
		
		public void setBaiduCityId(java.lang.Integer value) {
			this.baiduCityId = value;
		}
		

		public String toString() {
			return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("Id",getId())
				.append("ProvinceId",getProvinceId())
				.append("Spell",getSpell())
				.append("Abbreviation",getAbbreviation())
				.append("EnAbbreviation",getEnAbbreviation())
				.append("CityName",getCityName())
				.append("CityEnName",getCityEnName())
				.append("AreaCode",getAreaCode())
				.append("ZipCode",getZipCode())
				.append("Status",getStatus())
				.append("BaiduCityId",getBaiduCityId())
				.toString();
		}
		
		public int hashCode() {
			return new HashCodeBuilder()
				.append(getId())
				.toHashCode();
		}
		
		public boolean equals(Object obj) {
			if(obj instanceof CodeAreaCity == false) return false;
			if(this == obj) return true;
			CodeAreaCity other = (CodeAreaCity)obj;
			return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
		}
}

