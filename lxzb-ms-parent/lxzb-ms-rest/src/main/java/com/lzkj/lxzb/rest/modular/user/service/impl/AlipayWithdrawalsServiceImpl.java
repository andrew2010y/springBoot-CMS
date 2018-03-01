package com.lzkj.lxzb.rest.modular.user.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.lxzbEntities.entity.raw.model.Thirdbindingcash;
import com.lzkj.lxzb.rest.modular.user.dao.ThirdbindingcashMappex;
import com.lzkj.lxzb.rest.modular.user.entity.ThirdBindingCashExt;
import com.lzkj.lxzb.rest.modular.user.service.AlipayWithdrawalsService;


@Service
public class AlipayWithdrawalsServiceImpl implements AlipayWithdrawalsService {

	@Resource
	private ThirdbindingcashMappex thirdbindingcashMappex;
	
	/**
	 * 查询支付宝提现列表
	 **/
	@Override
	public List<ThirdBindingCashExt> list(Page<ThirdBindingCashExt> page,ThirdBindingCashExt thirdBindingCashExt){
		return thirdbindingcashMappex.list(page,thirdBindingCashExt);
	}
	
	/**
	 * 支付宝提现审核(拒绝)
	 **/
	@Override
	public int AlipayWithdrawalsExamineRefuse(Thirdbindingcash bean){
		return thirdbindingcashMappex.AlipayWithdrawalsExamineRefuse(bean);
	}
	
	/**
	 * 支付宝提现审核通过(批量)
	 **/
	@Override
	public int AlipayWithdrawalsBatchExamineAdopt(BigDecimal[] numthirdbindingcashid){
		return thirdbindingcashMappex.AlipayWithdrawalsBatchExamineAdopt(numthirdbindingcashid);
	}

}
