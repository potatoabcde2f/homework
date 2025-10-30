package com.my.blog.website.dao;

import com.my.blog.website.modal.Vo.AttachVo;
import com.my.blog.website.modal.Vo.AttachVoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
// 附件数据访问接口
public interface AttachVoMapper {
    /**
     * 根据条件统计记录数量。
     * @param example 条件构造器
     * @return 记录总数
     */
    long countByExample(AttachVoExample example);

    /**
     * 根据条件批量删除记录。
     * @param example 条件构造器
     * @return 受影响行数
     */
    int deleteByExample(AttachVoExample example);

    /**
     * 根据主键删除记录。
     * @param id 主键ID
     * @return 受影响行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入完整记录（所有字段）。
     * @param record 待插入实体
     * @return 受影响行数
     */
    int insert(AttachVo record);

    /**
     * 选择性插入（仅非空字段）。
     * @param record 待插入实体
     * @return 受影响行数
     */
    int insertSelective(AttachVo record);

    /**
     * 根据条件查询列表。
     * @param example 条件构造器
     * @return 符合条件的附件列表
     */
    List<AttachVo> selectByExample(AttachVoExample example);

    /**
     * 根据主键查询记录。
     * @param id 主键ID
     * @return 附件实体，未找到返回null
     */
    AttachVo selectByPrimaryKey(Integer id);

    /**
     * 根据条件选择性更新（仅更新非空字段）。
     * @param record 要更新的数据
     * @param example 条件构造器
     * @return 受影响行数
     */
    int updateByExampleSelective(@Param("record") AttachVo record, @Param("example") AttachVoExample example);

    /**
     * 根据条件全量更新（所有字段）。
     * @param record 要更新的数据
     * @param example 条件构造器
     * @return 受影响行数
     */
    int updateByExample(@Param("record") AttachVo record, @Param("example") AttachVoExample example);

    /**
     * 根据主键选择性更新（仅更新非空字段）。
     * @param record 要更新的数据
     * @return 受影响行数
     */
    int updateByPrimaryKeySelective(AttachVo record);

    /**
     * 根据主键全量更新（所有字段）。
     * @param record 要更新的数据
     * @return 受影响行数
     */
    int updateByPrimaryKey(AttachVo record);
}