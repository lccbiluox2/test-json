package com.fastjson.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonSortToStringApiTest {

    private JsonSortToStringApi jsonSortToStringApi = new JsonSortToStringApi();

    /**
     * todo: 2023/6/11 15:52 九师兄
     *
     * {
     * 	"name": "lcc",
     * 	"id": 1,
     * 	"personHaveArrayList": [{
     * 		"name": "lcc",
     * 		"id": 1,
     * 		"list": ["lcc", "xkk", "aa"],
     * 		"chName": "xjj"
     *        }, {
     * 		"name": "lcc",
     * 		"id": 1,
     * 		"list": ["lcc", "xkk", "aa"],
     * 		"chName": "xjj"
     *    }],
     * 	"list": ["lcc", "xkk", "aa"],
     * 	"chName": "xjj",
     * 	"personHaveArrays": [{
     * 		"$ref": "$.personHaveArrayList[0]"
     *    }, {
     * 		"$ref": "$.personHaveArrayList[1]"
     *    }]
     * }
     * 测试结果 确实乱了
     **/
    @Test
    public void sortToString() {
        jsonSortToStringApi.sortToString();
    }
}