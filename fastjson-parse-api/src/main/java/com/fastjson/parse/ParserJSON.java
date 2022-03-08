package com.fastjson.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fastjson.parse.rule.ParserRule;
import com.fastjson.parse.rule.check.CheckDataRule;
import com.fastjson.parse.strategy.ParserConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-08 13:33
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
public class ParserJSON {

    public String parse(String json, ParserConfig parserConfig, List<ParserRule> rule){
        if(rule == null || rule.isEmpty()){
            return json;
        }

        String strategy = parserConfig.getStrategy();
        if(strategy.equals("step-code")){
            parseByStepCode(json,parserConfig,rule);
        }
        return "";
    }


    private void parseByStepCode(String json, ParserConfig parserConfig, List<ParserRule> rule) {
        for (ParserRule parserRule : rule){
            int step = parserRule.getStep();
            String name = parserRule.getName();
            String ruleDetail = parserRule.getRule();
            CheckDataRule checkDataRule = JSONObject.parseObject(ruleDetail, CheckDataRule.class);
            System.out.println(checkDataRule);
        }
    }

}
