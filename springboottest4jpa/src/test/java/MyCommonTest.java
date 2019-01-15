import com.example.Application;
import com.example.service.StuService;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * 操作数据库测试
 * 目前还不知道如何回滚数据，使用@rollback没有成功？？
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
@Slf4j
public class MyCommonTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private StuService stuService;

    @Test
    public void test2() {
        log.info("allstus={}", stuService.findAll());
        TestCase.assertEquals(1, 1);
    }
}