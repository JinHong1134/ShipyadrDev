package com.hwy.shipyard.exception;

/**
 * @program: shipyard
 * @author: huangwenyu
 * @create: 2019-08-24
 */
public class PurchaseException extends RuntimeException {

    private int code;

    public PurchaseException(int code) {
        this.code = code;
    }

    public PurchaseException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
