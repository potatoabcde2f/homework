package com.my.blog.website;

import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.Vo.UserVo;
import com.my.blog.website.service.IUserService;
import com.my.blog.website.service.IOptionService;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 数据库事务测试类
 * 测试Spring声明式事务管理的功能，验证事务的原子性和回滚机制
 * 通过模拟用户和选项数据的插入操作，测试在异常情况下事务是否能正确回滚
 */
@MapperScan("com.my.blog.website.dao")          // 扫描MyBatis映射器接口
@RunWith(SpringRunner.class)                    // 使用Spring测试运行器
@SpringBootTest                                 // Spring Boot测试环境
@Transactional(rollbackFor = TipException.class) // 声明事务，指定在TipException异常时回滚
public class TranscationTest {

    @Resource
    private IUserService userService;           // 用户服务接口，用于操作用户数据

    @Resource
    private IOptionService optionService;       // 选项服务接口，用于操作系统配置选项

    /**
     * 事务测试方法
     * 测试同时插入用户数据和选项数据的事务行为
     * 如果其中任何一个操作失败，整个事务应该回滚，保证数据的一致性
     */
    @org.junit.Test
    @Ignore                                     // 忽略此测试用例
    public void test() {
        // 创建用户对象并设置属性
        UserVo user = new UserVo();
        user.setUsername("wangqiang111");       // 设置用户名
        user.setPassword("123456");             // 设置密码
        user.setEmail("8888");                  // 设置邮箱

        // 插入用户数据到数据库
        userService.insertUser(user);

        // 插入系统选项数据到数据库
        // 如果这个操作失败，前面的用户插入操作应该回滚
        optionService.insertOption("site_keywords", "qwqwq");
    }
}