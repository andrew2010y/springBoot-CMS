package com.lzkj.lxzb.rest.modular.auth.controller.dto;

import com.lzkj.lxzb.rest.modular.auth.validator.dto.Credence;

/**
 * 认证的请求dto
 *
 * @author jiangzh
 * @Date 2018年01月24日 17:32:54
 */
public class AuthRequest implements Credence {

    private String userName;
    private String password;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String getCredenceName() {
        return this.userName;
    }

    @Override
    public String getCredenceCode() {
        return this.password;
    }
}
