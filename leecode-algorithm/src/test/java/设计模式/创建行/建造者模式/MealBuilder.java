package 设计模式.创建行.建造者模式;

/**
 * @author albertliu
 * @className MealBuilder
 * @description 一餐构建者
 * @date 2020/10/20 16:26
 */
public class MealBuilder {
    public Meal buildVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal buildNonVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
