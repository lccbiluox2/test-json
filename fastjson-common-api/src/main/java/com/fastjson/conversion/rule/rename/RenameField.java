package com.fastjson.conversion.rule.rename;

import lombok.Data;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-10 18:27
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
@Data
public class RenameField {
    private String field;
    private String type;
    private String targetField;
}
