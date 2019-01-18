package com.example.pojo;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Stu implements Serializable {

    @Id
    private String id;

    private String name;

    private Integer age;

    private String address;
}
