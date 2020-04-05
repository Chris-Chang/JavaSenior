package com.chang.java;

import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-03 18:15
 */
public class ClassLoaderTest {
    @Test
    public void test1(){
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        //调用系统类加载器的getParent():获取扩展类加载器 PlatformClassLoader
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);
        //调用扩展类的加载器的getParent():无法获取引导类加载器
        //引导类加载器主要负责加载java的核心类库，无法加载自定义类
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);
    }

    @Test
    public void test2() throws Exception{
        Properties pros = new Properties();
        //读取配置文件的方式一
        //此时配置文件默认在当前的module下
//        FileInputStream fis = new FileInputStream("jdbc.properties");
        //读取配置文件的方式二
        //此时配置文件默认在当前module下的src下
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream fis = classLoader.getResourceAsStream("jdbc1.properties");
        pros.load(fis);

        String user = pros.getProperty("username");
        String password = pros.getProperty("password");
        System.out.println("user=" + user + ",password=" + password);
    }
}
