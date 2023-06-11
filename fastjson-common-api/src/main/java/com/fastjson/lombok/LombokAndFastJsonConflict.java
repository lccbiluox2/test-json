package com.fastjson.lombok;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

/**
 * @author lcc
 * @type LombokAndFastJsonConflict
 * @desc
 * @date 2022/6/14 09:14
 */
public class LombokAndFastJsonConflict {


    /**
     * 测试点：测试fastjson 和 lombok导致的代码冲突
     *
     * 测试结果 果然找不到方法
     *
     *
     * java: 找不到符号
     *   符号:   方法 setData(java.lang.String)
     *   位置: 类型为com.fastjson.demo.lombok.LombokEntity的变量 lombokEntity
     *
     * 参考文章：https://cloud.tencent.com/developer/article/1991502
     */
    public void lombokAndFastJsonConflict(){
        LombokEntity lombokEntity = new LombokEntity();
        lombokEntity.setData("{\"id\":\"111\",\"name\":\"sssss\"}");
        HashMap<String ,String> hashMap = JSON.parseObject(lombokEntity.getData(), HashMap.class);
    }
} 

