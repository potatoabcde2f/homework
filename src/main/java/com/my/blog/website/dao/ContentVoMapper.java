package com.my.blog.website.dao;

import com.my.blog.website.modal.Bo.ArchiveBo;
import com.my.blog.website.modal.Vo.ContentVo;
import com.my.blog.website.modal.Vo.ContentVoExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
// 内容（文章/页面）数据访问接口
public interface ContentVoMapper {
    /**
     * 根据条件统计记录数量。
     * @param example 条件构造器
     * @return 记录总数
     */
    long countByExample(ContentVoExample example);

    /**
     * 根据条件批量删除。
     * @param example 条件构造器
     * @return 受影响行数
     */
    int deleteByExample(ContentVoExample example);

    /**
     * 根据主键删除记录。
     * @param cid 主键ID
     * @return 受影响行数
     */
    int deleteByPrimaryKey(Integer cid);

    /**
     * 插入完整记录（所有字段）。
     * @param record 内容实体
     * @return 受影响行数
     */
    int insert(ContentVo record);

    /**
     * 选择性插入（仅非空字段）。
     * @param record 内容实体
     * @return 受影响行数
     */
    int insertSelective(ContentVo record);

    /**
     * 根据条件查询列表（包含BLOB字段，如正文）。
     * @param example 条件构造器
     * @return 内容列表
     */
    List<ContentVo> selectByExampleWithBLOBs(ContentVoExample example);

    /**
     * 根据条件查询列表（不含BLOB字段）。
     * @param example 条件构造器
     * @return 内容列表
     */
    List<ContentVo> selectByExample(ContentVoExample example);

    /**
     * 根据主键查询记录。
     * @param cid 主键ID
     * @return 内容实体，未找到返回null
     */
    ContentVo selectByPrimaryKey(Integer cid);

    /**
     * 根据条件选择性更新（仅非空字段）。
     * @param record 要更新的数据
     * @param example 条件构造器
     * @return 受影响行数
     */
    int updateByExampleSelective(@Param("record") ContentVo record, @Param("example") ContentVoExample example);

    /**
     * 根据条件全量更新（包含BLOB字段）。
     * @param record 要更新的数据
     * @param example 条件构造器
     * @return 受影响行数
     */
    int updateByExampleWithBLOBs(@Param("record") ContentVo record, @Param("example") ContentVoExample example);

    /**
     * 根据条件全量更新（不含BLOB字段）。
     * @param record 要更新的数据
     * @param example 条件构造器
     * @return 受影响行数
     */
    int updateByExample(@Param("record") ContentVo record, @Param("example") ContentVoExample example);

    /**
     * 根据主键选择性更新（仅非空字段）。
     * @param record 要更新的数据
     * @return 受影响行数
     */
    int updateByPrimaryKeySelective(ContentVo record);

    /**
     * 根据主键全量更新（包含BLOB字段）。
     * @param record 要更新的数据
     * @return 受影响行数
     */
    int updateByPrimaryKeyWithBLOBs(ContentVo record);

    /**
     * 根据主键全量更新（不含BLOB字段）。
     * @param record 要更新的数据
     * @return 受影响行数
     */
    int updateByPrimaryKey(ContentVo record);

    /**
     * 查询归档数据（按日期聚合的文章列表等）。
     * @return 归档业务对象列表
     */
    // 查询归档数据（按日期聚合）
    List<ArchiveBo> findReturnArchiveBo();

    /**
     * 根据分类/标签ID查询文章列表。
     * @param mid 分类或标签的主键ID
     * @return 内容列表
     */
    // 根据分类/标签ID查询文章
    List<ContentVo> findByCatalog(Integer mid);
}