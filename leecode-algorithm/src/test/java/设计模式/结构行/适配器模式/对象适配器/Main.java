package 设计模式.结构行.适配器模式.对象适配器;


/**
 * @author albertliu
 * @className Main
 * @description https://blog.csdn.net/wwwdc1012/article/details/82780560
 * @date 2020/10/20 20:52
 */
public class Main {
    public static void main(String[] args){
        AC110 ac110 = new AC110();
        AC220 ac220 = new AC220();

        ChinaACAdapter chinaACAdapter = new ChinaACAdapter();
        JapanACAdapter japanACAdapter = new JapanACAdapter();

        if(chinaACAdapter.support(ac110)){
            chinaACAdapter.adapter(ac110);
        }else if (japanACAdapter.support(ac110)){
            japanACAdapter.adapter(ac110);
        }

        if(chinaACAdapter.support(ac220)){
            chinaACAdapter.adapter(ac220);
        }else if (japanACAdapter.support(ac220)){
            japanACAdapter.adapter(ac220);
        }

    }
}
