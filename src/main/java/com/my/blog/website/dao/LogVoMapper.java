package com.my.blog.website.dao;

import com.my.blog.website.modal.Vo.LogVo;
import com.my.blog.website.modal.Vo.LogVoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/** 系统操作日志管理接口 */
@Component
public interface LogVoMapper {

    /** 获取日志数量 */
    long countByExample(LogVoExample example);

    /** 批量删除日志 */
    int deleteByExample(LogVoExample example);

    /** 删除指定日志 */
    int deleteByPrimaryKey(Integer id);

    /** 记录日志 */
    int insert(LogVo record);

    /** 记录日志（可选字段） */
    int insertSelective(LogVo record);

    /** 查询日志列表 */
    List<LogVo> selectByExample(LogVoExample example);

    /** 获取指定日志 */
    LogVo selectByPrimaryKey(Integer id);

    /** 按条件更新日志（可选字段） */
    int updateByExampleSelective(@Param("record") LogVo record, @Param("example") LogVoExample example);

    /** 按条件更新日志 */
    int updateByExample(@Param("record") LogVo record, @Param("example") LogVoExample example);

    /** 更新日志（可选字段） */
    int updateByPrimaryKeySelective(LogVo record);

    /** 更新日志 */
    int updateByPrimaryKey(LogVo record);


}