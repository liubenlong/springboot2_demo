package com.example.repository;

import com.example.pojo.Stu;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StuRepository extends ReactiveMongoRepository<Stu, Long> {


}