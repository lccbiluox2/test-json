package com.fastjson.conversion.factory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fastjson.conversion.rule.DataField;
import com.fastjson.conversion.rule.ParserRule;
import com.fastjson.conversion.rule.remove.RemoveFieldRule;
import com.fastjson.conversion.rule.rename.RenameField;
import com.fastjson.conversion.rule.rename.RenameFieldRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-10 18:22
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
public class RenameFieldsFactory {

    private static final Logger logger = LoggerFactory.getLogger(RenameFieldsFactory.class);


    public void processRenameFieldRule(ParserRule parserRule, ParserContext parserContext) {
        String rule = parserRule.getRule();
        RenameFieldRule renameFieldRule = JSONObject.parseObject(rule, RenameFieldRule.class);
        List<RenameField> fields = renameFieldRule.getFields();
        if(fields.isEmpty()){
            logger.error("没有配置相关的重命名策略");
            return;
        }
        if(parserContext.isTypeArray()){
            JSONArray array = parserContext.getArray();
            Iterator<Object> iterator = array.iterator();
            while (iterator.hasNext()){
                JSONObject next = (JSONObject) iterator.next();
                renameSONObjectFieldRule(next,fields);
            }
            parserContext.setTypeArray(true);
            parserContext.setArray(array);
        }else {
            JSONObject object = parserContext.getObject();
            renameSONObjectFieldRule(object,fields);
            System.out.println(object);
        }
    }

    private void renameSONObjectFieldRule(JSONObject next, List<RenameField> fields) {
        for (RenameField item:fields){
            String field = item.getField();
            Object o = next.get(field);
            String targetField = item.getTargetField();
            next.put(targetField,o);
            next.remove(field);
        }
    }
}
