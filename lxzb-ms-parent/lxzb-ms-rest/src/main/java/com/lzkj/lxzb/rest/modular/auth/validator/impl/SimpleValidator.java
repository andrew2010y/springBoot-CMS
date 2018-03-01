package com.lzkj.lxzb.rest.modular.auth.validator.impl;

import com.lzkj.lxzb.rest.modular.auth.validator.IReqValidator;
import com.lzkj.lxzb.rest.modular.auth.validator.dto.Credence;
import org.springframework.stereotype.Service;

/**
 * 直接验证账号密码是不是admin
 *
 * @author jiangzh
 * @date 2018年01月24日 17:32:54
 */
@Service
public class SimpleValidator implements IReqValidator {

    private static String USER_NAME = "admin";

    private static String PASSWORD = "admin";

    @Override
    public boolean validate(Credence credence) {

        String userName = credence.getCredenceName();
        String password = credence.getCredenceCode();

        if (USER_NAME.equals(userName) && PASSWORD.equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
