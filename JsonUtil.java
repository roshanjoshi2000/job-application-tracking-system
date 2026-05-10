package com.jats.util;

import java.util.Map;

public class JsonUtil {

    private JsonUtil() {
    }

    public static String toJson(Map<String, String> data) {
        StringBuilder json = new StringBuilder();
        json.append("{");

        int count = 0;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            json.append("\"")
                .append(entry.getKey())
                .append("\":\"")
                .append(entry.getValue())
                .append("\"");

            count++;
            if (count < data.size()) {
                json.append(",");
            }
        }

        json.append("}");
        return json.toString();
    }

    public static String simpleMessage(String message) {
        return "{\"message\":\"" + message + "\"}";
    }
}
