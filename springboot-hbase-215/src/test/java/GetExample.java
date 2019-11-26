import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GetExample {
    OkHttpClient client = new OkHttpClient();

    //同步请求
    String runSync(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    void run(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        //异步请求
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String string = response.body().string();
                System.out.println(string);
            }
        });
    }

    @Test
    public void testSync() throws IOException {
        GetExample example = new GetExample();
        String response = example.runSync("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }

    @Test
    public void test() throws InterruptedException {
        GetExample example = new GetExample();
        example.run("https://raw.github.com/square/okhttp/master/README.md");

        TimeUnit.SECONDS.sleep(5);
    }
}