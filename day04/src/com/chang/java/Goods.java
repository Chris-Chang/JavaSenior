package com.chang.java;

/**
 * 商品类
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-03-26 11:15
 */
public class Goods implements Comparable{
    private String name;
    private double price;

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Goods() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    /**
     * 指明商品比较大小的方式:按照价格从低到高进行排序,再按照产品名称从高到低排序
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        if(o instanceof Goods){
            Goods goods = (Goods)o;
//            方式一
            if(this.price > goods.price){
                return 1;
            }else if(this.price < goods.price){
                return -1;
            }else{
//                return 0;
                return -this.name.compareTo(goods.name);
            }
            //方式二：
//            return Double.compare(this.price,goods.price);

        }
        throw new RuntimeException("传入的数据类型不一致！");
    }
}
