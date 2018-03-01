package com.lzkj.lxzb.rest.modular.auth.controller;

import com.lzkj.lxzb.core.exception.LZKJException;
import com.lzkj.lxzb.rest.common.exception.BizExceptionEnum;
import com.lzkj.lxzb.rest.modular.auth.controller.dto.AuthRequest;
import com.lzkj.lxzb.rest.modular.auth.controller.dto.AuthResponse;
import com.lzkj.lxzb.rest.modular.auth.util.JwtTokenUtil;
import com.lzkj.lxzb.rest.modular.auth.validator.IReqValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 请求验证的
 *
 * @author jiangzh
 * @Date 2018年01月24日 17:32:54
 */
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource(name = "simpleValidator")
    private IReqValidator reqValidator;

    @RequestMapping(value = "${jwt.auth-path}")
    public ResponseEntity<?> createAuthenticationToken(AuthRequest authRequest) {

        boolean validate = reqValidator.validate(authRequest);

        if (validate) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(authRequest.getUserName(), randomKey);
            return ResponseEntity.ok(new AuthResponse(token, randomKey));
        } else {
            throw new LZKJException(BizExceptionEnum.AUTH_REQUEST_ERROR);
        }
    }
}
