package com.example.comtroller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo.provider.Demo1Service;
import com.example.dubbo.provider.DemoService;
import com.example.dubbo.provider.model.Stu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class DemoConsumerController {

    @Reference
    private Demo1Service demo1Service;
    @Reference(registry = "registry1")
    private DemoService demoService;


    @RequestMapping("/test")
    public Map<String, String> sayHello(@RequestParam String name) {
        return Map.of("demo1Serviceresult", demo1Service.sayHello3(name), "demoServiceresult", demoService.sayHello(name));
    }

    @RequestMapping("/test1")
    public Stu sayHello1(@RequestParam int age, String name) {
        Stu stu = demoService.sayHello1(Stu.builder().age(age).name(name).build());
        return stu;
    }
}