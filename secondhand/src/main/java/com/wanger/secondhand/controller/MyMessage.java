package com.wanger.secondhand.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MyMessage {

    private int code;

    private String msg;

    public MyMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MyMessage() {
    }
}
