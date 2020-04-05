package com.chang.java;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射：
 * 疑问1：通过直接new的方式或反射的方式都可以调用公共的结构，开发中用哪个？
 * 建议：直接new的方式
 * 什么时候会使用反射的方式。反射的特性：动态性
 * 疑问2：反射机制与面向对象中的封装性是不是矛盾？如何看待两个技术
 * 不矛盾。封装性是建议你调用公有方法。反射是能不能调用的问题
 *
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-03 15:52
 */
public class ReflectionTest {
    @Test
    public void test1 (){
        try {
            Class clazz = Person.class;
            //1.通过反射，创建Person类的对象
            Constructor cons = clazz.getConstructor(String.class,int.class);
            Object obj = cons.newInstance("Tom",12);
            Person p = (Person)obj;
            //2.通过反射，调用对象指定的属性、方法
            //调用属性
            Field age = clazz.getDeclaredField("age");
            age.set(p,10);
            System.out.println(p.toString());

            //通过反射，可以调用Person类的私有结构，比如：私有的构造器、方法、属性
            //调用私有构造器
            Constructor cons1 = clazz.getDeclaredConstructor(String.class);
            cons1.setAccessible(true);
            Person p1 = (Person)cons1.newInstance("Jerry");
            System.out.println(p1);
            //调用私有属性
            Field name = clazz.getDeclaredField("name");
            name.setAccessible(true);
            name.set(p1,"老韩");
            System.out.println(p1);
            //调用私有方法
            Method showNation = clazz.getDeclaredMethod("showNation",String.class);
            showNation.setAccessible(true);
            String nation = (String) showNation.invoke(p1, "中国");//相当于p1.showNation("中国")
            System.out.println(nation);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * 关于java.lang.Class类的理解
     * 1.类的加载过程：
     * 程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)
     * 接着我们使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件加载到内存中，
     * 此过程就称为类的加载。加载到内存中的类，我们就称为运行时类，此运行时类，就作为Class的一个实例
     *
     * 2.换句话说，Class的实例就对应着一个运行时类
     * 3.我们加载到内存中的运行时类，会缓存一段时间，在此时间之内，我们可以通过不同的方式来获取运行时类
     * （唯一存在）
     */
    //获取Class的实例方式
    @Test
    public void test2() throws ClassNotFoundException {
        //方式一：调用运行时类的属性：.class
        Class clazz1 = Person.class;
        System.out.println(clazz1);
        //方式二：通过运行时类的对象，调用getClass()
        Person p = new Person();
        Class clazz2 = p.getClass();
        System.out.println(clazz2);
        //方式三：调用Class的静态方法：forName(String classPath),此方式常用
        Class clazz3 = Class.forName("com.chang.java.Person");
//        clazz3 = Class.forName("java.lang.String");
        System.out.println(clazz3);

        System.out.println(clazz1 == clazz2);
        System.out.println(clazz2 == clazz3);
        //方式四：使用类的加载器：ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("com.chang.java.Person");
        System.out.println(clazz1 == clazz4);

    }

    /**
     * Class实例可以是哪些结构的说明
     */
    @Test
    public void test3(){

        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        //只要元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);
    }
}
