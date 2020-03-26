package com.chang.exer;

import org.junit.Test;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-03-24 9:14
 */
public class IDEADebug {
    @Test
    public void testStringBuffer(){
        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);

        System.out.println(sb.length());//4
        System.out.println(sb);//"null"

        StringBuffer sb1 = new StringBuffer(str);//抛异常
        System.out.println(sb1);
    }
}
