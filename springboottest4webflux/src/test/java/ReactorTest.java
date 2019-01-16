import com.example.pojo.Stu;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

/**
 * WebClient实现响应式客户端
 */
public class ReactorTest {

    @Test
    public void webClientTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        WebClient webClient = WebClient.create("http://localhost:8080");   // 1
        Mono<String> resp = webClient
                .get().uri("/hello") // 组装http请求
                .retrieve() // 执行HTTP请求并获取response
                .bodyToMono(String.class);  // 将返回值解析为string的相应流
        resp.subscribe(System.out::println, null, countDownLatch::countDown);    // 消费
        countDownLatch.await();
    }

    @Test
    public void webClientTest1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build(); // 1
        webClient
                .get().uri("/findAll")
                .accept(MediaType.APPLICATION_STREAM_JSON) // 设置请求header：Content-Type: application/stream+json；
                .retrieve()
                .bodyToFlux(Stu.class)
                .subscribe(System.out::println, null, countDownLatch::countDown);    // 消费
        countDownLatch.await();
    }

    /**
     * 上下两种写法效果相同
     *
     * @throws InterruptedException
     */
    @Test
    public void webClientTest2() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build(); // 1
        webClient
                .get().uri("/findAll")
                .accept(MediaType.APPLICATION_STREAM_JSON) // 设置请求header：Content-Type: application/stream+json；
                .exchange() // 执行HTTP请求并返回带有响应状态和标头的ClientResponse。  一般情况使用retrieve即可
                .flatMapMany(response -> response.bodyToFlux(Stu.class))   //将ClientResponse转为Flux
//                .doOnNext(System.out::println)  // 监控，并没有真正消费流
                .subscribe(System.out::println, null, countDownLatch::countDown);
        countDownLatch.await();
    }

    /**
     * 测试服务器端推送
     *
     * @throws InterruptedException
     */
    @Test
    public void webClientTest3() throws InterruptedException {
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient
                .get().uri("/getTimePerSecond")
                .accept(MediaType.TEXT_EVENT_STREAM)    // 设置header: 事件流
                .retrieve()
                .bodyToFlux(String.class)
                .log()  // 观察所有Reactive Streams信号记录日志。用log()代替doOnNext(System.out::println)来查看每个元素；
                .take(10)   // 由于/times是一个无限流，这里取前10个，会导致流被取消(会发送一个cancel信号)；
                .blockLast(); //在收到最后一个元素前会阻塞，响应式业务场景中慎用。
    }

    /**
     * 测试事件流flux作为入参
     *
     * @throws InterruptedException
     */
    @Test
    public void webClientTest4() throws InterruptedException {
        Flux<Stu> stuFlux = Flux.interval(Duration.ofSeconds(1))
                .map(l -> Stu.builder().age(Integer.parseInt(l + "")).name("name_" + l).address("address_" + l).build()).take(5); // 1
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient
                .post().uri("/loadStus")
                .contentType(MediaType.APPLICATION_STREAM_JSON) // 指定数据流
                .body(stuFlux, Stu.class) // 设置请求body体的数据
                .retrieve()
                .bodyToMono(Void.class)
                .block();//
    }

}
