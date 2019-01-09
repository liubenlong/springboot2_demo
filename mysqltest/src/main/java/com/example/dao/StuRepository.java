package com.example.dao;

import com.example.pojo.Stu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StuRepository extends JpaRepository<Stu, Long> {

    /**
     * 根据名字自定义方法
     * @param name
     * @param age
     * @return
     */
    List<Stu> findByNameAndAge(String name, int age);

    @Query(value="select * from stu where name like %?%" ,nativeQuery=true)
    List<Stu> findLikeName(String name);
}