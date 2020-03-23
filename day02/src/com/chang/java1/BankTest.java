package com.chang.java1;

/**
 * 使用同步机制将单例中的懒汉式改为线程安全的
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-03-12 12:06
 */
public class BankTest {
}

class Bank{
    private Bank(){}
    private static Bank instance = null;
    public static  Bank getInstance(){
        //方式一：效率稍差
//        synchronized (Bank.class) {
//            if (instance == null) {
//                instance = new Bank();
//            }
//            return instance;
//        }

        //方式二：效率更高
        if(instance == null) {
            synchronized (Bank.class){
                instance = new Bank();
            }
        }
        return instance;
    }
}
