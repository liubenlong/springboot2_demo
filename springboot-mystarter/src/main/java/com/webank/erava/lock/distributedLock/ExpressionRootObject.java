package com.webank.erava.lock.distributedLock;

/**
 * @Author: Lijian
 * @Date: 2019-09-06 09:26
 */
public class ExpressionRootObject {
    private final Object object;
    private final Object[] args;

    public ExpressionRootObject(Object object, Object[] args) {
        this.object = object;
        this.args = args;
    }

    public Object getObject() {
        return object;
    }

    public Object[] getArgs() {
        return args;
    }
}

