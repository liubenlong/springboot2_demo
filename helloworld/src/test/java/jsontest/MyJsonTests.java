package jsontest;

import com.example.Application;
import com.example.pojo.Stu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//这里不能使用@SpringBootTest否则报错：Configuration error: found multiple declarations of @BootstrapWith for test class [MyJsonTests]
@ContextConfiguration(classes = {Application.class})
@JsonTest
public class MyJsonTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private JacksonTester<Stu> json;

    @Test
    public void testSerialize() throws Exception {
        Stu details = new Stu("马云", 51);
        // Assert against a `.json` file in the same package as the test
        assertThat(this.json.write(details)).isEqualToJson("expected.json");
        // 或者使用基于JSON path的校验
        assertThat(this.json.write(details)).hasJsonPathStringValue("@.name");
        assertThat(this.json.write(details)).extractingJsonPathStringValue("@.name").isEqualTo("马云");
    }

    @Test
    public void testDeserialize() throws Exception {
        String content = "{\"name\":\"2\",\"age\":\"11\"}";
        assertThat(this.json.parse(content)).isEqualTo(new Stu("2", 11));
        assertThat(this.json.parseObject(content).getName()).isEqualTo("2");
    }

}