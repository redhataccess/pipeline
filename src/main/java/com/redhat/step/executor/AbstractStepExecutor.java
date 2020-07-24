package com.redhat.step.executor;

import com.redhat.common.markup.json.JsonUtils;
import com.redhat.common.processor.executor.AbstractExecutor;
import com.redhat.common.utils.Strings;
import com.redhat.common.utils.VariableExpansionUtils;
import com.redhat.step.Step;
import com.redhat.step.StepContext;
import com.redhat.step.StepExecutor;
import java.util.Map;
import java.util.Objects;
import org.json.JSONObject;

/**
 *
 * @author sfloess
 *
 * Copyright © 2019 Red Hat, Inc.
 */
public class AbstractStepExecutor extends AbstractExecutor<StepContext, Step> implements StepExecutor {

    /**
     * Store the return value in the pipeline context.
     */
    StepContext storeResultValue(final StepContext toProcess, final Object returnValue) {
        Objects.requireNonNull(toProcess, "Must provide an object to process!");

        toProcess.getPipelineContext().setResult(Strings.isString(returnValue) ? VariableExpansionUtils.replaceVars(returnValue.toString(), toProcess) : returnValue);

        return toProcess;
    }

    /**
     * Was a result element present?
     */
    boolean isResultPresent(final JSONObject metaStep) {
        return null != metaStep && !metaStep.isNull("result");
    }

    /**
     * We will store a result if the result eleement is present.
     */
    StepContext storeResult(final StepContext toProcess, final JSONObject metaStep) {
        Objects.requireNonNull(toProcess, "Must provide an object to process!");
        Objects.requireNonNull(metaStep, "Must provide a meta step (JSONObject) to examine for a result element!");

        return !isResultPresent(metaStep) ? toProcess : storeResultValue(toProcess, metaStep.get("result"));
    }

    /**
     * Execute a step name <code>stepName</code>. We may desire preprocessing depending on <code>isPreProcessable</code>.
     */
    StepContext executeStep(final StepContext toProcess, final String stepName, boolean isPreProcessable, final JSONObject initState) {
        logIfDebug("Executing step [", stepName, "]\n    -> ", initState.toString(4));

        return storeResult(
                toProcess.getStepDefinitions().create(
                        stepName, isPreProcessable ? toProcess.getStepPreprocessor().prepare(initState, toProcess) : initState
                ).process(toProcess), initState);
    }

    /**
     * Will create a step whose name is <code>stepName</code> using <code>metaStep</code> as it's initial state.
     *
     * @param toProcess the context to process.
     * @param stepName  the name of the step to execute.
     * @param metaStep  the initial state of the step.
     *
     * @return the context.
     */
    StepContext executeStepForName(final StepContext toProcess, final String stepName, final JSONObject metaStep) {
        Objects.requireNonNull(toProcess, "Must provide an object to process!");
        Strings.require("stepName", "Step name cannot be null or blank!");
        Objects.requireNonNull(metaStep, "Must provide a meta version of a step (initial version)!");

        return executeStep(toProcess, stepName, !JsonUtils.getValue(metaStep, "isPreparable", false), JsonUtils.getValue(metaStep, stepName));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected StepContext doProcess(Step processor, StepContext toProcess) {
        return executeMetaStep(toProcess, new JSONObject(this));
    }

    /**
     * Default constructor.
     */
    protected AbstractStepExecutor() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StepContext executeMetaStep(final StepContext toProcess, final JSONObject metaStep) {
        Objects.requireNonNull(toProcess, "Must provide an object to process!");
        Objects.requireNonNull(metaStep, "Must provide a meta version of a step (initial version)!");

        // The metaStep contains an args element.  This contains the actual unmarshalled version
        // of the class backing the step.
        return executeStepForName(toProcess, metaStep.keySet().toArray()[0].toString(), metaStep);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StepContext executeMetaStepMap(final StepContext toProcess, final Map metaStep) {
        Objects.requireNonNull(toProcess, "Must provide an object to process!");
        Objects.requireNonNull(metaStep, "Must provide a meta version of a step (initial version)!");

        return executeMetaStep(toProcess, new JSONObject(metaStep));
    }
}
