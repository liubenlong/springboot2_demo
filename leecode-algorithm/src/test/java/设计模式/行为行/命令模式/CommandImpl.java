package 设计模式.行为行.命令模式;

/**
 * @author albertliu
 * @className CommandImpl
 * @description TODO
 * @date 2020/10/14 11:33
 */
public class CommandImpl implements Command {

    private Receiver receiver;

    public CommandImpl(Receiver receiver){
        this.receiver=receiver;
    }

    @Override
    public void exe() {
        receiver.doAction();
    }
}
