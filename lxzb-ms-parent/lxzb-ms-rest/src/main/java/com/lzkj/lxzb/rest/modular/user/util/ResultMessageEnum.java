package com.lzkj.lxzb.rest.modular.user.util;

/**
 * @author xkyan 
 *
 *@see  
 */
public enum ResultMessageEnum {

	success(0),  fail(1);
	
	private Integer code;
	
	private ResultMessageEnum(Integer code) {
		this.code= code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
