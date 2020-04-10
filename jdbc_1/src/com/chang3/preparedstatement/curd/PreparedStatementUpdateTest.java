package com.chang3.preparedstatement.curd;

import com.chang3.util.JDBCUtils;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 增删改，查
 *
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-08 12:05
 */
public class PreparedStatementUpdateTest {

    @Test
    public void testCommonUpdate() {
        String sql = "delete from customers where id = ?";
        update(sql, 3);
    }

    /**
     * 通用增删改操作
     */
    public int update(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            System.out.println("***********");
            //1.获取数据库链接
            conn = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //4.执行
            //方式一：
//            ps.execute();
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.closeResource(conn, ps);
        }
        return 0;
    }

    /**
     * 更新操作
     */
    @Test
    public void testUpdate() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的链接
            conn = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement的实例
            String sql = "update customers set name = ? where id = ?";
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            ps.setObject(1, "莫扎特");
            ps.setObject(2, 18);
            //4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.资源的关闭
            JDBCUtils.closeResource(conn, ps);
        }

    }

    //向customer表中添加一条记录
    @Test
    public void testInsert() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.读取配置文件的基本信息
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            Properties pros = new Properties();
            pros.load(is);
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String url = pros.getProperty("url");
            String driverClass = pros.getProperty("driverClass");

            //2.加载驱动
            Class.forName(driverClass);
            //3.获取连接
            conn = DriverManager.getConnection(url, user, password);
//        System.out.println(conn);

            //4.
            String sql = "insert into customers(name,email,birth)values(?,?,?)";//?占位符
            ps = conn.prepareStatement(sql);
            //5.填充占位符
            ps.setString(1, "哪吒");
            ps.setString(2, "nezha@gmail.com");
            SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd");
            Date date = sdf.parse("1000-01-01");
            ps.setDate(3, new java.sql.Date(date.getTime()));

            //6.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //8.资源关闭
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

}
