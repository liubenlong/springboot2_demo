package com.example.service;

import com.example.mapper.master.StuMapper;
import com.example.mapper.second.CatMapper;
import com.example.mapper.second.StuSecondMapper;
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
    private StuSecondMapper stuSecondMapper;
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
     * transactionManager指定哪个数据源使用事务，不指定默认则默认数据源使用事务
     * @param str
     */
    @Transactional(rollbackFor = Exception.class, transactionManager = "secondTransactionManager")
    public void modify4multipleds(String str) {
        Stu stu = stuMapper.selectByPrimaryKey(2);
        stu.setAddress(str);
        int i = stuMapper.updateByPrimaryKey(stu);

        Cat cat = catMapper.selectByPrimaryKey(2);
        cat.setColor(str);
        int i1 = catMapper.updateByPrimaryKey(cat);

        int a = 1/0;//模拟异常

        stu = stuMapper.selectByPrimaryKey(2);
        stu.setAddress(str + "__2");
        i = stuMapper.updateByPrimaryKey(stu);
        log.info(i + "");

        cat = catMapper.selectByPrimaryKey(2);
        cat.setColor(str + "__2");
        i1 = catMapper.updateByPrimaryKey(cat);

    }


}
