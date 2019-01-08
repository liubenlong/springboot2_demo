package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloServiceImpl implements HelloService{

    public String getVal(){
        return "haha";
    }

    //模拟远程调用，或者其他服务调用
    public String getRemoteVal(){
        log.info("真正发起外部请求");
        return "remote Val";
    }
}
