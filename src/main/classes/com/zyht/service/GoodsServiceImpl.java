package com.zyht.service;
import com.zyht.common.util.JdbcUtils;
import com.zyht.common.util.SpringContextUtil;
import com.zyht.dao.GoodsDao;
import com.zyht.dao.GoodsDaoImpl;
import com.zyht.domain.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * GoodsServiceImpl
 *
 * @author PIN
 * @Description
 * @Date 2018/1/24
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
//    GoodsDao goodsDao =new GoodsDaoImpl();
    @Autowired
    private GoodsDao goodsDao;
/**
 * @ClassName deleteGoodsById
 * @Description 根据商品ID删除商品
 * @author guoxin
 * @Date 2018/1/25
 * @param id
 * @return  respond
*/
    @Override
    public Integer deleteGoodsById(Long id)  {
        return goodsDao.deleteGoodsById(id);
    }

/**
 * @ClassName deleteGoodsByIds
 * @Description 根据商品Ids撒谎年初多个商品
 * @author guoxin
 * @Date 2018/1/25
 * @param ids
 * @return respond
*/
    @Override
    public Integer  deleteGoodsByIds(Long[] ids)  {

            return  goodsDao.deleteGoodsByIds(ids);                                                           //返回结果

    }
/**
 * @ClassName updateGoods
 * @Description 更新商品
 * @author guoxin
 * @Date 2018/1/25
 * @param goods
 * @return respond
*/
    @Override
    public Integer updateGoods(Goods goods) {

        return goodsDao.updateGoods(goods);
    }
/**
 * @ClassName insertGoods
 * @Description 插入一个商品的信息
 * @author guoxin
 * @Date 2018/1/25
 * @param goods
 * @return respond
*/
    @Override
    public Integer insertGoods(Goods goods){

        return goodsDao.insertGoods(goods);
    }
/**
 * @ClassName queryGoods
 * @Description 根据商品id查询一个商品的信息
 * @author guoxin
 * @Date 2018/1/25
 * @param id
 * @return result
*/
    @Override
    public Goods queryGoodsById(Long id) {

        return goodsDao.queryGoodsById(id);
    }
/**
 * @ClassName queryGoodsByCondition
 * @Description 多个条件查询商品信息
 * @author guoxin
 * @Date 2018/1/25
 * @param  stringGoodsMap
 * @return stringGoodsMap
*/
    @Override
    public   List<Goods>  queryGoodsByCondition(Map<String, Object> stringGoodsMap){

        return goodsDao.queryGoodsByCondition(stringGoodsMap);
    }
    /**
     * @ClassName queryGoodsByCondition
     * @Description 多条件查询并且分页显示
     * @author guoxin
     * @Date 2018/1/25
     * @param stringBuyerMap  startRow size
     * @return stringBuyerMap
    */
    @Override
    public   List<Goods>  queryGoodsByCondition(Map<String, Object> stringBuyerMap, Integer startRow, Integer size)  {

        return goodsDao.queryGoodsByCondition(stringBuyerMap,startRow,size);                           //返回查询的信息给用户
    }
    }
