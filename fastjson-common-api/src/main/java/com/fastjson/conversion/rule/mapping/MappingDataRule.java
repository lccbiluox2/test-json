package com.fastjson.conversion.rule.mapping;

import com.alibaba.fastjson.JSONObject;
import com.fastjson.conversion.rule.DataField;
import lombok.Data;

import java.util.List;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-10 16:07
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
@Data
public class MappingDataRule {
    private List<MappingField> fields;
    // 映射表集合
    private JSONObject mappings;
    // 是否抛出异常
    private boolean isThrowEx;
    // 出现异常的时候抛出的异常
    private String exMessage;
}
