package com.chang.java;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-07 11:22
 */
public interface MyInterface {
    //如下的三个方法的权限修饰符都是public
    void methodAbstract();

    static void methodStatic(){
        System.out.println("我是接口中的静态方法");
    }

    default void methodDefault(){
        System.out.println("我是接口中的默认方法");
        methodPrivate();
    }

    //java9 中允许接口定义私有的方法
    private void methodPrivate(){
        System.out.println("我是接口中的私有方法");
    }
}
