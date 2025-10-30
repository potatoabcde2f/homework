package com.my.blog.website.modal.Bo;

import java.io.Serializable;

/**
 * 后台统计信息（文章、评论、链接、附件数）
 */
public class StatisticsBo implements Serializable {

    private static final long serialVersionUID = 1L;

    // 文章数量
    private Long articles;
    // 评论数量
    private Long comments;
    // 友链数量
    private Long links;
    // 附件数量
    private Long attachs;

    public Long getArticles() {
        // 返回文章数量
        return articles;
    }

    public void setArticles(Long articles) {
        // 设置文章数量
        this.articles = articles;
    }

    public Long getComments() {
        // 返回评论数量
        return comments;
    }

    public void setComments(Long comments) {
        // 设置评论数量
        this.comments = comments;
    }

    public Long getLinks() {
        // 返回友链数量
        return links;
    }

    public void setLinks(Long links) {
        // 设置友链数量
        this.links = links;
    }

    public Long getAttachs() {
        // 返回附件数量
        return attachs;
    }

    public void setAttachs(Long attachs) {
        // 设置附件数量
        this.attachs = attachs;
    }

    @Override
    public String toString() {
        // 简短的字符串表示，便于日志和调试输出
        return "StatisticsBo{" +
                "articles=" + articles +
                ", comments=" + comments +
                ", links=" + links +
                ", attachs=" + attachs +
                '}';
    }
}
