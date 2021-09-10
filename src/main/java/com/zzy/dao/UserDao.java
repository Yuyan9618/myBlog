package com.zzy.dao;

import com.zzy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ Description: 用户持久层接口
 * @ author zhangziyao
 * @ date 2021/9/6 20:30
 */
@Mapper // 让Mybatis找到对应的mapper，在编译的时候动态生成代理类，实现相应SQL功能
@Repository // 用来声明dao层的bean（这个注解可有可无，可以消去依赖注入的报错信息）
public interface UserDao {
    // @Param注解：将参数传递给SQL
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
