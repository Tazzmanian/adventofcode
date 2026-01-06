package com.tazz.adventofcode.y2015.day12;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class JsonRedSum {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static int sumIgnoringRedObjects(String json) {
        try {
            JsonNode root = MAPPER.readTree(json);
            return sum(root);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON", e);
        }
    }

    private static int sum(JsonNode node) {
        if (node == null || node.isNull()) return 0;

        if (node.isNumber()) {
            return node.intValue();
        }

        if (node.isArray()) {
            int s = 0;
            for (JsonNode child : node) s += sum(child);
            return s;
        }

        if (node.isObject()) {
            // If ANY property value is the string "red", ignore the whole object
            Iterator<JsonNode> fields = node.iterator();
            while (fields.hasNext()) {
                JsonNode v = fields.next();
                if (v.isTextual() && "red".equals(v.textValue())) {
                    return 0;
                }
            }
            // Otherwise, sum all values
            int s = 0;
            for (JsonNode v : node) s += sum(v);
            return s;
        }

        // strings/booleans/etc
        return 0;
    }
}
