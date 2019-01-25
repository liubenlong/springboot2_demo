package com.example.comtroller;

import com.example.pojo.Stu;
import com.example.repository.StuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;

/**
 * 这里的controller和RouterFunction可以共存
 */
@RestController
public class MyController {

    @Autowired
    private StuRepository stuRepository;

    /**
     * 最简单的webflux程序
     *
     * @return
     */
    @GetMapping("/hello")
    public Mono<String> hello1() {
        return Mono.just("Welcome to reactive world ~");
    }

    @GetMapping("/save")
    public Mono<Stu> save() {
        return stuRepository.save(Stu.builder().name("赵六").age(12).build());
    }
    @GetMapping("/findAll")
    public Flux<Stu> findAll() {
        return stuRepository.findAll();
    }

    @GetMapping("/findOne")
    public Flux<Stu> find1() {
        Example<Stu> e = Example.of(Stu.builder().name("张三").build());
        return stuRepository.findAll(e);
    }

    /**
     * 以stream+json流的方式推送到客户端
     * @return
     */
    @GetMapping(value = "/findAllPreSec", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Stu> findAllPreSec() {
        return stuRepository.findAll().delayElements(Duration.ofSeconds(1));
    }


    /**
     * 接收数据流的服务端
     * 客户端需要提供事件流
     * @return
     */
    @PostMapping(path = "loadStus", consumes = MediaType.APPLICATION_STREAM_JSON_VALUE) // 指定consume数据流是application/stream+json。上面方法是produces！！！
    public Mono<Void> loadStus(@RequestBody Flux<Stu> events) {
        // insert返回的是保存成功的记录的Flux，但我们不需要，使用then方法表示“忽略数据元素，只返回一个完成信号”。
        return this.stuRepository.insert(events).then();
    }



    @GetMapping(path = "getStusUnlimited", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)//produces生产者是一个数据流
    public Flux<Stu> getStusUnlimited() {
        return this.stuRepository.findBy();
    }
}
