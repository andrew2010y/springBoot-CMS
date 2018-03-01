package com.lzkj.lxzb.rest.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @author jiangzh
 * @Date 2018年01月24日 17:32:54
 */
@Configuration
@MapperScan(basePackages = {"com.lzkj.lxzb.rest.*.dao", "com.lzkj.lxzb.lxzbEntities.entity.raw.mapper"
		, "com.lzkj.lxzb.rest.modular.user.dao"})

public class MybatisPlusConfig {

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
