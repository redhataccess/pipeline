package com.redhat.pipeline;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.redhat.common.processor.meta.AbstractMetaProcessor;
import com.redhat.common.utils.JsonUtils;
import com.redhat.common.utils.Strings;
import com.redhat.step.Step;
import java.util.List;
import java.util.Objects;
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
public abstract class Bak_AbstractPipeline1 extends AbstractMetaProcessor<PipelineContext> implements Pipeline {

    /**
     * Flag if true, we will clear all step vars per run of each step.
     */
    @JsonIgnore
    private final boolean clearStepVars;

    /**
     * Do we clear step variables for each step?
     */
    @JsonIgnore
    protected boolean isClearStepVars() {
        return clearStepVars;
    }

    /**
     * Set the name and pipeline steps for the meta pipeline.
     *
     * @param retVal the object to return.
     * @param steps  a list of steps.
     *
     * @return a populated JSONObject.
     */
    JSONObject populateMetaPipeline(final JSONObject retVal, final JSONArray steps) {
        retVal.put(STEP_NAME_ATTRIBUTE, "Annonymously defined pipeline " + hashCode());
        retVal.put(STEP_DESCRIPTION_ATTRIBUTE, "An annonymously defined pipeline " + hashCode());

        retVal.put(PIPELINE_STEPS_ELEMENT, steps);

        return retVal;
    }

    /**
     * Set the name and pipeline steps for the meta pipeline.
     *
     * @param retVal the object to return.
     * @param steps  a list of steps.
     *
     * @return a populated JSONObject.
     */
    JSONObject populateMetaPipeline(final JSONObject retVal, final List<JSONObject> steps) {
        final JSONArray jsonArray = new JSONArray();
        for (final JSONObject metaStep : steps) {
            jsonArray.put(metaStep);
        }

        return populateMetaPipeline(retVal, jsonArray);
    }

    /**
     * Convert these steps to a meta pipeline.
     *
     * @param steps all the steps in the pipeline.
     *
     * @return a meta pipeline.
     */
    JSONObject toMetaPipeline(final JSONArray steps) {
        return populateMetaPipeline(new JSONObject(), steps);
    }

    /**
     * Convert these steps to a meta pipeline.
     *
     * @param steps all the steps in the pipeline.
     *
     * @return a meta pipeline.
     */
    JSONObject toMetaPipeline(final List<JSONObject> steps) {
        return populateMetaPipeline(new JSONObject(), steps);
    }

    /**
     * Set a result if there is one to set. If result is null, disregard.
     *
     * @param resultVar the name of our step variable that holds a result to set on <code>context</code>.
     * @param context   holds the result.
     */
    void setResult(final String resultVar, final PipelineContext context) {
        if (null != resultVar) {
            context.setResult(context.getStepContext().getStepVars().get(resultVar));
        }
    }

    /**
     * The step to process. We'll do the whole step life cycle here: pre/process/[post or post failure] processing.
     *
     * @param step      the object to process <code>context</code>.
     * @param resultVar the name of the result var to pull from the step vars to store as a result. If not set (meaning null), then we will not change
     *                  the result stored in context.
     * @param context   the object <code>step</code> is to process.
     *
     * @throws Exception if any problems arise using <code>step</code> to process <code>context</code>
     */
    void processStep(final Step step, final String resultVar, final PipelineContext context) {
        Objects.requireNonNull(step, "Cannot provide a null step object for processing!");
        Objects.requireNonNull(context, "Cannot provide a null context for processing!");

        if (isClearStepVars()) {
            context.getStepContext().getStepVars().clear();
        }

        try {
            step.preProcess(context);

            step.process(context);

            setResult(resultVar, context);

            step.postProcess(context);
        } catch (final Exception exception) {
            step.postProcessFailure(context, exception);
        }
    }

