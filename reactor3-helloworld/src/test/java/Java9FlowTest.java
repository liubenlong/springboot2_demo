import java.util.Arrays;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class Java9FlowTest {


    public static class MySubscriber<T> implements Flow.Subscriber<T> {
        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1); //这里要使用Long.MAX_VALUE就会被认为获取无穷的数据。
        }

        @Override
        public void onNext(T item) {
            System.out.println("Got : " + item);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable t) {
            t.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.println("Done");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        //注册订阅者
        MySubscriber<Integer> subscriber = new MySubscriber<>();
        publisher.subscribe(subscriber);

        //发布信息
        for(int i = 0 ; i < 10 ; i ++){
            publisher.submit(i);
            TimeUnit.SECONDS.sleep(1);
        }
        publisher.close();

        subscriber.wait();
    }

}
