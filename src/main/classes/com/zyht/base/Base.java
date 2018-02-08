package com.zyht.base;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author denghongbo
 * @ClassName Base
 * @Package com.zyht.base
 * @Description 类描述
 * @date 2018/2/6
 */
public class Base<T> {
    @Resource
    protected SqlSessionTemplate sqlSessionTemplate;

    private Class<T> cls = null;

    public Base() {
        Class cla = this.getClass();
        ParameterizedType type = (ParameterizedType)cla.getGenericSuperclass();
        Type[] types = type.getActualTypeArguments();
        cls = (Class<T>) types[0];
    }

    public String getMybaitsNameSpace(){
        return  cls.getName()+".";
    }
}