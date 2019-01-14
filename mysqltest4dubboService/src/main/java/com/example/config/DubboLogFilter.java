package com.example.config;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DubboLogFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long start = System.currentTimeMillis();
        Result result = invoker.invoke(invocation);
        long elapsed = System.currentTimeMillis() - start;
        if (invoker.getUrl() != null) {
            try {
                log.info("dubbo log [{}#{}], param={}, [result={}], [Exception={}], [elapsed={}]   ",
                        invoker.getInterface(), invocation.getMethodName(),
                        JSONArray.toJSONString(invocation.getArguments()),
                        JSONObject.toJSONString(result.getValue()),
                        result.getException(),
                        elapsed);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
            }
        }

        return result;
    }

}
