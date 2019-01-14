package com.example.comtroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.pojo.Stu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 不使用自定义的序列化方式
 * 即SpringBootConfig中不添加RedisTemplate的bean
 * <p>
 * 这个类和SpringBootConfig有一个开启
 */
@RestController
@RequestMapping("hello")
@Slf4j
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("redisTemplate2")
    private RedisTemplate redisTemplate2;

    @GetMapping("/deleteVal")
    public void deleteVal() {
        redisTemplate.delete("a");
        redisTemplate.delete("b");
        redisTemplate2.delete("a");
        redisTemplate2.delete("b");
    }

    @GetMapping("/testRedis")
    public Stu testRedis() {
        Stu stu = Stu.builder().name("张三").age(20).build();
        redisTemplate.opsForValue().set("a", stu);
        Object a = redisTemplate.opsForValue().get("a");
        return JSON.toJavaObject((JSONObject) a, Stu.class);
    }

    @GetMapping("/testRedis2")
    public Stu testRedis2() {
        Stu stu = Stu.builder().name("李四").age(30).build();
        redisTemplate2.opsForValue().set("a", stu);
        Object a = redisTemplate2.opsForValue().get("a");
        return JSON.toJavaObject((JSONObject) a, Stu.class);
    }

}
