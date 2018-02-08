package com.zyht.service;/********************************************************************/
/**
 * @Project: java_practice
 * @Package service
 * @author caoxin
 * @date 2018/1/25 15:25
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.zyht.common.util.JdbcUtils;
import com.zyht.dao.GoodsSellerRelationDaoImpl;
import com.zyht.domain.GoodsSellerRelation;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author caoxin
 * @ClassName GoodsSellerRelationServiceImpl
 * @Description 商品卖家关系表服务接口实现类
 * @date 2018/1/25
 */
@Service("goodsSellerRelationService")
public class GoodsSellerRelationServiceImpl implements GoodsSellerRelationService {
    /**
     * @Title: deleteGoodsSellerRelationById
     * @Description: 通过主键ID删除
     * @author caoxin
     * @date 2018/1/25
     * @param id
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteGoodsSellerRelationById(Long id){
        Integer operatedRows=null;
        GoodsSellerRelationDaoImpl goodsSellerRelationDaoImpl=new GoodsSellerRelationDaoImpl();
        //连接数据库
        Connection connection=JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement=null;
        //根据传入的id删除表中数据
        try {
            connection.setAutoCommit(false);
            operatedRows=goodsSellerRelationDaoImpl.deleteGoodsSellerRelationById(id,connection,preparedStatement);
        } catch (SQLException e) {
            try {
                //发生异常回滚
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            //关闭连接和预编译语句
            JdbcUtils.release(connection,preparedStatement);
        }
        return operatedRows;
    }
    /**
     * @Title: deleteGoodsSellerRelationByIds
     * @Description: 通过主键ID批量删除商品卖家关系
     * @author caoxin
     * @date 2018/1/25
     * @param  ids
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteGoodsSellerRelationByIds(Long[] ids){
        Integer operatedRows=null;
        GoodsSellerRelationDaoImpl goodsSellerRelationDaoImpl=new GoodsSellerRelationDaoImpl();
        //连接数据库
        Connection connection=JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement=null;
        //根据传入的ids批量删除表中数据
        try {
            connection.setAutoCommit(false);
            operatedRows=goodsSellerRelationDaoImpl.deleteGoodsSellerRelationByIds(ids,connection,preparedStatement);
        } catch (SQLException e) {
            try {
                //发生异常回滚
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            //关闭连接和预编译语句
            JdbcUtils.release(connection,preparedStatement);
        }
        return operatedRows;
    }
    /**
     * @Title: updateGoodsSellerRelation
     * @Description: 修改商品卖家关系信息
     * @author caoxin
     * @date 2018/1/25
     * @param goodsSellerRelation
     * @return domain.GoodsSellerRelation
     * @throw SQLException
     */
    @Override
    public GoodsSellerRelation updateGoodsSellerRelation(GoodsSellerRelation goodsSellerRelation){
        GoodsSellerRelationDaoImpl goodsSellerRelationDaoImpl=new GoodsSellerRelationDaoImpl();
        //连接数据库
        Connection connection=JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement=null;
        //更新goodsSellerRelation的数据
        try {
            connection.setAutoCommit(false);
            goodsSellerRelation=goodsSellerRelationDaoImpl.updateGoodsSellerRelation(goodsSellerRelation,connection,preparedStatement);
        } catch (SQLException e) {
            try {
                //发生异常回滚
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            //关闭连接和预编译语句
            JdbcUtils.release(connection,preparedStatement);
        }
        return goodsSellerRelation;
    }
    /**
     * @Title: insertGoodsSellerRelation
     * @Description: 添加商品卖家关系信息
     * @author caoxin
     * @date 2018/1/25
     * @param goodsSellerRelation
     * @return domain.GoodsSellerRelation
     * @throw SQLException
     */
    @Override
    public GoodsSellerRelation insertGoodsSellerRelation(GoodsSellerRelation goodsSellerRelation){
        GoodsSellerRelationDaoImpl goodsSellerRelationDaoImpl=new GoodsSellerRelationDaoImpl();
        //连接数据库
        Connection connection=JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement=null;
        //将goodsSellerRelation插入good_seller_relation表中
        try {
            connection.setAutoCommit(false);
            goodsSellerRelationDaoImpl.insertGoodsSellerRelation(goodsSellerRelation,connection,preparedStatement);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            //关闭连接和预编译语句
            JdbcUtils.release(connection,preparedStatement);
        }
        return goodsSellerRelation;
    }

    /**
     * @Title: queryGoodsSellerRelationById
     * @Description: 通过ID查询商品卖家关系信息表
     * @author caoxin
     * @date 2018/1/25
     * @param id
     * @return domain.GoodsSellerRelation
     * @throw SQLException
     */
    @Override
    public GoodsSellerRelation queryGoodsSellerRelationById(Long id) {
        GoodsSellerRelation goodsSellerRelation=null;
        GoodsSellerRelationDaoImpl goodsSellerRelationDaoImpl=new GoodsSellerRelationDaoImpl();
        //连接数据库
        Connection connection=JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement=null;
        //通过传入的id查询goods_seller_relation表
        try {
            goodsSellerRelation=goodsSellerRelationDaoImpl.queryGoodsSellerRelationById(id,connection,preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(connection,preparedStatement);
        }
        return goodsSellerRelation;
    }
    /**
     * @Title: queryGoodsSellerRelationByCondition
     * @Description: 通过特定条件查询买家表
     * @author caoxin
     * @date 2018/1/25
     * @param stringGoodsSellerRelationMap, connection, preparedStatement
     * @return java.util.List<domain.GoodsSellerRelation>
     * @throw SQLException
     */

    @Override
    public List<GoodsSellerRelation> queryGoodsSellerRelationByCondition(Map<String, String> stringGoodsSellerRelationMap) {
        List<GoodsSellerRelation> goodsSellerRelations=null;
        GoodsSellerRelationDaoImpl goodsSellerRelationDaoImpl=new GoodsSellerRelationDaoImpl();
        //连接数据库
        Connection connection=JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement=null;
        //根据特定条件查询goods_seller_relation表并返回查询结果集合
        try {
            goodsSellerRelations=goodsSellerRelationDaoImpl.queryGoodsSellerRelationByCondition(stringGoodsSellerRelationMap,connection,preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(connection,preparedStatement);
        }
        return goodsSellerRelations;
    }
    /**
     * @Title: queryGoodsSellerRelationByCondition
     * @Description: 通过特定条件查询买家表并分页显示
     * @author caoxin
     * @date 2018/1/25
     * @param stringGoodsSellerRelationMap,startRow,size
     * @return java.util.List<domain.GoodsSellerRelation>
     * @throw SQLException
     */
    @Override
    public List<GoodsSellerRelation> queryGoodsSellerRelationByCondition(Map<String, String> stringGoodsSellerRelationMap, Integer startRow, Integer size){
        List<GoodsSellerRelation> goodsSellerRelations=null;
        GoodsSellerRelationDaoImpl goodsSellerRelationDaoImpl=new GoodsSellerRelationDaoImpl();
        //连接数据库
        Connection connection=JdbcUtils.getConnection();
        //预编译语句
        PreparedStatement preparedStatement=null;
        //根据特定条件查询goods_seller_relation表并返回分页后的查询结果集合
        try {
            goodsSellerRelations=goodsSellerRelationDaoImpl.queryGoodsSellerRelationByCondition(stringGoodsSellerRelationMap,startRow,size,connection,preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(connection,preparedStatement);
        }
        return goodsSellerRelations;
    }
}
