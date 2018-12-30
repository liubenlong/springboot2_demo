package com.example.controller;

import com.example.pojo.Person;
import com.example.pojo.Stu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {


    @Autowired
    private Stu stu;
    @Autowired
    private Person person;

    @GetMapping("/properties1")
    public String properties1() {
        log.debug("com.example.controller.HelloController.properties1 执行");
        log.info("stu={}", stu);
        log.info("person={}", person);

        return "Welcome to springboot2 world ~";
    }


    @GetMapping("/hello")
    public String hello() {
        return "Welcome to springboot2 world ~";
    }


}