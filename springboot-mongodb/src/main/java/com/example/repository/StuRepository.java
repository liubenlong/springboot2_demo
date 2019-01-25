package com.example.repository;

import com.example.pojo.Stu;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StuRepository extends MongoRepository<Stu, Long> {


}