package com.lzkj.lxzb.rest.modular.auth.validator.dto;

/**
 * 验证的凭据
 *
 * @author jiangzh
 * @date 2018年01月24日 17:32:54
 */
public interface Credence {

    /**
     * 凭据名称
     */
    String getCredenceName();

    /**
     * 密码或者是其他的验证码之类的
     */
    String getCredenceCode();
}
