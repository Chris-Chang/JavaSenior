package com.chang.java2;

import com.chang.java1.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 调用运行时类中指定的结构：属性，方法，构造器
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-04 9:47
 */
public class ReflectionTest {
    /**
     *
     */
    @Test
    public void testField() throws Exception {
        Class clazz= Person.class;
        //创建运行时类的对象
        Person p = (Person)clazz.getDeclaredConstructor().newInstance();

        //获取指定的属性
        //通常不采用此方法
        Field id = clazz.getField("id");
        /**
         * 设置当前属性的值
         * set():参数1：指明设置哪个对象的属性    参数2：将此属性值设置为多少
         */
        id.set(p, 1001);
        /**
         * 获取当前属性的值
         * get():参数1：获取哪个对象的当前属性值
         */
        int pId = (int) id.get(p);
        System.out.println(pId);

    }

    /**
     * 如何操作运行时类中指定的属性--需要掌握
     * @throws Exception
     */
    @Test
    public void testField1() throws Exception{
        Class clazz = Person.class;
        //创建运行时类的对象
        Person p = (Person) clazz.getDeclaredConstructor().newInstance();
        //1. getDeclaredField(String fieldName):获取运行时类中指定变量名的属性
        Field name = clazz.getDeclaredField("name");
        //2. 保证当前属性是可访问的
        name.setAccessible(true);
        //3.获取，设定指定对象的此属性值
        name.set(p,"Chang");
        System.out.println(name.get(p));
    }

    /**
     *
     * 如何操作运行时类中指定的方法--需要掌握
     * @throws Exception
     */
    @Test
    public void testMethod() throws Exception{
        Class clazz = Person.class;
        //创建运行时类的对象
        Person p = (Person) clazz.getDeclaredConstructor().newInstance();
        //1. getDeclaredMethod():参数1指明获取的方法名称 参数2指明获取的方法的形参列表
        Method show = clazz.getDeclaredMethod("show", String.class);
        //2. 保证当前方法是可访问的
        show.setAccessible(true);
        //调用方法的invoke():参数1：方法的调用者，参数2：给方法形参赋值的实参
        //invoke()方法的返回值即为对应类中调用方法的返回值
        Object returnValue = show.invoke(p, "中国");
        System.out.println(returnValue);

        //获取静态方法
//        private static void showDesc(){
        Method showDesc = clazz.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        //如果调用的运行时类中的方法没有返回值，则此invoke()返回null
//        Object returnVal = showDesc.invoke(null);
        Object returnVal = showDesc.invoke(clazz);
        System.out.println(returnVal);
    }

    @Test
    public void testConstructor() throws Exception{
        Class clazz = Person.class;
//    private Person(String name, int age){
        /**
         * 1. 获取指定的构造器
         * getDeclaredConstructor(),参数：指明构造器的形参列表
         *
         */
        Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class);
        //2.保证此构造器是可访问的
        constructor.setAccessible(true);
        //3.调用此构造器，创建运行时类的对象
        Person p = (Person)constructor.newInstance("Tom", 18);
        System.out.println(p);

    }
}
