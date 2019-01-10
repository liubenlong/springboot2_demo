package com.example.comtroller;

import com.example.dao.StuRepository;
import com.example.pojo.Stu;
import com.example.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class StuController {
    @Autowired
    private StuRepository stuRepository;
    @Autowired
    private StuService stuService;

    @GetMapping("/findAll")
    public List<Stu> findAll() {
        List<Stu> stus = stuRepository.findAll();

        return stus;
    }

    /**
     * 创建
     *
     * @return
     */
    @GetMapping("/createOne")
    public List<Stu> createOne() {
        Stu stu = Stu.builder().name("李四").age(12).address("西湖").build();
        Stu save = stuRepository.save(stu);

        log.info(save.toString());

        List<Stu> stus = Arrays.asList(Stu.builder().name("李四").age(12).address("西湖").build(),
                Stu.builder().name("王五").age(12).address("南京路").build(),
                Stu.builder().name("交易员韩国").age(12).address("二环").build());
        List<Stu> stus1 = stuRepository.saveAll(stus);

        return stus1;
    }

    @GetMapping("/deleteById")
    public void deleteById() {
        stuRepository.existsById(1L);//判断是否存在
        stuRepository.deleteById(1L);//删除
    }


    /**
     * 根据条件查询
     *
     * @return
     */
    @GetMapping("/findByExample")
    public List<Stu> findByExample() {
        Stu stu = Stu.builder().age(12).build();
        Example<Stu> example = Example.of(stu);

        List<Stu> all = stuRepository.findAll(example);
        return all;
    }

    /**
     * 实现分页查询
     *
     * @return
     */
    @GetMapping("/findWithPage")
    public Page<Stu> findWithPage() {
        Stu stu = Stu.builder().age(12).build();
        Example<Stu> example = Example.of(stu);
        Page<Stu> all = stuRepository.findAll(example, PageRequest.of(1, 5, Sort.by("name")));

        return all;
    }

    /**
     * 使用自定义的查询方法
     * 其实上面的JpaRepository默认的方法已经够用了，这个可不使用。经常用到的方法可以写，减少代码量
     * @return
     */
    @GetMapping("/findByNameAndAge")
    public List<Stu> findByNameAndAge() {
        List<Stu> stus = stuRepository.findByNameAndAge("李四", 12);
        return stus;
    }
    @GetMapping("/findLikeName")
    public List<Stu> findLikeName() {
        List<Stu> stus = stuRepository.findLikeName("李");
        return stus;
    }


    /**
     * 事务测试
     * @return
     */
    @GetMapping("/testTransactional")
    public void testTransactional() {
        stuService.createStu4Transactional();
    }

}