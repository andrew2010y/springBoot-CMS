package com.lzkj.lxzb.rest.modular.user.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lzkj.lxzb.lxzbEntities.entity.raw.model.ChargeRecord;

public class ChargeRecordExt extends ChargeRecord{
	
	private static final long serialVersionUID = 2523071772469496474L;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date datorderStart;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date datorderEnd;
	
	private String vc2areacode;
	
	private BigDecimal agentId;
	
	private String vc2sdkusername;
	
	private String vc2sdkrecommandusername;
	
	private String vc2sdkagentusername;
	
	public String getVc2areacode() {
		return vc2areacode;
	}

	public void setVc2areacode(String vc2areacode) {
		this.vc2areacode = vc2areacode;
	}

	public BigDecimal getAgentId() {
		return agentId;
	}

	public void setAgentId(BigDecimal agentId) {
		this.agentId = agentId;
	}

	public Date getDatorderStart() {
		return datorderStart;
	}

	public void setDatorderStart(Date datorderStart) {
		this.datorderStart = datorderStart;
	}

	public Date getDatorderEnd() {
		return datorderEnd;
	}

	public void setDatorderEnd(Date datorderEnd) {
		this.datorderEnd = datorderEnd;
	}

	public String getVc2sdkusername() {
		return vc2sdkusername;
	}

	public void setVc2sdkusername(String vc2sdkusername) {
		this.vc2sdkusername = vc2sdkusername;
	}

	public String getVc2sdkrecommandusername() {
		return vc2sdkrecommandusername;
	}

	public void setVc2sdkrecommandusername(String vc2sdkrecommandusername) {
		this.vc2sdkrecommandusername = vc2sdkrecommandusername;
	}

	public String getVc2sdkagentusername() {
		return vc2sdkagentusername;
	}

	public void setVc2sdkagentusername(String vc2sdkagentusername) {
		this.vc2sdkagentusername = vc2sdkagentusername;
	}
	
}
