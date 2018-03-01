package com.lzkj.lxzb.rest.modular.user.dao;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.lxzbEntities.entity.raw.model.Thirdbindingcash;
import com.lzkj.lxzb.rest.modular.user.entity.ThirdBindingCashExt;

/**
 * @Author：
 * @Descriptor：
 * @Date： Create in 11:50 2018/1/26
 * @Modified by：
 */
public interface ThirdbindingcashMappex {

	List<ThirdBindingCashExt> list(Page<ThirdBindingCashExt> page,ThirdBindingCashExt thirdBindingCashExt);

	/**
	 * 支付宝提现审核(拒绝)
	 **/
	int AlipayWithdrawalsExamineRefuse(Thirdbindingcash bean);
	
	
	/**
	 * 支付宝提现审核通过(批量)
	 **/
	int AlipayWithdrawalsBatchExamineAdopt(BigDecimal[] numthirdbindingcashid);
}
