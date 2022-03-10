package com.fastjson.conversion;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastjson.conversion.factory.FormatDataFactory;
import com.fastjson.conversion.factory.GetDataFactory;
import com.fastjson.conversion.factory.MappingDataFactory;
import com.fastjson.conversion.factory.ParserContext;
import com.fastjson.conversion.factory.RemoveFieldFactory;
import com.fastjson.conversion.factory.RenameFieldsFactory;
import com.fastjson.conversion.factory.SinkDataFactory;
import com.fastjson.conversion.rule.DataField;
import com.fastjson.conversion.rule.ParserRule;
import com.fastjson.conversion.rule.check.CheckDataRule;
import com.fastjson.conversion.rule.mapping.MappingDataRule;
import com.fastjson.conversion.rule.rename.RenameFieldRule;
import com.fastjson.conversion.strategy.ParserConfig;
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
 *
 * 转换json 将一种json转成另外一种json
 */
public class TransformJSON {

    ObjectMapper mapper = new ObjectMapper();
    private GetDataFactory getDataFactory = new GetDataFactory();
    FormatDataFactory formatData = new FormatDataFactory();
    RemoveFieldFactory removeFieldFactory = new RemoveFieldFactory();
    private SinkDataFactory sinkDataFactory = new SinkDataFactory();
    private MappingDataFactory mappingDataFactory = new MappingDataFactory();
    private RenameFieldsFactory renameFieldsFactory = new RenameFieldsFactory();

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
        parserContext.setTypeArray(false);
        for (ParserRule parserRule : rule) {
            int step = parserRule.getStep();
            String name = parserRule.getName();
            if (name.equals("check")) {
                processCheckDataRule(data, parserRule);
            }
            if (name.equals("getData")) {
                getDataFactory.processGetDataRuleAiAnxin(parserRule,parserContext);
                printDetail(parserContext);
            }

            if (name.equals("getDataByField")) {
                getDataFactory.processGetDataByFieldRule(parserRule,parserContext);
                printDetail(parserContext);
            }


            if (name.equals("keyObjectSinkToArray")) {
                processKeyObjectSinkToArray(parserRule,parserContext);
            }
            if (name.equals("formattingDataByType")) {
                formatData.processFormattingDataByType(parserRule,parserContext);
                printDetail(parserContext);
            }

            if (name.equals("removeField")) {
                removeFieldFactory.processRemoveField(parserRule,parserContext);
                printDetail(parserContext);
            }

            if (name.equals("ObjectSinkToArrayObject")) {
                sinkDataFactory.processObjectSinkToArrayObject(parserRule,parserContext);
                printDetail(parserContext);
            }

            if (name.equals("objectKeySinkToArrayObject")) {
                sinkDataFactory.processObjectKeySinkToArrayObject(parserRule,parserContext);
                printDetail(parserContext);
            }

            if (name.equals("MappingDataRule")) {
                mappingDataFactory.processMappingDataRule(parserRule,parserContext);
                printDetail(parserContext);
            }


            if (name.equals("MappingRangeRule")) {
                mappingDataFactory.processMappingRangeRule(parserRule,parserContext);
                printDetail(parserContext);
            }


            if (name.equals("RenameFieldRule")) {
                renameFieldsFactory.processRenameFieldRule(parserRule,parserContext);
                printDetail(parserContext);
            }





        }
    }


    private void processKeyObjectSinkToArray(ParserRule parserRule, ParserContext parserContext) throws JsonProcessingException {
        boolean array = parserContext.isTypeArray();
        JSONObject object = parserContext.getObject();
        Set<String> strings = object.keySet();
        JSONArray arrayResult = new JSONArray();
        for (String item:strings){
            JSONObject item1 = object.getJSONObject(item);
            item1.put("_id",item);
            arrayResult.add(item1);
        }
        parserContext.setTypeArray(true);
        parserContext.setArray(arrayResult);
        printDetail(parserContext);
    }

    private void printDetail(ParserContext parserContext) throws JsonProcessingException {
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println("=====================");
        if(parserContext.isTypeArray()){
            //格式化/美化/优雅的输出
            System.out.println(mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(parserContext.getArray()));
        }else {
            //格式化/美化/优雅的输出
            System.out.println(mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(parserContext.getObject()));
        }

        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println("=====================");
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
