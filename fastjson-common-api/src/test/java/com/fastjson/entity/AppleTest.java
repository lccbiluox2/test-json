package com.fastjson.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

import java.math.BigDecimal;

/***
 * todo: 九师兄  2023/5/9 22:41
 *
 * 【FastJson】FastJson AutoType
 * https://blog.csdn.net/qq_21383435/article/details/130590063
 */
public class AppleTest {

    /***
     * todo: 九师兄  2023/5/9 22:22
     *
     * 测试点：测试数据序列化 其中一个是接口 Fruit
     * 我们创建了一个store，为他指定了名称，并且创建了一个Fruit的子类型Apple，
     * 然后将这个store使用`JSON.toJSONString`进行序列化，可以得到以下JSON内容
     *
     * 打印结果如下
     *
     * toJSONString : {"fruit":{"price":0.5},"name":"Hollis"}
     *
     */
    @Test
    public void interfaceSer(){
        Store store = new Store();
        store.setName("Hollis");
        Apple apple = new Apple();
        apple.setPrice(new BigDecimal(0.5));
        store.setFruit(apple);
        String jsonString = JSON.toJSONString(store);
        System.out.println("toJSONString : " + jsonString);
    }

    /***
     * todo: 九师兄  2023/5/9 22:22
     *
     * 测试点：测试数据序列化 其中一个是接口 Fruit
     * 我们创建了一个store，为他指定了名称，并且创建了一个Fruit的子类型Apple，
     * 然后将这个store使用`JSON.toJSONString`进行序列化，可以得到以下JSON内容
     *
     * 打印结果如下
     *
     * ClassCastException: com.sun.proxy.$Proxy4 cannot be cast to com.fastjson.entity.Apple
     *
     * 可以看到，在将store反序列化之后，我们尝试将Fruit转换成Apple，但是抛出了异常，尝试直接转换成Fruit则不会报错
     *
     * todo: 当一个类中包含了一个接口（或抽象类）的时候，在使用fastjson进行序列化的时候，会将子类型抹去，
     *       只保留接口（抽象类）的类型， 使得反序列化时无法拿到原始类型。
     *
     */
    @Test
    public void interfaceSerV1(){
        String jsonString = "{\"fruit\":{\"price\":0.5},\"name\":\"Hollis\"}";
        Store newStore = JSON.parseObject(jsonString, Store.class);
        System.out.println("parseObject : " + newStore);
        Apple newApple = (Apple)newStore.getFruit();
        System.out.println("getFruit : " + newApple);
    }

    /***
     * todo: 九师兄  2023/5/9 22:27
     * 测试点：测试 使用 SerializerFeature 序列化 与反序列化，序列化的时候
     *      写入类信息，解决接口的问题
     *
     * 测试结果如下
     *
     * toJSONString : {"@type":"com.fastjson.entity.Store","fruit":{"@type":"com.fastjson.entity.Apple","price":0.5},"name":"Hollis"}
     * parseObject : com.fastjson.entity.Store@1134affc
     * getFruit : Apple(price=0.5)
     *
     * 可以看到，使用`SerializerFeature.WriteClassName`进行标记后，JSON字符串中多出了
     * 一个`@type`字段，标注了类对应的原始类型，方便在反序列化的时候定位到具体类型
     *
     * 因为有了autoType功能，那么fastjson在对JSON字符串进行反序列化的时候，就会读取`@type`
     * 到内容，试图把JSON内容反序列化成这个对象，并且会调用这个类的setter方法。
     */
    @Test
    public void interfaceSerV2(){

        Store store = new Store();
        store.setName("Hollis");
        Apple apple = new Apple();
        apple.setPrice(new BigDecimal(0.5));
        store.setFruit(apple);
        // todo: 这一点保留
        String jsonString = JSON.toJSONString(store, SerializerFeature.WriteClassName);
        System.out.println("toJSONString : " + jsonString);

        Store newStore = JSON.parseObject(jsonString, Store.class);
        System.out.println("parseObject : " + newStore);
        Apple newApple = (Apple)newStore.getFruit();
        System.out.println("getFruit : " + newApple);

    }
}