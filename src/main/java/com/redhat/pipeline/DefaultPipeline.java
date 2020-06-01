package com.redhat.pipeline;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Default pipeline.
 *
 * @author sfloess
 *
 * Copyright © 2019 Red Hat, Inc.
 */
public final class DefaultPipeline extends AbstractPipeline {
    /**
     * This constructor sets our JSON backed pipeline.
     *
     * @param metaPipeline JSON representation of a pipeline.
     *
     * @throws IllegalArgumentException if metaPipeline is null.
     */
    public DefaultPipeline(final JSONObject metaPipeline, final boolean clearStepVars) {
        super(metaPipeline, clearStepVars);
    }

    /**
     * This constructor sets our JSON backed pipeline.
     *
     * @param metaPipeline JSON representation of a pipeline.
     *
     * @throws IllegalArgumentException if metaPipeline is null.
     */
    public DefaultPipeline(final JSONObject metaPipeline) {
        this(metaPipeline, true);
    }

    /**
     * This constructor sets our JSON backed pipeline and assumes the steps are those denoted in metaPipeline.
     *
     * @param metaPipeline steps for the meta pipeline.
     *
     * @throws IllegalArgumentException if metaPipeline is null.
     */
    public DefaultPipeline(final List<JSONObject> metaPipeline, final boolean clearStepVars) {
        super(metaPipeline, clearStepVars);
    }

    /**
     * This constructor sets our JSON backed pipeline and assumes the steps are those denoted in metaPipeline.
     *
     * @param metaPipeline steps for the meta pipeline.
     *
     * @throws IllegalArgumentException if metaPipeline is null.
     */
    public DefaultPipeline(final List<JSONObject> metaPipeline) {
        this(metaPipeline, true);
    }

    /**
     * This constructor sets our JSON backed pipeline and assumes the steps are those denoted in metaPipeline.
     *
     * @param metaPipeline steps for the meta pipeline.
     *
     * @throws IllegalArgumentException if metaPipeline is null.
     */
    public DefaultPipeline(final JSONArray metaPipeline, final boolean clearStepVars) {
        super(metaPipeline, clearStepVars);
    }

    /**
     * This constructor sets our JSON backed pipeline and assumes the steps are those denoted in metaPipeline.
     *
     * @param metaPipeline steps for the meta pipeline.
     *
     * @throws IllegalArgumentException if metaPipeline is null.
     */
    public DefaultPipeline(final JSONArray metaPipeline) {
        this(metaPipeline, true);
    }
}
