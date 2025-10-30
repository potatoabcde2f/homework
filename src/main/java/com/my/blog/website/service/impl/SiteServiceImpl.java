package com.my.blog.website.service.impl;

import com.my.blog.website.dao.UserVoMapper;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.Vo.UserVo;
import com.my.blog.website.service.IUserService;
import com.my.blog.website.utils.TaleUtils;
import com.my.blog.website.modal.Vo.UserVoExample;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户服务实现类
 * 负责处理用户的注册（插入）、查询、登录、信息更新等业务逻辑，依赖用户数据访问层与数据库交互
 * Created by BlueT on 2017/3/3.
 */
@Service
public class UserServiceImpl implements IUserService {
    // 日志记录器，用于记录当前类的运行日志
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    // 用户数据访问对象，用于与数据库交互执行用户相关的CRUD操作
    @Resource
    private UserVoMapper userDao;

    /**
     * 插入（注册）用户
     * 1. 校验用户名和邮箱非空
     * 2. 对密码进行加密（规则：用户名+密码拼接后MD5编码）
     * 3. 选择性插入用户信息（仅插入非空字段）
     * @param userVo 待注册的用户对象（含用户名、密码、邮箱等信息）
     * @return 注册成功后的用户ID
     */
    @Override
    public Integer insertUser(UserVo userVo) {
        Integer uid = null;
        // 校验用户名和邮箱非空
        if (StringUtils.isNotBlank(userVo.getUsername()) && StringUtils.isNotBlank(userVo.getEmail())) {
            // 按规则加密用户密码（用户名+原始密码的MD5值）
            String encodePwd = TaleUtils.MD5encode(userVo.getUsername() + userVo.getPassword());
            userVo.setPassword(encodePwd);
            // 选择性插入用户数据（非空字段才插入）
            userDao.insertSelective(userVo);
        }
        // 返回自增的用户ID
        return userVo.getUid();
    }

    /**
     * 根据用户ID查询用户信息
     * @param uid 用户ID
     * @return 用户对象，ID为空时返回null
     */
    @Override
    public UserVo queryUserById(Integer uid) {
        UserVo userVo = null;
        if (uid != null) {
            userVo = userDao.selectByPrimaryKey(uid);
        }
        return userVo;
    }

    /**
     * 用户登录验证
     * 1. 校验用户名和密码非空
     * 2. 检查用户名是否存在
     * 3. 按加密规则处理输入密码，与数据库中密码比对
     * 4. 校验登录结果，返回用户信息
     * @param username 用户名
     * @param password 登录密码（明文）
     * @return 登录成功的用户对象
     * @throws TipException 登录过程中参数不合法、用户不存在或密码错误时抛出
     */
    @Override
    public UserVo login(String username, String password) {
        // 校验用户名和密码非空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new TipException("用户名和密码不能为空");
        }
        UserVoExample example = new UserVoExample();
        UserVoExample.Criteria criteria = example.createCriteria();
        // 按用户名查询
        criteria.andUsernameEqualTo(username);
        long count = userDao.countByExample(example);
        // 检查用户名是否存在
        if (count < 1) {
            throw new TipException("不存在该用户");
        }
        // 按规则加密输入密码（与注册时加密规则一致）
        String pwd = TaleUtils.MD5encode(username + password);
        // 增加密码查询条件
        criteria.andPasswordEqualTo(pwd);
        List<UserVo> userVos = userDao.selectByExample(example);
        // 校验查询结果（正常应为1条，否则密码错误）
        if (userVos.size() != 1) {
            throw new TipException("用户名或密码错误");
        }
        return userVos.get(0);
    }

    /**
     * 根据用户ID更新用户信息
     * 1. 校验用户对象及用户ID非空
     * 2. 执行选择性更新（仅更新非空字段）
     * 3. 校验更新结果（需影响1条数据，否则抛出异常）
     * @param userVo 含更新信息的用户对象（需包含用户ID）
     * @throws TipException 用户对象/ID为空或更新结果异常时抛出
     */
    @Override
    public void updateByUid(UserVo userVo) {
        // 校验用户对象及ID非空
        if (null == userVo || null == userVo.getUid()) {
            throw new TipException("userVo is null");
        }
        // 选择性更新用户信息（非空字段才更新）
        int i = userDao.updateByPrimaryKeySelective(userVo);
        // 校验更新是否成功（需影响1条数据）
        if (i != 1) {
            throw new TipException("update user by uid and retrun is not one");
        }
    }
}