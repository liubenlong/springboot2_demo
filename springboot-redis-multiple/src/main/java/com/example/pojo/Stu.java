package com.example.pojo;

import lombok.*;

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
    private String name;
    private int age;
    private Date date;
    private BigDecimal b;
    private Duration d;
}
