package com.chang4.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-10 9:24
 */
public class C3P0Test {
    @Test
    public void  testConnection() throws Exception {
        //获取c3p0数据库连接池
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test" );
        cpds.setUser("root");
        cpds.setPassword("0227");
        //通过设置相关的参数， 对数据库连接池进行管理
        //设置初始时数据库连接池中的连接数
        cpds.setInitialPoolSize(10);

        Connection conn = cpds.getConnection();
        System.out.println(conn);

        //销毁c3p0连接池
        DataSources.destroy(cpds);

    }

    //方式二：配置文件
    @Test
    public void testConnection1() throws SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        Connection conn = cpds.getConnection();
        System.out.println(conn);
    }
}
