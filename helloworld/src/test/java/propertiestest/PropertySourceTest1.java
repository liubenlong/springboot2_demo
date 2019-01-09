package propertiestest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * 对@PropertySource加载的属性进行mock测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@ContextConfiguration(classes = PropertySourceConfig.class) //加载属性配置
@TestPropertySource( // 对属性进行设置
        properties = {"bar=uvw"},
        locations = "classpath:test-property-source.yml"
)
public class PropertySourceTest1 implements EnvironmentAware {

    private Environment environment;

    @Value("${testp}")
    String testp;

    @Test
    public void test1() {
        Assert.assertEquals(environment.getProperty("lastName"), "abc");
        Assert.assertEquals(testp, "123456789");
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