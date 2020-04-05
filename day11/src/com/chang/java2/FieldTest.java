package com.chang.java2;

import com.chang.java1.Person;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-03 20:31
 */
public class FieldTest {
    @Test
    public void test1(){
        Class clazz = Person.class;

        //获取属性结构
        //getFields()获取当前运行类及其父类中声明为public访问权限的属性
        Field[] fields = clazz.getFields();
        for(Field f : fields){
            System.out.println(f);
        }
        System.out.println();

        //getDeclaredFields():获取当前运行时类中声明的属性。（不包含父类中声明的属性）
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields){
            System.out.println(f);
        }
    }

    @Test
    public void test3(){
        Class clazz = Person.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field f : declaredFields){
            //1.权限修饰符
            int modifier = f.getModifiers();
            System.out.print(Modifier.toString(modifier) + "\t");
            //2.数据类型
            Class type = f.getType();
            System.out.print(type.getName() + "\t");
            //3.变量名
            String fName = f.getName();
            System.out.print(fName);
            System.out.println();
        }
    }
}
