package com.my.blog.website.controller.admin;

import com.my.blog.website.service.ISiteService;
import com.my.blog.website.constant.WebConst;
import com.my.blog.website.controller.BaseController;
import com.my.blog.website.dto.LogActions;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.Bo.RestResponseBo;
import com.my.blog.website.modal.Bo.StatisticsBo;
import com.my.blog.website.modal.Vo.CommentVo;
import com.my.blog.website.modal.Vo.ContentVo;
import com.my.blog.website.modal.Vo.LogVo;
import com.my.blog.website.modal.Vo.UserVo;
import com.my.blog.website.service.ILogService;
import com.my.blog.website.service.IUserService;
import com.my.blog.website.utils.GsonUtils;
import com.my.blog.website.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 后台管理首页控制器。
 *
 * - 仪表盘展示近期评论、文章、统计与日志
 * - 个人资料查看与修改、修改密码
 */
@Controller("adminIndexController")
@RequestMapping("/admin")
@Transactional(rollbackFor = TipException.class)
public class IndexController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private ISiteService siteService;

    @Resource
    private ILogService logService;

    @Resource
    private IUserService userService;

    /**
     * 管理后台首页。
     * @param request HTTP 请求
     * @return 模板视图名
     */
    @GetMapping(value = {"","/index"})
    public String index(HttpServletRequest request){
        LOGGER.info("Enter admin index method");
        List<CommentVo> comments = siteService.recentComments(5); // 最近5条评论
        List<ContentVo> contents = siteService.recentContents(5); // 最近5篇文章
        StatisticsBo statistics = siteService.getStatistics(); // 概览统计
        // 取最新的20条日志
        List<LogVo> logs = logService.getLogs(1, 5); // 最近操作日志

        request.setAttribute("comments", comments);
        request.setAttribute("articles", contents);
        request.setAttribute("statistics", statistics);
        request.setAttribute("logs", logs);
        LOGGER.info("Exit admin index method");
        return "admin/index";
    }

    /**
     * 个人设置页面。
     * @return 模板视图名
     */
    @GetMapping(value = "profile")
    public String profile() {
        return "admin/profile";
    }

    /**
     * 退出登录页面。
     * @return 模板视图名
     */
    @GetMapping(value = "logout")
    public String logout() {
        System.out.println("index-----------logout");
        return "admin/login";
    }


    /**
     * 保存个人信息。
     * @param screenName 昵称
     * @param email 邮箱
     * @param request HTTP 请求
     * @param session 会话
     * @return 操作结果
     */
    @PostMapping(value = "/profile")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo saveProfile(@RequestParam String screenName, @RequestParam String email, HttpServletRequest request, HttpSession session) {

        UserVo users = this.user(request); // 当前登录用户
        if (StringUtils.isNotBlank(screenName) && StringUtils.isNotBlank(email)) {
            UserVo temp = new UserVo();
            temp.setUid(users.getUid());
            temp.setScreenName(screenName);
            temp.setEmail(email);
            userService.updateByUid(temp); // 持久化更新
            logService.insertLog(LogActions.UP_INFO.getAction(), GsonUtils.toJsonString(temp), request.getRemoteAddr(), this.getUid(request)); // 记录日志

            //更新session中的数据
            UserVo original = (UserVo) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
            original.setScreenName(screenName);
            original.setEmail(email);
            session.setAttribute(WebConst.LOGIN_SESSION_KEY, original); // 刷新会话
        }
        return RestResponseBo.ok();
    }

    /**
     * 修改密码。
     * @param oldPassword 旧密码
     * @param password 新密码（6-14位）
     * @param request HTTP 请求
     * @param session 会话
     * @return 操作结果
     */
    @PostMapping(value = "/password")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo upPwd(@RequestParam String oldPassword, @RequestParam String password, HttpServletRequest request,HttpSession session) {
        UserVo users = this.user(request);
        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(password)) {
            return RestResponseBo.fail("请确认信息输入完整");
        }

        if (!users.getPassword().equals(TaleUtils.MD5encode(users.getUsername() + oldPassword))) { // 校验旧密码
            return RestResponseBo.fail("旧密码错误");
        }
        if (password.length() < 6 || password.length() > 14) { // 长度限制
            return RestResponseBo.fail("请输入6-14位密码");
        }

        try {
            UserVo temp = new UserVo();
            temp.setUid(users.getUid());
            String pwd = TaleUtils.MD5encode(users.getUsername() + password); // 生成新密码摘要
            temp.setPassword(pwd);
            userService.updateByUid(temp); // 保存新密码
            logService.insertLog(LogActions.UP_PWD.getAction(), null, request.getRemoteAddr(), this.getUid(request)); // 记录日志

            //更新session中的数据
            UserVo original= (UserVo)session.getAttribute(WebConst.LOGIN_SESSION_KEY);
            original.setPassword(pwd);
            session.setAttribute(WebConst.LOGIN_SESSION_KEY,original); // 刷新会话
            return RestResponseBo.ok();
        } catch (Exception e){
            String msg = "密码修改失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
    }
}
