package 算法.LRU;

/**
 * @author liubenlog
 * @className Main
 * @description TODO
 * @date 2020/10/22 16:03
 */
public class Main {
    public static void main(String[] args) {
        lruTest(new MyLRUCache(5));
        System.out.println("-------------------------");
        lruTest(new JavaLRUCache(5));
    }

    static void lruTest(ILru lru){
        lru.put("a", 1);
        System.out.println(lru.toString());
        lru.put("b", 2);
        System.out.println(lru.toString());
        lru.put("c", 3);
        System.out.println(lru.toString());
        lru.put("d", 4);
        System.out.println(lru.toString());
        lru.get("a");
        System.out.println("get a 1  " + lru.toString());
        lru.get("b");
        System.out.println("get b 2  " + lru.toString());
        lru.get("b");
        System.out.println("get b 2  " + lru.toString());
        lru.get("d");
        System.out.println("get d 4  " + lru.toString());
        lru.put("e", 5);
        System.out.println(lru.toString());
        lru.put("f", 6);
        System.out.println(lru.toString());
        lru.put("g", 7);
        System.out.println(lru.toString());
        lru.remove("g");
        System.out.println("remove g 7  " + lru.toString());
        lru.remove("e");
        System.out.println("remove e 5  " + lru.toString());
        lru.put("e", 5);
        System.out.println(lru.toString());
        lru.put("e", 9);
        System.out.println(lru.toString());
        lru.put("f", "f");
        System.out.println(lru.toString());
    }
}
