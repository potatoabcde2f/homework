package com.my.blog.website.dao;

import com.my.blog.website.modal.Vo.CommentVo;
import com.my.blog.website.modal.Vo.CommentVoExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
// 评论数据访问接口（含BLOBs相关方法）
public interface CommentVoMapper {
    /**
     * 根据条件统计记录数量。
     * @param example 条件构造器
     * @return 记录总数
     */
    long countByExample(CommentVoExample example);

    /**
     * 根据条件批量删除。
     * @param example 条件构造器
     * @return 受影响行数
     */
    int deleteByExample(CommentVoExample example);

    /**
     * 根据主键删除记录。
     * @param coid 评论主键ID
     * @return 受影响行数
     */
    int deleteByPrimaryKey(Integer coid);

    /**
     * 插入完整记录（所有字段）。
     * @param record 评论实体
     * @return 受影响行数
     */
    int insert(CommentVo record);

    /**
     * 选择性插入（仅非空字段）。
     * @param record 评论实体
     * @return 受影响行数
     */
    int insertSelective(CommentVo record);

    /**
     * 根据条件查询列表（包含BLOB字段，如内容等）。
     * @param example 条件构造器
     * @return 评论列表
     */
    List<CommentVo> selectByExampleWithBLOBs(CommentVoExample example);

    /**
     * 根据条件查询列表（不含BLOB字段）。
     * @param example 条件构造器
     * @return 评论列表
     */
    List<CommentVo> selectByExample(CommentVoExample example);

    /**
     * 根据主键查询记录。
     * @param coid 评论主键ID
     * @return 评论实体，未找到返回null
     */
    CommentVo selectByPrimaryKey(Integer coid);

    /**
     * 根据条件选择性更新（仅非空字段）。
     * @param record 要更新的数据
     * @param example 条件构造器
     * @return 受影响行数
     */
    int updateByExampleSelective(@Param("record") CommentVo record, @Param("example") CommentVoExample example);

    /**
     * 根据条件全量更新（包含BLOB字段）。
     * @param record 要更新的数据
     * @param example 条件构造器
     * @return 受影响行数
     */
    int updateByExampleWithBLOBs(@Param("record") CommentVo record, @Param("example") CommentVoExample example);

    /**
     * 根据条件全量更新（不含BLOB字段）。
     * @param record 要更新的数据
     * @param example 条件构造器
     * @return 受影响行数
     */
    int updateByExample(@Param("record") CommentVo record, @Param("example") CommentVoExample example);

    /**
     * 根据主键选择性更新（仅非空字段）。
     * @param record 要更新的数据
     * @return 受影响行数
     */
    int updateByPrimaryKeySelective(CommentVo record);

    /**
     * 根据主键全量更新（包含BLOB字段）。
     * @param record 要更新的数据
     * @return 受影响行数
     */
    int updateByPrimaryKeyWithBLOBs(CommentVo record);

    /**
     * 根据主键全量更新（不含BLOB字段）。
     * @param record 要更新的数据
     * @return 受影响行数
     */
    int updateByPrimaryKey(CommentVo record);
}