    /**
     * Processes a step backed by metaStep. This will be used to create a Step impl.
     *
     * @param metaStep  JSON backed version of a step.
     * @param stepName  the name of the step to execute.
     * @param resultVar the var who will contain the result in the step context's variables.
     * @param context   the object to operate upon.
     *
     * @throws Exception if any problems arise processing the step.
     */
    void processMetaStep(final JSONObject metaStep, final String stepName, final String resultVar, final PipelineContext context) {
        Objects.requireNonNull(metaStep, "Cannot provide a null JSON object to process for meta-step!");
        Strings.require(stepName, "Must provide a value for stepName in order to create/execute a step!");
        Objects.requireNonNull(context, "Cannot provide a null context object for processing for meta-step!");

        processStep(context.getStepContext().getStepDefinitions().create(stepName, context.getStepContext().getStepPreprocessor().preprocess(metaStep, context)), resultVar, context);
    }

    /**
     * Process a step whose backed by <code>metaStep</code>. We are after two items: The name attribute abd (to associate with the step impl) and the
     * args subelement.
     *
     * @param metaStep contains all we need to construct a Step impl (name attribute
     * @param context
     *
     * @throws Exception
     */
    void processMetaPipelineStep(final JSONObject metaStep, final PipelineContext context) {
        Objects.requireNonNull(metaStep, "Cannot provide a null JSON object to process!");
        Objects.requireNonNull(context, "Cannot provide a null context object for processing!");

        processMetaStep(JsonUtils.getValue(metaStep, STEP_ARGS_ELEMENT), JsonUtils.getValue(metaStep, STEP_NAME_ATTRIBUTE), JsonUtils.getValue(metaStep, STEP_RESULT_NAME_ATTRIBUTE), context);
    }

    /**
     * Process all steps denoted and backed by <code>metaPipeline</code>.
     *
     * @param metaPipeline a collection of steps denoted in JSON.
     * @param context      state to use when processing each step within <code>metaPipeline</code>.
     *
     * @return the context.
     *
     * @throws Exception if any problem arise processing <code>context</code>.
     */
    PipelineContext processMetaPipelineSteps(final JSONArray metaPipeline, final PipelineContext context) throws Exception {
        Objects.requireNonNull(metaPipeline, "Cannot provide a null JSON array!");

        for (int index = 0; index < metaPipeline.length(); index++) {
            processMetaPipelineStep(metaPipeline.getJSONObject(index), context);
        }

        return context;
    }

    /**
     * This constructor sets our JSON backed pipeline.
     *
     * @param metaPipeline JSON representation of a pipeline.
     *
     * @throws IllegalArgumentException if metaPipeline is null.
     */
    protected Bak_AbstractPipeline1(final JSONObject metaPipeline, final boolean clearStepVars) {
        this.metaPipeline = new JSONObject(Objects.requireNonNull(metaPipeline, "Cannot provide a null JSON object!").toString());
        this.clearStepVars = clearStepVars;
    }

    /**
     * This constructor sets our JSON backed pipeline.
     *
     * @param metaPipeline JSON representation of a pipeline.
     *
     * @throws IllegalArgumentException if metaPipeline is null.
     */
    protected Bak_AbstractPipeline1(final List<JSONObject> metaPipeline, final boolean clearStepVars) {
        this.metaPipeline = toMetaPipeline(metaPipeline);
        this.clearStepVars = clearStepVars;
    }

    /**
     * This constructor sets our JSON backed pipeline.
     *
     * @param metaPipeline JSON representation of a pipeline.
     *
     * @throws IllegalArgumentException if metaPipeline is null.
     */
    protected Bak_AbstractPipeline1(final JSONArray metaPipeline, final boolean clearStepVars) {
        this.metaPipeline = toMetaPipeline(metaPipeline);
        this.clearStepVars = clearStepVars;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PipelineContext process(final PipelineContext context) {
        return Objects.requireNonNull(context, "Cannot have a null context to process!").getPipelineExecutor().executeProcessor(this, context);
    }
}
