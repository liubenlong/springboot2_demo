package com.example.service;

import com.example.config.TargetDataSource;
import com.example.mapper.CatMapper;
import com.example.model.Cat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CatService {

    @Autowired
    private CatMapper catMapper;

    @TargetDataSource(name = "ds2")
    public List<Cat> findAll() {
        return catMapper.selectByExample(null);
    }


}
