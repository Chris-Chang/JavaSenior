package com.chang1.connection;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-08 10:23
 */
public class ConnectionTest {
    //方式一
    @Test
    public void connectionTest1() throws SQLException {
        //获取Driver的实现类对象
        Driver driver = new com.mysql.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","0227");
        Connection conn = driver.connect(url, info);
        System.out.println(conn);

    }

    //方式二
    @Test
    public void connectionTest2() throws Exception {
        //1.获取Driver的实现类对象，使用反射
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver)clazz.getDeclaredConstructor().newInstance();

        //2.提供要连接的数据库
        String url = "jdbc:mysql://localhost:3306/test";

        //3.提供连接需要的用户名和密码
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","0227");
        //4.获取连接
        Connection conn = driver.connect(url, info);
        System.out.println(conn);

    }

    //方式三：使用DriverManager替换Driver
    @Test
    public void connectionTest3() throws Exception{
        //1.提供另外三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "0227";

        //2.获取Driver的实现类对象，使用反射
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver)clazz.getDeclaredConstructor().newInstance();


        //3.注册驱动
        DriverManager.registerDriver(driver);

        //获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);


    }

    //方式四：使用DriverManager替换Driver
    @Test
    public void connectionTest4() throws Exception{
        //1.提供另外三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "0227";

        //2.加载Driver的实现类到内存，（静态代码块随着类的加载而执行）
        Class.forName("com.mysql.jdbc.Driver");
        //相较于方式三可以省略如下操作
//        Driver driver = (Driver)clazz.getDeclaredConstructor().newInstance();
//        //注册驱动
//        DriverManager.registerDriver(driver);
        //为什么可以省略如上操作？在mysql实现类中，声明了如下操作
        /**
         *
         *static {
         *try {
         *java.sql.DriverManager.registerDriver(new Driver());
         *} catch (SQLException E) {
         *throw new RuntimeException("Can't register driver!");
         *}
         *}
         */

        //3. 获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);


    }

    //方式五(final版):将数据库连接的四个基本信息声明在配置文件中，通过读取配置文件的方式获取连接
    //实现了数据与代码的分离，实现了解耦
    //如果修改配置文件信息，可以避免程序重新打包
    @Test
    public void connectionTest5() throws Exception {
        //1.读取配置文件的基本信息
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        //2.加载驱动
        Class.forName(driverClass);
        //3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }


}
