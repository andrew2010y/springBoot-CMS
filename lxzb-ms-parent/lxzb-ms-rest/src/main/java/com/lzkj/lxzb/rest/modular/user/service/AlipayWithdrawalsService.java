package com.lzkj.lxzb.rest.modular.user.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.lxzbEntities.entity.raw.model.Thirdbindingcash;
import com.lzkj.lxzb.rest.modular.user.entity.ThirdBindingCashExt;

public interface AlipayWithdrawalsService {

	/**
	 * 查询支付宝提现列表
	 **/
	public List<ThirdBindingCashExt> list(Page<ThirdBindingCashExt> page,ThirdBindingCashExt thirdBindingCashExt);
	
	/**
	 * 支付宝提现审核(拒绝)
	 **/
	public int AlipayWithdrawalsExamineRefuse(Thirdbindingcash bean);
	
	/**
	 * 支付宝提现审核通过(批量)
	 **/
	public int AlipayWithdrawalsBatchExamineAdopt(BigDecimal[] numthirdbindingcashid);
	
}
