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
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author caoxin
 * @ClassName GoodsDaoImpl
 * @Description 商品表接口实现类
 * @date 2018/1/19
 */
@Repository("goodsDao")
public class GoodsDaoImpl implements GoodsDao {
    /**
     * @Title: deleteGoodsById
     * @Description: 通过买家ID删除
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteGoodsById(Long id, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement=connection.prepareStatement("DELETE FROM goods WHERE ID=?");
        preparedStatement.setLong(1,id);
        Integer operatedRow=preparedStatement.executeUpdate();
        connection.commit();
        return operatedRow;
    }
    /**
     * @Title: deleteGoodsByIds
     * @Description: 通过ID批量删除买家
     * @author caoxin
     * @date 2018/1/19
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteGoodsByIds(Long[] ids, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        for(Long id:ids){
            preparedStatement=connection.prepareStatement("DELETE FROM goods WHERE ID=?");
            preparedStatement.setLong(1,id);
        }
        Integer operatedRows=preparedStatement.executeUpdate();
        connection.commit();
        return operatedRows;
    }
    /**
     * @Title: updateGoods
     * @Description: 修改买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param goods, connection, preparedStatement
     * @return domain.Goods
     * @throw SQLException
     */
    @Override
    public Integer updateGoods(Goods goods, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement=connection.prepareStatement("UPDATE goods SET NAME=?,NUMBER=?,TYPE=?,PRICE=?,ADDR=?,MAKE_TIME=?,EXPIRATION_TIME=?,UNIT=? WHERE ID=? ;");
        preparedStatement.setLong((int)9,goods.getId());
        preparedStatement.setString(1, goods.getName());
        preparedStatement.setString(2, goods.getNumber());
        preparedStatement.setString(3, goods.getType());
        preparedStatement.setDouble((int)4, goods.getPrice());
        preparedStatement.setString((int)5, goods.getAddr());
        preparedStatement.setDate((int)6, (Date) goods.getMakeTime());
        preparedStatement.setDate((int)7, (Date) goods.getExpirationTime());

        preparedStatement.setString((int)8,goods.getUnit());
        Integer operatedRow=preparedStatement.executeUpdate();
        connection.commit();
        return operatedRow;
    }
    /**
     * @Title: insertGoods
     * @Description: 添加买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param Goods, connection, preparedStatement
     * @return domain.Goods
     * @throw SQLException
     */
    @Override
    public Goods insertGoods(Goods Goods, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = null;
         Goods goods=null;
        preparedStatement=connection.prepareStatement("INSERT INTO goods( NAME,NUMBER,TYPE,PRICE,ADDR,MAKE_TIME,EXPIRATION_TIME,UNIT;)VALUES(?,?,?,?,?,?,?,?,?,?);");
        preparedStatement.setString((int)1, goods.getName());
        preparedStatement.setString((int)2, goods.getNumber());
        preparedStatement.setString((int)3, goods.getType());
        preparedStatement.setDouble((int)4, goods.getPrice());
        preparedStatement.setString((int)5, goods.getAddr());
        Date sqlDate=new Date(goods.getMakeTime().getTime());
        preparedStatement.setDate((int)6,sqlDate);
        sqlDate=new Date(goods.getExpirationTime().getTime());
        preparedStatement.setDate((int)7,sqlDate);
        preparedStatement.setString((int) 8, goods.getUnit());
        preparedStatement.getGeneratedKeys();
        resultSet.getInt(1);
        connection.commit();
        return Goods;
    }
    /**
     * @Title: queryGoodsById
     * @Description: 通过ID查询商品信息表
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return domain.Goods
     * @throw SQLException
     */
    @Override
    public Goods queryGoodsById(Long id, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = null;
        Goods goods=null;

        preparedStatement=connection.prepareStatement("SELECT  NAME,TYPE,NUMBER ,PRICE ,ID FROM goods WHERE ID=?;");
        preparedStatement.setLong(1,id);
        //获取查询的结果集
        resultSet=preparedStatement.executeQuery();
        //若非空，开始遍历结果集并给Goods赋值
        if(resultSet!=null){
            while(resultSet.next()){
                goods=new Goods();
                goods.setName(resultSet.getString(1));
                goods.setType(resultSet.getString(2));
                goods.setNumber(resultSet.getString(3));
                goods.setPrice(resultSet.getDouble(4));
                goods.setId(resultSet.getLong(5));
            }
            resultSet.close();
            return goods;
        }else{
            //结果集为空返回null
            return null;
        }
    }
    /**
     * @Title: queryGoodsByCondition
     * @Description: 通过特定条件查询商品表
     * @author caoxin
     * @date 2018/1/19
     * @param stringGoodsMap, connection, preparedStatement
     * @return java.util.List<domain.Goods>
     * @throw SQLException
     */

