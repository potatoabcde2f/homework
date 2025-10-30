package com.my.blog.website.dao;

import com.my.blog.website.dto.MetaDto;
import com.my.blog.website.modal.Vo.MetaVo;
import com.my.blog.website.modal.Vo.MetaVoExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/** 博客分类与标签管理接口 */
@Component
public interface MetaVoMapper {

    /** 获取分类/标签数量 */
    long countByExample(MetaVoExample example);

    /** 批量删除分类/标签 */
    int deleteByExample(MetaVoExample example);

    /** 删除指定分类/标签 */
    int deleteByPrimaryKey(Integer mid);

    /** 添加分类/标签 */
    int insert(MetaVo record);

    /** 添加分类/标签（可选字段） */
    int insertSelective(MetaVo record);

    /** 查询分类/标签列表 */
    List<MetaVo> selectByExample(MetaVoExample example);

    /** 获取指定分类/标签 */
    MetaVo selectByPrimaryKey(Integer mid);

    /** 按条件更新分类/标签（可选字段） */
    int updateByExampleSelective(@Param("record") MetaVo record, @Param("example") MetaVoExample example);

    /** 按条件更新分类/标签（全部字段） */
    int updateByExample(@Param("record") MetaVo record, @Param("example") MetaVoExample example);

    /** 更新指定分类/标签（可选字段） */
    int updateByPrimaryKeySelective(MetaVo record);

    /** 更新指定分类/标签（全部字段） */
    int updateByPrimaryKey(MetaVo record);

    /** 获取分类/标签详情列表 */
    List<MetaDto> selectFromSql(Map<String,Object> paraMap);

    /** 按名称和类型查找分类/标签 */
    MetaDto selectDtoByNameAndType(@Param("name") String name,@Param("type") String type);

    /** 获取分类/标签的文章数 */
    Integer countWithSql(Integer mid);


}