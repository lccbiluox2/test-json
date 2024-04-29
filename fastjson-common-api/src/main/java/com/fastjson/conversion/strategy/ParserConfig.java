package com.fastjson.conversion.strategy;

import lombok.Data;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-08 13:39
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
@Data
public class ParserConfig {
    /**
     * 采用哪种策略去解析
     * step-code： 采用配置步骤加上代码解析
     * aiql: aiql解析
     * template：模板解析
     */
    private String strategy;

}
