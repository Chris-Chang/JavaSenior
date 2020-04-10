package com.chang5.blob;

import com.chang3.bean.Customer;
import com.chang3.util.JDBCUtils;
import org.junit.Test;

import java.io.*;
import java.sql.*;

/**
 * @Description 使用PreparedStatement操作Blob类型的数据
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-09 15:16
 */
public class BlobTest {

    //查询数据表customers中Blob类型的字段
    @Test
    public void testQuery() {
        Connection conn = null;
        PreparedStatement ps = null;
        InputStream is = null;
        FileOutputStream fops = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select id, name, email, birth, photo from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,21);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date birth = rs.getDate("birth");

                Customer cust = new Customer(id,name,email,birth);
                System.out.println(cust);

                //将Blob类型字段下载下来，以文件的方式保存在本地
                Blob photo = rs.getBlob("photo");
                is = photo.getBinaryStream();
                fops = new FileOutputStream("zhangyuhao.jpg");
                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer)) != -1) {
                    fops.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps);
            try {
                if(fops != null)
                    fops.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    //向数据表customer中插入blob类型字段
    @Test
    public void testBlob() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into customers(name,email,birth,photo) values(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1, "张宇豪");
        ps.setObject(2, "zhangyh@qq.com");
        ps.setObject(3, "1993-03-02");
        FileInputStream fis = new FileInputStream(new File("book.jpg"));
        ps.setBlob(4,fis);

        ps.execute();
        fis.close();
        System.out.println("插入成功");
    }
}
