package org.suncreate.jq.function;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.TextNode;
import net.thisptr.jackson.jq.Function;
import net.thisptr.jackson.jq.JsonQuery;
import net.thisptr.jackson.jq.Scope;
import net.thisptr.jackson.jq.exception.JsonQueryException;
import net.thisptr.jackson.jq.internal.misc.Preconditions;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author <a href="402711062@qq.com">sunxy</a>
 * @date 2022/1/14 9:43
 */
public class JqLongToDateStrFunction implements Function {
    private Map<String, SimpleDateFormat> dateFormatCache = new HashMap();
    private final static String DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    @Override
    public List<JsonNode> apply(Scope scope, List<JsonQuery> args, JsonNode in) throws JsonQueryException {
        Preconditions.checkInputType("longToDateStr", in, JsonNodeType.OBJECT, JsonNodeType.NULL);
        List<JsonNode> apply = args.get(0).apply(scope, in);
        SimpleDateFormat dateFormat = dateFormatCache.getOrDefault(DEFAULT_FORMAT, new SimpleDateFormat(DEFAULT_FORMAT));
        List<JsonNode> result = new ArrayList<>();
        for (JsonNode jsonNode : apply) {
            if (!jsonNode.isLong()) {
                throw JsonQueryException.format("value is not long %s",jsonNode);
            }
            String format = dateFormat.format(new Date(jsonNode.asLong()));
            result.add(new TextNode(format));
        }
        return result;
    }
}
