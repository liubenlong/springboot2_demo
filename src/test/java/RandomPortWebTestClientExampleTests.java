import com.example.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * 测试基于webflux异步非阻塞的的运行的controller服务
 * 这个需要将spring-boot-starter-web替换为spring-boot-starter-webflux
 */
@RunWith(SpringRunner.class)
//使用随机端口
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class RandomPortWebTestClientExampleTests {

    /**
     *WebTestClient 是用于测试web服务器的非阻塞的响应式客户端
     */
	@Autowired
	private WebTestClient webClient;

	@Test
	public void exampleTest() {
		this.webClient.get().uri("/hello").exchange().expectStatus().isOk()
				.expectBody(String.class).isEqualTo("Welcome to springboot2 world ~");
	}

}