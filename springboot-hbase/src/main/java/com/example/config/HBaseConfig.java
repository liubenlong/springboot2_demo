package com.example.config;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

import java.net.URISyntaxException;

@Configuration
public class HBaseConfig
{
    @Bean
    public HbaseTemplate hbaseTemplate()
    {
        HbaseTemplate hbaseTemplate = new HbaseTemplate();
        hbaseTemplate.setConfiguration(getConfiguration());
        hbaseTemplate.setAutoFlush(true);
        return hbaseTemplate;
    }

    private org.apache.hadoop.conf.Configuration getConfiguration()
    {
        try
        {
            org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
            configuration.addResource(new Path(ClassLoader.getSystemResource("hdfs-site.xml").toURI()));
            configuration.addResource(new Path(ClassLoader.getSystemResource("core-site.xml").toURI()));
            configuration.addResource(new Path(ClassLoader.getSystemResource("hbase-site.xml").toURI()));
            return configuration;
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}