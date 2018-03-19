package com.ylw.entity.vo;

import java.io.Serializable;

public class WanlixingPeriodVo implements Serializable{
	private static final long serialVersionUID = 1L;

	private java.lang.String provinceName;
	private java.lang.Integer provinceId;
	private Integer types;
	private Integer count;
	public WanlixingPeriodVo(String provinceName, Integer provinceId,
			Integer types, Integer count) {
		super();
		this.provinceName = provinceName;
		this.provinceId = provinceId;
		this.types = types;
		this.count = count;
	}
	public java.lang.String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(java.lang.String provinceName) {
		this.provinceName = provinceName;
	}
	public java.lang.Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(java.lang.Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getTypes() {
		return types;
	}
	public void setTypes(Integer types) {
		this.types = types;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	
}