    @Override
    public List<Goods> queryGoodsByCondition(Map<String, String> stringGoodsMap, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        //取出stringGoodsMap中的鍵集合
        Set<String> keySet=stringGoodsMap.keySet();
        //储存查询结果集
        List<Goods> goodsList=null;
        goodsList=new ArrayList<Goods>();
        ResultSet resultSet = null;
        Goods goods=null;
        //遍历键集，取出对应值并通过键值对条件进行查询
        for(String key:keySet){
            String keyValue="1";
            if(stringGoodsMap!=null) {
                preparedStatement = connection.prepareStatement("SELECT NAME,TYPE,NUMBER,PRICE FROM goods WHERE ?=?;");
                preparedStatement.setString(1, key);
                preparedStatement.setString(2, keyValue);
                resultSet = preparedStatement.executeQuery();
                System.out.println(resultSet);
                if (resultSet != null) {
                    while (resultSet.next()) {
                        goods = new Goods();
                        goods.setName(resultSet.getString(1));
                        goods.setType(resultSet.getString(2));
                        goods.setNumber(resultSet.getString(3));
                        goods.setPrice(resultSet.getDouble(4));
                        goodsList.add(goods);
                    }
        //            resultSet.close();
                }
                return goodsList;
            }else {
                preparedStatement = connection.prepareStatement("SELECT NAME,TYPE,NULLABLE,PRICE FROM goods ;");
                preparedStatement.setString(1, key);
                preparedStatement.setString(2, keyValue);
                resultSet = preparedStatement.executeQuery();
                if (resultSet != null) {
                    while (resultSet.next()) {
                        goods = new Goods();
                        goods.setName(resultSet.getString(1));
                        goods.setType(resultSet.getString(2));
                        goods.setPrice(resultSet.getDouble(3));
                        goodsList.add(goods);
                    }
                    resultSet.close();
                }
            }
        }
        return goodsList;
    }
    /**
     * @Title: queryGoodsByCondition
     * @Description: 通过特定条件查询商品表并将结果分页显示
     * @author caoxin
     * @date 2018/1/19
     * @param stringGoodsMap, connection, preparedStatement
     * @return java.util.List<domain.Goods>
     * @throw SQLException
     */
    @Override
    public List<Goods> queryGoodsByCondition(Map<String, String> stringGoodsMap, Integer startRow, Integer size, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        List<Goods> goodsList=null;
        ResultSet resultSet = null;
        Goods goods=null;  //取出stringGoodsMap中的鍵集合
        Set<String> keySet=stringGoodsMap.keySet();
        //储存查询结果集
        goodsList=new ArrayList<Goods>();
        //遍历键集，取出对应值并通过键值对条件进行查询并对结果进行分页显示
        for(String key:keySet){
            String keyValue=stringGoodsMap.get(key);
            preparedStatement=connection.prepareStatement("SELECT NAME,TYPE,NUMBER ,PRICE FROM goods WHERE ?=? ;");
            preparedStatement.setString(1,key);
            preparedStatement.setString(2,keyValue);
            resultSet=preparedStatement.executeQuery();
            //指针定位到要startRow行开始输出记录
            resultSet.absolute(startRow);
            // 设置最大查询记录条数
            preparedStatement.setMaxRows(size);
            if(resultSet!=null){
                while(resultSet.next()){
                    goods=new Goods();
                    goods.setName(resultSet.getString(1));
                    goods.setType(resultSet.getString(2));
                    goods.setPrice(resultSet.getDouble(3));
                    goodsList.add(goods);
                }
                resultSet.close();
            }else {
                return null;
            }
        }
        return goodsList;
    }

}
