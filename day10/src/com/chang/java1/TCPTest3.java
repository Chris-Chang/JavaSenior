package com.chang.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCPTest3 服务器端接收到数据后发送消息给客户端
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-03 9:32
 */
public class TCPTest3 {
    @Test
    public void client(){
        Socket socket = null;
        OutputStream outputStream = null;
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            InetAddress inet = InetAddress.getByName("127.0.0.1");

//        1.
            socket = new Socket(inet,8848);
//        2.返回此套接字的输出流。
            outputStream = socket.getOutputStream();
            fis = new FileInputStream(new File("book.jpg"));
//        3.
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1){
                outputStream.write(buffer, 0, len);
            }
//            4.图片传完，不再输出数据
            socket.shutdownOutput();
//            5.接受来自服务器端的数据，并显示到控制台上
            InputStream is = socket.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buf = new byte[20];
            int len1;
            while ((len1 = is.read(buf)) != -1){
                baos.write(buf,0,len1);
            }
            System.out.println(baos.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){

                try {
                    outputStream.close();
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
        Socket accept = null;
        InputStream inputStream = null;
        FileOutputStream fos = null;
        OutputStream os = null;

        try {
//        1.
            ss = new ServerSocket(8848);
//        2.
            accept = ss.accept();
            inputStream = accept.getInputStream();
            fos = new FileOutputStream("book2.jpg");
//        3.
            byte[] buffer = new byte[1024];
            int len;
            while((len = inputStream.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }
//            4.通知客户端消息已经收到
            os = accept.getOutputStream();
            os.write("图片收到".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(accept != null){
                try {
                    accept.close();
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
