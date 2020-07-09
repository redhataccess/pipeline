package com.redhat.pipeline;

import com.redhat.common.markup.json.JsonUtils;
import org.json.JSONObject;

/**
 *
 * @author sfloess
 */
public enum PipelineMarkupEnum {
    NAME("name"),
    DESCRIPTION("description"),
    STEPS("steps");

    private final String name;

    PipelineMarkupEnum(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public <T> T getValue(final JSONObject json) {
        return JsonUtils.getValue(json, getName());
    }
}
