package com.zzy.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ Description: 登录拦截器
 * @ author zhangziyao
 * @ date 2021/9/7 11:25
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断session里面是否有用户，没有的话就重定向到登录页面拦截掉
        if(request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
// 继承HandlerInterceptorAdapter适配器，重写预处理方法preHandle
// 对session进行判断，看是否有用户，没有的话重定向到登录页面，给拦截掉
// 还需要指定拦截的内容，在同级包下新建配置类WebConfig来指定拦截的内容