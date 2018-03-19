package com.ylw.entity.vo;

import java.io.Serializable;

/**
 * 广告显示bean
 * @author ghost
 *
 */
public class RecommendAdVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private java.lang.Integer id;
	private java.lang.String title;
	private java.lang.String subtitle;
	private java.lang.String img;
	private java.lang.String imgAlt;
	private java.lang.String style;
	private java.lang.String link;
	
	public RecommendAdVo(Integer id, String title, String subtitle, String img,
			String imgAlt,String style, String link) {
		super();
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.img = img;
		this.imgAlt = imgAlt;
		this.style = style;
		this.link = link;
	}
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.String getTitle() {
		return title;
	}
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	public java.lang.String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(java.lang.String subtitle) {
		this.subtitle = subtitle;
	}
	public java.lang.String getImgAlt() {
		return imgAlt;
	}
	public void setImgAlt(java.lang.String imgAlt) {
		this.imgAlt = imgAlt;
	}
	public java.lang.String getStyle() {
		return style;
	}
	public void setStyle(java.lang.String style) {
		this.style = style;
	}
	public java.lang.String getLink() {
		return link;
	}
	public void setLink(java.lang.String link) {
		this.link = link;
	}
	public java.lang.String getImg() {
		return img;
	}
	public void setImg(java.lang.String img) {
		this.img = img;
	}
}
