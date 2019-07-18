package com.example.comtroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用自定义的序列化方式
 * 即在SpringBootConfig类中添加RedisTemplate的bean
 */
@RestController
@Slf4j
public class HbaseController {

    @GetMapping("/testHbase")
    public Object testHbase() {

        return null;
    }

}
