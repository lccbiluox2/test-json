package com.fastjson.conversion.factory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fastjson.conversion.rule.DataField;
import com.fastjson.conversion.rule.ParserRule;
import com.fastjson.conversion.rule.sink.ObjectKeySinkToArrayObjectRule;
import com.fastjson.conversion.rule.sink.ObjectSinkToArrayObjectRule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-10 14:39
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description: 下沉数据的工厂
 */
public class SinkDataFactory {

    private static final Logger logger = LoggerFactory.getLogger(SinkDataFactory.class);

    /**
     * 下沉Object的数据到数组的下一个层次
     *
     * @param parserRule
     * @param parserContext
     */
    public void processObjectSinkToArrayObject(ParserRule parserRule, ParserContext parserContext) {
        String rule = parserRule.getRule();
        ObjectSinkToArrayObjectRule removeFieldRule = JSONObject.parseObject(rule, ObjectSinkToArrayObjectRule.class);
        List<DataField> fields = removeFieldRule.getFields();
        if (fields.isEmpty()) {
            logger.error("没有配置相关的下沉字段信息");
            return;
        }
        String targetArray = removeFieldRule.getTargetArray();
        if (StringUtils.isBlank(targetArray)) {
            logger.error("下沉的目标数组为空");
            return;
        }

        if (parserContext.isTypeArray()) {
            JSONArray array = parserContext.getArray();
            Iterator<Object> iterator = array.iterator();
            List<Integer> removeIndex = new ArrayList<>();
            int remove = -1;

            JSONArray resultArray = new JSONArray();
            while (iterator.hasNext()) {
                remove++;
                JSONObject next = (JSONObject) iterator.next();
                JSONArray jsonArray = next.getJSONArray(targetArray);
                if (jsonArray == null || jsonArray.isEmpty()) {
                    logger.warn("该下沉的目标为空，忽略这个数据");
                    removeIndex.add(remove);
                    continue;
                }
                // 提取要下沉的数据
                JSONObject needSinkData = new JSONObject();
                for (DataField item : fields) {
                    Object o = next.get(item.getField());
                    needSinkData.put(item.getField(),o);
                }
                Iterator<Object> innerItery = jsonArray.iterator();
                while (innerItery.hasNext()){
                    JSONObject next1 = (JSONObject) innerItery.next();
                    next1.putAll(needSinkData);
                    resultArray.add(next1);
                }

                parserContext.setTypeArray(true);
                parserContext.setArray(resultArray);
            }
        } else {
            JSONObject object = parserContext.getObject();
            // 提取要下沉的数据
            JSONObject needSinkData = new JSONObject();
            for (DataField item : fields) {
                Object o = object.get(item.getField());
                needSinkData.put(item.getField(),o);
            }
            JSONArray jsonArray = object.getJSONArray(targetArray);
            if (jsonArray == null || jsonArray.isEmpty()) {
                logger.warn("该下沉的目标为空，忽略这个数据");
                return;
            }

            JSONArray resultArray = new JSONArray();
            Iterator<Object> innerItery = jsonArray.iterator();
            while (innerItery.hasNext()){
                JSONObject next1 = (JSONObject) innerItery.next();
                next1.putAll(needSinkData);
                resultArray.add(next1);
            }

            parserContext.setTypeArray(true);
            parserContext.setArray(resultArray);
        }
    }

    /**
     * [{
     * 	"yzsrdfp.f3322.net": [{
     * 		"alert_name": "DSL4 Botnet C&C 活动事件",
     * 		"campaign": "",
     *
     * 这样的数据下沉到
     *
     * [{
     * 	[{
     * 		"alert_name": "DSL4 Botnet C&C 活动事件",
     * 		"campaign": "",
     * 	    "_id":"yzsrdfp.f3322.net"
     * @param parserRule
     * @param parserContext
     */
    public void processObjectKeySinkToArrayObject(ParserRule parserRule, ParserContext parserContext) {
        String rule = parserRule.getRule();
        ObjectKeySinkToArrayObjectRule removeFieldRule = JSONObject.parseObject(rule, ObjectKeySinkToArrayObjectRule.class);

        if (parserContext.isTypeArray()) {
            JSONArray array = parserContext.getArray();
            Iterator<Object> iterator = array.iterator();

            JSONArray resultArray = new JSONArray();
            while (iterator.hasNext()) {
                JSONObject next = (JSONObject) iterator.next();
                System.out.println(next);
                Set<String> keys = next.keySet();
                if(keys.isEmpty() || keys.size() > 1){
                    logger.error("数据不规范，不适合这种下沉方式");
                    continue;
                }
                Object keyOne = keys.toArray()[0];
                JSONArray jsonArray = next.getJSONArray(keyOne.toString());
                Iterator<Object> innerItery = jsonArray.iterator();
                while (innerItery.hasNext()){
                    JSONObject next1 = (JSONObject) innerItery.next();
                    next1.put("_id",keyOne);
                    resultArray.add(next1);
                }
            }
            parserContext.setTypeArray(true);
            parserContext.setArray(resultArray);
        } else {

        }
    }
}
