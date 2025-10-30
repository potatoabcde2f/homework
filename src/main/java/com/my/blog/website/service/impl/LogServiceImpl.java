package com.my.blog.website.service.impl;

import com.github.pagehelper.PageHelper;
import com.my.blog.website.dao.LogVoMapper;
import com.my.blog.website.service.ILogService;
import com.my.blog.website.utils.DateKit;
import com.my.blog.website.constant.WebConst;
import com.my.blog.website.modal.Vo.LogVo;
import com.my.blog.website.modal.Vo.LogVoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 日志服务实现类
 * 负责处理系统日志的插入、分页查询等操作，依赖日志数据访问层与数据库交互
 * Created by BlueT on 2017/3/4.
 */
@Service
public class LogServiceImpl implements ILogService {

    // 日志记录器，用于记录当前类的运行日志
    private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);

    // 日志数据访问对象，用于与数据库交互执行日志相关的CRUD操作
    @Resource
    private LogVoMapper logDao;

    /**
     * 插入日志（直接使用LogVo对象）
     * 将传入的日志对象直接插入到数据库
     * @param logVo 日志对象，包含日志相关信息（动作、数据、IP、创建时间等）
     */
    @Override
    public void insertLog(LogVo logVo) {
        logDao.insert(logVo);
    }

    /**
     * 插入日志（通过参数构建LogVo对象）
     * 根据传入的日志详情参数（动作、数据、IP、作者ID）构建日志对象，设置创建时间后插入数据库
     * @param action 日志动作描述（如"登录"、"发布文章"等）
     * @param data 日志相关数据（如操作详情、请求参数等）
     * @param ip 操作IP地址
     * @param authorId 操作人ID（可为null，如匿名操作）
     */
    @Override
    public void insertLog(String action, String data, String ip, Integer authorId) {
        LogVo logs = new LogVo();
        logs.setAction(action); // 设置动作描述
        logs.setData(data);     // 设置相关数据
        logs.setIp(ip);         // 设置IP地址
        logs.setAuthorId(authorId); // 设置操作人ID
        logs.setCreated(DateKit.getCurrentUnixTime()); // 设置创建时间（当前时间戳）
        logDao.insert(logs);    // 插入数据库
    }

    /**
     * 分页查询日志列表
     * 对页码和每页条数进行合法性校验，按ID降序（最新在前）分页查询日志
     * @param page 页码（若小于等于0则默认取1）
     * @param limit 每页条数（若不合法则默认取10，最大不超过系统配置的最大条数）
     * @return 分页后的日志列表
     */
    @Override
    public List<LogVo> getLogs(int page, int limit) {
        LOGGER.debug("Enter getLogs method:page={},linit={}",page,limit);
        // 校验页码，小于等于0则设为1
        if (page <= 0) {
            page = 1;
        }
        // 校验每页条数，不合法则设为默认值10（最大不超过所设置的最大值WebConst.MAX_POSTS）
        if (limit < 1 || limit > WebConst.MAX_POSTS) {
            limit = 10;
        }
        // 构建查询条件：按ID降序排列
        LogVoExample logVoExample = new LogVoExample();
        logVoExample.setOrderByClause("id desc");
        // 开启分页（PageHelper分页参数为起始位置和每页条数，这里起始位置=（页码-1）*每页的条数）
        PageHelper.startPage((page - 1) * limit, limit);
        // 查询符合条件的日志列表
        List<LogVo> logVos = logDao.selectByExample(logVoExample);
        LOGGER.debug("Exit getLogs method");
        return logVos;
    }
}