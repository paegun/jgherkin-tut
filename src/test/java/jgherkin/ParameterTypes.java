package jgherkin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.ParameterType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ParameterTypes {
    @ParameterType(value = "true|True|TRUE|false|False|FALSE")
    public Boolean booleanValue(String value) {
        return Boolean.valueOf(value);
    }

    @ParameterType(value = "[{].*[}]")
    public HashMap<String, String> stringDictionaryValue(String value) {
        JsonObject jsonOject = JsonParser.parseString(value).getAsJsonObject();
        HashMap<String, String> values = new HashMap<>();
        for(String key: jsonOject.keySet()) {
            values.put(key, jsonOject.get(key).toString());
        }
        return values;
    }

    @ParameterType(value = "<.*>")
    public List<String> stringListValue(String value) {
        return Arrays.asList(value.substring(1, value.length() - 2).split(","));
    }

    @ParameterType(value = ".*")
    public String stringValue(String value) {
        if (value.equals("<null>")) value = null;
        return value;
    }
}
