package com.zzy.service.Impl;

import com.zzy.dao.TypeDao;
import com.zzy.entity.Type;

import com.zzy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ Description
 * @ author zhangziyao
 * @ date 2021/9/7 15:07
 */
@Service
public class TypeServiceImpl implements TypeService {
    
    @Autowired
    private TypeDao typeDao;
    
    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }
    
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }
    
    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }
    
    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }
    
    @Transactional
    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }
    
    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }
    
    @Transactional
    @Override
    public List<Type> getAllTypeAndBlog() {
        return typeDao.getAllTypeAndBlog();
    }
    
}
// @Service注解：用于标注业务层组件
//
// @Autowired注解：@Autowired表示被修饰的类需要注入对象,spring会扫描所有被@Autowired标注的类,然后根据类型在ioc容器中找到匹配的类注入
//
// @Transactional注解：实现事务操作