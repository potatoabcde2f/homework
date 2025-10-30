package com.my.blog.website.service;

import com.my.blog.website.modal.Vo.OptionVo;

import java.util.List;
import java.util.Map;

/**
 * 系统配置项服务核心接口
 * 定义系统配置项（如网站名称、描述、版权信息等全局参数）的增删改查规范，
 * 支撑系统基础配置的管理与加载，是博客系统全局参数设置的核心逻辑入口
 * Created by BlueT on 2017/3/7.
 */
public interface IOptionService {

    /**
     * 插入配置项（通过完整配置对象）
     * 直接接收封装好的OptionVo对象，将配置项的名称、值等信息保存到数据库
     * @param optionVo 配置项对象，包含配置名称（name）、配置值（value）等核心字段
     */
    void insertOption(OptionVo optionVo);

    /**
     * 插入或更新配置项（通过名称和值）
     * 接收配置项的名称和值，若该配置已存在则更新其值，若不存在则创建新配置，
     * 简化单个配置项的新增或修改操作
     * @param name 配置项名称（唯一标识，如"site_title"表示网站标题）
     * @param value 配置项值（如"我的博客"）
     */
    void insertOption(String name, String value);

    /**
     * 获取所有系统配置项
     * 查询数据库中所有配置项并返回列表，用于系统启动时加载全局配置或后台配置管理页展示
     * @return 所有配置项的列表，包含系统所有全局参数
     */
    List<OptionVo> getOptions();


    /**
     * 批量保存配置项
     * 接收配置项键值对Map，遍历Map中的每个配置项，调用单条插入/更新方法批量处理，
     * 适用于后台配置页提交多个配置项的场景（如一次性修改网站名称、描述、版权等）
     * @param options 配置项键值对Map（key为配置名称，value为配置值）
     */
    void saveOptions(Map<String, String> options);
}