package com.my.blog.website.dao;

import com.my.blog.website.modal.Vo.OptionVo;
import com.my.blog.website.modal.Vo.OptionVoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/** 系统配置访问接口，管理键值对形式的配置项 */
@Component
public interface OptionVoMapper {
    /** 获取配置项数量 */
    long countByExample(OptionVoExample example);

    /** 批量删除配置 */
    int deleteByExample(OptionVoExample example);

    /** 删除指定配置 */
    int deleteByPrimaryKey(String name);

    /** 添加配置 */
    int insert(OptionVo record);

    /** 添加配置（可选字段） */
    int insertSelective(OptionVo record);

    /** 查询配置列表 */
    List<OptionVo> selectByExample(OptionVoExample example);

    /** 获取配置 */
    OptionVo selectByPrimaryKey(String name);

    /** 按条件更新配置（可选字段） */
    int updateByExampleSelective(@Param("record") OptionVo record, @Param("example") OptionVoExample example);

    /** 按条件更新配置（全部字段） */
    int updateByExample(@Param("record") OptionVo record, @Param("example") OptionVoExample example);

    /** 更新指定配置（可选字段） */
    int updateByPrimaryKeySelective(OptionVo record);

    /** 更新指定配置（全部字段） */
    int updateByPrimaryKey(OptionVo record);

    /** 批量添加配置 */
    int insertOptions(List<OptionVo> optionVos);
}