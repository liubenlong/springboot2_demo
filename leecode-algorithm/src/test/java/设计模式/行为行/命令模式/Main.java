package 设计模式.行为行.命令模式;

import java.math.BigDecimal;

/**
 * @author albertliu
 * @className Main
 * @description 命令模式。https://www.cnblogs.com/meet/p/5116430.html
 *
 * 配合备忘录模式，实现可撤回命令
 *
 * @date 2020/10/14 11:37
 */
public class Main {
    public static void main(String[] args){
        Object o = null;
        System.out.println(o instanceof  Number);

        Command command = new CommandImpl(new Receiver());
        Invoker invoker = new Invoker(command);
        invoker.exe();
    }
}
