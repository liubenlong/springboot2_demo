package com.example.controller;

import com.example.vo.FatherVO;
import com.example.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}