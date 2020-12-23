package com.example.vo;

import lombok.Data;

@Data
public class HelloVO {
    String name;

    int age;
    String desc;
    InnerVO innerVO;

    @Override
    public String toString() {
        return "HelloVO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", desc='" + desc + '\'' +
                ", innerVO=" + innerVO +
                '}';
    }
}
