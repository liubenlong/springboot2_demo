import com.example.Application;
import com.example.pojo.Foo;
import config.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
@Slf4j
@Import(TestConfig.class)
public class TestConfigurationTest extends AbstractTestNGSpringContextTests {

    @Qualifier("foo")
    @Autowired
    private Foo foo;

    @Qualifier("foo2")
    @Autowired
    private Foo foo2;

    @Test
    public void testPlusCount() throws Exception {
        assertEquals(foo.getName(), "from test config");
        assertEquals(foo2.getName(), "from test config2");
    }


}
