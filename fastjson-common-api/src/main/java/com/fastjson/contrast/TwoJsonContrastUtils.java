package com.fastjson.contrast;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TwoJsonContrastUtils {

    /**
     *todo: 10/18/22 2:02 PM 九师兄
     *
     * 是这样的，我要做全链路，然后对方说输出数据和全链路测试的数据不一样，为了验证那些不一样
     * 我写了如下代码，可以计算出两个json那些字段不一样
     **/
    public static void contrast(JSONObject inData,JSONObject outData){
        Set<String> inKeys = inData.keySet();
        Set<String> outKeys = outData.keySet();

        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(inKeys);
        allKeys.addAll(outKeys);

        Map<String,Object>  inNoData = new HashMap();
        Map<String,Object>  outNoData = new HashMap();
        List<String> noEqualData = new ArrayList<>();

        for (String fieldName: allKeys){
            Object inItem = inData.get(fieldName);
            Object outItem = outData.get(fieldName);
            if(inItem == null){
                inNoData.put(fieldName,"null");
            }
            if(outItem == null){
                outNoData.put(fieldName,"null");
            }
            if(inItem != null && outItem != null){
                if(!inItem.toString().equals(outItem.toString())){
                    noEqualData.add(fieldName);
                }
            }
        }

        printMap("输入数据为空的字段",inNoData);
        printMap("输出数据为空的字段",outNoData);
        System.out.println("不相等的字段"+noEqualData);
    }

    private static void printMap(String tag, Map<String, Object> map) {
        System.out.println(tag);
        for (Map.Entry<String, Object> item:map.entrySet()){
            System.out.println(item.getKey() +" -> "+item.getValue());
        }
    }
}
