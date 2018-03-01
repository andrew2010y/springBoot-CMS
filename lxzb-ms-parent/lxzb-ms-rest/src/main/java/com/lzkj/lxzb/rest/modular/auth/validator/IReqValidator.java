package com.lzkj.lxzb.rest.modular.auth.validator;

import com.lzkj.lxzb.rest.modular.auth.validator.dto.Credence;

/**
 * <p>验证请求/auth接口时,请求参数的正确性</p>
 * <p>
 * <p>如果想拓展验证方法只需实现这个接口,然后在AuthenticationRestController类中注意相应实现的本接口的类即可</p>
 *
 * @author jiangzh
 * @date 2018年01月24日 17:32:54
 */
public interface IReqValidator {

    /**
     * 通过请求参数验证
     *
     * @author jiangzh
     * @Date
     */
    boolean validate(Credence credence);
}
