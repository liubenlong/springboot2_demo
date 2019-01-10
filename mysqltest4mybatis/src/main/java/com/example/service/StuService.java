package com.example.service;

import com.example.mapper.StuMapper;
import com.example.model.Stu;
import com.example.model.StuExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class StuService {

    @Autowired
    private StuMapper stuMapper;

    public List<Stu> findAll(){
        return stuMapper.selectByExample(null);
    }

    public List<Stu> selectByExample(){
        StuExample stuExample = new StuExample();
        stuExample.createCriteria().andAgeEqualTo(12);//条件查询
        stuExample.setOrderByClause("name");//排序
        return stuMapper.selectByExample(stuExample);
    }

    /**
     * PageHelper分页
     * @return
     */
    public PageInfo<Stu> selectByExampleWithPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        StuExample stuExample = new StuExample();
        stuExample.createCriteria().andAgeEqualTo(12);//条件查询
        stuExample.setOrderByClause("name");//排序
        List<Stu> stus = stuMapper.selectByExample(stuExample);
        PageInfo<Stu> pageInfo = new PageInfo<>(stus);
        return pageInfo;
    }



    public int modify(int id , String address){
        Stu stu = stuMapper.selectByPrimaryKey(id);
        stu.setAddress(address);
        return stuMapper.updateByPrimaryKeySelective(stu);
    }


    public int save(){
        Stu stu = new Stu();
        stu.setName("周杰伦");
        stu.setAge(30);
        return stuMapper.insert(stu);
    }



}
