package com.fastjson.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author lcc
 * @type LombokEntity
 * @desc
 * @date 2022/6/14 09:14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LombokEntity {
    private Integer id;
    private String name;
    private String data;
}
