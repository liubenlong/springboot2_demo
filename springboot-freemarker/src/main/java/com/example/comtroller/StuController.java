package com.example.comtroller;

import com.example.pojo.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


//Tips: 由于要返回模板页面文件，所以我们只能使用@Controller 而不可以使用@RestController
//@RestController
@Controller
@Slf4j
public class StuController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/welcome")
    public String hello1(Model m){
        m.addAttribute("name", "spring-boot11");
        return "welcome";
    }

    @RequestMapping("hello")
    public ModelAndView hello(ModelAndView m){
        m.addObject("name", "spring-boot");
        m.setViewName("welcome");
        return m;
    }

    @RequestMapping("sysUser")
    public String user(Model m){
        List<SysUser> list = new ArrayList<>();
        SysUser u1 = new SysUser(0001, "hello1", "11111111111111111");
        SysUser u2 = new SysUser(0002, "hello2", "22222222222222222");
        SysUser u3 = new SysUser(0003, "hello3", "33333333333333333");
        list.add(u1);
        list.add(u2);
        list.add(u3);
        m.addAttribute("userList", list);
        m.addAttribute("sysUser", "SysUser");
        return "sysUser/users";
    }

}