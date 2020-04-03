package com.chang.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例题2：客户端发送文件给服务端，服务端将文件保存到本地
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-01 14:53
 */
public class TCPTest2 {
    @Test
    public void client(){
        Socket socket = null;
        OutputStream os = null;
        FileInputStream fis = null;
        try {
            //1.
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet,8848);
            //2返回此套接字的输出流。.
            os = socket.getOutputStream();
            //3.
            fis = new FileInputStream(new File("book.jpg"));
            //4.
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1){
                os.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){

                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null){

                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){

                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    @Test
    public void server(){
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        FileOutputStream fops = null;
        try {
            //1.
            ss = new ServerSocket(8848);
            socket = ss.accept();
            is = socket.getInputStream();
            fops = new FileOutputStream(new File("book3.jpg"));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1){
                fops.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fops != null){

                try {
                    fops.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null){

                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){

                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ss != null){

                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
