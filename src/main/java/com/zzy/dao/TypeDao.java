package com.zzy.dao;

import com.zzy.entity.Type;
import com.zzy.queryvo.FirstPageBlog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ Description : 分类持久层接口
 * @ author zhangziyao
 * @ date 2021/9/7 14:33
 */

// 分类管理有查询分类、保存分类、修改编辑分类、删除分类、因此需要有getAllType、saveType、updateType、deleteType接口
// 还需要：getTypeByName()：修改分类时根据分类名称来查询分类
//     getType()：跳转修改分类页面时根据id查询分类
@Mapper
@Repository
public interface TypeDao {
    //新增保存分类
    int saveType(Type type);
    
    //根据id查询分类
    Type getType(Long id);
    
    //查询所有分类
    List<Type> getAllType();
    
    //根据分类名称查询分类
    Type getTypeByName(String name);
    
    //编辑修改分类
    int updateType(Type type);
    
    //删除分类
    void deleteType(Long id);

    // 查询所有分类
    List<Type> getAllTypeAndBlog();
    
    
}
