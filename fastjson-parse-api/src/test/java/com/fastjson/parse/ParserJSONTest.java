package com.fastjson.parse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fastjson.parse.ParserJSON;
import com.fastjson.parse.rule.ParserRule;
import com.fastjson.parse.strategy.ParserConfig;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-08 13:49
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
public class ParserJSONTest {

    private ParserJSON parserJSON = new ParserJSON();

    @Test
    public void stepCodeParse() throws JsonProcessingException {
        String  data = "{\n" +
                "  \"cost\": 69,\n" +
                "  \"code\": 1,\n" +
                "  \"message\": \"获取到2条结果\",\n" +
                "  \"data\": {\n" +
                "    \"ipxxxxxx\": {\n" +
                "      \"malicious\": true,\n" +
                "      \"threat_type\": \"C2\",\n" +
                "      \"threat_subtype\": \"Trojan\",\n" +
                "      \"confidence_level\": 3,\n" +
                "      \"confidence_score\": 8,\n" +
                "      \"risk_level\": 5,\n" +
                "      \"tags\": [\"apt\", \"c2\", \"apt_NOBELIUM\", \"钓鱼网站\"],\n" +
                "      \"is_apt\": true,\n" +
                "      \"warn_name\": \"普通远控木马活动事件\",\n" +
                "      \"source_name\": \"xxxx\",\n" +
                "      \"create_time\": 1629110670936,\n" +
                "      \"update_time\": 1629110679304\n" +
                "    },\n" +
                "    \"192.99.221.77\": {\n" +
                "      \"malicious\": true,\n" +
                "      \"threat_type\": \"C2\",\n" +
                "      \"threat_subtype\": \"Trojan\",\n" +
                "      \"confidence_level\": 3,\n" +
                "      \"confidence_score\": 8,\n" +
                "      \"risk_level\": 5,\n" +
                "      \"tags\": [\"apt\", \"c2\", \"apt_NOBELIUM\", \"钓鱼网站\"],\n" +
                "      \"is_apt\": true,\n" +
                "      \"warn_name\": \"普通远控木马活动事件\",\n" +
                "      \"source_name\": \"安恒情报源\",\n" +
                "      \"create_time\": 1629110670936,\n" +
                "      \"update_time\": 1629110679304\n" +
                "    }\n" +
                "  }\n" +
                "}";

        ParserConfig parserConfig = new ParserConfig();
        parserConfig.setStrategy("step-code");

        List<ParserRule> rule = new ArrayList<>();

        ParserRule check = new ParserRule(1,"check","{\"type\":\"check\",\"level\":1,\"fields\":[{\"field\":\"cost\",\"type\":\"int\"},{\"field\":\"code\",\"type\":\"int\"}],\"condition\":\"code > 0\",\"isThrowEx\":true,\"exMessage\":\"xxx\"}");
        ParserRule getData = new ParserRule(2,"getData","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // key数据下沉
        ParserRule keySink = new ParserRule(3,"keyObjectSinkToArray","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 根据数据类型格式化数据 比如字符串的转成int
        ParserRule formattingDataByType = new ParserRule(4,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 第 4层的数据 向上 取
        ParserRule upwardData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 获取邻居的数据
        ParserRule neighborData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 根据某个字段进行映射
        ParserRule mappingData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 根据某2个字段进行组合
        ParserRule combinationData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 根据某个字段进行排序
        ParserRule sortData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 提取全局字段
        ParserRule globalData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 提取字段  作为第几层的数据变量
        ParserRule shareData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");


        rule.add(check);
        rule.add(getData);
        rule.add(keySink);

        parserJSON.parse(data,parserConfig,rule);
    }


    @Test
    public void stepCodeParse1() throws JsonProcessingException {
        String  data = "{\n" +
                "  \"cost\": 69,\n" +
                "  \"code\": 1,\n" +
                "  \"message\": \"获取到2条结果\",\n" +
                "  \"data\": {\n" +
                "    \"ipxxxxxx\": {\n" +
                "      \"malicious\": true,\n" +
                "      \"threat_type\": \"C2\",\n" +
                "      \"threat_subtype\": \"Trojan\",\n" +
                "      \"confidence_level\": 3,\n" +
                "      \"confidence_score\": 8,\n" +
                "      \"risk_level\": 5,\n" +
                "      \"tags\": [\"apt\", \"c2\", \"apt_NOBELIUM\", \"钓鱼网站\"],\n" +
                "      \"is_apt\": true,\n" +
                "      \"warn_name\": \"普通远控木马活动事件\",\n" +
                "      \"source_name\": \"xxxx\",\n" +
                "      \"create_time\": 1629110670936,\n" +
                "      \"update_time\": 1629110679304\n" +
                "    },\n" +
                "    \"192.99.221.77\": {\n" +
                "      \"malicious\": true,\n" +
                "      \"threat_type\": \"C2\",\n" +
                "      \"threat_subtype\": \"Trojan\",\n" +
                "      \"confidence_level\": 3,\n" +
                "      \"confidence_score\": 8,\n" +
                "      \"risk_level\": 5,\n" +
                "      \"tags\": [\"apt\", \"c2\", \"apt_NOBELIUM\", \"钓鱼网站\"],\n" +
                "      \"is_apt\": true,\n" +
                "      \"warn_name\": \"普通远控木马活动事件\",\n" +
                "      \"source_name\": \"安恒情报源\",\n" +
                "      \"create_time\": 1629110670936,\n" +
                "      \"update_time\": 1629110679304\n" +
                "    }\n" +
                "  }\n" +
                "}";

        ParserConfig parserConfig = new ParserConfig();
        parserConfig.setStrategy("step-code");

        List<ParserRule> rule = new ArrayList<>();

        ParserRule check = new ParserRule(1,"check","{\"type\":\"check\",\"level\":1,\"fields\":[{\"field\":\"cost\",\"type\":\"int\"},{\"field\":\"code\",\"type\":\"int\"}],\"condition\":\"code > 0\",\"isThrowEx\":true,\"exMessage\":\"xxx\"}");
        ParserRule getData = new ParserRule(2,"getData","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // key数据下沉
        ParserRule keySink = new ParserRule(3,"keyObjectSinkToArray","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 根据数据类型格式化数据 比如字符串的转成int
        ParserRule formattingDataByType = new ParserRule(4,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"create_time\",\"before_type\":\"long\",\"after_type\":\"long_format_to_string\",\"format\":\"yyyy-MM-dd HH:mm:ss\"}],\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 第 4层的数据 向上 取
        ParserRule upwardData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 获取邻居的数据
        ParserRule neighborData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 根据某个字段进行映射
        ParserRule mappingData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 根据某2个字段进行组合
        ParserRule combinationData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 根据某个字段进行排序
        ParserRule sortData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 提取全局字段
        ParserRule globalData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 提取字段  作为第几层的数据变量
        ParserRule shareData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");


        rule.add(check);
        rule.add(getData);
        rule.add(keySink);
        rule.add(formattingDataByType);

        parserJSON.parse(data,parserConfig,rule);
    }


    @Test
    public void stepCodeParse2() throws JsonProcessingException {
        String  data = "{\n" +
                "    \"code\": \"0\",\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"data\": \"45.155.205.211\",\n" +
                "            \"dataType\": \"ip\",\n" +
                "            \"now\": [],\n" +
                "            \"machinename\": \"40.64.5.59, 218.28.220.234\",\n" +
                "            \"location\": {\n" +
                "                \"country\": \"俄罗斯\",\n" +
                "                \"province\": \"莫斯科\",\n" +
                "                \"lng\": 37.611856,\n" +
                "                \"city\": \"莫斯科\",\n" +
                "                \"countryCode\": \"RU\",\n" +
                "                \"lat\": 55.755847\n" +
                "            },\n" +
                "            \"id\": \"c97127e2d74f4486b053d1d11d7a7c86\",\n" +
                "            \"asn\": \"SERVERIUS-AS, NL\",\n" +
                "            \"createDate\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"data\": \"192.186.1.2\",\n" +
                "            \"dataType\": \"ip\",\n" +
                "            \"now\": [\n" +
                "                {\n" +
                "                    \"severity\": \"medium\",\n" +
                "                    \"data\": \"192.186.1.2\",\n" +
                "                    \"confidence\": 83,\n" +
                "                    \"typeName\": \"垃圾邮件\",\n" +
                "                    \"sourceName\": \"微步-全网IP信誉\",\n" +
                "                    \"typeMalicious\": \"1\",\n" +
                "                    \"type\": \"Spam\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"machinename\": \"40.64.5.59, 218.28.220.234\",\n" +
                "            \"location\": {\n" +
                "                \"country\": \"美国\",\n" +
                "                \"province\": \"\",\n" +
                "                \"lng\": -101.407912,\n" +
                "                \"city\": \"\",\n" +
                "                \"countryCode\": \"US\",\n" +
                "                \"lat\": 39.765054\n" +
                "            },\n" +
                "            \"id\": \"30cbab3af1d640abb5a585103e939780\",\n" +
                "            \"asn\": \"FEDERAL-ONLINE-GROUP-LLC - FEDERAL ONLINE GROUP LLC, US\",\n" +
                "            \"createDate\": null\n" +
                "        }\n" +
                "    ],\n" +
                "    \"message\": \"success\"\n" +
                "}";

        System.out.println(data);

        ParserConfig parserConfig = new ParserConfig();
        parserConfig.setStrategy("step-code");

        List<ParserRule> rule = new ArrayList<>();

        ParserRule check = new ParserRule(1,"check","{\"type\":\"check\",\"level\":1,\"fields\":[{\"field\":\"cost\",\"type\":\"int\"},{\"field\":\"code\",\"type\":\"int\"}],\"condition\":\"code > 0\",\"isThrowEx\":true,\"exMessage\":\"xxx\"}");
        ParserRule getData = new ParserRule(2,"getData","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // key数据下沉
        ParserRule keySink = new ParserRule(3,"keyObjectSinkToArray","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 根据数据类型格式化数据 比如字符串的转成int
        ParserRule formattingDataByType = new ParserRule(4,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"create_time\",\"before_type\":\"long\",\"after_type\":\"long_format_to_string\",\"format\":\"yyyy-MM-dd HH:mm:ss\"}],\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 第 4层的数据 向上 取
        ParserRule upwardData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 获取邻居的数据
        ParserRule neighborData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 根据某个字段进行映射
        ParserRule mappingData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 根据某2个字段进行组合
        ParserRule combinationData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 根据某个字段进行排序
        ParserRule sortData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 提取全局字段
        ParserRule globalData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 提取字段  作为第几层的数据变量
        ParserRule shareData = new ParserRule(5,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");


        ParserRule getDataByField = new ParserRule(2,"getDataByField","{\"type\":\"getDataByField\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONArray\"}],\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        rule.add(getDataByField);
        rule.add(check);

        rule.add(keySink);
        rule.add(formattingDataByType);

        parserJSON.parse(data,parserConfig,rule);
    }
}