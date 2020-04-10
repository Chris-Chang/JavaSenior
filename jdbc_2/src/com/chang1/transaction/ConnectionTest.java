package com.chang1.transaction;

import com.chang1.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-09 17:07
 */
public class ConnectionTest {
    @Test
    public void testGetConnection() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);
    }


}
