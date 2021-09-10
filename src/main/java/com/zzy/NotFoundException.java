package com.zzy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ Description: 自定义异常
 * @ author zhangziyao
 * @ date 2021/9/6 15:16
 */

// 对于资源找不到异常，一般也是要跳转到404页面的，这里就需要自定义一个异常类，专门用来应对资源找不到
// @ResponseStatus(HttpStatus.NOT_FOUND)注解表示资源找不到的状态码，标识了状态码的时候就不拦截
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    // 继承RuntimeException，实现继承RuntimeException的构造函数
    public NotFoundException(){
    }
    
    public  NotFoundException(String message) {
        super(message);
    }
    
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
