package com.my.blog.website.interceptor;


import com.my.blog.website.utils.TaleUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/** MVC配置，注册拦截器和静态资源 */
@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Resource
    private BaseInterceptor baseInterceptor;

    /** 添加拦截器配置 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor);
    }

    /** 配置静态资源访问路径 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置上传文件访问路径
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ TaleUtils.getUplodFilePath()+"upload/");
        super.addResourceHandlers(registry);
    }
}
