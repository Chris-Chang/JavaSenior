package com.chang.exer;

/**
 * 练习： 创建两个线程，其中一个线程遍历100以内的偶数，另一个线程遍历100以内的奇数
 * @author chang
 * @create 2020-03-07 17:34
 */
public class ThreadDemo {
    public static void main(String[] args) {
//        MyThead1 m1 = new MyThead1();
//        MyThead2 m2 = new MyThead2();
//        m1.start();
//        m2.start();

        //创建Thread类的匿名子类的方式
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i <100; i++) {
                    if(i % 2 == 0){
                        System.out.println(Thread.currentThread().getName()+":"+ i);
                    }

                }

            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i <100; i++) {
                    if(i % 2 != 0){
                        System.out.println(Thread.currentThread().getName()+":"+ i);
                    }

                }

            }
        }.start();
    }
}

class  MyThead1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+ i);
            }
            
        }
    }
}
class  MyThead2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <100; i++) {
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName()+":"+ i);
            }

        }
    }
}
