package org.suncreate.jq;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.thisptr.jackson.jq.JsonQuery;
import net.thisptr.jackson.jq.Scope;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author <a href="402711062@qq.com">sunxy</a>
 * @date 2022/1/10 19:18
 */
public class JqTest {

    private ObjectMapper MAPPER = new ObjectMapper();
    JsonNode in;

    @Before
    public void init() throws IOException {

        in = MAPPER.readTree("{\n" +
                "    \"cost\": 69,\n" +
                "    \"code\": 1,\n" +
                "    \"array\": [1,2,3],\n"+
                "    \"message\": \"获取到2条结果\",\n" +
                "    \"data\": {\n" +
                "      \"event1\": {\n" +
                "        \"malicious\": true,\n" +
                "        \"threat_type\": \"C2\",\n" +
                "        \"threat_subtype\": \"Trojan\",\n" +
                "        \"confidence_level\": 3,\n" +
                "        \"confidence_score\": 8,\n" +
                "        \"risk_level\": 5,\n" +
                "        \"tags\": [\"apt\", \"c2\", \"apt_NOBELIUM\", \"钓鱼网站\"],\n" +
                "        \"is_apt\": true,\n" +
                "        \"warn_name\": \"普通远控木马活动事件\",\n" +
                "        \"source_name\": \"安恒情报源\",\n" +
                "        \"create_time\": 1629110670936,\n" +
                "        \"update_time\": 1629110679304\n" +
                "      },\n" +
                "      \"event2\": {\n" +
                "        \"malicious\": true,\n" +
                "        \"threat_type\": \"C2\",\n" +
                "        \"threat_subtype\": \"Trojan\",\n" +
                "        \"confidence_level\": 3,\n" +
                "        \"confidence_score\": 8,\n" +
                "        \"risk_level\": 5,\n" +
                "        \"tags\": [\"apt\", \"c2\", \"apt_NOBELIUM\", \"钓鱼网站\"],\n" +
                "        \"is_apt\": true,\n" +
                "        \"warn_name\": \"普通远控木马活动事件\",\n" +
                "        \"source_name\": \"安恒情报源\",\n" +
                "        \"create_time\": 1629110670936,\n" +
                "        \"update_time\": 1629110679304\n" +
                "      }\n" +
                "    }\n" +
                "  }");

    }

    @Test
    public void jqTest() throws IOException {
        Scope scope = Scope.newEmptyScope();
        scope.loadFunctions(Thread.currentThread().getContextClassLoader());
        //以漂亮的方式输出
        System.out.println(JsonQuery.compile(".").apply(scope,in));
        //取一个key的值
        System.out.println(JsonQuery.compile(".cost").apply(scope,in));
        //嵌套取值
        System.out.println(JsonQuery.compile(".data.event1").apply(scope,in));
        System.out.println(JsonQuery.compile(".data.event1.tags[0]").apply(scope,in));
        //列表切片取值
        System.out.println(JsonQuery.compile(".data.event1.tags[0:2]").apply(scope,in));
        //构建一个数组
        System.out.println(JsonQuery.compile(".data[]").apply(scope,in));
        System.out.println(JsonQuery.compile(".data.event1.tags[]").apply(scope,in));
        //构建一个对象
        System.out.println(JsonQuery.compile("{s:.}").apply(scope,in));
        //计算一个值的长度
        System.out.println(JsonQuery.compile(".|length").apply(scope,in));
        //取出一个对象中所有的key
        System.out.println(JsonQuery.compile(".|keys").apply(scope,in));
        //多个过滤器
        System.out.println(JsonQuery.compile(".cost,.code").apply(scope,in));
        //通过管道将一个过滤器的输出当做下一个过滤器的输入
        System.out.println(JsonQuery.compile(".data|.event1").apply(scope,in));
        //过滤
        System.out.println(JsonQuery.compile("select(.cost<0)").apply(scope,in));
        //字符串拼接
        System.out.println(JsonQuery.compile("\"hello \\(.cost)\"").apply(scope,in));
        //条件判断
        System.out.println(JsonQuery.compile("if .cost == 0 then \"zero\" elif . == 1 then \"one\" else \"many\" end").apply(scope,in));
        //删除某个字段
        System.out.println(JsonQuery.compile(".|del(.array)").apply(scope,in));
        //添加字段
        System.out.println(JsonQuery.compile(".+{hello:\"world\"}").apply(scope,in));
        //元素赋值
        System.out.println(JsonQuery.compile(".|.array=\"hello\"").apply(scope,in));

    }
}
