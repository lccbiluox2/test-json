package com.fastjson.conversion.rule.sink;

import com.fastjson.conversion.rule.DataField;
import lombok.Data;

import java.util.List;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-10 14:37
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 *
 * 下沉数据
 *
 * [{
 * 	"data": "45.155.205.211",
 * 	"dataType": "ip",
 * 	"now": [],
 * 	"machinename": "40.64.5.59, 218.28.220.234",
 * 	"id": "c97127e2d74f4486b053d1d11d7a7c86"
 * }, {
 * 	"data": "192.186.1.2",
 * 	"dataType": "ip",
 * 	"now": [{
 * 		"severity": "medium",
 * 		"data": "192.186.1.2",
 * 		"confidence": 83,
 * 		"typeName": "垃圾邮件",
 * 		"sourceName": "微步-全网IP信誉",
 * 		"typeMalicious": "1",
 * 		"type": "Spam"
 *        }, {
 * 		"severity": "medium",
 * 		"data": "192.186.1.2",
 * 		"confidence": 83,
 * 		"typeName": "垃圾邮件",
 * 		"sourceName": "微步-全网IP信誉",
 * 		"typeMalicious": "1",
 * 		"type": "Spam"
 *    }],
 * 	"machinename": "40.64.5.59, 218.28.220.234",
 * 	"id": "30cbab3af1d640abb5a585103e939780"
 * }]
 *
 * 转换成
 *
 *
 */
@Data
public class ObjectSinkToArrayObjectRule {
    private List<DataField> fields;
    // 目标数组
    private String targetArray;
    // 是否抛出异常
    private boolean isThrowEx;
    // 出现异常的时候抛出的异常
    private String exMessage;
}
