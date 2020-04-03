package com.chang.java1;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress类代表IP
 * 如何实例化InetAddress:两个方法：getByName(String host),getLocalHost()
 *      两个常用方法：getHostName()/getHostAddress()
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-01 13:42
 */
public class InetAddressTest {
    public static void main(String[] args) {
        try {
            InetAddress inet1 = InetAddress.getByName("192.168.0.1");
            InetAddress inet2 = InetAddress.getByName("www.changzhi.space");
            InetAddress inet3 = InetAddress.getByName("127.0.0.1");
            InetAddress localHost = InetAddress.getLocalHost();

            String hostName = inet2.getHostName();
            String hostAddress = inet2.getHostAddress();
            System.out.println(inet1);
            System.out.println(inet2);
            System.out.println(inet3);
            System.out.println(localHost);
            System.out.println(hostName);
            System.out.println(hostAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
