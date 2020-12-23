package com.example.vo;

import lombok.Data;

@Data
public class InnerVO {
    String x;
    String c;

    @Override
    public String toString() {
        return "InnerVO{" +
                "x='" + x + '\'' +
                ", c='" + c + '\'' +
                '}';
    }
}
