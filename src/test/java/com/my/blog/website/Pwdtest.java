package com.my.blog.website;

import com.my.blog.website.modal.Vo.UserVo;
import com.my.blog.website.utils.TaleUtils;

/**
 * 密码加密测试类
 * 用于测试用户密码的MD5加密功能
 * 演示如何对用户名和密码组合进行MD5加密，生成存储到数据库的加密密码
 * 这是用户认证系统中常见的密码加密方式
 */
public class Pwdtest {

    /**
     * 主方法：测试密码加密功能
     * 创建用户对象，设置用户名和密码，然后使用TaleUtils工具类进行MD5加密
     */
    public static void main(String args[]){
        // 创建用户对象
        UserVo user = new UserVo();
        user.setUsername("admin");              // 设置用户名
        user.setPassword("asdfasdfs");          // 设置原始密码

        // 使用MD5对"用户名+密码"进行加密
        // 这种加密方式增加了密码的复杂度，提高了安全性
        String encodePwd = TaleUtils.MD5encode(user.getUsername() + user.getPassword());

        // 输出加密后的密码，用于验证加密功能是否正常工作
        System.out.println(encodePwd);
    }
}