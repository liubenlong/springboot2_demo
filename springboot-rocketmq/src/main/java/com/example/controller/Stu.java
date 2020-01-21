package com.example.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stu {
    private String id;
    private String name;
    private int age;
    private BigDecimal money;
    private List<String> mobiles;
}
