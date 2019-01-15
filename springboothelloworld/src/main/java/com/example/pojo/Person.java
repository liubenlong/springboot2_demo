package com.example.pojo;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "person")
@Data
@ToString
@Validated
public class Person {
    //各种普通数据类型
    @NotEmpty
    private String lastName;
    @Range(min = 0, max = 50)
    private Integer age;
    private Boolean boss;
    private Date birth;

    //复杂类型展示
    private Map<String,Object> map;
    private List<Object> list;
    private List<Dog> dogs;
    private Stu stu;

}