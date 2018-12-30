//package com.example.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class MyInitBean implements CommandLineRunner {
//
//    @Value("${name}")
//    private String name;
//
//
//    public void run(String... args) {
//        System.out.println("项目启动时做些事情...");
//        log.info("name={}", name);
//    }
//
//}