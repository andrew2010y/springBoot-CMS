package com.lzkj.lxzb.rest.modular.user.entity;

import com.lzkj.lxzb.lxzbEntities.entity.raw.model.Areaserver;

public class AreaserverExt extends Areaserver{
	
	private static final long serialVersionUID = 2523071772469496474L;
 
	private String vc2type;
	
	private String vc2sdkusername;

	public String getVc2type() {
		return vc2type;
	}

	public void setVc2type(String vc2type) {
		this.vc2type = vc2type;
	}

	public String getVc2sdkusername() {
		return vc2sdkusername;
	}

	public void setVc2sdkusername(String vc2sdkusername) {
		this.vc2sdkusername = vc2sdkusername;
	}
}
