package com.my.blog.website.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.utils.DateKit;
import com.my.blog.website.utils.TaleUtils;
import com.my.blog.website.dao.CommentVoMapper;
import com.my.blog.website.modal.Bo.CommentBo;
import com.my.blog.website.modal.Vo.CommentVo;
import com.my.blog.website.modal.Vo.CommentVoExample;
import com.my.blog.website.modal.Vo.ContentVo;
import com.my.blog.website.service.ICommentService;
import com.my.blog.website.service.IContentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 评论服务实现类
 * 负责处理评论的添加、查询、更新、删除等业务逻辑，依赖评论数据访问层和内容服务实现关联操作
 * Created by BlueT on 2017/3/16.
 */
@Service
public class CommentServiceImpl implements ICommentService {
    // 日志记录器，用于记录当前类的运行日志
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    // 评论数据访问对象，用于与数据库交互执行评论相关的CRUD操作
    @Resource
    private CommentVoMapper commentDao;

    // 内容服务，用于关联文章相关的查询与更新操作（如更新文章评论数）
    @Resource
    private IContentService contentService;

    /**
     * 添加评论
     * 1. 验证评论信息合法性（对象非空、作者、邮箱格式、内容长度、关联文章存在性等）
     * 2. 补全评论信息（默认作者名、创建时间、关联文章作者ID）
     * 3. 插入评论到数据库
     * 4. 更新对应文章的评论数量
     * @param comments 待添加的评论对象
     */
    @Override
    public void insertComment(CommentVo comments) {
        // 验证评论对象非空
        if (null == comments) {
            throw new TipException("评论对象为空");
        }
        // 若作者名为空，默认设置为"热心网友"
        if (StringUtils.isBlank(comments.getAuthor())) {
            comments.setAuthor("热心网友");
        }
        // 验证邮箱格式（若邮箱非空）
        if (StringUtils.isNotBlank(comments.getMail()) && !TaleUtils.isEmail(comments.getMail())) {
            throw new TipException("请输入正确的邮箱格式");
        }
        // 验证评论内容非空
        if (StringUtils.isBlank(comments.getContent())) {
            throw new TipException("评论内容不能为空");
        }
        // 验证评论内容长度是否正常（5-2000字符）
        if (comments.getContent().length() < 5 || comments.getContent().length() > 2000) {
            throw new TipException("评论字数在5-2000个字符");
        }
        // 验证关联文章ID非空
        if (null == comments.getCid()) {
            throw new TipException("评论文章不能为空");
        }
        // 验证关联文章是否存在
        ContentVo contents = contentService.getContents(String.valueOf(comments.getCid()));
        if (null == contents) {
            throw new TipException("不存在的文章");
        }
        // 补全评论的文章作者ID（用于权限控制等）
        comments.setOwnerId(contents.getAuthorId());
        // 设置评论创建时间（当前时间戳）
        comments.setCreated(DateKit.getCurrentUnixTime());
        // 插入评论到数据库（选择性插入，只插入非空字段）
        commentDao.insertSelective(comments);

        // 更新文章的评论数量（+1）
        ContentVo temp = new ContentVo();
        temp.setCid(contents.getCid());
        temp.setCommentsNum(contents.getCommentsNum() + 1);
        contentService.updateContentByCid(temp);
    }

    /**
     * 按文章ID分页查询一级评论（非回复的评论）
     * 1. 基于文章ID和parent=0条件查询评论
     * 2. 分页处理并转换为CommentBo对象
     * 3. 复制分页信息返回
     * @param cid 文章ID
     * @param page 页码
     * @param limit 每页条数
     * @return 包含评论列表的分页信息（CommentBo类型）
     */
    @Override
    public PageInfo<CommentBo> getComments(Integer cid, int page, int limit) {
        // 若文章ID并非空值，则执行查询
        if (null != cid) {
            // 开启分页
            PageHelper.startPage(page, limit);
            // 构建查询条件：文章ID匹配且为一级评论（parent=0），按评论ID降序排列
            CommentVoExample commentVoExample = new CommentVoExample();
            commentVoExample.createCriteria().andCidEqualTo(cid).andParentEqualTo(0);
            commentVoExample.setOrderByClause("coid desc");
            // 查询符合条件的评论（包含大字段内容）
            List<CommentVo> parents = commentDao.selectByExampleWithBLOBs(commentVoExample);
            // 构建原始评论的分页信息
            PageInfo<CommentVo> commentPaginator = new PageInfo<>(parents);
            // 复制分页信息（不含数据列表），用于转换为CommentBo类型的分页
            PageInfo<CommentBo> returnBo = copyPageInfo(commentPaginator);
            // 若存在评论，转换为CommentBo列表并设置到分页信息中
            if (parents.size() != 0) {
                List<CommentBo> comments = new ArrayList<>(parents.size());
                parents.forEach(parent -> {
                    CommentBo comment = new CommentBo(parent);
                    comments.add(comment);
                });
                returnBo.setList(comments);
            }
            return returnBo;
        }
        // 文章ID为空时返回null
        return null;
    }

