package com.zzy.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Description: 日志切面处理
 * @ author zhangziyao
 * @ date 2021/9/6 14:56
 */
@ControllerAdvice // 拦截掉所有带有@Controller注解的控制器
public class ControllerExceptionHandler {
    // 将异常记录到日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @ExceptionHandler(Exception.class) // 表示是异常处理方法
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
    // 记录异常信息：请求的url， 异常信息
        logger.error("Request URL : {}, Exception : {}", request.getRequestURI(), e);
        
    // 当标识了状态码的时候就不拦截，如资源找不到异常
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
    // 将记录的异常信息返回到error页面
        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", e);
        mv.setViewName("error/error");
        return mv;
    }
}
