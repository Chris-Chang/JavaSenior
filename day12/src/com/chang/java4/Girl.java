package com.chang.java4;

/**
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-06 17:06
 */
public class Girl {
    private String name;

    @Override
    public String toString() {
        return "Girl{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Girl() {
    }

    public Girl(String name) {
        this.name = name;
    }
}
