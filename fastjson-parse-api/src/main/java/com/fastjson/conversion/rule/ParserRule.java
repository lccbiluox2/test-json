package com.fastjson.conversion.rule;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-08 13:39
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
@Data
@AllArgsConstructor
public class ParserRule {
    private int step;
    private String name;
    private String rule;

}
