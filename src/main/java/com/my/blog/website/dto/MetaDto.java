package com.my.blog.website.dto;

import com.my.blog.website.modal.Vo.MetaVo;

/** 元数据统计扩展对象 */
public class MetaDto extends MetaVo {
    // 关联记录数量
    private int count;

    /** 获取关联数量 */
    public int getCount() {
        return count;
    }

    /** 设置关联数量 */
    public void setCount(int count) {
        this.count = count;
    }
}
