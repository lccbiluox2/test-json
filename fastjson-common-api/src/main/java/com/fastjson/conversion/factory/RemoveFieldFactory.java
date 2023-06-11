package com.fastjson.conversion.factory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fastjson.conversion.rule.DataField;
import com.fastjson.conversion.rule.ParserRule;
import com.fastjson.conversion.rule.remove.RemoveFieldRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-10 11:23
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
public class RemoveFieldFactory {

    private static final Logger logger = LoggerFactory.getLogger(RemoveFieldFactory.class);

    /**
     * 处理字段的移除，移除主要是移除干扰的数据，方便解析
     */
    public void processRemoveField(ParserRule parserRule, ParserContext parserContext) {
        String rule = parserRule.getRule();
        RemoveFieldRule removeFieldRule = JSONObject.parseObject(rule, RemoveFieldRule.class);
        List<DataField> fields = removeFieldRule.getFields();
        if(fields.isEmpty()){
            logger.error("没有配置相关的移除策略");
            return;
        }
        if(parserContext.isTypeArray()){
            JSONArray array = parserContext.getArray();
            Iterator<Object> iterator = array.iterator();
            while (iterator.hasNext()){
                JSONObject next = (JSONObject) iterator.next();
                removeJSONObjectFieldRule(next,fields);
                System.out.println(next);
            }
        }else {
            JSONObject object = parserContext.getObject();
            removeJSONObjectFieldRule(object,fields);
            System.out.println(object);
        }
    }

    private void removeJSONObjectFieldRule(JSONObject next, List<DataField> fields) {
        for (DataField item:fields){
            String field = item.getField();
            next.remove(field);
        }
    }
}
