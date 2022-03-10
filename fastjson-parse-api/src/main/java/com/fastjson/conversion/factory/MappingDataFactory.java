package com.fastjson.conversion.factory;

import cn.hutool.core.lang.Pair;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fastjson.conversion.rule.DataField;
import com.fastjson.conversion.rule.ParserRule;
import com.fastjson.conversion.rule.mapping.MappingDataRangeRule;
import com.fastjson.conversion.rule.mapping.MappingDataRule;
import com.fastjson.conversion.rule.mapping.MappingField;
import com.fastjson.conversion.rule.remove.RemoveFieldRule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-10 16:06
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description: 映射数据
 */
public class MappingDataFactory {

    private static final Logger logger = LoggerFactory.getLogger(MappingDataFactory.class);


    /**
     * 处理数据映射
     * @param parserRule
     * @param parserContext
     */
    public void processMappingDataRule(ParserRule parserRule, ParserContext parserContext) {
        String rule = parserRule.getRule();
        MappingDataRule mappingDataRule = JSONObject.parseObject(rule, MappingDataRule.class);
        List<MappingField> fields = mappingDataRule.getFields();
        if(fields.isEmpty()){
            logger.error("没有配置相关的字段映射策略");
            return;
        }
        Map<String, Map<String, String>> mapingTable = parseMappingTable(mappingDataRule.getMappings());
        
        if(parserContext.isTypeArray()){
            JSONArray array = parserContext.getArray();
            Iterator<Object> iterator = array.iterator();

            JSONArray resultArray = new JSONArray();
            while (iterator.hasNext()){
                JSONObject next = (JSONObject) iterator.next();
                JSONObject result = mappingJSONObjectFieldRule(next, fields, mapingTable);
                resultArray.add(result);
            }

            parserContext.setTypeArray(true);
            parserContext.setArray(resultArray);

        }else {
            JSONObject object = parserContext.getObject();
            object = mappingJSONObjectFieldRule(object,fields,mapingTable);
            parserContext.setTypeArray(false);
            parserContext.setObject(object);
        }
    }

    private JSONObject mappingJSONObjectFieldRule(JSONObject next, List<MappingField> fields, Map<String, Map<String, String>> mapingTable) {
        for (MappingField item:fields ){
            String field = item.getField();
            String value = next.getString(field);
            if(StringUtils.isBlank(value)){
                logger.error("参与映射的数据为空,字段:{}",field);
                continue;
            }
            String mapping_use = item.getMapping_use();
            Map<String, String> mapTableItems = mapingTable.get(mapping_use);
            if(mapTableItems == null){
                logger.error("{}对应的映射表为空",mapping_use);
                continue;
            }
            String mappingData = mapTableItems.get(value);
            String targetField = item.getTargetField();
            next.put(targetField,mappingData);
        }
        return next;
    }

    private Map<String, Map<String, String>> parseMappingTable(JSONObject mappings) {
        Set<String> keys = mappings.keySet();
        Map<String, Map<String, String>> resultMap = new HashMap<>();
        for (String item:keys){
            String string = mappings.getString(item);
            Map<String, String> mapingTable = getTagsMappingList(string);
            resultMap.put(item,mapingTable);
        }
        return resultMap;
    }

    private Map<String, String> getTagsMappingList(String mappings) {
        if(StringUtils.isBlank(mappings)){
            return Collections.emptyMap();
        }
        String[] split = mappings.split("->");
        Map<String, String> map = new HashMap<>();
        map.put(split[0],split[1]);
        return map;
    }

    private Map<String, String> getSclassMappingList(String mappings) {
        if(StringUtils.isBlank(mappings)){
            return Collections.emptyMap();
        }
        String[] split = mappings.split("->");
        Map<String, String> map = new HashMap<>();
        map.put(split[0],split[1]);
        return map;
    }

    private Map<String, String> getMclassMappingList(String mclass_mappings) {
        if(StringUtils.isBlank(mclass_mappings)){
            return Collections.emptyMap();
        }
        String[] split = mclass_mappings.split("->");
        Map<String, String> map = new HashMap<>();
        map.put(split[0],split[1]);
        return map;
    }

