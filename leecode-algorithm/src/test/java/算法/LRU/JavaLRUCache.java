package 算法.LRU;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liubenlog
 * @className Main
 * @description
 * LinkedHashMap 本身内部有一个触发条件则自动执行的方法：删除最老元素（最近最少使用的元素）
 * 由于最近最少使用元素是 LinkedHashMap 内部处理
 * 故我们不再需要维护 最近访问元素放在链尾，get 时直接访问/ put 时直接存储
 *
 * @date 2020/10/22 16:03
 */
public class JavaLRUCache implements ILru{

    private Map<Object, Object> map;
    private final int capacity;

    public JavaLRUCache(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<Object, Object>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
                return size() > capacity;
            }

            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    @Override
    public Object get(String key) {
        return map.get(key);
    }

    @Override
    public void put(Object key, Object value) {
        map.put(key, value);
    }

    @Override
    public void remove(String key) {
        map.remove(key);
    }

    @Override
    public String toString() {
        return "JavaLRUCache{" +
                "map=" + map +
                ", capacity=" + capacity +
                '}';
    }
}
