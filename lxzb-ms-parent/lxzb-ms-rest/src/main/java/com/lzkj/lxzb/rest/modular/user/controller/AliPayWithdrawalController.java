package com.lzkj.lxzb.rest.modular.user.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzkj.lxzb.lxzbEntities.entity.raw.model.Thirdbindingcash;
import com.lzkj.lxzb.rest.base.vo.ServletResponse;
import com.lzkj.lxzb.rest.modular.user.entity.ThirdBindingCashExt;
import com.lzkj.lxzb.rest.modular.user.service.AlipayWithdrawalsService;
import com.lzkj.lxzb.rest.modular.user.util.DateUtils;
import com.lzkj.lxzb.rest.modular.user.util.ExcelUtils;
import com.lzkj.lxzb.rest.modular.user.util.ResultMessageEnum;

/**
 * 支付宝提现管理
 * @author xkyan
 *
 */
@RestController
@RequestMapping("/alipay-withdrawal")
public class AliPayWithdrawalController {

	/**
	 *处理业务逻辑 
	 **/
	@Autowired
	private AlipayWithdrawalsService alipayWithdrawalsService;
	
	/**
	 *
	 * 查询支付宝提现列表
	 * 
	 **/
	@RequestMapping(value = "/list", method = { RequestMethod.POST,RequestMethod.GET })
	public ServletResponse<List<ThirdBindingCashExt>> list(ThirdBindingCashExt thirdBindingCashExt,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer pageNo,
    		@RequestParam(value = "limit", defaultValue = "20", required = false) Integer pageSize) {
    	
		Page<ThirdBindingCashExt> page = new Page<ThirdBindingCashExt>(pageNo, pageSize);
		
	    ServletResponse<List<ThirdBindingCashExt>> result = new ServletResponse<List<ThirdBindingCashExt>>();
	    
	    List<ThirdBindingCashExt> records = alipayWithdrawalsService.list(page, thirdBindingCashExt);
	    
	    BigDecimal currentPageTransactionSum = BigDecimal.valueOf(0);
	    
	    for (ThirdBindingCashExt bean:records) {
	    	currentPageTransactionSum = currentPageTransactionSum.add(bean.getNumcash());
	    }
	    
	    result.setSum(currentPageTransactionSum);
        result.isSuccess(page.getTotal(),records);
        result.setCode(ResultMessageEnum.success.getCode());
        result.setMsg(ResultMessageEnum.success.name());
	      
	    return result;  
	}
	
	
	/**
	 *
	 * 支付宝提现审核通过
	 * 
	 **/
	@RequestMapping(value = "/audit-pass", method = { RequestMethod.POST,RequestMethod.GET })
	public ServletResponse<List<Thirdbindingcash>> AlipayWithdrawalsExamineAdopt(BigDecimal[] numthirdbindingcashid){
		 
		ServletResponse<List<Thirdbindingcash>> result = new ServletResponse<List<Thirdbindingcash>>();
			  
		if (numthirdbindingcashid==null) {
		    result.setCode(ResultMessageEnum.fail.getCode());
		    result.setMsg(ResultMessageEnum.fail.name());  
		} else {
			int i = alipayWithdrawalsService.AlipayWithdrawalsBatchExamineAdopt(numthirdbindingcashid);
			 
			if (i > 0) {
				result.setCode(ResultMessageEnum.success.getCode());
			    result.setMsg(ResultMessageEnum.success.name()); 
			} else {
				result.setCode(ResultMessageEnum.fail.getCode());
			    result.setMsg(ResultMessageEnum.fail.name());
			}
		}
	 
		return result;
	}
	

	/**
	 *
	 * 支付宝提现审核(拒绝)
	 * 
	 **/
	@RequestMapping(value = "/audit-refuse", method = { RequestMethod.POST,RequestMethod.GET })
	public ServletResponse<List<Thirdbindingcash>> AlipayWithdrawalsExamineRefuse(Thirdbindingcash thirdbindingcash){
		ServletResponse<List<Thirdbindingcash>> result = new ServletResponse<List<Thirdbindingcash>>();
		
		if (thirdbindingcash==null||thirdbindingcash.getNumthirdbindingcashid()==null) {
			result.setCode(ResultMessageEnum.fail.getCode());
		    result.setMsg("参数不能为空");
		} else {
			int i = alipayWithdrawalsService.AlipayWithdrawalsExamineRefuse(thirdbindingcash);
			
			if (i > 0) {
				result.setCode(ResultMessageEnum.success.getCode());
			    result.setMsg(ResultMessageEnum.success.name()); 
			}else{
				result.setCode(ResultMessageEnum.fail.getCode());
			    result.setMsg(ResultMessageEnum.fail.name());
			}
		}
		
		return result;
	}
	
	
	/**
	 * 按条件导出数据 
	 * 
	 * @param response
	 * @param ThirdBindingCashExt
	 */
	//@RequestMapping(value = "/download", method = RequestMethod.POST)
	@RequestMapping(value = "/download", method = { RequestMethod.POST,RequestMethod.GET })
	public void down(HttpServletResponse response,ThirdBindingCashExt thirdBindingCashExt) {

		String[] titles = new String[]{"提现订单号", "提现用户名", "提现额度（元）",
				"应到账额度（元）","扣税额度（元）", "订单生成时间", "订单状态"};
		
		Page<ThirdBindingCashExt> page = new Page<ThirdBindingCashExt>(1, 100000);
		List<ThirdBindingCashExt> records = alipayWithdrawalsService.list(page, thirdBindingCashExt);
		   
		List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();
		
		for (ThirdBindingCashExt record : records) {
			Map<String, Object> row = new LinkedHashMap<String,Object>();
			row.put("vc2transactionid", record.getVc2transactionid());
			row.put("numuserid",record.getVc2sdkusername());// record.getNumuserid()
			row.put("numcash", record.getNumcash());
			row.put("numtransactionnum", record.getNumtransactionnum());
			row.put("numtax", record.getNumtax());
			row.put("datcreate",DateUtils.df.format(record.getDatcreate()));
					
			String value = (String)record.getVc2status();
			
			if("ORDER_CREATE".equals(value)) {
				value = "未审核";
			}else if("ORDER_CHECK_SUCCESS".equals(value)) {
				value = "已审核";
			}else if("ORDER_CHECK_REFUSE".equals(value)) {
				value = "已拒绝";
			}else if("ORDER_TRADE_SUCCESS".equals(value)) {
				value = "支付成功";
			}else if("ORDER_TRADE_FAIL".equals(value)) {
				value = "支付失败";
			}
			row.put("vc2status", value);
			rows.add(row);
		}
		
		try {
			ExcelUtils.exportExcel(titles, rows, "用户提现记录", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
