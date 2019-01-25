import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MyTest {

    @Test
    public void test1() {
        Flux.just(1, 2, 3, 4, 5, 6).subscribe(System.out::print);
        System.out.println();
        Flux.just(1, 2, 3, 4, 5, 6).subscribe(System.out::print, System.out::println, () -> System.out.println("Completed"));
        Mono.just(100).subscribe(System.out::println);
    }

    @Test
    public void test2() {
        Mono.error(new Exception("my error")).subscribe(
                System.out::print,
                System.out::println,
                () -> System.out.println("Completed"));
    }

    @Test
    public void test3() {
        Flux.fromIterable(Arrays.asList(1, 2, 3, 4, 5, 6))
                .map(i -> i * i)
                .subscribe(System.out::println);
    }

    @Test
    public void test4() {
        Flux.fromIterable(Arrays.asList(1, 2, 3, 4, 5, 6))
                .filter(i -> i % 2 == 0)//过滤
                .map(i -> i * i)//与map reduce中的map类型，逐个元素计算
                .subscribe(System.out::println);
    }

    /**
     * flatMap操作可以将每个数据元素转换/映射为一个流，然后将这些流合并为一个大的数据流。
     * 流的合并是异步无序的
     *
     * @throws InterruptedException
     */
    @Test
    public void test5() throws InterruptedException {
        Flux.just("flux", "mono")
                .flatMap(s -> Flux.fromArray(s.split("\\s*"))//java中的future可以用这个替换。比如多个请求合并，串行改并行
                        .delayElements(Duration.ofMillis(100)))
                .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(1);//主线程阻塞1秒
    }


    /**
     * 使用StepVerifier 进行测试
     *
     * @return
     */
    @Test
    public void testViaStepVerifier() {
        StepVerifier.create(Flux.just(1, 2, 3, 4, 5, 6))
                .expectNext(1, 2, 3, 4, 5, 6)//下一个期望的数据元素
                .expectComplete()//测试下一个元素是否为完成信号
                .verify();
        StepVerifier.create(Mono.error(new Exception("some error")))
                .expectErrorMessage("some error")//校验下一个元素是否为错误信号
                .verify();
    }

    /**
     * Flux.interval  无限流
     *
     * @return
     */
    @Test
    public void test7() throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .map(aLong -> "a" + aLong) // 类似于map reduce 中的map，对flux发出的消息逐个处理
                .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(10);
    }


    /**
     * zip: 将多个流一对一的合并起来
     *
     * @return
     */
    @Test
    public void testSimpleOperators() throws InterruptedException {
        String desc = "Reactor is a fully non-blocking reactive programming foundation for the JVM, with efficient demand management (in the form of managing \"backpressure\")";
        CountDownLatch countDownLatch = new CountDownLatch(1);  // 使用这个来控制程序结束，替换之前的sleep操作
        Flux.zip(
                Flux.fromArray(desc.split("\\s+")),
                Flux.interval(Duration.ofMillis(1000)))  // 使用Flux.interval声明一个每200ms发出一个元素的long数据流；因为zip操作是一对一的，故而将其与字符串流zip之后，字符串流也将具有同样的速度；
                .subscribe(t -> System.out.println(t.getT1() + "  " + t.getT2()), null, countDownLatch::countDown);    // zip之后的流中元素类型为Tuple2，使用getT1方法拿到字符串流的元素
        countDownLatch.await();     // 5
    }


    /**
     * Schedulers线程池
     *
     * @return
     */
    private String getStringSync() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello, Reactor!";
    }

    @Test
    public void testSyncToAsync() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Mono.fromCallable(() -> getStringSync())    // 声明一个异步回调的mono
                .subscribeOn(Schedulers.elastic())  // 将任务提交到内置的弹性线程池中执行
                .subscribe(System.out::println, null, countDownLatch::countDown);
        countDownLatch.await();
    }


    /**
     * 切换调度器的操作符
     * <p>
     * publishOn会影响链中其后的操作符，比如第一个publishOn调整调度器为elastic，则filter的处理操作是在弹性线程池中执行的；
     * 同理，flatMap是执行在固定大小的parallel线程池中的；
     * subscribeOn无论出现在什么位置，都只影响源头的执行环境，也就是range方法是执行在单线程中的，
     * 直至被第一个publishOn切换调度器之前，所以range后的map也在单线程中执行。
     *
     * @throws InterruptedException
     */
    @Test
    public void test6() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Flux.range(1, 1000)
                .map(i -> i * i)
                .publishOn(Schedulers.elastic()).filter(i -> i % 2 == 0)
                .publishOn(Schedulers.parallel()).flatMap(i -> Flux.just(i).delayElements(Duration.ofMillis(1000)))
                .subscribeOn(Schedulers.single())
                .subscribe(System.out::println, null, countDownLatch::countDown);
        countDownLatch.await();
    }


    /**
     * 异常处理
     * 错误和完成两个都是终止符，不可同时存在
     */
    @Test
    public void testErrorHandling() {
        Flux.range(1, 6)
                .map(i -> 10 / (i - 3)) // 1
                .map(i -> i * i)
                .subscribe(System.out::println, System.err::println, () -> System.err.println("ok"));
    }

    /**
     * onErrorReturn方法能够在收到错误信号的时候提供一个缺省值
     */
    @Test
    public void testErrorHandling1() {
        Flux.range(1, 6)
                .map(i -> 10 / (i - 3)) // 1
                .onErrorReturn(0)
                .map(i -> i * i)
                .subscribe(System.out::println, System.err::println, () -> System.err.println("ok"));
    }

    /**
     * onErrorResume:在收到错误信号的时候提供一个新的数据流。
     * 可用于fallback处理， 类似于hystrix的fallback。或者类似于读取不到缓存则读取DB
     */
    @Test
    public void testErrorHandling2() {
        Flux.range(1, 6)
                .map(i -> 10 / (i - 3))
                .onErrorResume(e -> Mono.just(new Random().nextInt(6))) // 提供新的数据流
                .map(i -> i * i)
                .subscribe(System.out::println, System.err::println, () -> System.err.println("ok"));
    }


    public Flux callExternalService(String str) {
        return Flux.range(1, 6)
                .map(i -> 10 / (i - 3)) // 1
                .map(i -> i * i);
    }
    /**
     * 捕获，并再包装为某一个业务相关的异常，然后再抛出业务异常
     * @throws InterruptedException
     */
    @Test
    public void testErrorHandling3() throws InterruptedException {
        Flux.just("a")
                .flatMap(k -> callExternalService(k))
                // 重新包装
                .onErrorMap(original -> new Exception("business  error"))
                .subscribe(System.out::println, System.err::println, () -> System.err.println("ok"));
        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 捕获，记录错误日志，然后继续抛出
     *
     * doOnXxx是只读的，对数据流不会造成影响
     */
    @Test
    public void testErrorHandling4() {
        Flux.range(1, 6)
                .map(i -> 10 / (i - 3))
                .doOnError(throwable -> log.error(throwable.getMessage()))
                .map(i -> i * i)
                .subscribe(System.out::println, System.err::println, () -> System.err.println("ok"));
    }


    /**
     * doFinally 及 take
     */
    @Test
    public void testErrorHandling5() {
        Flux.range(1, 6)
                .map(i -> 10 / (i - 3)) // 1
                .map(i -> i * i)
                .doFinally(type -> {//doFinally在序列终止（无论是 onComplete、onError还是取消）的时候被执行
                    if (type == SignalType.CANCEL)  // 2
                        log.info("SignalType.CANCEL");
                    else if(type == SignalType.ON_ERROR)
                        log.info("SignalType.ON_ERROR");
                })
                .take(1)//能够在发出N个元素后取消流。
                .subscribe(System.out::println, System.err::println, () -> System.err.println("ok"));
    }


    /**
     * 背压测试：
     * BaseSubscriber 是顶层的抽象类，上面介绍的所有subscribe方法最终都会转化为 subscribe(BaseSubscriber)执行
     * BaseSubscriber 最少要重写hookOnSubscribe和hookOnNext
     */
    @Test
    public void testBackpressure() {
        Flux.range(1, 6)
                .doOnRequest(n -> System.out.println("Request " + n + " values..."))
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        System.out.println("Subscribed and make a request...");
                        request(1); //发起第一个请求
                    }

                    @Override
                    protected void hookOnNext(Integer value) {  // 逐个处理剩余请求
                        try {
                            TimeUnit.SECONDS.sleep(1);  // 模拟耗时操作
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Get value [" + value + "]");
                        request(1); // 随着接收到新的值，我们继续以每次请求一个元素的节奏从源头请求值。  这是背压核心
                    }
                });
    }
}

