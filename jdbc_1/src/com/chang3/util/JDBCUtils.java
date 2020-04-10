package com.chang3.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Description 操作数据库的工具类
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-09 9:05
 */
public class JDBCUtils {
    /**
     * 链接数据库
     * @return
     * @throws Exception
     */
   public static Connection getConnection() throws Exception{
       //1.读取配置文件中的四个基本信息
       InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
       Properties pro = new Properties();
       pro.load(is);

       String driverClass = pro.getProperty("driverClass");
       String url = pro.getProperty("url");
       String user = pro.getProperty("user");
       String password = pro.getProperty("password");

       //2.加载驱动
       Class.forName(driverClass);
       //3.获取链接
       Connection conn = DriverManager.getConnection(url, user, password);
       return conn;
   }

    /**
     * 关闭链接和statement的操作
     * @param conn
     * @param ps
     */
   public static void closeResource(Connection conn, Statement ps){
       try {
           if(ps != null){
               ps.close();
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }

       try {
           if(conn != null){
               conn.close();
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
    public static void closeResource(Connection conn, Statement ps, ResultSet rs){
        try {
            if(ps != null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
