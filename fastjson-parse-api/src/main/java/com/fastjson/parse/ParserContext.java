package com.fastjson.parse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author: chuanchuan.lcc
 * @date: 2022-03-09 14:53
 * @modifiedBy: chuanchuan.lcc
 * @version: 1.0
 * @description:
 */
@Data
public class ParserContext {
    // 是否是JSONArray
    private boolean type;
    private JSONObject object;
    private JSONArray array;
}
