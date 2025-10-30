package com.my.blog.website.dao;

import com.my.blog.website.modal.Vo.UserVo;
import com.my.blog.website.modal.Vo.UserVoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/** 博客用户管理接口 */
@Component
public interface UserVoMapper {
    /** 获取用户数量 */
    long countByExample(UserVoExample example);

    /** 批量删除用户 */
    int deleteByExample(UserVoExample example);

    /** 删除指定用户 */
    int deleteByPrimaryKey(Integer uid);

    /** 添加用户 */
    int insert(UserVo record);

    /** 添加用户（可选字段） */
    int insertSelective(UserVo record);

    /** 查询用户列表 */
    List<UserVo> selectByExample(UserVoExample example);

    /** 获取指定用户 */
    UserVo selectByPrimaryKey(Integer uid);

    /** 按条件更新用户（可选字段） */
    int updateByExampleSelective(@Param("record") UserVo record, @Param("example") UserVoExample example);

    /** 按条件更新用户（全部字段） */
    int updateByExample(@Param("record") UserVo record, @Param("example") UserVoExample example);

    /** 更新指定用户（可选字段） */
    int updateByPrimaryKeySelective(UserVo record);

    /** 更新指定用户（全部字段） */
    int updateByPrimaryKey(UserVo record);
}