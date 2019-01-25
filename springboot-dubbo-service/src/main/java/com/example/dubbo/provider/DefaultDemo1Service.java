package com.example.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.provider.model.Stu;
import lombok.extern.slf4j.Slf4j;

@Service
//注册到多个配置中心：
//@Service(registry = {"registry1","registry2"})
@Slf4j
public class DefaultDemo1Service implements Demo1Service {
    @Override
    public String sayHello3(String name) {
        return "Hello, " + name + " (from Spring Boot 2)";
    }

    @Override
    public Stu sayHello4(Stu stu) {
        return null;
    }
}