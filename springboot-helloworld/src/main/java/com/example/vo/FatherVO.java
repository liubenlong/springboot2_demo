package com.example.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class FatherVO {

    public interface A{}

    @NotBlank(message = "父亲名字不可为空", groups = A.class)
    private String name;
    @NotBlank(message = "父亲工作不可为空")
    private String work;



    private Boolean a;
}

