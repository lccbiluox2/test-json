package com.fastjson.parse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fastjson.parse.rule.ParserRule;
import com.fastjson.parse.rule.format.DataFormatField;
import com.fastjson.parse.rule.format.FormatDataRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-10 09:50
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
public class FormatDataFactory {

    private static final Logger logger = LoggerFactory.getLogger(FormatDataFactory.class);

    /**
     * 根据类型格式化jsonArray或者 jsonObject
     * @param parserRule
     * @param parserContext
     * @throws JsonProcessingException
     */
    public void processFormattingDataByType(ParserRule parserRule, ParserContext parserContext) throws JsonProcessingException {
        boolean type = parserContext.isTypeArray();
        String rule = parserRule.getRule();
        FormatDataRule format = JSONObject.parseObject(rule, FormatDataRule.class);
        List<DataFormatField> fields = format.getFields();
        if (fields.isEmpty()) {
            logger.warn("没有需要转换的配置");
            return;
        }
        if (type) {
            JSONArray array = parserContext.getArray();
            Iterator<Object> iterator = array.iterator();

            JSONArray resultArray = new JSONArray();
            while (iterator.hasNext()) {
                JSONObject next = (JSONObject) iterator.next();
                JSONObject result = formatOneJSONObject(next, format);
                resultArray.add(result);
            }
            parserContext.setArray(resultArray);
        } else {
            JSONObject object = parserContext.getObject();
            JSONObject result = formatOneJSONObject(object, format);
            parserContext.setTypeArray(false);
            parserContext.setObject(result);
        }
    }

    private JSONObject formatOneJSONObject(JSONObject resultObject, FormatDataRule format) {
        List<DataFormatField> fields = format.getFields();
        for (DataFormatField item : fields) {
            String field = item.getField();
            Object value = resultObject.get(field);
            if (value == null) {
                logger.warn("字段：{} 对应的值为空", field);
                continue;
            }
            String before_type = item.getBefore_type();
            String after_type = item.getAfter_type();
            switch (after_type) {
                case "long_format_to_string":
                    String data = long_format_to_string(item, value);
                    resultObject.put(field, data);
                    break;
                default:
                    logger.error("暂时没有这种格式化方法");
                    break;
            }
        }
        return resultObject;
    }

    private String long_format_to_string(DataFormatField item, Object value) {
        String format = item.getFormat();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String format1 = sdf.format(value);
        return format1;
    }


}
