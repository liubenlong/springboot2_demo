package config;

import com.example.pojo.Foo;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public Foo foo() {
        return new Foo("from test config");
    }

    @Bean
    public Foo foo2() {
        return new Foo("from test config2");
    }

}