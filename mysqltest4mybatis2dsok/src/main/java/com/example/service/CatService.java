package com.example.service;

import com.example.mapper.second.CatMapper;
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

    public List<Cat> findAll() {
        return catMapper.selectByExample(null);
    }

    public Cat findByPK(int id) {
        return catMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKey(Cat cat) {
        return catMapper.updateByPrimaryKey(cat);
    }

}
