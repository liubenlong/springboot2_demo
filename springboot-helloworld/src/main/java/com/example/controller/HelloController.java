package com.example.controller;

import com.example.pojo.Dog;
import com.example.pojo.Person;
import com.example.pojo.Stu;
import com.example.service.HelloService;
//import com.webank.erava.lock.distributedLock.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {


    @Autowired
    private Stu stu;
    @Autowired
    private Person person;
    @Autowired
    private HelloService helloService;


    @RequestMapping("/annotition")
    //分布式锁，自定义springboot stater
//    @DistributedLock(key = "#dog.name.concat('d')", expire = 1000, el = true)
    @ResponseBody
    public String annotition(Dog dog) {
        return dog.getName();
    }

    @RequestMapping("/annotition1")
//    @DistributedLock(key = "#name", expire = 1000, el = true)
    @ResponseBody
    public String annotition1(String name) {
        return name;
    }

    @GetMapping("/properties1")
    public String properties1() {
        String s = new String();
        boolean s1 = s.equals("s");

        System.out.println(s.getClass().toString() + s1);

        log.debug("com.example.controller.HelloController.properties1 执行");
        log.info("stu={}", stu);
        log.info("person={}", person);

        return "Welcome to springboot2 world ~";
    }


    @GetMapping("/hello")
    public String hello() {
        return "Welcome to springboot2 world ~";
    }

    @GetMapping("/hello1")
    public String hello1() {
        return helloService.getVal();
    }

    public static void main(String[] args) {

        String s = new String();
        boolean s1 = s.equals("s");

        System.out.println(s.getClass().toString() + s1);
    }
}