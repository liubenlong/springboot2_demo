package com.example.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class FatherVO {
    @NotBlank(message = "父亲名字不可为空")
    private String name;
    @NotBlank(message = "父亲工作不可为空")
    private String work;
}

