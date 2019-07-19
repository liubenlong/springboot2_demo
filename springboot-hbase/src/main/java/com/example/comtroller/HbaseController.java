package com.example.comtroller;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用自定义的序列化方式
 * 即在SpringBootConfig类中添加RedisTemplate的bean
 */
@RestController
@Slf4j
public class HbaseController {
    @Autowired
    private HbaseTemplate hbaseTemplate;

    @GetMapping("/testHbase")
    public Object testHbase() {
        hbaseTemplate.put("mytable", "row1", "mycf", "name", Bytes.toBytes("aa"));
        return null;
    }

}
