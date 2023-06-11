package com.fastjson.conversion.rule.mapping;

import lombok.Data;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-10 16:20
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
@Data
public class MappingField {
    private String field;
    private String type;
    private String mapping_use;
    private String targetField;
}
