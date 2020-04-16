package com.redhat.common;

import com.redhat.common.utils.Strings;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Our default pipeline implementation. Manages all pipelines.
 *
 * @author sfloess
 */
public abstract class AbstractJsonDefinitions<T> extends AbstractBase implements JsonDefinitions<T> {
    /**
     * All JSON definitions - the key is the name of the pipeline and the value is the pipeline itself.
     */
    private final Map<String, T> defs;

    /**
     * Return all our defined pipelines.
     */
    protected Map<String, T> getDefMap() {
        return defs;
    }

    /**
     * Ensures a pipeline named name exists.
     */
    protected String ensureDefinitionName(final String name) {
        return Strings.require(name, "Must provide a name");
    }

    /**
     * Default constructor.
     */
    protected AbstractJsonDefinitions() {
        this.defs = new ConcurrentHashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getDefinitions() {
        return Collections.unmodifiableList(new LinkedList(getDefMap().values()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getDefinition(String name) {
        return getDefMap().get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeDefinition(String name) {
        return getDefMap().remove(name);
    }
}
