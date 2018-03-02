package com.zyht.dao;/********************************************************************/
/**
 * @Project: java_practice
 * @Package dao
 * @author caoxin
 * @date 2018/1/19 22:18
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.zyht.domain.Goods;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caoxin
 * @ClassName GoodsDaoImpl
 * @Description 商品表接口实现类
 * @date 2018/1/19
 */
@Repository("goodsDao")
public class GoodsDaoImpl  implements GoodsDao {
    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;
//通过ID删除数据
    @Override
    public Integer deleteGoodsById(Long id)  {
        return  this.sqlSessionTemplate.delete("com.zyht.domain.Goods." + "delete", id);
    }
//通过ID批量删除
    @Override
    public Integer deleteGoodsByIds(Long[] ids)  {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("ids",ids);
        return  this.sqlSessionTemplate.delete("com.zyht.domain.Goods." + "deleteGoodsByIds", map);
    }
//插入数据
    @Override
    public Integer insertGoods(Goods goods)  {
        return  this.sqlSessionTemplate.insert("com.zyht.domain.Goods." + "insert", goods);
    }
//更新数据
    @Override
    public Integer updateGoods(Goods goods)  {
        return  this.sqlSessionTemplate.update("com.zyht.domain.Goods."+"update",goods);
    }
//通过ID查询
    @Override
    public Goods queryGoodsById(Long id) {
        return  this.sqlSessionTemplate.selectOne("com.zyht.domain.Goods."+"getById",id);
    }
//通过IDS批量查询
    @Override
    public List<Goods> queryGoodsByCondition(Map<String, Object> stringGoodsMap)  {

        return this.sqlSessionTemplate.selectList("com.zyht.domain.Goods."+"queryGoodsByCondition",stringGoodsMap);
    }
//分页查询
    @Override
    public List<Goods> queryGoodsByCondition(Map<String,Object> stringGoodsMap, Integer startRow, Integer size)  {
        return this.sqlSessionTemplate.selectList("com.zyht.domain.Goods."+"queryGoodsByCondition",stringGoodsMap);
    }
    /**
     * @Title: deleteGoodsById
     * @Description: 通过买家ID删除
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */

}
