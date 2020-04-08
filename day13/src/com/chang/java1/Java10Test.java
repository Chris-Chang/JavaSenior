package com.chang.java1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-07 15:47
 */
public class Java10Test {

    /**
     * java10的新特性1：局部变量的类型推断
     */
    @Test
    public void test1(){
//        int num = 10;
        //声明变量时，根据所赋的值，推断变量的类型
        var num = 10;

        var list = new ArrayList<String>();
        list.add("Tom");

        //2.遍历操作
        for(var i : list){
            System.out.println(i);
            System.out.println(i.getClass());
        }

        //3.普通遍历操作
        for(var i = 0;i < 100;i++){
            System.out.println(i);
        }
    }

    @Test
    public void test2(){
        //1.局部变量不赋值就不能实现类型推断
//        var num;

        //2. Lambda表达式中，左边的函数式接口不能声明为var
//        Supplier<Double> sup = () -> Math.random();
//        var sup = () -> Math.random();
        //3.方法引用中,左边的函数式接口不能声明为var
//        Consumer<String> con = System.out::print;
//        var con = System.out::print;

        //4.数组静态初始化中
        int[] arr = new int[]{1,2,3,4};
        var arr1 = new int[]{1,2,3,4};
        int[] arr3 = {1,2,3,4};
//        var arr2 = {1,2,3,4};
    }

    @Test
    public void test3(){
        //情况一：没有初始化的局部变量声明
//        var s = null;

        //情况6：catch块
//        try {
//
//        }catch (var a){
//            a.printStackTrace();
//        }
    }

    //情况2：方法的返回值类型
//    public var method1(){
//        return 0;
//    }
    //情况3：方法的参数类型
//    public void method1(var a){
//
//    }
    //情况4：构造器的参数类型
//    public Java10Test(var i){
//
//    }

    //情况5：属性//因为属性有默认值
//    var num = 19;


    //java10的新特性2：集合中新增的copyOf()用于创建一个只读的集合
    @Test
    public void test6(){
        //示例1：
        var list1 = List.of("Java","Pytho","C");//是只读的
        var copy1 = List.copyOf(list1);
        System.out.println(copy1 == list1);//true

        //示例2：
        var list2 = new ArrayList<String>();//不是只读的
        var copy2 = List.copyOf(list2);
        System.out.println(copy2 == list2);//false

        //示例1和示例2代码基本一致，为什么结果不一样
        //结论：copyOf(Xxx col)如果参数col本身就是只读型，则copyOf()返回值即为本身col
        //如果参数col不是一个只读的集合，则colOf()返回一个新的集合，这个新集合是只读的
    }
}
