package com.fastjson.eval;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lcc
 * @type JSONPathEvalDemo
 * @desc
 * @date 2022/6/14 14:57
 */
public class JSONPathEvalDemo {

    private static Logger logger = LoggerFactory.getLogger(JSONPathEvalDemo.class);


    public void jsonEvalDemp() {
        String jsonStr = "{ \"store\": {\"book\": [{ \"category\": \"reference\"," +
                "\"author\": \"Nigel Rees\",\"title\": \"Sayings of the Century\"," +
                "\"price\": 8.95},{ \"category\": \"fiction\",\"author\": \"Evelyn Waugh\"," +
                "\"title\": \"Sword of Honour\",\"price\": 12.99,\"isbn\": \"0-553-21311-3\"" +
                "}],\"bicycle\": {\"color\": \"red\",\"price\": 19.95}}}";

        logger.info("{}", jsonStr);
        //Object jsonObject = JSON.parse(jsonStr); // 先解析JSON数据
        JSONObject jsonObject = JSON.parseObject(jsonStr);

        System.out.println("\n Book数目：" + JSONPath.eval(jsonObject, "$.store.book.size()"));
        System.out.println("第一本书title：" + JSONPath.eval(jsonObject, "$.store.book[0].title"));
        System.out.println("price大于10元的book：" + JSONPath.eval(jsonObject, "$.store.book[price > 10]"));
        System.out.println("price大于10元的title：" + JSONPath.eval(jsonObject, "$.store.book[price > 10][0].title"));
        System.out.println("category(类别)为fiction(小说)的book：" + JSONPath.eval(jsonObject, "$.store.book[category = 'fiction']"));
        System.out.println("bicycle的所有属性值" + JSONPath.eval(jsonObject, "$.store.bicycle.*"));
        System.out.println("bicycle的color和price属性值" + JSONPath.eval(jsonObject, "$.store.bicycle['color','price']"));
    }
} 

