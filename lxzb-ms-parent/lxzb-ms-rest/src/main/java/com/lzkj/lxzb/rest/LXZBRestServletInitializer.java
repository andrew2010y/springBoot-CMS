package com.lzkj.lxzb.rest;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 凌霄争霸 REST Web程序启动类
 *
 * @author jiangzh
 * @date 2018年01月24日 17:32:54
 */
public class LXZBRestServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LXZBRestApplication.class);
    }

}
