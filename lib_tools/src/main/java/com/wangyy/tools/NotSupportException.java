package com.wangyy.tools;

/**
 * 不支持的操作异常
 *
 * @author zhangpfan.
 */
public class NotSupportException extends Exception {

    public NotSupportException() {
    }

    public NotSupportException(String message) {
        super(message);
    }
}
