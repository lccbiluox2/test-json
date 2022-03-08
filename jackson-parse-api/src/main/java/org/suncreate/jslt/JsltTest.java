package org.suncreate.jslt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schibsted.spt.data.jslt.Expression;
import com.schibsted.spt.data.jslt.Parser;
import org.junit.Test;
import org.suncreate.FileUtil;

import java.io.IOException;

/**
 * @author <a href="402711062@qq.com">sunxy</a>
 * @date 2022/1/12 19:30
 */
public class JsltTest {
    private ObjectMapper MAPPER = new ObjectMapper();


    @Test
    public void jsltTest() throws IOException {
        String json = FileUtil.readText("src/main/resources/weibu.json");
        String grammar = FileUtil.readText("src/main/resources/jsltGrammar.jslt");
        JsonNode in = MAPPER.readTree(json);
        Expression jslt = Parser.compileString(grammar);
        JsonNode output = jslt.apply(in);
        System.out.println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(output));
    }
}
