package com.chang.java1;

import java.io.Serializable;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-03 20:12
 */
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物吃东西");
    }
}
