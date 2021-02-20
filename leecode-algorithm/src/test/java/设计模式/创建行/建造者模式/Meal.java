package 设计模式.创建行.建造者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @author albertliu
 * @className Meal
 * @description 一餐
 * @date 2020/10/20 16:25
 */
public class Meal {

    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public int getTotalPrice(){
        return items.stream().mapToInt(Item::price).sum();
    }

    public void showItem(){
        items.forEach(item -> System.out.println(item.toString()));
    }

}