    /**
     * 处理数据类型的映射
     *
     * {
     * 	"fields": [{
     * 		"field": "confidence",
     * 		"type": "int",
     * 		"mapping_use": "confidence_mappings",
     * 		"targetField": "confidence"
     *        }],
     * 	"mappings": {
     * 		"confidence_mappings": {
     * 			"Hight": "(80,100]",
     * 			"Medium": "(60,80]",
     * 			"Low": "(0,60]"
     *        }
     *    },
     * 	"isThrowEx": false,
     * 	"exMessage": "xxx"
     * }
     *
     * @param parserRule
     * @param parserContext
     */
    public void processMappingRangeRule(ParserRule parserRule, ParserContext parserContext) {
        String rule = parserRule.getRule();
        MappingDataRangeRule mappingRnageRule = JSONObject.parseObject(rule, MappingDataRangeRule.class);
        List<MappingField> fields = mappingRnageRule.getFields();
        if(fields.isEmpty()){
            logger.error("没有配置相关的字段映射策略");
            return;
        }
        Map<String, Map<String, Pair>> mapingTable = parseMappingRnageTable(mappingRnageRule.getMappings());

        if(parserContext.isTypeArray()){
            JSONArray array = parserContext.getArray();
            Iterator<Object> iterator = array.iterator();

            JSONArray resultArray = new JSONArray();
            while (iterator.hasNext()){
                JSONObject next = (JSONObject) iterator.next();
                JSONObject result = mappingRnageJSONObjectFieldRule(next, fields, mapingTable);
                resultArray.add(result);
            }

            parserContext.setTypeArray(true);
            parserContext.setArray(resultArray);

        }else {
            JSONObject object = parserContext.getObject();
            object = mappingRnageJSONObjectFieldRule(object,fields,mapingTable);
            parserContext.setTypeArray(false);
            parserContext.setObject(object);
        }
    }

    private JSONObject mappingRnageJSONObjectFieldRule(JSONObject next, List<MappingField> fields, Map<String, Map<String, Pair>> mapingTable) {
        for (MappingField item:fields ){
            String field = item.getField();
            Integer value = next.getInteger(field);
            if(value == null){
                logger.error("参与映射的数据为空,字段:{}",field);
                continue;
            }
            String mapping_use = item.getMapping_use();
            Map<String, Pair> mapTableItems = mapingTable.get(mapping_use);
            if(mapTableItems == null){
                logger.error("{}对应的映射表为空",mapping_use);
                continue;
            }
            for (Map.Entry<String, Pair> interVale:mapTableItems.entrySet()){
                // 获取到
                String level = interVale.getKey();
                Pair pair = interVale.getValue();

                Integer min = (Integer) pair.getKey();
                Integer max = (Integer) pair.getValue();
                if(min < value && value <= max){
                    String targetField = item.getTargetField();
                    next.put(targetField,level);
                    break;
                }

            }
        }
        return next;
    }

    private Map<String, Map<String, Pair>> parseMappingRnageTable(JSONObject mappings) {
        Set<String> keys = mappings.keySet();
        Map<String, Map<String, Pair>> resultMap = new HashMap<>();
        for (String item:keys){
            String string = mappings.getString(item);
            Map<String, Pair> mapingTable = getMappingIntervalList(string);
            resultMap.put(item,mapingTable);
        }
        return resultMap;
    }

    // {"Hight":"(80,100]","Low":"(0,60]","Medium":"(60,80]"}
    private Map<String, Pair> getMappingIntervalList(String string) {
        JSONObject object = JSON.parseObject(string);
        Set<String> keySet = object.keySet();
        Map<String, Pair> resultMap = new HashMap<>();
        for (String item:keySet){
            String interval = object.getString(item);
            // 不考虑 边界 默认都是 左开右闭的范围
            String substring = interval.substring(1, interval.length() - 1);
            String[] split = substring.split(",");
            Integer min = Integer.parseInt(split[0]);
            Integer max = Integer.parseInt(split[1]);
            Pair<Integer, Integer> pair = Pair.of(min, max);
            resultMap.put(item, pair);
        }
        return resultMap;
    }
}
