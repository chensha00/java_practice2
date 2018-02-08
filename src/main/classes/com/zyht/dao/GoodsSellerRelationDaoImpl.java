package com.zyht.dao;/********************************************************************/
/**
 * @Project: java_practice
 * @Package dao
 * @author caoxin
 * @date 2018/1/25 14:26
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.zyht.domain.GoodsSellerRelation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author caoxin
 * @ClassName GoodsSellerRelationDaoImpl
 * @Description 类描述
 * @date 2018/1/25
 */
public class GoodsSellerRelationDaoImpl implements GoodsSellerRelationDao {
    /**
     * @Title: deleteGoodsSellerRelationById
     * @Description: 通过主键ID删除
     * @author caoxin
     * @date 2018/1/25
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteGoodsSellerRelationById(Long id, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        Integer operatedRows=null;
        //根据传入的id在GoodsSellerRelation表中删除对应的数据并返回受影响的行数
        preparedStatement=connection.prepareStatement("DELETE FROM goods_seller_relation WHERE ID=?;");
        preparedStatement.setLong(1,id);
        operatedRows=preparedStatement.executeUpdate();
        connection.commit();
        return operatedRows;
    }
    /**
     * @Title: deleteGoodsSellerRelationByIds
     * @Description: 通过主键ID批量删除商品卖家关系
     * @author caoxin
     * @date 2018/1/25
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteGoodsSellerRelationByIds(Long[] ids, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        Integer operatedRows=null;
        //根据传入的id数据删除数据
        for(Long id:ids){
            preparedStatement=connection.prepareStatement("DELETE FROM goods_seller_relation WHERE ID=?;");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            operatedRows++;
        }
        connection.commit();
        return operatedRows;
    }
    /**
     * @Title: updateGoodsSellerRelation
     * @Description: 修改商品卖家关系信息
     * @author caoxin
     * @date 2018/1/25
     * @param goodsSellerRelation, connection, preparedStatement
     * @return domain.GoodsSellerRelation
     * @throw SQLException
     */
    @Override
    public GoodsSellerRelation updateGoodsSellerRelation(GoodsSellerRelation goodsSellerRelation, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        //根据ID更新GoodsSellerRelation表的INVENTORY,PRICE,GOODS_ID,SELLER_ID列
        preparedStatement=connection.prepareStatement("UPDATE goods_seller_relation SET INVENTORY=?,PRICE=?,GOODS_ID=?,SELLER_ID=?,NAME=? WHERE ID=?;");
        preparedStatement.setDouble(1,goodsSellerRelation.getInventory());
        preparedStatement.setDouble(2, goodsSellerRelation.getPrice());
        preparedStatement.setLong(3, goodsSellerRelation.getGoodsId());
        preparedStatement.setLong(4, goodsSellerRelation.getSellerId());
        preparedStatement.setString(5, goodsSellerRelation.getName());
        preparedStatement.setLong(6,goodsSellerRelation.getId());
        connection.commit();
        return goodsSellerRelation;
    }
    /**
     * @Title: insertGoodsSellerRelation
     * @Description: 添加商品卖家关系信息
     * @author caoxin
     * @date 2018/1/25
     * @param goodsSellerRelation, connection, preparedStatement
     * @return domain.GoodsSellerRelation
     * @throw SQLException
     */
    @Override
    public GoodsSellerRelation insertGoodsSellerRelation(GoodsSellerRelation goodsSellerRelation, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet=null;
        //将goodsSellerRelation添加到goods_seller_relation表中
        preparedStatement=connection.prepareStatement("INSERT INTO goods_seller_relation(NAME,INVENTORY,PRICE,GOODS_ID,SELLER_ID)VALUES(?,?,?,?,?);");
        preparedStatement.setString(1,goodsSellerRelation.getName());
        preparedStatement.setDouble(2, goodsSellerRelation.getInventory());
        preparedStatement.setDouble(3, goodsSellerRelation.getPrice());
        preparedStatement.setLong(4, goodsSellerRelation.getGoodsId());
        preparedStatement.setLong(5, goodsSellerRelation.getSellerId());
        resultSet= preparedStatement.getGeneratedKeys();
        resultSet.getInt(1);
        connection.commit();
        return goodsSellerRelation;
    }

