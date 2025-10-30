package com.my.blog.website.modal.Bo;

/**
 * REST 接口统一返回对象。
 * <p>泛型 T 为 payload 的类型。</p>
 *
 * 设计要点：
 * - payload: 服务器返回的数据体（可能为 null）
 * - success: 请求是否成功
 * - msg/code: 可选的错误信息和状态码
 * - timestamp: 服务端响应时间（秒）
 */
public class RestResponseBo<T> {

    /** 服务器响应数据 */
    private T payload;

    /** 请求是否成功 */
    private boolean success;

    /** 错误或提示信息 */
    private String msg;

    /** 状态码，默认 -1 表示未设置 */
    private int code = -1;

    /** 服务器响应时间（单位：秒） */
    private long timestamp;

    /**
     * 默认构造器，初始化 timestamp 为当前秒级时间戳
     */
    public RestResponseBo() {
        // 使用 System.currentTimeMillis() / 1000 取得秒级时间戳
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    /**
     * 仅设置 success
     */
    public RestResponseBo(boolean success) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
    }

    /**
     * 设置 success 与 payload
     */
    public RestResponseBo(boolean success, T payload) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
    }

    /**
     * 设置 success、payload 与 code
     */
    public RestResponseBo(boolean success, T payload, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
        this.code = code;
    }

    /**
     * 设置 success 与 msg
     */
    public RestResponseBo(boolean success, String msg) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
    }

    /**
     * 设置 success、msg 与 code
     */
    public RestResponseBo(boolean success, String msg, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
        this.code = code;
    }

    /** 返回响应数据 */
    public T getPayload() {
        return payload;
    }

    /** 设置响应数据 */
    public void setPayload(T payload) {
        this.payload = payload;
    }

    /** 是否成功 */
    public boolean isSuccess() {
        return success;
    }

    /** 设置是否成功 */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /** 获取提示或错误信息 */
    public String getMsg() {
        return msg;
    }

    /** 设置提示或错误信息 */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /** 获取状态码 */
    public int getCode() {
        return code;
    }

    /** 设置状态码 */
    public void setCode(int code) {
        this.code = code;
    }

    /** 返回秒级时间戳 */
    public long getTimestamp() {
        return timestamp;
    }

    /** 手动设置时间戳（一般不需要） */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    // ----------------- 静态工厂方法，返回泛型 RestResponseBo<T> -----------------

    /** 快速创建成功响应（无数据） */
    public static <T> RestResponseBo<T> ok() {
        return new RestResponseBo<T>(true);
    }

    /** 快速创建成功响应（包含数据） */
    public static <T> RestResponseBo<T> ok(T payload) {
        return new RestResponseBo<T>(true, payload);
    }

    /** 快速创建成功响应并包含状态码（无数据） */
    public static <T> RestResponseBo<T> ok(int code) {
        // 将 null 强制为 T 类型以避免与 (String) 重载发生二义性
        return new RestResponseBo<T>(true, (T) null, code);
    }

    /** 快速创建成功响应（数据 + 状态码） */
    public static <T> RestResponseBo<T> ok(T payload, int code) {
        return new RestResponseBo<T>(true, payload, code);
    }

    /** 快速创建失败响应（无消息） */
    public static <T> RestResponseBo<T> fail() {
        return new RestResponseBo<T>(false);
    }

    /** 快速创建失败响应（带消息） */
    public static <T> RestResponseBo<T> fail(String msg) {
        return new RestResponseBo<T>(false, msg);
    }

    /** 快速创建失败响应（带状态码） */
    public static <T> RestResponseBo<T> fail(int code) {
        // 将 null 强制为 T 类型以避免与 (String) 重载发生二义性
        return new RestResponseBo<T>(false, (T) null, code);
    }

    /** 快速创建失败响应（带状态码与消息） */
    public static <T> RestResponseBo<T> fail(int code, String msg) {
        return new RestResponseBo<T>(false, msg, code);
    }

}