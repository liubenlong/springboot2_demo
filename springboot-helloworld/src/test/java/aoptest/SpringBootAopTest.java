package aoptest;

import com.example.Application;
import com.example.aoptest.aspect.HelloAspect;
import com.example.aoptest.config.AopConfig;
import com.example.service.HelloService;
import com.example.service.HelloServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.util.AopTestUtils;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.*;

/**
 * AOP测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)//开启Mockito的支持
@Slf4j
public class SpringBootAopTest extends AbstractTestNGSpringContextTests {

    //声明一个被Mockito.spy过的Bean
    @SpyBean
    private HelloAspect helloAspect;

    @Autowired
    private HelloService helloService;

    @Test
    public void testFooService() {
        //判断helloService对象是不是HelloServiceImpl
        assertNotEquals(helloService.getClass(), HelloServiceImpl.class);

        //接下来通过AopUtils、AopProxyUtils、AopTestUtils来判断helloService是否是代理的对象
        assertTrue(AopUtils.isAopProxy(helloService));
        assertTrue(AopUtils.isCglibProxy(helloService));

        assertEquals(AopProxyUtils.ultimateTargetClass(helloService), HelloServiceImpl.class);

        assertEquals(AopTestUtils.getTargetObject(helloService).getClass(), HelloServiceImpl.class);
        assertEquals(AopTestUtils.getUltimateTargetObject(helloService).getClass(), HelloServiceImpl.class);

        /**
         * 但是证明HelloServiceImpl Bean被代理并不意味着HelloAspect生效了（假设此时有多个@Aspect），
         * 那么我们还需要验证HelloServiceImpl.getVal的行为。
         * 这里调用两次:
         */
        assertEquals(helloService.getVal(), "aopResult");
        assertEquals(helloService.getVal(), "aopResult");

        //通过MockitoTestExecutionListener来监听是否是调用了两次helloService.getVal()方法
        //注意这一行代码测试的是helloAspect的行为，而不是helloService的行为
        verify(helloAspect, times(2)).changeGetVal(any());
    }

}
