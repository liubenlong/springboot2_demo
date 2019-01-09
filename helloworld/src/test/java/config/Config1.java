package config;

import com.example.pojo.Foo;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class Config1 {

    @Bean
    public Foo foo() {
        return new Foo("from config1");
    }
}