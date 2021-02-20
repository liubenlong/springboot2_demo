package 设计模式.结构行.代理模式.静态代理;

/**
 * @author albertliu
 * @className ImageProxy
 * @description TODO
 * @date 2020/10/20 20:24
 */
public class ImageProxy implements IImage{

    private Image image;

    public ImageProxy(Image image){
        this.image=image;
    }

    @Override
    public void show() {
        System.out.println("proxy...");
        image.show();
    }
}
