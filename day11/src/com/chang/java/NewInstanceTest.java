package com.chang.java;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-03 19:32
 */
public class NewInstanceTest {
    @Test
    public void test1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Person> clazz = Person.class;
        //newInstance():调用此方法，创建对应的运行时类的对象.内部调用了运行时类的空参构造器
        /*
        要想此方法正常的创建运行时类的对象，要求：
        1.运行时类必须提供空参的构造器
        2.空参的构造器的访问权限得够，通常设置为public

        在javabean中要求提供一个public 的空参构造器。原因：
        1.便于通过反射，创建运行时类的对象
        2.便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器
         */
        Person obj = clazz.getDeclaredConstructor().newInstance();
        System.out.println(obj);
    }

    @Test
    public void Test2(){
        for (int i = 0; i < 100; i++) {

            //生成随机数边界为3(0,1,2)
            int num = new Random().nextInt(3);
            String classPath = "";
            switch (num){
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "com.chang.java.Person";
                    break;
            }
            Object obj = null;
            try {
                obj = getInstance(classPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(obj);
        }
    }
    public Object getInstance(String classPath) throws Exception{
        Class clazz = Class.forName(classPath);
        return clazz.getDeclaredConstructor().newInstance();
    }
}
