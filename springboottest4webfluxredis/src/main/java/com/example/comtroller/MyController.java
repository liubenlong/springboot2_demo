package com.example.comtroller;

import com.alibaba.fastjson.JSONObject;
import com.example.pojo.Stu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 这里的controller和RouterFunction可以共存
 */
@RestController
@Slf4j
public class MyController {

    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    /**
     * 最简单的webflux程序
     *
     * @return
     */
    @GetMapping("/hello")
    public Mono<String> hello1() {
        return Mono.just("Welcome to reactive world ~");
    }


    @GetMapping("/deleteVal")
    public Flux deleteVal() {
        Mono a = reactiveRedisTemplate.delete("a");
        Mono c = reactiveRedisTemplate.delete("c");
        a.subscribe(System.out::println);//这里需要消费才行。否则无法真正操作。
        c.subscribe(System.out::println);

        return Flux.just(a, c);
    }

    @GetMapping("testReactorRedis1")
    public Flux findCityById() {
        Mono mono1 = reactiveRedisTemplate.opsForValue().set("c", "vvvv");
        mono1.subscribe(System.out::println);

        Stu stu = Stu.builder().name("张三").age(20).build();
        Mono mono2 = reactiveRedisTemplate.opsForValue().set("a", JSONObject.toJSONString(stu));
        mono2.subscribe(System.out::println);

        return Flux.just(mono1, mono2);
    }

    @GetMapping("/testReactorRedis2")
    public Mono testReactorRedis2() {
        Mono monoa = reactiveRedisTemplate.opsForValue().get("a");
        return monoa;
    }

    @GetMapping("/testReactorRedis3")
    public Flux testReactorRedis3() {
        Flux flux = Flux.just("a", "c")
                .flatMap(s -> reactiveRedisTemplate.opsForValue().get(s));
        flux.subscribe(System.out::println);
        return flux;
    }


    /**
     * 这样是不能够获取到数据的，因为并没有真正发送请求。输出如下：
     * {
     * monoa: {
     * scanAvailable: true
     * },
     * monoc: {
     * scanAvailable: true
     * }
     * }
     *
     * @return
     */
    @GetMapping("/testReactorRedis4")
    public Map testReactorRedis4() {
        Mono monoa = reactiveRedisTemplate.opsForValue().get("a");
        Mono monoc = reactiveRedisTemplate.opsForValue().get("c");
        return Map.of("monoa", monoa, "monoc", monoc);
    }
}
