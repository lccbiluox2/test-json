package com.fastjson.compare;

import com.alibaba.fastjson.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class CompareTwoData {

    /***
     * todo: 九师兄  2024/4/29 09:39
     * 测试点：测试两个json数据对比
     */
    public void compareTwoData(String dataA, String dataB) {
        JSONObject jsonObjectA = JSONObject.parseObject(dataA);
        JSONObject jsonObjectB = JSONObject.parseObject(dataB);

        Set<String> stringsA = jsonObjectA.keySet();
        Set<String> stringsB = jsonObjectB.keySet();

        Set<String> all = new HashSet<>();
        all.addAll(stringsA);
        all.addAll(stringsB);

        for (String item : all) {
            String stringA = jsonObjectA.getString(item);
            String stringB = jsonObjectB.getString(item);
            if (stringA == null && stringB == null) {
                continue;
            } else if (stringA == null && stringB != null) {
                System.out.println("字段\t\t\t" + item + "\t\t\t\t" + "dataA:" + stringA + " dataB:" + stringB);
            }else if (stringA != null && stringB == null) {
                System.out.println("字段\t\t\t" + item + "\t\t\t\t" + "dataA:" + stringA + " dataB:" + stringB);
            }else if (stringA != null && stringB != null) {
                if(stringA.equals(stringB)){
                    continue;
                }
                System.out.println("字段\t\t\t" + item + "\t\t\t\t" + "dataA:" + stringA + " dataB:" + stringB);
            }
        }
    }
}
