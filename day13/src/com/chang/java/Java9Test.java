package com.chang.java;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-07 11:32
 */
public class Java9Test {
    //java9的特性五：钻石操作符的升级
    @Test
    public void test1(){

        //jdk7中的新特性：类型推断
        ArrayList<String> list = new ArrayList<>();

        //钻石操作符与匿名内部类在java8中不能共存，在java9中可以
        Comparator<Object> com = new Comparator<>() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
    }

    //java9特性六：try操作的升级
    public static void main(String[] args){
//        //java8之前资源关闭操作
//        InputStreamReader reader = null;
//        try {
//            reader = new InputStreamReader(System.in);
//            char[] cbuf = new char[20];
//            int len;
//            if((len = reader.read(cbuf) )!= -1){
//                String str = new String(cbuf,0,len);
//                System.out.println(str);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if(reader != null){
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

//        //java8中资源关闭操作:实现资源自动关闭.
//        //但要求执行后必须关闭的所有资源必须在try子句中初始化。否则编译不通过
//        try(InputStreamReader reader= new InputStreamReader(System.in)) {
//            char[] cbuf = new char[20];
//            int len;
//            if((len = reader.read(cbuf) )!= -1){
//                String str = new String(cbuf,0,len);
//                System.out.println(str);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //java9中关闭资源的操作:需要自动关闭的资源的实例化可以放在try的一对小括号外。
        //此时的资源属性是常量，声明为final不能修改
        InputStreamReader reader= new InputStreamReader(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        try(reader;writer) {
            char[] cbuf = new char[20];
            int len;
            if((len = reader.read(cbuf) )!= -1){
                String str = new String(cbuf,0,len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
