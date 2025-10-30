package com.my.blog.website.controller.admin;

import com.github.pagehelper.PageInfo;
import com.vdurmont.emoji.EmojiParser;
import com.my.blog.website.controller.BaseController;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.Bo.RestResponseBo;
import com.my.blog.website.modal.Vo.CommentVo;
import com.my.blog.website.modal.Vo.CommentVoExample;
import com.my.blog.website.modal.Vo.UserVo;
import com.my.blog.website.service.ICommentService;
import com.my.blog.website.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 评论管理控制器。
 *
 * - 评论列表、删除、状态更新
 * - 管理员回复评论
 */
@Controller
@RequestMapping("admin/comments")
public class CommentController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Resource
    private ICommentService commentsService;

    /**
     * 评论列表页。
     * @param page 页码，默认1
     * @param limit 每页数量，默认15
     * @param request HTTP 请求
     * @return 模板视图名
     */
    @GetMapping(value = "")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "15") int limit, HttpServletRequest request) {
        UserVo users = this.user(request);
        CommentVoExample commentVoExample = new CommentVoExample();
        commentVoExample.setOrderByClause("coid desc"); // 主键倒序
        commentVoExample.createCriteria().andAuthorIdNotEqualTo(users.getUid()); // 过滤掉自己的评论
        PageInfo<CommentVo> commentsPaginator = commentsService.getCommentsWithPage(commentVoExample,page, limit); // 分页
        request.setAttribute("comments", commentsPaginator);
        return "admin/comment_list";
    }

    /**
     * 删除一条评论。
     * @param coid 评论主键ID
     * @return 操作结果
     */
    @PostMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public  RestResponseBo delete(@RequestParam Integer coid) {
        try {
            CommentVo comments = commentsService.getCommentById(coid);
            if(null == comments){
                return RestResponseBo.fail("不存在该评论");
            }
            commentsService.delete(coid, comments.getCid()); // 删除并更新文章统计
        } catch (Exception e) {
            String msg = "评论删除失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

    @PostMapping(value = "status")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo delete(@RequestParam Integer coid, @RequestParam String status) {
        try {
            CommentVo comments = new CommentVo();
            comments.setCoid(coid);
            comments.setStatus(status); // 修改审核状态
            commentsService.update(comments); // 持久化更新
        } catch (Exception e) {
            String msg = "操作失败";
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
     * 回复评论。
     * @param coid 被回复的评论主键ID
     * @param content 回复内容
     * @param request HTTP 请求
     * @return 操作结果
     */
    @PostMapping(value = "")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo reply(@RequestParam Integer coid, @RequestParam String content, HttpServletRequest request) {
        if(null == coid || StringUtils.isBlank(content)){
            return RestResponseBo.fail("请输入完整后评论");
        }

        if(content.length() > 2000){ // 限制最大长度
            return RestResponseBo.fail("请输入2000个字符以内的回复");
        }
        CommentVo c = commentsService.getCommentById(coid);
        if(null == c){ // 校验被回复评论存在
            return RestResponseBo.fail("不存在该评论");
        }
        UserVo users = this.user(request);
        content = TaleUtils.cleanXSS(content); // XSS 过滤
        content = EmojiParser.parseToAliases(content); // emoji 转义

        CommentVo comments = new CommentVo();
        comments.setAuthor(users.getUsername());
        comments.setAuthorId(users.getUid());
        comments.setCid(c.getCid());
        comments.setIp(request.getRemoteAddr());
        comments.setUrl(users.getHomeUrl());
        comments.setContent(content);
        comments.setMail(users.getEmail());
        comments.setParent(coid);
        try {
            commentsService.insertComment(comments); // 插入回复并更新计数
            return RestResponseBo.ok();
        } catch (Exception e) {
            String msg = "回复失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
    }

}
