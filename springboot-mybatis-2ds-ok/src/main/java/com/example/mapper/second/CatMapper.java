package com.example.mapper.second;

import com.example.model.Cat;
import com.example.model.CatExample;

import java.util.List;

public interface CatMapper {
    long countByExample(CatExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cat record);

    int insertSelective(Cat record);

    List<Cat> selectByExample(CatExample example);

    Cat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cat record);

    int updateByPrimaryKey(Cat record);
}