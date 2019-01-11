//package com.example.comtroller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.example.pojo.Stu;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 不使用自定义的序列化方式
// * 即SpringBootConfig中不添加RedisTemplate的bean
// *
// * 这个类和SpringBootConfig有一个开启
// */
//@RestController
//@RequestMapping("hello")
//@Slf4j
//public class RedisController1 {
//
//    @Autowired
//    private RedisTemplate<Object, Object> redisTemplate;
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @GetMapping("/deleteVal")
//    public void deleteVal() {
//        stringRedisTemplate.delete("a");
//        stringRedisTemplate.delete("b");
//        redisTemplate.delete("a");
//        redisTemplate.delete("b");
//    }
//
//    @GetMapping("/testRedis")
//    public Object testRedis() {
//        Stu stu = Stu.builder().name("张三").age(20).build();
//        stringRedisTemplate.opsForValue().set("a", JSONObject.toJSONString(stu));
//        Object a = stringRedisTemplate.opsForValue().get("a");
//        return a;
//    }
//
//    @GetMapping("/testRedis1")
//    public Stu testRedis1(int age) {
//        Stu stu = Stu.builder().name("李四").age(age).build();
//        redisTemplate.opsForValue().set("b", stu);
//        Stu stu1 = (Stu) redisTemplate.opsForValue().get("b");
//        return stu1;
//    }
//
//    @GetMapping("/readVal")
//    public void readVal() {
//        Object a = redisTemplate.opsForValue().get("a");
//        log.info(a.toString());
//        String b = stringRedisTemplate.opsForValue().get("b");
//        log.info(b);
//    }
//}
