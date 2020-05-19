package com.webank.erava.lock.properties;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * Created by hzliubenlong on 2017/12/13.
 */
@Data
@Component
@ConfigurationProperties(prefix = "distributedLock.db")
@Validated
public class DB {
    @NotBlank(message = "driverClassName 不可为空")
    private String driverClassName;
    @NotBlank(message = "connectionUrl 不可为空")
    private String connectionUrl;
    @NotBlank(message = "userName 不可为空")
    private String userName;
    @NotBlank(message = "password 不可为空")
    private String password;
    @Range(min = 1, max = 5, message = "initialSize 取值范围是[1-5]")
    private int initialSize;
    @Range(min = 1, max = 5, message = "minIdle 取值范围是[1-5]")
    private int minIdle;
    @Range(min = 1, max = 5, message = "maxActive 取值范围是[1-5]")
    private int maxActive;

}