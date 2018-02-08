package service;
import common.util.JdbcUtils;
import dao.GoodsDao;
import dao.GoodsDaoImpl;
import domain.Goods;

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
    public String deleteGoodsById(Long id) throws SQLException {
        Connection connection= JdbcUtils.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement=null;
        String respond=null;
        try{
            connection.setAutoCommit(false);
            Integer result = goodsDao.deleteGoodsById(id, connection, preparedStatement);
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
        return respond;
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
    public String deleteGoodsByIds(Long[] ids) throws SQLException {
        Connection connection= JdbcUtils.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement=null;
        String  respond = null;                                      //返回给用户的信息
        try{
            connection.setAutoCommit(false);
            Integer result = goodsDao.deleteGoodsByIds(ids, connection, preparedStatement);
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
            return  respond;                                                           //返回结果
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
    public String updateGoods(Goods goods) throws SQLException {
        Connection  connection= JdbcUtils.getConnection();
        PreparedStatement preparedStatement=null;
        String respond=null;
        try{
            connection.setAutoCommit(false);
            int result = goodsDao.updateGoods(goods, connection, preparedStatement);
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
        return respond;
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
    public String insertGoods(Goods goods) throws SQLException {
        Connection connection= JdbcUtils.getConnection();
        PreparedStatement preparedStatement=null;
        String respond=null;
        try{
            connection.setAutoCommit(false);
            Goods result = goodsDao.insertGoods(goods, connection, preparedStatement);
            if(result == null){
                respond = "注销失败！";
            }else {
                respond = "注销成功！";
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
        return respond;
    }
/**
 * @ClassName queryGoods
 * @Description 根据商品id查询一个商品的信息
 * @author guoxin
 * @Date 2018/1/25
 * @param id
 * @return respond
*/
    @Override
    public String queryGoodsById(Long id) throws SQLException {
        Connection connection= JdbcUtils.getConnection();
        PreparedStatement preparedStatement=null;
        String respond=null;
        try{
            connection.setAutoCommit(false);
            Goods result = goodsDao.queryGoodsById(id,connection, preparedStatement);
            if(result == null){
                respond = "查询失败！";
            }else {
                respond = "查询成功！";
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
//            try {
//                connection.commit();//提交事务
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
        return respond;
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
    public  String queryGoodsByCondition(Map<String, String> stringGoodsMap) throws SQLException {
        Connection connection= JdbcUtils.getConnection();
        PreparedStatement preparedStatement=null;
        String respond=null;//返回给用户的字符串
        String str="";
        try{
            connection.setAutoCommit(false);
            List<Goods> result = goodsDao.queryGoodsByCondition(stringGoodsMap, connection, preparedStatement);
            if(result == null){
                respond = "查询失败！";
            }else {
                respond = "查询成功！";
            }
            System.out.print(result);                          //显示查询状态
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
        return respond;
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
    public  String queryGoodsByCondition(Map<String, String> stringBuyerMap, Integer startRow, Integer size) throws SQLException {
        Connection connection= JdbcUtils.getConnection();
        PreparedStatement preparedStatement=null;
        String respond=null;//返回给用户的字符串
        try{
            connection.setAutoCommit(false);
            List<Goods> result = goodsDao.queryGoodsByCondition(stringBuyerMap, connection, preparedStatement);

            if(result == null){
                respond = "查询失败！";
            }else {
                respond = "查询成功！";
            }
            System.out.println(respond);   //输出查询是否成功
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
        return respond;                           //返回查询的信息给用户
    }
    }
