package 设计模式.创建行.建造者模式;

/**
 * @author albertliu
 * @className Main
 * @description TODO
 * @date 2020/10/20 16:33
 */
public class Main {
    public static void main(String[] args){
        MealBuilder builder = new MealBuilder();
        Meal nonVegMeal = builder.buildNonVegMeal();
        System.out.println(nonVegMeal.getTotalPrice());
        nonVegMeal.showItem();

        System.out.println("===========");

        Meal vegMeal = builder.buildVegMeal();
        System.out.println(vegMeal.getTotalPrice());
        vegMeal.showItem();

    }

}
