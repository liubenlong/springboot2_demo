package com.example.config;

public enum ParamErrorCode {
    OK(200),
    PARAM_ERROR(100),
    BIZ_ERROR(300),
    ERROR(500),
    ;

    private Integer code;

    ParamErrorCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
