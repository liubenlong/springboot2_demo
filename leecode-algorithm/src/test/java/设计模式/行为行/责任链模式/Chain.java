package 设计模式.行为行.责任链模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @author albertliu
 * @className Chain
 * @description TODO
 * @date 2020/10/21 10:08
 */
public class Chain {

    private List<Log> logs = new ArrayList<>();

    Chain next(Log log){
        logs.add(log);
        return this;
    }

    void doOperation(String msg){
        logs.forEach(log -> log.print(msg));
    }

}
