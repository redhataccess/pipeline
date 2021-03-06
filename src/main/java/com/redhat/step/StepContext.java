package com.redhat.step;

import com.redhat.common.context.VarContext;
import com.redhat.pipeline.PipelineContext;

/**
 * Context for executing a pipeline. Maintains external state for the pipeline and its steps.
 *
 * @author sfloess
 */
public interface StepContext {
    /**
     * All our step definitions.
     */
    StepDefinitions getStepDefinitions();

    /**
     * Can execute a step.
     */
    StepExecutor getStepExecutor();

    /**
     * The object in charge of preprocessing and preparing a step to be used for processing.
     */
    StepPreprocessor getStepPreprocessor();

    /**
     * Return all our step vars. These are variables only visible to the currently executing step.
     */
    VarContext getStepVars();

    /**
     * Return the pipeline context for whom we are defined by.
     */
    PipelineContext getPipelineContext();

    /**
     * Use this method sparingly.
     */
    void setPipelineContext(PipelineContext context);
}
