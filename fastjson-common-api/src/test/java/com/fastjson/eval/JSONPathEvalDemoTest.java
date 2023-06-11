package com.fastjson.eval;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONPathEvalDemoTest  {
    private static Logger logger = LoggerFactory.getLogger(JSONPathEvalDemoTest.class);


    private JSONPathEvalDemo jsonPathEvalDemo = new JSONPathEvalDemo();

    @Test
    public void testJsonEvalDemp() {
        jsonPathEvalDemo.jsonEvalDemp();
    }
}