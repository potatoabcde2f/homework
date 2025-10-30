package com.my.blog.website.dto;

/** 系统日志操作类型 */
public enum LogActions {
    // 用户操作类
    LOGIN("登录后台"),
    UP_PWD("修改密码"),
    UP_INFO("修改个人信息"),

    // 内容管理类
    DEL_ARTICLE("删除文章"),
    DEL_PAGE("删除页面"),

    // 系统管理类
    SYS_BACKUP("系统备份"),
    SYS_SETTING("保存系统设置"),
    INIT_SITE("初始化站点");

    // 操作的中文描述
    private String action;

    /** 获取操作描述 */
    public String getAction() {
        return action;
    }

    /** 设置操作描述 */
    public void setAction(String action) {
        this.action = action;
    }

    /** 构造方法 */
    LogActions(String action) {
        this.action = action;
    }
}
