package com.lzkj.lxzb.core.exception;

/**
 * 抽象接口
 *
 * @author jiangzh
 * @date 2018年01月24日
 */
public interface ServiceExceptionEnum {

    /**
     * 获取异常编码
     */
    Integer getCode();

    /**
     * 获取异常信息
     */
    String getMessage();
}
