package com.my.blog.website.controller.admin;


import com.github.pagehelper.PageInfo;
import com.my.blog.website.controller.BaseController;
import com.my.blog.website.dto.LogActions;
import com.my.blog.website.dto.Types;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.Bo.RestResponseBo;
import com.my.blog.website.modal.Vo.ContentVo;
import com.my.blog.website.modal.Vo.ContentVoExample;
import com.my.blog.website.modal.Vo.MetaVo;
import com.my.blog.website.modal.Vo.UserVo;
import com.my.blog.website.service.IContentService;
import com.my.blog.website.service.ILogService;
import com.my.blog.website.service.IMetaService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 后台文章管理控制器。
 *
 * - 提供文章列表、发布、编辑、删除等操作
 * - 依赖内容服务、分类服务与日志服务
 */
@Controller
@RequestMapping("/admin/article")
@Transactional(rollbackFor = TipException.class)
public class ArticleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Resource
    private IContentService contentsService;

    @Resource
    private IMetaService metasService;

    @Resource
    private ILogService logService;

    /**
     * 文章列表页。
     * @param page 页码，默认1
     * @param limit 每页数量，默认15
     * @param request HTTP 请求对象
     * @return 模板视图名
     */
    @GetMapping(value = "")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "15") int limit, HttpServletRequest request) {
        ContentVoExample contentVoExample = new ContentVoExample();
        contentVoExample.setOrderByClause("created desc"); // 按创建时间倒序
        contentVoExample.createCriteria().andTypeEqualTo(Types.ARTICLE.getType()); // 仅查询文章类型
        PageInfo<ContentVo> contentsPaginator = contentsService.getArticlesWithpage(contentVoExample,page,limit); // 分页查询
        request.setAttribute("articles", contentsPaginator);
        return "admin/article_list";
    }

    /**
     * 文章发表页。
     * @param request HTTP 请求对象
     * @return 模板视图名
     */
    @GetMapping(value = "/publish")
    public String newArticle(HttpServletRequest request) {
        List<MetaVo> categories = metasService.getMetas(Types.CATEGORY.getType());
        request.setAttribute("categories", categories);
        return "admin/article_edit";
    }

    /**
     * 文章编辑页。
     * @param cid 内容主键（字符串形式）
     * @param request HTTP 请求对象
     * @return 模板视图名
     */
    @GetMapping(value = "/{cid}")
    public String editArticle(@PathVariable String cid, HttpServletRequest request) {
        ContentVo contents = contentsService.getContents(cid);
        request.setAttribute("contents", contents);
        List<MetaVo> categories = metasService.getMetas(Types.CATEGORY.getType());
        request.setAttribute("categories", categories);
        request.setAttribute("active", "article");
        return "admin/article_edit";
    }

    /**
     * 发表文章。
     * @param contents 文章内容对象
     * @param request HTTP 请求对象
     * @return 操作结果
     */
    @PostMapping(value = "/publish")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo publishArticle(ContentVo contents,  HttpServletRequest request) {
        UserVo users = this.user(request); // 获取当前登录用户
        contents.setAuthorId(users.getUid()); // 设定作者ID
        contents.setType(Types.ARTICLE.getType()); // 设定内容类型为文章
        if (StringUtils.isBlank(contents.getCategories())) {
            contents.setCategories("默认分类"); // 无分类时默认分类
        }
        try {
            contentsService.publish(contents); // 发布文章核心逻辑
        } catch (Exception e) {
            String msg = "文章发布失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

    /**
     * 更新文章。
     * @param contents 文章内容对象（包含 cid）
     * @param request HTTP 请求对象
     * @return 操作结果
     */
    @PostMapping(value = "/modify")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo modifyArticle(ContentVo contents,HttpServletRequest request) {
        UserVo users = this.user(request); // 当前用户
        contents.setAuthorId(users.getUid());
        contents.setType(Types.ARTICLE.getType());
        try {
            contentsService.updateArticle(contents); // 更新文章
        } catch (Exception e) {
            String msg = "文章编辑失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

    /**
     * 删除文章。
     * @param cid 文章主键ID
     * @param request HTTP 请求对象
     * @return 操作结果
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo delete(@RequestParam int cid, HttpServletRequest request) {
        try {
            contentsService.deleteByCid(cid); // 物理删除文章
            logService.insertLog(LogActions.DEL_ARTICLE.getAction(), cid+"", request.getRemoteAddr(), this.getUid(request)); // 记录删除日志
        } catch (Exception e) {
            String msg = "文章删除失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }
}
