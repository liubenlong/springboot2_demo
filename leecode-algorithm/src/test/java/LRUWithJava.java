import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * java实现LRU算法
 */
public class LRUWithJava {

    /**
     * 方式一：
     * 直接采用JDK自带的LinkedHashMap实现，其本身支持LRU。
     */
    @Test
    public void a(){
        int cacheSize = 10;
        Map<String, String> map = new LinkedHashMap<String, String>((int) Math.ceil(cacheSize / 0.75f) + 1,
                0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > cacheSize;
            }
        };

        for (int i = 0; i < 10; i++) {
            map.put(i+"", i+"");
        }

        System.out.println(map.toString());
        map.get("2");
        map.get("4");
        System.out.println(map.toString());

        map.put("10", "10'");
        System.out.println(map.toString());
    }


    /**
     * 方式二：
     * 使用hashmap加链表的数据结构
     */
    @Test
    public void b(){
        System.out.println("===========================LRU 链表实现===========================");
        LRUCache1<Integer, String> lru = new LRUCache1(5);
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        System.out.println(lru.toString());
        lru.put(6, "66");
        System.out.println(lru.toString());
        lru.get(2);
        System.out.println(lru.toString());
        lru.put(7, "77");
        System.out.println(lru.toString());
        lru.get(4);
        System.out.println(lru.toString());
    }
}
