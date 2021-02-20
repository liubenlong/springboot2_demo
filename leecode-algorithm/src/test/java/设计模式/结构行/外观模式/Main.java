package 设计模式.结构行.外观模式;

/**
 * @author albertliu
 * @className Main
 * @description TODO
 * @date 2020/10/14 10:45
 */
public class Main {
    public static void main(String[] args){
        ShapeFacade shapeFacade = new ShapeFacade();
        shapeFacade.drawCircle();
        shapeFacade.drawRectangle();
    }
}
