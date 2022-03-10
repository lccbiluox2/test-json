package com.fastjson.conversion.rule.check;

import com.fastjson.conversion.rule.DataField;
import lombok.Data;

import java.util.List;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-08 14:17
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
@Data
public class CheckDataRule {
    // 本次操作的类型
    private String type;
    private List<DataField> fields;
    private String condition;
    // 是否抛出异常
    private boolean isThrowEx;
    // 出现异常的时候抛出的异常
    private String exMessage;
}
