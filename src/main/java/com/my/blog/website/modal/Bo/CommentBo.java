package com.my.blog.website.modal.Bo;

import com.my.blog.website.modal.Vo.CommentVo;

import java.util.List;

/**
 * 页面返回的评论对象，包含层级信息与子评论列表
 */
public class CommentBo extends CommentVo {

    // 评论层级（用于前端展示缩进）
    private int levels;
    // 子评论列表
    private List<CommentVo> children;

    /**
     * 使用已有 CommentVo 构造带层级与子评论支持的 CommentBo
     */
    public CommentBo(CommentVo comments) {
        // 复制基础字段到扩展对象，避免继承字段为空
        setAuthor(comments.getAuthor());
        setMail(comments.getMail());
        setCoid(comments.getCoid());
        setAuthorId(comments.getAuthorId());
        setUrl(comments.getUrl());
        setCreated(comments.getCreated());
        setAgent(comments.getAgent());
        setIp(comments.getIp());
        setContent(comments.getContent());
        setOwnerId(comments.getOwnerId());
        setCid(comments.getCid());
    }

    /**
     * 获取评论层级
     * @return 层级数
     */
    public int getLevels() {
        return levels;
    }

    /**
     * 设置评论层级
     * @param levels 层级数
     */
    public void setLevels(int levels) {
        this.levels = levels;
    }

    /**
     * 获取子评论列表
     * @return 子评论集合
     */
    public List<CommentVo> getChildren() {
        return children;
    }

    /**
     * 设置子评论列表
     * @param children 子评论集合
     */
    public void setChildren(List<CommentVo> children) {
        this.children = children;
    }
}
