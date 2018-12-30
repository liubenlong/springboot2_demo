import com.example.Application;
import com.example.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * 测试bean结果的mock
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
public class MockBeanTest {
    @MockBean  //这里使用 @SpyBean 是同样效果
    private HelloService helloService;

    @Test
    public void exampleTest() {
        //这句的意思是当调用helloService的getRemoteVal方法时，返回mock的结果："远程调用结果"
        given(this.helloService.getRemoteVal()).willReturn("远程调用结果");

        //进行调用测试
        String reverse = helloService.getRemoteVal();
        assertThat(reverse).isEqualTo("远程调用结果");
    }

}