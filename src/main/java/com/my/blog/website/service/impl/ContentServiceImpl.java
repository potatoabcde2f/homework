package com.my.blog.website.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.blog.website.constant.WebConst;
import com.my.blog.website.dao.MetaVoMapper;
import com.my.blog.website.dto.Types;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.Vo.ContentVo;
import com.my.blog.website.modal.Vo.ContentVoExample;
import com.my.blog.website.service.IContentService;
import com.my.blog.website.service.IMetaService;
import com.my.blog.website.service.IRelationshipService;
import com.my.blog.website.utils.DateKit;
import com.my.blog.website.utils.TaleUtils;
import com.my.blog.website.utils.Tools;
import com.vdurmont.emoji.EmojiParser;
import com.my.blog.website.dao.ContentVoMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章内容服务实现类
 * 负责处理文章的发布、查询、更新、删除、分类/标签关联等核心业务逻辑
 * Created by Administrator on 2017/3/13 013.
 */
@Service
public class ContentServiceImpl implements IContentService {
    // 日志记录器，用于记录当前类的运行日志
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentServiceImpl.class);

    // 文章数据访问对象，用于与数据库交互执行文章相关的CRUD操作
    @Resource
    private ContentVoMapper contentDao;

    // 元数据（分类/标签）数据访问对象，用于元数据相关的数据库操作
    @Resource
    private MetaVoMapper metaDao;

    // 关系服务，用于处理文章与标签或分类的关联关系
    @Resource
    private IRelationshipService relationshipService;

    // 元数据服务，用于标签或分类的创建与关联管理
    @Resource
    private IMetaService metasService;

    /**
     * 发布文章
     * 1. 验证文章信息合法性（标题、内容、长度、作者登录状态等）
     * 2. 处理文章路径（slug）的唯一性校验
     * 3. 解析文章内容中的表情符号
     * 4. 设置文章创建/修改时间、初始阅读量和评论数
     * 5. 保存文章到数据库，并关联标签和分类
     * @param contents 待发布的文章对象
     */
    @Override
    public void publish(ContentVo contents) {
        // 验证文章对象是否空
        if (null == contents) {
            throw new TipException("文章对象为空");
        }
        // 验证标题是否空
        if (StringUtils.isBlank(contents.getTitle())) {
            throw new TipException("文章标题不能为空");
        }
        // 验证内容是否空
        if (StringUtils.isBlank(contents.getContent())) {
            throw new TipException("文章内容不能为空");
        }
        // 验证标题长度是否不超过最大值
        int titleLength = contents.getTitle().length();
        if (titleLength > WebConst.MAX_TITLE_COUNT) {
            throw new TipException("文章标题过长");
        }
        // 验证内容长度不超过最大值
        int contentLength = contents.getContent().length();
        if (contentLength > WebConst.MAX_TEXT_COUNT) {
            throw new TipException("文章内容过长");
        }
        // 验证作者是否已登录（作者ID是否非空）
        if (null == contents.getAuthorId()) {
            throw new TipException("请登录后发布文章");
        }

        // 处理文章路径（slug）：验证格式和唯一性
        if (StringUtils.isNotBlank(contents.getSlug())) {
            if (contents.getSlug().length() < 5) {
                throw new TipException("路径太短了");
            }
            if (!TaleUtils.isPath(contents.getSlug())) {
                throw new TipException("您输入的路径不合法");
            }
            // 校验同类型同状态下slug的唯一性
            ContentVoExample contentVoExample = new ContentVoExample();
            contentVoExample.createCriteria().andTypeEqualTo(contents.getType()).andStatusEqualTo(contents.getSlug());
            long count = contentDao.countByExample(contentVoExample);
            if (count > 0) {
                throw new TipException("该路径已经存在，请重新输入");
            }
        } else {
            contents.setSlug(null); // 路径为空时设为null
        }

        // 解析文章内容中的表情符号（转换为别名格式）
        contents.setContent(EmojiParser.parseToAliases(contents.getContent()));

        // 设置时间相关字段：创建时间、修改时间（当前时间戳）
        int time = DateKit.getCurrentUnixTime();
        contents.setCreated(time);
        contents.setModified(time);
        // 初始化阅读量和评论数为0
        contents.setHits(0);
        contents.setCommentsNum(0);

        // 保存文章到数据库
        contentDao.insert(contents);
        Integer cid = contents.getCid(); // 获取自增的文章ID

        // 关联标签和分类（通过元数据服务处理）
        String tags = contents.getTags();
        String categories = contents.getCategories();
        metasService.saveMetas(cid, tags, Types.TAG.getType()); // 保存标签关联
        metasService.saveMetas(cid, categories, Types.CATEGORY.getType()); // 保存分类关联
    }

    /**
     * 分页获取已发布的文章列表
     * 查询类型为"文章"、状态为"已发布"的内容，按创建时间降序排列，支持分页
     * @param p 页码
     * @param limit 每页条数
     * @return 包含文章列表的分页信息
     */
    @Override
    public PageInfo<ContentVo> getContents(Integer p, Integer limit) {
        LOGGER.debug("Enter getContents method");
        ContentVoExample example = new ContentVoExample();
        // 按创建时间降序排列
        example.setOrderByClause("created desc");
        // 查询条件：类型为文章、状态为已发布
        example.createCriteria().andTypeEqualTo(Types.ARTICLE.getType()).andStatusEqualTo(Types.PUBLISH.getType());
        // 开启分页
        PageHelper.startPage(p, limit);
        // 查询文章列表（包含大字段内容）
        List<ContentVo> data = contentDao.selectByExampleWithBLOBs(example);
        // 构建分页信息
        PageInfo<ContentVo> pageInfo = new PageInfo<>(data);
        LOGGER.debug("Exit getContents method");
        return pageInfo;
    }

    /**
     * 根据ID或路径（slug）获取文章详情
     * 支持两种查询方式：数字ID直接查询；字符串路径（slug）匹配查询
     * 访问时自动增加文章阅读量
     * @param id 文章ID（数字）或路径（字符串）
     * @return 文章详情对象，查询失败时返回null或抛出异常
     */
    @Override
    public ContentVo getContents(String id) {
        if (StringUtils.isNotBlank(id)) {
            // 若ID为数字，则按主键查询
            if (Tools.isNumber(id)) {
                ContentVo contentVo = contentDao.selectByPrimaryKey(Integer.valueOf(id));
                if (contentVo != null) {
                    // 阅读量+1并更新
                    contentVo.setHits(contentVo.getHits() + 1);
                    contentDao.updateByPrimaryKey(contentVo);
                }
                return contentVo;
            } else {
                // 若ID为字符串，则按路径（slug）查询
                ContentVoExample contentVoExample = new ContentVoExample();
                contentVoExample.createCriteria().andSlugEqualTo(id);
                List<ContentVo> contentVos = contentDao.selectByExampleWithBLOBs(contentVoExample);
                // 确保查询结果唯一
                if (contentVos.size() != 1) {
                    throw new TipException("query content by id and return is not one");
                }
                return contentVos.get(0);
            }
        }
        return null;
    }

    /**
     * 根据文章ID更新文章信息（选择性更新，只更新非空字段）
     * @param contentVo 包含更新信息的文章对象（需包含文章ID）
     */
    @Override
    public void updateContentByCid(ContentVo contentVo) {
        if (null != contentVo && null != contentVo.getCid()) {
            contentDao.updateByPrimaryKeySelective(contentVo);
        }
    }

    /**
     * 根据分类/标签ID分页获取关联的文章
     * 基于元数据ID（分类或标签ID）查询关联的文章，支持分页
     * @param mid 分类/标签的元数据ID
     * @param page 页码
     * @param limit 每页条数
     * @return 包含文章列表的分页信息
     */
    @Override
    public PageInfo<ContentVo> getArticles(Integer mid, int page, int limit) {
        // 查询该元数据关联的文章总数
        int total = metaDao.countWithSql(mid);
        // 开启分页
        PageHelper.startPage(page, limit);
        // 根据元数据ID查询文章列表
        List<ContentVo> list = contentDao.findByCatalog(mid);
        // 构建分页信息并设置总条数
        PageInfo<ContentVo> paginator = new PageInfo<>(list);
        paginator.setTotal(total);
        return paginator;
    }

    /**
     * 根据关键词分页搜索已发布的文章
     * 按标题模糊匹配关键词，查询类型为"文章"、状态为"已发布"的内容，支持分页
     * @param keyword 搜索关键词
     * @param page 页码
     * @param limit 每页条数
     * @return 包含搜索结果的分页信息
     */
    @Override
    public PageInfo<ContentVo> getArticles(String keyword, Integer page, Integer limit) {
        // 开启分页
        PageHelper.startPage(page, limit);
        ContentVoExample contentVoExample = new ContentVoExample();
        ContentVoExample.Criteria criteria = contentVoExample.createCriteria();
        // 查询条件：类型为文章、状态为已发布、标题包含关键词
        criteria.andTypeEqualTo(Types.ARTICLE.getType());
        criteria.andStatusEqualTo(Types.PUBLISH.getType());
        criteria.andTitleLike("%" + keyword + "%");
        // 按创建时间降序排列
        contentVoExample.setOrderByClause("created desc");
        // 查询文章列表（包含大字段内容）
        List<ContentVo> contentVos = contentDao.selectByExampleWithBLOBs(contentVoExample);
        return new PageInfo<>(contentVos);
    }

    /**
     * 按自定义条件分页查询文章
     * 基于ContentVoExample构建查询条件，支持灵活的分页查询
     * @param commentVoExample 查询条件对象
     * @param page 页码
     * @param limit 每页条数
     * @return 包含查询结果的分页信息
     */
    @Override
    public PageInfo<ContentVo> getArticlesWithpage(ContentVoExample commentVoExample, Integer page, Integer limit) {
        // 开启分页
        PageHelper.startPage(page, limit);
        // 按条件查询文章列表（包含大字段内容）
        List<ContentVo> contentVos = contentDao.selectByExampleWithBLOBs(commentVoExample);
        return new PageInfo<>(contentVos);
    }

    /**
     * 根据文章ID删除文章
     * 同时删除文章及其与标签/分类的关联关系
     * @param cid 文章ID
     */
    @Override
    public void deleteByCid(Integer cid) {
        // 查询文章是否存在
        ContentVo contents = this.getContents(cid + "");
        if (null != contents) {
            // 删除文章
            contentDao.deleteByPrimaryKey(cid);
            // 删除文章与标签/分类的关联关系
            relationshipService.deleteById(cid, null);
        }
    }

    /**
     * 批量更新文章分类
     * 将所有属于原分类的文章更新为新分类
     * @param ordinal 原分类名称
     * @param newCatefory 新分类名称
     */
    @Override
    public void updateCategory(String ordinal, String newCatefory) {
        ContentVo contentVo = new ContentVo();
        contentVo.setCategories(newCatefory); // 设置新分类
        // 构建查询条件：分类等于原分类
        ContentVoExample example = new ContentVoExample();
        example.createCriteria().andCategoriesEqualTo(ordinal);
        // 批量更新符合条件的文章
        contentDao.updateByExampleSelective(contentVo, example);
    }

    /**
     * 更新文章
     * 1. 验证文章信息合法性（同发布逻辑）
     * 2. 更新文章修改时间和内容（含表情解析）
     * 3. 重新关联标签和分类（先删除旧关联，再添加新关联）
     * @param contents 待更新的文章对象（需包含文章ID）
     */
    @Override
    public void updateArticle(ContentVo contents) {
        // 验证文章对象及ID非空
        if (null == contents || null == contents.getCid()) {
            throw new TipException("文章对象不能为空");
        }
        // 验证标题非空
        if (StringUtils.isBlank(contents.getTitle())) {
            throw new TipException("文章标题不能为空");
        }
        // 验证内容非空
        if (StringUtils.isBlank(contents.getContent())) {
            throw new TipException("文章内容不能为空");
        }
        // 验证标题长度
        if (contents.getTitle().length() > 200) {
            throw new TipException("文章标题过长");
        }
        // 验证内容长度
        if (contents.getContent().length() > 65000) {
            throw new TipException("文章内容过长");
        }
        // 验证作者已登录
        if (null == contents.getAuthorId()) {
            throw new TipException("请登录后发布文章");
        }
        // 处理路径（slug）为空的情况
        if (StringUtils.isBlank(contents.getSlug())) {
            contents.setSlug(null);
        }

        // 设置修改时间为当前时间戳
        int time = DateKit.getCurrentUnixTime();
        contents.setModified(time);
        Integer cid = contents.getCid(); // 获取文章ID
        // 解析文章内容中的表情符号
        contents.setContent(EmojiParser.parseToAliases(contents.getContent()));

        // 更新文章信息（选择性更新非空字段）
        contentDao.updateByPrimaryKeySelective(contents);
        // 删除旧的标签/分类关联
        relationshipService.deleteById(cid, null);
        // 重新关联新的标签和分类
        metasService.saveMetas(cid, contents.getTags(), Types.TAG.getType());
        metasService.saveMetas(cid, contents.getCategories(), Types.CATEGORY.getType());
    }
}