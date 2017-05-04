package com.zcbspay.platform.portal.query.statistics.bean;

import java.io.Serializable;

public class TxnsForPortalBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4213471451027165898L;
	
	private String merid;// 商户号
	private String busicode;
	private String stime;
	private String etime;
	private String status;
	private String orderid;
	private String orderidog;
	private String batchno;
	private String scommitime;
	private String ecommitime;
	private String account;
	private String name;
	private String stattype;
	
	
	public String getStattype() {
		return stattype;
	}
	public void setStattype(String stattype) {
		this.stattype = stattype;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScommitime() {
		return scommitime;
	}
	public void setScommitime(String scommitime) {
		this.scommitime = scommitime;
	}
	public String getEcommitime() {
		return ecommitime;
	}
	public void setEcommitime(String ecommitime) {
		this.ecommitime = ecommitime;
	}
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	public String getMerid() {
		return merid;
	}
	public void setMerid(String merid) {
		this.merid = merid;
	}
	public String getBusicode() {
		return busicode;
	}
	public void setBusicode(String busicode) {
		this.busicode = busicode;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOrderidog() {
		return orderidog;
	}
	public void setOrderidog(String orderidog) {
		this.orderidog = orderidog;
	}
	
	
}
