package 设计模式.结构行.享元模式;

import java.util.HashMap;
import java.util.Map;

/**
 * @author albertliu
 * @className ShapFactory
 * @description 享元模式
 * @date 2020/10/14 10:56
 */
public class ShapFactory {
    private static final Map<String, Shape> map = new HashMap<>();

    public Shape createShap(String color){
        if(map.get(color) == null){
            synchronized (ShapFactory.class){
                if(map.get(color) == null){
                    map.put(color, new Circle(color));
                }
            }
        }
        return map.get(color);
    }

}
