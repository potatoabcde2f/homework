package com.my.blog.website.service;

import com.github.pagehelper.PageInfo;
import com.my.blog.website.modal.Vo.ContentVoExample;
import com.my.blog.website.modal.Vo.ContentVo;

/**
 * 文章内容服务核心接口
 * 定义文章全生命周期的业务操作规范，包括文章发布、多维度查询、更新、删除、分类调整等，
 * 是博客系统中“文章管理”模块的核心逻辑入口，具体实现依赖数据访问层与关联服务（如元数据服务）
 * Created by Administrator on 2017/3/13 013.
 */
public interface IContentService {

    /**
     * 发布新文章
     * 功能：校验文章合法性（标题、内容长度、作者登录状态等）、处理表情符号、设置创建/修改时间与初始数据（阅读量0、评论数0），
     * 保存文章到数据库后，自动关联文章的标签（tag）和分类（category）
     * @param contents 待发布的文章对象，需包含标题、内容、作者ID、标签、分类等核心信息
     */
    void publish(ContentVo contents);

    /**
     * 分页查询已发布的文章列表
     * 功能：查询“类型为文章”且“状态为已发布”的内容，按创建时间降序排列，支持分页控制
     * @param p 当前页码（从1开始）
     * @param limit 每页展示的文章数量
     * @return 分页后的文章列表（含分页参数，如总条数、总页数）
     */
    PageInfo<ContentVo> getContents(Integer p, Integer limit);


    /**
     * 按ID或路径（slug）获取单篇文章详情
     * 功能：支持两种查询方式——1. 数字ID（直接查主键）；2. 字符串路径（slug，查自定义访问路径），
     * 按ID查询时会自动将文章阅读量+1并更新到数据库
     * @param id 文章标识（可为数字ID，也可为字符串slug）
     * @return 单篇文章的完整信息（含内容、作者、阅读量、评论数等），无匹配时返回null
     */
    ContentVo getContents(String id);

    /**
     * 按文章ID选择性更新文章信息
     * 功能：仅更新文章对象中“非空”的字段（如只更新评论数、或只更新标题），用于局部数据调整（如文章评论数增减）
     * @param contentVo 含更新信息的文章对象，必须包含文章ID（cid）以定位数据
     */
    void updateContentByCid(ContentVo contentVo);


    /**
     * 按分类/标签ID分页查询文章（归档场景）
     * 功能：查询指定分类或标签（元数据ID为mid）关联的已发布文章，支持分页，用于分类页、标签页的文章展示
     * @param mid 分类/标签的元数据ID（区分分类和标签由调用方保证）
     * @param page 当前页码
     * @param limit 每页展示的文章数量
     * @return 分页后的文章列表
     */
    PageInfo<ContentVo> getArticles(Integer mid, int page, int limit);

    /**
     * 按关键词分页搜索已发布文章
     * 功能：按文章标题模糊匹配关键词，查询“类型为文章”且“状态为已发布”的内容，按创建时间降序排列
     * @param keyword 搜索关键词（匹配文章标题）
     * @param page 当前页码
     * @param limit 每页展示的文章数量
     * @return 分页后的搜索结果列表
     */
    PageInfo<ContentVo> getArticles(String keyword,Integer page,Integer limit);


    /**
     * 按自定义条件分页查询文章
     * 功能：支持通过ContentVoExample构建灵活查询条件（如按创建时间范围、作者ID、文章状态等筛选），
     * 适用于复杂场景的文章查询（如后台管理系统的文章列表筛选）
     * @param commentVoExample 文章查询条件对象（可设置多维度筛选规则）
     * @param page 当前页码
     * @param limit 每页展示的文章数量
     * @return 分页后的文章列表
     */
    PageInfo<ContentVo> getArticlesWithpage(ContentVoExample commentVoExample, Integer page, Integer limit);

    /**
     * 按文章ID删除文章
     * 功能：删除指定ID的文章，同时自动删除文章与标签/分类的关联关系，保证数据一致性
     * @param cid 待删除文章的ID
     */
    void deleteByCid(Integer cid);

    /**
     * 编辑（更新）已存在的文章
     * 功能：校验文章合法性（同发布逻辑）、更新文章修改时间与内容（含表情处理），
     * 删除文章原有的标签/分类关联，重新关联新的标签和分类，完成文章全量更新
     * @param contents 含更新信息的文章对象，必须包含文章ID（cid）
     */
    void updateArticle(ContentVo contents);


    /**
     * 批量更新文章的分类
     * 功能：将所有“原分类为ordinal”的文章，统一更新为“新分类newCatefory”，用于分类重命名后的批量数据同步
     * @param ordinal 原分类名称
     * @param newCatefory 新分类名称
     */
    void updateCategory(String ordinal,String newCatefory);
}