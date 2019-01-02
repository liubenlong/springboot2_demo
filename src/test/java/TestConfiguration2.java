import com.example.Application;
import com.example.pojo.Foo;
import config.Config1;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
@Slf4j
@Import(Config1.class)
public class TestConfiguration2 {
    @Autowired
    private Foo foo;

    @Test
    public void testPlusCount() {
        log.info("TestConfiguration2");
        Assert.assertEquals(foo.getName(), "from config1");
    }
}