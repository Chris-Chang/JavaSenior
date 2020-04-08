package com.chang.bean;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-03-12 10:03
 */

class Window4 extends Thread {

    private static int ticket = 100;
//    private static Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            show();
        }
    }

//    public synchronized void show() {//同步监视器：t1,t2,t3此种解决方式是错误的
    public static synchronized void show() {//同步监视器：window4.class
            if (ticket > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" + ticket);
                ticket--;
            }
    }
}
public class WindowTest4 {
    public static void main(String[] args) {
        Window4 t1 = new Window4();
        Window4 t2 = new Window4();
        Window4 t3 = new Window4();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
