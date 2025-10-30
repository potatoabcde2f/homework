package com.my.blog.website.service;

import com.my.blog.website.modal.Vo.RelationshipVoKey;

import java.util.List;

/**
 * 关联关系服务接口
 * 专门处理文章（cid）与元数据（mid，如分类、标签等）之间的关联关系，提供完整的关联管理能力，
 * 包括关联的创建、删除、查询和数量统计，支持灵活的单条件或组合条件操作，是连接内容与元数据的核心桥梁。
 * Created by BlueT on 2017/3/18.
 */
public interface IRelationshipService {
    /**
     * 按文章ID和元数据ID删除关联关系
     * 支持三种删除场景：
     * 1. 仅传cid：删除某篇文章的所有关联（如删除某文章的所有标签和分类）；
     * 2. 仅传mid：删除某元数据的所有关联（如删除某标签关联的所有文章）；
     * 3. 同时传cid和mid：删除某文章与某元数据的特定关联（如移除某文章的某个标签）。
     * @param cid 文章ID（可为null，为null时不按文章筛选）
     * @param mid 元数据ID（可为null，为null时不按元数据筛选）
     */
    void deleteById(Integer cid, Integer mid);

    /**
     * 按文章ID和元数据ID统计关联关系数量
     * 支持三种统计场景：
     * 1. 仅传cid：统计某篇文章的关联总数（如某文章有多少个标签）；
     * 2. 仅传mid：统计某元数据的关联总数（如某标签关联了多少篇文章）；
     * 3. 同时传cid和mid：判断某文章与某元数据是否已关联（返回0表示未关联，1表示已关联）。
     * @param cid 文章ID（可为null，为null时不按文章筛选）
     * @param mid 元数据ID（可为null，为null时不按元数据筛选）
     * @return 符合条件的关联关系总条数（长整型）
     */
    Long countById(Integer cid, Integer mid);


    /**
     * 保存文章与元数据的关联关系
     * 用于建立文章和元数据（分类/标签）的关联映射，需确保关联的文章ID（cid）和元数据ID（mid）有效，
     * 通常在文章发布或编辑时调用，用于绑定文章与对应的分类/标签。
     * @param relationshipVoKey 关联关系对象，必须包含有效的cid（文章ID）和mid（元数据ID）
     */
    void insertVo(RelationshipVoKey relationshipVoKey);

    /**
     * 按文章ID和元数据ID查询关联关系列表
     * 支持三种查询场景：
     * 1. 仅传cid：查询某篇文章的所有关联（如获取某文章的所有标签ID）；
     * 2. 仅传mid：查询某元数据的所有关联（如获取某标签关联的所有文章ID）；
     * 3. 同时传cid和mid：查询某文章与某元数据的特定关联（通常用于验证是否存在关联）。
     * @param cid 文章ID（可为null，为null时不按文章筛选）
     * @param mid 元数据ID（可为null，为null时不按元数据筛选）
     * @return 符合条件的关联关系列表（每个元素包含一对cid和mid），无匹配时返回空列表
     */
    List<RelationshipVoKey> getRelationshipById(Integer cid, Integer mid);
}