package 观察者模式;

/**
 * 普通用户
 * 具体观察者
 */
public class User implements Observer {

    private String name;
    private String message;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    public void read() {
        System.out.println(name + " 收到被观察者推送消息： " + message);
    }

}
