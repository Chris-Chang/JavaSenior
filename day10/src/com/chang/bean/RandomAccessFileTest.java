package com.chang.bean;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 1.RandomAccessFile直接继承于java.lang.Object类，实现DataInput和DataOutput接口
 * 2.RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
 * 3.如果RandomAccessFile作为一个输出流，写出到的文件如果不存在，则在执行过程中自动创建，
 * 如果写出到的文件存在，则会对原有文件内容进行覆盖(默认从头覆盖)
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-01 11:15
 */
public class RandomAccessFileTest {
    @Test
    public void test1(){
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            raf1 = new RandomAccessFile(new File("book.jpg"), "r");
            raf2 = new RandomAccessFile(new File("book1.jpg"), "rw");

            byte[] buffer = new byte[1024];
            int len;
            while((len = raf1.read(buffer)) != -1){
                raf2.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(raf2 != null){
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * RandomAccessFile对指定位置覆盖
     */
    @Test
    public void test2() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("hello.txt", "rw");
        raf1.seek(3);
        raf1.write("xyz".getBytes());
        raf1.close();

    }
}
