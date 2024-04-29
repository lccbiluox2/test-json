package com.fastjson.reference;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-08 14:54
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description: 测试json的互相引用
 */
public class JSONArrayReference {

    /**
     * 测试点：测试fastjson的循环引用
     * 原因：一个环境，对方设置了一条规则，但是我却产出了2条数据，按理说一条规则产出一条数据，因为
     *     我是循环处理的，然后看着代码没有问题，但是运行起来产生了2条数据，而且是一样的
     *     主要是 json的这个语法产生的 "$ref\": \"$[0]\"\n"
     */
    public void jsonArrayRefenence(){
        String  data = "[{\n" +
                "\t\"rule\": \" (deviceHostname == \\\"www.zzz.com\\\") \",\n" +
                "\t\"username\": \"admin\",\n" +
                "\t\"userid\": 1,\n" +
                "\t\"type\": \"robot,dingtalk\"\n" +
                "}, {\n" +
                "\t\"$ref\": \"$[0]\"\n" +
                "}]";

        /**
         * 这一步解析的时候就已经变成2个了
         *
         * 0 = {JSONObject@975}  size = 4
         *  0 = {HashMap$Node@978} "rule" -> " (deviceHostname == "www.zzz.com") "
         *  1 = {HashMap$Node@979} "type" -> "robot,dingtalk"
         *  2 = {HashMap$Node@980} "userid" -> "1"
         *  3 = {HashMap$Node@981} "username" -> "admin"
         * 1 = {JSONObject@975}  size = 4
         *  0 = {HashMap$Node@978} "rule" -> " (deviceHostname == "www.zzz.com") "
         *  1 = {HashMap$Node@979} "type" -> "robot,dingtalk"
         *  2 = {HashMap$Node@980} "userid" -> "1"
         *  3 = {HashMap$Node@981} "username" -> "admin"
         */
        JSONArray jsonObject = JSONArray.parseArray(data);
        Iterator<Object> iterator = jsonObject.iterator();
        while (iterator.hasNext()){
            JSONObject next = (JSONObject) iterator.next();
            System.out.println(next.toJSONString());
        }
    }
}
