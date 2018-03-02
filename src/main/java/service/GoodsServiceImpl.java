package com.zyht.service;
import com.zyht.common.util.JdbcUtils;
import com.zyht.common.util.SpringContextUtil;
import com.zyht.dao.GoodsDao;
import com.zyht.dao.GoodsDaoImpl;
import com.zyht.domain.Goods;
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
@Service("GoodsService")
public class GoodsServiceImpl implements GoodsService {
    GoodsDao goodsDao =new GoodsDaoImpl();
/**
 * @ClassName deleteGoodsById
 * @Description 根据商品ID删除商品
 * @author guoxin
 * @Date 2018/1/25
 * @param id
 * @return  respond
*/
    @Override
    public Integer deleteGoodsById(Long id) throws SQLException {
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
    public Integer  deleteGoodsByIds(Long[] ids) throws SQLException {

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
    public Integer updateGoods(Goods goods) throws SQLException {

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
    public Goods insertGoods(Goods goods) throws SQLException {

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
    public Goods queryGoodsById(Long id) throws SQLException {

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
    public   List<Goods>  queryGoodsByCondition(Map<String, String> stringGoodsMap) throws SQLException {

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
    public   List<Goods>  queryGoodsByCondition(Map<String, String> stringBuyerMap, Integer startRow, Integer size) throws SQLException {

        return goodsDao.queryGoodsByCondition(stringBuyerMap,startRow,size);                           //返回查询的信息给用户
    }
    }
