package com.lzkj.lxzb.rest.modular.user.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 * 
 * @author zm
 * 
 */
public class Constant {

	// 保存当前登录用户信息的session key
	public static final String CURRENT_LOGIN_CMSUSER = "currentLoginCMSUser";

	// 新增管理员默认密码为zishiliu123456
	public static final String CMSUSER_DEFAULT_PASSWORD = "zishiliu123456";

	public static final String YES = "yes";
	public static final String NO = "no";
	/**
	 * 操作失败
	 */
	public static final String ERROR_CODE_40001 = "40001";
	/**
	 * 管理员名称已存在
	 */
	public static final String ERROR_CODE_40002 = "40002";
	
	/**
	 * 只读权限
	 */
	public static final String PERMISSION_R = "r";
	/**
	 * 读写权限
	 */
	public static final String PERMISSION_W = "w";
	public static final String CURRENT_LOGIN_CMSUSER_DEFAULT_MENU = "currentLoginCMSUserDefaultMenu";
	public static final String CURRENT_LOGIN_CMSUSER_LAST_MENU = "currentLoginCMSUserLastMenu";
	
	/**
	 * 分页大小，默认页面显示20条记录
	 */
	public static final int DEFAULT_PAGE_SIZE = 20;
	
	public static final String REQ_NAV_TAB_ACTIVE = "reqNavTabActive";
	public static final String PERSONAL_LIST_BY_USERID = "personalListByUserId";
	
	public static final String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * 订单状态: 创建订单
	 */
	public static final String ORDER_STATUS_CREATE = "ORDER_CREATE";
	
	/**
	 * 订单状态: 支付完成
	 */
	public static final String ORDER_STATUS_PAYED = "ORDER_PAYED";

	/**
	 * 支付方式: 官方
	 */
	public static final String PAY_TYPE_OFFICIAL = "OFFICIAL";
	
	/**
	 * 支付方式: 微信
	 */
	public static final String PAY_TYPE_WEIXIN = "WXPAY";

	/**
	 * 支付方式: 支付宝
	 */
	public static final String PAY_TYPE_ALIPAY = "ALIPAY";
	
	/**
	 * 接受申请
	 */
	public static final String CASH_STATUS_ORDER = "CASHORDER";
	
	/**
	 * 处理中
	 */
	public static final String CASH_STATUS_CASHING = "CASHING";
	
	/**
	 * 处理成功
	 */
	public static final String CASH_STATUS_SUCCESSFULL = "SUCCESSFULL";
	
	/**
	 * 处理失败
	 */
	public static final String CASH_STATUS_FAIL = "FAIL";
	
	/**
	 * 操作人:开始处理
	 */
	public static final String OPERAT_CASH_STATUS_START = "START";
	
	/**
	 * 操作人:处理成功
	 */
	public static final String OPERAT_CASH_STATUS_SUCCESSFULL = "SUCCESSFULL";
	
	/**
	 * 操作人:处理失败
	 */
	public static final String OPERAT_CASH_STATUS_FAILD = "FAILD";
	
	/**
	 * 积分点状态:开始兑换
	 */
	public static final String SCORE_PRICE_CONF_EXCHANGE_START = "EXCHANGESTART";

	/**
	 * 积分点状态:兑换完成
	 */
	public static final String SCORE_PRICE_CONF_EXCHANGE_SUCCESSFULL = "EXCHANGESUCCESSFULL";
	
	/**
	 * 积分点状态:删除
	 */
	public static final String SCORE_PRICE_CONF_DELETE = "DELETE";
	
	/**
	 * 积分点状态:生效(未兑换)
	 */
	public static final String SCORE_PRICE_CONF_ENABLE = "ENABLE";
	
	// 普通:NORMAL/专员:PROFESSIONAL/红钻:DIAMOND_RED/紫钻:DIAMOND_PURPLE
	public static final Map<String, String> USER_TYPE_MAP = new HashMap<String, String>(){{    
		put("NORMAL", "普通用户");
		put("PROFESSIONAL", "推广专员");
		put("DIAMOND_RED", "红钻会员");
		put("DIAMOND_PURPLE", "紫钻会员");   
	}}; 
	
	public static final Map<String, String> PAY_TYPE_MAP = new HashMap<String, String>(){{    
		put("WXPAY", "微信");
		put("ALIPAY", "支付宝");  
		put("OFFICIAL", "官方");  
	}};
	
	public static final Map<String, String> CASH_STATUS_MAP = new HashMap<String, String>(){{    
		put("CASHORDER", "接受申请");
		put("CASHING", "处理中");
		put("SUCCESSFULL", "处理成功");
		put("FAIL", "处理失败");
	}};
	
	public static final String TOKEN_NAME = "token";
	
	public static final Map<String, String> USER_STATUS_MAP = new HashMap<String, String>(){{    
//		put("FREEZE", "已冻结");
//		put("UNFREEZE", "未冻结");
//		put("DELETE", "已封禁");
		put("FREEZE", "已封禁");
		put("UNFREEZE", "正常");


	}};
	
	public static final Map<String, String> OPERATION_LEVEL_MAP = new HashMap<String, String>(){{    
		put("LEVEL_1", "一级");
		put("LEVEL_2", "二级");  
	}};
	
	public static final Map<String, String> PROCEDURE_STATUS_MAP = new HashMap<String, String>(){{    
		put("CHECK_NOSUBMIT", "未提交申请");
		put("CHECK_UNCHECK", "待审核");  
		put("CHEDK_FAILED", "审核不通过");
		put("CHECK_PASS", "审核成功");  
	}};
}
