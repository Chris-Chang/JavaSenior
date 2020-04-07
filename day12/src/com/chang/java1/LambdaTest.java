package com.chang.java1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda表达式的使用
 *
 * 1.举例:(o1, o2) -> Integer.compare(o1, o2)
 * 2.格式：
 *      ->:Lambda操作符 或 箭头操作符
 *      ->左边：lambda形参列表（其实就是接口中的抽象方法的形参列表）
 *      ->右边Lambda体，（其实就是重写的抽象方法的方法体）
 * 3.Lambda表达式的使用：（分为6种情况）
 *
 *      总结：->左边：Lambda形参列表的参数类型可以省略（类型推断）；如果Lambda形参列表只有一个参数，其一对（）可以省略
 *           ->右边：Lambda体应该使用一对{}包裹；如果Lambda体只有一个条执行语句(可能是return语句)，可以省略这一对大括号，和return
 *
 * 4. Lambda表达式的本质：作为函数式接口的实例
 *
 * 5.如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接口，我们可以在一个接口上使用注解 @FunctionalInterface
 *  这样做可以检查它是否是一个函数式接口
 *
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-06 9:11
 */
public class LambdaTest {
    //语法格式一：无参，无返回值
    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };
        r1.run();
        System.out.println("*****************");

        Runnable r2 = () -> {
            System.out.println("我爱故宫");
        };
        r2.run();
    }

    //语法格式二：Lambda需要一个参数，但是没有返回值
    @Test
    public void test2(){
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("Hi!中国");
        System.out.println("**************************");
        Consumer<String> con1 = (String s) -> {
            System.out.println(s);
        };
        con1.accept("你好");
    }

    //语法格式三：数据类型可以省略，因为可由编译器推断得出，称为“类型推断”
    @Test
    public void test3(){
        Consumer<String> con1 = (String s) -> {
            System.out.println(s);
        };
        con1.accept("原谅我这一生不羁放纵爱自由");
        System.out.println("*******************");

        Consumer<String> con2 = (s) -> {
            System.out.println(s);
        };
        con1.accept("也会怕有一天会跌倒");
    }

    //类型推断
    public void test4(){
        ArrayList<String> list = new ArrayList<>();//类型推断
        int[] arr = new int[]{1,2,3};
        int[] arr1 = {1,2,3};
    }

    //语法格式四:Lambda若只需要一个参数时，参数的小括号可以省略
    public void test5(){

        Consumer<String> con1 = (s) -> {
            System.out.println(s);
        };
        con1.accept("违背了理想");
        System.out.println("***********");

        Consumer<String> con2 = s -> {
            System.out.println(s);
        };
        con1.accept("谁人都可以");
    }

    //语法格式五：Lambda需要两个以上的参数，多条执行语句，并且可以有返回值
    public void test6(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(12,21));
        System.out.println("**************");

        Comparator<Integer> com2 =(o1,o2) -> {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
        };
        System.out.println(com2.compare(5,2));
    }

    //语法格式六：当Lambda体只有一条语句时，return与大括号若有，都可以省略
    @Test
    public void test7(){
        Comparator<Integer> com1 =(o1,o2) -> {
            return o1.compareTo(o2);
        };
        System.out.println(com1.compare(6,3));
        System.out.println("*******************");

        Comparator<Integer> com2 =(o1,o2) -> o1.compareTo(o2);
        System.out.println(com2.compare(3,8));

    }

}
