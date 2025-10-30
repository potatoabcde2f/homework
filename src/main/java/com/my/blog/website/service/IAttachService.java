package com.my.blog.website.service;

import com.github.pagehelper.PageInfo;
import com.my.blog.website.modal.Vo.AttachVo;

/**
 * 附件服务核心接口
 * 定义附件（如图片、文档等上传文件）的全生命周期管理规范，包括分页查询、保存、单条查询及删除操作，
 * 支撑博客系统中附件的上传、管理和使用，关联上传者信息以保证数据归属清晰。
 * Created by wangq on 2017/3/20.
 */
public interface IAttachService {
    /**
     * 分页查询附件列表
     * 按附件上传时间或ID排序，返回分页后的附件信息，用于后台管理系统展示所有上传的附件
     * @param page 当前页码（从1开始）
     * @param limit 每页展示的附件数量
     * @return 分页信息对象，包含当前页附件列表、总条数、总页数等分页参数
     */
    PageInfo<AttachVo> getAttachs(Integer page,Integer limit);

    /**
     * 保存上传的附件信息
     * 记录附件的基本信息到数据库，建立附件与上传者的关联，支撑附件的溯源和管理
     * @param fname 附件原始文件名（如"风景.jpg"）
     * @param fkey 附件存储标识（通常是服务器保存路径或唯一键，用于定位实际文件）
     * @param ftype 附件类型（如"image/jpeg"，标识文件的MIME类型）
     * @param author 上传者ID（关联用户表，记录附件归属）
     */
    void save(String fname, String fkey, String ftype, Integer author);

    /**
     * 根据附件ID查询单个附件详情
     * 获取附件的完整信息（包括文件名、存储路径、类型、上传者、上传时间等），用于附件预览或下载
     * @param id 附件主键ID（唯一标识一条附件记录）
     * @return 附件详情对象，若ID不存在则返回null
     */
    AttachVo selectById(Integer id);

    /**
     * 根据附件ID删除附件
     * 同时删除数据库中的附件记录和服务器上存储的实际文件，确保数据与文件系统的一致性
     * @param id 附件主键ID（待删除的附件标识）
     */
    void deleteById(Integer id);
}