package com.fastjson.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fastjson.conversion.rule.ParserRule;
import com.fastjson.conversion.strategy.ParserConfig;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-08 13:49
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
public class ParserJSONTest {

    private TransformJSON parserJSON = new TransformJSON();

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
        ParserRule getDataByField = new ParserRule(2,"getDataByField","{\"type\":\"getDataByField\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // key数据下沉
        ParserRule keySink = new ParserRule(3,"keyObjectSinkToArray","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONObject\"}],\"condition\":\"data.length > 0\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 根据数据类型格式化数据 比如字符串的转成int
        ParserRule formattingDataByType = new ParserRule(4,"formattingDataByType","{\"type\":\"getData\",\"level\":1,\"fields\":[{\"field\":\"create_time\",\"before_type\":\"long\",\"after_type\":\"long_format_to_string\",\"format\":\"yyyy-MM-dd HH:mm:ss\"}],\"isThrowEx\":false,\"exMessage\":\"xxx\"}");


        rule.add(check);
        rule.add(getDataByField);
        rule.add(keySink);
        rule.add(formattingDataByType);

        parserJSON.parse(data,parserConfig,rule);
    }


    /**
     * 鹰眼情报测试
     * @throws JsonProcessingException
     */
    @Test
    public void stepCodeParse2() throws JsonProcessingException {
        String  data = "{\n" +
                "\t\"code\": \"0\",\n" +
                "\t\"data\": [{\n" +
                "\t\t\"data\": \"45.155.205.211\",\n" +
                "\t\t\"dataType\": \"ip\",\n" +
                "\t\t\"now\": [],\n" +
                "\t\t\"machinename\": \"40.64.5.59, 218.28.220.234\",\n" +
                "\t\t\"location\": {\n" +
                "\t\t\t\"country\": \"俄罗斯\",\n" +
                "\t\t\t\"province\": \"莫斯科\",\n" +
                "\t\t\t\"lng\": 37.611856,\n" +
                "\t\t\t\"city\": \"莫斯科\",\n" +
                "\t\t\t\"countryCode\": \"RU\",\n" +
                "\t\t\t\"lat\": 55.755847\n" +
                "\t\t},\n" +
                "\t\t\"id\": \"c97127e2d74f4486b053d1d11d7a7c86\",\n" +
                "\t\t\"asn\": \"SERVERIUS-AS, NL\",\n" +
                "\t\t\"createDate\": null\n" +
                "\t}, {\n" +
                "\t\t\"data\": \"192.186.1.2\",\n" +
                "\t\t\"dataType\": \"ip\",\n" +
                "\t\t\"now\": [{\n" +
                "\t\t\t\"severity\": \"medium\",\n" +
                "\t\t\t\"data\": \"192.186.1.2\",\n" +
                "\t\t\t\"confidence\": 83,\n" +
                "\t\t\t\"typeName\": \"垃圾邮件\",\n" +
                "\t\t\t\"sourceName\": \"微步-全网IP信誉\",\n" +
                "\t\t\t\"typeMalicious\": \"1\",\n" +
                "\t\t\t\"type\": \"Spam\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"severity\": \"medium\",\n" +
                "\t\t\t\"data\": \"192.186.1.2\",\n" +
                "\t\t\t\"confidence\": 83,\n" +
                "\t\t\t\"typeName\": \"垃圾邮件\",\n" +
                "\t\t\t\"sourceName\": \"微步-全网IP信誉\",\n" +
                "\t\t\t\"typeMalicious\": \"1\",\n" +
                "\t\t\t\"type\": \"Spam\"\n" +
                "\t\t}],\n" +
                "\t\t\"machinename\": \"40.64.5.59, 218.28.220.234\",\n" +
                "\t\t\"location\": {\n" +
                "\t\t\t\"country\": \"美国\",\n" +
                "\t\t\t\"province\": \"\",\n" +
                "\t\t\t\"lng\": -101.407912,\n" +
                "\t\t\t\"city\": \"\",\n" +
                "\t\t\t\"countryCode\": \"US\",\n" +
                "\t\t\t\"lat\": 39.765054\n" +
                "\t\t},\n" +
                "\t\t\"id\": \"30cbab3af1d640abb5a585103e939780\",\n" +
                "\t\t\"asn\": \"FEDERAL-ONLINE-GROUP-LLC - FEDERAL ONLINE GROUP LLC, US\",\n" +
                "\t\t\"createDate\": null\n" +
                "\t}],\n" +
                "\t\"message\": \"success\"\n" +
                "}";

        System.out.println(data);

        ParserConfig parserConfig = new ParserConfig();
        parserConfig.setStrategy("step-code");

        List<ParserRule> rule = new ArrayList<>();


        ParserRule getDataByField = new ParserRule(2,"getDataByField","{\"type\":\"getDataByField\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONArray\"}],\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // 提取字段  作为第几层的数据变量
        ParserRule removeField = new ParserRule(5,"removeField","{\"type\":\"shareData\",\"level\":1,\"fields\":[{\"field\":\"location\",\"type\":\"JSONObject\"},{\"field\":\"asn\",\"type\":\"JSONObject\"},{\"field\":\"createDate\",\"type\":\"JSONObject\"}],\"isThrowEx\":false,\"exMessage\":\"xxx\"}");

        // 提取字段  作为第几层的数据变量 提取每层的
        ParserRule shareArrayData = new ParserRule(5,"removeField","{\"type\":\"shareData\",\"level\":1,\"fields\":[{\"field\":\"location\",\"type\":\"JSONObject\"},{\"field\":\"asn\",\"type\":\"JSONObject\"},{\"field\":\"createDate\",\"type\":\"JSONObject\"}],\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // JsonObject数据下沉到JSONArray 下面的 Object
        ParserRule objectSinkToArrayObject = new ParserRule(3,"ObjectSinkToArrayObject","{\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"String\"},{\"field\":\"dataType\",\"type\":\"String\"},{\"field\":\"machinename\",\"type\":\"String\"},{\"field\":\"id\",\"type\":\"String\"}],\"targetArray\":\"now\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");

        rule.add(getDataByField);
        rule.add(removeField);
        rule.add(objectSinkToArrayObject);

        parserJSON.parse(data,parserConfig,rule);
    }


    /**
     * 奇安信情报
     *
     * @throws JsonProcessingException
     */
    @Test
    public void qianxinParse() throws JsonProcessingException {
        String  data = "{\n" +
                "\t\"data\": [{\n" +
                "\t\t\t\"yzsrdfp.f3322.net\": [{\n" +
                "\t\t\t\t\"alert_name\": \"DSL4 Botnet C&C 活动事件\",\n" +
                "\t\t\t\t\"campaign\": \"\",\n" +
                "\t\t\t\t\"confidence\": \"high\",\n" +
                "\t\t\t\t\"current_status\": \"active\",\n" +
                "\t\t\t\t\"etime\": \"2017-02-06T22:00:52.000Z\",\n" +
                "\t\t\t\t\"id\": \"58beaf702a3317408d408c25\",\n" +
                "\t\t\t\t\"ioc\": [\n" +
                "\t\t\t\t\t\"yzsrdfp.f3322.net\",\n" +
                "\t\t\t\t\t\"0\",\n" +
                "\t\t\t\t\t\"\"\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"ioc_category\": \"DOMAIN_PORT\",\n" +
                "\t\t\t\t\"kill_chain\": \"c2\",\n" +
                "\t\t\t\t\"malicious_family\": [\n" +
                "\t\t\t\t\t\"DSL4\"\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"malicious_type\": \"僵尸网络\",\n" +
                "\t\t\t\t\"platform\": \"Windows\",\n" +
                "\t\t\t\t\"risk\": \"medium\",\n" +
                "\t\t\t\t\"tag\": [\n" +
                "\t\t\t\t\t\"\",\n" +
                "\t\t\t\t\t\"cc\"\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"targeted\": false\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"github.com\": []\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"47.100.10.70\": [{\n" +
                "\t\t\t\t\"alert_name\": \"Gh0st RAT 远控木马活动事件\",\n" +
                "\t\t\t\t\"campaign\": \"\",\n" +
                "\t\t\t\t\"confidence\": \"high\",\n" +
                "\t\t\t\t\"current_status\": \"unknown\",\n" +
                "\t\t\t\t\"etime\": \"2018-05-21T16:41:27.000Z\",\n" +
                "\t\t\t\t\"id\": \"5b0286370edec6074ce30054\",\n" +
                "\t\t\t\t\"ioc\": [\n" +
                "\t\t\t\t\t\"47.100.10.70\",\n" +
                "\t\t\t\t\t\"8080\",\n" +
                "\t\t\t\t\t\"\"\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"ioc_category\": \"IP_PORT\",\n" +
                "\t\t\t\t\"kill_chain\": \"c2\",\n" +
                "\t\t\t\t\"malicious_family\": [\n" +
                "\t\t\t\t\t\"Gh0st\"\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"malicious_type\": \"远控木马\",\n" +
                "\t\t\t\t\"platform\": \"Windows\",\n" +
                "\t\t\t\t\"risk\": \"high\",\n" +
                "\t\t\t\t\"tag\": [\n" +
                "\t\t\t\t\t\"\",\n" +
                "\t\t\t\t\t\"cc\"\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"targeted\": false\n" +
                "\t\t\t}]\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"from\": \"奇安信\",\n" +
                "\t\"msg\": \"执行成功!\",\n" +
                "\t\"status\": 2000,\n" +
                "\t\"user_defined\": []\n" +
                "}";

        System.out.println(data);

        ParserConfig parserConfig = new ParserConfig();
        parserConfig.setStrategy("step-code");

        List<ParserRule> rule = new ArrayList<>();


        ParserRule getDataByField = new ParserRule(2,"getDataByField","{\"type\":\"getDataByField\",\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"JSONArray\"}],\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // key数据下沉
        ParserRule objectKeySinkToArrayObject = new ParserRule(3,"objectKeySinkToArrayObject","{\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"String\"},{\"field\":\"dataType\",\"type\":\"String\"},{\"field\":\"machinename\",\"type\":\"String\"},{\"field\":\"id\",\"type\":\"String\"}],\"targetArray\":\"now\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");

        // 提取字段  作为第几层的数据变量
        ParserRule removeField = new ParserRule(5,"removeField","{\"type\":\"shareData\",\"level\":1,\"fields\":[{\"field\":\"location\",\"type\":\"JSONObject\"},{\"field\":\"asn\",\"type\":\"JSONObject\"},{\"field\":\"createDate\",\"type\":\"JSONObject\"}],\"isThrowEx\":false,\"exMessage\":\"xxx\"}");

        // 提取字段  作为第几层的数据变量 提取每层的
        ParserRule shareArrayData = new ParserRule(5,"removeField","{\"type\":\"shareData\",\"level\":1,\"fields\":[{\"field\":\"location\",\"type\":\"JSONObject\"},{\"field\":\"asn\",\"type\":\"JSONObject\"},{\"field\":\"createDate\",\"type\":\"JSONObject\"}],\"isThrowEx\":false,\"exMessage\":\"xxx\"}");
        // JsonObject数据下沉到JSONArray 下面的 Object
        ParserRule objectSinkToArrayObject = new ParserRule(3,"ObjectSinkToArrayObject","{\"level\":1,\"fields\":[{\"field\":\"data\",\"type\":\"String\"},{\"field\":\"dataType\",\"type\":\"String\"},{\"field\":\"machinename\",\"type\":\"String\"},{\"field\":\"id\",\"type\":\"String\"}],\"targetArray\":\"now\",\"isThrowEx\":false,\"exMessage\":\"xxx\"}");

        rule.add(getDataByField);
        rule.add(objectKeySinkToArrayObject);
//        rule.add(removeField);
//        rule.add(objectSinkToArrayObject);

        parserJSON.parse(data,parserConfig,rule);
    }
}