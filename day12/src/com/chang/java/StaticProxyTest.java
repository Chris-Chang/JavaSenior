package com.chang.java;

/**
 * 静态代理类举例
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-05 10:58
 */
interface ClothFactory{
    void produceCloth();
}
class ProxyClothFactory implements ClothFactory{

    private ClothFactory factory;//用被代理类对象进行实例化

    public ProxyClothFactory(ClothFactory factory){
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂做一些准备工作");
        factory.produceCloth();
        System.out.println("代理工厂做一些后续收尾工作");
    }
}

//被代理类
class NickClothFactory implements ClothFactory{

    @Override
    public void produceCloth() {
        System.out.println("Nick工厂做一批运动服");
    }
}
public class StaticProxyTest {
    public static void main(String[] args) {
        //创建被代理类的对象
        ClothFactory nike = new NickClothFactory();
        //创建代理类对象
        ClothFactory proxyClothFactory = new ProxyClothFactory(nike);
        proxyClothFactory.produceCloth();
    }
}
