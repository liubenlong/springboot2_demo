package com.example.repository;

import com.example.pojo.Stu;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface StuRepository extends ReactiveMongoRepository<Stu, Long> {

    //@Tailable注解的作用类似于linux的tail命令，被注解的方法将发送无限流
    @Tailable
    Flux<Stu> findBy();

}