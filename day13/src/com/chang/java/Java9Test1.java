package com.chang.java;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-07 12:28
 */
public class Java9Test1 {
    //java8中的写法
    @Test
    public void test1(){
        List<String> namesList = new ArrayList<>();
        namesList.add("Joe");
        namesList.add("Bob");
        namesList.add("Bill");
        //返回的namesList是一个只读的集合
        namesList = Collections.unmodifiableList(namesList);
        namesList.add("Mike");
        System.out.println(namesList);
    }

    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        //报异常
        list.add(6);
    }
    //java9新特性八：集合工厂方法，创建只读集合
    @Test
    public void test4(){
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        //不可添加
//        list.add(6);
        System.out.println(list);

        Set<Integer> set = Set.of(33, 21, 344, 32, 3);
        //不可添加
//        set.add(9);
        System.out.println(set);

        Map<String,Integer> map1 = Map.of("Tom",22,"Jerry",9);
//        map1.put("Lilei",22);
        System.out.println(map1);

        Map<String,Integer> map2 = Map.ofEntries(Map.entry("Tom",22),Map.entry("Jerry",33));
//        map1.put("Lilei",22);
        System.out.println(map2);
    }

    //InputStream的新方法：tranferTo()
    @Test
    public void test2(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        //getResourceAsStream默认在src下,如果文件不存在会报空指针异常
        try(InputStream is = classLoader.getResourceAsStream("hello.txt");
            OutputStream os = new FileOutputStream("src\\hello2.txt")){
            is.transferTo(os);//把输入流中的所有数据直接自动地复制到输出流中
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
