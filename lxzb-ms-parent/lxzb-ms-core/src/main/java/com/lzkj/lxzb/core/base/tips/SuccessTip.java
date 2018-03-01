package com.lzkj.lxzb.core.base.tips;

/**
 * 返回给前台的成功提示
 *
 * @author jiangzh
 * @date 2018年01月24日
 */
public class SuccessTip extends Tip {
	
	public SuccessTip(){
		super.code = 200;
		super.message = "操作成功";
	}
}
