package com.chang.java;

import org.junit.Test;

import java.util.Optional;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-07 9:59
 */
public class OptionalTest {
    @Test
    public void test1(){
        //empty():创建的Optional对象内部的value = null
        Optional<Object> op1 = Optional.empty();
        if(!op1.isPresent()){//封装的数据是否包含对象
            System.out.println("数据为空");
        }
        System.out.println(op1);//Optional.empty
        System.out.println(op1.isEmpty());//true
        System.out.println(op1.isPresent());//false
    }
    
    @Test
    public void test2(){
        String str = "Hello";
//        str = null;
        //of(T t):封装数据t生成Optional对象，要求t非空，否则报错
        Optional<String> op1 = Optional.of(str);
        //get()通常与of()搭配使用，用于获取内部封装的数据value
        String str1 = op1.get();
        System.out.println(str1);
    }

    @Test
    public void test3(){
        String str = "Hi";
        str = null;
        //ofNullable(T t)封装数据t赋给Optional内部的Value,不要求t非空
        Optional<String> op1 = Optional.ofNullable(str);
        //orElse(T t1):如果Optional内部的value非空，则返回此value值，如果value为空，则返回t1
        String str1 = op1.orElse("你好");
        System.out.println(str1);
    }

}
