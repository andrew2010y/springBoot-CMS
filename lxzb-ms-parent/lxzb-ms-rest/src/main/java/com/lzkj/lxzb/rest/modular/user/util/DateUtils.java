package com.lzkj.lxzb.rest.modular.user.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * @author lxn
 * @date 2017-11-22 17:38
 **/
public class DateUtils {

	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
	
	public static SimpleDateFormat DF_DD = new SimpleDateFormat("yyyy-MM-dd");//定义格式，不显示毫秒

	public static SimpleDateFormat DF_ZH = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");//定义格式，不显示毫秒

	public static String regEx = "^([0]{1})(\\.(\\d)*)?$" ; 
	
    /**
     * 四舍五入，保留两位。不足两位时，用0补全
     *
     * @param b
     * @return
     */
    public static String roundHalfUp(BigDecimal b) {
        System.out.println("格式化之前数字："+b);
        String result = "0.00" ;
        DecimalFormat df = new DecimalFormat();
        String style = "0.00";
        if (b != null) {
            df.applyPattern(style);
            result = df.format(b.doubleValue());
        } else {
            result =  "0.00";
        }
        System.out.println("格式化之后数字："+b);

        return result;
    }
}
