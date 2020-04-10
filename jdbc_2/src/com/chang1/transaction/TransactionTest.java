package com.chang1.transaction;

import com.chang1.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-09 19:47
 */
public class TransactionTest {
    @Test
    public void testUpdateWithTx() throws Exception {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            //1.取消数据自动提交
            conn.setAutoCommit(false);
            String sql1 = "update user_table set balance = balance - 100 where user = ?";
            update(conn, sql1, "AA");

            //模拟网络异常
            System.out.println(10 / 0);


            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            update(conn, sql2, "BB");

            //2.提交数据
            conn.commit();
            System.out.println("转账成功");
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            //修改为默认状态，主要针对数据库连接池使用
            conn.setAutoCommit(true);
            JDBCUtils.closeResource(conn,null);
        }


    }


    //***********************考虑数据库事务后的转账操作*********************

    /**
     * 通用的增删改操作---version2.0(考虑事务)
     * @param sql
     * @param args
     * @return
     */
    public int update(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;

        try {
            //1.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //2.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //3.执行
            //方式一：
//            ps.execute();
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.closeResource(null, ps);
        }
        return 0;
    }

    @Test
    public void testTransactionSelect() throws Exception{
        Connection conn = JDBCUtils.getConnection();
        //获取当前隔离级别
        System.out.println(conn.getTransactionIsolation());
        //设置数据库的隔离级别
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        //取消自动提交数据
        conn.setAutoCommit(false);
        String sql = "select user,password,balance from user_table where user = ?";
        User user = getInstance(conn, User.class, sql, "cc");
        System.out.println(user);
    }

    @Test
    public void testTransactionUpdate() throws Exception{
        Connection conn = JDBCUtils.getConnection();

        //取消自动提交数据
        conn.setAutoCommit(false);

        String sql = "update user_table set balance = ? where user = ?";
        update(conn, sql, 5000,"cc");

        Thread.sleep(15000);
        System.out.println("修改结束");
    }

    //*************************
    //通用的查询操作，用于返回数据表中的一条记录(version2.0 考虑上事务）
    public <T> T getInstance(Connection conn, Class<T> clazz, String sql, Object... args){


        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
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
            JDBCUtils.closeResource(null, ps,rs);
        }
        return null;
    }
}