    /**
     * 按条件分页查询评论
     * 基于自定义查询条件（CommentVoExample）分页查询评论，返回原生CommentVo的分页信息
     * @param commentVoExample 查询条件对象
     * @param page 页码
     * @param limit 每页条数
     * @return 包含评论列表的分页信息（CommentVo类型）
     */
    @Override
    public PageInfo<CommentVo> getCommentsWithPage(CommentVoExample commentVoExample, int page, int limit) {
        // 开启分页
        PageHelper.startPage(page, limit);
        // 按条件查询评论（包含大字段内容）
        List<CommentVo> commentVos = commentDao.selectByExampleWithBLOBs(commentVoExample);
        // 构建并返回分页信息
        PageInfo<CommentVo> pageInfo = new PageInfo<>(commentVos);
        return pageInfo;
    }

    /**
     * 更新评论信息
     * 验证评论对象及主键非空后，更新评论（包含大字段内容）
     * @param comments 待更新的评论对象
     */
    @Override
    public void update(CommentVo comments) {
        // 若评论对象及主键非空，则执行更新
        if (null != comments && null != comments.getCoid()) {
            commentDao.updateByPrimaryKeyWithBLOBs(comments);
        }
    }

    /**
     * 删除评论
     * 1. 验证评论ID非空
     * 2. 从数据库删除评论
     * 3. 更新对应文章的评论数量（-1）
     * @param coid 评论ID
     * @param cid 关联文章ID
     */
    @Override
    public void delete(Integer coid, Integer cid) {
        // 验证评论ID非空
        if (null == coid) {
            throw new TipException("主键为空");
        }
        // 从数据库删除评论
        commentDao.deleteByPrimaryKey(coid);
        // 查询关联文章信息
        ContentVo contents = contentService.getContents(cid + "");
        // 若文章存在且评论数>0，更新文章评论数（-1）
        if (null != contents && contents.getCommentsNum() > 0) {
            ContentVo temp = new ContentVo();
            temp.setCid(cid);
            temp.setCommentsNum(contents.getCommentsNum() - 1);
            contentService.updateContentByCid(temp);
        }
    }

    /**
     * 按ID查询评论
     * 验证评论ID非空后，查询并返回对应的评论对象
     * @param coid 评论ID
     * @return 评论对象（CommentVo），若ID为空则返回null
     */
    @Override
    public CommentVo getCommentById(Integer coid) {
        // 若评论ID非空，则查询并返回评论
        if (null != coid) {
            return commentDao.selectByPrimaryKey(coid);
        }
        return null;
    }

    /**
     * 复制分页信息（不含数据列表）
     * 用于将一种类型的分页信息（如PageInfo<CommentVo>）转换为另一种类型（如PageInfo<CommentBo>）时，
     * 保持分页参数（页码、每页条数、总条数等）一致
     * @param ordinal 原始分页信息
     * @param <T> 目标分页数据的类型
     * @return 复制后的分页信息（不含数据列表）
     */
    private <T> PageInfo<T> copyPageInfo(PageInfo ordinal) {
        PageInfo<T> returnBo = new PageInfo<T>();
        // 复制分页的核心参数
        returnBo.setPageSize(ordinal.getPageSize());
        returnBo.setPageNum(ordinal.getPageNum());
        returnBo.setEndRow(ordinal.getEndRow());
        returnBo.setTotal(ordinal.getTotal());
        returnBo.setHasNextPage(ordinal.isHasNextPage());
        returnBo.setHasPreviousPage(ordinal.isHasPreviousPage());
        returnBo.setIsFirstPage(ordinal.isIsFirstPage());
        returnBo.setIsLastPage(ordinal.isIsLastPage());
        returnBo.setNavigateFirstPage(ordinal.getNavigateFirstPage());
        returnBo.setNavigateLastPage(ordinal.getNavigateLastPage());
        returnBo.setNavigatepageNums(ordinal.getNavigatepageNums());
        returnBo.setSize(ordinal.getSize());
        returnBo.setPrePage(ordinal.getPrePage());
        returnBo.setNextPage(ordinal.getNextPage());
        return returnBo;
    }
}