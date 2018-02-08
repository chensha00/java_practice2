package dao;/********************************************************************/
/**
* @Project: java_practice
* @Package dao
* @author caoxin
* @date 2018/1/19 17:16
* @Copyright: 2018 www.zyht.com Inc. All rights reserved.
* @version V1.0
*/

import domain.Buyer;

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
* @ClassName BuyerDaoImpl
* @Description 买家信息表接口实现类
* @date 2018/1/19
*/
public class BuyerDaoImpl implements BuyerDao{
    /**
     * 查询结果集
     */
    private ResultSet resultSet=null;
    /**
     *买家信息
     */
    private Buyer buyer=null;
    /**
     *买家信息集合
     */
    private List<Buyer> buyers=null;
    /**
     *受影响行
     */
    private Integer operatedRows=null;
    /**
     *查询语句
     */
    String selectSql="SELECT ID, NAME,SEX,AGE,ID_NUMBER,TEL,PERMANENT_ADDR,PROFESSION,WORK_UNIT,SAVING FROM buyer WHERE ";
    /**
     * @Title: deleteBuyerById
     * @Description: 通过买家ID删除
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteBuyerById(Long id, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        //根据传入的id在buyer表中删除对应的数据并返回受影响的行数
        preparedStatement=connection.prepareStatement("DELETE FROM buyer WHERE ID=?;");
        preparedStatement.setLong(1,id);
        operatedRows=preparedStatement.executeUpdate();
        connection.commit();
        return operatedRows;
    }
    /**
     * @Title: deleteBuyerByIds
     * @Description: 通过ID批量删除买家
     * @author caoxin
     * @date 2018/1/19
     * @param  ids, connection, preparedStatement
     * @return java.lang.Integer
     * @throw SQLException
     */
    @Override
    public Integer deleteBuyerByIds(Long[] ids, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        operatedRows=0;
        //根据传入的id数据删除数据
        for(Long id:ids){
            preparedStatement=connection.prepareStatement("DELETE FROM buyer WHERE ID=?;");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            operatedRows++;
        }
        connection.commit();
        return operatedRows;
    }
    /**
     * @Title: updateBuyer
     * @Description: 修改买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param buyer, connection, preparedStatement
     * @return domain.Buyer
     * @throw SQLException
     */
    @Override
    public Buyer updateBuyer(Buyer buyer, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        //根据ID更新buyer表的NAME,SEX,AGE,ID_NUMBER,TEL,PERMANENT_ADDR,PROFESSION,WORK_UNIT和SAVING列
        preparedStatement=connection.prepareStatement("UPDATE buyer SET NAME=?,SEX=?,AGE=?,ID_NUMBER=?,TEL=?,PERMANENT_ADDR=?,PROFESSION=?,WORK_UNIT=?,SAVING=? WHERE ID=?;");
        preparedStatement.setString(1,buyer.getName());
        preparedStatement.setString(2, buyer.getSex());
        preparedStatement.setByte(3, buyer.getAge());
        preparedStatement.setString(4, buyer.getIdNumber());
        preparedStatement.setString(5,buyer.getTel());
        preparedStatement.setString(6,buyer.getPermanentAddr());
        preparedStatement.setString(7,buyer.getProfession());
        preparedStatement.setString(8,buyer.getWorkUnit());
        preparedStatement.setDouble(9,buyer.getSaving());
        preparedStatement.setLong(10,buyer.getId());
        System.out.println("buyer表中受影响的行数为：" +preparedStatement.executeUpdate());
        connection.commit();
        return buyer;
    }
    /**
     * @Title: insertBuyer
     * @Description: 添加买家信息
     * @author caoxin
     * @date 2018/1/19
     * @param buyer, connection, preparedStatement
     * @return domain.Buyer
     * @throw SQLException
     */
    @Override
    public Buyer insertBuyer(Buyer buyer, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        //将buyer添加到buyer表中
        preparedStatement=connection.prepareStatement("INSERT INTO buyer(NAME,SEX,AGE,ID_NUMBER,TEL,PERMANENT_ADDR,PROFESSION,WORK_UNIT,SAVING)VALUES(?,?,?,?,?,?,?,?,?);");
        preparedStatement.setString(1,buyer.getName());
        preparedStatement.setString(2, buyer.getSex());
        preparedStatement.setByte(3, buyer.getAge());
        preparedStatement.setString(4, buyer.getIdNumber());
        preparedStatement.setString(5,buyer.getTel());
        preparedStatement.setString(6,buyer.getPermanentAddr());
        preparedStatement.setString(7,buyer.getProfession());
        preparedStatement.setString(8,buyer.getWorkUnit());
        preparedStatement.setDouble(9, buyer.getSaving());
        System.out.println("buyer表中受影响的行数为：" + preparedStatement.executeUpdate());
        connection.commit();
        return buyer;
    }

