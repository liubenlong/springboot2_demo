package com.webank.erava.lock.distributedLock;

import java.lang.annotation.*;

/**
 * 分布式锁
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {

    /**
     * 锁的key
     *
     * @return
     */
    String key();

    /**
     * 锁有效期，默认1分钟
     * 单位：毫秒
     *
     * @return
     */
    int expire() default 1000 * 60;

    /**
     * 是否EL
     */
    boolean el() default false;

}