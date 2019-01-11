package com.example.mapper;

import com.example.config.TargetDataSource;
import com.example.model.Cat;
import com.example.model.CatExample;
import java.util.List;

public interface CatMapper {
    @TargetDataSource(name = "ds2")
    long countByExample(CatExample example);
    @TargetDataSource(name = "ds2")
    int deleteByPrimaryKey(Integer id);
    @TargetDataSource(name = "ds2")
    int insert(Cat record);
    @TargetDataSource(name = "ds2")
    int insertSelective(Cat record);
    @TargetDataSource(name = "ds2")
    List<Cat> selectByExample(CatExample example);
    @TargetDataSource(name = "ds2")
    Cat selectByPrimaryKey(Integer id);
    @TargetDataSource(name = "ds2")
    int updateByPrimaryKeySelective(Cat record);
    @TargetDataSource(name = "ds2")
    int updateByPrimaryKey(Cat record);
}