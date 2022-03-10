package com.fastjson.parse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fastjson.parse.rule.DataField;
import com.fastjson.parse.rule.ParserRule;
import com.fastjson.parse.rule.getdata.GetDataByFieldRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-10 10:50
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
public class GetDataFactory {

    private static final Logger logger = LoggerFactory.getLogger(GetDataFactory.class);

    public void processGetDataRule(ParserRule parserRule, ParserContext parserContext) {
//        parserContext.isTypeArray()
//        Object data2 = data.get("data");
//        if(data instanceof JSONObject){
//            parserContext.setType(false);
//            parserContext.setObject(data1);
//        }
//        JSONObject data1 = data.getJSONObject("data");

    }

    public void processGetDataRuleAiAnxin(ParserRule parserRule, ParserContext parserContext) {
        JSONObject object = parserContext.getObject();
        JSONArray data = object.getJSONArray("data");
        parserContext.setTypeArray(true);
        parserContext.setArray(data);
    }

    public void processGetDataByFieldRule(ParserRule parserRule, ParserContext parserContext) {
        String rule = parserRule.getRule();
        GetDataByFieldRule getDataByFieldRule = JSONObject.parseObject(rule, GetDataByFieldRule.class);
        List<DataField> fields = getDataByFieldRule.getFields();
        DataField dataField = fields.get(0);
        String field = dataField.getField();
        String type = dataField.getType();

        if(parserContext.isTypeArray()){
            JSONArray array = parserContext.getArray();
            if(type.equals("JSONArray")){
                logger.info("不支持这种获取方式");
            }else {
                logger.info("不支持这种获取方式");
            }
        }else {
            JSONObject object = parserContext.getObject();
            if(type.equals("JSONArray")){
                JSONArray jsonArray = object.getJSONArray(field);
                parserContext.setTypeArray(true);
                parserContext.setArray(jsonArray);
            }else {
                JSONObject jsonObject = object.getJSONObject(field);
                parserContext.setTypeArray(false);
                parserContext.setObject(jsonObject);
            }
        }

    }
}
