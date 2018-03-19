package com.ylw.util;

import java.util.Date;

/**
 * 验证码缓存类
 * @author Nicolas
 *
 */
public class ValiCodeCache {

	private Object loginName;
	private String valiCode;
	private Date createDate;
	private long timeOut;
	private int status;
	
	public ValiCodeCache() {
		super();
	}
	public ValiCodeCache(Object loginName, String valiCode, Date createDate,
			long timeOut, int status) {
		super();
		this.loginName = loginName;
		this.valiCode = valiCode;
		this.createDate = createDate;
		this.timeOut = timeOut;
		this.status = status;
	}
	public Object getLoginName() {
		return loginName;
	}
	public void setLoginName(Object loginName) {
		this.loginName = loginName;
	}
	public String getValiCode() {
		return valiCode;
	}
	public void setValiCode(String valiCode) {
		this.valiCode = valiCode;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public long getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
