package org.suncreate.jq;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.thisptr.jackson.jq.JsonQuery;
import net.thisptr.jackson.jq.Scope;
import org.junit.Test;
import org.suncreate.FileUtil;
import org.suncreate.jq.function.JqKeyInsideFunction;
import org.suncreate.jq.function.JqLongToDateStrFunction;
import org.suncreate.jq.function.JqSelectNFunction;
import org.suncreate.jq.function.JqValueInsideFunction;

import java.io.*;

/**
 * @author <a href="402711062@qq.com">sunxy</a>
 * @date 2022/1/12 9:59
 */
public class IocTest {
    private ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void parseWb() throws IOException {
        String s = FileUtil.readText("src/main/resources/weiBu.jq", s1 -> !s1.startsWith("//"));
        String wb = FileUtil.readText("src/main/resources/weiBu.json");
        JsonNode in = MAPPER.readTree(wb);
        Scope scope = Scope.newEmptyScope();
        scope.addFunction("selectN",new JqSelectNFunction());
        scope.addFunction("valueInside",new JqValueInsideFunction());
        scope.loadFunctions(Thread.currentThread().getContextClassLoader());
        System.out.println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(JsonQuery.compile(s).apply(scope,in)));
    }

    @Test
    public void parseAh() throws IOException {
        String s = FileUtil.readText("src/main/resources/anheng.jq",s1 -> !s1.startsWith("//"));
        String wb = FileUtil.readText("src/main/resources/anheng.json");
        JsonNode in = MAPPER.readTree(wb);
        Scope scope = Scope.newEmptyScope();
        scope.addFunction("keyInside",new JqKeyInsideFunction());
        scope.addFunction("jqLongToDateStr",new JqLongToDateStrFunction());
        scope.loadFunctions(Thread.currentThread().getContextClassLoader());
        System.out.println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(JsonQuery.compile(s).apply(scope,in)));
    }
}
