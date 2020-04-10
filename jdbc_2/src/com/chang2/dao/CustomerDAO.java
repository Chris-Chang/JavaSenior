package com.chang2.dao;

import com.chang2.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 此接口用于规范customer表的常用操作
 *
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-09 20:37
 */
public interface CustomerDAO {
    /**
     * 将cust对象添加到数据库中
     *
     * @param conn
     * @param cust
     */
    void insert(Connection conn, Customer cust);

    /**
     * 针对指定的Id删除表中的一条数据
     *
     * @param conn
     * @param id
     */
    void deleteById(Connection conn, int id);

    /**
     * 针对内存中的cust对象，去修改数据表中指定的记录
     *
     * @param con
     * @param cust
     */
    void update(Connection conn, Customer cust);


    /**
     * 针对指定的id查询得到对应的Customer对象
     *
     * @param conn
     * @param id
     * @return
     */
    Customer getCustomerById(Connection conn, int id);

    /**
     * 查询表中所有记录构成的集合
     *
     * @param conn
     * @return
     */
    List<Customer> getAll(Connection conn);

    /**
     * 返回数据表中数据的条目数
     *
     * @param conn
     * @return
     */
    Long getCount(Connection conn);


    /**
     * 返回数据表中最大生日
     *
     * @param conn
     * @return
     */
    Date getMaxBirth(Connection conn);
}
