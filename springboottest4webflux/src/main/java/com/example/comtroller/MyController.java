package com.example.comtroller;

import com.example.pojo.Stu;
import com.example.repository.StuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
