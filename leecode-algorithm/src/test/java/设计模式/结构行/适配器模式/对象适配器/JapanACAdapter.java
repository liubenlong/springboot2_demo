package 设计模式.结构行.适配器模式.对象适配器;

/**
 * @author albertliu
 * @className ChinaACAdapter
 * @description TODO
 * @date 2020/10/20 21:05
 */
public class JapanACAdapter implements ACAdapter {
    @Override
    public boolean support(AC ac) {
        return ac.outPutAC() == 110;
    }

    @Override
    public int adapter(AC ac) {
        System.out.println("日本适配器将110V转换为5V");
        return 5;
    }
}
