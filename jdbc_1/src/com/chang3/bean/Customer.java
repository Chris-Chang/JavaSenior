package com.chang3.bean;

import java.sql.Date;

/**
 * ORM编程思想（object relational mapping)
 * 一个数据表对应一个java类
 * 表中的一条记录对应java类中的一个对象
 * 表中的一个字段对应java类的一个属性
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-09 10:10
 */
public class Customer {
    private int id;
    private String name;
    private String email;
    private Date birth;

    public Customer(int id, String name, String email, Date birth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
