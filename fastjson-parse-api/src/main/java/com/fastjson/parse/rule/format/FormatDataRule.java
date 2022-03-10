package com.fastjson.parse.rule.format;

import com.fastjson.parse.rule.DataField;
import lombok.Data;

import java.util.List;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-10 09:38
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
@Data
public class FormatDataRule {
    // 本次操作的类型
    private String type;
    private List<DataFormatField> fields;
    // 是否抛出异常
    private boolean isThrowEx;
    // 出现异常的时候抛出的异常
    private String exMessage;
}
