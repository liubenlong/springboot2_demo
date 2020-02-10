package com.example.pojo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        writeObj();
        readObj();

        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqId(1);
        builder.setUserName("zzf");
        builder.setProductName("Netty Book");
        List<String> address = new ArrayList<>();
        address.add("浙江");
        address.add("超人");
        address.add("乌龟");
        builder.addAllAddress(address);
        SubscribeReqProto.SubscribeReq req = builder.build();
        System.out.println("Before encode:" + req.toString());

        SubscribeReqProto.SubscribeReq result = SubscribeReqProto.SubscribeReq.parseFrom(req.toByteArray());
        System.out.println("decode cotent is:" + result.toString());

        result.getAddressList().forEach(s -> System.out.println(s));

        System.out.println();
        System.out.println(req.equals(result));
    }

    public static void writeObj() throws Exception {
        Demo.Person person = Demo.Person.newBuilder().setId(1).setEmail("a.163.com").setName("小明").build();
        person.writeTo(new FileOutputStream("/Users/liubenlong/soft/prototbuf.data"));
    }

    public static void readObj() throws Exception {
        Demo.Person person = Demo.Person.parseFrom(new FileInputStream("/Users/liubenlong/soft/prototbuf.data"));
        System.out.println(String.format("id=%s  name=%s  email=%s", person.getId(), person.getName(), person.getEmail()));
    }

}
