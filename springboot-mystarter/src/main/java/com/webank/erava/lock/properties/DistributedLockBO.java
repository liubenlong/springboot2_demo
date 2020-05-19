package com.webank.erava.lock.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by hzliubenlong on 2017/12/13.
 */
@Data
@Component
@ConfigurationProperties(prefix = "distributedLock")
@Validated
public class DistributedLockBO {

    @NotNull(message = "db配置信息不可为空")
    @Valid
    private DB db;


    private boolean enabled;
}