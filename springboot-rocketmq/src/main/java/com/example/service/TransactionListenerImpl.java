package com.example.service;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionListenerImpl implements TransactionListener {
       private AtomicInteger transactionIndex = new AtomicInteger(0);
   
       private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();
   
       @Override
       public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
           System.out.println("executeLocalTransaction");
           int value = transactionIndex.getAndIncrement();
           int status = value % 3;//这里模拟失败超时等场景
           localTrans.put(msg.getTransactionId(), status);
           return LocalTransactionState.UNKNOW;
       }
   
       @Override
       public LocalTransactionState checkLocalTransaction(MessageExt msg) {
           Integer status = localTrans.get(msg.getTransactionId());
           System.out.println("checkLocalTransaction" + msg + "   "+status);
           if (null != status) {
               switch (status) {
                   case 0:
                       return LocalTransactionState.UNKNOW;
                   case 1:
                       return LocalTransactionState.COMMIT_MESSAGE;
                   case 2:
                       return LocalTransactionState.ROLLBACK_MESSAGE;
               }
           }
           return LocalTransactionState.COMMIT_MESSAGE;
       }
   }