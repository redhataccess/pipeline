package com.redhat.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.json.JSONObject;

/**
 * JSON Utilities.
 *
 * @author sfloess
 */
public final class JsonUtils {
    public static ObjectMapper configureMapper(final ObjectMapper objectMapper) throws Exception {
        return Objects.requireNonNull(objectMapper, "Mapper cannot be null").configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static ObjectMapper createMapper() throws Exception {
        return configureMapper(new ObjectMapper());
    }

    public static JSONObject jsonStrToJsonObject(final String jsonStr) throws Exception {
        return createMapper().readValue(jsonStr, JSONObject.class);
    }

    public static ObjectMapper createXmlMapper() throws Exception {
        return configureMapper(new ObjectMapper(new XmlFactory()));
    }

    public static <T> T xmlStrToObject(final String xmlStr, final Class<T> klass) throws Exception {
        return createXmlMapper().readValue(xmlStr, klass);
    }

    public static JSONObject xmlStrToJsonObject(final String xmlStr) throws Exception {
        return new JSONObject(createXmlMapper().readValue(xmlStr, HashMap.class));
    }

    public static ObjectMapper createYamlMapper() throws Exception {
        return configureMapper(new ObjectMapper(new YAMLFactory()));
    }

    public static <T> T yamlStrToObject(final String yamlStr, final Class<T> klass) throws Exception {
        return createYamlMapper().readValue(yamlStr, klass);
    }

    public static JSONObject yamlStrToJsonObject(final String yamlStr) throws Exception {
        return new JSONObject(createYamlMapper().readValue(yamlStr, HashMap.class));
    }

    public static <T> T jsonStrToObject(final String jsonStr, final Class<T> klass) throws Exception {
        return createMapper().readValue(jsonStr, klass);
    }

    public static <T> T jsonObjectToObject(final JSONObject json, final Class<T> klass) throws Exception {
        return jsonStrToObject(Objects.requireNonNull(json, "JSON Object cannot be null!").toString(), klass);
    }

    /**
     * Convert all maps in list to JSONObjects in jsonList.
     */
    public static List<JSONObject> populateJsonList(final List<JSONObject> jsonList, final List<Map> list) {
        Objects.requireNonNull(jsonList, "Cannot have a null list to populate!");
        Objects.requireNonNull(list, "Cannot have a null list from which to copy from!");

        for (final Map map : list) {
            jsonList.add(new JSONObject(map));
        }

        return jsonList;
    }

    public static List<JSONObject> toJsonList(final List<Map> list) {
        return populateJsonList(new ArrayList<>(list.size()), Objects.requireNonNull(list, "Cannot have a null list to convert!"));
    }

    public static <T> T getValue(final JSONObject json, final String name, final T defaultValue) {
        if (json.isNull(name)) {
            return defaultValue;
        }

        return (T) json.get(name);
    }

    public static <T> T getValue(final JSONObject json, final String name) {
        return getValue(json, name, null);
    }

    /**
     * Default utilities not allowed.
     */
    private JsonUtils() {
    }
}
