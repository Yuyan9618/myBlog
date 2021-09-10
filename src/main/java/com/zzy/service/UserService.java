package com.zzy.service;

import com.zzy.entity.User;

/**
 * @ Description: 用户业务层接口
 * @ author zhangziyao
 * @ date 2021/9/6 22:19
 */
public interface UserService {
    User checkUser(String username, String password);
}
