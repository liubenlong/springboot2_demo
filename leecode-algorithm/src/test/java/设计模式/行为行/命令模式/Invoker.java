package 设计模式.行为行.命令模式;

/**
 * @author albertliu
 * @className Invoker
 * @description 命令发起者
 * @date 2020/10/14 11:36
 */
public class Invoker {
    private Command command;
    public Invoker(Command command){
        this.command=command;
    }
    public void exe(){
        command.exe();
    }

}
