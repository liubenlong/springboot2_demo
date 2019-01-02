import com.example.Application;
import com.example.pojo.Person;
import com.example.service.HelloService;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
@Slf4j
public class MyTests {
    @Autowired
    private Person person;
    @Autowired
    private HelloService helloService;

    /**
     * 使用断言
     */
    @Test
    public void test2() {
        log.info("test hello 2");
        TestCase.assertEquals(1, 1);
    }


    /**
     * 测试注入
     */
    @Test
    public void test3() {
        log.info("person={}", person);
        log.info("helloService.getVal()={}", helloService.getVal());
    }

    @Before
    public void testBefore() {
        System.out.println("before");
    }

    @After
    public void testAfter() {
        System.out.println("after");
    }
}