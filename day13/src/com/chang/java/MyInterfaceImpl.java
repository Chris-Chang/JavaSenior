package com.chang.java;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-07 11:25
 */
public class MyInterfaceImpl implements MyInterface{

    @Override
    public void methodAbstract() {

    }

    @Override
    public void methodDefault() {
        System.out.println("实现类重写了接口中的默认方法");
    }

    public static void main(String[] args) {
        //接口中的静态方法只能由接口自己调用
        MyInterface.methodStatic();
        //接口的实现类不能调用接口的静态方法
//        MyInterfaceImpl.methodStatic();

        MyInterfaceImpl impl = new MyInterfaceImpl();
        impl.methodDefault();

        //接口中的私有方法实现类不能调用
//        impl.methodPrivate();

    }


}
