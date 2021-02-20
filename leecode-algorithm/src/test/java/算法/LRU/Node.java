package 算法.LRU;

import lombok.Data;

/**
 * @author liubenlog
 * @className Node
 * @description map 中的V，设置为一个对象，主要是为了记录前后指针，已达到 0（1）的时间复杂度
 * @date 2020/10/22 16:02
 */
@Data
public class Node {
    private Object key;
    private Object value;
    private Node pre;
    private Node next;

    public Node(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key.toString() + "=" + value.toString();
    }
}
