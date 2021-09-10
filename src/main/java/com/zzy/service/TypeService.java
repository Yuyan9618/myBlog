package com.zzy.service;

import com.zzy.entity.Type;

import java.util.List;

/**
 * @ Description : 分类业务层接口
 * @ author zhangziyao
 * @ date 2021/9/7 15:06
 */
public interface TypeService {
    //新增保存分类
    int saveType(Type type);
    
    //删除分类
    void deleteType(Long id);
    
    //编辑修改分类
    int updateType(Type type);
    
    //根据id查询分类
    Type getType(Long id);
    
    //查询所有分类
    List<Type> getAllType();
    
    //根据分类名称查询分类
    Type getTypeByName(String name);
    
    // 查询所有分类
    List<Type> getAllTypeAndBlog();
    
}
