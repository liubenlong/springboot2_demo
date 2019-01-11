//package com.example.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class MybatisMasterDbConfig {
//
//    @Bean(name = "masterDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.druid")
//    public DataSource testDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//}