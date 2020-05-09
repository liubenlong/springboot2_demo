package com.example.config;

public class R<T> {

    private Integer code;

    private String msg;

    private T data;

    public R() {

    }

    public R(ParamErrorCode returnCode, String msg, T data) {
        this.code = returnCode.getCode();
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
