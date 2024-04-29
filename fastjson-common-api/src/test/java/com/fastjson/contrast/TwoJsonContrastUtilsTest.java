package com.fastjson.contrast;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class TwoJsonContrastUtilsTest {


    @Test
    public void contrast() {
        String inData = "{\"destGeoRegionCode\":\"JS\",\"deviceId\":\"123123341\",\"responseCode\":\"200\",\"ruleId\":\"12010084\",\"deviceCat\":\"/Audit/Web\",\"machineCode\":\"testmachineCode_zj01\",\"tags\":[\"勒索软件\"],\"deviceSendProductName\":\"明御Web应用防火墙\",\"destMacAddress\":\"09-FF-CA-D3-E3-09\",\"deviceAddress\":\"192.168.30.1\",\"destGeoCountry\":\"中国\",\"customerId\":\"2\",\"refreshDate\":0,\"createTime\":1665554992455,\"baas_engineInfo\":\"info:78.118.1.25,1.datanode1\",\"IoCLevel\":\"Critical\",\"deviceName\":\"192.168.26.196\",\"destHostName\":\"www.testGZ.com\",\"srcGeoLatitude\":\"22.396427\",\"eventId\":\"6034908163071804483\",\"alarmName\":\"自定义情报库触发\",\"srcGeoCountry\":\"中国\",\"requestMethod\":\"GET\",\"TIType\":\"ip\",\"deviceProductType\":\"Web应用防火墙\",\"externalId\":\"dbapp-WAF:12010084\",\"attacker\":[\"1.1.1.1\"],\"threatSeverity\":\"High\",\"message\":\"WAF告警：SQL注入攻击。发生时间：2022-10-14 13:17:56 威胁级别：高 客户端IP：103.12.68.186 客户端端口：52461 服务器IP：114.212.0.4 服务器端口：80 动作：告警 HTTP/S响应码：200\",\"baas_alarm_level_uuid\":\"39813d01-6e84-4626-af0d-67b62498e9ab\",\"sclass\":\"/WhiteList/Others\",\"baas_sink_process_time\":\"1666071666574\",\"id\":\"customIntelligenceSource_1662084919121__ip__1.1.1.1\",\"TIName\":\"customIntelligenceSource_1662084919121\",\"deviceAssetTypeId\":\"48\",\"eventType\":\"1\",\"deviceAssetType\":\"安全类\",\"IoCThreatName\":\"自定义情报库触发\",\"catObject\":\"/Host/Application/Service\",\"deviceProtocol\":\"ftp\",\"eventIDs\":\"5034932860148534364\",\"isAPT\":false,\"suggestion\":\"defaultTemplate\",\"deviceAssetSubType\":\"Web应用防火墙\",\"updateTime\":1665554992455,\"destGeoLatitude\":\"32.041546\",\"modelName\":\"log_testQB002\",\"catBehavior\":\"/Access\",\"fromCustomStrategy\":true,\"dvcAction\":\"告警\",\"destGeoIsp\":\"教育网\",\"netId\":\"8cc61186-1c9e-42cd-88ea-0a544492cd25\",\"srcOrgId\":\"outer\",\"sourceEventIds\":\"5034932860148534364\",\"destGeoRegion\":\"江苏\",\"victim\":[\"1.1.1.1\"],\"direction\":\"11\",\"destOrgId\":\"outer\",\"subCategory\":\"/Others/Others\",\"srcGeoRegionCode\":\"XG\",\"modelType\":\"intelligence\",\"destGeoAddress\":\"江苏南京教育网\",\"expirationTime\":253402271999000,\"appProtocol\":\"http\",\"destSecurityZone\":\"outer\",\"attackerSecurityZone\":[\"outer\"],\"requestUrl\":\"114.212.0.4/scripts/app.waf.js\",\"destGeoCountryCode\":\"CN\",\"dbappWafAlertLog\":true,\"inner_ioc_type\":\"内置情报\",\"endTime\":\"2022-10-18 13:41:06\",\"IoCType\":\"ip\",\"registrationTime\":0,\"inner_tiMatchField\":\"srcAddress\",\"deviceReceiptTime\":\"2022-10-14 13:17:56\",\"mclass\":\"/WhiteList\",\"TIMatchField\":\"srcAddress\",\"name\":\"自定义情报库触发\",\"originator\":true,\"victimSecurityZone\":[\"outer\"],\"expired\":false,\"inner_IoC\":\"1.1.1.1\",\"destGeoCity\":\"南京\",\"expirationDate\":0,\"severity\":7,\"inner_reference\":\"ioc1->log_testQB002->AiLPHA_AutoTest_Intelligence_32->AiLPHA_AutoTest_Intelligence_31->log_testGZ001->log_testQB001->AiLPHA_AutoTest_Intelligence_11->log_testGZ002->dbappThreatIntelligenceHASH->AiLPHA_AutoTest_Intelligence_13\",\"sendHostAddress\":\"10.50.1.28\",\"srcPort\":123,\"enable\":true,\"destAddress\":\"18.50.93.31\",\"srcAddress\":\"1.1.1.1\",\"srcGeoCountryCode\":\"HK\",\"productVendorName\":\"安恒\",\"IoCIcon\":\"恶意\",\"catOutcome\":\"OK\",\"killChain\":\"KC_Others\",\"collectorReceiptTime\":\"2022-10-14 13:17:56\",\"startTime\":\"2022-10-18 13:41:06\",\"baas_internal_save_event\":false,\"srcGeoAddress\":\"香港\",\"deviceAssetSubTypeId\":\"13\",\"srcGeoRegion\":\"香港\",\"eventCount\":1,\"alarmDescription\":\"defaultTemplate\",\"srcGeoLongitude\":\"114.1095\",\"destGeoLongitude\":\"118.76741\",\"logname\":\"SQL注入攻击lcc\",\"attackSignature\":\"=30431170531;\",\"catTechnique\":\"/Exploit/Vulnerability/SQLInjection\",\"IoC\":\"1.1.1.1\",\"dataSource\":\"security_logs\",\"destPort\":123456,\"chineseModelName\":\"QB修改目的端口_开启抑制\",\"baas_internal_save_alarm\":true,\"attackName\":\"SQL注入攻击\",\"confidence\":\"High\",\"srcSecurityZone\":\"outer\",\"requestUrlQuery\":\"114.212.0.4/scripts/app.waf.js?v=30431170531;cat /etc/hosts;\",\"catSignificance\":\"/Informational/Alert\",\"category\":\"/Others\"}\n";
        String outData = "{\"inner_reference\":\"ioc1->log_testQB002->AiLPHA_AutoTest_Intelligence_32->AiLPHA_AutoTest_Intelligence_31->log_testGZ001->log_testQB001->AiLPHA_AutoTest_Intelligence_11->log_testGZ002->dbappThreatIntelligenceHASH->AiLPHA_AutoTest_Intelligence_13\",\"baas_alarm_level_uuid\":\"39813d01-6e84-4626-af0d-67b62498e9ab\",\"subCategory\":\"/Others/Others\",\"destGeoRegionCode\":\"JS\",\"sclass\":\"/WhiteList/Others\",\"sendHostAddress\":\"10.50.1.28\",\"baas_sink_process_time\":\"1666071718556\",\"srcPort\":123,\"deviceId\":\"123123341\",\"responseCode\":\"200\",\"srcGeoRegionCode\":\"XG\",\"enable\":true,\"destAddress\":\"18.50.93.31\",\"id\":\"customIntelligenceSource_1662084919121__ip__1.1.1.1\",\"ruleId\":\"12010084\",\"deviceCat\":\"/Audit/Web\",\"TIName\":\"customIntelligenceSource_1662084919121\",\"deviceAssetTypeId\":\"48\",\"machineCode\":\"testmachineCode_zj01\",\"eventType\":\"1\",\"modelType\":\"intelligence\",\"srcAddress\":\"1.1.1.1\",\"deviceAssetType\":\"安全类\",\"srcGeoCountryCode\":\"HK\",\"tags\":[\"勒索软件\"],\"IoCThreatName\":\"自定义情报库触发\",\"productVendorName\":\"安恒\",\"catObject\":\"/Host/Application/Service\",\"deviceSendProductName\":\"明御Web应用防火墙\",\"IoCIcon\":\"恶意\",\"destGeoAddress\":\"江苏南京教育网\",\"expirationTime\":253402271999000,\"catOutcome\":\"OK\",\"killChain\":\"KC_Others\",\"deviceProtocol\":\"ftp\",\"collectorReceiptTime\":\"2022-10-14 13:17:56\",\"appProtocol\":\"http\",\"destSecurityZone\":\"outer\",\"destMacAddress\":\"09-FF-CA-D3-E3-09\",\"eventIDs\":\"5034932860148534364\",\"attackerSecurityZone\":[\"outer\"],\"deviceAddress\":\"192.168.30.1\",\"destGeoCountry\":\"中国\",\"requestUrl\":\"114.212.0.4/scripts/app.waf.js\",\"customerId\":\"2\",\"destGeoCountryCode\":\"CN\",\"startTime\":\"2022-10-18 13:42:05\",\"baas_internal_save_event\":false,\"srcGeoAddress\":\"香港\",\"dbappWafAlertLog\":true,\"refreshDate\":0,\"isAPT\":false,\"suggestion\":\"defaultTemplate\",\"deviceAssetSubType\":\"Web应用防火墙\",\"updateTime\":1665554992455,\"destGeoLatitude\":\"32.041546\",\"modelName\":\"log_testQB002\",\"createTime\":1665554992455,\"baas_engineInfo\":\"info:78.118.1.11,1.flink1\",\"catBehavior\":\"/Access\",\"inner_ioc_type\":\"内置情报\",\"IoCLevel\":\"Critical\",\"endTime\":\"2022-10-18 13:42:05\",\"deviceAssetSubTypeId\":\"13\",\"srcGeoRegion\":\"香港\",\"fromCustomStrategy\":true,\"IoCType\":\"ip\",\"registrationTime\":0,\"dvcAction\":\"告警\",\"inner_tiMatchField\":\"srcAddress\",\"eventCount\":1,\"deviceReceiptTime\":\"2022-10-14 13:17:56\",\"deviceName\":\"192.168.26.196\",\"mclass\":\"/WhiteList\",\"TIMatchField\":\"srcAddress\",\"alarmDescription\":\"defaultTemplate\",\"destHostName\":\"www.testGZ.com\",\"srcGeoLongitude\":\"114.1095\",\"srcGeoLatitude\":\"22.396427\",\"eventId\":\"6034908712827617758\",\"destGeoIsp\":\"教育网\",\"netId\":\"8cc61186-1c9e-42cd-88ea-0a544492cd25\",\"destGeoLongitude\":\"118.76741\",\"logname\":\"SQL注入攻击\",\"alarmName\":\"自定义情报库触发\",\"srcGeoCountry\":\"中国\",\"srcOrgId\":\"outer\",\"sourceEventIds\":\"5034932860148534364\",\"attackSignature\":\"=30431170531;\",\"catTechnique\":\"/Exploit/Vulnerability/SQLInjection\",\"name\":\"自定义情报库触发\",\"destGeoRegion\":\"江苏\",\"IoC\":\"1.1.1.1\",\"dataSource\":\"security_logs\",\"requestMethod\":\"GET\",\"originator\":true,\"TIType\":\"ip\",\"victimSecurityZone\":[\"outer\"],\"expired\":false,\"destPort\":123456,\"chineseModelName\":\"QB修改目的端口_开启抑制\",\"deviceProductType\":\"Web应用防火墙\",\"baas_internal_save_alarm\":true,\"inner_IoC\":\"1.1.1.1\",\"victim\":[\"1.1.1.1\"],\"direction\":\"11\",\"destGeoCity\":\"南京\",\"expirationDate\":0,\"severity\":7,\"attackName\":\"SQL注入攻击\",\"destOrgId\":\"outer\",\"confidence\":\"High\",\"externalId\":\"dbapp-WAF:12010084\",\"attacker\":[\"1.1.1.1\"],\"threatSeverity\":\"High\",\"srcSecurityZone\":\"outer\",\"message\":\"WAF告警：SQL注入攻击。发生时间：2022-10-14 13:17:56 威胁级别：高 客户端IP：103.12.68.186 客户端端口：52461 服务器IP：114.212.0.4 服务器端口：80 动作：告警 HTTP/S响应码：200\",\"requestUrlQuery\":\"114.212.0.4/scripts/app.waf.js?v=30431170531;cat /etc/hosts;\",\"catSignificance\":\"/Informational/Alert\",\"category\":\"/Others\"}";
        JSONObject in = JSONObject.parseObject(inData);
        JSONObject out = JSONObject.parseObject(outData);
        TwoJsonContrastUtils.contrast(in,out);
    }
}