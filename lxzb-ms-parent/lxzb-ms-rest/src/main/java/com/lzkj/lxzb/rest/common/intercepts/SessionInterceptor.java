package com.lzkj.lxzb.rest.common.intercepts;

import com.lzkj.lxzb.core.base.controller.BaseController;
import com.lzkj.lxzb.core.util.HttpSessionHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 静态调用session的拦截器
 *
 * @author jiangzh
 * @date 2018年2月1日21:08:48
 */
//@Aspect
//@Component
public class SessionInterceptor extends BaseController {

    @Pointcut("execution(* com.lzkj.lxzb.*..controller.*.*(..))")
    public void cutService() {
    }

    @Around("cutService()")
    public Object sessionKit(ProceedingJoinPoint point) throws Throwable {

        HttpSessionHolder.put(super.getHttpServletRequest().getSession());
        try {
            return point.proceed();
        } finally {
            HttpSessionHolder.remove();
        }
    }
}
