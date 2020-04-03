package com.chang.java1;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-03 11:27
 */
public class URLTest {
    @Test
    public void test1(){
        try {
            URL url = new URL("https://www.bilibili.com/video/BV1Kb411W75N?p=629");
            //1.获取协议 https
            System.out.println(url.getProtocol());
            //2.获取URL主机名 www.bilibili.com
            System.out.println(url.getHost());
            //3.获取URL的端口号,未指定端口号时（即使用默认端口号），getport()返回-1
            System.out.println(url.getPort());
            //3.1 http协议默认端口号80，https协议默认端口号443
            System.out.println(url.getDefaultPort());
            //4.获取该URL的文件路径/video/BV1Kb411W75N
            System.out.println(url.getPath());
            //5.获取该URL的文件名 /video/BV1Kb411W75N?p=629
            System.out.println(url.getFile());
            //获取该URL的查询名 p=629
            System.out.println(url.getQuery());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
