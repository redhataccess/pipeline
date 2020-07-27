package com.redhat.common.context;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Abstract base class for contexts.
 *
 * @author sfloess
 *
 * Copyright © 2019 Red Hat, Inc.
 */
public abstract class AbstractVarContext implements VarContext {

    /**
     * Holds our values.
     */
    private final Map contextMap;

    /**
     * Returns our mapping.
     */
    protected Map getContextMap() {
        return contextMap;
    }

    /**
     * This constructor copies the contents of <code>contextMap</code>.
     */
    protected AbstractVarContext(final Map contextMap) {
        this.contextMap = new ConcurrentHashMap(contextMap);
    }

    /**
     * Default constructor.
     */
    protected AbstractVarContext() {
        this(Collections.EMPTY_MAP);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T get(final String name) {
        return (T) getContextMap().get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T get(final String name, final T defaultValue) {
        return (T) getContextMap().getOrDefault(name, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T set(final String name, final T value) {
        if (value != null) {
            return (T) getContextMap().put(name, value);
        }

        return (T) getContextMap().remove(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T remove(final String name) {
        return (T) getContextMap().remove(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        getContextMap().clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, ?> asMap() {
        return getContextMap();
    }

    public String toString() {
        return getContextMap().toString();
    }
}
