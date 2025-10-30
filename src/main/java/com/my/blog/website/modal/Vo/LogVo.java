package com.my.blog.website.modal.Vo;

import java.io.Serializable;

/**
 * 日志值对象（Log Value Object）, 用于记录系统操作日志，对应数据库中的日志表
*/
public class LogVo implements Serializable {
    //序列化版本ID
    private static final long serialVersionUID = 1L;

    // ========== 日志相关属性 ==========
    //日志主键,日志记录的唯一标识
    private Integer id;

    //产生的动作,记录用户执行的操作，如："用户登录"、"发布文章"等
    private String action;

    // 产生的数据,操作相关的数据，通常以JSON格式存储
    private String data;

    //发生人id, 执行操作的用户ID
    private Integer authorId;

    //日志产生的ip,记录操作来源的IP地址，用于安全审计
    private String ip;

    //日志创建时间, 操作发生的时间戳
    private Integer created;

    // ========== Getter和Setter方法 ==========

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }
}