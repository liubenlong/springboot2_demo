package com.example.pojo;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Stu implements Serializable {

    @Id
    private String id;

    private String name;

    private Integer age;

    private String address;

}