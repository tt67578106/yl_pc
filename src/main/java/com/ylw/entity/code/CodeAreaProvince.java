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
@Table(name = "site_code_area_province")
public class CodeAreaProvince extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	//alias
		public static final String TABLE_ALIAS = "CodeAreaProvince";
		public static final String ALIAS_ID = "主键ID";
		public static final String ALIAS_PROVINCE_NAME = "省名称";
		public static final String ALIAS_PROVINCE_EN_NAME = "省英文名称";
		public static final String ALIAS_SPELL = "拼音";
		public static final String ALIAS_ABBREVIATION = "简称";
		public static final String ALIAS_EN_ABBREVIATION = "英文简称";
		public static final String ALIAS_TYPES = "类型(1:省,2:直辖市)";
		public static final String ALIAS_STATUS = "状态(1:正常,2:不显示)";
		
		//date formats
		

		//columns START
		private java.lang.String provinceName;
		private java.lang.String provinceEnName;
		private java.lang.String spell;
		private java.lang.String abbreviation;
		private java.lang.String enAbbreviation;
		private Integer types;
		private Integer status;
		//columns END


		public CodeAreaProvince(){
		}
		
		
		@Column(name = "province_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
		public java.lang.String getProvinceName() {
			return this.provinceName;
		}
		
		public void setProvinceName(java.lang.String value) {
			this.provinceName = value;
		}
		
		@Column(name = "province_en_name", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
		public java.lang.String getProvinceEnName() {
			return this.provinceEnName;
		}
		
		public void setProvinceEnName(java.lang.String value) {
			this.provinceEnName = value;
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
		
		@Column(name = "types", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
		public Integer getTypes() {
			return this.types;
		}
		
		public void setTypes(Integer value) {
			this.types = value;
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
				.append("ProvinceName",getProvinceName())
				.append("ProvinceEnName",getProvinceEnName())
				.append("Spell",getSpell())
				.append("Abbreviation",getAbbreviation())
				.append("EnAbbreviation",getEnAbbreviation())
				.append("Types",getTypes())
				.append("Status",getStatus())
				.toString();
		}
		
		public int hashCode() {
			return new HashCodeBuilder()
				.append(getId())
				.toHashCode();
		}
		
		public boolean equals(Object obj) {
			if(obj instanceof CodeAreaProvince == false) return false;
			if(this == obj) return true;
			CodeAreaProvince other = (CodeAreaProvince)obj;
			return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
		}
}

