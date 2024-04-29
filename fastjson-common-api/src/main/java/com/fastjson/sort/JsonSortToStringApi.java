package com.fastjson.sort;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.fastjson.entity.PersonHaveArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonSortToStringApi {

    /**
     * todo: 2023/6/11 15:42 九师兄
     *
     * 测试点：如果一个对象内部有集合类型的属性，那么顺序还是乱
     *
     * 【FastJson】fastjson序列化保持字段顺序
     * https://blog.csdn.net/qq_21383435/article/details/126429372
     **/
    public void sortToString(){
        PersonHaveArray personHaveArray = new PersonHaveArray();
        personHaveArray.setId(1);
        personHaveArray.setName("lcc");
        personHaveArray.setChName("xjj");
        List<String> list = Arrays.asList(new String[]{"lcc","xkk","aa"});
        personHaveArray.setList(list);

        PersonHaveArray A1 = new PersonHaveArray();
        A1.setId(1);
        A1.setName("lcc");
        A1.setChName("xjj");
        A1.setList(list);

        PersonHaveArray A2 = new PersonHaveArray();
        A2.setId(1);
        A2.setName("lcc");
        A2.setChName("xjj");
        A2.setList(list);

        PersonHaveArray[] personHaveArrays = new PersonHaveArray[2];
        personHaveArrays[0] = A1;
        personHaveArrays[1] = A2;
        personHaveArray.setPersonHaveArrays(personHaveArrays);

        List<PersonHaveArray> list1 = new ArrayList<>();
        list1.add(A1);
        list1.add(A2);

        personHaveArray.setPersonHaveArrayList(list1);

        String stuString = JSONObject.toJSONString(personHaveArray);

        JSONObject jsonObject = JSON.parseObject(stuString, Feature.SortFeidFastMatch);
        System.out.println(jsonObject);
    }
}
