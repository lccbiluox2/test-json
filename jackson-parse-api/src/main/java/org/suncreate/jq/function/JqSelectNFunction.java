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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="402711062@qq.com">sunxy</a>
 * @date 2022/1/14 9:40
 */
public class JqSelectNFunction implements Function {
    @Override
    public List<JsonNode> apply(Scope scope, List<JsonQuery> args, JsonNode in) throws JsonQueryException {
        Preconditions.checkInputType("selectN", in, JsonNodeType.ARRAY, JsonNodeType.NULL);
        int n = args.get(1).apply(scope, in).get(0).asInt();
        List<JsonNode> result = new ArrayList<>();
        for (JsonNode jsonNode : ((ArrayNode) in)) {
            if (args.get(0).apply(scope, jsonNode).get(0).asBoolean(false)) {
                result.add(jsonNode);
                if (result.size()==n){
                    break;
                }
            }
        }
        return result;
    }
}
