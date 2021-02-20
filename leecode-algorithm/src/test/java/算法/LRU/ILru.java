package 算法.LRU;

/**
 * @author liubenlog
 * @className ILru
 * @description LRU接口
 * @date 2020/11/24 15:45
 */
public interface ILru {
    void put(Object key, Object value);

    void remove(String key);

    Object get(String key);
}
