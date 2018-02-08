package com.zyht.service;
import com.zyht.common.util.JdbcUtils;
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
        Connection connection= JdbcUtils.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement=null;
        String respond=null;
        Integer result=null;
        try{
            connection.setAutoCommit(false);
             result = goodsDao.deleteGoodsById(id, connection, preparedStatement);
            if(result.intValue() == 0){
                respond = "删除失败！";
            }else {
                respond = "删除成功！";
            }
        }catch (SQLException e){
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                connection.rollback();//出现异常，事物回滚
            }
            e.printStackTrace();
        }
        finally {
            try {
                connection.commit();
                JdbcUtils.release(connection, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.release(connection,preparedStatement);//关闭连接
        }
        return result;
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
        Connection connection= JdbcUtils.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement=null;
        Integer result=null;
        String  respond = null;                                      //返回给用户的信息
        try{
            connection.setAutoCommit(false);
            result = goodsDao.deleteGoodsByIds(ids, connection, preparedStatement);
            if(result.doubleValue() == 0){
                respond = "删除失败！";
            }else {
                respond = "删除成功！";
            }
        }catch (SQLException e){
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                connection.rollback();//出现异常，事物回滚
            }
            e.printStackTrace();
        }
        finally {
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.release(connection,preparedStatement);                        //关闭连接
            return  result;                                                           //返回结果
        }
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
        Connection  connection= JdbcUtils.getConnection();
        PreparedStatement preparedStatement=null;
        Integer  result=null;
        String respond=null;
        try{
            connection.setAutoCommit(false);
            result = goodsDao.updateGoods(goods, connection, preparedStatement);
            if(result == 0){
                respond = "更新失败！";
                System.out.println(respond);
            }else {
                respond = "更新成功！";
                System.out.println(respond);
            }
        }catch (SQLException e){
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                connection.rollback();//出现异常，事物回滚
            }
            e.printStackTrace();
        }
        finally {
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.release(connection,preparedStatement);//关闭连接
        }
        return result;
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
        Connection connection= JdbcUtils.getConnection();
        PreparedStatement preparedStatement=null;
        Goods result=null;
        String respond=null;
        try{
            connection.setAutoCommit(false);
            result = goodsDao.insertGoods(goods, connection, preparedStatement);
            if(result == null){
                respond = "插入失败！";
            }else {
                respond = "插入成功！";
            }
        }catch (SQLException e){
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                connection.rollback();//出现异常，事物回滚
            }
            e.printStackTrace();
        }
        finally {
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
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
        //建立数据库连接
        Connection connection= JdbcUtils.getConnection();
        PreparedStatement preparedStatement=null;
        Goods result=null;
        //从数据库中查询，查询完毕关闭连接
        try{
             result = goodsDao.queryGoodsById(id,connection, preparedStatement);
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            JdbcUtils.release(connection,preparedStatement);
        }
        return result;
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
        Connection connection= JdbcUtils.getConnection();
        PreparedStatement preparedStatement=null;
        String respond=null;//返回给用户的字符串
        List<Goods> result =null;
        String str="";
        try{
            connection.setAutoCommit(false);
             result = goodsDao.queryGoodsByCondition(stringGoodsMap, connection, preparedStatement);
            if(result == null){
                respond = "查询失败！";
            }else {
                respond = "查询成功！";
            }
        }catch (SQLException e){
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                connection.rollback();//出现异常，事物回滚
            }
            e.printStackTrace();
        }
        finally {
            try {
                connection.commit();                               //提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.release(connection,preparedStatement);       //关闭连接
        }
        return result;
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
        Connection connection= JdbcUtils.getConnection();
        PreparedStatement preparedStatement=null;
        List<Goods> result=null;
        String respond=null;//返回给用户的字符串
        try{
            connection.setAutoCommit(false);
             result = goodsDao.queryGoodsByCondition(stringBuyerMap, connection, preparedStatement);

            if(result == null){
                respond = "查询失败！";
            }else {
                respond = "查询成功！";
            }
        }catch (SQLException e){
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                connection.rollback();//出现异常，事物回滚
            }
            e.printStackTrace();
        }
        finally {
            try {
                connection.commit();                //提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.release(connection,preparedStatement);//关闭连接
        }
        return result;                           //返回查询的信息给用户
    }
    }
