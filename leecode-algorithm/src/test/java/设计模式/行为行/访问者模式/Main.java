package 设计模式.行为行.访问者模式;

/**
 * @author albertliu
 * @className Main
 * @description TODO
 * @date 2020/10/14 10:45
 */
public class Main {
    public static void main(String[] args){
        ElementSet elementSet = new ElementSet();
        elementSet.addElement(new ElementA());
        elementSet.addElement(new ElementB());

        System.out.println(elementSet.accept(new VisitM()));
        System.out.println(elementSet.accept(new VisitN()));


    }
}
