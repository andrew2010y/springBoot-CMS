package com.lzkj.lxzb.rest.base.vo;

import java.math.BigDecimal;

/**
 * @Author：jiangzh
 * @Descriptor：统一返回对象
 * @Date： Create in 18:12 2018/1/24
 * @Modified by：
 */
public class ServletResponse<T> {

     private int code;
     private String msg;
     private long count;
     private T data;
     private BigDecimal sum;

     public void isSuccess(long count,T data){
          this.code=0;
          this.msg="";
          this.count=count;
          this.data=data;
     }

     public int getCode() {
          return code;
     }

     public void setCode(int code) {
          this.code = code;
     }

     public String getMsg() {
          return msg;
     }

     public void setMsg(String msg) {
          this.msg = msg;
     }

     public long getCount() {
          return count;
     }

     public void setCount(long count) {
          this.count = count;
     }

     public T getData() {
          return data;
     }

     public void setData(T data) {
          this.data = data;
     }

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	 
}
