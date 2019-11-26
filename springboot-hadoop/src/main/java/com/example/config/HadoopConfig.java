package com.example.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
@ConfigurationProperties(prefix = "hadoop")
@Data
@Slf4j
public class HadoopConfig {

    private String nameNode;
    private String nameSpace;

//    @Bean("fileSystem")
//    public FileSystem createFs() throws URISyntaxException, IOException, InterruptedException {
//        //读取配置文件
//        Configuration conf = new Configuration();
//        FileSystem fs = FileSystem.get(new URI(nameNode), conf, "appweb");
//        return fs;
//    }
}
