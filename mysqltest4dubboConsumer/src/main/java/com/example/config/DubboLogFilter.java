package com.example.config;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DubboLogFilter implements Filter {

//    public static final String TRACE_ID = "TRACE_ID";


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long start = System.currentTimeMillis();

//        if (RpcContext.getContext().isConsumerSide()) {
//            String traceId = MDCUtil.get(MDCUtil.Type.TRACE_ID);
//            if (StringUtils.isBlank(traceId)) {
//                traceId = UUID.randomUUID().toString().replaceAll("-", "");
//            }
//            RpcContext.getContext().setAttachment(TRACE_ID, traceId);
//        }
//        if (RpcContext.getContext().isProviderSide()) {
//            String traceId = RpcContext.getContext().getAttachment(TRACE_ID);
//            if (StringUtils.isBlank(traceId)) {
//                traceId = UUID.randomUUID().toString().replaceAll("-", "");
//            }
//            MDCUtil.put(MDCUtil.Type.TRACE_ID, traceId);
//        }

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
