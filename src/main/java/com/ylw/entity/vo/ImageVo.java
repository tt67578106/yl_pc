package com.ylw.entity.vo;

import java.io.Serializable;

public class ImageVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private java.lang.Integer id;
	private java.lang.String imgPath;
	private java.lang.String imgName;
	private java.lang.Integer length;
	/**
	 * 隐藏图片地址，根据缓存取vo
	 */
	private java.lang.String key;
	private java.lang.String introduction;
	
	
	public ImageVo(String imgPath, String imgName, String key,
			String introduction) {
		super();
		this.imgPath = imgPath;
		this.imgName = imgName;
		this.key = key;
		this.introduction = introduction;
	}
	public ImageVo() {
	}
	public ImageVo(String imgPath, String key) {
		super();
		this.imgPath = imgPath;
		this.key = key;
	}
	public ImageVo(Integer id, String imgPath, String imgName, Integer length) {
		super();
		this.id = id;
		this.imgPath = imgPath;
		this.imgName = imgName;
		this.length = length;
	}
	public java.lang.String getKey() {
		return key;
	}
	public void setKey(java.lang.String key) {
		this.key = key;
	}
	public java.lang.String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(java.lang.String introduction) {
		this.introduction = introduction;
	}
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.String getImgPath() {
		return imgPath;
	}
	public void setImgPath(java.lang.String imgPath) {
		this.imgPath = imgPath;
	}
	public java.lang.String getImgName() {
		return imgName;
	}
	public void setImgName(java.lang.String imgName) {
		this.imgName = imgName;
	}
	public java.lang.Integer getLength() {
		return length;
	}
	public void setLength(java.lang.Integer length) {
		this.length = length;
	}
	
}
