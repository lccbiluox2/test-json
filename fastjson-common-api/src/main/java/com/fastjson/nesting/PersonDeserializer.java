package com.fastjson.nesting;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;

public class PersonDeserializer implements ObjectDeserializer {

    private static final String CONN_TYPE = "type";

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object o) {
        JSONObject jsonObj = parser.parseObject();
        String personType = jsonObj.getString(CONN_TYPE);


        Person connectParam;
        switch (personType) {
            case "WOMAN":
                connectParam = jsonObj.toJavaObject(Woman.class);
                break;
            case "MAN":
                connectParam = jsonObj.toJavaObject(Man.class);
                break;
            default:
                throw new IllegalArgumentException("not support connector type found");
        }
        return (T) connectParam;

    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}