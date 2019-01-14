package com.example.dubbo.provider.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@ToString
public class Stu implements Serializable {

    private Integer id;

    @NotEmpty
    private String name;

    @Range(min = 10, max = 20)
    private Integer age;

    private Date birthday;

}