package com.my.blog.website.dto;

/** 数据库连接配置对象 */
public class DataSource {
    // 数据库连接URL，如: jdbc:mysql://localhost:3306/dbname
    private String url;

    // 数据库用户名
    private String username;

    // 数据库密码
    private String password;

    // 数据库驱动类名，如: com.mysql.jdbc.Driver
    private String drivercClassName;

    // 数据库名称
    private String dbName;

    /** 获取数据库名称 */
    public String getDbName() {
        return dbName;
    }

    /** 设置数据库名称 */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /** 获取数据库URL */
    public String getUrl() {
        return url;
    }

    /** 获取数据库用户名 */
    public String getUsername() {
        return username;
    }

    /** 获取数据库密码 */
    public String getPassword() {
        return password;
    }

    /** 获取数据库驱动类名 */
    public String getDrivercClassName() {
        return drivercClassName;
    }

    /** 设置数据库URL */
    public void setUrl(String url) {
        this.url = url;
    }

    /** 设置数据库用户名 */
    public void setUsername(String username) {
        this.username = username;
    }

    /** 设置数据库密码 */
    public void setPassword(String password) {
        this.password = password;
    }

    /** 设置数据库驱动类名 */
    public void setDrivercClassName(String drivercClassName) {
        this.drivercClassName = drivercClassName;
    }

    @Override
    public String toString() {
        return "DataSource{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", drivercClassName='" + drivercClassName + '\'' +
                ", dbName='" + dbName + '\'' +
                '}';
    }
}
