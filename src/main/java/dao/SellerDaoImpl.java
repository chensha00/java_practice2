package dao;/********************************************************************/
/**
 * @Project: java_practice
 * @Package dao
 * @author caoxin
 * @date 2018/1/19 23:14
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import domain.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author caoxin
 * @ClassName SellerDaoImpl
 * @Description 类描述
 * @date 2018/1/19
 */
public class SellerDaoImpl implements SellerDao {
    /**
     * 查询结果集
     */
    private ResultSet resultSet=null;
    /**
     *卖家信息
     */
    private Seller seller=null;
    /**
     *卖家信息集合
     */
    private List<Seller> sellers=null;
    /**
     * @Title: deleteSellerById
     * @Description: 通过卖家ID删除
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteSellerById(Long id, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement=connection.prepareStatement("DELETE FROM seller WHERE ID=?");
        preparedStatement.setLong(1,id);
        Integer operatedRow=preparedStatement.executeUpdate();
        connection.commit();
        return operatedRow;
    }
    /**
     * @Title: deleteSellerByIds
     * @Description: 通过ID批量删除卖家
     * @author caoxin
     * @date 2018/1/19
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteSellerByIds(Long[] ids, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        for(Long id:ids){
            preparedStatement=connection.prepareStatement("DELETE FROM seller WHERE ID=?");
            preparedStatement.setLong(1,id);
        }
        Integer operatedRows=preparedStatement.executeUpdate();
        connection.commit();
        return operatedRows;
    }
    /**
     * @Title: updateSeller
     * @Description: 修改卖家信息
     * @author caoxin
     * @date 2018/1/19
     * @param Seller, connection, preparedStatement
     * @return domain.Seller
     * @throw SQLException
     */
    @Override
    public Seller updateSeller(Seller Seller, Connection connection, PreparedStatement preparedStatement) throws SQLException {

        preparedStatement=connection.prepareStatement("UPDATE Seller SET NAME=?,SEX=?,AGE=?,ID_NUMBER=?,TEL=?,ADDR=?,PROFESSION=?,WORK_UNIT=?,SAVING=? WHERE ID=?;");
        preparedStatement.setLong(10,Seller.getId());
        preparedStatement.setString(1,Seller.getName());
        preparedStatement.setString(2, Seller.getSex());
        preparedStatement.setByte(3, Seller.getAge());
        preparedStatement.setString(4, Seller.getIdNumber());
        preparedStatement.setString(5,Seller.getTel());
        preparedStatement.setString(6,Seller.getAddr());
        preparedStatement.setString(7,Seller.getProfession());
        preparedStatement.setString(8,Seller.getWorkUnit());
        preparedStatement.setDouble(9,Seller.getSaving());
        connection.commit();
        return Seller;
    }
    /**
     * @Title: insertSeller
     * @Description: 添加卖家信息
     * @author caoxin
     * @date 2018/1/19
     * @param Seller, connection, preparedStatement
     * @return domain.Seller
     * @throw SQLException
     */
    @Override
    public Seller insertSeller(Seller Seller, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement=connection.prepareStatement("INSERT INTO Seller(NAME,SEX,AGE,ID_NUMBER,TEL,ADDR,PROFESSION,WORK_UNIT,SAVING)VALUES(?,?,?,?,?,?,?,?,?);");
        preparedStatement.setString(1,Seller.getName());
        preparedStatement.setString(2, Seller.getSex());
        preparedStatement.setByte(3, Seller.getAge());
        preparedStatement.setString(4, Seller.getIdNumber());
        preparedStatement.setString(5,Seller.getTel());
        preparedStatement.setString(6,Seller.getAddr());
        preparedStatement.setString(7,Seller.getProfession());
        preparedStatement.setString(8,Seller.getWorkUnit());
        preparedStatement.setDouble(9,Seller.getSaving());
        connection.commit();
        return Seller;
    }

