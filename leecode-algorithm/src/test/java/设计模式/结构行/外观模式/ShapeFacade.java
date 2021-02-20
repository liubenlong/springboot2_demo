package 设计模式.结构行.外观模式;

/**
 * @author albertliu
 * @className ShapeMaker
 * @description 外观类
 * @date 2020/10/14 10:42
 */
public class ShapeFacade {

    private Shape circle;
    private Shape square;
    private Shape rectangle;

    public ShapeFacade() {
        circle = new Circle();
        square = new Square();
        rectangle = new Rectangle();
    }

    public void drawCircle(){
        circle.draw();
    }
    public void drawSquare(){
        square.draw();
    }
    public void drawRectangle(){
        rectangle.draw();
    }
}
