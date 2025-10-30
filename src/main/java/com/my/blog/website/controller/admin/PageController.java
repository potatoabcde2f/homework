package com.my.blog.website.controller.admin;

import com.github.pagehelper.PageInfo;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.Bo.RestResponseBo;
import com.my.blog.website.modal.Vo.UserVo;
import com.my.blog.website.service.ILogService;
import com.my.blog.website.constant.WebConst;
import com.my.blog.website.controller.BaseController;
import com.my.blog.website.dto.LogActions;
import com.my.blog.website.dto.Types;
import com.my.blog.website.modal.Vo.ContentVo;
import com.my.blog.website.modal.Vo.ContentVoExample;
import com.my.blog.website.service.IContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 独立页面管理控制器。
 *
 * - 页面列表、创建、编辑、删除
 */
@Controller()
@RequestMapping("admin/page")
public class PageController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @Resource
    private IContentService contentsService;

    @Resource
    private ILogService logService;

    /**
     * 页面列表页。
     * @param request HTTP 请求
     * @return 模板视图名
     */
    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        ContentVoExample contentVoExample = new ContentVoExample();
        contentVoExample.setOrderByClause("created desc"); // 时间倒序
        contentVoExample.createCriteria().andTypeEqualTo(Types.PAGE.getType()); // 仅查询独立页面
        PageInfo<ContentVo> contentsPaginator = contentsService.getArticlesWithpage(contentVoExample, 1, WebConst.MAX_POSTS); // 取前N条
        request.setAttribute("articles", contentsPaginator);
        return "admin/page_list";
    }

    /**
     * 新建页面编辑页。
     * @param request HTTP 请求
     * @return 模板视图名
     */
    @GetMapping(value = "new")
    public String newPage(HttpServletRequest request) {
        return "admin/page_edit";
    }

    /**
     * 编辑页面。
     * @param cid 页面主键
     * @param request HTTP 请求
     * @return 模板视图名
     */
    @GetMapping(value = "/{cid}")
    public String editPage(@PathVariable String cid, HttpServletRequest request) {
        ContentVo contents = contentsService.getContents(cid); // 加载页面内容
        request.setAttribute("contents", contents);
        return "admin/page_edit";
    }

    /**
     * 发布页面。
     * @param title 标题
     * @param content 正文
     * @param status 状态
     * @param slug 访问别名
     * @param allowComment 是否允许评论
     * @param allowPing 是否允许Ping
     * @param request HTTP 请求
     * @return 操作结果
     */
    @PostMapping(value = "publish")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo publishPage(@RequestParam String title, @RequestParam String content,
                                      @RequestParam String status, @RequestParam String slug,
                                      @RequestParam(required = false) Integer allowComment, @RequestParam(required = false) Integer allowPing, HttpServletRequest request) {

        UserVo users = this.user(request); // 当前用户
        ContentVo contents = new ContentVo();
        contents.setTitle(title); // 标题
        contents.setContent(content); // 正文
        contents.setStatus(status); // 状态
        contents.setSlug(slug); // 访问别名
        contents.setType(Types.PAGE.getType()); // 类型：页面
        if (null != allowComment) {
            contents.setAllowComment(allowComment == 1); // 是否允许评论
        }
        if (null != allowPing) {
            contents.setAllowPing(allowPing == 1); // 是否允许Ping
        }
        contents.setAuthorId(users.getUid()); // 作者ID

        try {
            contentsService.publish(contents); // 发布页面
        } catch (Exception e) {
            String msg = "页面发布失败";
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
     * 编辑页面（保存修改）。
     * @param cid 页面主键
     * @param title 标题
     * @param content 正文
     * @param status 状态
     * @param slug 访问别名
     * @param allowComment 是否允许评论
     * @param allowPing 是否允许Ping
     * @param request HTTP 请求
     * @return 操作结果
     */
    @PostMapping(value = "modify")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo modifyArticle(@RequestParam Integer cid, @RequestParam String title,
                                        @RequestParam String content,
                                        @RequestParam String status, @RequestParam String slug,
                                        @RequestParam(required = false) Integer allowComment, @RequestParam(required = false) Integer allowPing, HttpServletRequest request) {

        UserVo users = this.user(request);
        ContentVo contents = new ContentVo();
        contents.setCid(cid); // 主键
        contents.setTitle(title);
        contents.setContent(content);
        contents.setStatus(status);
        contents.setSlug(slug);
        contents.setType(Types.PAGE.getType());
        if (null != allowComment) {
            contents.setAllowComment(allowComment == 1);
        }
        if (null != allowPing) {
            contents.setAllowPing(allowPing == 1);
        }
        contents.setAuthorId(users.getUid());
        try {
            contentsService.updateArticle(contents); // 保存修改
        } catch (Exception e) {
            String msg = "页面编辑失败";
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
     * 删除页面。
     * @param cid 页面主键
     * @param request HTTP 请求
     * @return 操作结果
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo delete(@RequestParam int cid, HttpServletRequest request) {
        try {
            contentsService.deleteByCid(cid); // 删除页面
            logService.insertLog(LogActions.DEL_PAGE.getAction(), cid + "", request.getRemoteAddr(), this.getUid(request)); // 记录删除日志
        } catch (Exception e) {
            String msg = "页面删除失败";
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
