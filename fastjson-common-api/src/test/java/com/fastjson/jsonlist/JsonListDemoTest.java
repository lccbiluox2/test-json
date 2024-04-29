package com.fastjson.jsonlist;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: chuanchuan.lcc
 * @date: 2020-12-29 10:09
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
public class JsonListDemoTest {


    @Test
    public void test5() {


        String jsonStr = "[{\n" +
                "\t\"id\": \"lcc\",\n" +
                "\t\"name\": \"xx\"\n" +
                "}, {\n" +
                "\t\"id\": \"lcc\",\n" +
                "\t\"name\": \"xx\"\n" +
                "}]";

        List<UserDemo> list = JSONArray.parseArray(jsonStr, UserDemo.class);
        if(list instanceof ArrayList){
            System.out.println("ArrayList");
        }
        System.out.println(list);
    }

}