package com.fastjson;

import org.junit.Before;
import org.junit.Test;

public class JsonToObjectPerformanceTest {

    private JsonToObjectPerformance jsonToObjectPerformance = new JsonToObjectPerformance();

    @Before
    public void before() {
        jsonToObjectPerformance.before();
    }

    /**
     *todo: 2023/5/31 22:59 九师兄
     *
     * 测试点：JSON提供把对象转化为JSON字符串、把JSON字符串转为对象的功能，于是被某些人用来转化对象。
     * 这种对象转化方式，虽然在功能上没有问题，但是在性能上却存在问题。
     *
     * 测试如下，可以看到性能确实有非常大的差距
     *
     * jsonToObj耗时:669
     * setObj耗时:7
     **/
    @Test
    public void setObj() {
        jsonToObjectPerformance.jsonToObj();
        jsonToObjectPerformance.setObj();
    }

    /**
     *todo: 2023/5/31 23:03 九师兄
     * 测试点：尽量不使用反射赋值对象
     *
     * 用反射赋值对象，主要优点是节省了代码量，主要缺点却是性能有所下降。
     *
     * BeanUtil耗时:383
     * setObj耗时:8
     *
     * 可以看到同样性能下降的厉害
     **/
    @Test
    public void copyProperties() {
        jsonToObjectPerformance.copyProperties();
        jsonToObjectPerformance.setObj();
    }
}