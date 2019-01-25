package com.example.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.provider.model.Stu;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Service(registry = "registry1")
@Slf4j
public class DefaultDemoService implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot 2)";
    }

    @Override
    public Stu sayHello1(Stu stu) {
        stu.setBirthday(new Date());
        return stu;
    }

}