package com.zzy.service.Impl;

import com.zzy.dao.UserDao;
import com.zzy.entity.User;
import com.zzy.service.UserService;
import com.zzy.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Description
 * @ author zhangziyao
 * @ date 2021/9/6 22:21
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired // 注解的意思？？
    private UserDao userDao;
    
    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, MD5Utils.encrypt(password));
        return user;
    }
    
}
