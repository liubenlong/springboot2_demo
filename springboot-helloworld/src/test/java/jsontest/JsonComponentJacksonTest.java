package jsontest;

import com.example.pojo.Stu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试自定义的@JsonComponent
 */
@ContextConfiguration(classes = {JsonComponentJacksonTest.class, FooJsonComponent.class})
@JsonTest
public class JsonComponentJacksonTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private JacksonTester<Stu> json;

    @Test
    public void testSerialize() throws Exception {
        Stu details = new Stu("zhangsan", 12);
        assertThat(this.json.write(details).getJson()).isEqualTo("\"name=zhangsan,age=12\"");
    }

    @Test
    public void testDeserialize() throws Exception {
        String content = "\"name=zhangsan,age=13\"";
        Stu actual = this.json.parseObject(content);
        assertThat(actual).isEqualTo(new Stu("zhangsan", 13));
        assertThat(actual.getName()).isEqualTo("zhangsan");
        assertThat(actual.getAge()).isEqualTo(13);

    }

}
