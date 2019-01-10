package mockcontroller;

import com.example.Application;
import com.example.controller.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 根据官方文档，测试失败？？
 * https://www.jianshu.com/p/a9cb76c54c66  这里有解决方法，但是我没有验证，不如使用 MockMvcExampleTests 的写法
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebMvcTest
public class MyControllerTests extends AbstractTestNGSpringContextTests{

    @Autowired
    private MockMvc mvc;

    @Test
    public void testExample() throws Exception {
        this.mvc.perform(get("/hello")).andExpect(status().isOk())
                .andExpect(content().string("Welcome to springboot2 world ~"));
    }

}