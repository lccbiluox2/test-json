package com.fastjson.parse.rule.format;

import lombok.Data;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-10 09:43
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
@Data
public class DataFormatField {
    private String field;
    private String before_type;
    private String after_type;
    private String format;
}
