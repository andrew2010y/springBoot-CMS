package com.lzkj.lxzb.rest.modular.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.lzkj.lxzb.rest.base.vo.ServletResponse;
import com.lzkj.lxzb.rest.modular.user.util.ResultMessageEnum;

//@RestController
@Controller
@RequestMapping("/system")
public class SystemController  {

	private Logger logger = LoggerFactory.getLogger(SystemController.class);

	@RequestMapping(value = "/sessionTimeOut", method = { RequestMethod.GET})
	private String sessionTimeOut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		//如果是Ajax请求
		if(httpServletRequest.getHeader("x-requested-with")!=null
				&&httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
			return "redirect:/system/ajaxSuccess";
		}

		return "redirect:/login.html";
	}
	
	@RequestMapping(value = "/ajaxSuccess")
	@ResponseBody
	public ServletResponse<Boolean> ajaxSessionTimeOut(){

		ServletResponse<Boolean> servletResponse = new ServletResponse<>();
		servletResponse.setCode(-1);
		servletResponse.setMsg("Session超时");

		return servletResponse;
	}

	
	/**
	 * 登录验证逻辑处理
	 * 
	 * 登录验证通过调到系统主页main方法初始化页面数据，验证不通过调到登录页面提示用户登录失败及失败原因
	 *
	 * @return
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.POST})
	@ResponseBody
	private ServletResponse<Boolean> login(String username,String password) {
		
		ServletResponse<Boolean> result = new ServletResponse<Boolean>();
        
		try {
			Subject subject = SecurityUtils.getSubject();
			
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
			subject.login(token);

			SecurityUtils.getSubject().getSession().setAttribute("sessionFlag",true);

			result.setCode(ResultMessageEnum.success.getCode());
		    result.setMsg(ResultMessageEnum.success.name());
			
			logger.info("msg", "登录成功,登陆账号:"+username+"  登陆密码："+password);
		} catch (IncorrectCredentialsException e) {
			result.setCode(1);
		    result.setMsg("密码错误");
			logger.error("msg", "密码错误,登陆账号:"+username+"  登陆密码："+password);
		} catch (LockedAccountException e) {
			result.setCode(2);
		    result.setMsg("登录失败，该用户已被冻结");
			logger.error("msg", "登录失败，该用户已被冻结,登陆账号:"+username+"  登陆密码："+password);
		} catch (AuthenticationException e) {
			result.setCode(3);
		    result.setMsg("该用户不存在");
			logger.error("msg", "该用户不存在,登陆账号:"+username+"  登陆密码："+password);
		} catch (Exception e) {
			result.setCode(4);
		    result.setMsg("该用户不存在");
			logger.error("登陆异常，登陆账号："+username);
		}
		
		return result;
	}

	
	/**
	 * 管理后台用户退出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	private ServletResponse<Boolean> logout(HttpServletRequest request) {

		// 清空session中操作人
		Subject subject = SecurityUtils.getSubject();
		
		subject.logout();

		ServletResponse<Boolean> result = new ServletResponse<Boolean>();
        
        result.setCode(ResultMessageEnum.success.getCode());
        result.setMsg(ResultMessageEnum.success.name());
	        
	    return result;    
	}

}
