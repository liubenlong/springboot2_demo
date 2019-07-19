package com.example.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;

@Service
public class HBaseService {
    @Autowired
    private HbaseTemplate hbaseTemplate;

    public void saveDeviceHeartbeat(String uuid, JSONObject heartObject) {
        hbaseTemplate.put("mytable", "row1", "mycf", "uuid", Bytes.toBytes(uuid));
    }
}