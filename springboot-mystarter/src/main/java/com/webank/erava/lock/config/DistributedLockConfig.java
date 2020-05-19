package com.webank.erava.lock.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.webank.erava.lock.properties.DistributedLockBO;
import com.webank.erava.lock.service.DistributedLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DistributedLockBO.class)
    public class DistributedLockConfig {

    @Autowired
    private DistributedLockBO distributedLockBO;

    @ConditionalOnProperty(prefix = "distributedLock", value = "enabled", matchIfMissing = true)
    @ConditionalOnClass(DistributedLockService.class)
    @Bean(name="DistributedLockDataSource")
    public DruidDataSource initHelloService() {
        DruidDataSource dataSource = new DruidDataSource(); // 创建Druid连接池
        dataSource.setDriverClassName(distributedLockBO.getDb().getDriverClassName()); // 设置连接池的数据库驱动
        dataSource.setUrl(distributedLockBO.getDb().getConnectionUrl()); // 设置数据库的连接地址
        dataSource.setUsername(distributedLockBO.getDb().getUserName()); // 设置数据库的用户名
        dataSource.setPassword(distributedLockBO.getDb().getPassword()); // 设置数据库的密码
        dataSource.setInitialSize(distributedLockBO.getDb().getInitialSize()); // 设置连接池的初始大小
        dataSource.setMinIdle(distributedLockBO.getDb().getMinIdle()); // 设置连接池大小的下限
        dataSource.setMaxActive(distributedLockBO.getDb().getMaxActive()); // 设置连接池大小的上限
        return dataSource;
    }



}