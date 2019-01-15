//import com.example.Application;
//import com.example.pojo.Foo;
//import config.Config;
//import config.TestConfig;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * https://segmentfault.com/a/1190000010854811
// * 文章中讲可以覆盖已有的bean，但是我这里测试失败，不知道是不是版本的原因
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {Application.class, TestConfig.class, Config.class})// 指定启动类
//@Slf4j
////@Import({TestConfig.class, Config.class})
////@ComponentScan("config")
//public class TestConfiguration3 {
//
//    @Autowired
//    private Foo foo;
//
//    @Autowired
//    private Foo foo2;
//
//    @Test
//    public void testPlusCount() throws Exception {
//        log.info("TestConfiguration3");
//        Assert.assertEquals(foo.getName(), "from test config");
//        Assert.assertEquals(foo2.getName(), "from test config2");
//
//    }
//
//}
