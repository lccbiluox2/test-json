package com.fastjson.parse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastjson.parse.rule.DataField;
import com.fastjson.parse.rule.ParserRule;
import com.fastjson.parse.rule.check.CheckDataRule;
import com.fastjson.parse.strategy.ParserConfig;
import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-08 13:33
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
public class ParserJSON {

    ObjectMapper mapper = new ObjectMapper();
    FormatDataFactory formatData = new FormatDataFactory();

    public String parse(String json, ParserConfig parserConfig, List<ParserRule> rule) throws JsonProcessingException {
        if (rule == null || rule.isEmpty()) {
            return json;
        }
        JSONObject data = JSONObject.parseObject(json);

        String strategy = parserConfig.getStrategy();
        if (strategy.equals("step-code")) {
            parseByStepCode(data, parserConfig, rule);
        }
        return "";
    }


    private void parseByStepCode(JSONObject data, ParserConfig parserConfig, List<ParserRule> rule) throws JsonProcessingException {
        ParserContext parserContext = new ParserContext();
        parserContext.setObject(data);
        parserContext.setType(false);
        for (ParserRule parserRule : rule) {
            int step = parserRule.getStep();
            String name = parserRule.getName();
            if (name.equals("check")) {
                processCheckDataRule(data, parserRule);
            }
            if (name.equals("getData")) {
                processGetDataRule(data, parserRule,parserContext);
            }

            if (name.equals("keyObjectSinkToArray")) {
                processKeyObjectSinkToArray(parserRule,parserContext);
            }
            if (name.equals("formattingDataByType")) {
                formatData.processFormattingDataByType(parserRule,parserContext);
                printDetail(parserContext);
            }



        }
    }


    private void processKeyObjectSinkToArray(ParserRule parserRule, ParserContext parserContext) throws JsonProcessingException {
        boolean array = parserContext.isType();
        JSONObject object = parserContext.getObject();
        Set<String> strings = object.keySet();
        JSONArray arrayResult = new JSONArray();
        for (String item:strings){
            JSONObject item1 = object.getJSONObject(item);
            item1.put("_id",item);
            arrayResult.add(item1);
        }
        parserContext.setType(true);
        parserContext.setArray(arrayResult);
        printDetail(parserContext);
    }

    private void printDetail(ParserContext parserContext) throws JsonProcessingException {
        if(parserContext.isType()){
            //格式化/美化/优雅的输出
            System.out.println(mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(parserContext.getArray()));
        }else {
            //格式化/美化/优雅的输出
            System.out.println(mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(parserContext.getObject()));
        }

    }

    private void processGetDataRule(JSONObject data, ParserRule parserRule,ParserContext parserContext) {
        JSONObject data1 = data.getJSONObject("data");
        parserContext.setType(false);
        parserContext.setObject(data1);
    }

    private void processCheckDataRule(JSONObject data, ParserRule parserRule) {
        String ruleDetail = parserRule.getRule();
        CheckDataRule checkDataRule = JSONObject.parseObject(ruleDetail, CheckDataRule.class);
        String condition = checkDataRule.getCondition();
        List<DataField> fields = checkDataRule.getFields();
        Map<String, Object> tempMap = getDataByType(data, fields);
        Boolean flag = (Boolean) AviatorEvaluator.execute(condition, tempMap);
        boolean throwEx = checkDataRule.isThrowEx();
        // 如果计算为false 是否抛出异常
        if (!flag && throwEx) {
            throw new IllegalArgumentException(checkDataRule.getExMessage());
        }
    }

    private Map<String, Object> getDataByType(JSONObject data, List<DataField> fields) {
        Map<String, Object> map = new HashMap<>();
        for (DataField item : fields) {
            String field = item.getField();
            String type = item.getType();
            Object o = data.get(field);
            map.put(field, o);
        }
        return map;
    }

}
