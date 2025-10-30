package com.my.blog.website.service;

import com.my.blog.website.dto.MetaDto;
import com.my.blog.website.modal.Bo.ArchiveBo;
import com.my.blog.website.modal.Bo.BackResponseBo;
import com.my.blog.website.modal.Bo.StatisticsBo;
import com.my.blog.website.modal.Vo.CommentVo;
import com.my.blog.website.modal.Vo.ContentVo;

import java.util.List;

/**
 * 网站核心服务接口
 * 提供网站全局功能的统一入口，包括最新动态查询（评论、文章）、数据统计、备份、归档、元数据列表等，
 * 是连接前台展示与后台管理的核心服务，支撑网站整体运行的基础功能。
 * Created by 13 on 2017/2/23.
 */
public interface ISiteService {


    /**
     * 获取最新收到的评论列表
     * 按评论创建时间降序排列，返回指定数量的最新评论，用于网站首页或侧边栏展示近期互动
     * @param limit 最大返回数量（通常用于限制展示条数，如侧边栏显示5条最新评论）
     * @return 最新评论列表（包含评论内容、作者、关联文章等信息）
     */
    List<CommentVo> recentComments(int limit);

    /**
     * 获取最新发表的文章列表
     * 按文章创建时间降序排列，返回指定数量的最新发布文章，用于网站首页或侧边栏展示近期内容
     * @param limit 最大返回数量（如首页显示10条最新文章）
     * @return 最新文章列表（包含文章标题、摘要、发布时间等信息）
     */
    List<ContentVo> recentContents(int limit);

    /**
     * 根据评论ID查询单条评论详情
     * 用于获取指定评论的完整信息（如评论内容、作者、关联文章、创建时间等），支撑评论管理或详情展示
     * @param coid 评论ID（唯一标识一条评论）
     * @return 评论详情对象，若ID不存在则返回null
     */
    CommentVo getComment(Integer coid);

    /**
     * 执行系统数据备份
     * 支持不同类型的备份（如附件/主题备份、数据库备份），生成备份文件并返回备份结果信息
     * @param bk_type 备份类型（如"attach"表示备份附件和主题，"db"表示备份数据库）
     * @param bk_path 备份文件存储路径（附件备份时需指定，数据库备份可使用默认路径）
     * @param fmt 备份文件名中的日期格式（如"yyyyMMdd"，用于生成带时间戳的备份文件名）
     * @return 备份响应对象，包含备份文件路径等结果信息
     * @throws Exception 备份过程中发生错误时抛出（如路径不存在、权限不足等）
     */
    BackResponseBo backup(String bk_type, String bk_path, String fmt) throws Exception;


    /**
     * 获取网站后台统计数据
     * 汇总网站核心数据指标，用于后台管理页展示网站运行状态
     * @return 统计数据对象，包含文章总数、评论总数、附件总数、链接总数等核心指标
     */
    StatisticsBo getStatistics();

    /**
     * 查询文章归档列表
     * 按月份分组汇总已发布文章，每个月份包含该月发布的所有文章，用于网站归档页展示历史内容
     * @return 归档列表（每个元素包含月份和对应月份的文章列表）
     */
    List<ArchiveBo> getArchives();

    /**
     * 获取分类或标签列表（含关联文章数）
     * 按指定类型、排序方式和数量限制查询元数据，返回包含关联文章数的扩展信息，支撑分类页、标签云等展示
     * @param type 元数据类型（如"category"表示分类，"tag"表示标签）
     * @param orderBy 排序方式（如"count desc"表示按关联文章数降序）
     * @param limit 最大返回数量（如限制显示前20个热门标签）
     * @return 元数据DTO列表（包含名称、关联文章数等信息）
     */
    List<MetaDto> metas(String type, String orderBy, int limit);

}