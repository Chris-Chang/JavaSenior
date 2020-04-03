package com.chang.java;

import java.io.Serializable;

/**
 * 若使其序列化需要满足两个条件
 * 1. 实现Serializable接口
 * 2. 当前类提供一个全局常量：serialVersionUID
 * 3.保证其内部所有属性也必须是可序列化的
 * 4.ObjectInputStream和ObjectOutputStream不能序列化static和transient修饰的成员变量
 *
 * transient:将不需要序列化的属性前添加关键字transient，序列化对象的时候，这个属性就不会被序列化:
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-01 10:31
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 22384745859L;
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "com.chang.java.Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
