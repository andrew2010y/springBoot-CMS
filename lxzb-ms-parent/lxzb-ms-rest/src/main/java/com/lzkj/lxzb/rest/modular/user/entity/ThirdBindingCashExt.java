package com.lzkj.lxzb.rest.modular.user.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lzkj.lxzb.lxzbEntities.entity.raw.model.Thirdbindingcash;

public class ThirdBindingCashExt extends Thirdbindingcash {
 
	private static final long serialVersionUID = 250773627543617258L;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date datcreateStart;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date datcreateEnd;
	
	BigDecimal numtransactionnumMin;
	
	BigDecimal numtransactionnumMax;
	
	private String vc2sdkusername;
	
	public Date getDatcreateStart() {
		return datcreateStart;
	}

	public void setDatcreateStart(Date datcreateStart) {
		this.datcreateStart = datcreateStart;
	}

	public Date getDatcreateEnd() {
		return datcreateEnd;
	}

	public void setDatcreateEnd(Date datcreateEnd) {
		this.datcreateEnd = datcreateEnd;
	}

	public BigDecimal getNumtransactionnumMin() {
		return numtransactionnumMin;
	}

	public void setNumtransactionnumMin(BigDecimal numtransactionnumMin) {
		this.numtransactionnumMin = numtransactionnumMin;
	}

	public BigDecimal getNumtransactionnumMax() {
		return numtransactionnumMax;
	}

	public void setNumtransactionnumMax(BigDecimal numtransactionnumMax) {
		this.numtransactionnumMax = numtransactionnumMax;
	}

	public String getVc2sdkusername() {
		return vc2sdkusername;
	}

	public void setVc2sdkusername(String vc2sdkusername) {
		this.vc2sdkusername = vc2sdkusername;
	}
	
}
