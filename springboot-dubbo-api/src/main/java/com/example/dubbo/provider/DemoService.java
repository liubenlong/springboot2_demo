package com.example.dubbo.provider;

import com.example.dubbo.provider.model.Stu;

public interface DemoService {

    String sayHello(String name);

    Stu sayHello1(Stu stu);

}