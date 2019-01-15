package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "stu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Stu {
    private String name;
    private int age;
}
