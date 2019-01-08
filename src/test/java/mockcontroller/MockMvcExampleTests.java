package mockcontroller;

import com.example.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
@AutoConfigureMockMvc
@Slf4j
public class MockMvcExampleTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void exampleTest() throws Exception {
        this.mvc.perform(get("/hello")).andExpect(status().isOk())
                .andExpect(content().string("Welcome to springboot2 world ~"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void exampleTest1() throws Exception {
        this.mvc.perform(get("/hello1")).andExpect(status().isOk())
                .andExpect(content().string("aopResult"))
                .andDo(MockMvcResultHandlers.print());
    }
}