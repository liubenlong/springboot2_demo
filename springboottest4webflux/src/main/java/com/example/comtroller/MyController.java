package com.example.comtroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 这里的controller和RouterFunction可以共存
 */
@RestController
public class MyController {

    /**
     * 最简单的webflux程序
     * @return
     */
    @GetMapping("/hello")
    public Mono<String> hello1() {
        return Mono.just("Welcome to reactive world ~");
    }
}
