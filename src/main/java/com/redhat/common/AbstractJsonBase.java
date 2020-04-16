package com.redhat.common;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract base class for JSON Objects
 *
 * @author sfloess
 */
public abstract class AbstractJsonBase extends AbstractBase {
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonIgnore
    private List<String> orderedAdditionalProperties = new ArrayList<>();

    @JsonAnyGetter
    protected Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnyGetter
    protected List<String> getOrderedAdditionalProperties() {
        return this.orderedAdditionalProperties;
    }

    @JsonAnySetter
    protected void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        this.orderedAdditionalProperties.add(name);

        logWarning("Received unmanaged property [", name, "] -> [", value, "]");

        processUnmanagedProperty(name, value);
    }

    protected void processUnmanagedProperty(final String name, final Object value) {
    }
}
