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
                "            \"resolves\": 1,\n" +
                "            \"severity\": \"medium\",\n" +
                "            \"iocType\": \"domain\",\n" +
                "            \"apt\": null,\n" +
                "            \"ip\": {\n" +
                "                \"20150316\": [\n" +
                "                    \"173.230.158.166\",\n" +
                "                    \"209.99.40.227\"\n" +
                "                ],\n" +
                "                \"20150718\": [\n" +
                "                    \"173.230.158.166\"\n" +
                "                ],\n" +
                "                \"20160608\": [\n" +
                "                    \"195.22.26.232\"\n" +
                "                ],\n" +
                "                \"20160929\": [\n" +
                "                    \"89.185.44.100\"\n" +
                "                ],\n" +
                "                \"20190816\": [\n" +
                "                    \"72.26.218.76\"\n" +
                "                ],\n" +
                "                \"20191207\": [\n" +
                "                    \"72.26.218.70\"\n" +
                "                ]\n" +
                "            },\n" +
                "            \"relatedSample\": [\n" +
                "                \"e97db0cfeaa2ea5faf8f997a902432da\"\n" +
                "            ],\n" +
                "            \"confidence\": 85,\n" +
                "            \"intelType\": \"C2\",\n" +
                "            \"industry\": [],\n" +
                "            \"uri\": null,\n" +
                "            \"ssl\": [],\n" +
                "            \"platform\": [\n" +
                "                \"all\"\n" +
                "            ],\n" +
                "            \"tags\": [\n" +
                "                \"Ramnit\"\n" +
                "            ],\n" +
                "            \"protocol\": null,\n" +
                "            \"application\": null,\n" +
                "            \"port\": 80,\n" +
                "            \"createTime\": null,\n" +
                "            \"whois\": {\n" +
                "                \"expiryDate\": \"2021-06-07T13:50:07.0Z\",\n" +
                "                \"updateDate\": \"2020-05-23T14:20:44.0Z\",\n" +
                "                \"techStreet\": \"PO Box 701\",\n" +
                "                \"techCity\": \"San Mateo\",\n" +
                "                \"nameServer\": \"ns1.fget-career.com|ns2.fget-career.com|ns3.fget-career.com|ns4.fget-career.com|ns5.fget-career.com|ns6.fget-career.com|ns7.fget-career.com|ns8.fget-career.com\",\n" +
                "                \"registrarName\": \"DYNADOT LLC\",\n" +
                "                \"registrantEmail\": \"fget-career.com@superprivacyservice.com\",\n" +
                "                \"techCountry\": \"US\",\n" +
                "                \"ioc\": \"fget-career.com\",\n" +
                "                \"registrantName\": \"Super Privacy Service LTD c/o Dynadot\",\n" +
                "                \"createDate\": \"2016-06-07T13:50:07.0Z\"\n" +
                "            },\n" +
                "            \"tlp\": 3,\n" +
                "            \"levelN\": null,\n" +
                "            \"sourceName\": \"微步-失陷指标\",\n" +
                "            \"id\": \"0a308c7411b612f596246502\",\n" +
                "            \"ioc\": \"fget-career.com\",\n" +
                "            \"timestamp\": 1569413113\n" +
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


        rule.add(getData);
        rule.add(check);

        rule.add(keySink);
        rule.add(formattingDataByType);

        parserJSON.parse(data,parserConfig,rule);
    }
}