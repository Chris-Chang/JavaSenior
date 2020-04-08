package com.chang.java2;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-07 16:44
 */
public class Java11Test {
    //java11特性1：String中新增的方法
    @Test
    public void test1(){
        //isBlank()//判断字符串是否为空白
        System.out.println("   \t \n".isBlank());//true
        //strip()//去除收尾空白
        System.out.println("---" + " \t abc \n".strip() + "------------");
        System.out.println("---" + " \t abc \n".trim() + "------------");
        //stripLeading 去除首部空格
        System.out.println("---" + " \t abc \n".stripLeading() + "------------");
        //stripTrailing 去除尾部空格
        System.out.println("---" + " \t abc \n".stripTrailing() + "------------");
        //repeat(int count)
        String str1 = "abc";
        String str2 = str1.repeat(5);
        System.out.println(str2);

        //lines().count():
        String str3 = "abc\ndefg";
        System.out.println(str3.lines().count());


    }

    //java11新特性二：Optional新增的方法
    @Test
    public void test2(){
        Optional<Object> op = Optional.empty();
        System.out.println(op.isPresent());//判断内部value是否存在false
        System.out.println(op.isEmpty());//判断内部value是否为空true

        op = Optional.of("abc");
        Object obj = op.orElseThrow();
        System.out.println(obj);

        Optional<String> op1 = Optional.of("hello");
        //or:value非空，返回对应的Optional:value为空，返回形参封装的Optional
        Optional<Object> op2 = op.or(() -> op1);
        System.out.println(op2);
    }

    //java11特性三：局部变量类型推断的升级
    @Test
    public void test3(){
        //错误的形式：必须要有类型，可以加上var
//        Consumer<String> con1 = (@Deprecated t) -> System.out.println(t.toUpperCase());
        //正确的形式
        //使用var的好处是在使用Lambda表达式时给参数加上注解
        Consumer<String> con1 = (@Deprecated var t) -> System.out.println(t.toUpperCase());
    }

    //java11特性四：httpClient
    @Test
    public void test4(){

    }

    
}
