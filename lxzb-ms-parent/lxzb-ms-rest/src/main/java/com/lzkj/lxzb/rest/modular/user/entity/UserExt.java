package com.lzkj.lxzb.rest.modular.user.entity;

import com.lzkj.lxzb.lxzbEntities.entity.raw.model.Gameuser;

/**
 * Created by panlei on 2018/2/27.
 */
public class UserExt extends Gameuser{

    private String vc2sdkusername;
    
    private String vc2areaname;
    
    private String vc2areacode;
    
    private String vc2type;
    
	public String getVc2sdkusername() {
		return vc2sdkusername;
	}
	public void setVc2sdkusername(String vc2sdkusername) {
		this.vc2sdkusername = vc2sdkusername;
	}
	public String getVc2areaname() {
		return vc2areaname;
	}
	public void setVc2areaname(String vc2areaname) {
		this.vc2areaname = vc2areaname;
	}
	public String getVc2areacode() {
		return vc2areacode;
	}
	public void setVc2areacode(String vc2areacode) {
		this.vc2areacode = vc2areacode;
	}
	public String getVc2type() {
		return vc2type;
	}
	public void setVc2type(String vc2type) {
		this.vc2type = vc2type;
	}
    
}
