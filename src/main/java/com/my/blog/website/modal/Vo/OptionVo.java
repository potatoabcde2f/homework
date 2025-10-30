package com.my.blog.website.modal.Vo;

import java.io.Serializable;

/**配置项值对象（Option Value Object）, 用于存储系统配置信息，如网站标题、描述等
 * 对应数据库中的option表
 */
public class OptionVo implements Serializable {
    //配置名称, 配置项的唯一标识，如："site_title", "site_description"
    private String name;

    //配置值,配置项的具体值
    private String value;

    //配置描述,配置项的说明信息

    private String description;

    //序列化版本ID
    private static final long serialVersionUID = 1L;

    // ========== Getter和Setter方法 ==========

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}