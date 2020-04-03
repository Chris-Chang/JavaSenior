package com.chang.java1;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP测试
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-03 11:05
 */
public class UDPTest {
    @Test
    public void client(){
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress inet = InetAddress.getLocalHost();
            String str = "你好UDP服务端";
            byte[] buffer = str.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length,inet,8848);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           socket.close();
        }
    }

    @Test
    public void server(){
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(8848);
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
            socket.receive(packet);
            String str = new String(packet.getData(),0,packet.getLength());
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }



    }
}
