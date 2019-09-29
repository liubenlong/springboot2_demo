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
        String url = "http://127.0.0.1:9080/select?ticket=fbaa3a2a17afba65170f6939ac726af4daece82997c4fbd6c41cf01e1239ca17&sql=select+*+from+ZEUS.CREDITCARD_ACCOUNTS+where+pk+in+(select+max(pk)+from+ZEUS.CREDITCARD_ACCOUNTS+where+(+pk+like+%27156321480%23%25%27)+group+by+card_no+limit+1000)+limit+1000";
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
        String url = "http://127.0.0.1:9080/phoenix/upsert";

        OkHttpClient httpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        String post = "{\t\"ticket\": \"fbaa3a2a17afba65170f6939ac726af4daece82997c4fbd6c41cf01e1239ca17\",\t\"data\": \"upsert into BULLSEYE.BULLSEYE_REPORT_RESULT(PK, DEVICE_ID, DEVICE_TYPE, IDFA, IMEI, PLATFORM, SOURCE_ID, REPORT_TYPE, SOURCE_NAME, ADVERTISE_ID, ADS_ID, ADS_NAME, ADS_UNIT_ID, ADS_UNIT_NAME, CREATIVE_ID, CREATIVE_NAME, KEYWORD, KEYWORD_ID, QUERY, IP, UA, UID, RESULT, RESULT_DESC, REMARK, ACTION_TIME, CREATE_TIME, DELIVERY_ID, DELIVERY_NAME, TRACKING_CODE) values('imeiNo#55', '008deviceID', 'IOS', 'idfaNo', 'imeiNo', 55, 22, 8, '今日头条', 'advertiseId', 'adsId', 'adsName', 'adsUnitId', 'adsUnitName', 'creativeId', 'creativeName', 'keyword', 'kwId', 'query', 'IP..', 'ua.', 'uid22', 1, 'success', 'remark', 'actionTime', '1569229547584', '999', 'deliveryname', 'trackcode')\"}";
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