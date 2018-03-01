package com.lzkj.lxzb;

import com.lzkj.lxzb.rest.LXZBRestApplication;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author：jiangzh
 * @Descriptor：
 * @Date： Create in 11:49 2018/1/30
 * @Modified by：
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LXZBRestApplication.class)
public class UserPwdGenerator {

    @Test
    public void generatorPwd(){

        String algorithmName = "md5";
        int hashIterations = 1024;

        String password = "lingxzb123*";
        String salt = "123456";


        SimpleHash hash = new SimpleHash(algorithmName, password, salt, hashIterations);
        String encodedPassword = hash.toHex();

        System.out.println(encodedPassword);
    }


   // @Test
    public void testLogin(){

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
        usernamePasswordToken.setUsername("liu");
        usernamePasswordToken.setPassword("123".toCharArray());

        Subject subject = SecurityUtils.getSubject();

        subject.login(usernamePasswordToken);

    }

}
