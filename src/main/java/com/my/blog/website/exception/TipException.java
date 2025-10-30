package com.my.blog.website.exception;

/** 业务提示异常，用于返回友好的错误信息 */
public class TipException extends RuntimeException {
    /** 无参构造 */
    public TipException() {
    }

    /** 带消息的构造方法 */
    public TipException(String message) {
        super(message);
    }

    /** 带消息和原因的构造方法 */
    public TipException(String message, Throwable cause) {
        super(message, cause);
    }

    /** 带原因的构造方法 */
    public TipException(Throwable cause) {
        super(cause);
    }

}
