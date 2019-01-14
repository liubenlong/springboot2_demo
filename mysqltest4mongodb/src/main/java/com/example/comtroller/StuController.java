package com.example.comtroller;

import com.example.pojo.Stu;
import com.example.repository.StuRepository;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class StuController {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StuRepository stuRepository;

    /**
     * 插入并查询
     * @return
     */
    @GetMapping("/findAll")
    public List<Stu> findAll() {
        Stu stu = mongoTemplate.save(Stu.builder().age(10).name("李四").build());
        log.info("stu={}", stu);
        return mongoTemplate.findAll(Stu.class);
    }


    /**
     * 修改
     * @param id
     * @param age
     */
    @GetMapping("/update")
    public void update(String id, String age) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set("age", age);
        //更新查询返回结果集的第一条
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Stu.class);
        log.info("MatchedCount={}, UpsertedId={}", updateResult.getMatchedCount(), updateResult.getUpsertedId());
    }


    /**
     * 测试MongoRepository
     */
    @GetMapping("/test1")
    public void test1() {
        //insert
        Stu user = stuRepository.save(Stu.builder().age(12).name("张三").build());
        log.info("创建用户成功 : [{}]", user);

        //query
        List<Stu> all = stuRepository.findAll();
        log.info("查询用户 : [{}]", Arrays.toString(all.toArray()));
    }



}
