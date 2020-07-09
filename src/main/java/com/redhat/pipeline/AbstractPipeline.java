package com.redhat.pipeline;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.redhat.common.markup.json.JsonUtils;
import com.redhat.common.processor.AbstractProcessor;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Abstract base class for pipelines. Please note, we use JSON based Java objects as a decoupled representation of a
 * pipeline and it's steps. Why? Well it easily accommodates different markups as a data structure :)
 * <p>
 * When dealing with steps, we assume a JSON object that contains:
 * <ul>
 * <li>a required name attribute</li>
 * <li>an optional description attribute</li>
 * <li>an optional results attribute</li>
 * <li>a required args attribute</li>
 * </ul>
 * <p>
 * Notes:
 * <ul>
 * <li>The <code>args</code> attribute contains the "state" of the step: things it needs to operate. Think of it as the external state of a step.</li>
 * <li>If the <code>results</code> attribute is set, the value of that attribute is used to pull a value out of step vars and store as the result. If
 * not set, we ignore and do not affect change to the result.</li>
 * </ul>
 *
 * @author sfloess
 *
 * Copyright © 2019 Red Hat, Inc.
 */
public abstract class AbstractPipeline extends AbstractProcessor<PipelineContext> implements Pipeline {
    /**
     * Default prefix for name and description.
     */
    private static final String DEFAULT_ID_PREFIX = "Annonymously defined pipeline ";

    /**
     * JSON representation of a pipeline for processing.
     */
    @JsonIgnore
    private JSONObject metaPipeline;

    /**
     * Flag if true, we will clear all step vars per run of each step.
     */
    @JsonIgnore
    private final boolean clearStepVars;

    /**
     * Mutator constructor.
     *
     * @paran meta is our JSON representation.
     */
    protected AbstractPipeline(final JSONObject metaPipeline, final String id, final boolean clearStepVars) {
        this.metaPipeline = Objects.requireNonNull(metaPipeline, "Cannot have a null representation!");
        this.clearStepVars = clearStepVars;

        JsonUtils.put(this.metaPipeline, PipelineMarkupEnum.NAME.getName(), id);
        JsonUtils.put(this.metaPipeline, PipelineMarkupEnum.DESCRIPTION.getName(), id);
    }

    /**
     * Mutator constructor.
     *
     * @paran meta is our JSON representation.
     */
    protected AbstractPipeline(final JSONObject metaPipeline, final boolean clearStepVars) {
        this(metaPipeline, StringUtils.join(DEFAULT_ID_PREFIX, System.currentTimeMillis()), clearStepVars);
    }

    /**
     * Mutator constructor.
     *
     * @paran meta is our JSON representation.
     */
    protected AbstractPipeline(final JSONObject metaPipeline) {
        this(metaPipeline, false);
    }

    /**
     * Mutator constructor.
     *
     * @paran meta is our JSON representation.
     */
    protected AbstractPipeline(final JSONArray metaPipeline, final boolean clearStepVars) {
        this(JsonUtils.toJsonObject(PipelineMarkupEnum.STEPS.getName(), metaPipeline), clearStepVars);
    }

    /**
     * Mutator constructor.
     *
     * @paran meta is our JSON representation.
     */
    protected AbstractPipeline(final JSONArray metaPipeline) {
        this(metaPipeline, false);
    }

    /**
     * Mutator constructor.
     *
     * @paran meta is our JSON representation.
     */
    protected AbstractPipeline(final List<JSONObject> metaPipeline, final boolean clearStepVars) {
        this(JsonUtils.toJsonObject(PipelineMarkupEnum.STEPS.getName(), metaPipeline), clearStepVars);
    }

    /**
     * Mutator constructor.
     *
     * @paran meta is our JSON representation.
     */
    protected AbstractPipeline(final List<JSONObject> metaPipeline) {
        this(metaPipeline, false);
    }

    /**
     * Do we clear step variables for each step?
     */
    @JsonIgnore
    protected boolean isClearStepVars() {
        return clearStepVars;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PipelineContext process(final PipelineContext context) {
        return Objects.requireNonNull(context, "Cannot have a null context to process!").getPipelineExecutor().executeProcessor(this, context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JSONObject asJsonObject() {
        return new JSONObject(metaPipeline.toString());
    }
}
