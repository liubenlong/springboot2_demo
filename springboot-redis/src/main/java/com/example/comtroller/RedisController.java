package com.example.comtroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.pojo.Stu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;

/**
 * 使用自定义的序列化方式
 * 即在SpringBootConfig类中添加RedisTemplate的bean
 */
@RestController
@Slf4j
public class RedisController {


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 测试使用自定义的序列化方式
     *
     * @param age
     * @return
     */
    @GetMapping("/testRedis1")
    public Object testRedis1(int age) {
        redisTemplate.opsForValue().set("c", "c");
        Object c = redisTemplate.opsForValue().get("c");
        log.info(c.getClass().getName());
        log.info(c.toString());

        Stu stu = Stu.builder().name("李四").age(age).b(BigDecimal.TEN).d(Duration.ofSeconds(10000)).date(new Date()).build();
        redisTemplate.opsForValue().set("b", stu);
        JSONObject b = (JSONObject) redisTemplate.opsForValue().get("b");
        log.info(b.getClass().getName());
        Stu s = JSON.toJavaObject(b, Stu.class);
        log.info(s.toString());
        return s;
    }

}
