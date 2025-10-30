// 定义这个类所在的包路径
package com.my.blog.website.modal.Vo;

// 导入序列化接口
// Serializable 是一个标记接口，表示这个类的对象可以被序列化（可以转换成字节流进行存储或传输）
import java.io.Serializable;

/**评论值对象（Value Object）
 * 这个类用来表示评论的数据结构
 * @author 作者信息（通常写开发者名字）
 */
// public 表示这个类是公共的，可以被其他包中的类访问
// class 是定义类的关键字 ，implements Serializable 表示这个类实现了序列化接口
public class CommentVo implements Serializable {
    /**
     * serialVersionUID 是序列化版本控制ID，用来确保序列化和反序列化时类的版本一致性
     * 如果类结构发生变化，这个值也需要相应改变
     */
    private static final long serialVersionUID = 1L;

    // 下面是类的属性（成员变量），也称为字段（field）
    /**
     * comment表主键
     * private 表示这个属性是私有的，只能在本类中直接访问
     * Integer 是int的包装类，可以存储null值，而int不能
     */
    private Integer coid;

    /**
     * post表主键,关联字段
     * 这个字段用来关联文章表，表示这条评论属于哪篇文章
     */
    private Integer cid;

    /**
     * 评论生成时的GMT unix时间戳
     * Unix时间戳是从1970年1月1日开始的秒数
     * 在Java中通常用Long类型，这里用Integer可能不够（2038年问题）
     */
    private Integer created;

    /**
     * 评论作者，String 是Java中的字符串类，用来存储文本数据
     */
    private String author;

    /**
     * 评论所属用户id，如果评论者是注册用户，这里存储用户ID
     */
    private Integer authorId;

    /**
     * 评论所属内容作者id， 文章的作者ID
     */
    private Integer ownerId;

    /**
     * 评论者邮件
     */
    private String mail;

    /**
     * 评论者网址
     */
    private String url;

    /**
     * 评论者ip地址，记录评论者的IP地址，用于安全审计
     */
    private String ip;

    /**
     * 评论者客户端， 记录用户使用的浏览器、设备等信息
     */
    private String agent;

    /**
     * 评论类型，比如：文章评论、留言板评论等
     */
    private String type;

    /**
     * 评论状态，比如：已审核、待审核、已删除等
     */
    private String status;

    /**
     * 父级评论，用于实现评论的回复功能，指向父评论的ID
     */
    private Integer parent;

    /**
     * 评论内容，存储评论的具体文本内容
     */
    private String content;

    // 下面是getter和setter方法，这些方法用来读取和设置私有属性的值，这是JavaBean的规范

    /**
     * 获取评论ID的方法， @return 评论ID
     */
    public Integer getCoid() {
        return coid;  // 返回coid属性的值
    }

    /**
     * 设置评论ID的方法
     * @param coid 要设置的评论ID
     * void 表示这个方法不返回任何值
     */
    public void setCoid(Integer coid) {
        // this 关键字指向当前对象的引用，这里用来区分参数coid和类的属性coid
        this.coid = coid;
    }

    /**
     * 获取关联的文章ID
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * 设置关联的文章ID
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * 获取评论创建时间
     */
    public Integer getCreated() {
        return created;
    }

    /**
     * 设置评论创建时间
     */
    public void setCreated(Integer created) {
        this.created = created;
    }

    /**
     * 获取评论作者名
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置评论作者名
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取评论者用户ID
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * 设置评论者用户ID
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    /**
     * 获取文章作者ID
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * 设置文章作者ID
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 获取评论者邮箱
     */
    public String getMail() {
        return mail;
    }

    /**
     * 设置评论者邮箱
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * 获取评论者网址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置评论者网址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取评论者IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置评论者IP地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取评论者客户端信息
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 设置评论者客户端信息
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }

    /**
     * 获取评论类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置评论类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取评论状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置评论状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取父评论ID
     */
    public Integer getParent() {
        return parent;
    }

    /**
     * 设置父评论ID
     */
    public void setParent(Integer parent) {
        this.parent = parent;
    }

    /**
     * 获取评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}
// 类定义结束