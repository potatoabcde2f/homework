package com.my.blog.website.modal.Vo;

import java.io.Serializable;

/**元数据值对象（Meta Value Object）,用于存储分类、标签等元数据信息
 * 对应数据库中的meta表
 */
public class MetaVo implements Serializable {
    //项目主键,元数据的唯一标识
    private Integer mid;

    //名称,如：分类名称、标签名称
    private String name;

    //项目缩略名,用于URL的友好名称
    private String slug;

    //项目类型,如：category（分类）、tag（标签）等
    private String type;

    //选项描述,分类或标签的描述信息
    private String description;

    //项目排序,用于控制显示顺序
    private Integer sort;

    //父级ID, 用于实现多级分类
    private Integer parent;

    //序列化版本ID
    private static final long serialVersionUID = 1L;

    // Getter和Setter方法
    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }
}