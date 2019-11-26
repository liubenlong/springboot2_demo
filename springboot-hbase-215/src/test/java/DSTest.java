import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class DSTest {

    @Test
    public void testOkhttp2222Get() throws IOException, InterruptedException {
        testOkhttpGet();
        delete();
        testOkhttpGet();

        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    public void testOkhttpGet() throws InterruptedException {
        String url = "http://127.0.0.1:9080/hbase/get?ticket=fbaa3a2a17afba65170f6939ac726af4daece82997c4fbd6c41cf01e1239ca17&tableName=TEST:QA_MIDDLEWARE_USER_HBASE_REP&rowKey=a";
        okhttp3.Request request = new okhttp3.Request.Builder().url(url).get().build();
        OkHttpClient okHttpClient = new OkHttpClient();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String string = response.body().string();//string不能调用两次 被调用一次就关闭了，这里调用两次会报异常
                System.out.println(string);
            }
        });

        TimeUnit.SECONDS.sleep(1);

    }

    @Test
    public void testOkhttpGetphoenix() throws InterruptedException {
        String url = "http://127.0.0.1:9080/select?ticket=fbaa3a2a17afba65170f6939ac726af4daece82997c4fbd6c41cf01e1239ca17&sql=select+*+from+%22model%3asougo_phone_lib%22+where+pk+in+(%27079182125103%23%27%2c%2704336249505%23%27%2c%2713844754147%23%27%2c%2704336668668%23%27%2c%2717113271728%23%27%2c%27043395146068%23%27%2c%2715043323448%23%27%2c%27043395588%23%27%2c%274000271262%23%27%2c%2713043372760%23%27%2c%2713944338839%23%27%2c%276782636%23%27%2c%2707508892080%23%27%2c%2713545691760%23%27%2c%2704336748777%23%27%2c%2718643366111%23%27%2c%2713596558455%23%27%2c%2717094327351%23%27%2c%2718843348901%23%27%2c%276267277%23%27%2c%2715981335551%23%27%2c%2704336259505%23%27%2c%2718243347222%23%27%2c%2713596561787%23%27%2c%2704338959273%23%27%2c%2713944725958%23%27%2c%2704338959558%23%27%2c%2713147777033%23%27%2c%2713596564473%23%27%2c%2718504265261%23%27%2c%2704338981255%23%27%2c%27043110085%23%27%2c%2715834788832%23%27%2c%2718844719698%23%27%2c%2713654482203%23%27%2c%2713331489332%23%27%2c%2704336295300%23%27%2c%2713596546460%23%27%2c%276249505%23%27%2c%2717614332939%23%27%2c%27043310086%23%27%2c%2718744358880%23%27%2c%276295300%23%27%2c%2704557914111%23%27%2c%2715981359885%23%27%2c%2713596521751%23%27%2c%2717094328483%23%27%2c%2713089447876%23%27%2c%2717614334991%23%27%2c%2713944370490%23%27%2c%2713848962654%23%27%2c%2717094328237%23%27%2c%27043310105757%23%27%2c%2713234338082%23%27%2c%2719969653669%23%27%2c%2715981328345%23%27%2c%2702151205358%23%27%2c%2704336505551%23%27%2c%2704334006595555%23%27%2c%2715643398955%23%27%2c%2713844758711%23%27%2c%2713009097499%23%27%2c%2715973338252%23%27%2c%2713039348742%23%27%2c%2704336391185%23%27%2c%2715643922287%23%27%2c%2717614335991%23%27%2c%2718626948666%23%27%2c%2713844756598%23%27%2c%2713654673003%23%27%2c%2701083430128%23%27%2c%2713943351190%23%27%2c%2718743323990%23%27%2c%27037158657577%23%27%2c%2713894370703%23%27%2c%2704336252228%23%27%2c%2712599%23%27%2c%2717612267444%23%27%2c%27057588352254%23%27%2c%2715043300804%23%27%2c%2718922875876%23%27%2c%2717094327870%23%27%2c%2715143368444%23%27%2c%274009699559%23%27%2c%2718644807420%23%27%2c%2719188355847%23%27%2c%2715584616468%23%27%2c%2713624439332%23%27%2c%2704555949494%23%27%2c%2715143307119%23%27%2c%2717170376444%23%27%2c%2715124408867%23%27%2c%2715834723977%23%27%2c%2713009097259%23%27%2c%2717094329026%23%27%2c%2718504335485%23%27%2c%2701086396437%23%27%2c%2715526794128%23%27%2c%2717094327291%23%27%2c%2717094329246%23%27%2c%2718844317878%23%27%2c%2715584669018%23%27%2c%2717707606244%23%27%2c%2713843309398%23%27%2c%2717094328050%23%27%2c%2717094329051%23%27%2c%2717643227885%23%27%2c%27043395146052%23%27%2c%2704336782636%23%27%2c%2718143638887%23%27%2c%270433125909888123%23%27%2c%2713843325731%23%27%2c%2717643341587%23%27%2c%276748777%23%27%2c%27057588352265%23%27%2c%27043395146067%23%27%2c%2704336532619%23%27%2c%2715567624864%23%27%2c%2713630654942%23%27%2c%2718843321126%23%27%2c%2715043326287%23%27%2c%2715844389117%23%27%2c%2715981334522%23%27%2c%2718844388558%23%27%2c%2713039348420%23%27%2c%2713958961064%23%27%2c%2715948090177%23%27%2c%2713394331226%23%27%2c%2718343333782%23%27%2c%2715945983853%23%27%2c%2715585581385%23%27%2c%2717094328650%23%27%2c%2713645239195%23%27%2c%2713894375813%23%27%2c%2715834729979%23%27%2c%2704336267277%23%27%2c%2713736139953%23%27%2c%2713654486573%23%27%2c%2715043236471%23%27)";
//        String url = "http://127.0.0.1:9080/select?ticket=fbaa3a2a17afba65170f6939ac726af4daece82997c4fbd6c41cf01e1239ca17&sql=select+*+from+zeus.cell_call+limit+2";

        okhttp3.Request request = new okhttp3.Request.Builder().url(url).get().build();
        OkHttpClient okHttpClient = new OkHttpClient();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String string = response.body().string();//string不能调用两次 被调用一次就关闭了，这里调用两次会报异常
                System.out.println(string);
            }
        });

        TimeUnit.SECONDS.sleep(3);

    }


    @Test
    public void delete() throws IOException {
        String url = "http://127.0.0.1:9080/hbase/deletes";

        OkHttpClient httpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        String post = "{\t\"ticket\":\"fbaa3a2a17afba65170f6939ac726af4daece82997c4fbd6c41cf01e1239ca17\",\t\"tableName\":\"TEST:QA_MIDDLEWARE_USER_HBASE_REP\",\t\"rowKeys\":[\"a\"]}";
        RequestBody requestBody = RequestBody.create(mediaType, post);
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        Response response = httpClient.newCall(request).execute();
        System.out.println(response.body().string());
    }




    @Test
    public void update() throws IOException {
        String url = "http://172.16.83.114:9080/phoenix/upsert";

        OkHttpClient httpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        String post = "{\t\"ticket\": \"fbaa3a2a17afba65170f6939ac726af4daece82997c4fbd6c41cf01e1239ca17\",\t\"data\": \"upsert into atm.user_tag (pk,need_repayment) values ('13867863019','true')\"}";
        RequestBody requestBody = RequestBody.create(mediaType, post);
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        Response response = httpClient.newCall(request).execute();
        System.out.println(response.body().string());
    }


    @Test
    public void hbaseputs() throws IOException {
        String url = "http://127.0.0.1:9080/hbase/puts";

        OkHttpClient httpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        String post = "{\"ticket\": \"fbaa3a2a17afba65170f6939ac726af4daece82997c4fbd6c41cf01e1239ca17\",\"tableName\": \"GROWTH.REALTIME_PROFILE\",\"rows\": [{\"rowKey\": \"9#hist@445#20190925060000#29#192728715\",\"data\": {\"0\": {\"SR#704#0\": \"{\\\"Delivered\\\":{\\\"succed\\\":true,\\\"time\\\":1569369806721}}\"}}}]}";
        RequestBody requestBody = RequestBody.create(mediaType, post);
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        Response response = httpClient.newCall(request).execute();
        System.out.println(response.body().string());
    }


    @Test
    public void aaaaaaaaaa() throws IOException {
//        1569376752291
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String format = simpleDateFormat.format(new Date(1569376752291L));
        System.out.println(format);

        System.out.println("GROWTH.OVERTIME_PROFILE".replace(".",":"));


    }


}