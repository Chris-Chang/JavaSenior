package com.chang3.preparedstatement.curd;

import com.chang3.bean.Customer;
import com.chang3.bean.Order;
import com.chang3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @Description 使用PreparedStatement实现针对于不同表的通用的查询操作
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-09 11:37
 */
public class PreparedStatementQueryTest {

    @Test
    public void testGetInstance(){
        String sql = "select id, name, email, birth from `customers` where id = ?";
        Customer customer = getInstance(Customer.class, sql, 12);
        System.out.println(customer);

        String sql1 = "select order_id orderId, order_name orderName from `order` where order_id = ?";
        Order order = getInstance(Order.class, sql1, 4);
        System.out.println(order);
    }

    public <T> T getInstance(Class<T> clazz, String sql, Object... args){
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
                T t = clazz.getDeclaredConstructor().newInstance();
                //处理结果集一行数据中的每一个列
                for(int i = 0; i< columnCount; i++){
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);
                    //获取每个列的列名getColumnName() --不推荐使用
                    //获取每个列的别名getColumnLabel()
//                    String columnName = rsmd.getColumnName(i + 1);
                    String columnName = rsmd.getColumnLabel(i + 1);

                    //给customer对象指定的columnName属性，赋值为columnValue,通过反射
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps,rs);
        }
        return null;
    }
}
