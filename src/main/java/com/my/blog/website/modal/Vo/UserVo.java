// 定义包路径，表示这个类在项目中的位置
// 包名通常使用公司域名的反写，保证唯一性
package com.my.blog.website.modal.Vo;

// 导入序列化接口
// 序列化：把对象转换成字节序列的过程，便于存储或网络传输
import java.io.Serializable;

/**
 * 用户值对象（User Value Object）
 * 用于在层间传输用户数据，通常对应数据库中的user表
 * @author 开发者信息
 */
// public: 访问修饰符，表示这个类可以被任何其他类访问，class: 定义类的关键字， implements Serializable: 实现序列化接口，使对象可以序列化
public class UserVo implements Serializable {
    /**
     * 序列化版本ID
     * 重要：如果修改了类结构，应该更新这个值
     * 否则反序列化时可能出现版本不匹配异常
     */
    private static final long serialVersionUID = 1L;

    // ========== 类的属性（成员变量）==========
    // 这些属性通常对应数据库表中的字段

    /**
     * user表主键
     * private: 私有访问，只能通过getter/setter方法访问（封装性）
     * Integer: 包装类，可以存储null值，比int更安全
     */
    private Integer uid;

    /**
     * 用户名称，用于登录的用户名
     */
    private String username;

    /**
     * 用户密码，注意：实际项目中密码应该加密存储
     */
    private String password;

    /**
     * 用户的邮箱， 用于接收通知和找回密码
     */
    private String email;

    /**
     * 用户的主页，用户的个人网站或博客地址
     */
    private String homeUrl;

    /**
     * 用户显示的名称，在界面上显示的名字，可以和登录名不同
     */
    private String screenName;

    /**
     * 用户注册时的GMT unix时间戳
     * Unix时间戳：从1970年1月1日开始的秒数
     */
    private Integer created;

    /**
     * 最后活动时间，记录用户最后一次操作的时间戳
     */
    private Integer activated;

    /**
     * 上次登录最后活跃时间，用于判断用户是否在线
     */
    private Integer logged;

    /**
     * 用户组：用于权限控制，如：管理员、普通用户等
     */
    private String groupName;

    // ========== Getter和Setter方法 ==========
    // 这些方法遵循JavaBean规范，提供对私有属性的安全访问

    /**
     * 获取用户ID
     * @return 用户ID
     */
    public Integer getUid() {
        // 直接返回uid属性的值
        return uid;
    }

    /**
     * 设置用户ID
     * @param uid 要设置的用户ID
     */
    public void setUid(Integer uid) {
        // this.uid 表示当前对象的uid属性，uid 表示方法的参数， 这样区分可以避免命名冲突
        this.uid = uid;
    }

    /**
     * 获取用户名
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     * @param username 要设置的用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码，注意：在实际业务中，不应该直接返回明文密码
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     * @param password 要设置的密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取邮箱
     * @return 邮箱地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     * @param email 要设置的邮箱地址
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取主页URL
     * @return 主页地址
     */
    public String getHomeUrl() {
        return homeUrl;
    }

    /**
     * 设置主页URL
     * @param homeUrl 要设置的主页地址
     */
    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    /**
     * 获取显示名称
     * @return 显示名称
     */
    public String getScreenName() {
        return screenName;
    }

    /**
     * 设置显示名称
     * @param screenName 要设置的显示名称
     */
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    /**
     * 获取注册时间
     * @return 注册时间戳
     */
    public Integer getCreated() {
        return created;
    }

    /**
     * 设置注册时间
     * @param created 要设置的注册时间戳
     */
    public void setCreated(Integer created) {
        this.created = created;
    }

    /**
     * 获取最后活动时间
     * @return 最后活动时间戳
     */
    public Integer getActivated() {
        return activated;
    }

    /**
     * 设置最后活动时间
     * @param activated 要设置的最后活动时间戳
     */
    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    /**
     * 获取上次登录时间
     * @return 上次登录时间戳
     */
    public Integer getLogged() {
        return logged;
    }

    /**
     * 设置上次登录时间
     * @param logged 要设置的上次登录时间戳
     */
    public void setLogged(Integer logged) {
        this.logged = logged;
    }

    /**
     * 获取用户组名称
     * @return 用户组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置用户组名称
     * @param groupName 要设置的用户组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
