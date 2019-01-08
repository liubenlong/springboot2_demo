package propertiestest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//支持properties和yml
//@PropertySource("classpath:property-source.properties")
@PropertySource("classpath:property-source.yml")
public class PropertySourceConfig {
}
