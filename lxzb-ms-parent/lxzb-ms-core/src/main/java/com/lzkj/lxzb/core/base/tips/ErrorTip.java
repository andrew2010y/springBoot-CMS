package com.lzkj.lxzb.core.base.tips;

/**
 * 返回给前台的错误提示
 *
 * @author jiangzh
 * @date 2018年01月24日 17:32:54
 */
public class ErrorTip extends Tip {

    public ErrorTip(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
}
