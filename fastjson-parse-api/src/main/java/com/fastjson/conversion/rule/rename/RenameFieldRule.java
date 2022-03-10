package com.fastjson.conversion.rule.rename;

import com.fastjson.conversion.rule.DataField;
import lombok.Data;

import java.util.List;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-10 18:21
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
@Data
public class RenameFieldRule {
    private List<RenameField> fields;
    // 是否抛出异常
    private boolean isThrowEx;
    // 出现异常的时候抛出的异常
    private String exMessage;
}