    /**
     * @Title: queryBuyerById
     * @Description: 通过ID查询买家信息表
     * @author caoxin
     * @date 2018/1/19
     * @param id, connection, preparedStatement
     * @return domain.Buyer
     * @throw SQLException
     */
    @Override
    public Buyer queryBuyerById(Long id, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        //从buyer表中根据传入的id查询出买家所有信息
        preparedStatement=connection.prepareStatement("SELECT ID, NAME,SEX,AGE,ID_NUMBER,TEL,PERMANENT_ADDR,PROFESSION,WORK_UNIT,SAVING FROM buyer WHERE ID=?;");
        preparedStatement.setLong(1,id);
        //获取查询的结果集
        resultSet=preparedStatement.executeQuery();
        //若非空，开始遍历结果集并给buyer赋值
        if(resultSet!=null){
            while(resultSet.next()){
                buyer=new Buyer();
                buyer.setId(resultSet.getLong(1));
                buyer.setName(resultSet.getString(2));
                buyer.setSex(resultSet.getString(3));
                buyer.setAge(resultSet.getByte(4));
                buyer.setIdNumber(resultSet.getString(5));
                buyer.setTel(resultSet.getString(6));
                buyer.setPermanentAddr(resultSet.getString(7));
                buyer.setProfession(resultSet.getString(8));
                buyer.setWorkUnit(resultSet.getString(9));
                buyer.setSaving(resultSet.getDouble(10));
            }
            return buyer;
        }else{
            //结果集为空返回null
            return null;
        }
    }
    /**
     * @Title: queryBuyerByCondition
     * @Description: 通过特定条件查询买家表
     * @author caoxin
     * @date 2018/1/19
     * @param stringBuyerMap, connection, preparedStatement
     * @return java.util.List<domain.Buyer>
     * @throw SQLException
     */

    @Override
    public List<Buyer> queryBuyerByCondition(Map<String, String> stringBuyerMap, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        //取出stringBuyerMap中的鍵集合
        Set<String> keySet=stringBuyerMap.keySet();
        //储存查询结果集
        buyers=new ArrayList<Buyer>();
        //遍历键集，取出对应值并通过键值对条件进行查询
        for(String key:keySet){
            String keyValue=stringBuyerMap.get(key);
            selectSql=selectSql+key+"="+"'"+keyValue+"';";
            preparedStatement=connection.prepareStatement(selectSql);
            resultSet=preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    buyer=new Buyer();
                    buyer.setId(resultSet.getLong(1));
                    buyer.setName(resultSet.getString(2));
                    buyer.setSex(resultSet.getString(3));
                    buyer.setAge(resultSet.getByte(4));
                    buyer.setIdNumber(resultSet.getString(5));
                    buyer.setTel(resultSet.getString(6));
                    buyer.setPermanentAddr(resultSet.getString(7));
                    buyer.setProfession(resultSet.getString(8));
                    buyer.setWorkUnit(resultSet.getString(9));
                    buyer.setSaving(resultSet.getDouble(10));
                    buyers.add(buyer);
                }
                resultSet.close();
            }else {
                return null;
            }
        }
        return buyers;
    }
    /**
     * @Title: queryBuyerByCondition
     * @Description: 通过特定条件查询买家表并分页显示
     * @author caoxin
     * @date 2018/1/19
     * @param stringBuyerMap, connection, preparedStatement
     * @return java.util.List<domain.Buyer>
     * @throw SQLException
     */
    @Override
    public List<Buyer> queryBuyerByCondition(Map<String, String> stringBuyerMap, Integer startRow, Integer size, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        //取出stringBuyerMap中的鍵集合
        Set<String> keySet = stringBuyerMap.keySet();
        //储存查询结果集
        buyers = new ArrayList<Buyer>();
        //遍历键集，取出对应值并通过键值对条件进行查询并对结果进行分页显示
        for (String key : keySet) {
            String keyValue = stringBuyerMap.get(key);
            selectSql=selectSql+key+"="+"'"+keyValue+"' LIMIT "+startRow+","+size+";";
            preparedStatement = connection.prepareStatement(selectSql);
            resultSet = preparedStatement.executeQuery();
//            //指针定位到要startRow行开始输出记录
//            resultSet.absolute(startRow);
//            // 设置最大查询记录条数
//            preparedStatement.setMaxRows(size);
            if (resultSet != null) {
                while (resultSet.next()) {
                    buyer = new Buyer();
                    buyer.setId(resultSet.getLong(1));
                    buyer.setName(resultSet.getString(2));
                    buyer.setSex(resultSet.getString(3));
                    buyer.setAge(resultSet.getByte(4));
                    buyer.setIdNumber(resultSet.getString(5));
                    buyer.setTel(resultSet.getString(6));
                    buyer.setPermanentAddr(resultSet.getString(7));
                    buyer.setProfession(resultSet.getString(8));
                    buyer.setWorkUnit(resultSet.getString(9));
                    buyer.setSaving(resultSet.getDouble(10));
                    buyers.add(buyer);
                }
                resultSet.close();
            } else {
                return null;
            }
        }
        return buyers;
    }
}
