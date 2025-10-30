package com.my.blog.website.controller.admin;

import com.my.blog.website.constant.WebConst;
import com.my.blog.website.controller.BaseController;
import com.my.blog.website.dto.LogActions;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.Bo.RestResponseBo;
import com.my.blog.website.modal.Vo.UserVo;
import com.my.blog.website.service.ILogService;
import com.my.blog.website.service.IUserService;
import com.my.blog.website.utils.Commons;
import com.my.blog.website.utils.TaleUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 后台认证控制器：登录与登出。
 */
@Controller
@RequestMapping("/admin")
@Transactional(rollbackFor = TipException.class)
public class AuthController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Resource
    private IUserService usersService;

    @Resource
    private ILogService logService;

    @GetMapping(value = "/login")
    public String login() {
        return "admin/login";
    }

    /**
     * 管理后台登录。
     * @param username 用户名
     * @param password 密码
     * @param remeber_me 记住登录，可为空
     * @param request HTTP 请求
     * @param response HTTP 响应
     * @return 登录结果
     */
    @PostMapping(value = "login")
    @ResponseBody
    public RestResponseBo doLogin(@RequestParam String username,
                                  @RequestParam String password,
                                  @RequestParam(required = false) String remeber_me,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        // 从缓存读取登录失败次数
        Integer error_count = cache.get("login_error_count");
        try {
            // 校验用户名密码
            UserVo user = usersService.login(username, password);
            // 登录成功写入 session
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
            if (StringUtils.isNotBlank(remeber_me)) {
                // 记住登录：写入加密 cookie
                TaleUtils.setCookie(response, user.getUid());
            }
            // 记录登录日志
            logService.insertLog(LogActions.LOGIN.getAction(), null, request.getRemoteAddr(), user.getUid());
        } catch (Exception e) {
            // 累计错误次数
            error_count = null == error_count ? 1 : error_count + 1;
            if (error_count > 3) {
                return RestResponseBo.fail("您输入密码已经错误超过3次，请10分钟后尝试");
            }
            // 10 分钟内限制
            cache.set("login_error_count", error_count, 10 * 60);
            String msg = "登录失败";
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
     * 注销登录。
     * @param session 会话
     * @param response 响应
     * @param request 请求
     */
    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        // 清理会话与cookie
        session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
        Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        try {
            // 跳转到登录页
            //response.sendRedirect(Commons.site_url());
            response.sendRedirect(Commons.site_login());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("注销失败", e);
        }
    }
}
