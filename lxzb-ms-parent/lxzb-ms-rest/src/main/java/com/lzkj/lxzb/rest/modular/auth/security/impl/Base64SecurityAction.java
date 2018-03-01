package com.lzkj.lxzb.rest.modular.auth.security.impl;

import com.lzkj.lxzb.rest.modular.auth.security.DataSecurityAction;
import org.springframework.util.Base64Utils;

/**
 * 对数据进行base64编码的方式
 *
 * @author jiangzh
 * @date 2018年01月24日 17:32:54
 */
public class Base64SecurityAction implements DataSecurityAction {

    @Override
    public String doAction(String beProtected) {
        return Base64Utils.encodeToString(beProtected.getBytes());
    }

    @Override
    public String unlock(String securityCode) {
        byte[] bytes = Base64Utils.decodeFromString(securityCode);
        return new String(bytes);
    }
}
