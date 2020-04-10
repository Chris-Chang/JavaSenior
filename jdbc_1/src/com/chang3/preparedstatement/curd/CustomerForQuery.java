package com.chang3.preparedstatement.curd;

import com.chang3.bean.Customer;
import com.chang3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-09 10:14
 */
public class CustomerForQuery {

    /**
     * 针对于表的字段名与类的属性名不相同的情况
     * 1. 必须声明sql时，使用类的属性名来命名字段的别名
     * 2. 使用ResultMetaData时，需要使用getColumnLabel()来替换getColumnName(),
     *      获取列的别名
     *    说明：如果sql中没有给字段起别名，getColumnLabel()获取的就是别名
     */
    @Test
    public void testQueryForCustomer(){
        String sql = "select id, name, email, birth from customers where id = ?";
        Customer customer = queryForCustomer(sql, 13);
        System.out.println(customer);

        String sql1 = "select name, email from customers where name = ?";
        Customer customer1 = queryForCustomer(sql1, "周杰伦");
        System.out.println(customer1);
    }
    /**
     * 针对于customer表的通用查询操作
     * @param sql
     * @param args
     * @return
     */
    public Customer queryForCustomer(String sql,Object... args)  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < args.length; i++){
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
//            获取结果集中的元数据:ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            if(rs.next()){
                Customer customer = new Customer();
                //处理结果集一行数据中的每一个列
                for(int i = 0; i< columnCount; i++){
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);
                    //获取每个列的列名getColumnName() --不推荐使用
                    //获取每个列的别名getColumnLabel()
//                    String columnName = rsmd.getColumnName(i + 1);
                    String columnName = rsmd.getColumnLabel(i + 1);

                    //给customer对象指定的columnName属性，赋值为columnValue,通过反射
                    Field field = Customer.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(customer, columnValue);
                }
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps,rs);
        }
        return null;
    }
    @Test
    public void testQuery1() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            //1.链接数据库
            conn = JDBCUtils.getConnection();
            String sql = "select id,name,email,birth from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,1);
            //执行并返回结果集
            resultSet = ps.executeQuery();
            if (resultSet.next()){
                //获取当前这条数据的各个字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);
                //将数据封装为一个对象
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.closeResource(conn, ps, resultSet);
        }
    }
}
