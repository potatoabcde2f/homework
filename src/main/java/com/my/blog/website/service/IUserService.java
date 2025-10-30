package com.my.blog.website.service;

import com.my.blog.website.modal.Vo.UserVo;

/**
 * 用户服务核心接口
 * 定义用户全生命周期的核心操作规范，包括用户注册（数据保存）、查询、登录验证及信息更新，
 * 是博客系统中“用户管理”模块的核心契约，支撑用户相关的所有业务逻辑。
 * Created by BlueT on 2017/3/3.
 */
public interface IUserService {

    /**
     * 保存（注册）用户数据
     * 处理用户注册逻辑，包括校验用户名/邮箱唯一性、对密码进行加密（通常结合用户名加盐）、
     * 保存用户基本信息（用户名、密码、邮箱等）到数据库，返回生成的用户主键ID。
     * @param userVo 待注册的用户对象，包含用户名、原始密码、邮箱等核心信息
     * @return 注册成功后生成的用户主键ID（uid）
     */
    Integer insertUser(UserVo userVo);

    /**
     * 根据用户ID查询用户详情
     * 通过用户唯一标识（uid）查询用户的完整信息，包括用户名、加密后的密码、邮箱、创建时间等，
     * 用于用户信息展示、权限验证等场景。
     * @param uid 用户主键ID（唯一标识）
     * @return 对应的用户对象（UserVo），若ID不存在或无效则返回null
     */
    UserVo queryUserById(Integer uid);

    /**
     * 用户登录验证
     * 处理用户登录逻辑：校验用户名和密码非空性，查询用户是否存在，对输入密码按注册时的规则加密后与数据库中密码比对，
     * 验证通过则返回用户信息，失败则抛出异常（如用户不存在、密码错误等）。
     * @param username 登录用户名
     * @param password 登录密码（明文）
     * @return 登录成功的用户对象（包含用户ID、用户名等信息）
     * @throws 异常 当用户名不存在、密码错误或参数不合法时抛出
     */
    UserVo login(String username, String password);

    /**
     * 根据用户ID更新用户信息
     * 用于修改用户已有信息（如密码、邮箱、昵称等），仅更新用户对象中非空的字段，需通过用户ID（uid）定位数据，
     * 确保更新操作的准确性和安全性。
     * @param userVo 包含更新信息的用户对象，必须包含有效的用户ID（uid）
     */
    void updateByUid(UserVo userVo);
}