package 设计模式.结构行.代理模式.静态代理;

/**
 * @author albertliu
 * @className Main
 * @description TODO
 * @date 2020/10/20 16:33
 */
public class Main {
    public static void main(String[] args){
        Image image = new Image();
        image.show();
        System.out.println();

        new ImageProxy(image).show();
    }

}
