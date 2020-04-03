package com.chang.java1;

import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-03 11:48
 */
public class URLTest1 {
    @Test
    public void test1(){
        HttpsURLConnection urlConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
//        1.创建url对象
            URL url = new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1585896031753&di=33cff879fd47a098b07a22ba21d5ee56&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D1484500186%2C1503043093%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D853");
//        2.打开url并连接
            urlConnection = (HttpsURLConnection)url.openConnection();
            urlConnection.connect();
            //3.获取连接以后得到一个输入流
            is = urlConnection.getInputStream();
            fos = new FileOutputStream(new File("lion.jpg"));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }
            System.out.println("下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //关闭url连接
            urlConnection.disconnect();
        }

    }

}
