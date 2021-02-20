package 设计模式.行为行.责任链模式;

/**
 * @author albertliu
 * @className InfoLog
 * @description TODO
 * @date 2020/10/21 10:07
 */
public class ErrorLog implements Log{

    @Override
    public void print(String msg) {
        System.out.println("error.." + msg);
    }

}
