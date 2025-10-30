package com.my.blog.website.controller;

import com.my.blog.website.exception.TipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by BlueT on 2017/3/4.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = TipException.class)
    public String tipException(Exception e) {
        LOGGER.error("find exception:e={}",e.getMessage()); // 业务异常日志
        e.printStackTrace(); // 打印堆栈方便定位
        return "comm/error_500"; // 返回500错误页
    }


    @ExceptionHandler(value = Exception.class)
    public String exception(Exception e){
        LOGGER.error("find exception:e={}",e.getMessage()); // 兜底异常
        e.printStackTrace();
        return "comm/error_404"; // 非预期错误统一到404
    }
}
