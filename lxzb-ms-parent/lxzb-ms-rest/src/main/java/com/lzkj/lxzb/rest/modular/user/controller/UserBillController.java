package com.lzkj.lxzb.rest.modular.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.rest.base.vo.ServletResponse;
import com.lzkj.lxzb.rest.modular.user.entity.ChargeRecordExt;
import com.lzkj.lxzb.rest.modular.user.service.ChargeRecordService;
import com.lzkj.lxzb.rest.modular.user.util.ResultMessageEnum;

/**
 * 用户账单管理
 *
 * @author xkyan
 * @date 2018年01月26日 14:32:54
 */
@RestController
@RequestMapping("/user-bill")
public class UserBillController {

	@Autowired
    private ChargeRecordService chargeRecordService;

    @RequestMapping(value = "/list", method = { RequestMethod.POST,RequestMethod.GET })
    public ServletResponse<List<ChargeRecordExt>> list(ChargeRecordExt chargeRecordExt,
    		@RequestParam(value = "page", defaultValue = "1", required = false) Integer pageNo,
    		@RequestParam(value = "limit", defaultValue = "20", required = false) Integer pageSize) {
        
    	ServletResponse<List<ChargeRecordExt>> result = new ServletResponse<List<ChargeRecordExt>>();

    	Page<ChargeRecordExt> page = new Page<ChargeRecordExt>(pageNo, pageSize);
        
        List<ChargeRecordExt> records = chargeRecordService.list(page,chargeRecordExt);
        
        result.isSuccess(page.getTotal(),records);
        result.setCode(ResultMessageEnum.success.getCode());
        result.setMsg(ResultMessageEnum.success.name());
        
        return result;
    }
    
}
