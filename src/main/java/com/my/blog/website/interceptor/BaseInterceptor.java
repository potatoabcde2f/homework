package com.my.blog.website.interceptor;

import com.my.blog.website.modal.Vo.UserVo;
import com.my.blog.website.service.IUserService;
import com.my.blog.website.utils.*;
import com.my.blog.website.constant.WebConst;
import com.my.blog.website.dto.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 基础拦截器，处理登录认证和CSRF防护 */
@Component
public class BaseInterceptor implements HandlerInterceptor {
    // 日志记录器
    private static final Logger LOGGE = LoggerFactory.getLogger(BaseInterceptor.class);
    // User-Agent头部字段名
    private static final String USER_AGENT = "user-agent";

    @Resource
    private IUserService userService;

    private MapCache cache = MapCache.single();

    @Resource
    private Commons commons;

    @Resource
    private AdminCommons adminCommons;


    /** 请求预处理，进行登录验证和CSRF令牌生成 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();

        // 记录访问日志
        LOGGE.info("UserAgent: {}", request.getHeader(USER_AGENT));
        LOGGE.info("用户访问地址: {}, 来路地址: {}", uri, IPKit.getIpAddrByRequest(request));

        // 登录验证
        UserVo user = TaleUtils.getLoginUser(request);
        if (null == user) {
            Integer uid = TaleUtils.getCookieUid(request);
            if (null != uid) {
                // 从cookie恢复登录状态（注：cookie可被伪造）
                user = userService.queryUserById(uid);
                request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
            }
        }

        // 拦截未登录的后台访问
        if (uri.startsWith("/admin") && !uri.startsWith("/admin/login") && null == user) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;
        }

        // 为GET请求生成CSRF令牌（30分钟有效）
        if (request.getMethod().equals("GET")) {
            String csrf_token = UUID.UU64();
            cache.hset(Types.CSRF_TOKEN.getType(), csrf_token, uri, 30 * 60);
            request.setAttribute("_csrf_token", csrf_token);
        }
        return true;
    }

    /** 请求处理后，注入公共工具类到请求属性 */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // 注入公共工具类
        httpServletRequest.setAttribute("commons", commons);
        httpServletRequest.setAttribute("adminCommons", adminCommons);
    }

    /** 请求完成后的处理 */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // 暂无实现
    }
}
