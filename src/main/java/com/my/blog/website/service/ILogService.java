package com.my.blog.website.service;

import com.my.blog.website.modal.Vo.LogVo;

import java.util.List;

/**
 * 系统日志服务核心接口
 * 定义系统操作日志的保存与分页查询规范，用于记录用户或系统的关键操作（如登录、发布文章、删除数据等），
 * 支撑日志管理、操作溯源、问题排查等场景，是系统运维与安全审计的重要支撑
 * Created by BlueT on 2017/3/4.
 */
public interface ILogService {

    /**
     * 保存系统日志（通过完整日志对象）
     * 直接接收封装好的LogVo对象，将日志信息（如动作、数据、IP、创建时间、操作人ID等）插入数据库
     * @param logVo 完整的日志对象，需包含日志记录所需的核心字段（如action、data、ip、authorId、created等）
     */
    void insertLog(LogVo logVo);

    /**
     * 保存系统日志（通过零散参数构建）
     * 接收日志的关键零散参数，自动构建LogVo对象并补全创建时间（当前时间戳），再执行保存操作，
     * 简化日志记录时的参数传递，适用于直接在业务代码中埋点记录日志的场景
     * @param action 操作动作描述（如"用户登录"、"发布文章"、"删除评论"）
     * @param data 操作相关数据（如请求参数、操作结果、数据ID等，用于溯源）
     * @param ip 操作来源IP地址（记录操作的设备网络地址）
     * @param authorId 操作人ID（可为null，如系统自动执行的操作可传null）
     */
    void insertLog(String action, String data, String ip, Integer authorId);

    /**
     * 分页获取系统日志列表
     * 按日志ID降序（最新日志在前）返回分页数据，同时对非法的页码和每页条数进行自动调整（保证查询有效性）
     * @param page 当前页码（若小于等于0，自动调整为1）
     * @param limit 每页条数（若小于1或大于系统配置的最大条数，自动调整为默认值10）
     * @return 分页后的日志列表，包含符合条件的所有日志数据
     */
    List<LogVo> getLogs(int page,int limit);
}