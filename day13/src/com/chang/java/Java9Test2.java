package com.chang.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-07 15:08
 */
public class Java9Test2 {

    //Java9的新特性10：增强的SteamAPI
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(23, 44, 20, 55, 60, 32, 43, 99, 2);
        //takeWhile 返回从开头开始的尽可能多的元素
//        list.stream().takeWhile(x -> x < 60).forEach(System.out::println);
        //dropWhile():与takeWhile相反，返回剩余的元素
        list.stream().dropWhile(x -> x < 60).forEach(System.out::println);
    }

    @Test
    public void test2(){
        //of()参数中的多个元素，可以包含null值
        Stream<Integer> stream1 = Stream.of(1,2,3,null);
        stream1.forEach(System.out::println);
        //of()参数不能存储单个null值,否则报异常
//        Stream<Integer> stream2 = Stream.of(null);
//        stream2.forEach(System.out::println);
        Integer i = 10;
        i = null;
        //ofNullable 形参变量是可以为null值的单个元素
        Stream<Integer> stream3 = Stream.ofNullable(i);

//        stream3.forEach(System.out::println);
        long count = stream3.count();
        System.out.println(count);
    }

    @Test
    public void test3(){
        Stream.iterate(0,x -> x + 1).limit(10).forEach(System.out::println);//[0,9]
        Stream.iterate(0,x -> x < 100 ,x -> x + 1).forEach(System.out::println);//[0,99]
    }

    //java9新特性十一：Optional提供了新的方法stream()
    @Test
    public void test4(){
        List<String> list = new ArrayList<>();
        list.add("Tom");
        list.add("Jerry");
        list.add("Tim");
        Optional<List<String>> optional = Optional.ofNullable(list);
        Stream<List<String>> stream = optional.stream();
//        long count = stream.count();
//        System.out.println(count);
        stream.flatMap(x -> x.stream()).forEach(System.out::println);

    }
}
