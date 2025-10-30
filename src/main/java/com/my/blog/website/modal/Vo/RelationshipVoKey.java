package com.my.blog.website.modal.Vo;

import java.io.Serializable;

//关系主键类（Relationship Primary Key） 用于表示文章和分类/标签的多对多关系这是一个复合主键类，包含两个字段

public class RelationshipVoKey implements Serializable {
    // 序列化版本ID
    private static final long serialVersionUID = 1L;

    // ========== 复合主键字段 ==========
    // 内容主键,关联到文章表的ID
    private Integer cid;

    //项目主键,关联到分类/标签表的ID
    private Integer mid;

    // ========== Getter和Setter方法 ==========

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}