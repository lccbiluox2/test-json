package org.suncreate.jq.function;

import com.fasterxml.jackson.databind.JsonNode;
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
public class JqKeyInsideFunction implements Function {
    @Override
    public List<JsonNode> apply(Scope scope, List<JsonQuery> args, JsonNode in) throws JsonQueryException {
        Preconditions.checkInputType("keyInside", in, JsonNodeType.OBJECT, JsonNodeType.NULL);
        List<JsonNode> jsonNodes=null;
        String newKey = null;
        if (args.size()>=1) {
            jsonNodes = args.get(0).apply(scope,in);
        }
        if (args.size()>=2){
            newKey = args.get(1).apply(scope,in).get(0).asText();
        }
        return call(jsonNodes,newKey);
    }

    private List<JsonNode> call(List<JsonNode> jsonNodes,String newKey) throws JsonQueryException {
        newKey = newKey==null?"key":newKey;
        List<JsonNode> results = new ArrayList<>();
        for (JsonNode jsonNode : jsonNodes) {
            if (!jsonNode.isObject()){
                throw JsonQueryException.format("value is not object %s",jsonNode);
            }
            Iterator<Map.Entry<String, JsonNode>> iterator = jsonNode.fields();
            while (iterator.hasNext()) {
                Map.Entry<String, JsonNode> next = iterator.next();
                String key = next.getKey();
                JsonNode value = next.getValue();
                if (value.isObject()) {
                    results.add(((ObjectNode) value).put(newKey, key));
                }
            }
        }
        return results;
    }
}
