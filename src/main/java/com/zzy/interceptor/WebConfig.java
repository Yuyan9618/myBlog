package com.zzy.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ Description : 指定拦截内容的配置类
 * @ author zhangziyao
 * @ date 2021/9/7 11:30
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
            .addPathPatterns("/admin/**")
            .excludePathPatterns("/admin")
            .excludePathPatterns("/admin/login");
    }
}
//     @Configuration注解：表明是一个有效的配置类
//     实现WebMvcConfigurer接口, 重写addInterceptors方法（继承WebMvcConfigurerAdapter类已经deprecated（已弃用））
//     指定要拦截的路径，这里拦截"admin"访问路径