    /**
     * @Title: queryGoodsSellerRelationById
     * @Description: 通过ID查询商品卖家关系信息表
     * @author caoxin
     * @date 2018/1/25
     * @param id, connection, preparedStatement
     * @return domain.GoodsSellerRelation
     * @throw SQLException
     */
    @Override
    public GoodsSellerRelation queryGoodsSellerRelationById(Long id, Connection connection, PreparedStatement preparedStatement) throws SQLException {
         ResultSet resultSet=null;
        GoodsSellerRelation goodsSellerRelation=null;
        //从GoodsSellerRelation表中根据传入的id查询出买家所有信息
        preparedStatement=connection.prepareStatement("SELECT ID, NAME,INVENTORY,PRICE,GOODS_ID,SELLER_ID FROM goods_seller_relation WHERE ID=?;");
        preparedStatement.setLong(1,id);
        //获取查询的结果集
        resultSet=preparedStatement.executeQuery();
        //若非空，开始遍历结果集并给GoodsSellerRelation赋值
        if(resultSet!=null){
            while(resultSet.next()){
                goodsSellerRelation=new GoodsSellerRelation();
                goodsSellerRelation.setId(resultSet.getLong(1));
                goodsSellerRelation.setName(resultSet.getString(2));
                goodsSellerRelation.setInventory(resultSet.getDouble(3));
                goodsSellerRelation.setPrice(resultSet.getDouble(4));
                goodsSellerRelation.setGoodsId(resultSet.getLong(5));
                goodsSellerRelation.setSellerId(resultSet.getLong(6));
            }
            return goodsSellerRelation;
        }else{
            //结果集为空返回null
            return null;
        }
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
    public List<GoodsSellerRelation> queryGoodsSellerRelationByCondition(Map<String, String> stringGoodsSellerRelationMap, Connection connection, PreparedStatement preparedStatement) throws SQLException {
//        ResultSet resultSet=null;
//        GoodsSellerRelation goodsSellerRelation=null;
//        List<GoodsSellerRelation> goodsSellerRelations=null;
//        String selectSql="SELECT ID,NAME,INVENTORY,PRICE,GOODS_ID,SELLER_ID FROM goods_seller_relation WHERE ";
//        //取出stringGoodsSellerRelationMap中的鍵集合
//        Set<String> keySet=stringGoodsSellerRelationMap.keySet();
//        //储存查询结果集
//        goodsSellerRelations=new ArrayList<GoodsSellerRelation>();
//        //遍历键集，取出对应值并通过键值对条件进行查询
//        for(String key:keySet){
//            String keyValue=stringGoodsSellerRelationMap.get(key);
//            selectSql=selectSql+key+"="+"'"+keyValue+"';";
//            preparedStatement=connection.prepareStatement(selectSql);
//            resultSet=preparedStatement.executeQuery();
//            if(resultSet!=null){
//                while(resultSet.next()){
//                    goodsSellerRelation=new GoodsSellerRelation();
//                    goodsSellerRelation.setId(resultSet.getLong(1));
//                    goodsSellerRelation.setName(resultSet.getString(2));
//                    goodsSellerRelation.setInventory(resultSet.getDouble(3));
//                    goodsSellerRelation.setPrice(resultSet.getDouble(4));
//                    goodsSellerRelation.setGoodsId(resultSet.getLong(5));
//                    goodsSellerRelation.setSellerId(resultSet.getLong(6));
//                    goodsSellerRelations.add(goodsSellerRelation);
//                }
//                resultSet.close();
//            }else {
//                return null;
//            }
//        }
//        return goodsSellerRelations;
        ResultSet resultSet;
        GoodsSellerRelation goodsSellerRelation;
        List<GoodsSellerRelation> goodsSellerRelationList = new ArrayList<GoodsSellerRelation>();
        String sql = null;
        if (stringGoodsSellerRelationMap != null) {
            //ID,NAME,INVENTORY,PRICE,GOODS_ID,SELLER_ID FROM goods_seller_relation
            sql = "SELECT\n" + " `ID`,\n" + "`NAME`,\n" + "`INVENTORY`,\n" + "`PRICE`,\n" + "`GOODS_ID`,\n" + "`SELLER_ID`\n" + "FROM\n" + " `goods_seller_relation` WHERE 1=1 ";
            Set<Map.Entry<String, String>> set = stringGoodsSellerRelationMap.entrySet();
            Iterator<Map.Entry<String, String>> iterator = set.iterator();
            if (iterator.hasNext()) {
                Map.Entry<String, String> map = iterator.next();
                sql =sql+ "AND"+ map.getKey() + " LIKE '%" + map.getValue() + "%'";
            }
            while (iterator.hasNext()) {
                Map.Entry<String, String> map = iterator.next();
                sql =sql+ "AND " + map.getKey() + " LIKE '%" + map.getValue() + "%'";
            }
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                goodsSellerRelation = new GoodsSellerRelation();
                goodsSellerRelation.setId(resultSet.getLong(1));
                goodsSellerRelation.setName(resultSet.getString(2));
                goodsSellerRelation.setInventory(resultSet.getDouble(3));
                goodsSellerRelation.setPrice(resultSet.getDouble(4));
                goodsSellerRelation.setGoodsId(resultSet.getLong(5));
                goodsSellerRelation.setSellerId(resultSet.getLong(6));
                goodsSellerRelationList.add(goodsSellerRelation);
            }
        }else{
            sql = "SELECT\n" + " `ID`,\n" + "`NAME`,\n" + "`INVENTORY`,\n" + "`PRICE`,\n" + "`GOODS_ID`,\n" + "`SELLER_ID`,\n" + "FROM\n" + "`goods_seller_relation` WHERE 1=1 ";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                goodsSellerRelation = new GoodsSellerRelation();
                goodsSellerRelation.setId(resultSet.getLong(1));
                goodsSellerRelation.setName(resultSet.getString(2));
                goodsSellerRelation.setInventory(resultSet.getDouble(3));
                goodsSellerRelation.setPrice(resultSet.getDouble(4));
                goodsSellerRelation.setGoodsId(resultSet.getLong(5));
                goodsSellerRelation.setSellerId(resultSet.getLong(6));
                goodsSellerRelationList.add(goodsSellerRelation);
            }
        }
        return goodsSellerRelationList;
    }
    /**
     * @Title: queryGoodsSellerRelationByCondition
     * @Description: 通过特定条件查询买家表并分页显示
     * @author caoxin
     * @date 2018/1/25
     * @param stringGoodsSellerRelationMap, connection, preparedStatement
     * @return java.util.List<domain.GoodsSellerRelation>
     * @throw SQLException
     */
    @Override
    public List<GoodsSellerRelation> queryGoodsSellerRelationByCondition(Map<String, String> stringGoodsSellerRelationMap, Integer startRow, Integer size, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet=null;
        GoodsSellerRelation goodsSellerRelation=null;
        List<GoodsSellerRelation> goodsSellerRelations=null;
        String selectSql="SELECT ID,NAME,INVENTORY,PRICE,GOODS_ID,SELLER_ID FROM goods_seller_relation WHERE ";
        //取出stringGoodsSellerRelationMap中的鍵集合
        Set<String> keySet = stringGoodsSellerRelationMap.keySet();
        //储存查询结果集
        goodsSellerRelations = new ArrayList<GoodsSellerRelation>();
        //遍历键集，取出对应值并通过键值对条件进行查询并对结果进行分页显示
        for (String key : keySet) {
            String keyValue = stringGoodsSellerRelationMap.get(key);
            selectSql=selectSql+key+"="+"'"+keyValue+"' LIMIT "+startRow+","+size+";";
            preparedStatement = connection.prepareStatement(selectSql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    goodsSellerRelation = new GoodsSellerRelation();
                    goodsSellerRelation.setId(resultSet.getLong(1));
                    goodsSellerRelation.setName(resultSet.getString(2));
                    goodsSellerRelation.setInventory(resultSet.getDouble(3));
                    goodsSellerRelation.setPrice(resultSet.getDouble(4));
                    goodsSellerRelation.setGoodsId(resultSet.getLong(5));
                    goodsSellerRelation.setSellerId(resultSet.getLong(6));
                    goodsSellerRelations.add(goodsSellerRelation);
                }
                resultSet.close();
            } else {
                return null;
            }
        }
        return goodsSellerRelations;
    }
}
