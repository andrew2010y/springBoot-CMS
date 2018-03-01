package com.lzkj.lxzb.rest.modular.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.rest.base.vo.ServletResponse;
import com.lzkj.lxzb.rest.modular.user.entity.UserExt;
import com.lzkj.lxzb.rest.modular.user.service.UserService;
import com.lzkj.lxzb.rest.modular.user.util.ResultMessageEnum;

/**
 * Created by panlei on 2018/2/27.
 */
@RestController
@RequestMapping("/game-user")
public class GameUserController {

    @Autowired
    UserService userService;

    @RequestMapping("/list")
    public ServletResponse<List<UserExt>> userList(@RequestParam(value = "page", defaultValue = "1", required = false) Integer pageNo,
         @RequestParam(value = "limit", defaultValue = "20", required = false) Integer pageSize,String username){

        ServletResponse<List<UserExt>> result = new ServletResponse<List<UserExt>>();

        Page<UserExt> page = new Page<UserExt>(pageNo, pageSize);

        List<UserExt> records = userService.queryUserList(page,username);

        result.isSuccess(page.getTotal(),records);
        result.setCode(ResultMessageEnum.success.getCode());
        result.setMsg(ResultMessageEnum.success.name());
        
        return result;
    }
}
