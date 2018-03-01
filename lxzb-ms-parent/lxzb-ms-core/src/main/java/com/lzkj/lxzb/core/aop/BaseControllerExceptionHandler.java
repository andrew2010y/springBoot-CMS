package com.lzkj.lxzb.core.aop;

import com.lzkj.lxzb.core.base.tips.ErrorTip;
import com.lzkj.lxzb.core.exception.LZKJException;
import com.lzkj.lxzb.core.exception.LZKJExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author jiangzh
 * @date 2018年01月24日 17:32:54
 */
public class BaseControllerExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截业务异常
     *
     * @author jiangzh
     */
    @ExceptionHandler(LZKJException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFount(LZKJException e) {
        log.error("业务异常:", e);
        return new ErrorTip(e.getCode(), e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     *
     * @author jiangzh
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return new ErrorTip(LZKJExceptionEnum.SERVER_ERROR.getCode(), LZKJExceptionEnum.SERVER_ERROR.getMessage());
    }

}
