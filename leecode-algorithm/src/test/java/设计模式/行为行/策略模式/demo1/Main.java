package 设计模式.行为行.策略模式.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author albertliu
 * @className Main
 * @description 策略模式
 * @date 2020/10/10 11:34
 */
public class Main {
    public static List<MyComparable> initCat() {
        List<MyComparable> cats = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Cat cat = new Cat();
            cat.setAge(random.nextInt(20));
            cat.setHeight(random.nextInt(40));
            cat.setWeight(random.nextInt(30));
            cats.add(cat);
        }

        cats.forEach(System.out::println);
        return cats;
    }

    public static void sort(List<MyComparable> list, MyCompartor compartor) {
        for (int i = 0; i < list.size(); i++) {
            for(int j =0;j<list.size()-i-1;j++){
                if(compartor.comare(list.get(j), list.get(j+1))>0){
                    MyComparable m = list.get(j);
                    list.remove(j);
                    list.add(j+1, m);
                }
            }
        }
    }

    public static void sortmaopao(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j + 1] < nums[j]) {
                    int o = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = o;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] ints = {1, 5, 3, 2, 4, 9, 6};
        sortmaopao(ints);
        for (int anInt : ints) {
            System.out.print(anInt);
        }
        System.out.println();

        List<MyComparable> cats = initCat();

        sort(cats, (o1, o2) -> {
            if (!(o1 instanceof Cat) || !(o2 instanceof Cat)) {
                throw new IllegalArgumentException("");
            }
            Cat cat1 = (Cat) o1;
            Cat cat2 = (Cat) o2;

            if (cat1.getAge() > cat2.getAge()) {
                return -1;
            } else {
                return 1;
            }
        });
        System.out.println("-----------");

        cats.forEach(System.out::println);
        System.out.println("-----------");


        sort(cats, new MyCompartorImpl());
        cats.forEach(System.out::println);
    }
}
