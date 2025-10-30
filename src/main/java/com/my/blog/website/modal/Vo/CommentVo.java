// 包声明
package com.my.blog.website.modal.Vo;

// 导入序列化接口
import java.io.Serializable;

/**内容值对象（Content Value Object）
 * 对应数据库中的post表，存储文章内容信息
 * @author 开发者信息
 */
public class ContentVo implements Serializable {
    /**
     * 序列化版本ID
     * 用于版本控制，确保序列化兼容性
     */
    private static final long serialVersionUID = 1L;

    // ========== 内容相关属性 ==========

    /**
     * post表主键
     * 文章的唯一标识
     */
    private Integer cid;

    /**
     * 内容标题
     * 文章的标题
     */
    private String title;

    /**
     * 内容缩略名
     * 用于生成URL的友好名称
     */
    private String slug;

    /**
     * 内容生成时的GMT unix时间戳
     * 文章创建时间
     */
    private Integer created;

    /**
     * 内容更改时的GMT unix时间戳
     * 文章最后修改时间
     */
    private Integer modified;

    /**
     * 内容所属用户id
     * 文章作者的用户ID
     */
    private Integer authorId;

    /**
     * 内容类别
     * 如：post（文章）、page（页面）等
     */
    private String type;

    /**
     * 内容状态
     * 如：publish（发布）、draft（草稿）等
     */
    private String status;

    /**
     * 标签列表
     * 多个标签用逗号分隔
     */
    private String tags;

    /**
     * 分类列表
     * 多个分类用逗号分隔
     */
    private String categories;

    /**
     * 点击次数
     * 文章被浏览的次数
     */
    private Integer hits;

    /**
     * 内容所属评论数
     * 文章的评论数量
     */
    private Integer commentsNum;

    /**
     * 是否允许评论
     * Boolean类型：true-允许，false-不允许
     */
    private Boolean allowComment;

    /**
     * 是否允许ping
     * 是否允许其他网站pingback
     */
    private Boolean allowPing;

    /**
     * 允许出现在聚合中
     * 是否允许在RSS订阅中显示
     */
    private Boolean allowFeed;

    /**
     * 内容文字
     * 文章的正文内容
     */
    private String content;

    // ========== Getter和Setter方法 ==========

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getModified() {
        return modified;
    }

    public void setModified(Integer modified) {
        this.modified = modified;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    /**
     * 获取是否允许评论
     * Boolean和boolean的区别：
     * - Boolean是包装类，可以存储null值
     * - boolean是基本类型，默认值false
     * @return 是否允许评论
     */
    public Boolean getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Boolean allowComment) {
        this.allowComment = allowComment;
    }

    public Boolean getAllowPing() {
        return allowPing;
    }

    public void setAllowPing(Boolean allowPing) {
        this.allowPing = allowPing;
    }

    public Boolean getAllowFeed() {
        return allowFeed;
    }

    public void setAllowFeed(Boolean allowFeed) {
        this.allowFeed = allowFeed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}