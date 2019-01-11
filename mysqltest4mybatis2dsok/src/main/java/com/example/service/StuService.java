package com.example.service;

import com.example.mapper.CatMapper;
import com.example.mapper.StuMapper;
import com.example.model.Cat;
import com.example.model.Stu;
import com.example.model.StuExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
public class StuService {

    @Autowired
    private StuMapper stuMapper;
    @Autowired
    private CatMapper catMapper;

    public List<Stu> findAll() {
        return stuMapper.selectByExample(null);
    }

    public List<Stu> selectByExample() {
        StuExample stuExample = new StuExample();
        stuExample.createCriteria().andAgeEqualTo(12);//条件查询
        stuExample.setOrderByClause("name");//排序
        return stuMapper.selectByExample(stuExample);
    }

    /**
     * PageHelper分页
     *
     * @return
     */
    public PageInfo<Stu> selectByExampleWithPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        StuExample stuExample = new StuExample();
        stuExample.createCriteria().andAgeEqualTo(12);//条件查询
        stuExample.setOrderByClause("name");//排序
        List<Stu> stus = stuMapper.selectByExample(stuExample);
        PageInfo<Stu> pageInfo = new PageInfo<>(stus);
        return pageInfo;
    }


    public int modify(int id, String address) {
        Stu stu = stuMapper.selectByPrimaryKey(id);
        stu.setAddress(address);
        return stuMapper.updateByPrimaryKeySelective(stu);
    }

    public int save() {
        Stu stu = new Stu();
        stu.setName("周杰伦");
        stu.setAge(30);
        return stuMapper.insert(stu);
    }


    /**
     * 测试多数据源事务
     * 使用AbstractRoutingDataSource路由，事务测试失败？？？？？
     *
     * @param str
     */
    @Transactional(rollbackFor = Exception.class)
    public void modify4multipleds(String str) {
        Stu stu = stuMapper.selectByPrimaryKey(5);
        stu.setAddress(str);
        int i = stuMapper.updateByPrimaryKey(stu);
        log.info(i + "");

        Cat cat = catMapper.selectByPrimaryKey(1);
        cat.setColor(str);
        int i1 = catMapper.updateByPrimaryKey(cat);

        log.info(i1 + "");
    }


}
