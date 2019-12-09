package 源码;

import java.util.concurrent.LinkedBlockingQueue;

public class Test {
    @org.junit.Test
    public void test1() throws InterruptedException {
        LinkedBlockingQueue queue = new LinkedBlockingQueue();

        queue.put(1);

        queue.take();
    }
}
