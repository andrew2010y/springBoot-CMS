package com.lzkj.lxzb.core.util;

import com.baomidou.mybatisplus.toolkit.IdWorker;

/**
 * 唯一id生成器
 *
 * @author jiangzh
 * @date 2018年01月24日
 */
public class IdGenerator {

    public static String getId() {
        return String.valueOf(IdWorker.getId());
    }

    public static long getIdLong() {
        return IdWorker.getId();
    }
}
