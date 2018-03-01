package com.lzkj.lxzb.jwt;

import com.alibaba.fastjson.JSON;
import com.lzkj.lxzb.core.util.MD5Util;
import com.lzkj.lxzb.rest.common.SimpleObject;
import com.lzkj.lxzb.rest.modular.auth.converter.BaseTransferEntity;
import com.lzkj.lxzb.rest.modular.auth.security.impl.Base64SecurityAction;

/**
 * jwt测试
 *
 * @author fengshuonan
 * @date 2017-08-21 16:34
 */
public class DecryptTest {

    public static void main(String[] args) {

        String key = "mySecret";

        String compactJws = "eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJmYm9vbmQiLCJzdWIiOiJhZG1pbiIsImV4cCI6MTUxNzU0MDM4MCwiaWF0IjoxNTE2OTM1NTgwfQ.Kg7wJ0fi-v7Rry_lHDhE3Ojt3xLMg0VAmkyvzncbhcnXk16yeyz43aoL7UxkSG63I3aMd2lILk3Q85VMLiUj2Q";
        String salt = "fboond";

        SimpleObject simpleObject = new SimpleObject();
        simpleObject.setUser("stylefeng");
        simpleObject.setAge(12);
        simpleObject.setName("ffff");
        simpleObject.setTips("code");

        String jsonString = JSON.toJSONString(simpleObject);
        String encode = new Base64SecurityAction().doAction(jsonString);
        String md5 = MD5Util.encrypt(encode + salt);

        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        baseTransferEntity.setObject(encode);
        baseTransferEntity.setSign(md5);

        System.out.println(JSON.toJSONString(baseTransferEntity));

        //System.out.println("body = " + Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody());
        //System.out.println("header = " + Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getHeader());
        //System.out.println("signature = " + Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getSignature());
    }
}
