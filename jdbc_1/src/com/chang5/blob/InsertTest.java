package com.chang5.blob;

import com.chang3.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-09 16:10
 */
public class InsertTest {
    //批量插入的方式二：使用PreparedStatement
    @Test
    public void testInsert1() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtils.getConnection();

            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < 20000; i++){
                ps.setString(1,"name_" + i);
                ps.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("花费的时间为:" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps);
        }

    }

    /**
     * 批量插入的方式三
     * 1.addBatch executeBatch clearBatch
     * Mysql默认不支持，需要在url后加上rewriteBatchedStatements=true
     * 花费：20000: 35575ms(一) -> 2179ms(二)
     *      1000000:33971(二)
     */
    @Test
    public void testInsert2(){

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtils.getConnection();

            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < 1000000; i++){
                ps.setString(1,"name_" + i);
                //1.攒sql
                ps.addBatch();
                if(i % 500 == 0){
                    //2.执行Batch
                    ps.executeBatch();
                    //3.清空Batch
                    ps.clearBatch();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("花费的时间为:" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps);
        }
    }


    /**
     * 批量操作的方式四：
     * 设置不允许自动提交数据
     * 花费：20000: 35575ms(一) -> 2179ms(二)
     *      1000000:33971(二) -> 17361(三)
     */
    @Test
    public void test3(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtils.getConnection();

            //设置不允许自动提交数据
            conn.setAutoCommit(false);

            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < 1000000; i++){
                ps.setString(1,"name_" + i);
                //1.攒sql
                ps.addBatch();
                if(i % 500 == 0){
                    //2.执行Batch
                    ps.executeBatch();
                    //3.清空Batch
                    ps.clearBatch();
                }
            }
            //提交数据
            conn.commit();
            long end = System.currentTimeMillis();
            System.out.println("花费的时间为:" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps);
        }
    }
}
