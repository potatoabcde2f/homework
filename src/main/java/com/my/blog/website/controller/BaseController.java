package com.my.blog.website.controller;

import com.my.blog.website.modal.Vo.UserVo;
import com.my.blog.website.utils.TaleUtils;
import com.my.blog.website.utils.MapCache;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

    public static String THEME = "themes/default"; // 前台主题根路径

    protected MapCache cache = MapCache.single(); // 简单内存缓存（单例）

    /**
     * 主页的页面主题
     * @param viewName
     * @return
     */
    public String render(String viewName) {
        return THEME + "/" + viewName; // 拼接主题路径
    }

    public BaseController title(HttpServletRequest request, String title) {
        request.setAttribute("title", title); // 设置页面标题
        return this;
    }

    public BaseController keywords(HttpServletRequest request, String keywords) {
        request.setAttribute("keywords", keywords); // 设置页面关键词
        return this;
    }

    /**
     * 获取请求绑定的登录对象
     * @param request
     * @return
     */
    public UserVo user(HttpServletRequest request) {
        return TaleUtils.getLoginUser(request); // 从请求中解析当前登录用户
    }

    public Integer getUid(HttpServletRequest request){
        return this.user(request).getUid(); // 便捷获取用户ID
    }

    public String render_404() {
        return "comm/error_404"; // 返回404页面
    }

}
