package com.fastjson.reference.parse;

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
    public void stepCodeParse() {
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

        ParserRule check = new ParserRule(1,"check","{\"type\":\"check\",\"level\":1,\"fields\":[{\"cost\":\"int\",\"code\":\"int\"}],\"condition\":\"code > 0\"}");
        ParserRule getData = new ParserRule(1,"getData","");

        rule.add(check);
        rule.add(getData);

        parserJSON.parse(data,parserConfig,rule);
    }
}