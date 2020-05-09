package com.example.controller;

import com.example.vo.FatherVO;
import com.example.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController("validateController")
@Slf4j
public class ValidateController {


    @RequestMapping(value = "posttest", method = RequestMethod.POST)
    public String test1(@Validated @RequestBody StudentVO studentVO) {
        return studentVO.getName();
    }

    @RequestMapping(value = "gettest", method = RequestMethod.GET)
    public String test2(@Validated @RequestBody FatherVO father) {
        return father.getName();
    }

    @RequestMapping(value = "nomal", method = RequestMethod.GET)
    public String test3(@NotBlank(message = "name 不可为空") String name,
                        @NotNull(message = "age不可为空") @Min(value = 2, message = "最小2") Integer age) {
        return name;
    }
}