package com.chang.java1;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-03 20:14
 */
@MyAnnotation(value = "hi")
public class Person extends Creature<String> implements Comparable<String>,MyInterface {

    private String name;
    int age;
    public int id;

    public Person(){}

    @MyAnnotation(value = "abc")
    Person(String name){
        this.name = name;
    }

    private Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @MyAnnotation
    private String show(String nation){
        System.out.println("我的国籍是" + nation);
        return nation;
    }

    public String display(String interests , int age) throws NullPointerException,ClassCastException{
        return interests + age;
    }
    @Override
    public int compareTo(String o) {
        return 0;
    }

    @Override
    public void info() {
        System.out.println("我是一个人");
    }

    private static void showDesc(){
        System.out.println("我是一个可爱的人");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
