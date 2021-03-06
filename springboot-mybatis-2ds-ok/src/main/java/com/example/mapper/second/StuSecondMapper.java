package com.example.mapper.second;

import com.example.model.Stu;
import com.example.model.StuExample;

import java.util.List;

public interface StuSecondMapper {

    long countByExample(StuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Stu record);

    int insertSelective(Stu record);

    List<Stu> selectByExample(StuExample example);

    Stu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stu record);

    int updateByPrimaryKey(Stu record);
}