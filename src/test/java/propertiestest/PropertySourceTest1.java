package propertiestest;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static java.util.stream.Collectors.toList;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@ContextConfiguration(classes = PropertySourceConfig.class)
@TestPropertySource(
        properties = {"lastName=abc", "bar=uvw"}
)
public class PropertySourceTest1 implements EnvironmentAware {

    private Environment environment;

    @Test
    public void test1() {
        TestCase.assertEquals(environment.getProperty("lastName"), "abc");
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        Map<String, Object> systemEnvironment = ((ConfigurableEnvironment) environment).getSystemEnvironment();
        System.out.println("=== System Environment ===");
        System.out.println(getMapString(systemEnvironment));
        System.out.println();

        System.out.println("=== Java System Properties ===");
        Map<String, Object> systemProperties = ((ConfigurableEnvironment) environment).getSystemProperties();
        System.out.println(getMapString(systemProperties));
    }

    private String getMapString(Map<String, Object> map) {
        return String.join("\n",
                map.keySet().stream().map(k -> k + "=" + map.get(k)).collect(toList())
        );
    }
}