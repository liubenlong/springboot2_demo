//package com.example.service;
//
//import com.example.config.HadoopConfig;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//
//@Component
//@ConditionalOnBean(FileSystem.class)
//@Slf4j
//public class HadoopTemplate {
//
//    @Autowired
//    private FileSystem fileSystem;
//    @Autowired
//    private HadoopConfig hadoopConfig;
//
//    @PostConstruct
//    public void init() {
//        existDir(hadoopConfig.getNameSpace(), true);
//    }
//
//    public void uploadFile(String srcFile) {
//        copyFileToHDFS(false, true, srcFile, hadoopConfig.getNameSpace());
//    }
//
//    public void uploadFile(String srcFile, String destPath) {
//        copyFileToHDFS(false, true, srcFile, destPath);
//    }
//
//    public void delFile(String fileName) {
//        rmdir(hadoopConfig.getNameSpace(), fileName);
//    }
//
//    public void delDir(String path) {
//        rmdir(hadoopConfig.getNameSpace() + "/" + path, null);
//    }
//
//    public void download(String fileName, String savePath) {
//        getFile(hadoopConfig.getNameSpace() + "/" + fileName, savePath);
//    }
//
//
//
//
//}
