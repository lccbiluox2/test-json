package org.suncreate.jq.function;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.thisptr.jackson.jq.Function;
import net.thisptr.jackson.jq.JsonQuery;
import net.thisptr.jackson.jq.Scope;
import net.thisptr.jackson.jq.exception.JsonQueryException;
import net.thisptr.jackson.jq.internal.misc.Preconditions;
import org.suncreate.jq.JacksonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="402711062@qq.com">sunxy</a>
 * @date 2022/1/14 13:50
 */
public class JqValueInsideFunction implements Function {
    @Override
    public List<JsonNode> apply(Scope scope, List<JsonQuery> args, JsonNode in) throws JsonQueryException {
        Preconditions.checkInputType("valueInside", in, JsonNodeType.OBJECT, JsonNodeType.NULL);
        JsonNode jsonNode = args.get(0).apply(scope, in).get(0);
        List<JsonNode> result = new ArrayList<>();

        ArrayNode jsonNodes = JacksonUtil.requireType(jsonNode, ArrayNode.class);
        for (JsonNode node : jsonNodes) {
            ObjectNode insideNode = JacksonUtil.requireType(node, ObjectNode.class);
            for (int i = 1; i < args.size(); i++) {
                ObjectNode value = JacksonUtil.requireType(args.get(i).apply(scope, in).get(0), ObjectNode.class);
                insideNode.setAll(value);
            }
            result.add(insideNode);
        }
        return Arrays.asList(JacksonUtil.MAPPER.valueToTree(result));
    }
}
