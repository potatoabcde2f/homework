package com.my.blog.website.service;

import com.my.blog.website.dto.MetaDto;
import com.my.blog.website.modal.Vo.MetaVo;

import java.util.List;

/**
 * 元数据服务核心接口
 * 定义系统中元数据（包括分类category、标签tag等）的CRUD、关联管理及统计操作规范，
 * 是博客系统中“分类管理”和“标签管理”功能的核心逻辑入口，支撑文章与元数据的关联关系维护
 * Created by BlueT on 2017/3/17.
 */
public interface IMetaService {

    /**
     * 根据类型和名称查询元数据详情
     * 用于精准查询某类元数据中指定名称的项（如查询名称为“技术”的分类，或名称为“Java”的标签），
     * 返回包含扩展信息的MetaDto（通常包含元数据基本信息及关联文章数）
     * @param type 元数据类型（如分类Types.CATEGORY、标签Types.TAG）
     * @param name 元数据名称（如分类名、标签名）
     * @return 元数据详情DTO，无匹配项时返回null
     */
    MetaDto getMeta(String type, String name);

    /**
     * 统计指定元数据关联的文章数量
     * 用于查询某分类或标签下关联的文章总数（如统计“技术”分类下有多少篇文章）
     * @param mid 元数据ID（分类或标签的唯一标识）
     * @return 关联的文章数量
     */
    Integer countMeta(Integer mid);

    /**
     * 根据类型查询元数据列表
     * 用于查询某类元数据的所有项（如查询所有分类、所有标签），返回基础元数据信息
     * @param types 元数据类型（如分类Types.CATEGORY、标签Types.TAG）
     * @return 元数据列表，无匹配项时返回null
     */
    List<MetaVo> getMetas(String types);


    /**
     * 为文章批量绑定元数据（分类或标签）
     * 用于文章发布或编辑时，关联多个元数据（如给一篇文章添加“技术”“Java”两个标签），
     * 自动处理元数据的创建（若不存在）及与文章的关联关系
     * @param cid 文章ID（被关联的文章）
     * @param names 元数据名称集合（逗号分隔，如“技术,Java”）
     * @param type 元数据类型（分类或标签，确保批量绑定的是同一类元数据）
     */
    void saveMetas(Integer cid, String names, String type);

    /**
     * 保存或更新单个元数据
     * 用于创建新元数据或修改已有元数据（通过mid区分：mid为null则创建，不为null则更新），
     * 确保同类型元数据名称唯一（避免重复创建）
     * @param type 元数据类型（分类或标签）
     * @param name 元数据名称
     * @param mid 元数据ID（更新时传入，创建时为null）
     */
    void saveMeta(String type, String name, Integer mid);

    /**
     * 按条件查询元数据列表（含关联文章数）
     * 用于查询指定类型的元数据，支持排序（如按文章数降序）和数量限制（如取前10个热门标签），
     * 返回包含关联文章数的DTO列表，适用于前台分类/标签云展示
     * @param type 元数据类型（分类或标签）
     * @param orderby 排序方式（如“count desc”表示按文章数降序）
     * @param limit 最大返回数量（限制查询结果条数）
     * @return 元数据DTO列表（含文章数），无匹配项时返回null
     */
    List<MetaDto> getMetaList(String type, String orderby, int limit);

    /**
     * 删除指定元数据
     * 用于删除某分类或标签，同时会自动处理关联关系：更新关联文章的元数据信息（移除该元数据），
     * 并删除文章与该元数据的关联记录，保证数据一致性
     * @param mid 元数据ID（待删除的分类或标签ID）
     */
    void delete(int mid);

    /**
     * 保存元数据（通过完整对象）
     * 直接接收MetaVo对象并保存到数据库，适用于需要设置更多字段（如排序值、路径等）的场景
     * @param metas 完整的元数据对象（需包含类型、名称等核心字段）
     */
    void saveMeta(MetaVo metas);

    /**
     * 更新元数据信息
     * 用于修改元数据的非主键字段（如名称、排序值等），需通过元数据ID定位数据
     * @param metas 含更新信息的元数据对象（必须包含元数据ID mid）
     */
    void update(MetaVo metas);
}