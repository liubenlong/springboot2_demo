package propertiestest;

import com.example.Application;
import com.example.pojo.Person;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})// 指定启动类
@Slf4j
@TestPropertySource(
        properties = {"person.lastName=张飞", "person.age=49"}
)
public class PropertySourceTest {
    @Autowired
    private Person person;

    @Test
    public void test1() {
        log.info(person.getLastName());
        TestCase.assertEquals(person.getLastName(), "张飞");
    }
}