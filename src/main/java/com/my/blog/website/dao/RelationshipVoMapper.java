package com.my.blog.website.dao;

import com.my.blog.website.modal.Vo.RelationshipVoExample;
import com.my.blog.website.modal.Vo.RelationshipVoKey;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/** 文章与分类/标签的关联关系管理接口 */
@Component
public interface RelationshipVoMapper {
    
    /**
     * 统计符合条件的关系记录数量
     * @param example 查询条件对象，支持组合查询条件
     * @return 满足条件的记录总数
     */
    long countByExample(RelationshipVoExample example);

    /** 批量删除关联关系 */
    int deleteByExample(RelationshipVoExample example);

    /** 删除指定关联关系 */
    int deleteByPrimaryKey(RelationshipVoKey key);

    /** 添加关联关系 */
    int insert(RelationshipVoKey record);

    /** 添加关联关系（可选字段） */
    int insertSelective(RelationshipVoKey record);

    /** 查询关联关系列表 */
    List<RelationshipVoKey> selectByExample(RelationshipVoExample example);

    /** 按条件更新关联关系（可选字段） */
    int updateByExampleSelective(@Param("record") RelationshipVoKey record, 
                                @Param("example") RelationshipVoExample example);

    /** 按条件更新关联关系（全部字段） */
    int updateByExample(@Param("record") RelationshipVoKey record, 
                       @Param("example") RelationshipVoExample example);
}