package com.chang.java;

/**
 * 使用同步方法解决实现Runnable接口的线程安全问题
 *
 * 关于同步方法的总结：
 * 1. 同步方法仍然涉及到同步监视器，只是不需要我们显示的声明
 * 2. 非静态的同步方法，同步监视器是：当前类本身
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-03-12 9:53
 */

class Window3 implements Runnable{
    private int ticket = 100;
    //    Object obj = new Object();
    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    public synchronized void show(){
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + ":卖票，票号为" + ticket);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticket--;
        }
    }
}

public class WindowTest3 {
    public static void main(String[] args) {
        Window3 w = new Window3();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();

    }
}
