package com.example.comtroller;

import com.example.pojo.SysUser;
import com.example.service.FreemarkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


//Tips: 由于要返回模板页面文件，所以我们只能使用@Controller 而不可以使用@RestController
//@RestController
@Controller
@Slf4j
public class StuController {

    @Autowired
    private FreemarkerService freemarkerService;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/welcome")
    public String hello1(Model m) {
        m.addAttribute("name", "spring-boot11");
        return "welcome";
    }

    @RequestMapping("hello")
    public ModelAndView hello(ModelAndView m) {
        m.addObject("name", "spring-boot");
        m.setViewName("welcome");
        return m;
    }

    @RequestMapping("sysUser")
    public String user(Model m) {
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

    @RequestMapping("test1")
    @ResponseBody
    public String test1() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 20);
        String s = freemarkerService.process("test1.ftl", map);
        log.info(s);
        return s;
    }


    @RequestMapping("test2")
    @ResponseBody
    public String test2() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "FreeMarker是一款用java语言编写的模版引擎");
        map.put("dateTime", new Date());

        List<SysUser> users = new ArrayList<>();
        users.add(new SysUser(1, "ITDragon 博客", "111"));
        users.add(new SysUser(2, "欢迎", "13333333333"));
        users.add(new SysUser(3, "You！", null));
        map.put("users", users);
        String s = freemarkerService.process("test2.ftl", map);
        log.info(s);
        return s;
    }

}