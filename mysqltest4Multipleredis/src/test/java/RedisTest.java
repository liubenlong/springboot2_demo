import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.Application;
import com.example.pojo.Stu;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
@Slf4j
public class RedisTest {

    /**
     * 不写默认使用带有@Primary的RedisTemplate
     */
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    @Qualifier("redisTemplate2")
    private RedisTemplate redisTemplate2;

    @Test
    public void deleteVal() {
        redisTemplate.delete("a");
        redisTemplate.delete("b");
        redisTemplate2.delete("a");
        redisTemplate2.delete("b");
    }

    @Test
    public void testRedis() {
        Stu stu = Stu.builder().name("张三").age(20).build();
        redisTemplate.opsForValue().set("a", stu);

        Stu stu1 = Stu.builder().name("李四").age(30).build();
        redisTemplate2.opsForValue().set("a", stu1);

    }

    @Test
    public void testRedis2() {
        Object a = redisTemplate.opsForValue().get("a");
        Stu stu1 = JSON.toJavaObject((JSONObject) a, Stu.class);
        log.info(stu1.toString());

        a = redisTemplate2.opsForValue().get("a");
        stu1 = JSON.toJavaObject((JSONObject) a, Stu.class);
        log.info(stu1.toString());
    }


    @Test
    public void testRedis3() {
        Long d = redisTemplate2.opsForValue().increment("d", 1);
        log.info("d={}", d);
        d = redisTemplate2.opsForValue().increment("d", 1);
        log.info("d={}", d);
        Object d1 = redisTemplate2.opsForValue().get("d");
        log.info("d={}", d1);
    }
}
