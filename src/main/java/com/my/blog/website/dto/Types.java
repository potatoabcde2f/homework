package com.my.blog.website.dto;

/** 系统类型常量 */
public enum Types {
    // 内容类型
    TAG("tag"),               // 标签
    CATEGORY("category"),     // 分类
    ARTICLE("post"),         // 文章
    PAGE("page"),           // 页面
    
    // 内容状态
    PUBLISH("publish"),      // 已发布
    DRAFT("draft"),         // 草稿
    
    // 资源类型
    LINK("link"),           // 链接
    IMAGE("image"),         // 图片
    FILE("file"),          // 文件
    
    // 系统配置
    CSRF_TOKEN("csrf_token"),
    COMMENTS_FREQUENCY("comments:frequency"),
    ATTACH_URL("attach_url"),        // 附件URL
    BLOCK_IPS("site_block_ips");     // IP黑名单


    // 类型字符串值
    private String type;

    /** 获取类型值 */
    public java.lang.String getType() {
        return type;
    }

    /** 设置类型值 */
    public void setType(java.lang.String type) {
        this.type = type;
    }

    /** 构造方法 */
    Types(java.lang.String type) {
        this.type = type;
    }
}
