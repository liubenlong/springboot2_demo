package 算法.LRU;

import java.util.Map;

/**
 * @author liubenlog
 * @className MyList
 * @description 双向链表. 我这里规定tail是最新的数据
 * @date 2020/10/22 16:03
 */
public class MyList {

    /**
     * 最大缓存数据量
     */
    private long maxSize;

    /**
     * 当前缓存的数据量
     */
    private long size;

    private Node head;

    /**
     * 新添加的元素添加到tail
     */
    private Node tail;

    private Map map;

    public MyList(long maxSize, Map map) {
        this.maxSize = maxSize;
        this.map = map;
    }

    /**
     * 直接添加到tail
     *
     * @param node
     */
    public void add(Node node) {
        size++;
        if (null == head) {
            //head为空，说明此时队列中一个数据也没有
            head = node;
            tail = node;
        } else {
            node.setPre(tail);
            tail.setNext(node);
            tail = node;

            //超过容量后，将head元素删除
            if (size > maxSize) {
                map.remove(head.getKey());

                Node next = head.getNext();
                next.setPre(null);
                head.setNext(null);
                head = next;
                size--;
            }
        }
    }

    public void remove(Node node) {
        if (null == head) {//队列为空
            return;
        } else if (head == node && tail == node) {//只有一个元素时
            head = null;
            tail = null;
            size--;
        } else {
            Node pre = node.getPre();
            Node next = node.getNext();
            if (null != pre) {
                pre.setNext(next);
            } else {
                head = next;
            }
            if (null != next) {
                next.setPre(pre);
            } else {
                tail = pre;
            }
            node.setPre(null);
            node.setNext(null);
            size--;
        }
    }

    public void get(Node node) {
        remove(node);
        add(node);
    }

    public void update(Node node) {
        remove(node);
        add(node);
    }

    @Override
    public String toString() {
        String result = "";
        if (head == null) {
            return result;
        } else {
            Node next = head;
            result += next + ", ";
            while ((next = next.getNext()) != null) {
                result += next + ", ";
            }
            return result;
        }
    }
}