    /**
     * @Title: querySellerById
     * @Description: 通过ID查询卖家信息表
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return domain.Seller
     * @throw SQLException
     */
    @Override
    public Seller querySellerById(Long id, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement=connection.prepareStatement("SELECT NAME,SEX,ID_NUMBER,SAVING FROM Seller WHERE ID=?;");
        preparedStatement.setLong(1,id);
        //获取查询的结果集
        resultSet=preparedStatement.executeQuery();
        //若非空，开始遍历结果集并给Seller赋值
        if(resultSet!=null){
            while(resultSet.next()){
                seller=new Seller();
                seller.setName(resultSet.getString(1));
                seller.setSex(resultSet.getString(2));
                seller.setIdNumber(resultSet.getString(3));
                seller.setSaving(resultSet.getDouble(4));
            }
            resultSet.close();
            return seller;
        }else{
            //结果集为空返回null
            return null;
        }
    }
    /**
     * @Title: querySellerByCondition
     * @Description: 通过特定条件查询卖家表
     * @author caoxin
     * @date 2018/1/19
     * @param stringSellerMap, connection, preparedStatement
     * @return java.util.List<domain.Seller>
     * @throw SQLException
     */

    @Override
    public List<Seller> querySellerByCondition(Map<String, String> stringSellerMap, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        //取出stringSellerMap中的鍵集合
        Set<String> keySet=stringSellerMap.keySet();
        //储存查询结果集
        sellers=new ArrayList<Seller>();
        //遍历键集，取出对应值并通过键值对条件进行查询
        for(String key:keySet){
            String keyValue=stringSellerMap.get(key);
            preparedStatement=connection.prepareStatement("SELECT NAME,SEX,ID_NUMBER,SAVING FROM Seller WHERE ?=?;");
            preparedStatement.setString(1,key);
            preparedStatement.setString(2,keyValue);
            resultSet=preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    seller=new Seller();
                    seller.setName(resultSet.getString(1));
                    seller.setSex(resultSet.getString(2));
                    seller.setIdNumber(resultSet.getString(3));
                    seller.setSaving(resultSet.getDouble(4));
                    sellers.add(seller);
                }
                resultSet.close();
            }else {
                return null;
            }
        }
        return sellers;
    }
    /**
     * @Title: querySellerByCondition
     * @Description: 通过特定条件查询卖家表并分页显示
     * @author caoxin
     * @date 2018/1/19
     * @param stringSellerMap, connection, preparedStatement
     * @return java.util.List<domain.Seller>
     * @throw SQLException
     */
    @Override
    public List<Seller> querySellerByCondition(Map<String, String> stringSellerMap, Integer startRow, Integer size, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        //取出stringSellerMap中的鍵集合
        Set<String> keySet=stringSellerMap.keySet();
        //储存查询结果集
        sellers=new ArrayList<Seller>();
        //遍历键集，取出对应值并通过键值对条件进行查询并对结果进行分页显示
        for(String key:keySet){
            String keyValue=stringSellerMap.get(key);
            preparedStatement=connection.prepareStatement("SELECT NAME,SEX,ID_NUMBER,SAVING FROM Seller WHERE ?=? ;");
            preparedStatement.setString(1,key);
            preparedStatement.setString(2,keyValue);
            resultSet=preparedStatement.executeQuery();
            //指针定位到要startRow行开始输出记录
            resultSet.absolute(startRow);
            // 设置最大查询记录条数
            preparedStatement.setMaxRows(size);
            if(resultSet!=null){
                while(resultSet.next()){
                    seller=new Seller();
                    seller.setName(resultSet.getString(1));
                    seller.setSex(resultSet.getString(2));
                    seller.setIdNumber(resultSet.getString(3));
                    seller.setSaving(resultSet.getDouble(4));
                    sellers.add(seller);
                }
                resultSet.close();
            }else {
                return null;
            }
        }
        return sellers;
    }
}