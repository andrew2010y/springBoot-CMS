package com.lzkj.lxzb.rest.common.intercepts;

import com.lzkj.lxzb.core.base.controller.BaseController;
import com.lzkj.lxzb.core.support.HttpKit;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 验证session超时的拦截器
 *
 * @author jiangzh
 * @date 2018年2月1日21:08:48
 */
//@Aspect
//@Component
public class SessionTimeoutInterceptor extends BaseController {

    @Pointcut("execution(* com.lzkj.lxzb.*..controller.*.*(..))")
    public void cutService() {
    }

    @Around("cutService()")
    public Object sessionTimeoutValidate(ProceedingJoinPoint point) throws Throwable {

        String servletPath = HttpKit.getRequest().getServletPath();

        System.out.println("servletPath="+servletPath);
        if (servletPath.equals("/system/login") || servletPath.equals("/login.html")) {
            return point.proceed();
        }else{
            if(SecurityUtils.getSubject().getSession().getAttribute("sessionFlag") == null){
                SecurityUtils.getSubject().logout();
                throw new InvalidSessionException();
            }else{
                return point.proceed();
            }
        }
    }
}
