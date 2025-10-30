package com.my.blog.website.modal.Bo;

import com.my.blog.website.modal.Vo.ContentVo;

import java.io.Serializable;
import java.util.List;

/**
 * 按日期归档的文章集合（用于前端归档列表展示）
 */
public class ArchiveBo implements Serializable {

    private static final long serialVersionUID = 1L;

    // 归档日期（例如：2017-02）
    private String date;
    // 该月文章数（字符串形式，兼容旧代码）
    private String count;
    // 该日期下的文章列表
    private List<ContentVo> articles;

    public String getDate() {
        // 返回归档日期
        return date;
    }

    public void setDate(String date) {
        // 设置归档日期
        this.date = date;
    }

    public String getCount() {
        // 返回文章数（字符串）
        return count;
    }

    public void setCount(String count) {
        // 设置文章数（字符串）
        this.count = count;
    }

    public List<ContentVo> getArticles() {
        // 返回该归档日期下的文章列表
        return articles;
    }

    public void setArticles(List<ContentVo> articles) {
        // 设置文章列表
        this.articles = articles;
    }

    @Override
    public String toString() {
    // 简单字符串表示，便于日志输出
    return "Archive [" +
        "date='" + date + '\'' +
        ", count='" + count + '\'' +
        ", articles=" + articles +
        ']';
    }
}
