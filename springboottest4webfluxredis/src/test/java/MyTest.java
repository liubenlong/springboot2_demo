import com.example.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
@Slf4j
public class MyTest {

    @Autowired
    private ReactiveRedisTemplate<Object, Object> reactiveRedisTemplate;


    @Test
    public void test1() throws InterruptedException {
        reactiveRedisTemplate.opsForValue().set("a", "a1");
        Mono<Object> a = reactiveRedisTemplate.opsForValue().get("a");
        a.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(2);
    }
}
