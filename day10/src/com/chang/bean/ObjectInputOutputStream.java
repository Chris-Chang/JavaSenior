package com.chang.bean;

import org.junit.Test;

import java.io.*;

/**
 * 对象流的使用
 *
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-01 10:09
 */
public class ObjectInputOutputStream {
    @Test
    public void testObjectOutputStream() {
//        1.
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("test.dat"));
//        2.

            objectOutputStream.writeObject(new String("我爱中国"));
            objectOutputStream.flush();//刷新

            objectOutputStream.writeObject(new Person("小米",20));
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//        3.
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @Test
    public void testObjectInputStream(){
//        1.
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("test.dat"));
//        2.
            Object obj = ois.readObject();
            String str = (String)obj;

            Person p = (Person)ois.readObject();

            System.out.println(p);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
