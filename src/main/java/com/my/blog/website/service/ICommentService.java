package com.my.blog.website.service;

import com.github.pagehelper.PageInfo;
import com.my.blog.website.modal.Vo.CommentVo;
import com.my.blog.website.modal.Vo.CommentVoExample;
import com.my.blog.website.modal.Bo.CommentBo;

/**
 * 评论服务核心接口
 * 定义评论的新增、查询、删除、更新等核心操作，支撑文章评论相关业务逻辑
 * Created by BlueT on 2017/3/16.
 */
public interface ICommentService {

    /**
     * 保存（新增）评论
     * 会先对评论信息进行合法性校验（如作者、邮箱格式、内容长度、关联文章存在性等），
     * 校验通过后插入数据库，并同步更新对应文章的评论数量
     * @param commentVo 待保存的评论对象，需包含评论内容、关联文章ID、作者等核心信息
     */
    void insertComment(CommentVo commentVo);

    /**
     * 按文章ID分页查询评论
     * 仅查询指定文章下的一级评论（非回复类评论），并将评论数据封装为CommentBo（含扩展信息）返回
     * @param cid 关联文章ID，用于筛选该文章下的评论
     * @param page 页码，指定查询第几页数据
     * @param limit 每页条数，指定每页返回的评论数量
     * @return 分页后的评论列表（CommentBo类型），包含分页参数和评论数据
     */
    PageInfo<CommentBo> getComments(Integer cid, int page, int limit);

    /**
     * 按自定义条件分页查询评论
     * 支持通过CommentVoExample构建灵活查询条件（如按评论状态、创建时间、作者等筛选），
     * 返回原生CommentVo类型的分页数据，适用于多场景下的评论查询需求
     * @param commentVoExample 评论查询条件对象，可设置多维度筛选规则
     * @param page 页码，指定查询第几页数据
     * @param limit 每页条数，指定每页返回的评论数量
     * @return 分页后的评论列表（CommentVo类型），包含分页参数和原始评论数据
     */
    PageInfo<CommentVo> getCommentsWithPage(CommentVoExample commentVoExample, int page, int limit);


    /**
     * 根据评论ID查询评论详情
     * 通过评论主键（coid）精准查询单条评论的完整信息，包括评论内容、作者、关联文章等
     * @param coid 评论主键ID，唯一标识一条评论
     * @return 对应的评论对象（CommentVo），若ID不存在则返回null
     */
    CommentVo getCommentById(Integer coid);


    /**
     * 删除评论
     * 根据评论ID删除指定评论，同时会同步更新对应文章的评论数量（减1），确保数据一致性
     * @param coid 待删除评论的主键ID
     * @param cid 评论关联的文章ID，用于更新文章的评论数
     */
    void delete(Integer coid, Integer cid);

    /**
     * 更新评论信息
     * 支持更新评论的状态、内容等信息（需包含评论主键ID以定位数据），
     * 通常用于评论审核状态变更、内容修改等场景
     * @param comments 包含更新信息的评论对象，必须包含评论主键ID（coid）
     */
    void update(CommentVo comments);

}