import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {

    @Test
    public void test() throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        AtomicLong aLong = new AtomicLong();
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        for(int i = 0; i < 1000000;i++){
            threadPool.execute(() -> {
                longAdder.increment();
                aLong.incrementAndGet();
            });
        }

        TimeUnit.SECONDS.sleep(2);//等待线城池执行完成

        System.out.println(longAdder.longValue());
        System.out.println(aLong.get());
    }

}
