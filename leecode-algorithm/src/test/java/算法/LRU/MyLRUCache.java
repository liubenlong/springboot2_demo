package 算法.LRU;

import java.util.HashMap;

/**
 * @author liubenlog
 * @className MyLRUCache
 * @description 使用Map和双向链表构建自己的LRU算法
 * @date 2020/10/22 16:30
 */
public class MyLRUCache implements ILru{
    private HashMap<Object, Node> map = new HashMap();
    private MyList list;

    public MyLRUCache(long maxSize) {
        list = new MyList(maxSize, map);
    }

    @Override
    public void put(Object key, Object value) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node(key, value);
            map.put(key, node);
            list.add(node);
        } else {
            node.setValue(value);
            map.put(key, node);
            list.update(node);
        }
    }

    @Override
    public void remove(String key) {
        list.remove(map.get(key));
        map.remove(key);
    }

    @Override
    public Object get(String key) {
        Node node = map.get(key);
        if (node == null) {
            return null;
        }
        list.get(node);

        return node.getValue();
    }

    @Override
    public String toString() {
        return "LRU{" +
                "list=" + list +
                '}';
    }
}

